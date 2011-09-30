/**
 * @author L2Rambase Project
 * FIXME: PvPEventTeam - need rewrite
 */
package l2.rambase.gameserver.event.pvp;

import l2.rambase.Config;
import l2.rambase.util.Rnd;


public class PvPEventMember
{
	private String _name;
	private int[] _coordinates;
	private short _points;
	private int _playerId;
	
	public PvPEventMember(int playerId,String name, int[] coordinates)
	{
		_name = name;
		_playerId = playerId;
		if (Rnd.get(1) == 0)
		{
			_coordinates = Config.PVP_EVENT_1_COORDINATES;
		}
		else
		{
			_coordinates = Config.PVP_EVENT_2_COORDINATES;
		}
		_points = 0;
	}
	
	public void increasePoints()
	{
		++_points;
	}
	
	public int getPlayerId()
	{
		return _playerId;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public int[] getCoordinates()
	{
		return _coordinates;
	}
	
	public short getPoints()
	{
		return _points;
	}
}