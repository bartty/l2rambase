/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.L2FriendSay;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


/**
 * Recieve Private (Friend) Message - 0xCC
 *
 * Format: c SS
 *
 * S: Message
 * S: Receiving Player
 */
public final class RequestSendFriendMsg extends L2GameClientPacket
{
    private static final String _C__CC_REQUESTSENDMSG = "[C] CC RequestSendMsg";
	private static Logger _logChat = Logger.getLogger("chat");

    private String _message;
    private String _reciever;

    @Override
	protected void readImpl()
    {
        _message = readS();
        _reciever = readS();
    }

    @Override
	protected void runImpl()
    {
    	L2PcInstance activeChar = getClient().getActiveChar();
    	if (activeChar == null) return;

        L2PcInstance targetPlayer = L2World.getInstance().getPlayer(_reciever);
        if (targetPlayer == null)
        {
        	activeChar.sendPacket(new SystemMessage(SystemMessageId.TARGET_IS_NOT_FOUND_IN_THE_GAME));
        	return;
        }

		if (Config.LOG_CHAT)
		{
			LogRecord record = new LogRecord(Level.INFO, _message);
			record.setLoggerName("chat");
			record.setParameters(new Object[]{"PRIV_MSG", "[" + activeChar.getName() + " to "+ _reciever +"]"});

			_logChat.log(record);
		}

        L2FriendSay frm = new L2FriendSay(activeChar.getName(), _reciever, _message);
        targetPlayer.sendPacket(frm);
    }

    @Override
	public String getType()
    {
        return _C__CC_REQUESTSENDMSG;
    }
}