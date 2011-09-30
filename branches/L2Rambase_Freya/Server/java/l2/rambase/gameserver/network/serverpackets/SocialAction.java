/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class SocialAction extends L2GameServerPacket
{
	public static final int LEVEL_UP = 2122;
	
	private static final String _S__3D_SOCIALACTION = "[S] 27 SocialAction";
	private int _charObjId;
	private int _actionId;

	/**
	 * 0x3d SocialAction         dd
	 * @param _characters
	 */
	public SocialAction(int playerId, int actionId)
	{
		_charObjId = playerId;
		_actionId = actionId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x27);
		writeD(_charObjId);
		writeD(_actionId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__3D_SOCIALACTION;
	}
}