/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Trap;

public class TrapKnownList extends CharKnownList
{
    public TrapKnownList(L2Trap activeChar)
    {
        super(activeChar);
    }
    
    @Override
    public final L2Trap getActiveChar()
    {
        return (L2Trap) super.getActiveChar();
    }
    
    @Override
    public int getDistanceToForgetObject(L2Object object)
    {
        if (object == getActiveChar().getOwner() || object == getActiveChar().getTarget())
            return 6000;
        
        return 3000;
    }
    
    @Override
    public int getDistanceToWatchObject(L2Object object)
    {
        return 1500;
    }
}