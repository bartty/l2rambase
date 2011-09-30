/**
 * @author L2Rambase Project
 */
package custom.PathtoBecominganExaltedMercenary;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.util.Util;


public class PathtoBecominganExaltedMercenary extends Quest
{
	private static final String qn = "148_PathtoBecominganExaltedMercenary";
	// NPCs
	private static final int[] _merc = { 36481, 36482, 36483, 36484, 36485, 36486, 36487, 36488, 36489 };
	// Items
	private static final int _cert_elite = 13767;
	private static final int _cert_top_elite = 13768;
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		QuestState st = player.getQuestState(qn);
		
		if (st == null)
			return htmltext;
		
		if (Util.contains(_merc, npc.getNpcId()))
		{
			if (event.equalsIgnoreCase("exalted-00b.htm"))
			{
				st.giveItems(_cert_elite, 1);
			}
			else if (event.equalsIgnoreCase("exalted-03.htm"))
			{
				st.setState(State.STARTED);
				st.set("cond", "1");
				st.playSound("ItemSound.quest_accept");
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
		QuestState st = player.getQuestState(qn);
		if (st == null)
			return htmltext;
		
		if (Util.contains(_merc, npc.getNpcId()))
		{
			switch(st.getState())
			{
				case State.CREATED :
					QuestState _prev = player.getQuestState("147_PathtoBecominganEliteMercenary");
					if (st.hasQuestItems(_cert_elite))
					{
						htmltext = "exalted-01.htm";
					}
					else
					{
						if (_prev != null && _prev.getState() == State.COMPLETED)
							htmltext = "exalted-00a.htm";
						else
							htmltext = "exalted-00.htm";
					}
					break;
				case State.STARTED :
					if (st.getInt("cond") < 4)
					{
						htmltext = "elite-04.htm";
					}
					else if (st.getInt("cond") == 4)
					{
						st.unset("cond");
						st.unset("kills");
						st.takeItems(_cert_elite, -1);
						st.giveItems(_cert_top_elite, 1);
						st.exitQuest(false);
						htmltext = "exalted-05.htm";
					}
					break;
				case State.COMPLETED :
					htmltext = "<html><body>You already completed this quest.</body></html>";
					break;
			}
		}
		return htmltext;
	}
	
	public PathtoBecominganExaltedMercenary(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		for(int _npc : _merc)
		{
			addStartNpc(_npc);
			addTalkId(_npc);
		}
	}
	
	public static void main(String[] args)
	{
		new PathtoBecominganExaltedMercenary(148, qn, "Path to Becoming an Exalted Mercenary");
	}
}