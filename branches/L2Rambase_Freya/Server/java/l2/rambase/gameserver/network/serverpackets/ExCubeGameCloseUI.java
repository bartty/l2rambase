/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExCubeGameCloseUI extends L2GameServerPacket
{
	private static final String _S__FE_97_FFFFFFFF_EXCUBEGAMECLOSEUI = "[S] FE:97:FFFFFFFF ExCubeGameCloseUI";
	int _seconds;
	
	/**
	 * Close Minigame Waiting List
	 */
	public ExCubeGameCloseUI()
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
		writeD(0xffffffff);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_97_FFFFFFFF_EXCUBEGAMECLOSEUI;
	}

}