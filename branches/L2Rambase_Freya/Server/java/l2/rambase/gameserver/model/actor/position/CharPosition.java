/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.position;

import l2.rambase.gameserver.model.L2WorldRegion;
import l2.rambase.gameserver.model.actor.L2Character;


public class CharPosition extends ObjectPosition
{
    // =========================================================
    // Constructor
    public CharPosition(L2Character activeObject)
    {
        super(activeObject);
    }
    
    @Override
    protected void badCoords()
    {
    	getActiveObject().decayMe();
    }
    
    @Override
    public final void setWorldRegion(L2WorldRegion value)
    {
        if(getWorldRegion() != null && getActiveObject() instanceof L2Character) // confirm revalidation of old region's zones
        {
            if (value != null)
            	getWorldRegion().revalidateZones((L2Character)getActiveObject());    // at world region change
            else
            	getWorldRegion().removeFromZones((L2Character)getActiveObject());    // at world region change
        }
  
        super.setWorldRegion(value);
    }
}