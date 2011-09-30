/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public interface IActionHandler
{
	public static Logger _log = Logger.getLogger(IActionHandler.class.getName());

	public boolean action(L2PcInstance activeChar, L2Character target, boolean interact);

	public InstanceType getInstanceType();
}