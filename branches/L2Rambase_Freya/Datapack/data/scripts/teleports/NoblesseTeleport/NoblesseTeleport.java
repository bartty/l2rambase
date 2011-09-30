package teleports.NoblesseTeleport;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class NoblesseTeleport extends Quest
{
	private final static String qn = "2000_NoblesseTeleport";
	
	private static final int[] NPC = 
	{
		30006,30059,30080,30134,30146,
		30177,30233,30256,30320,30540,
		30576,30836,30848,30878,30899,
		31275,31320,31964,32163
	};
	
	public NoblesseTeleport(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npcId: NPC)
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
		if (event.startsWith("noble-nopass.htm"))
		{
			htmltext = "<html><body><br>You do not have any Olympiad Tokens. If you wish to continue, you must pay using Adena.<br>";
			htmltext += "<a action=\"bypass -h npc_"+npc.getObjectId() +"_Chat 0\">Return</a>";
			htmltext += "</body></html>";
		}
		return htmltext;
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);		
		if (player.isNoble())
		{
			String bypass = "Quest 2000_NoblesseTeleport noble-nopass.htm";
			if (st.getQuestItemsCount(13722) > 0)
			{
				bypass = "npc_%objectId%_Chat 3";
			}
			htmltext = "<html><body><br>Ah, you're a Noblesse! I can offer you a special service then.<br><br>You may use this Olympiad Token.<br>";
            htmltext += "<a action=\"bypass -h " + bypass +"\">Teleport to Hunting Grounds</a><br><br>Don't use Olympiad Token.<br>";
            htmltext += "<a action=\"bypass -h npc_"+npc.getObjectId() +"_Chat 2\">Teleport to Hunting Grounds</a><br>";
            htmltext += "<a action=\"bypass -h npc_"+npc.getObjectId() +"_Chat 0\">Back</a></body></html>";
		}
		else
		{
			htmltext = "<html><body><br>Only a Noblesse can use the Noblesse Only Teleport.<br>";
			htmltext += "<a action=\"bypass -h npc_"+npc.getObjectId() +"_Chat 0\">Return</a>";
			htmltext += "</body></html>"; 
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new NoblesseTeleport(-1, qn, "teleports");
	}
}