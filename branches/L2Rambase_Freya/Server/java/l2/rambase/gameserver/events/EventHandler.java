/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.events;


public abstract class EventHandler
{
	private Object _owner;
	
	public EventHandler(Object owner)
	{
		_owner = owner;
	}
	
	public final Object getOwner()
	{
		return _owner;
	}
	
	@Override
	public final boolean equals(Object object)
	{
		if (object instanceof EventHandler && _owner == ((EventHandler) object)._owner)
			return true;
		return false;
	}
	
	public abstract void handler(Object trigger, IEventParams params);
	
}
