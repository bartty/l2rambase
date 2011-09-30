/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

/**
 * This class describes a RecipeList statUse and altStatChange component.<BR><BR>
 */
public class L2RecipeStatInstance
{
    public static enum StatType
    {
    	HP,
    	MP,
    	XP,
    	SP,
    	GIM // grab item modifier:
    	// GIM: the default function uses only the skilllevel to determine
    	//      how many item is grabbed in each step 
    	//      with this stat changer you can multiple this
    }

	/** The Identifier of the statType */
    private StatType _type;

	/** The value of the statType */
    private int _value;
    
	/**
	 * Constructor of L2RecipeStatInstance.<BR><BR>
	 */
    public L2RecipeStatInstance(String type, int value)
    {
		try {
			_type = Enum.valueOf(StatType.class, type);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
        _value = value;
    }

	/**
	 * Return the the type of the L2RecipeStatInstance.<BR><BR>
	 */
    public StatType getType()
    {
        return _type;
    }

	/**
	 * Return the value of the L2RecipeStatInstance.<BR><BR>
	 */
    public int getValue()
    {
        return _value;
    }

}
