/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.model.L2World;

/**
 * Support for "Chat with Friends" dialog.
 * 
 * Add new friend or delete.
 * <BR>
 * Format: cddSdd <BR>
 * d: action <BR>
 * d: Player Object ID <BR>
 * S: Friend Name <BR>
 * d: Online/Offline <BR>
 * d: Unknown (0 if offline)<BR>
 */
public class FriendPacket extends L2GameServerPacket
{	
	private static final String _S__FA_FRIENDLIST = "[S] 76 FriendPacket";
	private boolean _action, _online;
	private int _objid;
	private String _name;
	
	/**
	 * 
	 * @param action - true for adding, false for remove
	 */
	public FriendPacket(boolean action, int objId)
	{
		_action = action;
		_objid = objId;
		_name = CharNameTable.getInstance().getNameById(objId);
		_online = L2World.getInstance().getPlayer(objId) != null;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x76);
		writeD(_action ? 1 : 3); // 1-add 3-remove
		writeD(_objid);
		writeS(_name);
		writeD(_online ? 1 : 0);
		writeD(_online ? _objid : 0);
		
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