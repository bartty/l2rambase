/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap;

import l2.rambase.gameserver.templates.item.L2EtcItem;


public class ItemHandler
{
	private TIntObjectHashMap<IItemHandler> _datatable;
	
	/**
	 * Create ItemHandler if doesn't exist and returns ItemHandler
	 * @return ItemHandler
	 */
	public static ItemHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	/**
	 * Returns the number of elements contained in datatable
	 * @return int : Size of the datatable
	 */
	public int size()
	{
		return _datatable.size();
	}
	
	/**
	 * Constructor of ItemHandler
	 */
	private ItemHandler()
	{
		_datatable = new TIntObjectHashMap<IItemHandler>();
	}
	
	/**
	 * Adds handler of item type in <I>datatable</I>.<BR><BR>
	 * <B><I>Concept :</I></U><BR>
	 * This handler is put in <I>datatable</I> Map &lt;String ; IItemHandler &gt; for each ID corresponding to an item type
	 * (existing in classes of package itemhandlers) sets as key of the Map.
	 * @param handler (IItemHandler)
	 */
	public void registerItemHandler(IItemHandler handler)
	{
		_datatable.put(handler.getClass().getSimpleName().intern().hashCode(), handler);
	}
	
	/**
	 * Returns the handler of the item
	 * @param itemId : int designating the itemID
	 * @return IItemHandler
	 */
	public IItemHandler getItemHandler(L2EtcItem item)
	{
		if (item == null)
			return null;
		return _datatable.get(item.getHandlerName().hashCode());
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ItemHandler _instance = new ItemHandler();
	}
}
