/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;


public final class L2PledgeSkillLearn
{
    // these two build the primary key
    private final int _id;
    private final int _level;

    // not needed, just for easier debug
    private final String _name;

    private final int _repCost;
    private final int _baseLvl;
    private final int _itemId;
    private final int _itemCount;

    public L2PledgeSkillLearn(int id, int lvl, int baseLvl, String name, int cost, int itemId, int itemCount)
    {
        _id = id;
        _level = lvl;
        _baseLvl = baseLvl;
        _name = name.intern();
        _repCost = cost;
        _itemId = itemId;
        _itemCount = itemCount;
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
    public int getBaseLevel()
    {
        return _baseLvl;
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
    public int getRepCost()
    {
        return _repCost;
    }

    public int getItemId()
    {
        return _itemId;
    }

    public int getItemCount()
    {
    	return _itemCount;
    }
}