/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class SunSet extends L2GameServerPacket
{
	private static final String _S__29_SUNSET = "[S] 13 SunSet";

	@Override
	protected final void writeImpl()
	{
		writeC(0x13);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__29_SUNSET;
	}
}