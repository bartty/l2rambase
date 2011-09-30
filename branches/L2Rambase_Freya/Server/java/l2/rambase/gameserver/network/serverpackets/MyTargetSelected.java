/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class MyTargetSelected extends L2GameServerPacket
{
	private static final String _S__BF_MYTARGETSELECTED = "[S] b9 MyTargetSelected";
	private int _objectId;
	private int _color;

	/**
	 * @param int objectId of the target
	 * @param int level difference to the target. name color is calculated from that
	 */
	public MyTargetSelected(int objectId, int color)
	{
		_objectId = objectId;
		_color = color;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(185);
		writeD(_objectId);
		writeH(_color);
		writeD(0x00);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__BF_MYTARGETSELECTED;
	}
}