/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.admincommandhandlers;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.handler.IAdminCommandHandler;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2ControllableMobInstance;
import l2.rambase.gameserver.model.actor.instance.L2GrandBossInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2RaidBossInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


/**
 * This class handles following admin commands:
 * - kill = kills target L2Character
 * - kill_monster = kills target non-player
 *
 * - kill <radius> = If radius is specified, then ALL players only in that radius will be killed.
 * - kill_monster <radius> = If radius is specified, then ALL non-players only in that radius will be killed.
 *
 * @version $Revision: 1.2.4.5 $ $Date: 2007/07/31 10:06:06 $
 */
public class AdminKill implements IAdminCommandHandler
{
	private static Logger _log = Logger.getLogger(AdminKill.class.getName());
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_kill",
		"admin_kill_monster"
	};
	
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		if (command.startsWith("admin_kill"))
		{
			StringTokenizer st = new StringTokenizer(command, " ");
			st.nextToken(); // skip command
			
			if (st.hasMoreTokens())
			{
				String firstParam = st.nextToken();
				L2PcInstance plyr = L2World.getInstance().getPlayer(firstParam);
				if (plyr != null)
				{
					if (st.hasMoreTokens())
					{
						try
						{
							int radius = Integer.parseInt(st.nextToken());
							for (L2Character knownChar : plyr.getKnownList().getKnownCharactersInRadius(radius))
							{
								if (knownChar instanceof L2ControllableMobInstance || knownChar == activeChar)
									continue;
								if (knownChar instanceof L2RaidBossInstance || knownChar instanceof L2GrandBossInstance)
									continue;
								
								kill(activeChar, knownChar);
							}
							
							activeChar.sendMessage("Killed all characters within a " + radius + " unit radius.");
							return true;
						}
						catch (NumberFormatException e)
						{
							activeChar.sendMessage("Invalid radius.");
							return false;
						}
					}
					else
					{
						kill(activeChar, plyr);
					}
				}
				else
				{
					try
					{
						int radius = Integer.parseInt(firstParam);
						
						for (L2Character knownChar : activeChar.getKnownList().getKnownCharactersInRadius(radius))
						{
							if (knownChar instanceof L2ControllableMobInstance || knownChar instanceof L2RaidBossInstance || knownChar instanceof L2GrandBossInstance || knownChar == activeChar)
								continue;
							if (knownChar instanceof L2RaidBossInstance || knownChar instanceof L2GrandBossInstance)
								continue;
							kill(activeChar, knownChar);
						}
						
						activeChar.sendMessage("Killed all characters within a " + radius + " unit radius.");
						return true;
					}
					catch (NumberFormatException e)
					{
						activeChar.sendMessage("Usage: //kill <player_name | radius>");
						return false;
					}
				}
			}
			else
			{
				L2Object obj = activeChar.getTarget();
				if (obj instanceof L2ControllableMobInstance || !(obj instanceof L2Character))
				{
					activeChar.sendPacket(new SystemMessage(SystemMessageId.INCORRECT_TARGET));
				}
				else if (obj instanceof L2RaidBossInstance || obj instanceof L2GrandBossInstance)
				{
					activeChar.sendMessage("You cannot kill raidboss or grandboss");
				}
				else
				{
					kill(activeChar, (L2Character) obj);
				}
			}
		}
		return true;
	}
	
	private void kill(L2PcInstance activeChar, L2Character target)
	{
		if (target instanceof L2PcInstance)
		{
			if (!((L2PcInstance) target).isGM())
				target.stopAllEffects(); // e.g. invincibility effect
			target.reduceCurrentHp(target.getMaxHp() + target.getMaxCp() + 1, activeChar, null);
		}
		else if (Config.MOD_CHAMPION_ENABLE && target.isChampion())
			target.reduceCurrentHp(target.getMaxHp() * Config.MOD_CHAMPION_HP + 1, activeChar, null);
		else if (Config.MOD_RIFT_CHAMPION_ENABLE && target.isRiftChampion())
			target.reduceCurrentHp(target.getMaxHp() * Config.MOD_RIFT_CHAMPION_HP + 1, activeChar, null);
		else
		{
			if(target.isInvul()) target.setIsInvul(false);
			target.reduceCurrentHp(target.getMaxHp() + 1, activeChar, null);
		}
		if (Config.DEBUG)
			_log.fine("GM: " + activeChar.getName() + "(" + activeChar.getObjectId() + ")" + " killed character " + target.getObjectId());
	}
	
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}
