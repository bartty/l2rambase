/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.L2Clan.SubPledge;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PledgeShowMemberListAll extends L2GameServerPacket
{
	private static final String _S__68_PLEDGESHOWMEMBERLISTALL = "[S] 5a PledgeShowMemberListAll";
	private L2Clan _clan;
	private L2PcInstance _activeChar;
	private L2ClanMember[] _members;
	private int _pledgeType;	

	public PledgeShowMemberListAll(L2Clan clan, L2PcInstance activeChar)
	{
		_clan = clan;
		_activeChar = activeChar;
		_members = _clan.getMembers();
	}

	@Override
	protected final void writeImpl()
	{

		_pledgeType = 0;
		writePledge(0);

		for (SubPledge subPledge: _clan.getAllSubPledges())
		{
			_activeChar.sendPacket(new PledgeReceiveSubPledgeCreated(subPledge, _clan));
		}

		for (L2ClanMember m : _members)
		{
            if (m.getPledgeType() == 0) continue;
			_activeChar.sendPacket(new PledgeShowMemberListAdd(m));
		}

		// unless this is sent sometimes, the client doesn't recognise the player as the leader
		_activeChar.sendPacket(new UserInfo(_activeChar));
		_activeChar.sendPacket(new ExBrExtraUserInfo(_activeChar));

	}

	void writePledge(int mainOrSubpledge)
	{
		writeC(0x5a);

		writeD(mainOrSubpledge);
		writeD(_clan.getClanId());
		writeD(_pledgeType);
		writeS(_clan.getName());
		writeS(_clan.getLeaderName());

		writeD(_clan.getCrestId()); // crest id .. is used again
		writeD(_clan.getLevel());
		writeD(_clan.getHasCastle());
		writeD(_clan.getHasHideout());
		writeD(_clan.getHasFort());
		writeD(_clan.getRank());
		writeD(_clan.getReputationScore());
		writeD(0); //0
		writeD(0); //0
		writeD(_clan.getAllyId());
		writeS(_clan.getAllyName());
		writeD(_clan.getAllyCrestId());
        writeD(_clan.isAtWar()? 1 : 0);// new c3
        writeD(0); // Territory castle ID
		writeD(_clan.getSubPledgeMembersCount(_pledgeType));

		for (L2ClanMember m : _members)
		{
    		if(m.getPledgeType() != _pledgeType) continue;
			writeS(m.getName());
			writeD(m.getLevel());
			writeD(m.getClassId());
            L2PcInstance player;
            if ((player = m.getPlayerInstance()) != null)
            {
                writeD(player.getAppearance().getSex() ? 1 : 0); // no visible effect
                writeD(player.getRace().ordinal());//writeD(1);
            }
            else
            {
                writeD(1); // no visible effect
                writeD(1);
            }
			writeD(m.isOnline() ? m.getObjectId() : 0);  // objectId=online 0=offline
			writeD(m.getSponsor() != 0 ? 1 : 0);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__68_PLEDGESHOWMEMBERLISTALL;
	}
}