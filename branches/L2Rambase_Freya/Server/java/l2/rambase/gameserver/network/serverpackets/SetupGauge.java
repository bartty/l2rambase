/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class SetupGauge extends L2GameServerPacket
{
	private static final String _S__85_SETUPGAUGE = "[S] 6b SetupGauge";
	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int CYAN = 2;

	private int _dat1;
	private int _time;
	private int _time2;

	public SetupGauge(int dat1, int time)
	{
		_dat1 = dat1;// color  0-blue   1-red  2-cyan  3-green
		_time = time;
		_time2 = time;
	}

	public SetupGauge (int color, int currentTime, int maxTime)
	{
		_dat1 = color;// color  0-blue   1-red  2-cyan  3-green
		_time = currentTime;
		_time2 = maxTime;
	}
	@Override
	protected final void writeImpl()
	{
		writeC(0x6b);
		writeD(_dat1);
		writeD(_time);
		writeD(_time2);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__85_SETUPGAUGE;
	}
}