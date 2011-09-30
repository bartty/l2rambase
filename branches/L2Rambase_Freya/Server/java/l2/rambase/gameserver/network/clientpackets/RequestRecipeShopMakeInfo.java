/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.RecipeShopItemInfo;


public final class RequestRecipeShopMakeInfo extends L2GameClientPacket
{
    private static final String _C__B5_RequestRecipeShopMakeInfo = "[C] b5 RequestRecipeShopMakeInfo";
    

    private int _playerObjectId;
    private int _recipeId;

    @Override
	protected void readImpl()
    {
        _playerObjectId = readD();
        _recipeId = readD();
	}

	@Override
	protected void runImpl()
	{
        L2PcInstance player = getClient().getActiveChar();
        if (player == null)
        	return;


        player.sendPacket(new RecipeShopItemInfo(_playerObjectId,_recipeId));

    }


    @Override
	public String getType()
    {
        return _C__B5_RequestRecipeShopMakeInfo;
    }


}
