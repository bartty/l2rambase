/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2CabaleBufferInstance;
import l2.rambase.gameserver.model.actor.instance.L2FestivalGuideInstance;
import l2.rambase.gameserver.model.actor.instance.L2NpcInstance;


public class NpcKnownList extends CharKnownList
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public NpcKnownList(L2Npc activeChar)
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
	public L2Npc getActiveChar() { return (L2Npc)super.getActiveChar(); }

    @Override
	public int getDistanceToForgetObject(L2Object object) { return 2 * getDistanceToWatchObject(object); }

    @Override
	public int getDistanceToWatchObject(L2Object object)
    {
        if (object instanceof L2FestivalGuideInstance)
            return 4000;

        if (object instanceof L2NpcInstance || !(object instanceof L2Character))
            return 0;

        if (object instanceof L2CabaleBufferInstance)
            return 900;

        if (object instanceof L2Playable)
            return 1500;

        return 500;
    }
}