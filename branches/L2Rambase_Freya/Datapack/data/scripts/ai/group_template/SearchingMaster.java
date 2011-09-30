/*
 * L2Rambase Project Project
 */
package ai.group_template;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class SearchingMaster extends L2AttackableAIScript
{
	private static final int[] mobs =
	{
		20965,20966,20967,20968,20969,20970,20971,20972,20973
	};

	public SearchingMaster(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int id : mobs)
			addAttackId(id);
	}

	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet)
	{
		if (player == null)
			return null;
		
		L2Character attacker = isPet ? player.getPet().getOwner() : player;

		npc.setIsRunning(true);
		((L2Attackable) npc).addDamageHate(attacker, 0, 999);
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);

		return super.onAttack(npc, player, damage, isPet);
	}

	public static void main(String[] args)
	{
		new SearchingMaster(-1, "SearchingMaster", "ai");
	}
}