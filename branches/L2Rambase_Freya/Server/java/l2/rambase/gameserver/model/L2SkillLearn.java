/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public final class L2SkillLearn
{
	// these two build the primary key
	private final int _id;
	private final int _level;

	// not needed, just for easier debug
	private final String _name;

	private final int _spCost;
	private final int _minLevel;
	private final int _costid;
	private final int _costcount;

	public L2SkillLearn(int id, int lvl, int minLvl, String name, int cost, int costid, int costcount)
	{
		_id = id;
		_level = lvl;
		_minLevel = minLvl;
		_name = name.intern();
		_spCost = cost;
		_costid = costid;
		_costcount = costcount;
	}

	/**
	 * @return Returns the id.
	 */
	public int getId()
	{
		return _id;
	}

	/**
	 * @return Returns the level.
	 */
	public int getLevel()
	{
		return _level;
	}

	/**
	 * @return Returns the minLevel.
	 */
	public int getMinLevel()
	{
		return _minLevel;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return _name;
	}

	/**
	 * @return Returns the spCost.
	 */
	public int getSpCost()
	{
		return _spCost;
	}
	public int getIdCost()
	{
		return _costid;
	}
	public int getCostCount()
	{
		return _costcount;
	}
}
