/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RecipeShopItemInfo  extends L2GameServerPacket
{

	private static final String _S__DA_RecipeShopItemInfo = "[S] e0 RecipeShopItemInfo";
	private int _shopId;
	private int _recipeId;


	public RecipeShopItemInfo(int shopId, int recipeId)
	{
		_shopId = shopId;
		_recipeId = recipeId;
	}

	@Override
	protected final void writeImpl()
	{
        if (!(L2World.getInstance().findObject(_shopId) instanceof L2PcInstance))
            return;

		L2PcInstance manufacturer = (L2PcInstance)L2World.getInstance().findObject(_shopId);
		writeC(0xe0);
		writeD(_shopId);
		writeD(_recipeId);
		writeD(manufacturer != null ? (int)manufacturer.getCurrentMp() : 0);
		writeD(manufacturer != null ? (int)manufacturer.getMaxMp() : 0);
		writeD(0xffffffff);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__DA_RecipeShopItemInfo;
	}
}