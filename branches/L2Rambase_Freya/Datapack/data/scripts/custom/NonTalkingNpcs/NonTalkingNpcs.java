package custom.NonTalkingNpcs;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;

public class NonTalkingNpcs extends Quest
{
	private static final String qn = "NonTalkingNpcs";
	
	private static final int[] NPCs = 
	{
		18684,18685,18686,18687,18688,18689,18690,19691,18692,31557,31606,
		31671,31672,31673,31674,32026,32030,32031,32032,32619,32620,32621
	};
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		player.sendPacket(ActionFailed.STATIC_PACKET);
		return htmltext;
	}
	
	public NonTalkingNpcs(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		for (int npcId : NPCs)
			addFirstTalkId(npcId);
	}
	public static void main(String[] args)
	{
		new NonTalkingNpcs(-1,qn,"NonTalkingsNpcs");
	}
}