/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPutItemResultForVariationCancel extends L2GameServerPacket
{
	private static final String _S__FE_56_EXCONFIRMCANCELITEM = "[S] FE:57 ExPutItemResultForVariationCancel";

	private int _itemObjId;
	private int _price;

	public ExPutItemResultForVariationCancel(int itemObjId, int price)
	{
		_itemObjId = itemObjId;
		_price = price;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x57);
		writeD(0x40A97712);
		writeD(_itemObjId);
		writeD(0x27);
		writeD(0x2006);
		writeQ(_price);
		writeD(0x01);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_56_EXCONFIRMCANCELITEM;
	}
}