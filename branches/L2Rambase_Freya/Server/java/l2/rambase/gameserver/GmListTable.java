/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.L2GameServerPacket;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

import javolution.util.FastList;
import javolution.util.FastMap;


public class GmListTable
{
	private static Logger _log = Logger.getLogger(GmListTable.class.getName());
	
	/** Set(L2PcInstance>) containing all the GM in game */
	private FastMap<L2PcInstance, Boolean> _gmList;
	
	public static GmListTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public FastList<L2PcInstance> getAllGms(boolean includeHidden)
	{
		FastList<L2PcInstance> tmpGmList = new FastList<L2PcInstance>();
		
		for (FastMap.Entry<L2PcInstance, Boolean> n = _gmList.head(), end = _gmList.tail(); (n = n.getNext()) != end;)
			if (includeHidden || !n.getValue())
				tmpGmList.add(n.getKey());
		
		return tmpGmList;
	}
	
	public FastList<String> getAllGmNames(boolean includeHidden)
	{
		FastList<String> tmpGmList = new FastList<String>();
		
		for (FastMap.Entry<L2PcInstance, Boolean> n = _gmList.head(), end = _gmList.tail(); (n = n.getNext()) != end;)
		{
			if (!n.getValue())
			{
				String accessName = "";
				switch(n.getKey().getAccessLevel().getLevel())
				{
					case 7:
						accessName = " [Event GM]";
						break;
					case 100:
						accessName = " [Head GM]";
						break;
					case 127:
						accessName = " [Master GM]";
						break;
					default:
						accessName = " [Normal GM]";
						break;
				}
				tmpGmList.add(n.getKey().getName() + accessName);
			}
			else if (includeHidden)
			{
				String accessName = "";
				switch(n.getKey().getAccessLevel().getLevel())
				{
					case 7:
						accessName = " [Event GM]";
						break;
					case 100:
						accessName = " [Head GM]";
						break;
					case 127:
						accessName = " [Master GM]";
						break;
					default:
						accessName = " [Normal GM]";
						break;
				}
				tmpGmList.add(n.getKey().getName() + accessName + " (invis)");
			}
		}
		return tmpGmList;
	}
	
	private GmListTable()
	{
		_gmList = new FastMap<L2PcInstance, Boolean>().shared();
	}
	
	/**
	 * Add a L2PcInstance player to the Set _gmList
	 */
	public void addGm(L2PcInstance player, boolean hidden)
	{
		if (Config.DEBUG)
			_log.fine("added gm: " + player.getName());
		_gmList.put(player, hidden);
	}
	
	public void deleteGm(L2PcInstance player)
	{
		if (Config.DEBUG)
			_log.fine("deleted gm: " + player.getName());
		
		_gmList.remove(player);
	}
	
	/**
	 * GM will be displayed on clients gmlist
	 * @param player
	 */
	public void showGm(L2PcInstance player)
	{
		FastMap.Entry<L2PcInstance, Boolean> gm = _gmList.getEntry(player);
		if (gm != null)
			gm.setValue(false);
	}
	
	/**
	 * GM will no longer be displayed on clients gmlist
	 * @param player
	 */
	public void hideGm(L2PcInstance player)
	{
		FastMap.Entry<L2PcInstance, Boolean> gm = _gmList.getEntry(player);
		if (gm != null)
			gm.setValue(true);
	}
	
	public boolean isGmOnline(boolean includeHidden)
	{
		for (FastMap.Entry<L2PcInstance, Boolean> n = _gmList.head(), end = _gmList.tail(); (n = n.getNext()) != end;)
		{
			if (includeHidden || !n.getValue())
				return true;
		}
		
		return false;
	}
	
	public void sendListToPlayer(L2PcInstance player)
	{
		if (isGmOnline(player.isGM()))
		{
			player.sendPacket(new SystemMessage(SystemMessageId.GM_LIST));
			
			for (String name : getAllGmNames(player.isGM()))
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.GM_C1);
				sm.addString(name);
				player.sendPacket(sm);
			}
		}
		else
			player.sendPacket(new SystemMessage(SystemMessageId.NO_GM_PROVIDING_SERVICE_NOW));
	}
	
	public static void broadcastToGMs(L2GameServerPacket packet)
	{
		for (L2PcInstance gm : getInstance().getAllGms(true))
			gm.sendPacket(packet);
	}
	
	public static void broadcastMessageToGMs(String message)
	{
		for (L2PcInstance gm : getInstance().getAllGms(true))
			gm.sendMessage(message);
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final GmListTable _instance = new GmListTable();
	}
}
