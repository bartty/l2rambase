/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class PartySmallWindowUpdate extends L2GameServerPacket
{
	private static final String _S__67_PARTYSMALLWINDOWUPDATE = "[S] 52 PartySmallWindowUpdate";
	private L2PcInstance _member;

	public PartySmallWindowUpdate(L2PcInstance member)
	{
		_member = member;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x52);
		writeD(_member.getObjectId());
		writeS(_member.getName());

		writeD((int) _member.getCurrentCp()); //c4
		writeD(_member.getMaxCp()); //c4

		writeD((int) _member.getCurrentHp());
		writeD(_member.getMaxVisibleHp());
		writeD((int) _member.getCurrentMp());
		writeD(_member.getMaxMp());
		writeD(_member.getLevel());
		writeD(_member.getClassId().getId());

	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__67_PARTYSMALLWINDOWUPDATE;
	}
}