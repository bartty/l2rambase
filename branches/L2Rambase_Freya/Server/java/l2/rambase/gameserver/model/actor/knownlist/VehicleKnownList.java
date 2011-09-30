/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class VehicleKnownList extends CharKnownList
{
	public VehicleKnownList(L2Character activeChar)
	{
		super(activeChar);
	}

	@Override
	public int getDistanceToForgetObject(L2Object object)
    {
        if (!(object instanceof L2PcInstance))
            return 0;

        return object.getKnownList().getDistanceToForgetObject(getActiveObject());
    }

    @Override
	public int getDistanceToWatchObject(L2Object object)
    {
        if (!(object instanceof L2PcInstance))
            return 0;

        return object.getKnownList().getDistanceToWatchObject(getActiveObject());
    }
}