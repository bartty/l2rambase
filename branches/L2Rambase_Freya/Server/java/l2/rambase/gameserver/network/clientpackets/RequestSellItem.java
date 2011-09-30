/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.List;

import l2.rambase.Config;
import l2.rambase.gameserver.TradeController;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2TradeList;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2MerchantInstance;
import l2.rambase.gameserver.model.actor.instance.L2MerchantSummonInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.ExBuySellListPacket;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.util.Util;

import static l2.rambase.gameserver.model.actor.L2Npc.INTERACTION_DISTANCE;
import static l2.rambase.gameserver.model.itemcontainer.PcInventory.MAX_ADENA;


public final class RequestSellItem extends L2GameClientPacket
{
	private static final String _C__1E_REQUESTSELLITEM = "[C] 1E RequestSellItem";
    
	private static final int BATCH_LENGTH = 16; // length of the one item

	private int _listId;
	private Item[] _items = null;

	/**
	 * packet type id 0x1e
	 *
	 * sample
	 *
	 * 1e
	 * 00 00 00 00		// list id
	 * 02 00 00 00		// number of items
	 *
	 * 71 72 00 10		// object id
	 * ea 05 00 00		// item id
	 * 01 00 00 00		// item count
	 *
	 * 76 4b 00 10		// object id
	 * 2e 0a 00 00		// item id
	 * 01 00 00 00		// item count
	 *
	 * format:		cdd (ddd)
	 * @param decrypt
	 */

	@Override
	protected void readImpl()
	{
		_listId = readD();
		int count = readD();
		if (count <= 0
				|| count > Config.MAX_ITEM_IN_PACKET
				|| count * BATCH_LENGTH != _buf.remaining())
		{
		    return;
		}

		_items = new Item[count];
		for (int i = 0; i < count; i++)
		{
			int objectId = readD();
			int itemId = readD();
			long cnt = readQ();
			if (objectId < 1 || itemId < 1 || cnt < 1)
			{
				_items = null;
				return;
			}
			_items[i] = new Item(objectId, itemId, cnt);
		}
	}

	@Override
    protected void runImpl()
    {
	    this.processSell();
    }
    
	protected void processSell()
	{
		L2PcInstance player = getClient().getActiveChar();
        
		if (player == null)
			return;

		if (!player.getFloodProtectors().getTransaction().tryPerformAction("buy"))
		{
			player.sendMessage("You buying too fast.");
			return;
		}

		if(_items == null)
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Alt game - Karma punishment
		if (!Config.ALT_GAME_KARMA_PLAYER_CAN_SHOP && player.getKarma() > 0)
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		L2Object target = player.getTarget();
		if (!player.isGM() && (target == null								// No target (ie GM Shop)
				|| !(target instanceof L2MerchantInstance
						|| target instanceof L2MerchantSummonInstance)	// Target not a merchant
						|| target.getInstanceId() != player.getInstanceId()
						|| !player.isInsideRadius(target, INTERACTION_DISTANCE, true, false))) // Distance is too far
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		L2Character merchant = null;
		double taxRate = 0;
		if (target instanceof L2MerchantInstance
				|| target instanceof L2MerchantSummonInstance)
			merchant = (L2Character)target;
		else if (!player.isGM())
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		L2TradeList list = null;
		if (merchant != null)
		{
			List<L2TradeList> lists;
			if (merchant instanceof L2MerchantInstance)
			{
				lists = TradeController.getInstance().getBuyListByNpcId(((L2MerchantInstance)merchant).getNpcId());
				taxRate = ((L2MerchantInstance)merchant).getMpc().getTotalTaxRate();
			}
			else
			{
				lists = TradeController.getInstance().getBuyListByNpcId(((L2MerchantSummonInstance)merchant).getNpcId());
				taxRate = 50;
			}

			if(!player.isGM() )
			{
				if (lists == null)
				{
					Util.handleIllegalPlayerAction(player,"Warning!! Character "+player.getName()+" of account "+player.getAccountName()+" sent a false BuyList list_id.",Config.DEFAULT_PUNISH);
					return;
				}
				for (L2TradeList tradeList : lists)
				{
					if (tradeList.getListId() == _listId)
						list = tradeList;
				}
			}
			else
				list = TradeController.getInstance().getBuyList(_listId);
		}
		else
			list = TradeController.getInstance().getBuyList(_listId);

		if (list == null)
		{
			Util.handleIllegalPlayerAction(player,"Warning!! Character "+player.getName()+" of account "+player.getAccountName()+" sent a false BuyList list_id.",Config.DEFAULT_PUNISH);
			return;
		}

		long totalPrice = 0;
		// Proceed the sell
		for (Item i : _items)
		{
			L2ItemInstance item = player.checkItemManipulation(i.getObjectId(), i.getCount(), "sell");
			if (item == null || (!item.isSellable()))
				continue;

			long price = item.getReferencePrice() / 2;
			totalPrice += price * i.getCount();
			if ((MAX_ADENA / i.getCount()) < price || totalPrice > MAX_ADENA)
			{
				Util.handleIllegalPlayerAction(player,"Warning!! Character "+player.getName()+" of account "+player.getAccountName()+" tried to purchase over "+MAX_ADENA+" adena worth of goods.",  Config.DEFAULT_PUNISH);
				return;
			}

			if (Config.ALLOW_REFUND)
				item = player.getInventory().transferItem("Sell", i.getObjectId(), i.getCount(), player.getRefund(), player, merchant);
			else
				item = player.getInventory().destroyItem("Sell", i.getObjectId(), i.getCount(), player, merchant);
		}
		player.addAdena("Sell", totalPrice, merchant, false);

		// Update current load as well
		StatusUpdate su = new StatusUpdate(player.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
		player.sendPacket(su);
		player.sendPacket(new ExBuySellListPacket(player, list, taxRate, true));
	}

	private class Item
	{
		private final int _objectId;
		private final int _itemId;
		private final long _count;
		
		public Item(int objId, int id, long num)
		{
			_objectId = objId;
			_itemId = id;
			_count = num;
		}

		public int getObjectId()
		{
			return _objectId;
		}

		@SuppressWarnings("unused")
		public int getItemId()
		{
			return _itemId;
		}

		public long getCount()
		{
			return _count;
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__1E_REQUESTSELLITEM;
	}
}