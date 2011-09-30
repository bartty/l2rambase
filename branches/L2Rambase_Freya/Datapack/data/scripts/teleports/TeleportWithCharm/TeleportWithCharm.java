package teleports.TeleportWithCharm;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class TeleportWithCharm extends Quest
{
	private final static String qn = "1100_teleport_with_charm";

	private final static int ORC_GATEKEEPER_CHARM     = 1658;
	private final static int DWARF_GATEKEEPER_TOKEN   = 1659;
	private final static int WHIRPY      = 30540;
	private final static int TAMIL      = 30576;
	
	public TeleportWithCharm(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(WHIRPY);
		addTalkId(WHIRPY);
		addStartNpc(TAMIL);
		addTalkId(TAMIL);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);		
		int npcId = npc.getNpcId();
		// ORC_VILLAGE
		if (npcId == TAMIL)
		{
			if (st.getQuestItemsCount(ORC_GATEKEEPER_CHARM) >= 1)
			{
				st.takeItems(ORC_GATEKEEPER_CHARM,1);
				st.getPlayer().teleToLocation(-80826,149775,-3043);
				st.exitQuest(true);
				return htmltext;
			}
			else
			{
				st.exitQuest(true);
				htmltext = "<html><body>Gatekeeper Tamil:<br>";
				htmltext += "You Cannot teleport without the Gatekeeper Charm. I will give it to you if you complete my quest.</body></html>";
				return htmltext;
			}
		}
		// DWARVEN_VILLAGE
		else if (npcId == WHIRPY)
		{
			if (st.getQuestItemsCount(DWARF_GATEKEEPER_TOKEN) >= 1)
			{
				st.takeItems(DWARF_GATEKEEPER_TOKEN,1);
				st.getPlayer().teleToLocation(-80826,149775,-3043);
				st.exitQuest(true);
				return htmltext;
			}
			else
			{
				st.exitQuest(true);
				htmltext = "<html><body>Gatekeeper Wirphy:<br>";
				htmltext += "My sensory apparatus tells me that you don't have the Gatekeeper token. If you will go out and acquire starstones for me, I'll give you the Gatekeeper token.</body></html>";
				return htmltext;
			}
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new TeleportWithCharm(-1, qn, "teleports");
	}
}