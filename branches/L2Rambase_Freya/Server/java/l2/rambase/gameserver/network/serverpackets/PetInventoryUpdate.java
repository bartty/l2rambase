/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.ItemInfo;
import l2.rambase.gameserver.model.L2ItemInstance;

import javolution.util.FastList;


public class PetInventoryUpdate extends L2GameServerPacket
{
	private static Logger _log = Logger.getLogger(InventoryUpdate.class.getName());
	private static final String _S__37_INVENTORYUPDATE = "[S] b4 InventoryUpdate";
	private List<ItemInfo> _items;

	/**
	 * @param items
	 */
	public PetInventoryUpdate(List<ItemInfo> items)
	{
		_items = items;
		if (Config.DEBUG)
		{
			showDebug();
		}
	}

	public PetInventoryUpdate()
	{
		this(new FastList<ItemInfo>());
	}

	public void addItem(L2ItemInstance item) { _items.add(new ItemInfo(item)); }
	public void addNewItem(L2ItemInstance item) { _items.add(new ItemInfo(item, 1)); }
	public void addModifiedItem(L2ItemInstance item) { _items.add(new ItemInfo(item, 2)); }
	public void addRemovedItem(L2ItemInstance item) { _items.add(new ItemInfo(item, 3)); }
	public void addItems(List<L2ItemInstance> items) { for (L2ItemInstance item : items) _items.add(new ItemInfo(item)); }

	private void showDebug()
	{
		for (ItemInfo item : _items)
		{
			_log.fine("oid:" + Integer.toHexString(item.getObjectId()) +
					" item:" + item.getItem().getName()+" last change:" + item.getChange());
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xb4);
		int count = _items.size();
		writeH(count);
		for (ItemInfo item : _items)
		{
			writeH(item.getChange());
			writeH(item.getItem().getType1()); // item type1
			writeD(item.getObjectId());
			writeD(item.getItem().getItemId());
			writeQ(item.getCount());
			writeH(item.getItem().getType2());	// item type2
			writeH(0x00);	// ?
			writeH(item.getEquipped());
			writeD(item.getItem().getBodyPart());	// rev 415   slot    0006-lr.ear  0008-neck  0030-lr.finger  0040-head  0080-??  0100-l.hand  0200-gloves  0400-chest  0800-pants  1000-feet  2000-??  4000-r.hand  8000-r.hand
			writeH(item.getEnchant());	// enchant level
			writeH(0x00);	// ?

			writeH(item.getAttackElementType());
			writeH(item.getAttackElementPower());
			for (byte i = 0; i < 6; i++)
			{
				writeH(item.getElementDefAttr(i));
			}

			writeH(0x00); // Enchant effect 1
			writeH(0x00); // Enchant effect 2
			writeH(0x00); // Enchant effect 3 
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__37_INVENTORYUPDATE;
	}
}