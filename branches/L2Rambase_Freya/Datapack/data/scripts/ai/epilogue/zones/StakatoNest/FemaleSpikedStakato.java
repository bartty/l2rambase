/**
 * @author L2Rambase Project Project
 * 
 * Fixed by L2Rambase Project Project in 18.06.2011
 */
package ai.epilogue.zones.StakatoNest;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;



public class FemaleSpikedStakato extends L2AttackableAIScript
{
	private static final int MALE_SPIKED_STAKATO = 22621;
	//private static final int FEMALE_SPIKED_STAKATO = 22620;
	private static final int SPIKED_STAKATO_GUARD = 22619;
	
	public FemaleSpikedStakato(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addKillId(MALE_SPIKED_STAKATO);
	}

	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		final L2Npc couple = getCouple(npc);
		if (couple != null && !couple.isDead())
		{
			for (int i = 0; i < 3; i++)
			{
				// Set despawn delay 4 minutes for spawned minions. To avoid multiple instances over time in the same place
				final L2Npc guard = addSpawn(SPIKED_STAKATO_GUARD, npc.getX() + Rnd.get(10, 50), npc.getY() + Rnd.get(10, 50), npc.getZ(), 0, false, 240000, true);
				guard.setRunning();
				((L2Attackable) guard).addDamageHate(killer, 1, 99999);
				guard.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, killer);
			}
		}
		return super.onKill(npc, killer, isPet);
	}

	public L2Npc getCouple(L2Npc couple)
	{
		// For now, minions are set as minionInstance. If they change to only monster, use the above code
		if (((L2MonsterInstance)couple).hasMinions())
			return ((L2MonsterInstance)couple).getMinionList().getSpawnedMinions().get(0);
		
		return null;
	}

	public static void main(String[] args)
	{
		new FemaleSpikedStakato(-1, "FemaleSpikedStakato", "ai");
	}
}