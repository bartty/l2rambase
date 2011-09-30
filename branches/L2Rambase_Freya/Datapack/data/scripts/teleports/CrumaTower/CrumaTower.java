package teleports.CrumaTower;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class CrumaTower extends Quest
{
	private final static String qn = "1107_CrumaTower";
	
	private static final int npc = 30483;
	
	public CrumaTower(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(npc);
		addTalkId(npc);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);		
		if (player.getLevel() > 55)
		{
		      htmltext = "<html><body>Gatekeeper Mozella:<br>";
		      htmltext += "Ah! You are too strong for the Gate of Time and Space to open.";
		      htmltext += "You seem to have exceeded the limits of the tower's magnetic field.<br>";
		      htmltext += "(Characters who are level 56 and above cannot enter the Cruma Tower.)";
		      htmltext += "</body></html>";
		      
		}
		else
		{
		      player.teleToLocation(17724,114004,-11672);
		}
		st.exitQuest(true);
		return htmltext;
	}

	public static void main(String[] args)
	{
		new CrumaTower(-1, qn, "teleports");
	}
}