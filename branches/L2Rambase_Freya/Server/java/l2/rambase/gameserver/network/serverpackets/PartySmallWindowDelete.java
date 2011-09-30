/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class PartySmallWindowDelete extends L2GameServerPacket
{
	private static final String _S__66_PARTYSMALLWINDOWDELETE = "[S] 51 PartySmallWindowDelete";
	private L2PcInstance _member;

	public PartySmallWindowDelete(L2PcInstance member)
	{
		_member = member;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x51);
		writeD(_member.getObjectId());
		writeS(_member.getName());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__66_PARTYSMALLWINDOWDELETE;
	}
}