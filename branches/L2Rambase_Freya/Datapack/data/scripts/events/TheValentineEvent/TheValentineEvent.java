package events.TheValentineEvent;

import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.util.Rnd;

import events.EventsConfig;


/**
 * Event Code for "The Valentine Event"
 * @author  L2Rambase Project
 */
public class TheValentineEvent extends Quest
{
	private static final String qn = "TheValentineEvent";
	private static final int _npc		= 4301;
	
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
	
	public TheValentineEvent(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(_npc);
		addFirstTalkId(_npc);
		addTalkId(_npc);		
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
		if (EventsConfig.TVE_ACTIVE_DROP)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					switch(Rnd.get(1,9))
					{
						case 1:
							st.giveItems(4209,1);
							break;
						case 2:
							st.giveItems(4210,1);
							break;
						case 3:
							st.giveItems(4211,1);
							break;
						case 4:
							st.giveItems(4212,1);
							break;
						case 5:
							st.giveItems(4213,1);
							break;
						case 6:
							st.giveItems(4214,1);
							break;
						case 7:
							st.giveItems(4215,1);
							break;
						case 8:
							st.giveItems(4216,1);
							break;
						case 9:
							st.giveItems(4217,1);
							break;
						default:
							break;
					}
				}
			}			
		}
		checkHeart(player,st);		
		return super.onKill(npc, player, isPet);
	}	
	private void checkHeart(L2PcInstance player, QuestState st) 
	{
		long count1 = st.getQuestItemsCount(4209);
		long count2 = st.getQuestItemsCount(4210);
		long count3 = st.getQuestItemsCount(4211);
		long count4 = st.getQuestItemsCount(4212);
		long count5 = st.getQuestItemsCount(4213);
		long count6 = st.getQuestItemsCount(4214);
		long count7 = st.getQuestItemsCount(4215);
		long count8 = st.getQuestItemsCount(4216);
		long count9 = st.getQuestItemsCount(4217);
		if (count1 >= 1 && count2 >= 1 && count3 >= 1 && count4 >= 1 && count5 >= 1 && count6 >= 1 && count7 >= 1 && count8 >= 1 && count9 >= 1)
		{
			int chance;
			chance = Rnd.get(1,4);
			st.takeItems(4209, 1);
			st.takeItems(4210, 1);
			st.takeItems(4211, 1);
			st.takeItems(4212, 1);
			st.takeItems(4213, 1);
			st.takeItems(4214, 1);
			st.takeItems(4215, 1);
			st.takeItems(4216, 1);
			st.takeItems(4217, 1);
			switch(chance)
			{
				case 1:					
					st.giveItems(EventsConfig.TVE_RECIPE, 1);
					break;
				case 2:
					st.giveItems(EventsConfig.TVE_REAGENT1, 1);
					break;
				case 3:
					st.giveItems(EventsConfig.TVE_REAGENT2, 1);
					break;
				case 4:
					st.giveItems(EventsConfig.TVE_REAGENT3, 1);
					break;
			}			
		}		
	}
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);

		htmltext = event;
		if(event.equalsIgnoreCase("recipe"))
		{
			long recipeCount = st.getQuestItemsCount(EventsConfig.TVE_RECIPE);
			long reagent1Count = st.getQuestItemsCount(EventsConfig.TVE_REAGENT1);
			long reagent2Count = st.getQuestItemsCount(EventsConfig.TVE_REAGENT2);
			long reagent3Count = st.getQuestItemsCount(EventsConfig.TVE_REAGENT3);
			if(recipeCount > 0 && reagent1Count > 4 && reagent2Count > 9 && reagent3Count > 9)
			{
				st.takeItems(EventsConfig.TVE_RECIPE, 1);
				st.takeItems(EventsConfig.TVE_REAGENT1, 5);
				st.takeItems(EventsConfig.TVE_REAGENT2, 10);
				st.takeItems(EventsConfig.TVE_REAGENT3, 10);
				int chance;
				chance = Rnd.get(100);
				if(chance > 79)
				{
					st.giveItems(EventsConfig.TVE_CAKE1, 1);
				}
				else if(chance > 49 && chance < 80)
				{
					st.giveItems(EventsConfig.TVE_CAKE2, 1);
				}
				else if(chance > 29 && chance < 50)
				{
					st.giveItems(EventsConfig.TVE_CAKE3, 1);
				}
				else
				{
					st.giveItems(EventsConfig.TVE_CAKE4, 1);
				}
			}
			else
			{
				htmltext = "<html><body>";
				htmltext += "Meow~ meow~ <br>";
				htmltext += "I cannot help you with cake you need:<br>";
				htmltext += "<font color=LEVEL> 1 Valentine Cake Recipe, 5 Valentine Fresh Cream, 10 Valentine White Chocolate and 10 Valentine Dark Chocolate</font><br>";
				htmltext += "<a action=\"bypass -h Quest TheValentineEvent 4301.htm\">I want check something else.</a><br>";
				htmltext += "</body></html>";
			}
		}
		else
		{
			if (event.endsWith(".htm"))
			{
				htmltext = event;
			}
		}
		return htmltext;
	}
	
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			Quest q = QuestManager.getInstance().getQuest(getName());
			st = q.newQuestState(player);
		}
		if (EventsConfig.TVE_STARTED)
		{
			htmltext = npc.getNpcId() + ".htm";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}	
	
	public static void main(String[] args)
	{
		new TheValentineEvent(-1, "TheValentineEvent", "events");
		if (EventsConfig.TVE_STARTED)
			_log.info("Event System: The Valentine Event loaded ...");
	}
}