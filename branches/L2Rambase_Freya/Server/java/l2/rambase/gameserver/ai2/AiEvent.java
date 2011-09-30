/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import l2.rambase.gameserver.model.actor.L2Character;

public class AiEvent
{
	private AiEventType _type;
	private L2Character _source;
	private L2Character _target;
	
	public AiEvent(AiEventType type, L2Character source, L2Character target)
	{
		_type = type;
		_source = source;
		_target = target;
	}
	
	public AiEventType getType()
	{
		return _type;
	}
	
	public L2Character getSource()
	{
		return _source;
	}
	
	public L2Character getTarget()
	{
		return _target;
	}
}