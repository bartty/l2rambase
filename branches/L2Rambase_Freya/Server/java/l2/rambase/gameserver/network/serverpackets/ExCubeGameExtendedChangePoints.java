/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: (chd) dddddd
 * d: Time Left
 * d: Blue Points
 * d: Red Points
 * d: Player Team
 * d: Player Object ID
 * d: Player Points
 */
public class ExCubeGameExtendedChangePoints extends L2GameServerPacket
{
	private static final String _S__FE_98_00_EXCUBEGAMEEXTENDEDCHANGEPOINTS = "[S] FE:98:00 ExCubeGameExtendedChangePoints";
	int _timeLeft;
	int _bluePoints;
	int _redPoints;
	boolean _isRedTeam;
	L2PcInstance _player;
	int _playerPoints;
	
	/**
	 * Update a Secret Point Counter (used by client when receive ExCubeGameEnd)
	 * 
	 * @param timeLeft Time Left before Minigame's End
	 * @param bluePoints Current Blue Team Points
	 * @param redPoints Current Blue Team points
	 * @param isRedTeam Is Player from Red Team?
	 * @param player Player Instance
	 * @param playerPoints Current Player Points
	 */
	public ExCubeGameExtendedChangePoints(int timeLeft, int bluePoints, int redPoints,
			boolean isRedTeam, L2PcInstance player, int playerPoints)
	{
		_timeLeft = timeLeft;
		_bluePoints = bluePoints;
		_redPoints = redPoints;
		_isRedTeam = isRedTeam;
		_player = player;
		_playerPoints = playerPoints;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x98);
		writeD(0x00);
		
		writeD(_timeLeft);
		writeD(_bluePoints);
		writeD(_redPoints);
		
		writeD(_isRedTeam ? 0x01 : 0x00);
		writeD(_player.getObjectId());
		writeD(_playerPoints);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_98_00_EXCUBEGAMEEXTENDEDCHANGEPOINTS;
	}
}