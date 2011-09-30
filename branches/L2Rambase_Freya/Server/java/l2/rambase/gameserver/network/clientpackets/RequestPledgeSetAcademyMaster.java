/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 * Format: (ch) dSS
 *
 */
public final class RequestPledgeSetAcademyMaster extends L2GameClientPacket
{
    private static final String _C__D0_19_REQUESTSETPLEADGEACADEMYMASTER = "[C] D0:19 RequestPledgeSetAcademyMaster";
    private String _currPlayerName;
    private int _set; // 1 set, 0 delete
    private String _targetPlayerName;

    @Override
	protected void readImpl()
    {
        _set = readD();
        _currPlayerName = readS();
        _targetPlayerName = readS();
    }

    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
	protected void runImpl()
    {
    	L2PcInstance activeChar = getClient().getActiveChar();
        L2Clan clan = activeChar.getClan();
        if (clan == null) return;

        if((activeChar.getClanPrivileges() & L2Clan.CP_CL_APPRENTICE) != L2Clan.CP_CL_APPRENTICE)
        {
        	activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE));
        	return;
        }

        L2ClanMember currentMember = clan.getClanMember(_currPlayerName);
        L2ClanMember targetMember = clan.getClanMember(_targetPlayerName);
        if (currentMember == null || targetMember == null) return;

        L2ClanMember apprenticeMember, sponsorMember;
        if (currentMember.getPledgeType() == L2Clan.SUBUNIT_ACADEMY)
        {
        	apprenticeMember = currentMember;
        	sponsorMember = targetMember;
        }
        else
        {
        	apprenticeMember = targetMember;
        	sponsorMember = currentMember;
        }

        L2PcInstance apprentice = apprenticeMember.getPlayerInstance();
        L2PcInstance sponsor = sponsorMember.getPlayerInstance();

        SystemMessage sm = null;
        if(_set == 0)
        {
        	// do we get the current sponsor & apprentice from this packet or no?
        	if (apprentice != null) apprentice.setSponsor(0);
        	else // offline
        		apprenticeMember.initApprenticeAndSponsor(0, 0);

        	if (sponsor != null) sponsor.setApprentice(0);
        	else // offline
        		sponsorMember.initApprenticeAndSponsor(0, 0);

        	apprenticeMember.saveApprenticeAndSponsor(0, 0);
        	sponsorMember.saveApprenticeAndSponsor(0, 0);

        	sm = new SystemMessage(SystemMessageId.S2_CLAN_MEMBER_C1_APPRENTICE_HAS_BEEN_REMOVED);
        }
        else
        {
        	if (apprenticeMember.getSponsor() != 0 || sponsorMember.getApprentice() != 0
        			|| apprenticeMember.getApprentice() != 0 || sponsorMember.getSponsor() != 0)
        	{
        		activeChar.sendMessage("Remove previous connections first.");
        		return;
        	}
        	if (apprentice != null)
        		apprentice.setSponsor(sponsorMember.getObjectId());
        	else // offline
        		apprenticeMember.initApprenticeAndSponsor(0, sponsorMember.getObjectId());

        	if (sponsor != null)
        		sponsor.setApprentice(apprenticeMember.getObjectId());
        	else // offline
        		sponsorMember.initApprenticeAndSponsor(apprenticeMember.getObjectId(), 0);

        	// saving to database even if online, since both must match
        	apprenticeMember.saveApprenticeAndSponsor(0, sponsorMember.getObjectId());
        	sponsorMember.saveApprenticeAndSponsor(apprenticeMember.getObjectId(), 0);

        	sm = new SystemMessage(SystemMessageId.S2_HAS_BEEN_DESIGNATED_AS_APPRENTICE_OF_CLAN_MEMBER_S1);
        }
        sm.addString(sponsorMember.getName());
    	sm.addString(apprenticeMember.getName());
    	if (sponsor != activeChar && sponsor != apprentice) activeChar.sendPacket(sm);
    	if (sponsor != null) sponsor.sendPacket(sm);
    	if (apprentice != null) apprentice.sendPacket(sm);
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_19_REQUESTSETPLEADGEACADEMYMASTER;
    }


}