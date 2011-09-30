/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Vehicle;

public abstract class L2VehicleAI extends L2CharacterAI
{
	public L2VehicleAI(L2Vehicle.AIAccessor accessor)
	{
		super(accessor);
	}

	@Override
	protected void onIntentionAttack(L2Character target)
	{
	}

	@Override
	protected void onIntentionCast(L2Skill skill, L2Object target)
	{
	}

	@Override
	protected void onIntentionFollow(L2Character target)
	{
	}

	@Override
	protected void onIntentionPickUp(L2Object item)
	{
	}

	@Override
	protected void onIntentionInteract(L2Object object)
	{
	}

	@Override
	protected void onEvtAttacked(L2Character attacker)
	{
	}

	@Override
	protected void onEvtAggression(L2Character target, int aggro)
	{
	}

	@Override
	protected void onEvtStunned(L2Character attacker)
	{
	}

	@Override
	protected void onEvtSleeping(L2Character attacker)
	{
	}

	@Override
	protected void onEvtRooted(L2Character attacker)
	{
	}

	@Override
	protected void onEvtForgetObject(L2Object object)
	{
	}

	@Override
	protected void onEvtCancel()
	{
	}

	@Override
	protected void onEvtDead()
	{
	}

	@Override
	protected void onEvtFakeDeath()
	{
	}

	@Override
	protected void onEvtFinishCasting()
	{
	}

	@Override
	protected void clientActionFailed()
	{
	}

	@Override
	protected void moveToPawn(L2Object pawn, int offset)
	{
	}

	@Override
	protected void clientStoppedMoving()
	{
	}
}