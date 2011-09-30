/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.RecipeController;


public class L2ManufactureItem
{
    private int _recipeId;
    private long _cost;
    private boolean _isDwarven;

    public L2ManufactureItem(int recipeId, long cost)
    {
        _recipeId = recipeId;
        _cost = cost;

        _isDwarven = RecipeController.getInstance().getRecipeList(_recipeId).isDwarvenRecipe();
    }

    public int getRecipeId()
    {
        return _recipeId;
    }

    public long getCost()
    {
        return _cost;
    }

    public boolean isDwarven()
    {
    	return _isDwarven;
    }
}
