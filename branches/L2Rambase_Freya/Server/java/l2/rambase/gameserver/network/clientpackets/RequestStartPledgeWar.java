/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.Collection;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestStartPledgeWar extends L2GameClientPacket
{
    private static final String _C__4D_REQUESTSTARTPLEDGEWAR = "[C] 4D RequestStartPledgewar";

    private String _pledgeName;
    private L2Clan _clan;
    private L2PcInstance player;

    @Override
	protected void readImpl()
    {
        _pledgeName = readS();
    }

    @Override
	protected void runImpl()
    {
        player = getClient().getActiveChar();
        if (player == null) return;

        _clan = getClient().getActiveChar().getClan();
        if (_clan == null) return;

        if (_clan.getLevel() < 3 || _clan.getMembersCount() < Config.ALT_CLAN_MEMBERS_FOR_WAR)
        {
            SystemMessage sm = new SystemMessage(SystemMessageId.CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER);
            player.sendPacket(sm);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            sm = null;
            return;
        }
        else if ((player.getClanPrivileges() & L2Clan.CP_CL_PLEDGE_WAR) != L2Clan.CP_CL_PLEDGE_WAR )
        {
            player.sendPacket(new SystemMessage(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT));
            player.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

        L2Clan clan = ClanTable.getInstance().getClanByName(_pledgeName);
        if (clan == null)
        {
        	SystemMessage sm = new SystemMessage(SystemMessageId.CLAN_WAR_CANNOT_DECLARED_CLAN_NOT_EXIST);
            player.sendPacket(sm);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }
        else if (_clan.getAllyId() == clan.getAllyId() && _clan.getAllyId() != 0)
        {
            SystemMessage sm = new SystemMessage(SystemMessageId.CLAN_WAR_AGAINST_A_ALLIED_CLAN_NOT_WORK);
            player.sendPacket(sm);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            sm = null;
            return;
        }
        else if (clan.getLevel() < 3 || clan.getMembersCount() < Config.ALT_CLAN_MEMBERS_FOR_WAR)
        {
            SystemMessage sm = new SystemMessage(SystemMessageId.CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER);
            player.sendPacket(sm);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            sm = null;
            return;
        }
        else if (_clan.isAtWarWith(clan.getClanId()))
        {
            SystemMessage sm = new SystemMessage(SystemMessageId.ALREADY_AT_WAR_WITH_S1_WAIT_5_DAYS); //msg id 628
            sm.addString(clan.getName());
            player.sendPacket(sm);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            sm = null;
            return;
        }       

        ClanTable.getInstance().storeclanswars(player.getClanId(), clan.getClanId());
        Collection<L2PcInstance> pls = L2World.getInstance().getAllPlayers().values();
        for (L2PcInstance cha : pls)
        {
        	if (cha.getClan() == player.getClan() || cha.getClan() == clan)
        		cha.broadcastUserInfo();
        }
    }

    @Override
	public String getType()
    {
        return _C__4D_REQUESTSTARTPLEDGEWAR;
    }
}
