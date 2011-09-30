/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;


public class PledgeShowInfoUpdate extends L2GameServerPacket
{
	private static final String _S__A1_PLEDGESHOWINFOUPDATE = "[S] 8e PledgeShowInfoUpdate";
	private L2Clan _clan;

	public PledgeShowInfoUpdate(L2Clan clan)
	{
		_clan = clan;
	}

	@Override
	protected final void writeImpl()
	{		
		writeC(0x8e);
		//sending empty data so client will ask all the info in response ;)
		writeD(_clan.getClanId());
		writeD(_clan.getCrestId());
		writeD(_clan.getLevel()); //clan level
		writeD(_clan.getHasCastle());
		writeD(_clan.getHasHideout());
		writeD(_clan.getHasFort());
		writeD(_clan.getRank());
		writeD(_clan.getReputationScore()); // clan reputation score
		writeD(0);
		writeD(0);
		writeD(_clan.getAllyId());
		writeS(_clan.getAllyName()); //c5
		writeD(_clan.getAllyCrestId()); //c5
		writeD(_clan.isAtWar() ? 1 : 0); //c5
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A1_PLEDGESHOWINFOUPDATE;
	}
}