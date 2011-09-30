/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.events;

import javolution.util.FastList;


public class Event
{
	private final FastList<EventHandler> _handlers = new FastList<EventHandler>();
	
	public void add(EventHandler handler)
	{
		if (!_handlers.contains(handler))
			_handlers.add(handler);
	}
	
	public void remove(EventHandler handler)
	{
		if (handler != null)
			_handlers.remove(handler);
	}
	
	public void fire(Object trigger, IEventParams params)
	{
		for (EventHandler handler : _handlers)
			handler.handler(trigger, params);
	}
	
	public void clear()
	{
		_handlers.clear();
	}
}
