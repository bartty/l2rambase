package events.L2Marks;

import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.gameserver.model.quest.State;

import events.EventsConfig;

/**
 * @recoded for Hi5 - ShadowMan
 */
public class L2Marks extends Quest
{
	private static final String qn = "L2Marks";
	//NPCs
	private static final int MERCHANT = 50024;
	/*
	 * List of Monsters 80+ Epiloque Farms
	 * 
	 */
	private static final int[] GiantCave = {
    	22660,22661,22662,22663,22664,
    	22665,22666,22667,22668,22669,
    	22670,22671,22672,22673,22674,
    	22675,22676,22677    	
	};
    private static final int[] MithrilMines = {
    	22678,22679,22680,22681,22682,
    	22683,22684,22685,22686,22687,
    	22688,22689,22690
	};
    private static final int[] Fields = {
    	22650,22651,22652,22653,22654,
    	22655,22656,22657,22658,22659,
    	18805
	};
    private static final int[] DenOfEvil = {
    	22691,22692,22693,22694,22695,
    	22696,22697,22698,22699,22700,
    	22701,22702
	};
    private static final int[] StakatoNest = {
    	22617,22618,22619,22620,22621,
    	22622,22623,22624,22625,22626,
    	22627,22628,22629,22630,22631,
    	22632,22633
	};
    private static final int[] CryptOfDisgrace = {
    	22703,22704,22705
	};    
    private static final int[] PrimevalIsle = {
    	22196,22197,22198,22199,22200,
		22201,22202,22203,22204,22205,
		22208,22209,22210,22211,22212,
		22213,22214,22215,22216,22217,
		22218,22219,22220,22221,22222,
		22223,22224,22225,22226,22227,
		22742,22743,22744,22745
    };
	
	public static boolean contains(int[] array, int obj)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == obj)
				return true;
		}
		return false;
	}
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
			st.setState(State.STARTED);
		}
		String htmltext = "";
		if (EventsConfig.L2MARKS_MERCHANT_STARTED)
		{
			htmltext = "welcome.htm";
		}
		else
		{
			htmltext = "welcome-no.htm";
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
		if (event.equalsIgnoreCase("getprizes"))
		{
			htmltext = "prize.htm";			
		}
		if (event.equalsIgnoreCase("production"))
		{
			htmltext = "mats.htm";			
		}
		if (event.equalsIgnoreCase("getenchant"))
		{
			htmltext = "enchant.htm";			
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
	
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int npcId = npc.getNpcId();
		if (player.getLevel() > EventsConfig.L2MARKS_VITAMIN_LEVEL)
		{
			if (contains(GiantCave, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(MithrilMines, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(Fields, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(DenOfEvil, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(StakatoNest, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(CryptOfDisgrace, npcId))
			{ 
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
			else if (contains(PrimevalIsle, npcId))
			{
				st.giveItems(EventsConfig.L2MARKS_COIN,1);
			}
		}
		return super.onKill(npc, player, isPet);
	}
	
	public L2Marks(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(MERCHANT);
		addFirstTalkId(MERCHANT);
		addTalkId(MERCHANT);
		for (int id : GiantCave)
		{
			addKillId(id);
		}
		for (int id : MithrilMines)
		{
			addKillId(id);
		}
		for (int id : Fields)
		{
			addKillId(id);
		}
		for (int id : DenOfEvil)
		{
			addKillId(id);
		}
		for (int id : StakatoNest)
		{
			addKillId(id);
		}
		for (int id : CryptOfDisgrace)
		{
			addKillId(id);
		}
	}
	public static void main(String[] args)
	{
		new L2Marks(-1,qn,"events");
		_log.warning("Event System: L2Marks loaded ...");
	}
}