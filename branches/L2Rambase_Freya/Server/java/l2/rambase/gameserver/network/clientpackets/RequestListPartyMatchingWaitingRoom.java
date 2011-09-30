/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExListPartyMatchingWaitingRoom;


public class RequestListPartyMatchingWaitingRoom extends L2GameClientPacket
{
	private static int _page;
	private static int _minlvl;
	private static int _maxlvl;
    private static int _mode; // 1 - waitlist 0 - room waitlist

    @Override
	protected void readImpl()
    {
    	_page = readD();
    	_minlvl = readD();
    	_maxlvl = readD();
    	_mode	= readD();
    }

    @Override
	protected void runImpl()
    {
		L2PcInstance _activeChar = getClient().getActiveChar();
		
		if (_activeChar == null)
			return;

		_activeChar.sendPacket(new ExListPartyMatchingWaitingRoom(_activeChar,_page,_minlvl,_maxlvl, _mode));
    }

    @Override
    public String getType()
    {
        return "[C] D0:31 RequestListPartyMatchingWaitingRoom";
    }

}