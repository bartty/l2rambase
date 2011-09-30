package custom.HeroCirclet;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class HeroCirclet extends Quest
{
	private final static int[] npcIds =
	{
		31690,31769,31770,31771,31772
	};

	public HeroCirclet(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i : npcIds)
		{
			addStartNpc(i);
			addTalkId(i);
		}
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			newQuestState(player);

		if (player.isHero())
		{
			if (player.getInventory().getItemByItemId(6842) == null)
				st.giveItems(6842, 1);
			else
				htmltext = "already_have_circlet.htm";
		}
		else
			htmltext = "no_hero.htm";

		st.exitQuest(true);
		return htmltext;
	}

	public static void main(String[] args)
	{
		new HeroCirclet(-1, "HeroCirclet", "custom");
	}
}