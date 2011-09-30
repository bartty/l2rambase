package handlers.admincommandhandlers;

import java.util.StringTokenizer;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.handler.IAdminCommandHandler;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.GMAudit;


/**
 * This class handles following admin commands:
 * - penalty charname = give player skill penalty.
 * - unpenalty charname = remove from player, teleport him to Floran.
 *
 */
public class AdminPenalty implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS = 
	{
		"admin_penalty",
		"admin_unpenalty"
	};

	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		StringTokenizer st = new StringTokenizer(command);
		st.nextToken();
		String player = "";
		L2PcInstance targetPlayer = null;

		if (st.hasMoreTokens())
		{
			player = st.nextToken();
			targetPlayer = L2World.getInstance().getPlayer(player);	        
		}
		else
		{
			if (activeChar.getTarget() != null && activeChar.getTarget() instanceof L2PcInstance)
			{
				targetPlayer = (L2PcInstance)activeChar.getTarget();
			}
		}
		
		if (targetPlayer != null && targetPlayer.equals(activeChar))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANNOT_USE_ON_YOURSELF));
			return false;
		}
		
		
		if (command.startsWith("admin_penalty"))
		{
			if (targetPlayer == null && player.equals(""))
			{
				activeChar.sendMessage("Usage: //penalty <charname> [if no name is given, selected target get skill penalty indefinitely)");
				return false;
			}
			if (targetPlayer != null)
			{
				if (targetPlayer.isFlyingMounted())
					targetPlayer.untransform();
				SkillTable.getInstance().getInfo(7085,1).getEffects(targetPlayer,targetPlayer);
				activeChar.sendMessage("Character "+targetPlayer.getName()+" get skill penalty!");
				auditAction(command, activeChar, targetPlayer.getName());
			}
			else
			{
				activeChar.sendMessage("Cannot find player that must get");
				auditAction(command, activeChar, player);
			}
		}
		else if (command.startsWith("admin_unpenalty"))
		{
			if (targetPlayer == null && player.equals(""))
			{
				activeChar.sendMessage("Usage: //unpenalty <charname> (If no name is given target is used)");
				return false;
			}
			else if (targetPlayer != null)
			{				
				L2Effect[] effects = targetPlayer.getAllEffects();
				
				for (L2Effect e : effects)
				{
					if ((e != null) && (e.getSkill().getId() == 7085))
					{
						e.exit();
						activeChar.sendMessage("Skill Penalty removed from "+ targetPlayer.getName() + ".");
					}
				}
				auditAction(command, activeChar, targetPlayer.getName());
			}
		}
		return true;
	}
	
	private void auditAction(String fullCommand, L2PcInstance activeChar, String target)
	{
		if (!Config.GMAUDIT)
			return;
		
		String[] command = fullCommand.split(" ");
		
		GMAudit.auditGMAction(activeChar.getName()+" ["+activeChar.getObjectId()+"]", command[0], (target.equals("") ? "no-target" : target), (command.length > 2 ? command[2] : ""));
	}	
	

	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}
}