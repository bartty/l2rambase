/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPutCommissionResultForVariationMake extends L2GameServerPacket
{
	private static final String _S__FE_54_EXCONFIRMVARIATIONGEMSTONE = "[S] FE:55 ExPutCommissionResultForVariationMake";

	private int _gemstoneObjId;
	private int _itemId;
	private long _gemstoneCount;
	private int _unk1;
	private int _unk2;
	private int _unk3;

	public ExPutCommissionResultForVariationMake(int gemstoneObjId, long count, int itemId)
	{
		_gemstoneObjId = gemstoneObjId;
		_itemId = itemId;
		_gemstoneCount = count;
		_unk1 = 0;
		_unk2 = 0;
		_unk3 = 1;		
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x55);
        writeD(_gemstoneObjId);
        writeD(_itemId);
        writeQ(_gemstoneCount);
        writeD(_unk1);
        writeD(_unk2);
        writeD(_unk3);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_54_EXCONFIRMVARIATIONGEMSTONE;
	}
}