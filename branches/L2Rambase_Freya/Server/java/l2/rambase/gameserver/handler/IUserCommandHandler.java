/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public interface IUserCommandHandler
{
	public static Logger _log = Logger.getLogger(IUserCommandHandler.class.getName());
	/**
	 * this is the worker method that is called when someone uses an admin command.
	 * @param activeChar
	 * @param command
	 * @return command success
	 */
	public boolean useUserCommand(int id, L2PcInstance activeChar);
	
	/**
	 * this method is called at initialization to register all the item ids automatically
	 * @return all known itemIds
	 */
	public int[] getUserCommandList();
}
