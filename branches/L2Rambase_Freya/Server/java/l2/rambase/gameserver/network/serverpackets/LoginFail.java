/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class LoginFail extends L2GameServerPacket
{
	private static final String _S__12_AUTHLOGINFAIL = "[S] 0a LoginFail";
	public static final int NO_TEXT = 0;
	public static final int SYSTEM_ERROR_LOGIN_LATER = 1;
	public static final int PASSWORD_DOES_NOT_MATCH_THIS_ACCOUNT = 2;
	public static final int PASSWORD_DOES_NOT_MATCH_THIS_ACCOUNT2 = 3;
	public static final int ACCESS_FAILED_TRY_LATER = 4;
	public static final int INCORRECT_ACCOUNT_INFO_CONTACT_CUSTOMER_SUPPORT = 5;
	public static final int ACCESS_FAILED_TRY_LATER2 = 6;
	public static final int ACOUNT_ALREADY_IN_USE = 7;
	public static final int ACCESS_FAILED_TRY_LATER3 = 8;
	public static final int ACCESS_FAILED_TRY_LATER4 = 9;
	public static final int ACCESS_FAILED_TRY_LATER5 = 10;

	private int _reason;

	/**
	 * @param _characters
	 */
	public LoginFail(int reason)
	{
		_reason = reason;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x0a);
		writeD(_reason);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__12_AUTHLOGINFAIL;
	}
}