/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class TradeDone extends L2GameServerPacket
{
	private static final String _S__32_SENDTRADEDONE = "[S] 1c SendTradeDone";
	private int _num;
	
	public TradeDone(int num)
	{
		_num = num;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x1c);
		writeD(_num);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__32_SENDTRADEDONE;
	}	
}