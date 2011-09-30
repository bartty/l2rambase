/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Point3D;


public class ValidateLocationInVehicle extends L2GameServerPacket
{
	private static final String _S__80_ValidateLocationInVehicle = "[S] 80 ValidateLocationInVehicle";

	private int _charObjId;
	private int _boatObjId;
	private int _heading;
	private Point3D _pos;

	/**
	 * 0x80 ValidateLocationInVehicle         hdd
	 * @param _characters
	 */
	public ValidateLocationInVehicle(L2PcInstance player)
	{
		_charObjId = player.getObjectId();
		_boatObjId = player.getBoat().getObjectId();
		_heading = player.getHeading();
		_pos = player.getInVehiclePosition();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x80);
		writeD(_charObjId);
		writeD(_boatObjId);
		writeD(_pos.getX());
		writeD(_pos.getY());
		writeD(_pos.getZ());
		writeD(_heading);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__80_ValidateLocationInVehicle;
	}
}