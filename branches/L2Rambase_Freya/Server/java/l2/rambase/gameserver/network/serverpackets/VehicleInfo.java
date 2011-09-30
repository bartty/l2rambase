/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2BoatInstance;


public class VehicleInfo  extends L2GameServerPacket
{
	 private L2BoatInstance _boat;
	/**
	 * @param instance
	 */
	public VehicleInfo(L2BoatInstance boat)
	{
		_boat = boat;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0x60);
		writeD(_boat.getObjectId());
		writeD(_boat.getX());
		writeD(_boat.getY());
        writeD(_boat.getZ());
        writeD(_boat.getHeading());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] 59 VehicleInfo";
	}
}