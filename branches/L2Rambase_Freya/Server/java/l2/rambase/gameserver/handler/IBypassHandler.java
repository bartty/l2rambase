/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public interface IBypassHandler
{
	public static Logger _log = Logger.getLogger(IBypassHandler.class.getName());

	/**
	 * this is the worker method that is called when someone uses an bypass command
	 * @param command
	 * @param activeChar
	 * @param target
	 * @return success
	 */
	public boolean useBypass(String command, L2PcInstance activeChar, L2Character target);

	/**
	 * this method is called at initialization to register all bypasses automatically
	 * @return all known bypasses
	 */
	public String[] getBypassList();
}