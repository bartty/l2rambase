/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExOlympiadUserInfo extends L2GameServerPacket
{
	private static final String _S__FE_29_OLYMPIADUSERINFO = "[S] FE:7A ExOlympiadUserInfo";
	private L2PcInstance _player;

	/**
	 * @param _player
	 * @param _side (1 = right, 2 = left)
	 */
	public ExOlympiadUserInfo(L2PcInstance player)
	{
		_player = player;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x7a);
		writeC(_player.getOlympiadSide());
		writeD(_player.getObjectId());
		writeS(_player.getName());
		writeD(_player.getClassId().getId());
		writeD((int)_player.getCurrentHp());
		writeD(_player.getMaxVisibleHp());
		writeD((int)_player.getCurrentCp());
		writeD(_player.getMaxCp());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_29_OLYMPIADUSERINFO;
	}
}