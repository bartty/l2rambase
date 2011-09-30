/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: ch d
 */
public class ExDuelStart extends L2GameServerPacket
{
	private static final String _S__FE_4D_EXDUELSTART = "[S] FE:4e ExDuelStart";
	private int _unk1;

	public ExDuelStart(int unk1)
	{
		_unk1 = unk1;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x4e);

		writeD(_unk1);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4D_EXDUELSTART;
	}
}