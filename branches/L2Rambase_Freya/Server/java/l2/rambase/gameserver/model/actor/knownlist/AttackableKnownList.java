/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import java.util.Collection;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2NpcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class AttackableKnownList extends NpcKnownList
{
	public AttackableKnownList(L2Attackable activeChar)
	{
		super(activeChar);
	}

	@Override
	protected boolean removeKnownObject(L2Object object, boolean forget)
	{
		if (!super.removeKnownObject(object, forget))
			return false;

		// Remove the L2Object from the _aggrolist of the L2Attackable
		if (object instanceof L2Character)
			getActiveChar().getAggroList().remove(object);
		// Set the L2Attackable Intention to AI_INTENTION_IDLE
		final Collection<L2PcInstance> known = getKnownPlayers().values();

		if (getActiveChar().hasAI() && (known == null || known.isEmpty()))
			getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);

		return true;
	}

	@Override
	public L2Attackable getActiveChar()
	{
		return (L2Attackable)super.getActiveChar();
	}

	@Override
	public int getDistanceToForgetObject(L2Object object)
	{
		if (getActiveChar().getAggroList().get(object) != null) 
			return 3000;

		return Math.min(2200, 2 * getDistanceToWatchObject(object));
	}

	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		if (object instanceof L2NpcInstance
				|| !(object instanceof L2Character))
			return 0;

		if (object instanceof L2Playable)
			return object.getKnownList().getDistanceToWatchObject(getActiveObject());

		if (getActiveChar().getAggroRange() > getActiveChar().getFactionRange())
			return getActiveChar().getAggroRange();

		if (getActiveChar().getFactionRange() > 300)
			return getActiveChar().getFactionRange();

		return 300;
	}
}