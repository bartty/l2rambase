/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Skill;


public class PledgeSkillList extends L2GameServerPacket
{
	private static final String _S__FE_39_PLEDGESKILLLIST = "[S] FE:3a PledgeSkillList";
	private L2Skill[] _skills;

	public PledgeSkillList(L2Clan clan)
	{
        _skills = clan.getAllSkills();
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x3a);
		writeD(_skills.length);
        writeD(0x00);
		for(L2Skill sk : _skills)
		{
			writeD(sk.getId());
			writeD(sk.getLevel());
		}
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_39_PLEDGESKILLLIST;
	}
}