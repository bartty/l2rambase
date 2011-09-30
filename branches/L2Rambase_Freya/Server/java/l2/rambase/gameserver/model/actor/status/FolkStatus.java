/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.status;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2NpcInstance;


public class FolkStatus extends NpcStatus
{
	public FolkStatus(L2Npc activeChar)
	{
		super(activeChar);
	}

	@Override
	public final void reduceHp(double value, L2Character attacker)
	{
		reduceHp(value, attacker, true, false, false);
	}

	@Override
	public final void reduceHp(double value, L2Character attacker, boolean awake, boolean isDOT, boolean isHpConsumption)
	{
	}

	@Override
	public final void reduceMp(double value)
	{
	}

	@Override
	public L2NpcInstance getActiveChar()
	{
		return (L2NpcInstance)super.getActiveChar();
	}
}