/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: ch d
 */
public class ExDuelReady extends L2GameServerPacket
{
	private static final String _S__FE_4C_EXDUELREADY = "[S] FE:4d ExDuelReady";
	private int _unk1;

	public ExDuelReady(int unk1)
	{
		_unk1 = unk1;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x4d);

		writeD(_unk1);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4C_EXDUELREADY;
	}
}