/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ClanMember;


public class PledgeReceivePowerInfo extends L2GameServerPacket
{
	private static final String _S__FE_3C_PLEDGERECEIVEPOWERINFO = "[S] FE:3D PledgeReceivePowerInfo";
	private L2ClanMember _member;

	/**
	 * @param member
	 */
	public PledgeReceivePowerInfo(L2ClanMember member)
	{
		_member = member;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x3d);

		writeD(_member.getPowerGrade()); //power grade
		writeS(_member.getName());
		writeD(_member.getClan().getRankPrivs(_member.getPowerGrade())); //privileges
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_3C_PLEDGERECEIVEPOWERINFO;
	}
}