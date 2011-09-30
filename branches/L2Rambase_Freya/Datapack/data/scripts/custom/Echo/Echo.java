package custom.Echo;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

/**
 * 
 * @author L2Rambase Project
 *
 */
public class Echo extends Quest
{
	private static final String qn = "Echo";

	private static final int[] NPCS	= { 31042,31043 };
	private static final int ADENA = 57;
	private static final long COST = 200;
	
	private static final int[] scoreList = {
		4410,4409,4408,4420,4421,4419,4418
	};
	private static final int[] crystalList = {
		4411,4412,4413,4414,4415,4417,4416
	};
	
	public Echo(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i : NPCS)
		{
			addStartNpc(i);
			addTalkId(i);
		}
	}
	
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);		
		if (st == null)
		{
			st = newQuestState(player);
		}
		htmltext ="<html><body><table width=200>";
		htmltext += "<tr><td align=\"center\">Echo Crystals Creation Menu</td></tr>";
		htmltext += "</table><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4410\">[Create an Echo Crystal - Theme of Journey]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4409\">[Create an Echo Crystal - Theme of Battle]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4408\">[Create an Echo Crystal - Theme of Love]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4420\">[Create an Echo Crystal - Theme of Solitude]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4421\">[Create an Echo Crystal - Theme of Feast]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4419\">[Create an Echo Crystal - Theme of Comedy]</a><br>";
		htmltext += "<a action=\"bypass -h Quest Echo 4418\">[Create an Echo Crystal - Theme of Celebration]</a>";
		htmltext += "</body></html>";		
		return htmltext;
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "Sorry but not working!!";
		if (st.getQuestItemsCount(Integer.valueOf(event)) < 1)
		{
			htmltext="<html><body><font color=FF0000>You don't have a score to create a Echo Crystal</font></body></html>";
		}
		else if (st.getQuestItemsCount(ADENA) < COST)
		{
			htmltext="<html><body><font color=FF0000>You don't have a adena to create a Echo Crystal</font></body></html>";
		}
		else
		{
			for (int i=0; i < scoreList.length; i++)
			{
				if (event.equalsIgnoreCase(String.valueOf(scoreList[i])))
				{
					st.takeItems(ADENA, COST);
					st.giveItems(crystalList[i],1);
					player.sendMessage("You created Echo Crystal");
					htmltext ="<html><body><table width=200>";
					htmltext += "<tr><td align=\"center\">Echo Crystals Creation Menu</td></tr>";
					htmltext += "</table><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4410\">[Create an Echo Crystal - Theme of Journey]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4409\">[Create an Echo Crystal - Theme of Battle]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4408\">[Create an Echo Crystal - Theme of Love]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4420\">[Create an Echo Crystal - Theme of Solitude]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4421\">[Create an Echo Crystal - Theme of Feast]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4419\">[Create an Echo Crystal - Theme of Comedy]</a><br>";
					htmltext += "<a action=\"bypass -h Quest Echo 4418\">[Create an Echo Crystal - Theme of Celebration]</a>";
					htmltext += "</body></html>";
				}
			}
		}				
		return htmltext;
	}

	public static void main(String[] args)
	{
		new Echo(-1, qn, "custom");
	}
}