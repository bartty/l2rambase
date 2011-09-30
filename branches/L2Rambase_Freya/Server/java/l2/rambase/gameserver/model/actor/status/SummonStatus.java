/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.status;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Duel;


public class SummonStatus extends PlayableStatus
{
	public SummonStatus(L2Summon activeChar)
	{
		super(activeChar);
	}

	@Override
	public void reduceHp(double value, L2Character attacker)
	{
		reduceHp(value, attacker, true, false, false);
	}

	@Override
	public void reduceHp(double value, L2Character attacker, boolean awake, boolean isDOT, boolean isHPConsumption)
	{
		if (getActiveChar().isDead())
			return;

		if (attacker != null)
		{
			final L2PcInstance attackerPlayer = attacker.getActingPlayer();
			if (attackerPlayer != null
					&& (getActiveChar().getOwner() == null
							|| getActiveChar().getOwner().getDuelId() != attackerPlayer.getDuelId()))
				attackerPlayer.setDuelState(Duel.DUELSTATE_INTERRUPTED);
		}		
		super.reduceHp(value, attacker, awake, isDOT, isHPConsumption);
	}

	@Override
	public L2Summon getActiveChar()
	{
		return (L2Summon)super.getActiveChar();
	}
}