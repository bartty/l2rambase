package events.L2Day;

import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.gameserver.model.quest.State;
import l2.Rambase.util.Rnd;

import events.EventsConfig;

/**
 * 
 * @author L2m Project
 * @version 1.5
 * Updated for MissionMap
 */
public class L2Day extends Quest
{
	private static final String qn = "L2Day";

	/**
	 * Event Npc
	 * @param Npc
	 */	
	private static final int L2DAY_CAT = 31228;
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
	* Words used in event:
	* @param LineageII
	* @param Chronicle
	* @param Ncsoft
	* @param 5Years
	* @param Chaotic
	* @param AllIsClear
	* @param NiceCash	
	*  
	* New fun words (not used)
	* @param AgeOfStyle
	*/	
	
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
		if (EventsConfig.L2DAY_STARTED)
		{
			htmltext = "welcome.htm";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}
	
	private boolean checkEventWord(L2PcInstance player, String eventWord) 
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		//Count letters
		long GET_A = st.getQuestItemsCount(EventsConfig.L2DAY_A);
		long GET_C = st.getQuestItemsCount(EventsConfig.L2DAY_C);
		long GET_E = st.getQuestItemsCount(EventsConfig.L2DAY_E);
		long GET_F = st.getQuestItemsCount(EventsConfig.L2DAY_F);
		long GET_G = st.getQuestItemsCount(EventsConfig.L2DAY_G);
		long GET_H = st.getQuestItemsCount(EventsConfig.L2DAY_H);
		long GET_I = st.getQuestItemsCount(EventsConfig.L2DAY_I);
		long GET_L = st.getQuestItemsCount(EventsConfig.L2DAY_L);
		long GET_N = st.getQuestItemsCount(EventsConfig.L2DAY_N);
		long GET_O = st.getQuestItemsCount(EventsConfig.L2DAY_O);
		long GET_R = st.getQuestItemsCount(EventsConfig.L2DAY_R);
		long GET_S = st.getQuestItemsCount(EventsConfig.L2DAY_S);
		long GET_T = st.getQuestItemsCount(EventsConfig.L2DAY_T);
		long GET_II = st.getQuestItemsCount(EventsConfig.L2DAY_II);
		long GET_Y = st.getQuestItemsCount(EventsConfig.L2DAY_Y);
		long GET_5 = st.getQuestItemsCount(EventsConfig.L2DAY_5);
		if (eventWord.equalsIgnoreCase("lineageii"))
		{
			if (GET_L>0 && GET_I>0 && GET_N>0 && GET_E>1 && GET_A>0 && GET_G>0 && GET_II>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("chronicle"))
		{
			if (GET_C>1 && GET_H>0 && GET_R>0 && GET_O>1 && GET_N>0 && GET_I>0 && GET_L>0 && GET_E>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("ncsoft"))
		{
			if (GET_N>0 && GET_C>0 &&GET_S>0 &&GET_O>0 && GET_F>0 &&GET_T>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("5years"))
		{
			if (GET_5>0 && GET_Y>0 && GET_E>0 && GET_A>0 && GET_R>0 && GET_S>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("chaotic"))
		{
			if (GET_C>1 && GET_H>0 && GET_A>0 && GET_O>0 && GET_T>0 && GET_I>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("allisclear"))
		{
			if (GET_A>1 && GET_L>2 && GET_I>0 && GET_S>0 && GET_C>0 && GET_E>0 && GET_R>0)
			{
				return true;
			}
		}
		else if (eventWord.equalsIgnoreCase("nicecash"))
		{
			if (GET_N>0 && GET_I>0 && GET_C>1 && GET_E>0 && GET_A>0 && GET_S>0 && GET_H>0)
			{
				return true;
			}
		}
		return false;
	}
	
	private void rewardPlayer(L2PcInstance player, QuestState st,String eventWord)
	{
		if (eventWord.equalsIgnoreCase("lineageii"))
		{
			st.takeItems(EventsConfig.L2DAY_L,1);
			st.takeItems(EventsConfig.L2DAY_I,1);
			st.takeItems(EventsConfig.L2DAY_N,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_G,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.takeItems(EventsConfig.L2DAY_II,1);
			st.giveItems(EventsConfig.L2DAY_GREATER_ACUMEN,3);
			st.giveItems(EventsConfig.L2DAY_MYSTIC_EMPOWER,3);
			st.giveItems(EventsConfig.L2DAY_WINDWALK,3);
			st.giveItems(EventsConfig.L2DAY_SHIELD,3);
		}
		else if (eventWord.equalsIgnoreCase("chronicle"))
		{
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_H,1);
			st.takeItems(EventsConfig.L2DAY_R,1);
			st.takeItems(EventsConfig.L2DAY_O,1);
			st.takeItems(EventsConfig.L2DAY_N,1);
			st.takeItems(EventsConfig.L2DAY_I,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_L,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.giveItems(EventsConfig.L2DAY_GUIDANCE,3);
			st.giveItems(EventsConfig.L2DAY_DEATH_WHISPER,3);
			st.giveItems(EventsConfig.L2DAY_FOCUS,3);
			st.giveItems(EventsConfig.L2DAY_HASTE,3);
			st.giveItems(EventsConfig.L2DAY_AGILITY,3);
			st.giveItems(EventsConfig.L2DAY_MIGHT,3);
			st.giveItems(EventsConfig.L2DAY_WINDWALK,3);
			st.giveItems(EventsConfig.L2DAY_SHIELD,3);
		}
		else if (eventWord.equalsIgnoreCase("ncsoft"))
		{
			st.takeItems(EventsConfig.L2DAY_N,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_S,1);
			st.takeItems(EventsConfig.L2DAY_O,1);
			st.takeItems(EventsConfig.L2DAY_F,1);
			st.takeItems(EventsConfig.L2DAY_T,1);
			st.giveItems(EventsConfig.L2DAY_BSOE,1);
			st.giveItems(EventsConfig.L2DAY_BSOR,1);
		}
		else if (eventWord.equalsIgnoreCase("5years"))
		{
			st.takeItems(EventsConfig.L2DAY_5,1);
			st.takeItems(EventsConfig.L2DAY_Y,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_R,1);
			st.takeItems(EventsConfig.L2DAY_S,1);
			st.giveItems(EventsConfig.L2DAY_MAGE_COCKTAIL,1);
		}
		else if (eventWord.equalsIgnoreCase("chaotic"))
		{
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_H,1);
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_O,1);
			st.takeItems(EventsConfig.L2DAY_T,1);
			st.takeItems(EventsConfig.L2DAY_I,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.giveItems(EventsConfig.L2DAY_FIGHTER_COCKTAIL,1);	
		}
		else if (eventWord.equalsIgnoreCase("allisclear"))
		{
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_L,1);
			st.takeItems(EventsConfig.L2DAY_L,1);
			st.takeItems(EventsConfig.L2DAY_I,1);
			st.takeItems(EventsConfig.L2DAY_S,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_L,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_R,1);
			st.giveItems(EventsConfig.L2DAY_MANA_REGENERATION,3);
		}
		else if (eventWord.equalsIgnoreCase("nicecash"))
		{
			st.takeItems(EventsConfig.L2DAY_N,1);
			st.takeItems(EventsConfig.L2DAY_I,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_E,1);
			st.takeItems(EventsConfig.L2DAY_C,1);
			st.takeItems(EventsConfig.L2DAY_A,1);
			st.takeItems(EventsConfig.L2DAY_S,1);
			st.takeItems(EventsConfig.L2DAY_H,1);
			st.giveItems(EventsConfig.L2DAY_ADENA,20000);
			st.giveItems(EventsConfig.L2DAY_ANCIENT_ADENA,25000);
		}
		else
		{
			_log.warning("Event System: Player "+ player.getName() + " tried to get prize from L2Day word " + eventWord);
		}
	}
	/**
	 * On Advanced Event Script 
	 */
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		
		if (event.equalsIgnoreCase("prizes"))
		{
			htmltext = "prizes.htm";
		}
		if (event.equalsIgnoreCase("1"))
		{
			if (checkEventWord(player,"lineageii"))			
			{
				//Word: LINEAGE II - Mage Scrolls
				rewardPlayer(player,st,"lineageii");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("2"))
		{
			if (checkEventWord(player,"chronicle"))
			{
				//Word: CHRONICLE - Fighter Scrolls
				rewardPlayer(player,st,"chronicle");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("3"))
		{
			if (checkEventWord(player,"ncsoft"))
			{
				//Word: NCSOFT - Bsoe and Bsor
				rewardPlayer(player,st,"ncsoft");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("4"))
		{
			if (checkEventWord(player,"5years"))
			{
				//Word: 5YEARS - Mage Potion
				rewardPlayer(player,st,"5years");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("5"))
		{
			if (checkEventWord(player,"chaotic"))
			{
				//Word: CHAOTIC - Fighter Potions
				rewardPlayer(player,st,"chaotic");			
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("6"))
		{
			if (checkEventWord(player,"allisclear"))
			{
				//Word: ALLISCLEAR - Mana Regeneration Scroll
				rewardPlayer(player,st,"allisclear");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("7"))
		{
			if (checkEventWord(player,"nicecash"))
			{
				//Word: NICECASH - Adena and Ancient Adena -- Adena with quest reward implemented
				rewardPlayer(player,st,"nicecash");
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}				
		}
		if (event.equalsIgnoreCase("info"))
		{
			htmltext = "info.htm";
		}
		if (event.equalsIgnoreCase("back"))
		{
			htmltext = "welcome.htm";
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
		if (EventsConfig.L2DAY_ACTIVE_DROP)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					switch(Rnd.get(1,16))
					{
						case 1:
							st.giveItems(EventsConfig.L2DAY_A,1);
							break;
						case 2:
							st.giveItems(EventsConfig.L2DAY_C,1);
							break;
						case 3:
							st.giveItems(EventsConfig.L2DAY_E,1);
							break;
						case 4:
							st.giveItems(EventsConfig.L2DAY_F,1);
							break;
						case 5:
							st.giveItems(EventsConfig.L2DAY_G,1);
							break;
						case 6:
							st.giveItems(EventsConfig.L2DAY_H,1);
							break;
						case 7:
							st.giveItems(EventsConfig.L2DAY_I,1);
							break;
						case 8:
							st.giveItems(EventsConfig.L2DAY_L,1);
							break;
						case 9:
							st.giveItems(EventsConfig.L2DAY_N,1);
							break;
						case 10:
							st.giveItems(EventsConfig.L2DAY_O,1);
							break;
						case 11:
							st.giveItems(EventsConfig.L2DAY_R,1);
							break;
						case 12:
							st.giveItems(EventsConfig.L2DAY_S,1);
							break;
						case 13:
							st.giveItems(EventsConfig.L2DAY_T,1);
							break;
						case 14:
							st.giveItems(EventsConfig.L2DAY_II,1);
							break;
						case 15:
							st.giveItems(EventsConfig.L2DAY_Y,1);
							break;
						case 16:
							st.giveItems(EventsConfig.L2DAY_5,1);
						default:
							break;
					}
				}
			}			
		}
		return super.onKill(npc, player, isPet);
	}	
	
	public L2Day(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(L2DAY_CAT);
		addFirstTalkId(L2DAY_CAT);
		addTalkId(L2DAY_CAT);
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}		
	}
	public static void main(String[] args)
	{
		new L2Day(-1,qn,"events");
		if (EventsConfig.L2DAY_STARTED)
			_log.info("Event System: L2Day Event loaded ...");
	}
}