/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: (chd)
 */
public class ExCubeGameRequestReady extends L2GameServerPacket
{
	private static final String _S__FE_97_04_EXCUBEGAMEREQUESTREADY = "[S] FE:97:04 ExCubeGameRequestReady";
	
	/**
	 * Show Confirm Dialog for 10 seconds
	 */
	public ExCubeGameRequestReady()
	{
		
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x97);
		writeD(0x04);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_97_04_EXCUBEGAMEREQUESTREADY;
	}
}