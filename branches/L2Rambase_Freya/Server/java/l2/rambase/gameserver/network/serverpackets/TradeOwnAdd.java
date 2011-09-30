/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.TradeList;


public final class TradeOwnAdd extends L2GameServerPacket
{
	private static final String _S__30_TRADEOWNADD = "[S] 1a TradeOwnAdd";
	private TradeList.TradeItem _item;
	
	public TradeOwnAdd(TradeList.TradeItem item)
	{
		_item = item;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x1a);
		
		writeH(1); // items added count
		
		writeH(_item.getItem().getType1()); // item type1
		writeD(_item.getObjectId());
		writeD(_item.getItem().getItemId());
		writeQ(_item.getCount());
		writeH(_item.getItem().getType2()); // item type2
		writeH(0x00); // ?
		
		writeD(_item.getItem().getBodyPart()); // rev 415  slot    0006-lr.ear  0008-neck  0030-lr.finger  0040-head  0080-??  0100-l.hand  0200-gloves  0400-chest  0800-pants  1000-feet  2000-??  4000-r.hand  8000-r.hand
		writeH(_item.getEnchant()); // enchant level
		writeH(0x00);
		writeH(_item.getCustomType2());
		
		// T1
		writeH(_item.getAttackElementType());
		writeH(_item.getAttackElementPower());
		for (byte i = 0; i < 6; i++)
			writeH(_item.getElementDefAttr(i));
		
		writeH(0x00); // Enchant effect 1
		writeH(0x00); // Enchant effect 2
		writeH(0x00); // Enchant effect 3 
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__30_TRADEOWNADD;
	}	
}