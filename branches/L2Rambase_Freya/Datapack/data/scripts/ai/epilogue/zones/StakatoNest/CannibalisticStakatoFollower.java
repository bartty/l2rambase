/**
 * @author L2Rambase Project Project
 * Added special script for minions in core getMinionList()
 * 
 * Fixed by L2Rambase Project Project in 18.06.2011
 */
package ai.epilogue.zones.StakatoNest;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2Rambase ProjectonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.MagicSkillUse;
import l2.rambase.util.Rnd;



public class CannibalisticStakatoFollower extends L2AttackableAIScript
{
	private static final int CANNIBALISTIC_LEADER = 22625;
	//private static final int CANNIBALISTIC_FOLLOWER = 22624;
	
	public CannibalisticStakatoFollower(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addAttackId(CANNIBALISTIC_LEADER);
	}

	@Override
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet)
	{
		if (npc.getMaxHp() * 0.3 > npc.getCurrentHp())
		{
			if (Rnd.get(100) <= 25)
			{
				final L2Npc minion = getLeaderMinion(npc);
				if (minion != null && !minion.isDead())
				{
					npc.broadcastPacket(new MagicSkillUse(npc, minion, 4485, 1, 3000, 0));
					ThreadPoolManager.getInstance().scheduleGeneral(new eatTask(npc, minion), 3000);
				}
			}
		}
		return super.onAttack(npc, player, damage, isPet);
	}

	public L2Npc getLeaderMinion(L2Npc leader)
	{
		// For now, minions are set as minionInstance. If they change to only monster, use the above code
		if (((L2Rambase ProjectonsterInstance)leader).hasMinions())
			return ((L2Rambase ProjectonsterInstance)leader).getMinionList().getSpawnedMinions().get(0);
		
		return null;
	}

	private class eatTask implements Runnable
	{
		private L2Npc _npc;
		private L2Npc _minion;
		
		private eatTask (L2Npc npc, L2Npc minion)
		{
			_npc = npc;
			_minion = minion;
		}
		
		public void run()
		{
			if (_minion == null)
				return;
			
			final double hpToSacrifice = _minion.getCurrentHp();
			_npc.setCurrentHp(_npc.getCurrentHp() + hpToSacrifice);
			_npc.broadcastPacket(new MagicSkillUse(_npc, _minion, 4484, 1, 1000, 0));
			_minion.doDie(_minion);
		}
	}

	public static void main(String[] args)
	{
		new CannibalisticStakatoFollower(-1, "CannibalisticStakatoFollower", "ai");
	}
}
