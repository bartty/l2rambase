/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.Config;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.ai.L2CharacterAI;
import l2.rambase.gameserver.ai.L2ControllableMobAI;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2ControllableMobInstance extends L2MonsterInstance
{
	private boolean _isInvul;
	private L2ControllableMobAI _aiBackup;	// to save ai, avoiding beeing detached

	protected class ControllableAIAcessor extends AIAccessor
    {
		@Override
		public void detachAI()
        {
			// do nothing, AI of controllable mobs can't be detached automatically
		}
	}


	@Override
	public boolean isAggressive()
    {
		return true;
	}

	@Override
	public int getAggroRange()
    {
		// force mobs to be aggro
		return 500;
	}

	public L2ControllableMobInstance(int objectId, L2NpcTemplate template)
    {
		super(objectId, template);
		setInstanceType(InstanceType.L2ControllableMobInstance);
	}

	@Override
	public L2CharacterAI getAI()
    {
		L2CharacterAI ai = _ai; // copy handle
		if (ai == null)
		{
			synchronized(this)
			{
				if (_ai == null && _aiBackup == null)
                {
					_ai = new L2ControllableMobAI(new ControllableAIAcessor());
					_aiBackup = (L2ControllableMobAI)_ai;
				}
				else
                {
					_ai = _aiBackup;
                }
				return _ai;
			}
		}
		return ai;
	}

	@Override
	public boolean isInvul()
    {
		return _isInvul;
	}

	public void setInvul(boolean isInvul)
    {
		_isInvul = isInvul;
	}

	@Override
	public void reduceCurrentHp(double i, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
    {
		if (isInvul() || isDead())
			return;

		if (awake)
			stopSleeping(null);
		
		if (isImmobileUntilAttacked())
			stopImmobileUntilAttacked(null);

		i = getCurrentHp() - i;

		if (i < 0)
			i = 0;

		setCurrentHp(i);

		if (getCurrentHp() < 0.5) // Die
		{
			// first die (and calculate rewards), if currentHp < 0,
			// then overhit may be calculated
			if (Config.DEBUG) _log.fine("char is dead.");

			stopMove(null);

			// Start the doDie process
			doDie(attacker);

			// now reset currentHp to zero
			setCurrentHp(0);
		}
	}

	@Override
	public boolean doDie(L2Character killer)
    {
		if (!super.doDie(killer))
			return false;

		removeAI();
		return true;
	}

	@Override
	public void deleteMe()
    {
		removeAI();
		super.deleteMe();
	}

	/**
	 * Definitively remove AI
	 */
	protected void removeAI()
    {
		synchronized (this)
        {
			if (_aiBackup != null)
            {
				_aiBackup.setIntention(CtrlIntention.AI_INTENTION_IDLE);
				_aiBackup = null;
				_ai = null;
			}
		}
	}
}