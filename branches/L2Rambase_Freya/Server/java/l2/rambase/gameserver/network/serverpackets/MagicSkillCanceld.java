/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class MagicSkillCanceld extends L2GameServerPacket
{
	private static final String _S__5B_MAGICSKILLCANCELD = "[S] 49 MagicSkillCanceld";

	private int _objectId;

	public MagicSkillCanceld (int objectId)
	{
		_objectId = objectId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x49);
		writeD(_objectId);
	}

	@Override
	public String getType()
	{
		return _S__5B_MAGICSKILLCANCELD;
	}
}