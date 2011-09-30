/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchWaitingList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public final class RequestExitPartyMatchingWaitingRoom extends L2GameClientPacket
{
	private static final String _C__D0_17_REQUESTEXITPARTYMATCHINGWAITINGROOM = "[C] D0:17 RequestExitPartyMatchingWaitingRoom";
	
    @Override
	protected void readImpl()
    {
    }

    @Override
	protected void runImpl()
    {
    	L2PcInstance _activeChar = getClient().getActiveChar();
    	
    	if (_activeChar == null)
    		return;
    	
    	PartyMatchWaitingList.getInstance().removePlayer(_activeChar);
    }

    @Override
    public String getType()
    {
        return _C__D0_17_REQUESTEXITPARTYMATCHINGWAITINGROOM;
    }

}