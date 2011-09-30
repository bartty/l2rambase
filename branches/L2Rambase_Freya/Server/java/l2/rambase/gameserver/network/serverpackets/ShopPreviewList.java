/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.Collection;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2TradeList;
import l2.rambase.gameserver.model.L2TradeList.L2TradeItem;
import l2.rambase.gameserver.templates.item.L2Item;


public class ShopPreviewList extends L2GameServerPacket
{
	private static final String _S__EF_WEARLIST = "[S] f5 WearList";
	private int _listId;
    private Collection<L2TradeItem> _list;
	private long _money;
	private int _expertise;

	public ShopPreviewList(L2TradeList list, long currentMoney, int expertiseIndex)
	{
		_listId = list.getListId();
		_list = list.getItems();
		_money = currentMoney;
		_expertise = expertiseIndex;
	}

	public ShopPreviewList(Collection<L2TradeItem> lst, int listId, long currentMoney)
	{
		_listId = listId;
		_list = lst;
		_money = currentMoney;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xf5);
		writeC(0xc0);	// ?
		writeC(0x13);	// ?
		writeC(0x00);	// ?
		writeC(0x00);	// ?
		writeQ(_money);		// current money
		writeD(_listId);

		int newlength = 0;
		for (L2TradeItem item : _list)
        {
		    if (item.getTemplate().getCrystalType() <= _expertise && item.getTemplate().isEquipable())
			newlength++;
		}
		writeH(newlength);

		for (L2TradeItem item : _list)
		{
		    if (item.getTemplate().getCrystalType() <= _expertise && item.getTemplate().isEquipable())
		    {
		        writeD(item.getItemId());
		        writeH(item.getTemplate().getType2());	// item type2
		        
		        if (item.getTemplate().getType1() != L2Item.TYPE1_ITEM_QUESTITEM_ADENA)
		        {
		            writeH(item.getTemplate().getBodyPart());	// rev 415  slot    0006-lr.ear  0008-neck  0030-lr.finger  0040-head  0080-??  0100-l.hand  0200-gloves  0400-chest  0800-pants  1000-feet  2000-??  4000-r.hand  8000-r.hand
		        }
		        else
		        {
		            writeH(0x00);	// rev 415  slot    0006-lr.ear  0008-neck  0030-lr.finger  0040-head  0080-??  0100-l.hand  0200-gloves  0400-chest  0800-pants  1000-feet  2000-??  4000-r.hand  8000-r.hand
		        }
		        
		        writeQ(Config.WEAR_PRICE);
		    }
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__EF_WEARLIST;
	}
}