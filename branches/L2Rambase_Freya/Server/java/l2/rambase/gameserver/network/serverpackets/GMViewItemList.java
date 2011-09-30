/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;


public class GMViewItemList extends L2GameServerPacket
{	
	private static final String _S__AD_GMVIEWITEMLIST = "[S] 9a GMViewItemList";
	private L2ItemInstance[] _items;
	private int _limit;
	private String _playerName;
	
	public GMViewItemList(L2PcInstance cha)
	{
		_items = cha.getInventory().getItems();
		_playerName = cha.getName();
		_limit = cha.getInventoryLimit();
	}
	
	public GMViewItemList(L2PetInstance cha)
	{
		_items = cha.getInventory().getItems();
		_playerName = cha.getName();
		_limit = cha.getInventoryLimit();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x9a);
		writeS(_playerName);
		writeD(_limit); // inventory limit
		writeH(0x01); // show window ??
		writeH(_items.length);
		
		for (L2ItemInstance temp : _items)
		{
			writeH(temp.getItem().getType1());
			
			writeD(temp.getObjectId());
			writeD(temp.getItemId());
			writeD(temp.getLocationSlot()); // T1
			writeQ(temp.getCount());
			writeH(temp.getItem().getType2());
			writeH(temp.getCustomType1());
			writeH(temp.isEquipped() ? 0x01 : 0x00);
			writeD(temp.getItem().getBodyPart());
			writeH(temp.getEnchantLevel());
			writeH(temp.getCustomType2());
			if (temp.isAugmented())
				writeD(temp.getAugmentation().getAugmentationId());
			else
				writeD(0x00);
			writeD(temp.getMana());
			
			// T1
			writeH(temp.getAttackElementType());
			writeH(temp.getAttackElementPower());
			for (byte i = 0; i < 6; i++)
				writeH(temp.getElementDefAttr(i));

			// T2
			writeD(temp.isTimeLimitedItem() ? (int) (temp.getRemainingTime()/1000) : -1);

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
		return _S__AD_GMVIEWITEMLIST;
	}
}