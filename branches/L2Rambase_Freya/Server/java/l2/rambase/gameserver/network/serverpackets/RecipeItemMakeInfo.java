/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.RecipeController;
import l2.rambase.gameserver.model.L2RecipeList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RecipeItemMakeInfo extends L2GameServerPacket
{
    private static final String _S__D7_RECIPEITEMMAKEINFO = "[S] dd RecipeItemMakeInfo";
    private static Logger _log = Logger.getLogger(RecipeItemMakeInfo.class.getName());

    private int _id;
    private L2PcInstance _activeChar;
    private boolean _success;

    public RecipeItemMakeInfo(int id, L2PcInstance player, boolean success)
    {
        _id = id;
        _activeChar = player;
        _success = success;
    }

    public RecipeItemMakeInfo(int id, L2PcInstance player)
    {
        _id = id;
        _activeChar = player;
        _success = true;
    }

    @Override
	protected final void writeImpl()
    {
        L2RecipeList recipe = RecipeController.getInstance().getRecipeList(_id);

        if (recipe != null)
        {
            writeC(0xdd);

            writeD(_id);
            writeD(recipe.isDwarvenRecipe() ? 0 : 1); // 0 = Dwarven - 1 = Common
            writeD((int) _activeChar.getCurrentMp());
            writeD(_activeChar.getMaxMp());
            writeD(_success ? 1 : 0); // item creation success/failed
        }
        else if (Config.DEBUG) _log.info("No recipe found with ID = " + _id);
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__D7_RECIPEITEMMAKEINFO;
    }
}