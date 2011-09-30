/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class GetOffVehicle extends L2GameServerPacket
{
	private int _charObjId, _boatObjId, _x, _y, _z;

	/**
	 * @param activeChar
	 * @param boat
	 * @param x
	 * @param y
	 * @param z
	 */
	public GetOffVehicle(int charObjId, int boatObjId, int x, int y, int z)
	{
		_charObjId = charObjId;
		_boatObjId = boatObjId;
		_x = x;
		_y = y;
		_z = z;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0x6f);
		writeD(_charObjId);
		writeD(_boatObjId);
		writeD(_x);
		writeD(_y);
		writeD(_z);
	}

	@Override
	public String getType()
	{
		return "[S] 6f GetOffVehicle";
	}
}
