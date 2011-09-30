/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.loginserverpackets;
import l2.rambase.util.network.BaseRecievePacket; 

public class LoginServerFail extends BaseRecievePacket
{

	private static final String[] REASONS = {"None" ,
	                                        "Reason: ip banned",
	                                        "Reason: ip reserved",
	                                        "Reason: wrong hexid",
	                                        "Reason: id reserved",
	                                        "Reason: no free ID",
	                                        "Not authed",
	                                        "Reason: already logged in"};
	private int _reason;
	/**
	 * @param decrypt
	 */
	public LoginServerFail(byte[] decrypt)
	{
		super(decrypt);
		_reason = readC();
	}

	public String getReasonString()
	{
		return REASONS[_reason];
	}

	public int getReason()
	{
		return _reason;
	}

}