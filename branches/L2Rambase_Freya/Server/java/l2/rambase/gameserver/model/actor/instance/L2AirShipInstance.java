/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.ai.L2AirShipAI;
import l2.rambase.gameserver.instancemanager.AirShipManager;
import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.Location;
import l2.rambase.gameserver.model.actor.L2Vehicle;
import l2.rambase.gameserver.network.serverpackets.ExAirShipInfo;
import l2.rambase.gameserver.network.serverpackets.ExGetOffAirShip;
import l2.rambase.gameserver.network.serverpackets.ExGetOnAirShip;
import l2.rambase.gameserver.network.serverpackets.ExMoveToLocationAirShip;
import l2.rambase.gameserver.network.serverpackets.ExStopMoveAirShip;
import l2.rambase.gameserver.templates.chars.L2CharTemplate;
import l2.rambase.util.Point3D;

public class L2AirShipInstance extends L2Vehicle
{
	public L2AirShipInstance(int objectId, L2CharTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2AirShipInstance);
		setAI(new L2AirShipAI(new L2Vehicle.AIAccessor()));
	}
	
	public boolean isAirShip()
	{
		return true;
	}
	
	public boolean isOwner(L2PcInstance player)
	{
		return false;
	}
	
	public int getOwnerId()
	{
		return 0;
	}
	
	public boolean isCaptain(L2PcInstance player)
	{
		return false;
	}
	
	public int getCaptainId()
	{
		return 0;
	}
	
	public int getHelmObjectId()
	{
		return 0;
	}
	
	public int getHelmItemId()
	{
		return 0;
	}
	
	public boolean setCaptain(L2PcInstance player)
	{
		return false;
	}
	
	public int getFuel()
	{
		return 0;
	}
	
	public void setFuel(int f)
	{
	}
	
	public int getMaxFuel()
	{
		return 0;
	}
	
	public void setMaxFuel(int mf)
	{
	}
	
	public boolean moveToNextRoutePoint()
	{
		boolean result = super.moveToNextRoutePoint();
		if (result) {
			broadcastPacket(new ExMoveToLocationAirShip(this));
		}
		return result;
	}
	
	public boolean addPassenger(L2PcInstance player)
	{
		if (!super.addPassenger(player)) 
		{
			return false;
		}
		player.setVehicle(this);
		player.setInVehiclePosition(new Point3D(0, 0, 0));
		player.broadcastPacket(new ExGetOnAirShip(player, this));
		player.getKnownList().removeAllKnownObjects();
		player.setXYZ(getX(), getY(), getZ());
		player.revalidateZone(true);
		return true;
	}
	
	public void oustPlayer(L2PcInstance player)
	{
		super.oustPlayer(player);
		Location loc = getOustLoc();
		if (player.isOnline() > 0)
		{
			player.broadcastPacket(new ExGetOffAirShip(player, this, loc.getX(), loc.getY(), loc.getZ()));
			player.getKnownList().removeAllKnownObjects();
			player.setXYZ(loc.getX(), loc.getY(), loc.getZ());
			player.revalidateZone(true);
		}
		else 
		{
			player.setXYZInvisible(loc.getX(), loc.getY(), loc.getZ());
		}
	}
	
	public void deleteMe()
	{
		super.deleteMe();
		AirShipManager.getInstance().removeAirShip(this);
	}
	
	public void stopMove(L2CharPosition pos, boolean updateKnownObjects)
	{
		super.stopMove(pos, updateKnownObjects);
		broadcastPacket(new ExStopMoveAirShip(this));
	}
	
	public void updateAbnormalEffect()
	{
		broadcastPacket(new ExAirShipInfo(this));
	}
	
	public void sendInfo(L2PcInstance activeChar)
	{
		activeChar.sendPacket(new ExAirShipInfo(this));
	}
}