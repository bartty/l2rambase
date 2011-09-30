/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.ai.CtrlEvent;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class MonsterKnownList extends AttackableKnownList
{
	public MonsterKnownList(L2MonsterInstance activeChar)
	{
		super(activeChar);
	}

	@Override
	public boolean addKnownObject(L2Object object)
	{
		if (!super.addKnownObject(object))
			return false;

		// Set the L2MonsterInstance Intention to AI_INTENTION_ACTIVE if the state was AI_INTENTION_IDLE
		if (object instanceof L2PcInstance
				&& getActiveChar().hasAI()
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
			// Notify the L2MonsterInstance AI with EVT_FORGET_OBJECT
			getActiveChar().getAI().notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, object);
		}

		if (getActiveChar().isVisible()
				&& getKnownPlayers().isEmpty()
				&& getKnownSummons().isEmpty())
		{
			// Clear the _aggroList of the L2MonsterInstance
			getActiveChar().clearAggroList();

			// Remove all L2Object from _knownObjects and _knownPlayer of the L2MonsterInstance then cancel Attak or Cast and notify AI
			//removeAllKnownObjects();
		}

		return true;
    }

	@Override
	public final L2MonsterInstance getActiveChar()
	{
		return (L2MonsterInstance)super.getActiveChar();
	}
}