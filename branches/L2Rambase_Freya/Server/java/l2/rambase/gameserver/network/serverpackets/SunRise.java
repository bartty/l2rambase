/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class SunRise extends L2GameServerPacket
{
	private static final String _S__28_SUNRISE = "[S] 12 SunRise";

	@Override
	protected final void writeImpl()
	{
		writeC(0x12);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__28_SUNRISE;
	}
}