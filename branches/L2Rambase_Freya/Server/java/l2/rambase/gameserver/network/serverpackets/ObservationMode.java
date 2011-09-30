/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ObservationMode extends L2GameServerPacket
{
	private static final String _S__DF_OBSERVMODE = "[S] eb ObservationMode";
	private int _x, _y, _z;

	/**
	 * @param _characters
	 */
	public ObservationMode(int x, int y, int z)
	{
		_x = x;
		_y = y;
		_z = z;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xeb);
		writeD(_x);
		writeD(_y);
		writeD(_z);
		writeC( 0x00);
		writeC( 0xc0);
		writeC( 0x00);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__DF_OBSERVMODE;
	}
}