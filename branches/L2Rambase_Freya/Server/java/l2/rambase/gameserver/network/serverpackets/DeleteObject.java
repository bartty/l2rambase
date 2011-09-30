/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Object;


public final class DeleteObject extends L2GameServerPacket
{
	private static final String _S__1E_DELETEOBJECT = "[S] 08 DeleteObject";
	private int _objectId;

	public DeleteObject(L2Object obj)
	{
		_objectId = obj.getObjectId();
	}
	
	public DeleteObject(int objId)
	{
		_objectId = objId;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x08);
		writeD(_objectId);
		writeD(0x00); //c2
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__1E_DELETEOBJECT;
	}
}