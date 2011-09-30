/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Decoy;

public class DecoyKnownList extends CharKnownList
{
    public DecoyKnownList(L2Decoy activeChar)
    {
        super(activeChar);
    }
    
    @Override
    public final L2Decoy getActiveChar()
    {
        return (L2Decoy) super.getActiveChar();
    }
    
    @Override
    public int getDistanceToForgetObject(L2Object object)
    {
        if (object == getActiveChar().getOwner()
                || object == getActiveChar().getTarget())
            return 6000;
        return 3000;
    }
    
    @Override
    public int getDistanceToWatchObject(L2Object object)
    {
        return 1500;
    }
}