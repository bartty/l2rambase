/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.L2Playable;


public interface IItemHandler
{
	public static Logger _log = Logger.getLogger(IItemHandler.class.getName());
	
	/**
	 * Launch task associated to the item.
	 * @param activeChar : L2PlayableInstance designating the player
	 * @param item : L2ItemInstance designating the item to use
	 */
	public void useItem(L2Playable playable, L2ItemInstance item);
}
