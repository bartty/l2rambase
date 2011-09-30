/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.util.Map;

import l2.rambase.gameserver.model.actor.instance.L2ControllableMobInstance;

import javolution.util.FastMap;


public class MobGroupTable
{
	private Map<Integer, MobGroup> _groupMap;
	
	public static final int FOLLOW_RANGE = 300;
	public static final int RANDOM_RANGE = 300;
	
	private MobGroupTable()
	{
		_groupMap = new FastMap<Integer, MobGroup>();
	}
	
	public static MobGroupTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public void addGroup(int groupKey, MobGroup group)
	{
		_groupMap.put(groupKey, group);
	}
	
	public MobGroup getGroup(int groupKey)
	{
		return _groupMap.get(groupKey);
	}
	
	public int getGroupCount()
	{
		return _groupMap.size();
	}
	
	public MobGroup getGroupForMob(L2ControllableMobInstance mobInst)
	{
		for (MobGroup mobGroup : _groupMap.values())
			if (mobGroup.isGroupMember(mobInst))
				return mobGroup;
		
		return null;
	}
	
	public MobGroup[] getGroups()
	{
		return _groupMap.values().toArray(new MobGroup[getGroupCount()]);
	}
	
	public boolean removeGroup(int groupKey)
	{
		return (_groupMap.remove(groupKey) != null);
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final MobGroupTable _instance = new MobGroupTable();
	}
}