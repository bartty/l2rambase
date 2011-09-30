/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

/**
 * Format: (ch) S
 * 
 *
 */
public class RequestAskJoinPartyRoom extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestAskJoinPartyRoom.class.getName());
    private static final String _C__D0_14_REQUESTASKJOINPARTYROOM = "[C] D0:14 RequestAskJoinPartyRoom";
    private String _player; 


    @Override
	protected void readImpl()
    {
        _player = readS();
    }

    @Override
	protected void runImpl()
    {
    	_log.info((new StringBuilder()).append("C5:RequestAskJoinPartyRoom: S: ").append(_player).toString());
    }


    @Override
    public String getType()
    {
        return _C__D0_14_REQUESTASKJOINPARTYROOM;
    }

}