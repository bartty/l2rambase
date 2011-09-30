/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExClosePartyRoom;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestWithdrawPartyRoom extends L2GameClientPacket
{
	private int _roomid;
	@SuppressWarnings("unused")
    private int _unk1;


	@Override
	protected void readImpl()
	{
		_roomid = readD();
		_unk1 = readD();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance _activeChar = getClient().getActiveChar();

		if (_activeChar == null)
			return;

		PartyMatchRoom _room = PartyMatchRoomList.getInstance().getRoom(_roomid);
		if (_room == null)
			return;

		if((_activeChar.isInParty() && _room.getOwner().isInParty()) && (_activeChar.getParty().getPartyLeaderOID() == _room.getOwner().getParty().getPartyLeaderOID()))
		{			
			// If user is in party with Room Owner
			// is not removed from Room
			
			_activeChar.broadcastUserInfo();			
		}
		else
		{
			_room.deleteMember(_activeChar);
			
			_activeChar.setPartyRoom(0);

			_activeChar.sendPacket(new ExClosePartyRoom());
			_activeChar.sendPacket(new SystemMessage(SystemMessageId.PARTY_ROOM_EXITED));
		}
	}

	@Override
	public String getType()
	{
		return "[C] D0:0B RequestWithdrawPartyRoom";
	}

}
