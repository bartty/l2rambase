/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class GMHide extends L2GameServerPacket
{
	private static int _mode;

	/**
	 * @param _mode (0 = display windows, 1 = hide windows)
	 */
	public GMHide(int mode)
	{
		_mode = mode;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x93);
		writeD(_mode);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] 93 GMHide";
	}
}