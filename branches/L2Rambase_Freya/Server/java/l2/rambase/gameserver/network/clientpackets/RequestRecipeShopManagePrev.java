/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.RecipeShopSellList;


public final class RequestRecipeShopManagePrev extends L2GameClientPacket
{
	private static final String _C__B7_RequestRecipeShopPrev = "[C] b7 RequestRecipeShopPrev";
	

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null || player.getTarget() == null)
		    return;

        // Player shouldn't be able to set stores if he/she is alike dead (dead or fake death)
        if (player.isAlikeDead())
        {
            sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

	if (!(player.getTarget() instanceof L2PcInstance))
	    return;
	L2PcInstance target = (L2PcInstance)player.getTarget();
		player.sendPacket(new RecipeShopSellList(player,target));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__B7_RequestRecipeShopPrev;
	}
}