/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class AskJoinParty extends L2GameServerPacket
{
	private static final String _S__4B_ASKJOINPARTY_0X4B = "[S] 39 AskJoinParty";
	
	private String _requestorName;
	private int _itemDistribution;

	/**
	 * @param int objectId of the target
	 * @param int
	 */
	public AskJoinParty(String requestorName, int itemDistribution)
	{
		_requestorName = requestorName;
		_itemDistribution = itemDistribution;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x39);
		writeS(_requestorName);
		writeD(_itemDistribution);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__4B_ASKJOINPARTY_0X4B;
	}
}