/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.L2ItemInstance.ItemLocation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Message;
import l2.rambase.gameserver.model.itemcontainer.ItemContainer;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExChangePostState;
import l2.rambase.gameserver.network.serverpackets.InventoryUpdate;
import l2.rambase.gameserver.network.serverpackets.ItemList;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;

import static l2.rambase.gameserver.model.itemcontainer.PcInventory.ADENA_ID;
import static l2.rambase.gameserver.model.actor.L2Character.ZONE_PEACE;


public final class RequestPostAttachment extends L2GameClientPacket
{
	private static final String _C__D0_6A_REQUESTPOSTATTACHMENT = "[C] D0:6A RequestPostAttachment";

	private int _msgId;

	@Override
	protected void readImpl()
	{
		_msgId = readD();
	}

	@Override
	public void runImpl()
	{
		if (!Config.ALLOW_MAIL || !Config.ALLOW_ATTACHMENTS)
			return;

		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (!activeChar.getFloodProtectors().getTransaction().tryPerformAction("getattach"))
			return;

		if (!activeChar.getAccessLevel().allowTransaction())
		{
			activeChar.sendMessage("Transactions are disabled for your Access Level");
			return;
		}

		if (!activeChar.isInsideZone(ZONE_PEACE))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_NOT_IN_PEACE_ZONE));
			return;
		}
		
		if(activeChar.getKarma() > 0)
        {
            activeChar.sendMessage("You cannot use mail when having karma.");
            return;
        } 

		if (activeChar.getActiveTradeList() != null)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_DURING_EXCHANGE));
			return;
		}

		if (activeChar.isEnchanting())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_DURING_ENCHANT));
			return;
		}

		if (activeChar.getPrivateStoreType() > 0)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_PRIVATE_STORE));
			return;
		}

		final Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null)
			return;

		if (msg.getReceiverId() != activeChar.getObjectId())
		{
			Util.handleIllegalPlayerAction(activeChar,
					"Player "+activeChar.getName()+" tried to get not own attachment!", Config.DEFAULT_PUNISH);
			return;
		}

		if (!msg.hasAttachments())
			return;

		final ItemContainer attachments = msg.getAttachments();
		if (attachments == null)
			return;

		int weight = 0;
		int slots = 0;

		for (L2ItemInstance item : attachments.getItems())
		{
			if (item == null)
				continue;

			// Calculate needed slots
			if (item.getOwnerId() != msg.getSenderId())
			{
				Util.handleIllegalPlayerAction(activeChar,
						"Player "+activeChar.getName()+" tried to get wrong item (ownerId != senderId) from attachment!", Config.DEFAULT_PUNISH);
				return;
			}

			if (!item.getLocation().equals(ItemLocation.MAIL))
			{
				Util.handleIllegalPlayerAction(activeChar,
						"Player "+activeChar.getName()+" tried to get wrong item (Location != MAIL) from attachment!", Config.DEFAULT_PUNISH);
				return;
			}

			if (item.getLocationSlot() != msg.getId())
			{
				Util.handleIllegalPlayerAction(activeChar,
						"Player "+activeChar.getName()+" tried to get items from different attachment!", Config.DEFAULT_PUNISH);
				return;
			}

			weight += item.getCount() * item.getItem().getWeight();
			if (!item.isStackable())
				slots += item.getCount();
			else if (activeChar.getInventory().getItemByItemId(item.getItemId()) == null)
				slots++;
		}

		// Item Max Limit Check
		if (!activeChar.getInventory().validateCapacity(slots))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_INVENTORY_FULL));
			return;
		}

		// Weight limit Check
		if (!activeChar.getInventory().validateWeight(weight))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_INVENTORY_FULL));
			return;
		}

		long adena = msg.getReqAdena();
		if (adena > 0 && !activeChar.reduceAdena("PayMail", adena, null, true))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_RECEIVE_NO_ADENA));
			return;
		}

		// Proceed to the transfer
		InventoryUpdate playerIU = Config.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (L2ItemInstance item : attachments.getItems())
		{
			if (item == null)
				continue;

			if (item.getOwnerId() != msg.getSenderId())
			{
				Util.handleIllegalPlayerAction(activeChar,
						"Player "+activeChar.getName()+" tried to get items with owner != sender !", Config.DEFAULT_PUNISH);
				return;
			}

			long count = item.getCount();
			final L2ItemInstance newItem = attachments.transferItem(attachments.getName(), item.getObjectId(), item.getCount(), activeChar.getInventory(), activeChar, null);
			if (newItem == null)
				return;

			if (playerIU != null)
			{
				if (newItem.getCount() > count)
					playerIU.addModifiedItem(newItem);
				else
					playerIU.addNewItem(newItem);
			}
			SystemMessage sm = new SystemMessage(SystemMessageId.YOU_ACQUIRED_S2_S1);
			sm.addItemName(item.getItemId());
			sm.addItemNumber(count);
			activeChar.sendPacket(sm);
		}

		// Send updated item list to the player
		if (playerIU != null)
			activeChar.sendPacket(playerIU);
		else
			activeChar.sendPacket(new ItemList(activeChar, false));

		msg.removeAttachments();

		// Update current load status on player
		StatusUpdate su = new StatusUpdate(activeChar.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, activeChar.getCurrentLoad());
		activeChar.sendPacket(su);

		SystemMessage sm;
		final L2PcInstance sender = L2World.getInstance().getPlayer(msg.getSenderId());
		if (adena > 0)
		{
			if (sender != null)
			{
				sender.addAdena("PayMail", adena, activeChar, false);
				sm = new SystemMessage(SystemMessageId.PAYMENT_OF_S1_ADENA_COMPLETED_BY_S2);
				sm.addItemNumber(adena);
				sm.addCharName(activeChar);
				sender.sendPacket(sm);
			}
			else
			{
				L2ItemInstance paidAdena = ItemTable.getInstance().createItem("PayMail", ADENA_ID, adena, activeChar, null);
				paidAdena.setOwnerId(msg.getSenderId());
				paidAdena.setLocation(ItemLocation.INVENTORY);
				paidAdena.updateDatabase(true);
				L2World.getInstance().removeObject(paidAdena);
			}
		}
		else if (sender != null)
		{
			sm = new SystemMessage(SystemMessageId.S1_ACQUIRED_ATTACHED_ITEM);
			sm.addCharName(activeChar);
			sender.sendPacket(sm);
		}

		activeChar.sendPacket(new ExChangePostState(true, _msgId, Message.READED));
		activeChar.sendPacket(new SystemMessage(SystemMessageId.MAIL_SUCCESSFULLY_RECEIVED));
	}

	@Override
	public String getType()
	{
		return _C__D0_6A_REQUESTPOSTATTACHMENT;
	}
}