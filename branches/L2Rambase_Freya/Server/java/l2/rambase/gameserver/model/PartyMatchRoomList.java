/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.util.Map;

import javolution.util.FastMap;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExClosePartyRoom;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class PartyMatchRoomList
{
	private int _maxid = 1;
	private Map<Integer, PartyMatchRoom> _rooms;
	
	private PartyMatchRoomList()
	{
		_rooms = new FastMap<Integer, PartyMatchRoom>();
	}	
	
	public synchronized void addPartyMatchRoom(int id, PartyMatchRoom room)
	{
		_rooms.put(id, room);
		_maxid++;
	}
	
	public void deleteRoom(int id)
	{
		for(L2PcInstance _member : getRoom(id).getPartyMembers())
		{
			if(_member == null)
				continue;

			_member.sendPacket(new ExClosePartyRoom());
			_member.sendPacket(new SystemMessage(SystemMessageId.PARTY_ROOM_DISBANDED));
			
			_member.setPartyRoom(0);
			//_member.setPartyMatching(0);
			_member.broadcastUserInfo();
		}		
		_rooms.remove(id);
	}
	
	public PartyMatchRoom getRoom(int id)
	{
		return _rooms.get(id);
	}
	
	public PartyMatchRoom[] getRooms()
	{
		return _rooms.values().toArray(new PartyMatchRoom[_rooms.size()]);
	}
	
	public int getPartyMatchRoomCount()
	{
		return _rooms.size();
	}

	public int getMaxId()
	{
		return _maxid;
	}	
	
	public PartyMatchRoom getPlayerRoom(L2PcInstance player)
	{
		for(PartyMatchRoom _room : _rooms.values())
			for(L2PcInstance member : _room.getPartyMembers())
				if(member.equals(player))
					return _room;
		return null;
	}
	
	public int getPlayerRoomId(L2PcInstance player)
	{
		for(PartyMatchRoom _room : _rooms.values())
			for(L2PcInstance member : _room.getPartyMembers())
				if(member.equals(player))
					return _room.getId();
		return -1;
	}
	
	public static PartyMatchRoomList getInstance()
	{
		return SingletonHolder._instance;
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final PartyMatchRoomList _instance = new PartyMatchRoomList();
	}
}