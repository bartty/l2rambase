/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.itemcontainer;

import java.util.logging.Level;

import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2ItemInstance.ItemLocation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PcRefund extends ItemContainer
{
	private L2PcInstance _owner;

	public PcRefund(L2PcInstance owner)
	{
		_owner = owner;
	}

	@Override
	public String getName() 
	{
		return "Refund";
	}

	@Override
	public L2PcInstance getOwner()
	{
		return _owner;
	}

	@Override
	public ItemLocation getBaseLocation()
	{
		return ItemLocation.REFUND;
	}

	@Override
	protected void addItem(L2ItemInstance item)
	{
		super.addItem(item);
		try
		{
			if (getSize() > 12)
			{
				L2ItemInstance removedItem;
				synchronized (_items)
				{
					removedItem = _items.remove(0);
				}
				if (removedItem != null)
				{
					ItemTable.getInstance().destroyItem("ClearRefund", removedItem, getOwner(), null);
					removedItem.updateDatabase(true);
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "addItem()", e);
		}
	}

	@Override
	public void refreshWeight()
	{
	}

	@Override
	public void deleteMe()
	{
		try
		{
			for (L2ItemInstance item : _items)
			{
				if (item != null)
				{
					ItemTable.getInstance().destroyItem("ClearRefund", item, getOwner(), null);
					item.updateDatabase(true);
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "deleteMe()", e);
		}
		_items.clear();
	}

	@Override
	public void restore()
	{
	}
}