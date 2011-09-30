/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: (chd) ddd
 * d: Winner Team
 */
public class ExCubeGameEnd extends L2GameServerPacket
{
	private static final String _S__FE_98_01_EXCUBEGAMEEND = "[S] FE:98:01 ExCubeGameEnd";
	boolean _isRedTeamWin;
	
	/**
	 * Show Minigame Results
	 * 
	 * @param isRedTeamWin: Is Red Team Winner?
	 */
	public ExCubeGameEnd(boolean isRedTeamWin)
	{
		_isRedTeamWin = isRedTeamWin;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x98);
		writeD(0x01);
		
		writeD(_isRedTeamWin ? 0x01 : 0x00);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_98_01_EXCUBEGAMEEND;
	}
}