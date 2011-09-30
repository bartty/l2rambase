package teleports.StrongholdsTeleports;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class StrongholdsTeleports extends Quest
{
	private static final String qn = "StrongholdsTeleports";
	
	private static final int[] npcs =
	{
		32163,32181,32184,32186
	};
	
	public StrongholdsTeleports(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npcId: npcs)
		{
			addStartNpc(npcId);
			addFirstTalkId(npcId);
		}		
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		String htmltext = "";
		if (player.getLevel() < 20)
		{
			htmltext = String.valueOf(npc.getNpcId()) + ".htm";			
		}
		else
		{
			htmltext = String.valueOf(npc.getNpcId()) + "-no.htm";
		}
		if (htmltext == "")
			npc.showChatWindow(player);		    
		return htmltext;
	}	

	public static void main(String[] args)
	{
		new StrongholdsTeleports(-1, qn, "teleports");
	}
}