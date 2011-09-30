/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.PledgeReceiveMemberInfo;


/**
 * Format: (ch) dS
 *
 */
public final class RequestPledgeMemberInfo extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestPledgeMemberInfo.class.getName());
    private static final String _C__D0_1D_REQUESTPLEDGEMEMBERINFO = "[C] D0:1D RequestPledgeMemberInfo";
    @SuppressWarnings("unused")
    private int _unk1;
    private String _player;

    @Override
	protected void readImpl()
    {
        _unk1 = readD();
        _player = readS();
    }

    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
	protected void runImpl()
    {
        L2PcInstance activeChar = getClient().getActiveChar();
        if(activeChar == null)
        	return;

        L2Clan clan = activeChar.getClan();
        if(clan == null)
        	return;
        L2ClanMember member = clan.getClanMember(_player);
        if(member == null)
        	return;
        activeChar.sendPacket(new PledgeReceiveMemberInfo(member));
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_1D_REQUESTPLEDGEMEMBERINFO;
    }

}