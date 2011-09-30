/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.PledgeReceiveWarList;


/**
 * Format: (ch) dd
 *
 */
public final class RequestPledgeWarList extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestPledgeWarList.class.getName());
    private static final String _C__D0_1E_REQUESTPLEDGEWARLIST = "[C] D0:1E RequestPledgeWarList";
    @SuppressWarnings("unused")
    private int _unk1;
    private int _tab;


    @Override
	protected void readImpl()
    {
        _unk1 = readD();
        _tab = readD();
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
        if (activeChar.getClan() == null) 
        	return;
        activeChar.sendPacket(new PledgeReceiveWarList(activeChar.getClan(),_tab));
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_1E_REQUESTPLEDGEWARLIST;
    }

}