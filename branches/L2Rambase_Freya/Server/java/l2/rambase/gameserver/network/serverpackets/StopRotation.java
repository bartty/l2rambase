/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class StopRotation extends L2GameServerPacket
{
	private static final String _S__78_STOPROTATION = "[S] 61 StopRotation";
	private int _charObjId, _degree, _speed;

	public StopRotation(int objectId, int degree, int speed)
	{
		_charObjId = objectId;
		_degree = degree;
		_speed = speed;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x61);
		writeD(_charObjId);
		writeD(_degree);
		writeD(_speed);
		writeC(0); // ?
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__78_STOPROTATION;
	}
}