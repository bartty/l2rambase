/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.Collection;
import java.util.List;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2TradeList;
import l2.rambase.gameserver.model.L2TradeList.L2TradeItem;
import l2.rambase.gameserver.templates.item.L2Item;


public final class BuyList extends L2GameServerPacket
{
	private static final String _S__1D_BUYLIST = "[S] 07 BuyList";
	private int _listId;
	private Collection<L2TradeItem> _list;
	private long _money;
	private double _taxRate = 0;

	public BuyList(L2TradeList list, long currentMoney)
	{
		_listId = list.getListId();
		_list = list.getItems();
		_money = currentMoney;
	}

	public BuyList(L2TradeList list, long currentMoney, double taxRate)
	{
		_listId = list.getListId();
        _list = list.getItems();
		_money = currentMoney;
		_taxRate = taxRate;
	}

	public BuyList(List<L2TradeItem> lst, int listId, long currentMoney)
	{
		_listId = listId;
		_list = lst;
		_money = currentMoney;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x07);
		writeQ(_money);		// current money
		writeD(_listId);

		writeH(_list.size());

		for (L2TradeItem item : _list)
		{
			if (item.getCurrentCount() > 0 || !item.hasLimitedStock())
            {
				writeH(item.getTemplate().getType1()); // item type1
				writeD(0x00); //objectId
				writeD(item.getItemId());
				writeQ(item.getCurrentCount() <0 ? 0 : item.getCurrentCount());
				writeH(item.getTemplate().getType2());	// item type2
				writeH(0x00);	// ?

				if (item.getTemplate().getType1() != L2Item.TYPE1_ITEM_QUESTITEM_ADENA)
				{
					writeD(item.getTemplate().getBodyPart());
					writeH(0x00); // item enchant level
					writeH(0x00); // 
					writeH(0x00);
				}
				else
				{
					writeD(0x00);
					writeH(0x00);
					writeH(0x00);
					writeH(0x00);
				}

	            if (item.getItemId() >= 3960 && item.getItemId() <= 4026)//Config.RATE_SIEGE_GUARDS_PRICE-//'
	                writeQ((long) (item.getPrice() * Config.RATE_SIEGE_GUARDS_PRICE * (1 + _taxRate)));
	            else
	                writeQ((long) (item.getPrice() * (1 + _taxRate)));
                
                // T1
				for (byte i = 0; i < 8; i++)
					writeH(0x00);

				writeH(0x00); // Enchant effect 1
				writeH(0x00); // Enchant effect 2
				writeH(0x00); // Enchant effect 3 
			}
		}
	}
    
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__1D_BUYLIST;
	}
}
