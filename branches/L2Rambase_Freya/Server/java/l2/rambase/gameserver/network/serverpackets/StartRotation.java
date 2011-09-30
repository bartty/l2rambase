/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class StartRotation extends L2GameServerPacket
{
	private static final String _S__77_BEGINROTATION = "[S] 7a BeginRotation";
	private int _charObjId, _degree, _side, _speed;

	public StartRotation(int objectId, int degree, int side, int speed)
	{
		_charObjId = objectId;
		_degree = degree;
		_side = side;
		_speed = speed;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x7a);
		writeD(_charObjId);
		writeD(_degree);
		writeD(_side);
		writeD(_speed);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__77_BEGINROTATION;
	}
}