/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public class L2TeleportLocation
{
	private int _teleId;
	private int _locX;
	private int _locY;
	private int _locZ;
	private int _price;
	private boolean _forNoble;
	private int _itemId;


	/**
	 * @param id
	 */
	public void setTeleId(int id)
	{
		_teleId = id;
	}

	/**
	 * @param locX
	 */
	public void setLocX(int locX)
	{
		_locX = locX;
	}

	/**
	 * @param locY
	 */
	public void setLocY(int locY)
	{
		_locY = locY;
	}

	/**
	 * @param locZ
	 */
	public void setLocZ(int locZ)
	{
		_locZ = locZ;
	}

	/**
	 * @param price
	 */
	public void setPrice(int price)
	{
		_price = price;
	}

	/**
	 * @param val
	 */
	public void setIsForNoble(boolean val)
	{
		_forNoble = val;
	}

	/**
	 * @param val
	 */
	public void setItemId(int val)
	{
		_itemId = val;
	}

	/**
	 * @return
	 */
	public int getTeleId()
	{
		return _teleId;
	}

	/**
	 * @return
	 */
	public int getLocX()
	{
		return _locX;
	}

	/**
	 * @return
	 */
	public int getLocY()
	{
		return _locY;
	}

	/**
	 * @return
	 */
	public int getLocZ()
	{
		return _locZ;
	}

	/**
	 * @return
	 */
	public int getPrice()
	{
		return _price;
	}

	/**
	 * @return
	 */
	public boolean getIsForNoble()
	{
		return _forNoble;
	}

	/**
	 * @return
	 */
	public int getItemId()
	{
		return _itemId;
	}
}
