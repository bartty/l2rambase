/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.Location;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Point3D;


public class GeoData
{
	private static Logger _log = Logger.getLogger(GeoData.class.getName());
	
	protected GeoData()
	{
	}
	
	private GeoData(final boolean disabled)
	{
		if (disabled)
		{
			_log.info("Geodata Engine: Disabled.");
		}
	}
	
	public static GeoData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	// Public Methods
	/**
	 * @param x
	 * @param y
	 * @return Geo Block Type
	 */
	public short getType(int x, int y)
	{
		return 0;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return Nearles Z
	 */
	public short getHeight(int x, int y, int z)
	{
		return (short) z;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param zmin
	 * @param zmax
	 * @param spawnid
	 * @return
	 */
	public short getSpawnHeight(int x, int y, int zmin, int zmax, L2Spawn spawn)
	{
		return (short) zmin;
	}
	
	
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public String geoPosition(int x, int y)
	{
		return "";
	}
	
	/**
	 * @param cha
	 * @param target
	 * @return True if cha can see target (LOS)
	 */
	public boolean canSeeTarget(L2Object cha, L2Object target)
	{
		//If geo is off do simple check :]
		//Don't allow casting on players on different dungeon lvls etc
		return (Math.abs(target.getZ() - cha.getZ()) < 1000);
	}
	
	public boolean canSeeTarget(L2Object cha, Point3D worldPosition)
	{
		//If geo is off do simple check :]
		//Don't allow casting on players on different dungeon lvls etc
		return Math.abs(worldPosition.getZ() - cha.getZ()) < 1000;
	}
	
	public boolean canSeeTarget(int x, int y, int z, int tx, int ty, int tz)
	{
		// If geo is off do simple check :]
		// Don't allow casting on players on different dungeon lvls etc
		return (Math.abs(z - tz) < 1000);
	}
	
	/**
	 * @param cha
	 * @param target
	 * @return True if cha can see target (LOS) and send usful info to PC
	 */
	public boolean canSeeTargetDebug(L2PcInstance gm, L2Object target)
	{
		return true;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return Geo NSWE (0-15)
	 */
	public short getNSWE(int x, int y, int z)
	{
		return 15;
	}
	
	public short getHeightAndNSWE(int x, int y, int z) 
	{ 
		return (short)((z << 1) | 15); 
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param tx
	 * @param ty
	 * @param tz
	 * @param instanceId
	 * @return Last Location (x,y,z) where player can walk - just before wall
	 */
	public Location moveCheck(int x, int y, int z, int tx, int ty, int tz, int instanceId)
	{
		return new Location(tx, ty, tz);
	}
	
	public boolean canMoveFromToTarget(int x, int y, int z, int tx, int ty, int tz, int instanceId)
	{
		return true;
	}
	
	/**
	 * @param gm
	 * @param comment
	 */
	public void addGeoDataBug(L2PcInstance gm, String comment)
	{
		//Do Nothing
	}
	
	public static void unloadGeodata(byte rx, byte ry)
	{
		
	}
	
	public static boolean loadGeodataFile(byte rx, byte ry)
	{
		return false;
	}
	
	public boolean hasGeo(int x, int y)
	{
		return false;
	}		
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final GeoData _instance = Config.GEODATA > 0 ? GeoEngine.getInstance() : new GeoData(true);
	}
}
