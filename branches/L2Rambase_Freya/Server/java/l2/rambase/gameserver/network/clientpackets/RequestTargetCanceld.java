/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.TargetUnselected;


public final class RequestTargetCanceld extends L2GameClientPacket
{
	private static final String _C__37_REQUESTTARGETCANCELD = "[C] 37 RequestTargetCanceld";

    private int _unselect;

	@Override
	protected void readImpl()
	{
        _unselect = readH();
	}

	@Override
	protected void runImpl()
	{
		L2Character activeChar = getClient().getActiveChar();
        if (activeChar != null)
        {
        	if (((L2PcInstance)activeChar).isLockedTarget())
        	{
        		activeChar.sendPacket(new SystemMessage(SystemMessageId.FAILED_DISABLE_TARGET));
        		return;
        	}
            if (_unselect == 0)
            {
            	if (activeChar.isCastingNow() && activeChar.canAbortCast())
            		activeChar.abortCast();
            	else if (activeChar.getTarget() != null)
            		activeChar.setTarget(null);
            }
            else if (activeChar.getTarget() != null)
            	activeChar.setTarget(null);
            else if(((L2PcInstance)activeChar).isInAirShip())
                    activeChar.broadcastPacket(new TargetUnselected(activeChar));

        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__37_REQUESTTARGETCANCELD;
	}
}
