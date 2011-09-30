/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2SpawnZone;


public class L2TownZone extends L2SpawnZone
{
	private int _townId;
	private int _taxById;
	private boolean _isPeaceZone;
	
	public L2TownZone(int id)
	{
		super(id);
		
		_taxById = 0;
		
		// Default peace zone
		_isPeaceZone = true;
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("townId"))
		{
			_townId = Integer.parseInt(value);
		}
		else if (name.equals("taxById"))
		{
			_taxById = Integer.parseInt(value);
		}
		else if (name.equals("isPeaceZone"))
		{
			_isPeaceZone = Boolean.parseBoolean(value);
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			// PVP possible during siege, now for siege participants only
			// Could also check if this town is in siege, or if any siege is going on
			if (((L2PcInstance) character).getSiegeState() != 0 && Config.ZONE_TOWN == 1)
				return;
			//((L2PcInstance)character).sendMessage("You entered "+_townName);
		}
		
		if (_isPeaceZone && Config.ZONE_TOWN != 2)
			character.setInsideZone(L2Character.ZONE_PEACE, true);
		
		character.setInsideZone(L2Character.ZONE_TOWN, true);
		
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (_isPeaceZone)
		{
			character.setInsideZone(L2Character.ZONE_PEACE, false);
		}
		
		character.setInsideZone(L2Character.ZONE_TOWN, false);
		
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
	}
	
	@Override
	public void onReviveInside(L2Character character)
	{
	}
	
	/**
	 * Returns this zones town id (if any)
	 * @return
	 */
	public int getTownId()
	{
		return _townId;
	}
	
	/**
	 * Returns this town zones castle id
	 * @return
	 */
	public final int getTaxById()
	{
		return _taxById;
	}
	
	public final boolean isPeaceZone()
	{
		return _isPeaceZone;
	}
	
	/**
	 * Return town zone name<BR>
	 * Call {@link MapRegionTable#getClosestTownName(int)}
	 * @return String
	 */
	public String getName()
	{
		return MapRegionTable.getInstance().getClosestTownName(_townId);
	}
}
