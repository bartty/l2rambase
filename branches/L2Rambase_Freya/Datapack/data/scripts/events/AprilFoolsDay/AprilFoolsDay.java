package events.AprilFoolsDay;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.util.Rnd;
import events.EventsConfig;


public class AprilFoolsDay extends Quest
{
	private static final String qn = "AprilFoolsDay";

	private static final int April_Npc = 32639;
	
	private static final String start = "start.htm";
	private static final String good = "good.htm";
	private static final String bad = "bad.htm";
	private static final String no_items = "no_items.htm";
	
	/**
	 * Good Reward List:
	 * - Zaken or Queen Ant (20204 - 20207)    	Chance 5%
	 * - Event Dress (20099)					Chance 27%
	 * - Special Aghations (14093,14094)		Chance 15%
	 * - Coke (3434 - only one)					Chance 3%
	 * 
	 * Bad Reward List:
	 * - give 5000 karma 						Chance 15%
	 * - get 150k Adena							Chance 3%
	 * - Death Penalty - (skill 5076 lvl 15)	Chance 7%
	 * - set Vitality to 0						Chance 25%
	 */
	
	private static final int APRIL_GIFT = 20958;
	
	private static final int[] EventMonsters = 
	{ 
		7000,7001,7002,7003,7004,7005,7006,7007,7008,7009,
		7010,7011,7012,7013,7014,7015,7016,7017,7018,7019,
		7020,7021,7022,7023
	};
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		if (EventsConfig.AP_STARTED)
		{
			htmltext = start;
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}		
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
		if (event.equalsIgnoreCase("play"))
		{
			if (st.getQuestItemsCount(APRIL_GIFT) >= 100)
			{
				st.takeItems(APRIL_GIFT,100);
				htmltext = giveReward(st,player);
			}
			else
			{
				htmltext = no_items;
			}
		}
		if (event.endsWith(".htm"))
		{
			htmltext = event;
		}
      	return htmltext;
	}
	private String giveReward(QuestState st, L2PcInstance player) 
	{
		String text = "";
		int randomGet = Rnd.get(100);
		if (randomGet <= 50)
		{			
			if (randomGet <= 5)
			{
				if (Rnd.get(2) == 2)
				{
					if (st.getQuestItemsCount(20204) < 1)
					{
						st.giveItems(20204, 1);
					}
					else
					{
						st.giveAdena(5000, true);
					}
				}
				else
				{
					if (st.getQuestItemsCount(20207) < 1)
					{
						st.giveItems(20207, 1);
					}
					else
					{
						st.giveAdena(15000, true);
					}
				}
			}
			else if (randomGet >5 && randomGet <=32)
			{
				if (st.getQuestItemsCount(20099) < 1)
				{
					st.giveItems(20099, 1);
				}
				else
				{
					st.giveAdena(5000, true);
				}
			}
			else if (randomGet > 32 && randomGet <=47)
			{
				if(Rnd.get(2) == 2)
				{
					if (st.getQuestItemsCount(14093) < 1)
					{
						st.giveItems(14093,1);
					}
					else
					{
						st.giveAdena(5000, true);
					}
				}
				else
				{
					if (st.getQuestItemsCount(14094) < 1)
					{
						st.giveItems(14094,1);
					}
					else
					{
						st.giveAdena(5000, true);
					}
				}
			}
			else
			{
				if (st.getQuestItemsCount(3434) < 1)
				{
					st.giveItems(3434,1);
				}
				else
				{
					st.giveAdena(50000, true);
				}
			}
			text = good;
		}
		else
		{			
			if (randomGet > 50 && randomGet <= 65)
			{
				player.setKarma(player.getKarma() + 5000);
			}
			else if (randomGet > 65 && randomGet <= 68)
			{
				if (st.getQuestItemsCount(57) < 150000)
				{
					if (st.getQuestItemsCount(57) != 0)
					{
						st.takeItems(57,st.getQuestItemsCount(57));
					}
				}
				else
				{
					st.takeItems(57,150000);
				}
			}
			else if(randomGet > 68 && randomGet <= 75)
			{
				SkillTable.getInstance().getInfo(5076,15).getEffects(player, player);
			}
			else
			{
				player.setVitalityPoints(0, true);
			}
			text = bad;
		}
		return text;
	}

	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{		
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int npcId = npc.getNpcId();
		if (EventsConfig.AP_STARTED)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					st.giveItems(APRIL_GIFT,1);					
				}
			}			
		}
		return super.onKill(npc, player, isPet);
	}
	
	public AprilFoolsDay(int questId, String name, String descr)
	{
		super(questId, name, descr);

		addStartNpc(April_Npc);
		addFirstTalkId(April_Npc);
		addTalkId(April_Npc);
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}
	}	
	public static void main(String[] args)
	{
		new AprilFoolsDay(-1,qn,"events");
		if (EventsConfig.AP_STARTED)
			_log.warning("Event System: April Fools Day Event loaded ...");
	}
}