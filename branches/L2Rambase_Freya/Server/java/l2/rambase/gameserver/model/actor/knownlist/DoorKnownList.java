/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2DefenderInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class DoorKnownList extends CharKnownList
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public DoorKnownList(L2DoorInstance activeChar)
    {
        super(activeChar);
    }

    // =========================================================
    // Method - Public

    // =========================================================
    // Method - Private

    // =========================================================
    // Property - Public
    @Override
	public final L2DoorInstance getActiveChar() { return (L2DoorInstance)super.getActiveChar(); }

    @Override
	public int getDistanceToForgetObject(L2Object object)
    {
        if (object instanceof L2DefenderInstance) return 800;
    	if (!(object instanceof L2PcInstance))
            return 0;

        return 4000;
    }

    @Override
	public int getDistanceToWatchObject(L2Object object)
    {
    	if (object instanceof L2DefenderInstance) return 600;
    	if (!(object instanceof L2PcInstance))
            return 0;
        return 2000;
    }
}