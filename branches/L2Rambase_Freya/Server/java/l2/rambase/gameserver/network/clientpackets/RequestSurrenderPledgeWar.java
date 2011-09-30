/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestSurrenderPledgeWar extends L2GameClientPacket
{
    private static final String _C__51_REQUESTSURRENDERPLEDGEWAR = "[C] 51 RequestSurrenderPledgeWar";
    private static Logger _log = Logger.getLogger(RequestSurrenderPledgeWar.class.getName());

    private String _pledgeName;
    private L2Clan _clan;
    private L2PcInstance _activeChar;

    @Override
	protected void readImpl()
    {
        _pledgeName  = readS();
    }

    @Override
	protected void runImpl()
    {
    	_activeChar = getClient().getActiveChar();
		if (_activeChar == null)
		    return;
        _clan = _activeChar.getClan();
		if (_clan == null)
		    return;
        L2Clan clan = ClanTable.getInstance().getClanByName(_pledgeName);

        if(clan == null)
        {
        	_activeChar.sendMessage("No such clan.");
        	_activeChar.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

        _log.info("RequestSurrenderPledgeWar by "+getClient().getActiveChar().getClan().getName()+" with "+_pledgeName);

        if(!_clan.isAtWarWith(clan.getClanId()))
        {
        	_activeChar.sendMessage("You aren't at war with this clan.");
        	_activeChar.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }


        SystemMessage msg = new SystemMessage(SystemMessageId.YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN);
        msg.addString(_pledgeName);
        _activeChar.sendPacket(msg);
        msg = null;
        _activeChar.deathPenalty(false, false, false);
        ClanTable.getInstance().deleteclanswars(_clan.getClanId(), clan.getClanId());
    }

    @Override
	public String getType()
    {
        return _C__51_REQUESTSURRENDERPLEDGEWAR;
    }
}