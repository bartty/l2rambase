/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPlayScene extends L2GameServerPacket
{
	private static final String _S__FE_5B_EXSHOWSLIDESHOWKAMAEL = "[S] FE:5c ExPlayScene";

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x5c);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_5B_EXSHOWSLIDESHOWKAMAEL;
	}
}