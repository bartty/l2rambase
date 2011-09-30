/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public interface IVoicedCommandHandler
{
	public static Logger _log = Logger.getLogger(IVoicedCommandHandler.class.getName());
	/**
	 * this is the worker method that is called when someone uses an admin command.
	 * @param activeChar
	 * @param command
	 * @return command success
	 */
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params);
	
	/**
	 * this method is called at initialization to register all the item ids automatically
	 * @return all known itemIds
	 */
	public String[] getVoicedCommandList();
}
