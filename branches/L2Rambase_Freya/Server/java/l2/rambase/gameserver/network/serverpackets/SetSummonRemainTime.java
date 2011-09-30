/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class SetSummonRemainTime extends L2GameServerPacket
{
	private static final String _S__D1_SET_SUMMON_REMAIN_TIME = "[S] d1 SetSummonRemainTime";
	private int _maxTime;
	private int _remainingTime;

	public SetSummonRemainTime(int maxTime, int remainingTime)
	{
		_remainingTime = remainingTime;
		_maxTime = maxTime;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xd1);
		writeD(_maxTime);
		writeD(_remainingTime);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D1_SET_SUMMON_REMAIN_TIME;
	}
}