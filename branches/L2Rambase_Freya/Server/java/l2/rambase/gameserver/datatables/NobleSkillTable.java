/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import l2.rambase.gameserver.model.L2Skill;


public class NobleSkillTable
{
	private static final L2Skill[] _nobleSkills = new L2Skill[8];
	
	private NobleSkillTable()
	{
		_nobleSkills[0] = SkillTable.getInstance().getInfo(1323, 1);
		_nobleSkills[1] = SkillTable.getInstance().getInfo(325, 1);
		_nobleSkills[2] = SkillTable.getInstance().getInfo(326, 1);
		_nobleSkills[3] = SkillTable.getInstance().getInfo(327, 1);
		_nobleSkills[4] = SkillTable.getInstance().getInfo(1324, 1);
		_nobleSkills[5] = SkillTable.getInstance().getInfo(1325, 1);
		_nobleSkills[6] = SkillTable.getInstance().getInfo(1326, 1);
		_nobleSkills[7] = SkillTable.getInstance().getInfo(1327, 1);
	}
	
	public static NobleSkillTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public L2Skill[] getNobleSkills()
	{
		return _nobleSkills;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final NobleSkillTable _instance = new NobleSkillTable();
	}
}
