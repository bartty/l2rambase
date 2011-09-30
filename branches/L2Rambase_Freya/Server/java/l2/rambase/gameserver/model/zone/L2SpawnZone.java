/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.Config;
import l2.rambase.gameserver.model.Location;
import l2.rambase.util.Rnd;


public abstract class L2SpawnZone extends L2ZoneType
{
	private List<Location> _spawnLocs = null;
	private List<Location> _chaoticSpawnLocs = null;

	public L2SpawnZone(int id)
	{
		super(id);
	}

	public final void addSpawn(int x, int y, int z)
	{
		if (_spawnLocs == null)
			_spawnLocs = new FastList<Location>();

		_spawnLocs.add(new Location(x, y, z));
	}

	public final void addChaoticSpawn(int x, int y, int z)
	{
		if (_chaoticSpawnLocs == null)
			_chaoticSpawnLocs = new FastList<Location>();

		_chaoticSpawnLocs.add(new Location(x, y, z));
	}

	public Location getSpawnLoc()
	{
		if (Config.RANDOM_RESPAWN_IN_TOWN_ENABLED) 
			return _spawnLocs.get(Rnd.get(_spawnLocs.size())); 
		else 
			return _spawnLocs.get(0);
	}

	public Location getChaoticSpawnLoc()
	{
		if (_chaoticSpawnLocs != null)
			return _chaoticSpawnLocs.get(Rnd.get(_chaoticSpawnLocs.size()));
		else
			return getSpawnLoc();
	}
}