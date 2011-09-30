/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class Earthquake extends L2GameServerPacket
{
	private static final String _S__C4_EARTHQUAKE = "[S] d3 Earthquake";
	private int _x;
	private int _y;
	private int _z;
	private int _intensity;
    private int _duration;

	/**
	 * @param
	 */
	public Earthquake(int x, int y, int z, int intensity, int duration)
	{
		_x = x;
		_y = y;
		_z = z;
		_intensity = intensity;
		_duration = duration;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xd3);
		writeD(_x);
		writeD(_y);
		writeD(_z);
		writeD(_intensity);
		writeD(_duration);
		writeD(0x00);       // Unknown
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__C4_EARTHQUAKE;
	}
}