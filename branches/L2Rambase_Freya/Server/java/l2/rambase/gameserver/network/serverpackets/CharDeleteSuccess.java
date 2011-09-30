/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class CharDeleteSuccess extends L2GameServerPacket
{
	private static final String _S__33_CHARDELETEOK = "[S] 1d CharDeleteOk";

	@Override
	protected final void writeImpl()
	{
		writeC(0x1d);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__33_CHARDELETEOK;
	}
}