/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class Dice extends L2GameServerPacket
{
	private static final String _S__D4_Dice = "[S] da Dice";
	private int _charObjId;
	private int _itemId;
	private int _number;
	private int _x;
	private int _y;
	private int _z;

	/**
	 * 0xd4 Dice         dddddd
	 * @param _characters
	 */
	public Dice(int charObjId, int itemId, int number, int x , int y , int z)
	{
		_charObjId = charObjId;
		_itemId = itemId;
		_number = number;
		_x =x;
		_y =y;
		_z =z;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xda);
		writeD(_charObjId);  //object id of player
		writeD(_itemId);     //	item id of dice (spade)  4625,4626,4627,4628
		writeD(_number);      //number rolled
		writeD(_x);       //x
		writeD(_y);       //y
		writeD(_z);     //z
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D4_Dice;
	}
}