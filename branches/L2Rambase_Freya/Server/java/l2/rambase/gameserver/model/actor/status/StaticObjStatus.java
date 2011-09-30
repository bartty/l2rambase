/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.status;

import l2.rambase.gameserver.model.actor.instance.L2StaticObjectInstance;


public class StaticObjStatus extends CharStatus
{
	public StaticObjStatus(L2StaticObjectInstance activeChar)
	{
		super(activeChar);
	}

	@Override
	public L2StaticObjectInstance getActiveChar()
	{
		return (L2StaticObjectInstance)super.getActiveChar();
	}
}