/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class GMViewSkillInfo extends L2GameServerPacket
{
	private static final String _S__91_GMViewSkillInfo = "[S] 97 GMViewSkillInfo";
	private L2PcInstance _activeChar;
	private L2Skill[] _skills;

	public GMViewSkillInfo (L2PcInstance cha)
	{
		_activeChar = cha;
		_skills = _activeChar.getAllSkills();
		if (_skills.length == 0)
			_skills = new L2Skill[0];
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x97);
		writeS(_activeChar.getName());
		writeD(_skills.length);

		boolean isDisabled = false;
		if (_activeChar.getClan() != null)
			isDisabled = _activeChar.getClan().getReputationScore() < 0;

		for (L2Skill skill: _skills)
		{
			writeD(skill.isPassive() ? 1 : 0);
			writeD(skill.getLevel());
			writeD(skill.getId());
			writeC(isDisabled && skill.isClanSkill() ? 1 : 0);
			writeC(SkillTable.getInstance().isEnchantable(skill.getId()) ? 1 : 0);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__91_GMViewSkillInfo;
	}
}