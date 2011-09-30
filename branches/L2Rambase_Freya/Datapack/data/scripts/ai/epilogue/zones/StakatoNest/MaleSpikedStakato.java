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
import l2.rambase.gameserver.model.actor.instance.L2Rambase ProjectonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;


public class MaleSpikedStakato extends L2AttackableAIScript
{
	private static final int FEMALE_SPIKED_STAKATO = 22620;
	//private static final int MALE_SPIKED_STAKATO = 22621;
	private static final int MALE_SPIKED_STAKATO_2ND_FORM = 22622;
	
	public MaleSpikedStakato(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addKillId(FEMALE_SPIKED_STAKATO);
	}

	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		final L2Npc couple = getCouple(npc);
		if (couple != null && !couple.isDead())
		{
			couple.doDie(couple);
			final L2Npc newForm = addSpawn(MALE_SPIKED_STAKATO_2ND_FORM, npc.getX() + Rnd.get(10, 50), npc.getY() + Rnd.get(10, 50), npc.getZ(), 0, false, 0, true);
			newForm.setRunning();
			((L2Attackable) newForm).addDamageHate(killer, 1, 99999);
			newForm.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, killer);
		}
		return super.onKill(npc, killer, isPet);
	}

	public L2Npc getCouple(L2Npc couple)
	{
		// For now, minions are set as minionInstance. If they change to only monster, use the above code
		return ((L2Rambase ProjectonsterInstance)couple).getLeader();
	}

	public static void main(String[] args)
	{
		new MaleSpikedStakato(-1, "MaleSpikedStakato", "ai");
	}
}