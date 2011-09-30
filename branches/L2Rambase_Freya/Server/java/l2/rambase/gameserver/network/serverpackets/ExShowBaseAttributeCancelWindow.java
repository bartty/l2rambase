/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.templates.item.L2Item;
import l2.rambase.gameserver.templates.item.L2Weapon;

public class ExShowBaseAttributeCancelWindow extends L2GameServerPacket
{
	private static final String		_S__FE_74_EXCSHOWBASEATTRIBUTECANCELWINDOW = "[S] FE:74 ExShowBaseAttributeCancelWindow";

	private L2ItemInstance[]		_items;
	private long					_price;

	public ExShowBaseAttributeCancelWindow(L2PcInstance player)
	{
		_items = player.getInventory().getElementItems();
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x74);
		writeD(_items.length);
		for (L2ItemInstance item : _items)
		{
			writeD(item.getObjectId());
			writeQ(getPrice(item));
		}
	}

	private long getPrice(L2ItemInstance item)
	{
		switch(item.getItem().getCrystalType())
		{
			case L2Item.CRYSTAL_S:
				if (item.getItem() instanceof L2Weapon)
					_price = 50000;
				else
					_price = 40000;
				break;
			case L2Item.CRYSTAL_S80:
				if (item.getItem() instanceof L2Weapon)
					_price = 100000;
				else
					_price = 80000;
				break;
			case L2Item.CRYSTAL_S84:
				if (item.getItem() instanceof L2Weapon)
					_price = 200000;
				else
					_price = 160000;
				break;
		}

		return _price;
	}

	@Override
	public String getType()
	{
		return _S__FE_74_EXCSHOWBASEATTRIBUTECANCELWINDOW;
	}
}