/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import l2.rambase.gameserver.model.actor.L2Summon;

public class SummonStat extends PlayableStat
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public SummonStat(L2Summon activeChar)
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
	public L2Summon getActiveChar() { return (L2Summon)super.getActiveChar(); }
}