/**
 * @author L2Rambase Project
 * FIXME: TvTEventTeam - need rewrite
 */
package l2.rambase.gameserver.event.tvt;

import java.util.Map;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import javolution.util.FastMap;


public class TvTEventTeam
{
	private String _name;
	private int[] _coordinates = new int[3];
	private short _points;
	private Map<Integer, L2PcInstance> _participatedPlayers = new FastMap<Integer, L2PcInstance>();
	
	public TvTEventTeam(String name, int[] coordinates)
	{
		_name = name;
		_coordinates = coordinates;
		_points = 0;
	}
	
	public boolean addPlayer(L2PcInstance playerInstance)
	{
		if (playerInstance == null)
		{
			return false;
		}
		
		synchronized (_participatedPlayers)
		{
			_participatedPlayers.put(playerInstance.getObjectId(), playerInstance);
		}
		
		return true;
	}
	
	/**
	 * Removes a player from the team<br><br>
	 *
	 * @param playerName as String<br>
	 */
	public void removePlayer(int playerObjectId)
	{
		synchronized (_participatedPlayers)
		{
			_participatedPlayers.remove(playerObjectId);
		}
	}
	
	public void increasePoints()
	{
		++_points;
	}
	
	public void cleanMe()
	{
		_participatedPlayers.clear();
		_participatedPlayers = new FastMap<Integer, L2PcInstance>();
		_points = 0;
	}
	
	public boolean containsPlayer(int playerObjectId)
	{
		boolean containsPlayer;
		
		synchronized (_participatedPlayers)
		{
			containsPlayer = _participatedPlayers.containsKey(playerObjectId);
		}
		
		return containsPlayer;
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
	
	public Map<Integer, L2PcInstance> getParticipatedPlayers()
	{
		Map<Integer, L2PcInstance> participatedPlayers = null;
		
		synchronized (_participatedPlayers)
		{
			participatedPlayers = _participatedPlayers;
		}
		
		return participatedPlayers;
	}
	
	public int getParticipatedPlayerCount()
	{
		int participatedPlayerCount;
		
		synchronized (_participatedPlayers)
		{
			participatedPlayerCount = _participatedPlayers.size();
		}
		
		return participatedPlayerCount;
	}
}