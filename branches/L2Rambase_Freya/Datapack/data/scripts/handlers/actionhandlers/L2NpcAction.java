/**
 * @author L2Rambase Project
 */
package handlers.actionhandlers;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.event.L2Event;
import l2.rambase.gameserver.handler.IActionHandler;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.network.serverpackets.ValidateLocation;
import l2.rambase.util.Rnd;

public class L2NpcAction implements IActionHandler
{
	/**
	 * Manage actions when a player click on the L2NpcInstance.<BR><BR>
	 *
	 * <B><U> Actions on first click on the L2NpcInstance (Select it)</U> :</B><BR><BR>
	 * <li>Set the L2NpcInstance as target of the L2PcInstance player (if necessary)</li>
	 * <li>Send a Server->Client packet MyTargetSelected to the L2PcInstance player (display the select window)</li>
	 * <li>If L2NpcInstance is autoAttackable, send a Server->Client packet StatusUpdate to the L2PcInstance in order to update L2NpcInstance HP bar </li>
	 * <li>Send a Server->Client packet ValidateLocation to correct the L2NpcInstance position and heading on the client </li><BR><BR>
	 *
	 * <B><U> Actions on second click on the L2NpcInstance (Attack it/Intercat with it)</U> :</B><BR><BR>
	 * <li>Send a Server->Client packet MyTargetSelected to the L2PcInstance player (display the select window)</li>
	 * <li>If L2NpcInstance is autoAttackable, notify the L2PcInstance AI with AI_INTENTION_ATTACK (after a height verification)</li>
	 * <li>If L2NpcInstance is NOT autoAttackable, notify the L2PcInstance AI with AI_INTENTION_INTERACT (after a distance verification) and show message</li><BR><BR>
	 *
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : Each group of Server->Client packet must be terminated by a ActionFailed packet in order to avoid
	 * that client wait an other packet</B></FONT><BR><BR>
	 *
	 * <B><U> Example of use </U> :</B><BR><BR>
	 * <li> Client packet : Action, AttackRequest</li><BR><BR>
	 *
	 * @param activeChar The L2PcInstance that start an action on the L2NpcInstance
	 *
	 */
	public boolean action(L2PcInstance activeChar, L2Character target, boolean interact)
	{
		if (!((L2Npc)target).canTarget(activeChar))
			return false;

		activeChar.setLastFolkNPC((L2Npc)target);

		// Check if the L2PcInstance already target the L2NpcInstance
		if (target != activeChar.getTarget())
		{
			// Set the target of the L2PcInstance activeChar
			activeChar.setTarget(target);

			// Check if the activeChar is attackable (without a forced attack)
			if (target.isAutoAttackable(activeChar))
			{
				// Send a Server->Client packet MyTargetSelected to the L2PcInstance activeChar
				// The activeChar.getLevel() - getLevel() permit to display the correct color in the select window
				MyTargetSelected my = new MyTargetSelected(target.getObjectId(), activeChar.getLevel() - target.getLevel());
				activeChar.sendPacket(my);

				// Send a Server->Client packet StatusUpdate of the L2NpcInstance to the L2PcInstance to update its HP bar
				StatusUpdate su = new StatusUpdate(target.getObjectId());
				su.addAttribute(StatusUpdate.CUR_HP, (int) target.getCurrentHp());
				su.addAttribute(StatusUpdate.MAX_HP, target.getMaxHp());
				activeChar.sendPacket(su);
			}
			else
			{
				// Send a Server->Client packet MyTargetSelected to the L2PcInstance activeChar
				MyTargetSelected my = new MyTargetSelected(target.getObjectId(), 0);
				activeChar.sendPacket(my);
			}

			// Send a Server->Client packet ValidateLocation to correct the L2NpcInstance position and heading on the client
			activeChar.sendPacket(new ValidateLocation(target));
		}
		else if (interact)
		{
			activeChar.sendPacket(new ValidateLocation(target));
			// Check if the activeChar is attackable (without a forced attack) and isn't dead
			if (target.isAutoAttackable(activeChar) && !target.isAlikeDead())
			{
				// Check the height difference
				if (Math.abs(activeChar.getZ() - target.getZ()) < 400) // this max heigth difference might need some tweaking
				{
					// Set the L2PcInstance Intention to AI_INTENTION_ATTACK
					activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
					// activeChar.startAttack(this);
				}
				else
				{
					// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
					activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				}
			}
			else if (!target.isAutoAttackable(activeChar))
			{
				// Calculate the distance between the L2PcInstance and the L2NpcInstance
				if (!((L2Npc)target).canInteract(activeChar))
				{
					// Notify the L2PcInstance AI with AI_INTENTION_INTERACT
					activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, target);
				}
				else
				{
					if (((L2Npc)target).hasRandomAnimation())
						((L2Npc)target).onRandomAnimation(Rnd.get(8));

					// Open a chat window on client with the text of the L2NpcInstance
					if (((L2Npc)target).isEventMob)
					{
						L2Event.showEventHtml(activeChar, String.valueOf(target.getObjectId()));
					}
					else
					{
						Quest[] qlsa = ((L2Npc)target).getTemplate().getEventQuests(Quest.QuestEventType.QUEST_START);
						if ((qlsa != null) && qlsa.length > 0)
							activeChar.setLastQuestNpcObject(target.getObjectId());
						Quest[] qlst = ((L2Npc)target).getTemplate().getEventQuests(Quest.QuestEventType.ON_FIRST_TALK);
						if ((qlst != null) && qlst.length == 1)
							qlst[0].notifyFirstTalk((L2Npc)target, activeChar);
						else
							((L2Npc)target).showChatWindow(activeChar);
					}
				}
			}
		}
		return true;
	}

	public InstanceType getInstanceType()
	{
		return InstanceType.L2Npc;
	}
}