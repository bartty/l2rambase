/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class SendTradeRequest extends L2GameServerPacket
{
	private static final String _S__73_SENDTRADEREQUEST = "[S] 70 SendTradeRequest";
	private int _senderID;
	
	public SendTradeRequest(int senderID)
	{
		_senderID = senderID;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x70);
		writeD(_senderID);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__73_SENDTRADEREQUEST;
	}
}