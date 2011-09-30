/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.PartyMatchWaitingList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExManagePartyRoomMember;
import l2.rambase.gameserver.network.serverpackets.ExPartyRoomMember;
import l2.rambase.gameserver.network.serverpackets.PartyMatchDetail;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestPartyMatchDetail extends L2GameClientPacket
{
	private int _roomid;
	@SuppressWarnings("unused")
    private int _unk1;
	@SuppressWarnings("unused")
    private int _unk2;
	@SuppressWarnings("unused")
    private int _unk3;


	@Override
	protected void readImpl()
	{
		_roomid = readD();
		/*
		 * IF player click on Room all unk are 0
		 * IF player click AutoJoin values are -1 1 1
		 */
        _unk1 = readD();
        _unk2 = readD();
        _unk3 = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance _activeChar = getClient().getActiveChar();
		if (_activeChar == null)
			return;
		
		PartyMatchRoom _room = PartyMatchRoomList.getInstance().getRoom(_roomid);
		if (_room == null)
			return;
		
		if ((_activeChar.getLevel() >= _room.getMinLvl()) && (_activeChar.getLevel() <= _room.getMaxLvl()))
		{
			// Remove from waiting list
			PartyMatchWaitingList.getInstance().removePlayer(_activeChar);
			
			_activeChar.setPartyRoom(_roomid);
			
			_activeChar.sendPacket(new PartyMatchDetail(_activeChar, _room));
			_activeChar.sendPacket(new ExPartyRoomMember(_activeChar, _room, 0));

			for(L2PcInstance _member : _room.getPartyMembers())
			{
				if(_member == null)
					continue;
				
				_member.sendPacket(new ExManagePartyRoomMember(_activeChar, _room, 0));
				
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_ENTERED_PARTY_ROOM);
				sm.addCharName(_activeChar);
				_member.sendPacket(sm);				
			}
			_room.addMember(_activeChar);

			// Info Broadcast
			_activeChar.broadcastUserInfo();			
		}
		else
		{
			_activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_ENTER_PARTY_ROOM));
		}
	}

	@Override
	public String getType()
	{
		return "[C] 81 RequestPartyMatchDetail";
	}
}
