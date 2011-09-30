/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import java.util.List;


public interface ISpecificAiManager
{
	List<EventHandler> getEventHandlers();
	
	void load();
}