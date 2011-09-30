/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExManagePartyRoomMember;
import l2.rambase.gameserver.network.serverpackets.JoinParty;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 *  sample
 *  2a
 *  01 00 00 00
 *
 *  format  cdd
 *
 *
 * @version $Revision: 1.7.4.2 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestAnswerJoinParty extends L2GameClientPacket
{
	private static final String _C__2A_REQUESTANSWERPARTY = "[C] 2A RequestAnswerJoinParty";
	//private static Logger _log = Logger.getLogger(RequestAnswerJoinParty.class.getName());

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

    		JoinParty join = new JoinParty(_response);
    		requestor.sendPacket(join);

    		if (_response == 1)
            {
				if (requestor.getParty() != null)
				{
					if (requestor.getParty().getMemberCount() >= 9)
					{
						player.sendPacket(new SystemMessage(SystemMessageId.PARTY_FULL));
						requestor.sendPacket(new SystemMessage(SystemMessageId.PARTY_FULL));
						return;
					}
				}
    			player.joinParty(requestor.getParty());

    			if(requestor.isInPartyMatchRoom() && player.isInPartyMatchRoom())
    			{
    				PartyMatchRoomList list = PartyMatchRoomList.getInstance();
    				if(list != null && (list.getPlayerRoomId(requestor) == list.getPlayerRoomId(player)))
    				{
    					PartyMatchRoom room = list.getPlayerRoom(requestor);
    					for(L2PcInstance member : room.getPartyMembers())
    					{
    						member.sendPacket(new ExManagePartyRoomMember(player, room, 1));
    					}
    				}
    			}
    			else if (requestor.isInPartyMatchRoom() && !player.isInPartyMatchRoom())
    			{
    				PartyMatchRoomList list = PartyMatchRoomList.getInstance();
    				if(list != null)
    				{
    					PartyMatchRoom room = list.getPlayerRoom(requestor);
    					room.addMember(player);
    					for(L2PcInstance member : room.getPartyMembers())
    					{
    						member.sendPacket(new ExManagePartyRoomMember(player, room, 1));
    					}
    					player.setPartyRoom(room.getId());
    					player.broadcastUserInfo();
    				}
    			}
    		}
    		else
            {
    			requestor.sendPacket(new SystemMessage(SystemMessageId.PLAYER_DECLINED));

    			//activate garbage collection if there are no other members in party (happens when we were creating new one)
    			if (requestor.getParty() != null && requestor.getParty().getMemberCount() == 1) requestor.setParty(null);
    		}
    		
    		if (requestor.getParty() != null)
    			requestor.getParty().setPendingInvitation(false); // if party is null, there is no need of decreasing

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
		return _C__2A_REQUESTANSWERPARTY;
	}
}
