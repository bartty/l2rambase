/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.idfactory.IdFactory;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.VehiclePathPoint;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2ControllableAirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExAirShipTeleportList;
import l2.rambase.gameserver.templates.StatsSet;
import l2.rambase.gameserver.templates.chars.L2CharTemplate;
import gnu.trove.TIntObjectHashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AirShipManager
{
	private static final Logger _log = Logger.getLogger(AirShipManager.class.getName());
	private static final String LOAD_DB = "SELECT * FROM airships";
	private static final String ADD_DB = "INSERT INTO airships (owner_id,fuel) VALUES (?,?)";
	private static final String UPDATE_DB = "UPDATE airships SET fuel=? WHERE owner_id=?";
	
	private L2CharTemplate _airShipTemplate = null;  
	private TIntObjectHashMap<StatsSet> _airShipsInfo = new TIntObjectHashMap<StatsSet>();
	private TIntObjectHashMap<L2AirShipInstance> _airShips = new TIntObjectHashMap<L2AirShipInstance>();
	private TIntObjectHashMap<AirShipTeleportList> _teleports = new TIntObjectHashMap<AirShipTeleportList>();
	
	public static final AirShipManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private AirShipManager(L2Object object)
	{
		StatsSet npcDat = new StatsSet();
		npcDat.set("npcId", 9);
		npcDat.set("level", 0);
		npcDat.set("jClass", "boat");
		
		npcDat.set("baseSTR", 0);
		npcDat.set("baseCON", 0);
		npcDat.set("baseDEX", 0);
		npcDat.set("baseINT", 0);
		npcDat.set("baseWIT", 0);
		npcDat.set("baseMEN", 0);
		
		npcDat.set("baseShldDef", 0);
		npcDat.set("baseShldRate", 0);
		npcDat.set("baseAccCombat", 38);
		npcDat.set("baseEvasRate", 38);
		npcDat.set("baseCritRate", 38);
		
		npcDat.set("collision_radius", 0);
		npcDat.set("collision_height", 0);
		npcDat.set("sex", "male");
		npcDat.set("type", "");
		npcDat.set("baseAtkRange", 0);
		npcDat.set("baseMpMax", 0);
		npcDat.set("baseCpMax", 0);
		npcDat.set("rewardExp", 0);
		npcDat.set("rewardSp", 0);
		npcDat.set("basePAtk", 0);
		npcDat.set("baseMAtk", 0);
		npcDat.set("basePAtkSpd", 0);
		npcDat.set("aggroRange", 0);
		npcDat.set("baseMAtkSpd", 0);
		npcDat.set("rhand", 0);
		npcDat.set("lhand", 0);
		npcDat.set("armor", 0);
		npcDat.set("baseWalkSpd", 0);
		npcDat.set("baseRunSpd", 0);
		npcDat.set("name", "AirShip");
		npcDat.set("baseHpMax", 50000);
		npcDat.set("baseHpReg", 0.003000000026077032D);
		npcDat.set("baseMpReg", 0.003000000026077032D);
		npcDat.set("basePDef", 100);
		npcDat.set("baseMDef", 100);
		this._airShipTemplate = new L2CharTemplate(npcDat);
		
		load();
	}
	
	public L2AirShipInstance getNewAirShip(int x, int y, int z, int heading)
	{
		L2AirShipInstance airShip = new L2AirShipInstance(IdFactory.getInstance().getNextId(), this._airShipTemplate);
		
		airShip.setHeading(heading);
		airShip.setXYZInvisible(x, y, z);
		airShip.spawnMe();
		airShip.getStat().setMoveSpeed(280.0F);
		airShip.getStat().setRotationSpeed(2000);
		return airShip;
	}
	
	public L2AirShipInstance getNewAirShip(int x, int y, int z, int heading, int ownerId)
	{
		StatsSet info = (StatsSet)this._airShipsInfo.get(ownerId);
		if (info == null)
			return null;
		L2AirShipInstance airShip = null;
		if (this._airShips.containsKey(ownerId))
		{
			airShip = (L2AirShipInstance)this._airShips.get(ownerId);
			airShip.refreshID();
		}
		else
		{
			airShip = new L2ControllableAirShipInstance(IdFactory.getInstance().getNextId(), this._airShipTemplate, ownerId);
			this._airShips.put(ownerId, airShip);

			airShip.setMaxFuel(600);
			airShip.setFuel(info.getInteger("fuel"));
			airShip.getStat().setMoveSpeed(280.0F);
			airShip.getStat().setRotationSpeed(2000);
		}

		airShip.setHeading(heading);
		airShip.setXYZInvisible(x, y, z);
		airShip.spawnMe();
		return airShip;
	}

	public void removeAirShip(L2AirShipInstance ship)
	{
		if (ship.getOwnerId() == 0)
			return;
		storeInDb(ship.getOwnerId());
		StatsSet info = (StatsSet)this._airShipsInfo.get(ship.getOwnerId());
		if (info != null)
			info.set("fuel", ship.getFuel());
	}
	
	public boolean hasAirShipLicense(int ownerId)
	{
		return this._airShipsInfo.contains(ownerId);
	}
	
	public void registerLicense(int ownerId)
	{
		if (this._airShipsInfo.contains(ownerId))
			return;
		StatsSet info = new StatsSet();
		info.set("fuel", 600);
		
		this._airShipsInfo.put(ownerId, info);
		
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement = con.prepareStatement(ADD_DB);
			statement.setInt(1, ownerId);
			statement.setInt(2, info.getInteger("fuel"));
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Could not add new airship license: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Error while initializing: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	public boolean hasAirShip(int ownerId)
	{
		L2AirShipInstance ship = (L2AirShipInstance)this._airShips.get(ownerId);
		
		return (ship != null) && (((ship.isVisible()) || (ship.isTeleporting())));
	}
	
	public void registerAirShipTeleportList(int dockId, int locationId, VehiclePathPoint[][] tp, int[] fuelConsumption)
	{
		if (tp.length != fuelConsumption.length) 
		{
			return;
		}
		this._teleports.put(dockId, new AirShipTeleportList(locationId, fuelConsumption, tp));
		return;
	}
	
	public void sendAirShipTeleportList(L2PcInstance player)
	{
		if ((player == null) || (!player.isInAirShip())) 
		{
			return;
		}
		L2AirShipInstance ship = player.getAirShip();
		if ((!ship.isCaptain(player)) || (!ship.isInDock()) || (ship.isMoving())) 
		{
			return;
		}
		int dockId = ship.getDockId();
		if (!this._teleports.contains(dockId)) 
		{
			return;
		}
		AirShipTeleportList all = (AirShipTeleportList)this._teleports.get(dockId);
		player.sendPacket(new ExAirShipTeleportList(all.location, all.routes, all.fuel));
	}
	
	public VehiclePathPoint[] getTeleportDestination(int dockId, int index)
	{
		AirShipTeleportList all = (AirShipTeleportList)this._teleports.get(dockId);
		if (all == null) 
		{
			return null;
		}
		if ((index < -1) || (index >= all.routes.length)) 
		{
			return null;
		}
		return all.routes[(index + 1)];
	}
	
	public int getFuelConsumption(int dockId, int index)
	{
		AirShipTeleportList all = (AirShipTeleportList)this._teleports.get(dockId);
		if (all == null) 
		{
			return 0;
		}
		if ((index < -1) || (index >= all.fuel.length)) 
		{
			return 0;
		}
		return all.fuel[(index + 1)];
	}
	
	private void load()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement = con.prepareStatement(LOAD_DB);
			ResultSet rset = statement.executeQuery();
			
			while (rset.next())
			{
				StatsSet info = new StatsSet();
				info.set("fuel", rset.getInt("fuel"));
				this._airShipsInfo.put(rset.getInt("owner_id"), info);
				info = null;
			}
			
			rset.close();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Could not load airships table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Error while initializing: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		_log.info(super.getClass().getSimpleName() + ": Loaded " + this._airShipsInfo.size() + " private airships");
	}
	
	private void storeInDb(int ownerId)
	{
		StatsSet info = (StatsSet)this._airShipsInfo.get(ownerId);
		if (info == null) 
		{
			return;
		}
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement = con.prepareStatement(UPDATE_DB);
			statement.setInt(1, info.getInteger("fuel"));
			statement.setInt(2, ownerId);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Could not update airships table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, super.getClass().getSimpleName() + ": Error while save: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	private static class SingletonHolder
	{
		protected static final AirShipManager _instance = new AirShipManager(null);
	}
	
	private class AirShipTeleportList
	{
		public int location;
		public int[] fuel;
		public VehiclePathPoint[][] routes;
		
		public AirShipTeleportList(int loc, int[] f, VehiclePathPoint[][] r)
		{
			this.location = loc;
			this.fuel = f;
			this.routes = r;
		}
	}
}