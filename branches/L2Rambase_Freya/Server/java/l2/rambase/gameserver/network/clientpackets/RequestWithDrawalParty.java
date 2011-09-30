/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExClosePartyRoom;
import l2.rambase.gameserver.network.serverpackets.ExPartyRoomMember;
import l2.rambase.gameserver.network.serverpackets.PartyMatchDetail;


public final class RequestWithDrawalParty extends L2GameClientPacket
{
	private static final String _C__2B_REQUESTWITHDRAWALPARTY = "[C] 2B RequestWithDrawalParty";
	
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;

		if (player.isInParty())
		{
			if (player.getParty().isInDimensionalRift() && !player.getParty().getDimensionalRift().getRevivedAtWaitingRoom().contains(player))
				player.sendPacket(SystemMessageId.COULD_NOT_LEAVE_PARTY);
			else
			{				
				player.getParty().removePartyMember(player);
				
				if(player.isInPartyMatchRoom())
				{
					PartyMatchRoom _room = PartyMatchRoomList.getInstance().getPlayerRoom(player);
					if(_room != null)
					{
						player.sendPacket(new PartyMatchDetail(player, _room));
						player.sendPacket(new ExPartyRoomMember(player, _room, 0));
						player.sendPacket(new ExClosePartyRoom());
						
						_room.deleteMember(player);
					}
					player.setPartyRoom(0);
					player.broadcastUserInfo();
				}
			}
		}
	}

	@Override
	public String getType()
	{
		return _C__2B_REQUESTWITHDRAWALPARTY;
	}
}
