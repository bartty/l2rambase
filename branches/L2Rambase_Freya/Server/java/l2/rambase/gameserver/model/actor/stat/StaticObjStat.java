/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import l2.rambase.gameserver.model.actor.instance.L2StaticObjectInstance;


public class StaticObjStat extends CharStat
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public StaticObjStat(L2StaticObjectInstance activeChar)
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
	public L2StaticObjectInstance getActiveChar() { return (L2StaticObjectInstance)super.getActiveChar(); }

    @Override
	public final byte getLevel() { return 1; }
}