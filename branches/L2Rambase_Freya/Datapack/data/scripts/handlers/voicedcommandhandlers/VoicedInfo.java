/**
 * @author L2Rambase Project
 */
package handlers.voicedcommandhandlers;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;


public class VoicedInfo implements IVoicedCommandHandler
{
	private static final String[] _voicedCommands = { "tvt","pvp" };
	
	/**
	 * Set this to false and recompile script if you dont want to use string cache.
	 * This will decrease performance but will be more consistent against possible html editions during runtime
	 * Recompiling the script will get the new html would be enough too [DrHouse]
	 */
	private static final boolean USE_STATIC_HTML = true;
	private static final String TVT_HTML = HtmCache.getInstance().getHtm(null, "data/html/mods/TvTEvent/Status.htm");
	private static final String PVP_HTML = HtmCache.getInstance().getHtm(null, "data/html/mods/TvTEvent/Status.htm");

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target)
	{
		if (command.equalsIgnoreCase("pvp"))
		{
			if (PvPEvent.isStarting() || PvPEvent.isStarted())
			{
				String htmContent = (USE_STATIC_HTML && !PVP_HTML.isEmpty()) ? PVP_HTML : 
					HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/mods/PvPEvent/Status.htm");
				
				try
				{
					NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);

					npcHtmlMessage.setHtml(htmContent);
					// npcHtmlMessage.replace("%objectId%",
					// String.valueOf(getObjectId()));
					npcHtmlMessage.replace("%playername%", activeChar.getName());
					try
					{
						npcHtmlMessage.replace("%points%", String.valueOf(PvPEvent._members.get(activeChar.getObjectId()).getPoints()));
					}
					catch (Exception e)
					{						
					}
					activeChar.sendPacket(npcHtmlMessage);
				}
				catch (Exception e)
				{
					_log.warning("wrong PvP voiced: " + e);
				}

			}
			else
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
		if (command.equalsIgnoreCase("tvt"))
		{
			if (TvTEvent.isStarting() || TvTEvent.isStarted())
			{
				String htmContent = (USE_STATIC_HTML && !TVT_HTML.isEmpty()) ? TVT_HTML : 
					HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/mods/TvTEvent/Status.htm");
				
				try
				{
					NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);

					npcHtmlMessage.setHtml(htmContent);
					// npcHtmlMessage.replace("%objectId%",
					// String.valueOf(getObjectId()));
					npcHtmlMessage.replace("%team1name%", Config.TVT_EVENT_TEAM_1_NAME);
					npcHtmlMessage.replace("%team1playercount%", String.valueOf(TvTEvent.getTeamsPlayerCounts()[0]));
					npcHtmlMessage.replace("%team1points%", String.valueOf(TvTEvent.getTeamsPoints()[0]));
					npcHtmlMessage.replace("%team2name%", Config.TVT_EVENT_TEAM_2_NAME);
					npcHtmlMessage.replace("%team2playercount%", String.valueOf(TvTEvent.getTeamsPlayerCounts()[1]));
					npcHtmlMessage.replace("%team2points%", String.valueOf(TvTEvent.getTeamsPoints()[1]));
					activeChar.sendPacket(npcHtmlMessage);
				}
				catch (Exception e)
				{
					_log.warning("wrong TvT voiced: " + e);
				}

			}
			else
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
		return true;
	}

	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}