/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.instance.L2DefenderInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2StaticObjectInstance;


public class StaticObjectKnownList extends CharKnownList
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public StaticObjectKnownList(L2StaticObjectInstance activeChar)
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
	public final L2StaticObjectInstance getActiveChar() { return (L2StaticObjectInstance)super.getActiveChar(); }

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