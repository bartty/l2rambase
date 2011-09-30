/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class GMViewPledgeInfo extends L2GameServerPacket
{
	private static final String _S__A9_GMVIEWPLEDGEINFO = "[S] 96 GMViewPledgeInfo";
	private L2Clan _clan;
	private L2PcInstance _activeChar;

	public GMViewPledgeInfo(L2Clan clan, L2PcInstance activeChar)
	{
		_clan = clan;
		_activeChar = activeChar;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x96);
		writeS(_activeChar.getName());
		writeD(_clan.getClanId());
		writeD(0x00);
		writeS(_clan.getName());
		writeS(_clan.getLeaderName());
		writeD(_clan.getCrestId()); // -> no, it's no longer used (nuocnam) fix by game
		writeD(_clan.getLevel());
		writeD(_clan.getHasCastle());
		writeD(_clan.getHasHideout());
        writeD(0); // -> _clan.getHasFortress() need implementation
		writeD(_clan.getRank());
		writeD(_clan.getReputationScore());
		writeD(0);
		writeD(0);

		writeD(_clan.getAllyId()); //c2
		writeS(_clan.getAllyName()); //c2
		writeD(_clan.getAllyCrestId()); //c2
		writeD(_clan.isAtWar()? 1 : 0); //c3
		writeD(0); // T3 Unknown 
		writeD(_clan.getMembers().length);

		for (L2ClanMember member : _clan.getMembers())
		{
		    if (member != null)
		    {
            writeS(member.getName());
            writeD(member.getLevel());
            writeD(member.getClassId());
            writeD(member.getSex() ? 1 : 0);
            writeD(member.getRaceOrdinal());
            writeD(member.isOnline() ? member.getObjectId() : 0);
            writeD(member.getSponsor() != 0 ? 1 : 0);
		    }
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A9_GMVIEWPLEDGEINFO;
	}
}