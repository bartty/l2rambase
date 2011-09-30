/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.loginserverpackets;

import l2.rambase.util.network.BaseRecievePacket; 

public class InitLS extends BaseRecievePacket
{
	private int _rev;
	private byte[] _key;

	public int getRevision()
	{
		return _rev;
	}

	public byte[] getRSAKey()
	{
		return _key;
	}
	/**
	 * @param decrypt
	 */
	public InitLS(byte[] decrypt)
	{
		super(decrypt);
		_rev = readD();
		int size = readD();
		_key = readB(size);
	}

}