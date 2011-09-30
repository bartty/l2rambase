/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExRequestHackShield extends L2GameServerPacket
{
	private static final String _S__FE_48_EXREQUESTHACKSHIELD = "[S] FE:49 ExRequestHackShield";

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x49);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_48_EXREQUESTHACKSHIELD;
	}
}