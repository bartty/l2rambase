/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.status;

import l2.rambase.gameserver.ai.CtrlEvent;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class PetStatus extends SummonStatus
{
	private int _currentFed               = 0; //Current Fed of the L2PetInstance

	public PetStatus(L2PetInstance activeChar)
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
		if (getActiveChar().isDead())
			return;

		super.reduceHp(value, attacker, awake, isDOT, isHpConsumption);

		if (attacker != null)
		{
			if (!isDOT && getActiveChar().getOwner() != null)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.PET_RECEIVED_S2_DAMAGE_BY_C1);
				sm.addCharName(attacker);
				sm.addNumber((int)value);
				getActiveChar().getOwner().sendPacket(sm);
			}
			getActiveChar().getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, attacker);
		}
	}

	public int getCurrentFed()
	{
		return _currentFed;
	}

	public void setCurrentFed(int value)
	{
		_currentFed = value;
	}

	@Override
	public L2PetInstance getActiveChar()
	{
		return (L2PetInstance)super.getActiveChar();
	}
}