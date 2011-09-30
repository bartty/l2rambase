/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;


public final class SpawnItem extends L2GameServerPacket
{
	private static final String _S__15_SPAWNITEM = "[S] 05 SpawnItem";
	private int _objectId;
	private int _itemId;
	private int _x, _y, _z;
	private int _stackable; 
	private long _count;

	public SpawnItem(L2Object obj)
	{
		_objectId = obj.getObjectId();
		_x = obj.getX();
		_y = obj.getY();
		_z = obj.getZ();
        
        if (obj instanceof L2ItemInstance)
        {
            L2ItemInstance item = (L2ItemInstance) obj;
            _itemId = item.getItemId();
            _stackable = item.isStackable() ? 0x01 : 0x00;
            _count = item.getCount();
        }
        else
        {
            _itemId = obj.getPoly().getPolyId();
            _stackable = 0;
            _count = 1;
        }
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x05);
		writeD(_objectId);
		writeD(_itemId);

		writeD(_x);
		writeD(_y);
		writeD(_z);
		// only show item count if it is a stackable item
		writeD(_stackable);
		writeQ(_count);
		writeD(0x00); //c2
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__15_SPAWNITEM;
	}
}