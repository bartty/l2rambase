/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager.SeedProduction;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.instance.L2ManorManagerInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.InventoryUpdate;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.item.L2Item;
import l2.rambase.gameserver.util.Util;

import static l2.rambase.gameserver.model.actor.L2Npc.INTERACTION_DISTANCE;
import static l2.rambase.gameserver.model.itemcontainer.PcInventory.MAX_ADENA;

/**
 * Format: cdd[dd]
 * c    // id (0xC4)
 *
 * d    // manor id
 * d    // seeds to buy
 * [
 * d    // seed id
 * q    // count
 * ]
 * @param decrypt
 */



public class RequestBuySeed extends L2GameClientPacket
{
	private static final String _C__C4_REQUESTBUYSEED = "[C] C4 RequestBuySeed";

	private static final int BATCH_LENGTH = 12; // length of the one item

	private int _manorId;
	private Seed[] _seeds = null;

	@Override
	protected void readImpl()
	{
		_manorId = readD();

		int count = readD();
		if (count <= 0
				|| count > Config.MAX_ITEM_IN_PACKET
				|| count * BATCH_LENGTH != _buf.remaining())
		{
			return;
		}

		_seeds = new Seed[count];
		for (int i = 0; i < count; i++)
		{
			int itemId = readD();
			long cnt = readQ();
			if (cnt < 1)
			{
				_seeds = null;
				return;
			}
			_seeds[i] = new Seed(itemId, cnt);
		}
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;

		if (_seeds == null)
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		L2Object manager = player.getTarget();

		if (!(manager instanceof L2ManorManagerInstance))
			manager = player.getLastFolkNPC();

		if (!(manager instanceof L2ManorManagerInstance))
			return;

		if (!player.isInsideRadius(manager, INTERACTION_DISTANCE, true, false))
			return;

		Castle castle = CastleManager.getInstance().getCastleById(_manorId);

		long totalPrice = 0;
		int slots = 0;
		int totalWeight = 0;

		for (Seed i : _seeds)
		{
			if (!i.setProduction(castle))
				return;

			totalPrice += i.getPrice();

			if (totalPrice > MAX_ADENA)
			{
				Util.handleIllegalPlayerAction(player, "Warning!! Character "
						+ player.getName() + " of account "
						+ player.getAccountName() + " tried to purchase over "
						+ MAX_ADENA + " adena worth of goods.",
						Config.DEFAULT_PUNISH);
				return;
			}

			L2Item template = ItemTable.getInstance().getTemplate(i.getSeedId());
			totalWeight += i.getCount() * template.getWeight();
			if (!template.isStackable())
				slots += i.getCount();
			else if (player.getInventory().getItemByItemId(i.getSeedId()) == null)
				slots++;
		}

		if (!player.getInventory().validateWeight(totalWeight))
		{
			sendPacket(new SystemMessage(SystemMessageId.WEIGHT_LIMIT_EXCEEDED));
			return;
		}

		if (!player.getInventory().validateCapacity(slots))
		{
			sendPacket(new SystemMessage(SystemMessageId.SLOTS_FULL));
			return;
		}

		// Charge buyer
		if ((totalPrice < 0) || !player.reduceAdena("Buy", totalPrice, manager, false))
		{
			sendPacket(new SystemMessage(SystemMessageId.YOU_NOT_ENOUGH_ADENA));
			return;
		}

		// Adding to treasury for Manor Castle
		castle.addToTreasuryNoTax(totalPrice);

		// Proceed the purchase
		InventoryUpdate playerIU = new InventoryUpdate();
		for (Seed i : _seeds)
		{
			i.updateProduction(castle);

			// Add item to Inventory and adjust update packet
			L2ItemInstance item = player.getInventory().addItem("Buy", i.getSeedId(), i.getCount(), player, manager);

			if (item.getCount() > i.getCount())
				playerIU.addModifiedItem(item);
			else
				playerIU.addNewItem(item);

			// Send Char Buy Messages
			SystemMessage sm = null;
			sm = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
			sm.addItemName(item);
			sm.addItemNumber(i.getCount());
			player.sendPacket(sm);
		}
		// Send update packets
		player.sendPacket(playerIU);

		StatusUpdate su = new StatusUpdate(player.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
		player.sendPacket(su);
	}

	private class Seed
	{
		private final int _seedId;
		private final long _count;
		SeedProduction _seed;
		
		public Seed(int id, long num)
		{
			_seedId = id;
			_count = num;
		}

		public int getSeedId()
		{
			return _seedId;
		}

		public long getCount()
		{
			return _count;
		}

		public long getPrice()
		{
			return _seed.getPrice() * _count;
		}

		public boolean setProduction(Castle c)
		{
			_seed = c.getSeed(_seedId, CastleManorManager.PERIOD_CURRENT);
			// invalid price - seed disabled
			if (_seed.getPrice() <= 0)
				return false;
			// try to buy more than castle can produce 
			if (_seed.getCanProduce() < _count)
				return false;
			// check for overflow
			if ((MAX_ADENA / _count) < _seed.getPrice())
				return false;

			return true;
		}

		public void updateProduction(Castle c)
		{
			_seed.setCanProduce(_seed.getCanProduce() - _count);
			// Update Castle Seeds Amount
			if (Config.ALT_MANOR_SAVE_ALL_ACTIONS)
				c.updateSeed(_seedId, _seed.getCanProduce(), CastleManorManager.PERIOD_CURRENT);
		}
	}

	@Override
	public String getType()
	{
		return _C__C4_REQUESTBUYSEED;
	}
}
