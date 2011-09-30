package events.HeavyMedal;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.util.Rnd;

import events.EventsConfig;

public class HeavyMedal extends Quest
{
	private static final String qn = "HeavyMedal";
		
	/**
	 * List of Event Npc
	 * @param Npc
	 */
	private static final int MEDAL_CAT = 31227;
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
		if (EventsConfig.HM_STARTED)
		{
			htmltext = "welcome.htm";
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
		int level = st.getInt("level");
		if (event.equalsIgnoreCase("event"))
		{
			if (st.getState() != State.STARTED)
			{
				st.setState(State.STARTED);
			}
			else if (st.getInt("level") == 1)
			{
				htmltext = "level1.htm";
			}
			else if (st.getInt("level") == 2)
			{
				htmltext = "level2.htm";
			}
			else
			{
				htmltext = "level0.htm";
			}
		}
		if (event.equalsIgnoreCase("playgame"))
		{
			htmltext = "playgame.htm";
		}
		if (event.equalsIgnoreCase("playgame2"))
		{
			htmltext = "playgame2.htm";
		}
		if (event.equalsIgnoreCase("information"))
		{
			htmltext = "information.htm";
		}
		if (event.equalsIgnoreCase("back"))
		{
			if (level < 1)
			{
				htmltext = "level0.htm";
			}
			if (level == 1)
			{
				htmltext = "level1.htm";
			}
			if (level == 2)
			{
				htmltext = "level2.htm";
			}
		}
		if (event.equalsIgnoreCase("getlevel2") && level == 1)
		{
			if (st.getQuestItemsCount(EventsConfig.HM_GLITTERING_MEDAL) > 199)
			{
				st.takeItems(EventsConfig.HM_GLITTERING_MEDAL, 200);
				if (Rnd.get(100) < 30)
				{
					st.set("level","2");
					htmltext = "levelup.htm";
				}
				else
				{
					htmltext = "nolevelup.htm";
				}
			}
			else
			{
				htmltext = "nomedals.htm";
			}
		}
		if (event.equalsIgnoreCase("getlevel1") && level == 0)
		{
			if (st.getQuestItemsCount(EventsConfig.HM_GLITTERING_MEDAL) > 99)
			{
				st.takeItems(EventsConfig.HM_GLITTERING_MEDAL, 100);
				if (Rnd.get(100) < 30)
				{
					st.set("level","1");
					htmltext = "levelup.htm";
				}
				else
				{
					htmltext = "nolevelup.htm";
				}
			}
			else
			{
				htmltext = "nomedals.htm";
			}
		}
       	return htmltext;
	}
	/**
	 * On Kill Monster Script
	 */
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int npcId = npc.getNpcId();
		if (EventsConfig.HM_ACTIVE_DROP)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					if(Rnd.get(100) < 81)
					{
						st.giveItems(EventsConfig.HM_MEDAL,1);
					}
					else
					{
						st.giveItems(EventsConfig.HM_GLITTERING_MEDAL,1);
					}
				}
			}			
		}
		return super.onKill(npc, player, isPet);
	}	
	
	public HeavyMedal(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(MEDAL_CAT);
		addFirstTalkId(MEDAL_CAT);
		addTalkId(MEDAL_CAT);
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}		
	}
	public static void main(String[] args)
	{
		new HeavyMedal(-1,qn,"events");
		if (EventsConfig.HM_STARTED)
			_log.warning("Event System: Heavy Medal Event loaded ...");
	}
}