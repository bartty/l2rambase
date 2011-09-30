/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.TradeList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PrivateStoreListSell extends L2GameServerPacket
{
	private static final String _S__B4_PRIVATESTORELISTSELL = "[S] a1 PrivateStoreListSell";
	private int _objId;
	private long _playerAdena;
	private boolean _packageSale;
	private TradeList.TradeItem[] _items;
	
	// player's private shop
	public PrivateStoreListSell(L2PcInstance player, L2PcInstance storePlayer)
	{
		_objId = storePlayer.getObjectId();
		_playerAdena = player.getAdena();
		_items = storePlayer.getSellList().getItems();
		_packageSale = storePlayer.getSellList().isPackaged();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xa1);
		writeD(_objId);
		writeD(_packageSale ? 1 : 0);
		writeQ(_playerAdena);
		writeD(_items.length);
		for (TradeList.TradeItem item : _items)
		{
			writeD(item.getItem().getType2());
			writeD(item.getObjectId());
			writeD(item.getItem().getItemId());
			writeQ(item.getCount());
			writeH(0x00);
			writeH(item.getEnchant());
			writeH(item.getCustomType2());
			writeD(item.getItem().getBodyPart());
			writeQ(item.getPrice()); //your price
			writeQ(item.getItem().getReferencePrice()); //store price
			
			// T1
			writeH(item.getAttackElementType());
			writeH(item.getAttackElementPower());
			for (byte i = 0; i < 6; i++)
				writeH(item.getElementDefAttr(i));
			
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
		return _S__B4_PRIVATESTORELISTSELL;
	}
}