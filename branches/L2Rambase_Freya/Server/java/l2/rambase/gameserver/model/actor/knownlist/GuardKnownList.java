/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.instance.L2GuardInstance;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class GuardKnownList extends AttackableKnownList
{
	private static final Logger _log = Logger.getLogger(GuardKnownList.class.getName());

	public GuardKnownList(L2GuardInstance activeChar)
	{
		super(activeChar);
	}

	@Override
	public boolean addKnownObject(L2Object object)
	{
		if (!super.addKnownObject(object))
			return false;

		if (object instanceof L2PcInstance)
		{
			// Check if the object added is a L2PcInstance that owns Karma
			if (((L2PcInstance)object).getKarma() > 0)
			{
				if (Config.DEBUG)
					_log.fine(getActiveChar().getObjectId()+": PK "+object.getObjectId()+" entered scan range");

				// Set the L2GuardInstance Intention to AI_INTENTION_ACTIVE
				if (getActiveChar().getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)
					getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null);
			}
		}
		else if ((Config.GUARD_ATTACK_AGGRO_MOB && getActiveChar().isInActiveRegion())
				&& object instanceof L2MonsterInstance)
		{
			// Check if the object added is an aggressive L2MonsterInstance
			if (((L2MonsterInstance)object).isAggressive())
			{
				if (Config.DEBUG)
					_log.fine(getActiveChar().getObjectId()+": Aggressive mob "+object.getObjectId()+" entered scan range");

				// Set the L2GuardInstance Intention to AI_INTENTION_ACTIVE
				if (getActiveChar().getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)
					getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null);
			}
		}

		return true;
	}

	@Override
	protected boolean removeKnownObject(L2Object object, boolean forget)
	{
		if (!super.removeKnownObject(object, forget))
			return false;

		// Check if the _aggroList of the L2GuardInstance is Empty
		if (getActiveChar().noTarget())
		{
			//removeAllKnownObjects();

			// Set the L2GuardInstance to AI_INTENTION_IDLE
			if (getActiveChar().hasAI())
				getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);
		}

		return true;
	}

	@Override
	public final L2GuardInstance getActiveChar()
	{
		return (L2GuardInstance)super.getActiveChar();
	}
}