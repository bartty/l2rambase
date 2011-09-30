/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import l2.rambase.gameserver.templates.StatsSet;
import l2.rambase.gameserver.templates.item.L2Item;


public class Item
{
	public int id;
	
	@SuppressWarnings("unchecked")
    public Enum type;
	
	public String name;
	
	public StatsSet set;
	
	public int currentLevel;
	
	public L2Item item;
}
