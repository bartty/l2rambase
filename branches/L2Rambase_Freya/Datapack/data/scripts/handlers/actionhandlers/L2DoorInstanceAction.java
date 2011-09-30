/**
 * @author L2Rambase Project
 */
package handlers.actionhandlers;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.handler.IActionHandler;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ConfirmDlg;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.StaticObject;
import l2.rambase.gameserver.network.serverpackets.ValidateLocation;

public class L2DoorInstanceAction implements IActionHandler
{
	public boolean action(L2PcInstance activeChar, L2Character target, boolean interact)
	{
		// Check if the L2PcInstance already target the L2NpcInstance
		if (activeChar.getTarget() != target)
		{
			// Set the target of the L2PcInstance activeChar
			activeChar.setTarget(target);
			
			// Send a Server->Client packet MyTargetSelected to the L2PcInstance activeChar
			activeChar.sendPacket(new MyTargetSelected(target.getObjectId(), 0));

			StaticObject su;
			// send HP amount if doors are inside castle/fortress zone
			if ((((L2DoorInstance)target).getCastle() != null
					&& ((L2DoorInstance)target).getCastle().getCastleId() > 0)
					|| (((L2DoorInstance)target).getFort() != null
					&& ((L2DoorInstance)target).getFort().getFortId() > 0
					&& !((L2DoorInstance)target).getIsCommanderDoor()))
			{
				su = new StaticObject((L2DoorInstance)target, true);
			}
			else if (((L2DoorInstance)target).getClanHall() != null &&
					((L2DoorInstance)target).getClanHall().isResidence())
			{
				su = new StaticObject((L2DoorInstance)target, true);
			}			
			else
			{
				su = new StaticObject((L2DoorInstance)target, false);
			}

			activeChar.sendPacket(su);
			
			// Send a Server->Client packet ValidateLocation to correct the L2NpcInstance position and heading on the client
			activeChar.sendPacket(new ValidateLocation(target));
		}
		else if (interact)
		{
			//            MyTargetSelected my = new MyTargetSelected(getObjectId(), activeChar.getLevel());
			//            activeChar.sendPacket(my);
			if (target.isAutoAttackable(activeChar))
			{
				if (Math.abs(activeChar.getZ() - target.getZ()) < 400) // this max heigth difference might need some tweaking
					activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
			}
			else if (activeChar.getClan() != null
					&& ((L2DoorInstance)target).getClanHall() != null
					&& activeChar.getClanId() == ((L2DoorInstance)target).getClanHall().getOwnerId())
			{
				if (!target.isInsideRadius(activeChar, L2Npc.INTERACTION_DISTANCE, false, false))
				{
					activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, target);
				}
				else
				{
					if ((activeChar.getClanPrivileges() & L2Clan.CP_CH_OPEN_DOOR) == L2Clan.CP_CH_OPEN_DOOR)
					{
						activeChar.gatesRequest((L2DoorInstance)target);
						if (!((L2DoorInstance)target).getOpen())
							activeChar.sendPacket(new ConfirmDlg(1140));
						else
							activeChar.sendPacket(new ConfirmDlg(1141));
					}
					else
					{
						activeChar.sendMessage("You don't have rights to open that door!!");
					}
				}
			}
			else if (activeChar.getClan() != null
					&& ((L2DoorInstance)target).getFort() != null
					&& activeChar.getClan() == ((L2DoorInstance)target).getFort().getOwnerClan()
					&& ((L2DoorInstance)target).isUnlockable()
					&& !((L2DoorInstance)target).getFort().getSiege().getIsInProgress())
			{
				if (!target.isInsideRadius(activeChar, L2Npc.INTERACTION_DISTANCE, false, false))
				{
					activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, target);
				}
				else
				{
					if ((activeChar.getClanPrivileges() & L2Clan.CP_CS_OPEN_DOOR) == L2Clan.CP_CS_OPEN_DOOR)
					{
						activeChar.gatesRequest((L2DoorInstance)target);
						if (!((L2DoorInstance)target).getOpen())
							activeChar.sendPacket(new ConfirmDlg(1140));
						else
							activeChar.sendPacket(new ConfirmDlg(1141));
					}
					else
					{
						activeChar.sendMessage("You don't have rights to open that gate!!");						
					}
				}
			}
		}
		return true;
	}

	public InstanceType getInstanceType()
	{
		return InstanceType.L2DoorInstance;
	}
}
