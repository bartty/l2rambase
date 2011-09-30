/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import javolution.util.FastList;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ListPartyWating extends L2GameServerPacket
{
	private L2PcInstance _cha;
	private int _loc;
	private int _lim;
	private FastList<PartyMatchRoom> _rooms;
	
	public ListPartyWating(L2PcInstance player, int auto, int location, int limit)
	{
		_cha = player;
		_loc = location;
		_lim = limit;
		_rooms = new FastList<PartyMatchRoom>();
	}

	@Override
	protected final void writeImpl()
	{
		for(PartyMatchRoom room : PartyMatchRoomList.getInstance().getRooms())
		{
			if (room.getMembers() < 1 || room.getOwner() == null || room.getOwner().isOnline() == 0 || room.getOwner().getPartyRoom() != room.getId())
			{
				PartyMatchRoomList.getInstance().deleteRoom(room.getId());
				continue;
			}
			if (_loc > 0 && _loc != room.getLocation())
				continue;
			if (_lim == 0 && ((_cha.getLevel() < room.getMinLvl()) || (_cha.getLevel() > room.getMaxLvl())))
				continue;
			_rooms.add(room);
		}
		int count = 0;
		int size = _rooms.size();

		writeC(0x9c);
		if (size > 0)
			writeD(1);
		else
			writeD(0);

		writeD(_rooms.size());
		while(size > count)
		{
			writeD(_rooms.get(count).getId());
			writeS(_rooms.get(count).getTitle());
			writeD(_rooms.get(count).getLocation());
			writeD(_rooms.get(count).getMinLvl());
			writeD(_rooms.get(count).getMaxLvl());
			writeD(_rooms.get(count).getMembers());
			writeD(_rooms.get(count).getMaxMembers());
			writeS(_rooms.get(count).getOwner().getName());
			count++;
		}
	}

	@Override
	public String getType()
	{
		return "[S] 9c ListPartyWating";
	}
}