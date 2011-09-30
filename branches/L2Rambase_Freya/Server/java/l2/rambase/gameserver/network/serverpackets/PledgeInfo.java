/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;


public class PledgeInfo extends L2GameServerPacket
{
	private static final String _S__9C_PLEDGEINFO = "[S] 89 PledgeInfo";
	private L2Clan _clan;

	public PledgeInfo(L2Clan clan)
	{
		_clan = clan;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x89);
		writeD(_clan.getClanId());
		writeS(_clan.getName());
		writeS(_clan.getAllyName());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__9C_PLEDGEINFO;
	}
}