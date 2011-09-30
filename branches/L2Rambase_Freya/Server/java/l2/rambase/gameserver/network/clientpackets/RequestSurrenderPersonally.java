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


public final class RequestSurrenderPersonally extends L2GameClientPacket
{
    private static final String _C__69_REQUESTSURRENDERPERSONALLY = "[C] 69 RequestSurrenderPersonally";
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
        _log.info("RequestSurrenderPersonally by "+getClient().getActiveChar().getName()+" with "+_pledgeName);
        _clan = getClient().getActiveChar().getClan();
        L2Clan clan = ClanTable.getInstance().getClanByName(_pledgeName);

        if(_clan == null)
            return;

        if(clan == null)
        {
        	_activeChar.sendMessage("No such clan.");
        	_activeChar.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

        if(!_clan.isAtWarWith(clan.getClanId()) || _activeChar.getWantsPeace() == 1)
        {
        	_activeChar.sendMessage("You aren't at war with this clan.");
        	_activeChar.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

        _activeChar.setWantsPeace(1);
        _activeChar.deathPenalty(false, false, false);
        SystemMessage msg = new SystemMessage(SystemMessageId.YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN);
        msg.addString(_pledgeName);
        _activeChar.sendPacket(msg);
        msg = null;
        ClanTable.getInstance().checkSurrender(_clan, clan);
    }

    @Override
	public String getType()
    {
        return _C__69_REQUESTSURRENDERPERSONALLY;
    }
}