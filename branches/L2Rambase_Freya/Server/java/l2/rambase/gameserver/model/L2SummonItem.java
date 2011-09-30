/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public class L2SummonItem
{
	private final int _itemId;
	private final int  _npcId;
	private final byte  _type;
	private final int  _despawnDelay;

	public L2SummonItem(int itemId, int npcId, byte type, int despawnDelay)
	{
		_itemId = itemId;
		_npcId = npcId;
		_type = type;
		_despawnDelay = despawnDelay;
	}

	public int getItemId()
	{
		return _itemId;
	}

	public int getNpcId()
	{
		return _npcId;
	}

	public byte getType()
	{
		return _type;
	}

	public boolean isPetSummon()
	{
		return _type == 1 || _type == 2;
	}

	public int getDespawnDelay()
	{
		return _despawnDelay;
	}
}
