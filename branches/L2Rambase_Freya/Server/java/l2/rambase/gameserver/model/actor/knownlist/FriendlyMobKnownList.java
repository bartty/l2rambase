/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.ai.CtrlEvent;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2FriendlyMobInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class FriendlyMobKnownList extends AttackableKnownList
{
	public FriendlyMobKnownList(L2FriendlyMobInstance activeChar)
	{
		super(activeChar);
	}

	@Override
	public boolean addKnownObject(L2Object object)
	{
		if (!super.addKnownObject(object))
			return false;

		if (object instanceof L2PcInstance
				&& getActiveChar().getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)
			getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null);

		return true;
	}

	@Override
	protected boolean removeKnownObject(L2Object object, boolean forget)
	{
		if (!super.removeKnownObject(object, forget))
			return false;

		if (!(object instanceof L2Character))
			return true;

		if (getActiveChar().hasAI())
		{
			getActiveChar().getAI().notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, object);
			if (getActiveChar().getTarget() == (L2Character)object)
				getActiveChar().setTarget(null);
		}

		if (getActiveChar().isVisible()
				&& getKnownPlayers().isEmpty()
				&& getKnownSummons().isEmpty())
		{
			getActiveChar().clearAggroList();
			//removeAllKnownObjects();
			if (getActiveChar().hasAI())
				getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);
		}

		return true;
	}

	@Override
	public final L2FriendlyMobInstance getActiveChar()
	{
		return (L2FriendlyMobInstance)super.getActiveChar();
	}
}