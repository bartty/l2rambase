/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class CharCreateOk extends L2GameServerPacket
{
	private static final String _S__25_CHARCREATEOK = "[S] 0f CharCreateOk";

	@Override
	protected final void writeImpl()
	{
		writeC(0x0f);
		writeD(0x01);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__25_CHARCREATEOK;
	}
}