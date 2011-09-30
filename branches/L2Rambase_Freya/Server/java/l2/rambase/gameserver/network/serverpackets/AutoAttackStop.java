/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class AutoAttackStop extends L2GameServerPacket
{
	private static final String _S__3C_AUTOATTACKSTOP = "[S] 26 AutoAttackStop";
	private int _targetObjId;

	/**
	 * @param _characters
	 */
	public AutoAttackStop(int targetObjId)
	{
		_targetObjId = targetObjId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x26);
		writeD(_targetObjId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__3C_AUTOATTACKSTOP;
	}
}