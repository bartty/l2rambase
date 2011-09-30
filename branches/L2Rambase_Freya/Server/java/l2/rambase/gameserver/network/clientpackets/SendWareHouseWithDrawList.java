/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.ClanWarehouse;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.ItemContainer;
import l2.rambase.gameserver.model.itemcontainer.PcWarehouse;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.InventoryUpdate;
import l2.rambase.gameserver.network.serverpackets.ItemList;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;


public final class SendWareHouseWithDrawList extends L2GameClientPacket
{
	private static final String _C__32_SENDWAREHOUSEWITHDRAWLIST = "[C] 32 SendWareHouseWithDrawList";
	private static Logger _log = Logger.getLogger(SendWareHouseWithDrawList.class.getName());

	private static final int BATCH_LENGTH = 12; // length of the one item

	private WarehouseItem _items[] = null;

	@Override
	protected void readImpl()
	{
		final int count = readD();
		if (count <= 0
				|| count > Config.MAX_ITEM_IN_PACKET
				|| count * BATCH_LENGTH != _buf.remaining())
		{
		    return;
		}

		_items = new WarehouseItem[count];
		for (int i=0; i < count; i++)
		{
			int objId = readD();
			long cnt    = readQ();
			if (objId < 1 || cnt < 0)
			{
				_items = null;
				return;
			}
			_items[i] = new WarehouseItem(objId, cnt);
		}
	}

	@Override
	protected void runImpl()
	{
		if (_items == null)
			return;

		final L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;

		if (!player.getFloodProtectors().getTransaction().tryPerformAction("withdraw"))
		{
			player.sendMessage("You withdrawing items too fast.");
			return;
		}

		final ItemContainer warehouse = player.getActiveWarehouse();
		if (warehouse == null)
			return;

		final L2Npc manager = player.getLastFolkNPC();
		if ((manager == null
				|| !manager.isWarehouse()
				|| !manager.canInteract(player)) && !player.isGM())
			return;

		if (!(warehouse instanceof PcWarehouse) && !player.getAccessLevel().allowTransaction())
		{
			player.sendMessage("Transactions are disable for your Access Level");
			return;
		}

		// Alt game - Karma punishment
		if (!Config.ALT_GAME_KARMA_PLAYER_CAN_USE_WAREHOUSE && player.getKarma() > 0)
			return;

		if (Config.ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH)
		{
			if (warehouse instanceof ClanWarehouse
					&& ((player.getClanPrivileges() & L2Clan.CP_CL_VIEW_WAREHOUSE) != L2Clan.CP_CL_VIEW_WAREHOUSE))
				return;
		}
		else
		{
			if (warehouse instanceof ClanWarehouse && !player.isClanLeader())
			{				 
				player.sendPacket(new SystemMessage(SystemMessageId.ONLY_CLAN_LEADER_CAN_RETRIEVE_ITEMS_FROM_CLAN_WAREHOUSE));
				return;
			}
		}

		int weight = 0;
		int slots = 0;

		for (WarehouseItem i : _items)
		{
			// Calculate needed slots
			L2ItemInstance item = warehouse.getItemByObjectId(i.getObjectId());
			if (item == null || item.getCount() < i.getCount())
			{
				Util.handleIllegalPlayerAction(player, "Warning!! Character "
						+ player.getName() + " of account "
						+ player.getAccountName() + " tried to withdraw non-existent item from warehouse.",
						Config.DEFAULT_PUNISH);
				return;
			}

			weight += i.getCount() * item.getItem().getWeight();
			if (!item.isStackable())
				slots += i.getCount();
			else if (player.getInventory().getItemByItemId(item.getItemId()) == null)
				slots++;
		}

		// Item Max Limit Check
		if (!player.getInventory().validateCapacity(slots))
		{
			sendPacket(new SystemMessage(SystemMessageId.SLOTS_FULL));
			return;
		}

		// Weight limit Check
		if (!player.getInventory().validateWeight(weight))
		{
			sendPacket(new SystemMessage(SystemMessageId.WEIGHT_LIMIT_EXCEEDED));
			return;
		}

		// Proceed to the transfer
		InventoryUpdate playerIU = Config.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (WarehouseItem i : _items)
		{
			L2ItemInstance oldItem = warehouse.getItemByObjectId(i.getObjectId());
			if (oldItem == null || oldItem.getCount() < i.getCount())
			{
				_log.warning("Error withdrawing a warehouse object for char " + player.getName() + " (olditem == null)");
				return;
			}
			final L2ItemInstance newItem = warehouse.transferItem(warehouse.getName(), i.getObjectId(), i.getCount(), player.getInventory(), player, manager);
			if (newItem == null)
			{
				_log.warning("Error withdrawing a warehouse object for char " + player.getName() + " (newitem == null)");
				return;
			}

			if (playerIU != null)
			{
				if (newItem.getCount() > i.getCount())
					playerIU.addModifiedItem(newItem);
				else
					playerIU.addNewItem(newItem);
			}
		}

		// Send updated item list to the player
		if (playerIU != null)
			player.sendPacket(playerIU);
		else
			player.sendPacket(new ItemList(player, false));

		// Update current load status on player
		StatusUpdate su = new StatusUpdate(player.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
		player.sendPacket(su);
	}

	private class WarehouseItem
	{
		private final int _objectId;
		private final long _count;
		
		public WarehouseItem(int id, long num)
		{
			_objectId = id;
			_count = num;
		}

		public int getObjectId()
		{
			return _objectId;
		}

		public long getCount()
		{
			return _count;
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__32_SENDWAREHOUSEWITHDRAWLIST;
	}
}
