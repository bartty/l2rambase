/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: ch d
 */
public class ExDuelEnd extends L2GameServerPacket
{
	private static final String _S__FE_4E_EXDUELEND = "[S] FE:4f ExDuelEnd";
	private int _unk1;

	public ExDuelEnd(int unk1)
	{
		_unk1 = unk1;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x4f);

		writeD(_unk1);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4E_EXDUELEND;
	}

}
