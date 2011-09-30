/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public final class RequestReplySurrenderPledgeWar extends L2GameClientPacket
{
    private static final String _C__52_REQUESTREPLYSURRENDERPLEDGEWAR = "[C] 52 RequestReplySurrenderPledgeWar";
    
    private int _answer;

    @Override
	protected void readImpl()
    {
        @SuppressWarnings("unused") String _reqName = readS();
        _answer  = readD();
    }

    @Override
	protected void runImpl()
    {
        L2PcInstance activeChar = getClient().getActiveChar();
        if (activeChar == null)
            return;
        L2PcInstance requestor = activeChar.getActiveRequester();
        if (requestor == null)
            return;

        if (_answer == 1)
        {
            requestor.deathPenalty(false, false, false);
            ClanTable.getInstance().deleteclanswars(requestor.getClanId(), activeChar.getClanId());
        }

        activeChar.onTransactionRequest(null);
    }

    @Override
	public String getType()
    {
        return _C__52_REQUESTREPLYSURRENDERPLEDGEWAR;
    }
}