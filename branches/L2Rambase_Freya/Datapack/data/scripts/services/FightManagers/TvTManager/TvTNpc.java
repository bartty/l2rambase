/**
 * @author L2Rambase Project
 */
package services.FightManagers.TvTManager;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.olympiad.Olympiad;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;


public class TvTNpc extends Quest
{
	private static final String qn = "TvTNpc";	
	
	private static final String htmlPath="data/html/mods/TvTEvent/";
	
	/**
	 * TvT Npc Manager
	 * @param npc
	 */
	private static final int TVT_NPC = 70010;
	
	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
			st.setState(State.STARTED);
		}
		String htmltext = "";
		if (player == null)
			return "";

		if (TvTEvent.isParticipating())
		{
			final boolean isParticipant = TvTEvent.isPlayerParticipant(player.getObjectId()); 
			final String htmContent;

			if (!isParticipant)
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "Participation.htm");
			else
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "RemoveParticipation.htm");

	    	if (htmContent != null)
	    	{
	    		int playerCounts = TvTEvent.getParticipatedPlayersCount();
	    		String rewards = "";
	    		for (int[] rewardList : Config.TVT_EVENT_REWARDS)
	    		{
	    			rewards += String.valueOf(rewardList[1]) + " " + String.valueOf(ItemTable.getInstance().getTemplate(rewardList[0]).getName()) + "<br>";
	    		}
	    		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(npc.getObjectId());

				npcHtmlMessage.setHtml(htmContent);
	    		npcHtmlMessage.replace("%objectId%", String.valueOf(npc.getObjectId()));
	    		npcHtmlMessage.replace("%rewards%", String.valueOf(rewards));	    		
				npcHtmlMessage.replace("%playercount%", String.valueOf(playerCounts));

				player.sendPacket(npcHtmlMessage);
	    	}
		}
		else if (TvTEvent.isStarting() || TvTEvent.isStarted())
		{
			final String htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "Status.htm");

	    	if (htmContent != null)
	    	{
	    		int[] teamsPlayerCounts = TvTEvent.getTeamsPlayerCounts();
	    		int[] teamsPointsCounts = TvTEvent.getTeamsPoints();
	    		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(npc.getObjectId());

				npcHtmlMessage.setHtml(htmContent);
				npcHtmlMessage.replace("%team1name%", Config.TVT_EVENT_TEAM_1_NAME);
				npcHtmlMessage.replace("%team1playercount%", String.valueOf(teamsPlayerCounts[0]));
				npcHtmlMessage.replace("%team1points%", String.valueOf(teamsPointsCounts[0]));
				npcHtmlMessage.replace("%team2name%", Config.TVT_EVENT_TEAM_2_NAME);
				npcHtmlMessage.replace("%team2playercount%", String.valueOf(teamsPlayerCounts[1]));
				npcHtmlMessage.replace("%team2points%", String.valueOf(teamsPointsCounts[1])); // <---- array index from 0 to 1
	    		player.sendPacket(npcHtmlMessage);
	    	}
		}
		player.sendPacket(ActionFailed.STATIC_PACKET);
		return htmltext;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (!TvTEvent.isParticipating())
			return "";
			
		final String htmContent;
		if (event.equals("tvt_event_participation"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(0);
			int playerLevel = player.getLevel();
			
			if (player.isCursedWeaponEquipped())
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"CursedWeaponEquipped.htm");
				if (htmContent != null)
					npcHtmlMessage.setHtml(htmContent);
			}
			else if (Olympiad.getInstance().isRegistered(player))
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Olympiad.htm");
				if (htmContent != null)
					npcHtmlMessage.setHtml(htmContent);
			}
			else if (player.getKarma() > 0)
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Karma.htm");
				if (htmContent != null)
					npcHtmlMessage.setHtml(htmContent);
			}
			else if (playerLevel < 75)
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Level.htm");
				if (htmContent != null)
				{
					npcHtmlMessage.setHtml(htmContent);
					npcHtmlMessage.replace("%min%", String.valueOf(75));
					npcHtmlMessage.replace("%max%", String.valueOf(85));
				}
			}
			else if (TvTEvent._teams.get(0).getParticipatedPlayerCount() == Config.TVT_EVENT_MAX_PLAYERS_IN_TEAMS && TvTEvent._teams.get(1).getParticipatedPlayerCount() == Config.TVT_EVENT_MAX_PLAYERS_IN_TEAMS)
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"TeamsFull.htm");
				if (htmContent != null)
				{
					npcHtmlMessage.setHtml(htmContent);
					npcHtmlMessage.replace("%max%", String.valueOf(Config.TVT_EVENT_MAX_PLAYERS_IN_TEAMS));
				}
			}
			else if (TvTEvent.addParticipant(player))
			{
				npcHtmlMessage.setHtml(HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Registered.htm"));
			}
			else
			{
				return "";
			}
			
			player.sendPacket(npcHtmlMessage);
		}
		else if (event.equals("tvt_event_remove_participation"))
		{
			TvTEvent.removeParticipant(player.getObjectId());
				
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(0);
			
			npcHtmlMessage.setHtml(HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Unregistered.htm"));
			player.sendPacket(npcHtmlMessage);
		}		
		return htmltext;
	}
	
	public TvTNpc(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(TVT_NPC);
		addFirstTalkId(TVT_NPC);
		addTalkId(TVT_NPC);	
		TvTManager.getInstance();
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new TvTNpc(-1,qn,"TvT Npc Manager");
		_log.warning("TvT Npc Manager: Npc Loaded ...");
	}
}