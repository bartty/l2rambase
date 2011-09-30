/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class ChooseInventoryItem extends L2GameServerPacket
{
	private static final String _S__6F_CHOOSEINVENTORYITEM = "[S] 7c ChooseInventoryItem";

	private int _itemId;

	public ChooseInventoryItem(int itemId)
	{
		_itemId=itemId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x7c);
		writeD(_itemId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__6F_CHOOSEINVENTORYITEM;
	}
}