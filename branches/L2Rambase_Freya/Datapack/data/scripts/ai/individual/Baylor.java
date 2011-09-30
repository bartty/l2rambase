package ai.individual;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;

import ai.group_template.L2AttackableAIScript;

public class Baylor extends L2AttackableAIScript
{
	private final int STATUE = 32109;
	// private final int CRYSTALINE = 29100;
	private final int BAYLOR = 29099;

	private final int CHEST = 29116;
	
	private final int ORACLE_GUIDE = 32273;
	private final int PARME = 32271;
	
	public Baylor(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(STATUE);
		addKillId(BAYLOR);
	}

	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState("baylor");
		int npcId = npc.getNpcId();
		if (npcId == BAYLOR)
		{
			int instanceId = npc.getInstanceId();
			addSpawn(CHEST, 153763, 142075, -12741, 64792, false, 0, false, instanceId);
			addSpawn(CHEST, 153701, 141942, -12741, 57739, false, 0, false, instanceId);
			addSpawn(CHEST, 153573, 141894, -12741, 49471, false, 0, false, instanceId);
			addSpawn(CHEST, 153445, 141945, -12741, 41113, false, 0, false, instanceId);
			addSpawn(CHEST, 153381, 142076, -12741, 32767, false, 0, false, instanceId);
			addSpawn(CHEST, 153441, 142211, -12741, 25730, false, 0, false, instanceId);
			addSpawn(CHEST, 153573, 142260, -12741, 16185, false, 0, false, instanceId);
			addSpawn(CHEST, 153706, 142212, -12741, 7579, false, 0, false, instanceId);
			addSpawn(CHEST, 153571, 142860, -12741, 16716, false, 0, false, instanceId);
			addSpawn(CHEST, 152783, 142077, -12741, 32176, false, 0, false, instanceId);
			addSpawn(CHEST, 153571, 141274, -12741, 49072, false, 0, false, instanceId);
			addSpawn(CHEST, 154365, 142073, -12741, 64149, false, 0, false, instanceId);
			addSpawn(CHEST, 154192, 142697, -12741, 7894, false, 0, false, instanceId);
			addSpawn(CHEST, 152924, 142677, -12741, 25072, false, 0, false, instanceId);
			addSpawn(CHEST, 152907, 141428, -12741, 39590, false, 0, false, instanceId);
			addSpawn(CHEST, 154243, 141411, -12741, 55500, false, 0, false, instanceId);
			addSpawn(ORACLE_GUIDE, 154243, 141411, -12741, 55500, false, 0, false, instanceId);
			addSpawn(PARME, 153570, 142067, -9727, 55500, false, 0, false, instanceId);
			if (st == null)
				return "";
			st.exitQuest(true);
		}
		return "";
	}

	public static void main(String[] args)
	{
		new Baylor(-1, "baylor", "ai");
	}
}