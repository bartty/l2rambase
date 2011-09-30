/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExNoticePostArrived extends L2GameServerPacket
{
	private static final String _S__FE_A9_EXNOTICEPOSTARRIVED = "[S] A9 ExNoticePostArrived";
	private static final ExNoticePostArrived STATIC_PACKET_TRUE = new ExNoticePostArrived(true);
	private static final ExNoticePostArrived STATIC_PACKET_FALSE = new ExNoticePostArrived(false);
	
	public static final ExNoticePostArrived valueOf(boolean result)
	{
		return result ? STATIC_PACKET_TRUE : STATIC_PACKET_FALSE;
	}
	
	boolean _showAnim;

	public ExNoticePostArrived(boolean showAnimation)
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
		writeH(0xa9);
		writeD(_showAnim ? 0x01 : 0x00);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_A9_EXNOTICEPOSTARRIVED;
	}
}