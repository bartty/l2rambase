/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestReplyStartPledgeWar extends L2GameClientPacket
{
    private static final String _C__4e_REQUESTREPLYSTARTPLEDGEWAR = "[C] 4e RequestReplyStartPledgeWar";
	
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
            ClanTable.getInstance().storeclanswars(requestor.getClanId(), activeChar.getClanId());
        }
        else
        {
            requestor.sendPacket(new SystemMessage(SystemMessageId.WAR_PROCLAMATION_HAS_BEEN_REFUSED));
        }
        activeChar.setActiveRequester(null);
        requestor.onTransactionResponse();
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _C__4e_REQUESTREPLYSTARTPLEDGEWAR;
    }
}