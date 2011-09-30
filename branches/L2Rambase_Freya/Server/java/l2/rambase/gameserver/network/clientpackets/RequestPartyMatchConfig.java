/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.PartyMatchWaitingList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.ExPartyRoomMember;
import l2.rambase.gameserver.network.serverpackets.ListPartyWating;
import l2.rambase.gameserver.network.serverpackets.PartyMatchDetail;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestPartyMatchConfig extends L2GameClientPacket
{
	private static final String _C__7F_REQUESTPARTYMATCHCONFIG = "[C] 7F RequestPartyMatchConfig";

	private int _auto, _loc, _lvl;


	@Override
	protected void readImpl()
	{
		_auto = readD();	//
		_loc = readD();		// Location
		_lvl = readD();		// my level
	}

 	@Override
 	protected void runImpl()
 	{
		L2PcInstance _activeChar = getClient().getActiveChar();
 
		if (_activeChar == null)
			return;

		if( !_activeChar.isInPartyMatchRoom() && _activeChar.getParty() != null && _activeChar.getParty().getLeader() != _activeChar)
		{
			_activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_VIEW_PARTY_ROOMS));
			_activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		if(_activeChar.isInPartyMatchRoom())
		{
			// If Player is in Room show him room, not list
			PartyMatchRoomList _list = PartyMatchRoomList.getInstance();
			if (_list==null)
				return;
			
			PartyMatchRoom _room = _list.getPlayerRoom(_activeChar);
			if (_room == null)
				return;
			
			_activeChar.sendPacket(new PartyMatchDetail(_activeChar,_room));
			_activeChar.sendPacket(new ExPartyRoomMember(_activeChar, _room, 2));

			_activeChar.setPartyRoom(_room.getId());			
			_activeChar.broadcastUserInfo();
		}
		else
		{
			// Add to waiting list
			PartyMatchWaitingList.getInstance().addPlayer(_activeChar);

			// Send Room list
			ListPartyWating matchList = new ListPartyWating(_activeChar,_auto,_loc,_lvl);
			
			_activeChar.sendPacket(matchList);
		}
 	}

	@Override
	public String getType()
	{
		return _C__7F_REQUESTPARTYMATCHCONFIG;
	}
}
