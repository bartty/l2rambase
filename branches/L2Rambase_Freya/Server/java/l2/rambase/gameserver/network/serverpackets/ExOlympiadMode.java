/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExOlympiadMode extends L2GameServerPacket
{
	private static final String _S__FE_2B_OLYMPIADMODE = "[S] FE:7c ExOlympiadMode";
	private int _mode;

	/**
	 * @param _mode (0 = return, 3 = spectate)
	 */
	public ExOlympiadMode(int mode)
	{
		_mode = mode;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x7c);
		writeC(_mode);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_2B_OLYMPIADMODE;
	}
}