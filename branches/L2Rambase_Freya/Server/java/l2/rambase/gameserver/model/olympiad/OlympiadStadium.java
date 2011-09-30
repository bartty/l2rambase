/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.olympiad;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.L2FastList;


class OlympiadStadium
{
	private boolean _freeToUse = true;
	private static DoorTable _doorTable;
	private int[] _coords = new int[3];
	private int[] _doors = new int[2];
	private L2FastList<L2PcInstance> _spectators;
	
	public boolean isFreeToUse()
	{
		return _freeToUse;
	}
	
	public void setStadiaBusy()
	{
		_freeToUse = false;
	}
	
	public void setStadiaFree()
	{
		_freeToUse = true;
	}
	
	public int[] getCoordinates()
	{
		return _coords;
	}
	
	public int[] getDoorID()
	{
		return _doors;
	}
	
	public OlympiadStadium(int x, int y, int z, int d1, int d2)
	{
		_coords[0] = x;
		_coords[1] = y;
		_coords[2] = z;
		_doors[0] = d1;
		_doors[1] = d2;
		_spectators = new L2FastList<L2PcInstance>();
	}
	
	public void openDoors()
	{
		_doorTable = DoorTable.getInstance();
		try
		{
			_doorTable.getDoor(getDoorID()[0]).openMe();
			_doorTable.getDoor(getDoorID()[1]).openMe();
		}
		catch (Exception e)
		{
		}
	}
	
	public void closeDoors()
	{
		_doorTable = DoorTable.getInstance();
		try
		{
			_doorTable.getDoor(getDoorID()[0]).closeMe();
			_doorTable.getDoor(getDoorID()[1]).closeMe();
		}
		catch (Exception e)
		{
		}
	}

	protected void addSpectator(int id, L2PcInstance spec, boolean storeCoords)
	{
		spec.enterOlympiadObserverMode(getCoordinates()[0] + 1200, getCoordinates()[1], getCoordinates()[2], id, storeCoords);
		_spectators.add(spec);
	}

	protected L2FastList<L2PcInstance> getSpectators()
	{
		return _spectators;
	}
	
	protected void removeSpectator(L2PcInstance spec)
	{
		if (_spectators != null && _spectators.contains(spec))
			_spectators.remove(spec);
	}
}