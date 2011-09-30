/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public final class TradeItem
{
	private int _objectId;
	private int _itemId;
	private long _price;
	private long _storePrice;
	private long _count;
	private int _enchantLevel;
	
	public TradeItem()
	{
	}
	
	public void setObjectId(int id)
	{
		_objectId = id;
	}
	
	public int getObjectId()
	{
		return _objectId;
	}
	
	public void setItemId(int id)
	{
		_itemId = id;
	}
	
	public int getItemId()
	{
		return _itemId;
	}
	
	public void setOwnersPrice(long price)
	{
		_price = price;
	}
	
	public long getOwnersPrice()
	{
		return _price;
	}
	
	public void setstorePrice(long price)
	{
		_storePrice = price;
	}
	
	public long getStorePrice()
	{
		return _storePrice;
	}
	
	public void setCount(long count)
	{
		_count = count;
	}
	
	public long getCount()
	{
		return _count;
	}
	
	public void setEnchantLevel(int enchant)
	{
		_enchantLevel = enchant;
	}
	
	public int getEnchantLevel()
	{
		return _enchantLevel;
	}
	
}
