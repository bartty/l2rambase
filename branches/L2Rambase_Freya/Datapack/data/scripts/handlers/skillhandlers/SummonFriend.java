package handlers.skillhandlers;

import java.util.logging.Level;

import l2.rambase.gameserver.handler.ISkillHandler;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.Inventory;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ConfirmDlg;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.skills.L2SkillType;
import l2.rambase.gameserver.util.Util;


/**
 *  @author L2Rambase Project
 */
public class SummonFriend implements ISkillHandler
{
	//private static Logger _log = Logger.getLogger(SummonFriend.class.getName());
	private static final L2SkillType[] SKILL_IDS =
	{
		L2SkillType.SUMMON_FRIEND
	};

	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#useSkill(l2.rambase.gameserver.model.actor.L2Character, l2.rambase.gameserver.model.L2Skill, l2.rambase.gameserver.model.L2Object[])
	 */
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{
		if (!(activeChar instanceof L2PcInstance))
			return; // currently not implemented for others
		L2PcInstance activePlayer = (L2PcInstance) activeChar;
		
		if (!L2PcInstance.checkSummonerStatus(activePlayer))
			return;
		
		try
		{
			for (L2Character target: (L2Character[]) targets)
			{
				if (activeChar == target)
					continue;
				
				if (target instanceof L2PcInstance)
				{
					L2PcInstance targetPlayer = (L2PcInstance) target;
					
					if (!L2PcInstance.checkSummonTargetStatus(targetPlayer, activePlayer))
						continue;
										
					if (!Util.checkIfInRange(0, activeChar, target, false))
					{
						if(!targetPlayer.teleportRequest(activePlayer, skill))
						{
							SystemMessage sm = new SystemMessage(SystemMessageId.C1_ALREADY_SUMMONED);
							sm.addString(target.getName());
							activePlayer.sendPacket(sm);
							continue;
						}
						if (skill.getId() == 1403) //summon friend
						{
							// Send message
							ConfirmDlg confirm = new ConfirmDlg(SystemMessageId.C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT.getId());
							confirm.addCharName(activeChar);
							confirm.addZoneName(activeChar.getX(), activeChar.getY(), activeChar.getZ());
							confirm.addTime(30000);
							confirm.addRequesterId(activePlayer.getCharId());
							target.sendPacket(confirm);
						}
						else if (skill.getId() == 21097) //summon party love
						{
							L2ItemInstance parmorInstance = target.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LBRACELET);
							if (parmorInstance != null)
							{
								if (parmorInstance.getItemId() == 20594 || parmorInstance.getItemId() == 20628)
								{
									ConfirmDlg confirm = new ConfirmDlg(SystemMessageId.C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT.getId());
									confirm.addCharName(activeChar);
									confirm.addZoneName(activeChar.getX(), activeChar.getY(), activeChar.getZ());
									confirm.addTime(30000);
									confirm.addRequesterId(activePlayer.getCharId());
									target.sendPacket(confirm);
								}
							}
						}
						else
						{
							L2PcInstance.teleToTarget(targetPlayer, activePlayer, skill);
							targetPlayer.teleportRequest(null, null);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "", e);
		}
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#getSkillIds()
	 */
	public L2SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}
