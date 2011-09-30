/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.skills.conditions;

import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.skills.Env;

/** 
 * The Class ConditionPlayerTvTEvent. 
 */
public class ConditionPlayerEvent extends Condition
{
	private final boolean _val;

	/** 
	 * Instantiates a new condition player tv t event. 
	 * 
	 * @param val the val 
	 */
	public ConditionPlayerEvent(boolean val)
	{
		_val = val;
	}

	@Override
	public boolean testImpl(Env env)
	{
		final L2PcInstance player = env.player.getActingPlayer();
		if (player == null)
			return !_val;
		if (TvTEvent.isStarted())
		{
			return (TvTEvent.isPlayerParticipant(player.getObjectId()) == _val);
		}
		if (PvPEvent.isStarted())
		{
			return (PvPEvent.isPlayerParticipant(player.getObjectId()) == _val);
		}
		else
		{
			return !_val;
		}
	}
}
