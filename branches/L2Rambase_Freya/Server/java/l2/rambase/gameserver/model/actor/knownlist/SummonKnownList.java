/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Summon;


public class SummonKnownList extends PlayableKnownList
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public SummonKnownList(L2Summon activeChar)
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
	public final L2Summon getActiveChar() { return (L2Summon)super.getActiveChar(); }

    @Override
	public int getDistanceToForgetObject(L2Object object)
    {
        if (object == getActiveChar().getOwner() || object == getActiveChar().getTarget()) return 6000;
        return 3000;
    }

    @Override
	public int getDistanceToWatchObject(L2Object object) { return 1500; }
}