/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class TargetUnselected extends L2GameServerPacket
{
	private static final String _S__3A_TARGETUNSELECTED = "[S] 24 TargetUnselected";
	private int _targetObjId;
	private int _x;
	private int _y;
	private int _z;

	/**
	 * @param _characters
	 */
	public TargetUnselected(L2Character character)
	{
		_targetObjId = character.getObjectId();
		_x = character.getX();
		_y = character.getY();
		_z = character.getZ();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x24);
		writeD(_targetObjId);
		writeD(_x);
		writeD(_y);
		writeD(_z);
		writeD(0x00); //??
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__3A_TARGETUNSELECTED;
	}
}