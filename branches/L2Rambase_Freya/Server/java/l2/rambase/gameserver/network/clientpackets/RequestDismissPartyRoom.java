/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class RequestDismissPartyRoom extends L2GameClientPacket
{
	private static final String _C__D0_0A_REQUESTDISMISSPARTYROOM = "[C] D0:0A RequestDismissPartyRoom";
	
	private int _roomid;
	@SuppressWarnings("unused")
	private int _data2;

	@Override
	protected void readImpl()
	{
		_roomid = readD();
		_data2 = readD();
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

		PartyMatchRoomList.getInstance().deleteRoom(_roomid);
	}

	@Override
	public String getType()
	{
		return _C__D0_0A_REQUESTDISMISSPARTYROOM;
	}

}
