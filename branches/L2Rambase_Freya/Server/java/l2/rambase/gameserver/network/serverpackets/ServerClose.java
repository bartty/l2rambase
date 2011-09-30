/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ServerClose extends L2GameServerPacket
{
	private static final String _S__26_SERVERCLOSE = "[S] 20 ServerClose";
	public static final ServerClose STATIC_PACKET = new ServerClose();

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0x20);
	}

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__26_SERVERCLOSE;
	}
}