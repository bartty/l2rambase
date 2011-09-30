/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class CharDeleteFail extends L2GameServerPacket
{
	private static final String _S__34_CHARDELETEFAIL = "[S] 1e CharDeleteFail";

	public static final int REASON_DELETION_FAILED = 0x01;
	public static final int REASON_YOU_MAY_NOT_DELETE_CLAN_MEMBER = 0x02;
	public static final int REASON_CLAN_LEADERS_MAY_NOT_BE_DELETED = 0x03;

	private int _error;

	public CharDeleteFail(int errorCode)
	{
		_error = errorCode;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x1e);
		writeD(_error);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__34_CHARDELETEFAIL;
	}
}