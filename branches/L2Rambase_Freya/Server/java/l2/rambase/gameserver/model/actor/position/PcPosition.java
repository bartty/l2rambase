/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.position;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PcPosition extends CharPosition
{
    // =========================================================
    // Constructor
    public PcPosition(L2PcInstance activeObject)
    {
        super(activeObject);
    }
    
    @Override
	public L2PcInstance getActiveObject()
    {
    	return ((L2PcInstance)super.getActiveObject());
    }
    
    @Override
    protected void badCoords()
    {
    	getActiveObject().teleToLocation(0,0,0, false);
        getActiveObject().sendMessage("Error with your coords, Please ask a GM for help!");
    }
}