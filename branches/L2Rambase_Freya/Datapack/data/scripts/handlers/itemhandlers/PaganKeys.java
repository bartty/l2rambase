/**
 * @author L2Rambase Project
 */
package handlers.itemhandlers;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.handler.IItemHandler;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class PaganKeys implements IItemHandler
{
	public static final int INTERACTION_DISTANCE = 100;
	
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IItemHandler#useItem(l2.rambase.gameserver.model.actor.L2Playable, l2.rambase.gameserver.model.L2ItemInstance)
	 */
	public void useItem(L2Playable playable, L2ItemInstance item)
	{
		int itemId = item.getItemId();
		if (!(playable instanceof L2PcInstance))
			return;
		L2PcInstance activeChar = (L2PcInstance) playable;
		L2Object target = activeChar.getTarget();
		
		if (!(target instanceof L2DoorInstance))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.INCORRECT_TARGET));
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		L2DoorInstance door = (L2DoorInstance) target;
		
		if (!(activeChar.isInsideRadius(door, INTERACTION_DISTANCE, false, false)))
		{
			activeChar.sendMessage("Too far.");
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if (activeChar.getAbnormalEffect() > 0 || activeChar.isInCombat())
		{
			activeChar.sendMessage("You cannot use the key now.");
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		if (!playable.destroyItem("Consume", item.getObjectId(), 1, null, false))
			return;
		
		switch (itemId)
		{
			case 9694:
				if (door.getDoorId() == 24220001
						|| door.getDoorId() == 24220002 || door.getDoorId() == 24220003
						|| door.getDoorId() == 24220004)
				{
					if (activeChar.getInstanceId() != door.getInstanceId())
					{
						for (L2DoorInstance instanceDoor : InstanceManager.getInstance().getInstance(activeChar.getInstanceId()).getDoors())
							if (instanceDoor.getDoorId() == door.getDoorId())
							{
								instanceDoor.openMe();
							}
					}
					else
					{
						door.openMe();
					}
				}
				else
				{
					activeChar.sendMessage("Incorrect Door.");
				}
				break;
			case 9698:
				if (door.getDoorId() == 24220020)
				{
					if (activeChar.getInstanceId() != door.getInstanceId())
					{
						for (L2DoorInstance instanceDoor : InstanceManager.getInstance().getInstance(activeChar.getInstanceId()).getDoors())
							if (instanceDoor.getDoorId() == door.getDoorId())
							{
								instanceDoor.openMe();
							}
					}
					else
					{
						door.openMe();
					}
				}
				else
				{
					activeChar.sendMessage("Incorrect Door.");
				}
				break;
			case 9699:
				if (door.getDoorId() == 24220022)
				{
					if (activeChar.getInstanceId() != door.getInstanceId())
					{
						for (L2DoorInstance instanceDoor : InstanceManager.getInstance().getInstance(activeChar.getInstanceId()).getDoors())
							if (instanceDoor.getDoorId() == door.getDoorId())
							{
								instanceDoor.openMe();
							}
					}
					else
					{
						door.openMe();
					}
				}
				else
				{
					activeChar.sendMessage("Incorrect Door.");
				}
				break;
			case 8056:
				if (door.getDoorId() == 23150004||door.getDoorId() == 23150003)
				{
					DoorTable.getInstance().getDoor(23150003).openMe();
					DoorTable.getInstance().getDoor(23150003).onOpen();
					DoorTable.getInstance().getDoor(23150004).openMe();
					DoorTable.getInstance().getDoor(23150004).onOpen();
				}
				else
				{
					activeChar.sendMessage("Incorrect Door.");
				}
				break;
		}
	}
}
