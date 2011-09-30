/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: (chd) d
 * d: Seconds Left
 */
public class ExCubeGameChangeTimeToStart extends L2GameServerPacket
{
	private static final String _S__FE_97_03_EXCUBEGAMECHANGETIMETOSTART = "[S] FE:97:03 ExCubeGameChangeTimeToStart";
	int _seconds;
	
	/**
	 * Update Minigame Waiting List Time to Start
	 * 
	 * @param seconds
	 */
	public ExCubeGameChangeTimeToStart(int seconds)
	{
		_seconds = seconds;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x97);
		writeD(0x03);
		
		writeD(_seconds);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_97_03_EXCUBEGAMECHANGETIMETOSTART;
	}
}