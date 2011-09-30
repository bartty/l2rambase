/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ItemInstance;


public final class GetItem extends L2GameServerPacket
{
	private static final String _S__17_GETITEM = "[S] 17 GetItem";
	private L2ItemInstance _item;
	private int _playerId;

	public GetItem(L2ItemInstance item, int playerId)
	{
		_item = item;
		_playerId = playerId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x17);
		writeD(_playerId);
		writeD(_item.getObjectId());

		writeD(_item.getX());
		writeD(_item.getY());
		writeD(_item.getZ());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__17_GETITEM;
	}
}