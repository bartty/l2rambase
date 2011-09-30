/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class PartySmallWindowDeleteAll extends L2GameServerPacket
{
	private static final String _S__65_PARTYSMALLWINDOWDELETEALL = "[S] 50 PartySmallWindowDeleteAll";

	@Override
	protected final void writeImpl()
	{
		writeC(0x50);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__65_PARTYSMALLWINDOWDELETEALL;
	}
}