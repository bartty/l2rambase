/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public class L2ExtractableProductItem
{
	private final int[] _id;
	private final int[] _ammount;
	private final int _chance;

	public L2ExtractableProductItem(int[] id, int[] ammount, int chance)
	{
		_id = id;
		_ammount = ammount;
		_chance = chance;
	}

	public int[] getId()
	{
		return _id;
	}

	public int[] getAmmount()
	{
		return _ammount;
	}

	public int getChance()
	{
		return _chance;
	}
}
