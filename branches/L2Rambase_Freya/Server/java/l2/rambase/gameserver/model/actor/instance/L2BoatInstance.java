/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.util.logging.Logger;

import l2.rambase.gameserver.ai.L2BoatAI;
import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.Location;
import l2.rambase.gameserver.model.actor.L2Vehicle;
import l2.rambase.gameserver.network.serverpackets.VehicleDeparture;
import l2.rambase.gameserver.network.serverpackets.VehicleInfo;
import l2.rambase.gameserver.network.serverpackets.VehicleStarted;
import l2.rambase.gameserver.templates.chars.L2CharTemplate;


public class L2BoatInstance extends L2Vehicle
{
	protected static final Logger _logBoat = Logger.getLogger(L2BoatInstance.class.getName());

	public L2BoatInstance(int objectId, L2CharTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2BoatInstance);
		setAI(new L2BoatAI(new AIAccessor()));
	}

	@Override
	public boolean isBoat()
	{
		return true;
	}

	@Override
	public boolean moveToNextRoutePoint()
	{
		final boolean result = super.moveToNextRoutePoint();
		if (result)
			broadcastPacket(new VehicleDeparture(this));

		return result;
	}

	@Override
	public void oustPlayer(L2PcInstance player)
	{
		super.oustPlayer(player);

		final Location loc = getOustLoc();
		if (player.isOnline() > 0)
			player.teleToLocation(loc.getX(), loc.getY(), loc.getZ());
		else
			player.setXYZInvisible(loc.getX(), loc.getY(), loc.getZ()); // disconnects handling
	}

	@Override
	public void stopMove(L2CharPosition pos, boolean updateKnownObjects)
	{
		super.stopMove(pos, updateKnownObjects);

		broadcastPacket(new VehicleStarted(this, 0));
		broadcastPacket(new VehicleInfo(this));
	}

    @Override
    public void sendInfo(L2PcInstance activeChar)
    {
    	activeChar.sendPacket(new VehicleInfo(this));
    }
}
