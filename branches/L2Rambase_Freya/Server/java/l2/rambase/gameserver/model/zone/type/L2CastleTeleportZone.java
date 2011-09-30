/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.util.Rnd;

import javolution.util.FastList;


public class L2CastleTeleportZone extends L2ZoneType
{
	private int[] _spawnLoc;
	private int _castleId;
	
	public L2CastleTeleportZone(int id)
	{
		super(id);
		
		_spawnLoc = new int[5];
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("castleId"))
		{
			_castleId = Integer.parseInt(value);
		}
		else if (name.equals("spawnMinX"))
		{
			_spawnLoc[0] = Integer.parseInt(value);
		}
		else if (name.equals("spawnMaxX"))
		{
			_spawnLoc[1] = Integer.parseInt(value);
		}
		else if (name.equals("spawnMinY"))
		{
			_spawnLoc[2] = Integer.parseInt(value);
		}
		else if (name.equals("spawnMaxY"))
		{
			_spawnLoc[3] = Integer.parseInt(value);
		}
		else if (name.equals("spawnZ"))
		{
			_spawnLoc[4] = Integer.parseInt(value);
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, false);
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
	 * Returns all players within this zone
	 * @return
	 */
	public FastList<L2PcInstance> getAllPlayers()
	{
		FastList<L2PcInstance> players = new FastList<L2PcInstance>();
		
		for (L2Character temp : _characterList.values())
		{
			if (temp instanceof L2PcInstance)
				players.add((L2PcInstance) temp);
		}
		
		return players;
	}
	
	public void oustAllPlayers()
	{
		if (_characterList == null)
			return;
		if (_characterList.isEmpty())
			return;
		for (L2Character character : _characterList.values())
		{
			if (character == null)
				continue;
			if (character instanceof L2PcInstance)
			{
				L2PcInstance player = (L2PcInstance) character;
				if (player.isOnline() == 1)
					player.teleToLocation(Rnd.get(_spawnLoc[0], _spawnLoc[1]), Rnd.get(_spawnLoc[2], _spawnLoc[3]), _spawnLoc[4]);
			}
		}
	}
	
	public int getCastleId()
	{
		return _castleId;
	}
	
	/**
	 * Get the spawn locations
	 * @return
	 */
	public int[] getSpawn()
	{
		return _spawnLoc;
	}
}