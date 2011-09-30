/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class GMSkillTable
{
	private static final L2Skill[] _gmSkills = new L2Skill[33];
	private static final int[] _gmSkillsId = { 7041, 7042, 7043, 7044, 7045, 7046, 7047, 7048, 7049, 7050, 7051, 7052, 7053, 7054, 7055, 7056, 7057, 7058, 7059, 7060, 7061, 7062, 7063, 7064, 7088, 7089, 7090, 7091, 7092, 7093, 7094 ,7095, 7096 };
	
	private GMSkillTable()
	{
		for (int i = 0; i < _gmSkillsId.length; i++)
		{
			_gmSkills[i] = SkillTable.getInstance().getInfo(_gmSkillsId[i], 1);
		}
	}
	
	public static GMSkillTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public static L2Skill[] getGMSkills()
	{
		return _gmSkills;
	}
	
	public static boolean isGMSkill(int skillid)
	{
		for (int id : _gmSkillsId)
		{
			if (id == skillid)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void addSkills(L2PcInstance gmchar)
	{
		for (L2Skill s : getGMSkills())
		{
			gmchar.addSkill(s, false); // Don't Save GM skills to database
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final GMSkillTable _instance = new GMSkillTable();
	}
}
