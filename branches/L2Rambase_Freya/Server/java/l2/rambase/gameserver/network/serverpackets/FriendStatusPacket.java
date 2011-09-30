/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.model.L2World;

/**
 * Support for "Chat with Friends" dialog.
 * <BR>
 * Inform player about friend online status change
 * <BR>
 * Format: cdSd<BR> 
 * d: Online/Offline<BR>
 * S: Friend Name  <BR>
 * d: Player Object ID <BR>
 */
public class FriendStatusPacket extends L2GameServerPacket
{
	private static final String _S__FA_FRIENDLIST = "[S] 77 FriendStatusPacket";
	private boolean  _online;
	private int _objid;
	private String _name;

	public FriendStatusPacket(int objId)
	{
		_objid = objId;
		_name = CharNameTable.getInstance().getNameById(objId);
		_online = L2World.getInstance().getPlayer(objId) != null;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x77);
		writeD(_online ? 1 : 0);
		writeS(_name);
		writeD(_objid); 
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