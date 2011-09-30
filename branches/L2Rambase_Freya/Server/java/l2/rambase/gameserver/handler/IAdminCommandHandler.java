/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public interface IAdminCommandHandler
{
	/**
	 * this is the worker method that is called when someone uses an admin command.
	 * @param activeChar
	 * @param command
	 * @return command success
	 */
	public boolean useAdminCommand(String command, L2PcInstance activeChar);
	
	/**
	 * this method is called at initialization to register all the item ids automatically
	 * @return all known itemIds
	 */
	public String[] getAdminCommandList();
}
