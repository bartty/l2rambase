/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExNoticePostSent extends L2GameServerPacket
{
	private static final String _S__FE_B4_EXNOTICEPOSTSENT = "[S] B4 ExNoticePostSent";
	private static final ExNoticePostSent STATIC_PACKET_TRUE = new ExNoticePostSent(true);
	private static final ExNoticePostSent STATIC_PACKET_FALSE = new ExNoticePostSent(false);
	
	public static final ExNoticePostSent valueOf(boolean result)
	{
		return result ? STATIC_PACKET_TRUE : STATIC_PACKET_FALSE;
	}
	
	boolean _showAnim;

	public ExNoticePostSent(boolean showAnimation)
	{
		_showAnim = showAnimation;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xb4);
		writeD(_showAnim ? 0x01 : 0x00);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_B4_EXNOTICEPOSTSENT;
	}
}