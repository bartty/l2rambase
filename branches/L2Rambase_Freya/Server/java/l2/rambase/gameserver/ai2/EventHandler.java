/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import java.util.logging.Logger;


public abstract class EventHandler
{
	protected static final Logger _log = Logger.getLogger(EventHandler.class.getName());
	
	abstract AiEventType getEvenType();
	
	/**
	 * @return the priority of this EventHandler INSIDE the EventHandlerSet
	 */
	abstract int getPriority();
	
	abstract void runImpl(AiParameters aiParams, AiEvent event);
	
	abstract AiPlugingParameters getPlugingParameters();
	
	@Override
	public String toString()
	{
		return "EventHandler: " + getEvenType().name() + " Priority:" + getPriority();
	}
}
