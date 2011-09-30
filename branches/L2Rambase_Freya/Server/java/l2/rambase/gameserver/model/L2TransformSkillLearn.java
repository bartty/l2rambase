/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public final class L2TransformSkillLearn
{
	// these two build the primary key
	private final int _race_id;
	private final int _skill_id;
	private final int _item_id;
	private final int _level;

	// not needed, just for easier debug
	private final String _name;

	private final int _sp;
	private final int _min_level;

	public L2TransformSkillLearn(int race_id, int skill_id, int item_id, int level, String name, int sp, int min_level)
	{
		_race_id = race_id;
		_skill_id = skill_id;
		_item_id = item_id;
		_level = level;
		_name = name.intern();
		_sp = sp;
		_min_level = min_level;
	}

	/**
	 * @return Returns the skill_id.
	 */
	public int getId()
	{
		return _skill_id;
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
		return _min_level;
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
		return _sp;
	}
	public int getRace()
	{
		return _race_id;
	}
	public int getItemId()
	{
		return _item_id;
	}
}
