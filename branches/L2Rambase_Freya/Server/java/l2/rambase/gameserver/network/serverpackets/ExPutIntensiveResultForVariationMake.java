/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPutIntensiveResultForVariationMake extends L2GameServerPacket
{
	private static final String _S__FE_53_EXCONFIRMVARIATIONREFINER = "[S] FE:54 ExPutIntensiveResultForVariationMake";

	private int _refinerItemObjId;
	private int _lifestoneItemId;
	private int _gemstoneItemId;
	private int _gemstoneCount;
	private int _unk2;

	public ExPutIntensiveResultForVariationMake(int refinerItemObjId, int lifeStoneId, int gemstoneItemId, int gemstoneCount)
	{
		_refinerItemObjId = refinerItemObjId;
		_lifestoneItemId = lifeStoneId;
		_gemstoneItemId = gemstoneItemId;
		_gemstoneCount = gemstoneCount;
		_unk2 = 1;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x54);
		writeD(_refinerItemObjId);
		writeD(_lifestoneItemId);
		writeD(_gemstoneItemId);
		writeQ(_gemstoneCount);
		writeD(_unk2);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_53_EXCONFIRMVARIATIONREFINER;
	}
}