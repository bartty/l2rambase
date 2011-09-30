/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExRequestChangeNicknameColor extends L2GameServerPacket
{
	private int _itemObjectId;

	public ExRequestChangeNicknameColor(int itemObjectId)
	{
		_itemObjectId = itemObjectId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x83);
		writeD(_itemObjectId);
	}

	@Override
	public String getType()
	{
		return "[S] FE:83 ExRequestChangeNicknameColor";
	}
}