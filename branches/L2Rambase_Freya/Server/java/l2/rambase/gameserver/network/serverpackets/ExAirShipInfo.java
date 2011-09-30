/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;


public class ExAirShipInfo extends L2GameServerPacket
{
	
	private static final String _S__FE_60_EXAIRSHIPINFO = "[S] FE:60 ExAirShipInfo";

	
	private final L2AirShipInstance _ship;
	private final int _x, _y, _z, _heading, _moveSpeed, _rotationSpeed, _captain, _helm, _objectId;
	
	public ExAirShipInfo(L2AirShipInstance ship)
	{
		_ship = ship;
		_x = ship.getX();
		_y = ship.getY();
		_z = ship.getZ();
		_heading = ship.getHeading();
		_objectId = ship.getObjectId();
		_moveSpeed = (int)ship.getStat().getMoveSpeed();
		_rotationSpeed = ship.getStat().getRotationSpeed();
		_captain = ship.getCaptainId();
		_helm = ship.getHelmObjectId();
	}

	@Override
	protected void writeImpl()
	{
		writeC(254);
		writeH(96);

		writeD(_objectId);
		writeD(_x);
		writeD(_y);
		writeD(_z);
		writeD(_heading);

		writeD(_captain);
		writeD(_moveSpeed);
		writeD(_rotationSpeed);
		writeD(_helm);
		if (_helm != 0)
		{
			writeD(366); // Controller X
			writeD(0x00); // Controller Y
			writeD(107); // Controller Z
			writeD(348); // Captain X
			writeD(0x00); // Captain Y
			writeD(105); // Captain Z
		}
		else
		{
			writeD(0x00);
			writeD(0x00);
			writeD(0x00);
			writeD(0x00);
			writeD(0x00);
			writeD(0x00);
		}

		writeD(_ship.getFuel());
		writeD(_ship.getMaxFuel());
	}

	@Override
    public String getType()
    {
	    return _S__FE_60_EXAIRSHIPINFO;
    }	
}