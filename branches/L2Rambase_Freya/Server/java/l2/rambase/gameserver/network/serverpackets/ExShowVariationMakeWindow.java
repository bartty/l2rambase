/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExShowVariationMakeWindow extends L2GameServerPacket
{
	private static final String _S__FE_50_EXSHOWVARIATIONMAKEWINDOW = "[S] FE:51 ExShowVariationMakeWindow";

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x51);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_50_EXSHOWVARIATIONMAKEWINDOW;
	}
}