/**
 * @author L2Rambase Project
 */
package services.FightManagers.PvPManager;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.olympiad.Olympiad;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;


public class PvPNpc extends Quest
{
	private static final String qn = "PvPNpc";	
	
	private static final String htmlPath="data/html/mods/PvPEvent/";
	
	/**
	 * PvP Npc Manager
	 * @param npc
	 */
	private static final int PVP_NPC = 70011;
	
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

		if (PvPEvent.isParticipating())
		{
			boolean isParticipant = false;
			isParticipant = PvPEvent.isPlayerParticipant(player.getObjectId());			
			String htmContent;

			if (!isParticipant)
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "Participation.htm");
			}
			else
			{
				htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "RemoveParticipation.htm");
			}

	    	if (htmContent != null)
	    	{
	    		int playerCounts = 0;
	    		int minimumParticipants = Config.PVP_EVENT_MIN_PLAYERS;
	    		String rewards = "";
	    		for (int[] rewardList : Config.PVP_EVENT_REWARDS)
	    		{
	    			rewards += String.valueOf(rewardList[1]) + " " + String.valueOf(ItemTable.getInstance().getTemplate(rewardList[0]).getName()) + "<br>";
	    		}
	    		playerCounts = PvPEvent.getParticipatedPlayersCount();
	    		
	    		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(npc.getObjectId());

				npcHtmlMessage.setHtml(htmContent);
				npcHtmlMessage.replace("%rewards%", String.valueOf(rewards));
				npcHtmlMessage.replace("%minPlayers%", String.valueOf(minimumParticipants));
	    		npcHtmlMessage.replace("%objectId%", String.valueOf(npc.getObjectId()));
				npcHtmlMessage.replace("%playercount%", String.valueOf(playerCounts));
				player.sendPacket(npcHtmlMessage);
	    	}
		}
		else if (PvPEvent.isStarting() || PvPEvent.isStarted())
		{
			String htmContent = HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath + "Status.htm");

	    	if (htmContent != null)
	    	{
	    		int playerCounts = PvPEvent.getParticipatedPlayersCount();
	    		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(npc.getObjectId());
				npcHtmlMessage.setHtml(htmContent);				
				npcHtmlMessage.replace("%playercount%", String.valueOf(playerCounts));
	    		player.sendPacket(npcHtmlMessage);
	    	}
		}
		player.sendPacket(ActionFailed.STATIC_PACKET);
		return htmltext;
	}
	
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (!PvPEvent.isParticipating())
			return "";
			
		final String htmContent;
		if (event.equals("pvp_event_participation"))
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
			else if (PvPEvent.addParticipant(player))
			{
				npcHtmlMessage.setHtml(HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Registered.htm"));
			}
			else
			{
				return "";
			}
			
			player.sendPacket(npcHtmlMessage);
		}
		else if (event.equals("pvp_event_remove_participation"))
		{
			PvPEvent.removeParticipant(player.getObjectId());
				
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(0);
			
			npcHtmlMessage.setHtml(HtmCache.getInstance().getHtm(player.getHtmlPrefix(), htmlPath+"Unregistered.htm"));
			player.sendPacket(npcHtmlMessage);
		}		
		return htmltext;
	}
	
	public PvPNpc(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(PVP_NPC);
		addFirstTalkId(PVP_NPC);
		addTalkId(PVP_NPC);		
		PvPManager.getInstance();
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new PvPNpc(-1,qn,"PvP Npc Manager");
		_log.warning("PvP Npc Manager: Npc Loaded ...");
	}
}