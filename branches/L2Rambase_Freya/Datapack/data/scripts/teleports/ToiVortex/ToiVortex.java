package teleports.ToiVortex;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class ToiVortex extends Quest
{
	private static final String qn = "ToiVortex";
	
	private static final int GREEN_DIMENSION_STONE    = 4401;
	private static final int BLUE_DIMENSION_STONE    = 4402;
	private static final int RED_DIMENSION_STONE    = 4403;
	
	private static final int DIMENSION_VORTEX_1      = 30952;
	private static final int DIMENSION_VORTEX_2    	 = 30953;
	private static final int DIMENSION_VORTEX_3      = 30954;
	
	public ToiVortex(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(DIMENSION_VORTEX_1);
		addStartNpc(DIMENSION_VORTEX_2);
		addStartNpc(DIMENSION_VORTEX_3);
		addFirstTalkId(DIMENSION_VORTEX_1);
		addFirstTalkId(DIMENSION_VORTEX_2);
		addFirstTalkId(DIMENSION_VORTEX_3);
		addTalkId(DIMENSION_VORTEX_1);
		addTalkId(DIMENSION_VORTEX_2);
		addTalkId(DIMENSION_VORTEX_3);
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st ==null)
		{
			st = newQuestState(player);
		}
		if (event.equalsIgnoreCase("blue"))
		{
			if (st.getQuestItemsCount(BLUE_DIMENSION_STONE) >= 1)
			{
				st.takeItems(BLUE_DIMENSION_STONE,1);
				st.getPlayer().teleToLocation(114097,19935,935);
				st.exitQuest(true);
				return "";
			}
			else
			{
				st.exitQuest(true);
				htmltext = "<html><body>Dimensional Vortex:<br>";
				htmltext += "You do not have the proper stones needed for teleport.</body></html>";	    
			}
		}
		else if (event.equalsIgnoreCase("green"))
		{
			if (st.getQuestItemsCount(GREEN_DIMENSION_STONE) >= 1)
			{
				st.takeItems(GREEN_DIMENSION_STONE,1);
				st.getPlayer().teleToLocation(110930,15963,-4378);
				st.exitQuest(true);
				return "";
			}
			else
			{
				st.exitQuest(true);
				htmltext = "<html><body>Dimensional Vortex:<br>";
				htmltext += "You do not have the proper stones needed for teleport.</body></html>";
			}
		}
		else if (event.equalsIgnoreCase("red"))
		{
			if (st.getQuestItemsCount(RED_DIMENSION_STONE) >= 1)
			{
				st.takeItems(RED_DIMENSION_STONE,1);
				st.getPlayer().teleToLocation(118558,16659,5987);
				st.exitQuest(true);
				return "";
			}
			else
			{
				st.exitQuest(true);
				htmltext = "<html><body>Dimensional Vortex:<br>";
				htmltext += "You do not have the proper stones needed for teleport.</body></html>";
			}
		}
		return htmltext;
	}
	
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st ==null)
		{
			st = newQuestState(player);
		}
		int npcId = npc.getNpcId();
		if (npcId == DIMENSION_VORTEX_1)
		{
			htmltext = "<html><body>Dimension Vortex:<br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex blue\">Use a blue dimension stone to move to the fifth floor</a><br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex red\">Use a red dimension stone to move to the tenth floor</a>";
			htmltext += "</body></html>";
		}
		else if (npcId == DIMENSION_VORTEX_2)
		{
			htmltext = "<html><body>Dimension Vortex:<br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex green\">Use a green dimension stone to move to the first floor</a><br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex red\">Use a red dimension stone to move to the tenth floor</a>";
			htmltext += "</body></html>";
		}
		else if (npcId == DIMENSION_VORTEX_3)
		{
			htmltext = "<html><body>Dimension Vortex:<br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex green\">Use a green dimension stone to move to the first floor</a><br>";
			htmltext += "<a action=\"bypass -h Quest ToiVortex blue\">Use a blue dimension stone to move to the fifth floor</a>";
			htmltext += "</body></html>";
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new ToiVortex(-1, "ToiVortex", "teleports");
	}
}