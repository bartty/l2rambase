/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.loginserverpackets;
import l2.rambase.util.network.BaseRecievePacket; 

public class FailedLoginCount extends BaseRecievePacket
{
	private int _count;
	private String _accountName;
	/**
	 * @param decrypt
	 */
	public FailedLoginCount(byte decrypt[])
    {
        super(decrypt);
        _count = 0;
        _count = readD();
        _accountName = readS();
    }

    public int getCount()
    {
        return _count;
    }

    public String getAccountName()
    {
        return _accountName;
    }
    
}