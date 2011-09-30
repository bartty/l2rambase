package teleports.NewbieTravelToken;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class NewbieTravelToken extends Quest
{
	private static final String qn = "1104_NewbieTravelToken";
	private static final int TOKEN = 8542;
	private static final int[] NPCS =
	{
		30600,30601,30599,30602,30598,32135
	};
	
	private static final String de = "<a action=\"bypass -h Quest 1104_NewbieTravelToken de\">Dark Elven Village - 1 Newbie Travel Token</a><br>";
	private static final String dw = "<a action=\"bypass -h Quest 1104_NewbieTravelToken dw\">Dwarven Village - 1 Newbie Travel Token</a><br>";
	private static final String ev = "<a action=\"bypass -h Quest 1104_NewbieTravelToken ev\">Elven Village - 1 Newbie Travel Token</a><br>";
	private static final String ov = "<a action=\"bypass -h Quest 1104_NewbieTravelToken ov\">Orc Village - 1 Newbie Travel Token</a><br>";
	private static final String ti = "<a action=\"bypass -h Quest 1104_NewbieTravelToken ti\">Talking Island - 1 Newbie Travel Token</a><br>";
	private static final String kv = "<a action=\"bypass -h Quest 1104_NewbieTravelToken kv\">Kamael Village - 1 Newbie Travel Token</a><br>";	
	
	private static final int[][] DATA = 
	{
			{12160,  16554,-4583},			//Dark Elven Village
			{115594, -177993, -912},		//Dwarven Village
			{45470,  48328,-3059},			//Elven Village
			{-45067,-113563, -199},			//Orc Village
			{-84053, 243343,-3729},			//Talking Island 
			{-119712, 44519,368}			//Kamael Village
	};
	
	public NewbieTravelToken(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npcId: NPCS)
		{
			addStartNpc(npcId);
			addTalkId(npcId);
		}
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (event.startsWith("de"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[0][0],DATA[0][1],DATA[0][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		if (event.startsWith("dw"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[1][0],DATA[1][1],DATA[1][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		if (event.startsWith("ev"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[2][0],DATA[2][1],DATA[2][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		if (event.startsWith("ov"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[3][0],DATA[3][1],DATA[3][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		if (event.startsWith("ti"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[4][0],DATA[4][1],DATA[4][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		if (event.startsWith("kv"))
		{
			if (st.getQuestItemsCount(TOKEN)>0)
			{
				st.takeItems(TOKEN,1);
				player.teleToLocation(DATA[5][0],DATA[5][1],DATA[5][2]);
			}
			else
			{
				st.exitQuest(true);
				return "Incorrect item count";
			}
		}
		st.exitQuest(true);
		return htmltext;
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (player.getLevel() >= 20)
		{
			htmltext = "<html><body>Newbie Guide:<br>";
			htmltext += "Characters of level 20 or above may not use the Newbie Travel Token to teleport.<br>";
			htmltext += "Newbie Travel Token were designed to help newcomers to this world get started.";
			htmltext += "</body></html>";
			st.exitQuest(true);
		}	
		else
		{
			int npcId = npc.getNpcId();
			htmltext = "<html><body>&$556;<br>";
			String teleports = "";
			if (npcId == 30600)
			{
				teleports = dw + ti + ev + ov + kv;
			}
			else if (npcId == 30601)
			{
				teleports = de + ti + ev + ov + kv;
			}
			else if (npcId == 30599)
			{
				teleports = de + dw + ti + ov + kv;
			}
			else if (npcId == 30602)
			{
				teleports = de + dw + ti + ev + kv;
			}
			else if (npcId == 30598)
			{
				teleports = de + dw + ev + ov + kv;
			}
			else if (npcId == 32135)
			{
				teleports = de + dw + ti + ev + ov;
			}
			htmltext += teleports;
			htmltext += "</body></html>";
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new NewbieTravelToken(-1, qn, "teleports");
	}
}