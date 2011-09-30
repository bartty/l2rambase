/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import java.util.List;

import l2.rambase.gameserver.TaskPriority;

import javolution.util.FastList;


public class EventHandlerSet implements Comparable<EventHandlerSet>
{
	private int _comparatorPrio;
	private long _insertionTime;
	private List<EventHandler> _handlers;
	private AiEventType _eventType;
	
	public EventHandlerSet(AiEventType event, List<EventHandler> handlers, TaskPriority prio)
	{
		_comparatorPrio = (prio.ordinal() + 1) * 3;
		_handlers = new FastList<EventHandler>();
		_eventType = event;
		for (EventHandler handler : handlers)
			addHandler(handler);
	}
	
	public EventHandlerSet(EventHandler handler, TaskPriority prio)
	{
		_comparatorPrio = (prio.ordinal() + 1) * 3;
		_handlers = new FastList<EventHandler>();
		_eventType = handler.getEvenType();
		addHandler(handler);
	}
	
	public void addHandler(EventHandler handler)
	{
		if (handler == null)
			return;
		int prio = handler.getPriority();
		int index = -1;
		for (EventHandler eventHandler : _handlers)
		{
			if (eventHandler.getPriority() <= prio)
			{
				index = eventHandler.getPriority();
				break;
			}
		}
		if (index != -1)
		{
			_handlers.add(index, handler);
		}
		else
		{
			_handlers.add(handler);
		}
	}
	
	public void setPrio(TaskPriority prio)
	{
		_comparatorPrio = (prio.ordinal() + 1) * 3;
	}
	
	public void stampInsertionTime()
	{
		_insertionTime = System.currentTimeMillis();
	}
	
	public int getComparatorPriority()
	{
		return _comparatorPrio;
	}
	
	public List<EventHandler> getHandlers()
	{
		return _handlers;
	}
	
	public AiEventType getEventType()
	{
		return _eventType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(T)
	 */
	public int compareTo(EventHandlerSet es)
	{
		return (int) ((System.currentTimeMillis() - _insertionTime) / 1000) + _comparatorPrio - es.getComparatorPriority();
	}
	
	@Override
	public String toString()
	{
		String str = "EventHandlerSet: size:" + _handlers.size() + " Priority:" + _comparatorPrio + (_insertionTime != 0 ? " TimePoints: " + (int) ((System.currentTimeMillis() - _insertionTime) / 1000) : "");
		for (EventHandler handler : _handlers)
		{
			str = str.concat(" - " + handler.toString());
		}
		return str;
	}
	
}
