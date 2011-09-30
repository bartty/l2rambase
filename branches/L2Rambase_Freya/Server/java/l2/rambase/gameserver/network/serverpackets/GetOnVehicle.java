/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.util.Point3D;


public class GetOnVehicle extends L2GameServerPacket
{
	private int _charObjId;
	private int _boatObjId;
	private Point3D _pos;

	/**
	 * @param activeChar
	 * @param boat
	 * @param x
	 * @param y
	 * @param z
	 */
	public GetOnVehicle(int charObjId, int boatObjId, Point3D pos)
	{
		_charObjId = charObjId;
		_boatObjId = boatObjId;
		_pos = pos;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0x6e);
		writeD(_charObjId);
		writeD(_boatObjId);
        writeD(_pos.getX());
        writeD(_pos.getY());
        writeD(_pos.getZ());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] 6e GetOnVehicle";
	}
}