/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.FriendPacket;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 *  sample
 *  5F
 *  01 00 00 00
 *
 *  format  cdd
 *
 *
 * @version $Revision: 1.7.4.2 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestAnswerFriendInvite extends L2GameClientPacket
{
	private static final String _C__5F_REQUESTANSWERFRIENDINVITE = "[C] 5F RequestAnswerFriendInvite";
	private static Logger _log = Logger.getLogger(RequestAnswerFriendInvite.class.getName());

	private int _response;

	@Override
	protected void readImpl()
	{
		_response = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
    	if(player != null)
        {
    		L2PcInstance requestor = player.getActiveRequester();
    		if (requestor == null)
    		    return;

    		if (_response == 1)
            {
        		Connection con = null;
        		try
        		{
        		    con = L2DatabaseFactory.getInstance().getConnection();
        		    PreparedStatement statement = con.prepareStatement("INSERT INTO character_friends (charId, friendId) VALUES (?, ?), (?, ?)");
                    statement.setInt(1, requestor.getObjectId());
                    statement.setInt(2, player.getObjectId());
                    statement.setInt(3, player.getObjectId());
                    statement.setInt(4, requestor.getObjectId());
        		    statement.execute();
        		    statement.close();
        			SystemMessage msg = new SystemMessage(SystemMessageId.YOU_HAVE_SUCCEEDED_INVITING_FRIEND);
        			requestor.sendPacket(msg);

        			//Player added to your friendlist
            		msg = new SystemMessage(SystemMessageId.S1_ADDED_TO_FRIENDS);
        			msg.addString(player.getName());
            		requestor.sendPacket(msg);
            		requestor.getFriendList().add(player.getObjectId());

        			//has joined as friend.
            		msg = new SystemMessage(SystemMessageId.S1_JOINED_AS_FRIEND);
        			msg.addString(requestor.getName());
            		player.sendPacket(msg);
            		player.getFriendList().add(requestor.getObjectId());

            		//Send notificacions for both player in order to show them online
    				player.sendPacket(new FriendPacket(true, requestor.getObjectId()));
    				requestor.sendPacket(new FriendPacket(true, player.getObjectId()));
        		}
        		catch (Exception e)
        		{
        		    _log.log(Level.WARNING, "Could not add friend objectid: "+ e.getMessage(), e);
        		}
        		finally
        		{
        		    L2DatabaseFactory.close(con);
        		}
    		} else
            {
    			SystemMessage msg = new SystemMessage(SystemMessageId.FAILED_TO_INVITE_A_FRIEND);
    			requestor.sendPacket(msg);
    		}

    		player.setActiveRequester(null);
    		requestor.onTransactionResponse();
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__5F_REQUESTANSWERFRIENDINVITE;
	}
}
