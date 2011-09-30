/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExAutoSoulShot extends L2GameServerPacket
{
	private static final String _S__FE_12_EXAUTOSOULSHOT = "[S] FE:0c ExAutoSoulShot";
	private int _itemId;
	private int _type;

	/**
	 * 0xfe:0x12 ExAutoSoulShot         (ch)dd
	 * @param _characters
	 */
	public ExAutoSoulShot(int itemId, int type)
	{
		_itemId = itemId;
		_type = type;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xFE);
		writeH(0x0c);     // sub id
		writeD(_itemId);
		writeD(_type);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_12_EXAUTOSOULSHOT;
	}
}