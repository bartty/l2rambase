package teleports.ToiVortexExit;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class ToiVortexExit extends Quest
{
	private final static String qn = "2400_toivortex_exit";
	
	private static final int npc = 29055;
	
	public ToiVortexExit(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(npc);
		addTalkId(npc);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		int chance = st.getRandom(3);
		int x,y,z;
	    if (chance == 0)
	    {
	    	x = 108784+st.getRandom(100);
	    	y = 16000+st.getRandom(100);
	    	z = -4928;
	    }
	    else if (chance == 1)
	    {
	    	x = 113824+st.getRandom(100);
	    	y = 10448+st.getRandom(100);
	    	z = -5164;
	    }
	    else
	    {
	    	x = 115488+st.getRandom(100);
	    	y = 22096+st.getRandom(100);
	    	z = -5168;
	    }
	    player.teleToLocation(x,y,z);
		st.exitQuest(true);
		return htmltext;
	}

	public static void main(String[] args)
	{
		new ToiVortexExit(-1, qn, "teleports");
	}
}