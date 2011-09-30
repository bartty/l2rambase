/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import static l2.rambase.gameserver.model.actor.L2Npc.INTERACTION_DISTANCE;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.instancemanager.CastleManorManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Manor;
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

public class RequestBuyProcure extends L2GameClientPacket {
	private static final String _C__C3_REQUESTBUYPROCURE = "[C] C3 RequestBuyProcure";

	private static final int BATCH_LENGTH = 12; // length of the one item

	@SuppressWarnings("unused")
	private int _listId;
	private Procure[] _items = null;

	@Override
	protected void readImpl()
	{
		_listId = readD();
		int count = readD();
		if(count <= 0
				|| count > Config.MAX_ITEM_IN_PACKET
				|| count * BATCH_LENGTH != _buf.remaining())
		{
			return;
		}

		_items = new Procure[count];
		for (int i = 0; i < count; i++)
		{
			readD(); //service
			int itemId = readD();
			long cnt = readQ();
			if (itemId < 1 || cnt < 1)
			{
				_items = null;
				return;
			}
			_items[i] = new Procure(itemId, cnt);
		}
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;

		if(_items == null)
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Alt game - Karma punishment
		if (!Config.ALT_GAME_KARMA_PLAYER_CAN_SHOP && player.getKarma() > 0)
			return;

		L2Object manager = player.getTarget();

		if (!(manager instanceof L2ManorManagerInstance))
			manager = player.getLastFolkNPC();

		if (!(manager instanceof L2ManorManagerInstance))
			return;

		if (!player.isInsideRadius(manager, INTERACTION_DISTANCE, true, false))
			return;

		Castle castle = ((L2ManorManagerInstance)manager).getCastle();
		int slots = 0;
		int weight = 0;

		for (Procure i : _items)
		{
			i.setReward(castle);

			L2Item template = ItemTable.getInstance().getTemplate(i.getReward());
	        weight += i.getCount() * template.getWeight();

			if (!template.isStackable())
				slots += i.getCount();
			else if (player.getInventory().getItemByItemId(i.getItemId()) == null)
				slots++;
		}

		if (!player.getInventory().validateWeight(weight))
		{
			sendPacket(new SystemMessage(SystemMessageId.WEIGHT_LIMIT_EXCEEDED));
			return;
		}

		if (!player.getInventory().validateCapacity(slots))
		{
			sendPacket(new SystemMessage(SystemMessageId.SLOTS_FULL));
			return;
		}

		// Proceed the purchase
		InventoryUpdate playerIU = new InventoryUpdate();

		for (Procure i : _items)
		{
			// check if player have correct items count
			L2ItemInstance item = player.getInventory().getItemByItemId(i.getItemId());
			if (item == null || item.getCount() < i.getCount())
				continue;

			L2ItemInstance iteme = player.getInventory().destroyItemByItemId("Manor",i.getItemId(),i.getCount(),player,manager);
			if (iteme == null)
				continue;

			// Add item to Inventory and adjust update packet
			item = player.getInventory().addItem("Manor",i.getReward(),i.getCount(),player,manager);
			if (item == null)
				continue;

			playerIU.addRemovedItem(iteme);
			if (item.getCount() > i.getCount())
				playerIU.addModifiedItem(item);
			else
				playerIU.addNewItem(item);

			// Send Char Buy Messages
			SystemMessage sm = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
			sm.addItemName(item);
			sm.addItemNumber(i.getCount());
			player.sendPacket(sm);
			sm = null;			
		}

		// Send update packets
		player.sendPacket(playerIU);

		StatusUpdate su = new StatusUpdate(player.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
		player.sendPacket(su);
	}

	private class Procure
	{
		private final int _itemId;
		private final long _count;
		private int _reward;

		public Procure(int id, long num)
		{
			_itemId = id;
			_count = num;
		}

		public int getItemId()
		{
			return _itemId;
		}

		public long getCount()
		{
			return _count;
		}

		public int getReward()
		{
			return _reward;
		}

		public void setReward(Castle c)
		{
			_reward = L2Manor.getInstance().getRewardItem(_itemId,
					c.getCrop(_itemId,CastleManorManager.PERIOD_CURRENT).getReward());
		}
	}

	@Override
	public String getType()
	{
		return _C__C3_REQUESTBUYPROCURE;
	}
}
