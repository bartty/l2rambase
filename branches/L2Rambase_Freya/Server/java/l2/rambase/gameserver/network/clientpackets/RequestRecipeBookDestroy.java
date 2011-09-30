/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.RecipeController;
import l2.rambase.gameserver.model.L2RecipeList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.RecipeBookItemList;

public final class RequestRecipeBookDestroy extends L2GameClientPacket
{
    private static final String _C__AC_REQUESTRECIPEBOOKDESTROY = "[C] AD RequestRecipeBookDestroy";

    private int _recipeID;

    @Override
	protected void readImpl()
    {
        _recipeID = readD();
    }

    @Override
	protected void runImpl()
    {
        L2PcInstance activeChar = getClient().getActiveChar();
        if (activeChar != null)
        {
        	L2RecipeList rp = RecipeController.getInstance().getRecipeList(_recipeID);
         	if (rp == null)
         		return;
            activeChar.unregisterRecipeList(_recipeID);

            RecipeBookItemList response = new RecipeBookItemList(rp.isDwarvenRecipe(),activeChar.getMaxMp());
         	if (rp.isDwarvenRecipe())
         		response.addRecipes(activeChar.getDwarvenRecipeBook());
         	else
         		response.addRecipes(activeChar.getCommonRecipeBook());

            activeChar.sendPacket(response);
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _C__AC_REQUESTRECIPEBOOKDESTROY;
    }
}