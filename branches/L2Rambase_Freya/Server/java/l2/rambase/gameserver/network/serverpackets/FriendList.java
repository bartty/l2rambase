/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Support for "Chat with Friends" dialog.
 *
 * This packet is sent only at login.
 *
 * Format: cd (dSdd)
 * d: Total Friend Count
 *
 * d: Player Object ID
 * S: Friend Name
 * d: Online/Offline
 * d: Unknown (0 if offline)
 */
public class FriendList extends L2GameServerPacket
{
	private static final String _S__FA_FRIENDLIST = "[S] 75 FriendList";
	private List<Integer> _friends;
	private L2PcInstance _activeChar;
	
	@Override
	public void runImpl()
	{
		if (getClient() != null && getClient().getActiveChar() != null)
		{
			_activeChar = getClient().getActiveChar();
			_friends = _activeChar.getFriendList();
		}
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x75);
		if (_friends != null)
		{
			writeD(_friends.size());
			for (int ObjId : _friends)
			{
				String name = CharNameTable.getInstance().getNameById(ObjId);
				L2PcInstance player = L2World.getInstance().getPlayer(ObjId);
				boolean online = false;
				if (player != null && player.isOnline() == 1)
					online = true;
				writeD(ObjId); // character id
				writeS(name);
				writeD(online ? 0x01 : 0x00); // online
				writeD(online ? ObjId : 0x00); // object id if online
			}
		}
		else
			writeD(0);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FA_FRIENDLIST;
	}
}