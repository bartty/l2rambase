/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;


public class L2LandingZone extends L2ZoneType
{
	public L2LandingZone(int id)
	{
		super(id);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (character instanceof L2PcInstance)
			character.setInsideZone(L2Character.ZONE_LANDING, true);
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (character instanceof L2PcInstance)
			character.setInsideZone(L2Character.ZONE_LANDING, false);
	}

	/**
     * @see l2.rambase.gameserver.model.zone.L2ZoneType#onDieInside(l2.rambase.gameserver.model.actor.L2Character)
     */
    @Override
    public void onDieInside(L2Character character)
    {  
    }

	/**
     * @see l2.rambase.gameserver.model.zone.L2ZoneType#onReviveInside(l2.rambase.gameserver.model.actor.L2Character)
     */
    @Override
    public void onReviveInside(L2Character character)
    {
    }
}