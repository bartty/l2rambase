/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap;

import l2.rambase.gameserver.templates.skills.L2SkillType;


public class SkillHandler
{
	private TIntObjectHashMap<ISkillHandler> _datatable;
	
	public static SkillHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private SkillHandler()
	{
		_datatable = new TIntObjectHashMap<ISkillHandler>();
	}
	
	public void registerSkillHandler(ISkillHandler handler)
	{
		L2SkillType[] types = handler.getSkillIds();
		for (L2SkillType t : types)
		{
			_datatable.put(t.ordinal(), handler);
		}
	}
	
	public ISkillHandler getSkillHandler(L2SkillType skillType)
	{
		return _datatable.get(skillType.ordinal());
	}
	
	/**
	 * @return
	 */
	public int size()
	{
		return _datatable.size();
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final SkillHandler _instance = new SkillHandler();
	}	
}
