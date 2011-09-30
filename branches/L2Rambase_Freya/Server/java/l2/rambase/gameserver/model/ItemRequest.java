/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public class ItemRequest
{
	int _objectId;
	int _itemId;
	long _count;
	long _price;

	public ItemRequest(int objectId, long count, long price)
	{
		_objectId = objectId;
		_count = count;
		_price = price;
	}

	public ItemRequest(int objectId, int itemId, long count, long price)
	{
		_objectId = objectId;
		_itemId = itemId;
		_count = count;
		_price = price;
	}

	public int getObjectId(){return _objectId;}
	public int getItemId(){return _itemId;}
	public void setCount(long count){_count = count;}
	public long getCount(){return _count;}
	public long getPrice(){return _price;}
	
	/* (non-Javadoc) 
	 * @see java.lang.Object#hashCode() 
	 */ 
	@Override 
	public int hashCode() 
	{ 
		return _objectId; 
	} 
	
	/* (non-Javadoc) 
	 * @see java.lang.Object#equals(java.lang.Object) 
	 */ 
	@Override 
	public boolean equals(Object obj) 
	{ 
		if (this == obj) 
		{ 
			return true; 
		} 
		if (obj == null) 
		{ 
			return false; 
		} 
		if (getClass() != obj.getClass()) 
		{ 
			return false; 
		} 
		ItemRequest other = (ItemRequest) obj; 
		if (_objectId != other._objectId) 
		{ 
			return false; 
		} 
		return true; 
	} 
}
