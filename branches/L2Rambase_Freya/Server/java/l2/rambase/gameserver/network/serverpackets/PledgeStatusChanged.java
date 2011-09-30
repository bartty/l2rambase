/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;


public final class PledgeStatusChanged extends L2GameServerPacket
{
	private static final String _S__CD_PLEDGESTATUS_CHANGED = "[S] cd PledgeStatusChanged";
	private L2Clan _clan;

	public PledgeStatusChanged(L2Clan clan)
	{
		_clan = clan;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xcd);
		writeD(_clan.getLeaderId());
		writeD(_clan.getClanId());
        writeD(0);
        writeD(_clan.getLevel());
        writeD(0);
        writeD(0);
        writeD(0);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__CD_PLEDGESTATUS_CHANGED;
	}
}