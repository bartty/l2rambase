/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.util.List;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.ItemsOnGroundManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.templates.item.L2EtcItemType;

import javolution.util.FastList;

public class ItemsAutoDestroy
{
	protected static final Logger _log = Logger.getLogger(ItemsAutoDestroy.class.getName());
	protected List<L2ItemInstance> _items = null;
	protected static long _sleep;
	
	private ItemsAutoDestroy()
	{
		_log.info("Initializing ItemsAutoDestroy.");
		_items = new FastList<L2ItemInstance>();
		_sleep = Config.AUTODESTROY_ITEM_AFTER * 1000;
		if (_sleep == 0) // it should not happend as it is not called when AUTODESTROY_ITEM_AFTER = 0 but we never know..
			_sleep = 3600000;
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckItemsForDestroy(), 5000, 5000);
	}
	
	public static ItemsAutoDestroy getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public synchronized void addItem(L2ItemInstance item)
	{
		item.setDropTime(System.currentTimeMillis());
		_items.add(item);
	}
	
	public synchronized void removeItems()
	{
		if (Config.DEBUG)
			_log.info("[ItemsAutoDestroy] : " + _items.size() + " items to check.");
		
		if (_items.isEmpty())
			return;
		
		long curtime = System.currentTimeMillis();
		for (L2ItemInstance item : _items)
		{
			if (item == null || item.getDropTime() == 0 || item.getLocation() != L2ItemInstance.ItemLocation.VOID)
				_items.remove(item);
			else
			{
				if (item.getItemType() == L2EtcItemType.HERB)
				{
					if ((curtime - item.getDropTime()) > Config.HERB_AUTO_DESTROY_TIME)
					{
						L2World.getInstance().removeVisibleObject(item, item.getWorldRegion());
						L2World.getInstance().removeObject(item);
						_items.remove(item);
						if (Config.SAVE_DROPPED_ITEM)
							ItemsOnGroundManager.getInstance().removeObject(item);
					}
				}
				else if ((curtime - item.getDropTime()) > _sleep)
				{
					L2World.getInstance().removeVisibleObject(item, item.getWorldRegion());
					L2World.getInstance().removeObject(item);
					_items.remove(item);
					if (Config.SAVE_DROPPED_ITEM)
						ItemsOnGroundManager.getInstance().removeObject(item);
				}
			}
		}
		
		if (Config.DEBUG)
			_log.info("[ItemsAutoDestroy] : " + _items.size() + " items remaining.");
	}
	
	protected class CheckItemsForDestroy extends Thread
	{
		@Override
		public void run()
		{
			removeItems();
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ItemsAutoDestroy _instance = new ItemsAutoDestroy();
	}
}