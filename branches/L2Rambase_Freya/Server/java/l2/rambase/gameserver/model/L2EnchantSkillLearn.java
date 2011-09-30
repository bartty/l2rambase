/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import gnu.trove.TIntIntHashMap;

import l2.rambase.gameserver.datatables.EnchantGroupsTable; 
import l2.rambase.gameserver.model.L2EnchantSkillGroup.EnchantSkillDetail; 


public final class L2EnchantSkillLearn
{
 	private final int _id; 
 	private final int _baseLvl; 
 	private final TIntIntHashMap _enchantRoutes = new TIntIntHashMap(); 

    public L2EnchantSkillLearn(int id, int baseLvl)
    {
        _id = id;
        _baseLvl = baseLvl;
    }

    public void addNewEnchantRoute(int route, int group) 
    { 
    	_enchantRoutes.put(route, group); 
    } 
    /** 
     * @return Returns the id. 
     */ 
    public int getId() 
    { 
    	return _id; 
    } 
    
    /** 
     * @return Returns the minLevel. 
     */ 
    public int getBaseLevel() 
    { 
    	return _baseLvl; 
    } 
    
    public static int getEnchantRoute(int level) 
    { 
    	return (int) Math.floor(level / 100); 
    } 
    
    public static int getEnchantIndex(int level) 
    { 
    	return (level % 100) - 1; 
    } 
    
    public static int getEnchantType(int level) 
    { 
    	return ((level - 1) / 100) - 1; 
    } 
    
    public L2EnchantSkillGroup getFirstRouteGroup() 
    { 
    	return EnchantGroupsTable.getInstance().getEnchantSkillGroupById(_enchantRoutes.getValues()[0]); 
    } 
    
    public int[] getAllRoutes() 
    { 
    	return _enchantRoutes.keys(); 
    } 
    
    public int getMinSkillLevel(int level) 
    { 
    	if (level % 100 == 1) 
    		return _baseLvl; 
    	return level - 1; 
    } 
    
    public boolean isMaxEnchant(int level) 
    { 
    	int enchantType = L2EnchantSkillLearn.getEnchantRoute(level); 
    	if (enchantType < 1 || !_enchantRoutes.contains(enchantType)) 
    	{ 
    		return false; 
    	} 
    	int index = L2EnchantSkillLearn.getEnchantIndex(level); 
    	
    	if ((index + 1) >= EnchantGroupsTable.getInstance().getEnchantSkillGroupById(_enchantRoutes.get(enchantType)).getEnchantGroupDetails().size()) 
    	{ 
    		return true; 
    	} 
    	return false; 
    } 
    
    public EnchantSkillDetail getEnchantSkillDetail(int level) 
    { 
    	int enchantType = L2EnchantSkillLearn.getEnchantRoute(level); 
    	if (enchantType < 1 || !_enchantRoutes.contains(enchantType)) 
    	{ 
    		return null; 
    	} 
    	int index = L2EnchantSkillLearn.getEnchantIndex(level); 
    	L2EnchantSkillGroup group = EnchantGroupsTable.getInstance().getEnchantSkillGroupById(_enchantRoutes.get(enchantType)); 
    	
    	if (index < 0) 
    	{ 
    		return group.getEnchantGroupDetails().get(0); 
    	} 
    	else if (index >= group.getEnchantGroupDetails().size()) 
    	{ 
    		return group.getEnchantGroupDetails().get(EnchantGroupsTable.getInstance().getEnchantSkillGroupById(_enchantRoutes.get(enchantType)).getEnchantGroupDetails().size() - 1); 
    	} 
    	return group.getEnchantGroupDetails().get(index); 
    }    
}