/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.actor.L2Playable;

public class PlayableKnownList extends CharKnownList
{
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public PlayableKnownList(L2Playable activeChar)
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
	public L2Playable getActiveChar() { return (L2Playable)super.getActiveChar(); }
}