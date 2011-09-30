/**
 * @author L2Rambase Project
 * @param
 * @info command for 
 */
package handlers.voicedcommandhandlers;

import java.util.StringTokenizer;

import l2.rambase.gameserver.datatables.AdminCommandAccessRights;
import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class ChatAdmin implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS =
	{
		"banchat",
		"unbanchat"
	};

	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IVoicedCommandHandler#useVoicedCommand(java.lang.String, l2.rambase.gameserver.model.actor.instance.L2PcInstance, java.lang.String)
	 */
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (activeChar.getClan() == null)
		{
			return false;
		}
		else
		{
			if ((activeChar.getClanPrivileges() & L2Clan.CP_CL_MANAGE_RANKS) != L2Clan.CP_CL_MANAGE_RANKS || !activeChar.isClanLeader())
			{
				activeChar.sendMessage("To use command .banchat in clan you must be clan leader or get Manage Ranks privilige.");
				return false;
			}
		}
		
		if (command.equalsIgnoreCase(VOICED_COMMANDS[0])) // banchat
		{
			if (params == null)
			{
				activeChar.sendMessage("Usage: .banchat name [minutes]");
				return true;
			}
			StringTokenizer st = new StringTokenizer(params);
			if (st.hasMoreTokens())
			{
				String name = st.nextToken();
				int length = 0;
				if (st.hasMoreTokens())
				{
					try
					{
						length = Integer.parseInt(st.nextToken());
					}
					catch (NumberFormatException e)
					{
						activeChar.sendMessage("Wrong ban length !");
						return false;
					}
				}
				if (length < 0)
					length = 0;

				int objId = CharNameTable.getInstance().getIdByName(name);
				L2PcInstance clanMember = L2World.getInstance().getPlayer(objId);
				if (clanMember == null || clanMember.isOnline() == 0)
				{
					activeChar.sendMessage("Target player not online !");
					return false;
				}
				else if (clanMember.getClan() == null)
				{
					activeChar.sendMessage("Target player not in clan !");
					return false;
				}
				else if (activeChar.getClan().getClanId() != clanMember.getClan().getClanId())
				{
					activeChar.sendMessage("Target player is not in your clan !");
					return false;
				}
				if (objId > 0)
				{
					L2PcInstance player = L2World.getInstance().getPlayer(objId);
					if (player == null || player.isOnline() == 0)
					{
						activeChar.sendMessage("Player not online !");
						return false;
					}
					if (player.getPunishLevel() != L2PcInstance.PunishLevel.NONE)
					{
						activeChar.sendMessage("Player is already punished !");
						return false;
					}
					if (player == activeChar)
					{
						activeChar.sendMessage("You can't ban yourself !");
						return false;
					}
					if (player.isGM())
					{
						activeChar.sendMessage("You can't ban GM !");
						return false;
					}
					if (AdminCommandAccessRights.getInstance().hasAccess(command, player.getAccessLevel()))
					{
						activeChar.sendMessage("You can't ban moderator !");
						return false;
					}

					player.setPunishLevel(L2PcInstance.PunishLevel.CHAT, length);
					player.sendMessage("Chat banned by moderator " + activeChar.getName());

					if (length > 0)
						activeChar.sendMessage("Player " + player.getName() + " chat banned for " + length + " minutes.");
					else
						activeChar.sendMessage("Player " + player.getName() + " chat banned forever.");
				}
				else
				{
					activeChar.sendMessage("Player not found !");
					return false;
				}
			}
		}
		else if (command.equalsIgnoreCase(VOICED_COMMANDS[1])) //unbanchat
		{
			if (params == null)
			{
				activeChar.sendMessage("Usage: .unbanchat name");
				return true;
			}
			StringTokenizer st = new StringTokenizer(params);
			if (st.hasMoreTokens())
			{
				String name = st.nextToken();
				
				int objId = CharNameTable.getInstance().getIdByName(name);
				L2PcInstance clanMember = L2World.getInstance().getPlayer(objId);
				if (clanMember == null || clanMember.isOnline() == 0)
				{
					activeChar.sendMessage("Target player not online !");
					return false;
				}
				else if (clanMember.getClan() == null)
				{
					activeChar.sendMessage("Target player not in clan !");
					return false;
				}
				else if (activeChar.getClan().getClanId() != clanMember.getClan().getClanId())
				{
					activeChar.sendMessage("Target player is not in your clan !");
					return false;
				}
				if (objId > 0)
				{
					L2PcInstance player = L2World.getInstance().getPlayer(objId);
					if (player == null || player.isOnline() == 0)
					{
						activeChar.sendMessage("Player not online !");
						return false;
					}
					if (player.getPunishLevel() != L2PcInstance.PunishLevel.CHAT)
					{
						activeChar.sendMessage("Player is not chat banned !");
						return false;
					}

					player.setPunishLevel(L2PcInstance.PunishLevel.NONE, 0);

					activeChar.sendMessage("Player " + player.getName() + " chat unbanned.");
					player.sendMessage("Chat unbanned by moderator " + activeChar.getName());
				}
				else
				{
					activeChar.sendMessage("Player not found !");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IVoicedCommandHandler#getVoicedCommandList()
	 */
	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}
