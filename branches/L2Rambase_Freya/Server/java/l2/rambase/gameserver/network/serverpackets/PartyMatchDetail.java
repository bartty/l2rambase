/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PartyMatchDetail extends L2GameServerPacket
{
	private PartyMatchRoom _room;

	/**
	 * @param allPlayers
	 */
	public PartyMatchDetail(L2PcInstance player, PartyMatchRoom room)
	{
		_room = room;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x9d);
		writeD(_room.getId());			//	Room ID
		writeD(_room.getMaxMembers());		//	Max Members
		writeD(_room.getMinLvl());		//	Level Min
		writeD(_room.getMaxLvl());		//	Level Max
		writeD(_room.getLootType());		//	Loot Type
		writeD(_room.getLocation());	//	Room Location
		writeS(_room.getTitle());		//	Room title
	}

	@Override
	public String getType()
	{
		return "[S] 9d PartyMatchDetail";
	}
}