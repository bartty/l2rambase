/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class PartySmallWindowAdd extends L2GameServerPacket
{
	private static final String _S__64_PARTYSMALLWINDOWADD = "[S] 4f PartySmallWindowAdd";

	private final L2PcInstance _member;
	private final int _leaderId;
	private final int _distribution;

	public PartySmallWindowAdd(L2PcInstance member, L2Party party)
	{
		_member = member;
		_leaderId = party.getPartyLeaderOID();
		_distribution = party.getLootDistribution();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x4f);
		writeD(_leaderId); // c3
		writeD(_distribution);//writeD(0x04); ?? //c3
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
		writeD(0);
		writeD(0);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__64_PARTYSMALLWINDOWADD;
	}
}