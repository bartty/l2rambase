/**
 * @author L2Rambase Project
 */
package handlers.actionhandlers;

import l2.rambase.Config;
import l2.rambase.gameserver.GeoData;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.handler.IActionHandler;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.ValidateLocation;

public class L2PcInstanceAction implements IActionHandler
{
	/**
	 * Manage actions when a player click on this L2PcInstance.<BR><BR>
	 *
	 * <B><U> Actions on first click on the L2PcInstance (Select it)</U> :</B><BR><BR>
	 * <li>Set the target of the player</li>
	 * <li>Send a Server->Client packet MyTargetSelected to the player (display the select window)</li><BR><BR>
	 *
	 * <B><U> Actions on second click on the L2PcInstance (Follow it/Attack it/Intercat with it)</U> :</B><BR><BR>
	 * <li>Send a Server->Client packet MyTargetSelected to the player (display the select window)</li>
	 * <li>If target L2PcInstance has a Private Store, notify the player AI with AI_INTENTION_INTERACT</li>
	 * <li>If target L2PcInstance is autoAttackable, notify the player AI with AI_INTENTION_ATTACK</li><BR><BR>
	 * <li>If target L2PcInstance is NOT autoAttackable, notify the player AI with AI_INTENTION_FOLLOW</li><BR><BR>
	 *
	 * <B><U> Example of use </U> :</B><BR><BR>
	 * <li> Client packet : Action, AttackRequest</li><BR><BR>
	 *
	 * @param activeChar The player that start an action on target L2PcInstance
	 *
	 */
	public boolean action(L2PcInstance activeChar, L2Character target, boolean interact)
	{
		// See description in TvTEvent.java
		if (!TvTEvent.onAction( activeChar, target.getObjectId()))
			return false;
		if (!PvPEvent.onAction( activeChar, target.getObjectId()))
			return false;

		// Check if the L2PcInstance is confused
		if (activeChar.isOutOfControl())
			return false;

		// Aggression target lock effect
		if (activeChar.isLockedTarget() && activeChar.getLockedTarget() != target)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.FAILED_CHANGE_TARGET));
			return false;
		}

		// Check if the activeChar already target this L2PcInstance
		if (activeChar.getTarget() != target)
		{
			// Set the target of the activeChar
			activeChar.setTarget(target);

			// Send a Server->Client packet MyTargetSelected to the activeChar
			// The color to display in the select window is White
			activeChar.sendPacket(new MyTargetSelected(target.getObjectId(), 0));
			if (activeChar != target) activeChar.sendPacket(new ValidateLocation(target));
		}
		else if (interact)
		{
			if (activeChar != target) activeChar.sendPacket(new ValidateLocation(target));
			// Check if this L2PcInstance has a Private Store
			if (((L2PcInstance)target).getPrivateStoreType() != 0)
            {
				activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, target);
            }
			else
			{
				// Check if this L2PcInstance is autoAttackable
				if (target.isAutoAttackable(activeChar))
				{
					// activeChar with lvl < 21 can't attack a cursed weapon holder
					// And a cursed weapon holder  can't attack activeChars with lvl < 21
					if ((((L2PcInstance)target).isCursedWeaponEquipped() && activeChar.getLevel() < 21)
							|| (activeChar.isCursedWeaponEquipped() && target.getLevel() < 21))
					{
						activeChar.sendPacket(ActionFailed.STATIC_PACKET);
					}
					else
					{
						if (Config.GEODATA > 0)
						{
							if (GeoData.getInstance().canSeeTarget(activeChar, target))
							{
								activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
								activeChar.onActionRequest();
							}
						}
						else
						{
							activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
							activeChar.onActionRequest();
						}
					}
				} else
				{
					// This Action Failed packet avoids activeChar getting stuck when clicking three or more times
					activeChar.sendPacket(ActionFailed.STATIC_PACKET);
					if (Config.GEODATA > 0)
					{
						if(GeoData.getInstance().canSeeTarget(activeChar, target))
							activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, target);
					}
					else
						activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, target);
				}
			}
		}
		return true;
	}

	public InstanceType getInstanceType()
	{
		return InstanceType.L2PcInstance;
	}
}
