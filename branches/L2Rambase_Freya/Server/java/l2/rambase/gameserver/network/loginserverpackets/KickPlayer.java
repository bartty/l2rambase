/**
 * @author L2Rambase Project
 */

package l2.rambase.gameserver.network.loginserverpackets;
import l2.rambase.util.network.BaseRecievePacket; 

public class KickPlayer extends BaseRecievePacket
{

	private String _account;

	/**
	 * @param decrypt
	 */
	public KickPlayer(byte[] decrypt)
	{
		super(decrypt);
		_account = readS();
	}

	/**
	 * @return Returns the account.
	 */
	public String getAccount()
	{
		return _account;
	}
}