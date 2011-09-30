/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: ch Sddddddddd
 */
public class ExDuelUpdateUserInfo extends L2GameServerPacket
{
	private static final String _S__FE_4F_EXDUELUPDATEUSERINFO = "[S] FE:50 ExDuelUpdateUserInfo";
	private L2PcInstance _activeChar;

	public ExDuelUpdateUserInfo(L2PcInstance cha)
	{
		_activeChar = cha;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x50);
		writeS(_activeChar.getName());
		writeD(_activeChar.getObjectId());
		writeD(_activeChar.getClassId().getId());
		writeD(_activeChar.getLevel());
		writeD((int)_activeChar.getCurrentHp());
		writeD(_activeChar.getMaxVisibleHp());
		writeD((int)_activeChar.getCurrentMp());
		writeD(_activeChar.getMaxMp());
		writeD((int)_activeChar.getCurrentCp());
		writeD(_activeChar.getMaxCp());
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4F_EXDUELUPDATEUSERINFO;
	}
}