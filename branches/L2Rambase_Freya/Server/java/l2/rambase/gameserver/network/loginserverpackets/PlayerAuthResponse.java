/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.loginserverpackets;
import l2.rambase.util.network.BaseRecievePacket; 

public class PlayerAuthResponse extends BaseRecievePacket
{

	private String _account;
	private boolean _authed;

	/**
	 * @param decrypt
	 */
	public PlayerAuthResponse(byte[] decrypt)
	{
		super(decrypt);

		_account = readS();
		_authed = (readC() == 0 ? false : true);
	}

	/**
	 * @return Returns the account.
	 */
	public String getAccount()
	{
		return _account;
	}

	/**
	 * @return Returns the authed state.
	 */
	public boolean isAuthed()
	{
		return _authed;
	}
}