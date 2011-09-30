/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class AutoAttackStart extends L2GameServerPacket
{
	private static final String _S__3B_AUTOATTACKSTART = "[S] 25 AutoAttackStart";
	private int _targetObjId;

	/**
	 * @param _characters
	 */
	public AutoAttackStart(int targetId)
	{
		_targetObjId = targetId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x25);
		writeD(_targetObjId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__3B_AUTOATTACKSTART;
	}
}