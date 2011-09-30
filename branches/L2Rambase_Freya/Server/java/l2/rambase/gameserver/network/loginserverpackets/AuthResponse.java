/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.loginserverpackets;

import l2.rambase.util.network.BaseRecievePacket; 


public class AuthResponse extends BaseRecievePacket
{

	private int _serverId;
	private String _serverName;

	/**
	 * @param decrypt
	 */
	public AuthResponse(byte[] decrypt)
	{
		super(decrypt);
		_serverId = readC();
		_serverName = readS();
	}

	/**
	 * @return Returns the serverId.
	 */
	public int getServerId()
	{
		return _serverId;
	}

	/**
	 * @return Returns the serverName.
	 */
	public String getServerName()
	{
		return _serverName;
	}
}