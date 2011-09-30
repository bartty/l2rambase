/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class VehicleCheckLocation extends L2GameServerPacket
{
	private L2Character _boat;
    
	public VehicleCheckLocation(L2Character boat)
	{
		_boat = boat;
	}

	@Override
	protected
	void writeImpl()
	{
		writeC(0x6d);
		writeD(_boat.getObjectId());
		writeD(_boat.getX());
		writeD(_boat.getY());
		writeD(_boat.getZ());
		writeD(_boat.getHeading());
	}

	@Override
	public String getType()
	{
		return "[S] 6D VehicleCheckLocation";
	}
}