/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class KeyPacket extends L2GameServerPacket
{
	private static final String _S__01_KEYPACKET = "[S] 2e KeyPacket";

	private byte[] _key;
	private int _id;

	public KeyPacket(byte[] key, int id)
	{
		_key = key;
		_id = id;
	}

	@Override
	public void writeImpl()
	{
		writeC(0x2e);
		writeC(_id); //0 - wrong protocol, 1 - protocol ok
		for (int i = 0; i < 8; i++)
		{
			writeC(_key[i]); // key
		}
		writeD(0x01);
		writeD(0x01); // server id
		writeC(0x01);
		writeD(0x00); // obfuscation key
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__01_KEYPACKET;
	}
}