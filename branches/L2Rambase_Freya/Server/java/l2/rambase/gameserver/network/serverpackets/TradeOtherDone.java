/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class TradeOtherDone extends L2GameServerPacket
{
	private static final String _S__82_SENDTRADEOTHERDONE = "[S] 82 SendTradeOtherDone";
	
	public TradeOtherDone()
	{
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x82);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__82_SENDTRADEOTHERDONE;
	}
}