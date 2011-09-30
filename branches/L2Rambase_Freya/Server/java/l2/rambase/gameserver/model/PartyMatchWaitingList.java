/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.util.List;

import javolution.util.FastList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PartyMatchWaitingList
{
	private List<L2PcInstance> _members;

	private PartyMatchWaitingList()
	{
		_members = new FastList<L2PcInstance>();
	}
	
	public void addPlayer(L2PcInstance player)
	{
		// player.setPartyWait(1);
		if (!_members.contains(player))
			_members.add(player);
	}
	
	public void removePlayer(L2PcInstance player)
	{
		//player.setPartyWait(0);
		if (_members.contains(player))
			_members.remove(player);
	}

	public List<L2PcInstance> getPlayers()
	{
		return _members;
	}
	
	public static PartyMatchWaitingList getInstance()
	{
		return SingletonHolder._instance;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final PartyMatchWaitingList _instance = new PartyMatchWaitingList();
	}	
}