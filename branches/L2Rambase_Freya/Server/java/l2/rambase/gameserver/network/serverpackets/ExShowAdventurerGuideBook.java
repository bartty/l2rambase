/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExShowAdventurerGuideBook extends L2GameServerPacket
{
	private static final String _S__FE_37_EXSHOWADVENTURERGUIDEBOOK = "[S] FE:38 ExShowAdventurerGuideBook";

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x38);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_37_EXSHOWADVENTURERGUIDEBOOK;
	}
}