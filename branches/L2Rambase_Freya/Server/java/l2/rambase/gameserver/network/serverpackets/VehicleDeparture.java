/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2BoatInstance;


public class VehicleDeparture extends L2GameServerPacket
{
	private L2BoatInstance _boat;

	/**
	 * @param _boat
	 * @param speed1
	 * @param speed2
	 * @param x
	 * @param y
	 * @param z
	 */
	public VehicleDeparture(L2BoatInstance boat)
	{
		_boat = boat;
	}

	@Override
	protected
	void writeImpl()
	{
		writeC(0x6c);
		writeD(_boat.getObjectId());
		writeD((int)_boat.getStat().getMoveSpeed());
		writeD(_boat.getStat().getRotationSpeed());
		writeD(_boat.getXdestination());
		writeD(_boat.getYdestination());
		writeD(_boat.getZdestination());
	}

	@Override
	public String getType()
	{
		return "[S] 5A VehicleDeparture";
	}
}