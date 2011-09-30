/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: (chd) ddd
 * d: Time Left
 * d: Blue Points
 * d: Red Points
 */
public class ExCubeGameChangePoints extends L2GameServerPacket
{
	private static final String _S__FE_98_02_EXCUBEGAMECHANGEPOINTS = "[S] FE:98:02 ExCubeGameChangePoints";
	int _timeLeft;
	int _bluePoints;
	int _redPoints;
	
	/**
	 * Change Client Point Counter
	 * 
	 * @param timeLeft Time Left before Minigame's End
	 * @param bluePoints Current Blue Team Points
	 * @param redPoints Current Red Team Points
	 */
	public ExCubeGameChangePoints(int timeLeft, int bluePoints, int redPoints)
	{
		_timeLeft = timeLeft;
		_bluePoints = bluePoints;
		_redPoints = redPoints;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x98);
		writeD(0x02);
		
		writeD(_timeLeft);
		writeD(_bluePoints);
		writeD(_redPoints);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_98_02_EXCUBEGAMECHANGEPOINTS;
	}
}