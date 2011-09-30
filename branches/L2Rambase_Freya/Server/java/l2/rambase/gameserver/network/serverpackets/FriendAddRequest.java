/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 *
 * sample
 * <p>
 * 7d
 * c1 b2 e0 4a
 * 00 00 00 00
 * <p>
 *
 * format
 * cdd
 */
public class FriendAddRequest extends L2GameServerPacket
{
	private static final String _S__7d_ASKJoinFriend_0X7d = "[S] 83 FriendAddRequest";

	private String _requestorName;

	/**
	 * @param int objectId of the target
	 * @param int
	 */
	public FriendAddRequest(String requestorName)
	{
		_requestorName = requestorName;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x83);
		writeS(_requestorName);
		writeD(0);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__7d_ASKJoinFriend_0X7d;
	}
}