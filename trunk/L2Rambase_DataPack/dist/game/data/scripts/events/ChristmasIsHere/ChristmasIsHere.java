package events.ChristmasIsHere;

import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;

import events.EventsConfig;

public class ChristmasIsHere extends Quest
{
	private static final String qn = "ChristmasIsHere";
		
	/**
	 * List of Event Npc
	 * @param Npc
	 */
	private static final int SANTA_CLAUS = 7106;
	/**
	 * List of Event Monsters
	 * @param Npc
	 */
	private static final int[] EventMonsters = 
	{ 
		7000,7001,7002,7003,7004,7005,7006,7007,7008,7009,
		7010,7011,7012,7013,7014,7015,7016,7017,7018,7019,
		7020,7021,7022,7023
	};	
	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (EventsConfig.CH_STARTED)
		{
			htmltext = "<html><body>Santa:<br>Ho ho ho !! Merry Christmas! Have you been bad or good this year?<br>You must see what i can give you.<br>";
			htmltext +="<a action=\"bypass -h Quest ChristmasIsHere getprizes\">Search for a Gift To Me</a><br>";
			htmltext +="<a action=\"bypass -h Quest ChristmasIsHere info\">How i can get prizes.</a><br></body></html>";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}
	/**
	 * On Advanced Event Script 
	 */
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("getprizes"))
		{
			htmltext = "prizes.htm";
		}
		else if (event.equalsIgnoreCase("info"))
		{
			htmltext = "<html><body>Santa:<br>Oh no i don't have my socks. You must go to dark dungeon (Mission Master) and get my socks, then you can get one of my prizes.<br>"; 
			htmltext += "<br></body></html>";
		}		
       	return htmltext;
	}
	/**
	 * On Kill Monster Script
	 */
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int npcId = npc.getNpcId();
		if (EventsConfig.CH_ACTIVE_DROP)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					st.giveItems(EventsConfig.CH_CHRISTMAS_SOCK,1);					
				}
			}			
		}
		return super.onKill(npc, player, isPet);
	}	
	
	public ChristmasIsHere(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(SANTA_CLAUS);
		addFirstTalkId(SANTA_CLAUS);
		addTalkId(SANTA_CLAUS);
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}		
	}
	public static void main(String[] args)
	{
		new ChristmasIsHere(-1,qn,"events");
		if (EventsConfig.CH_STARTED)
			_log.warning("Event System: Christmas Event loaded ...");
	}
}