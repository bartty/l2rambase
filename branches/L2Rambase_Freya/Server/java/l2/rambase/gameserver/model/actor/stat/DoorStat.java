/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;


public class DoorStat extends CharStat
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public DoorStat(L2DoorInstance activeChar)
    {
        super(activeChar);

        setLevel((byte)1);
    }

    // =========================================================
    // Method - Public

    // =========================================================
    // Method - Private

    // =========================================================
    // Property - Public
    @Override
	public L2DoorInstance getActiveChar() { return (L2DoorInstance)super.getActiveChar(); }

    @Override
	public final byte getLevel() 
    { 
    	return 1;
    }
}