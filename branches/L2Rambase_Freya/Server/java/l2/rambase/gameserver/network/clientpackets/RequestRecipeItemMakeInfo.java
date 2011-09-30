/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.RecipeItemMakeInfo;


public final class RequestRecipeItemMakeInfo extends L2GameClientPacket
{
    private static final String _C__AE_REQUESTRECIPEITEMMAKEINFO = "[C] AE RequestRecipeItemMakeInfo";
	
	private int _id;

	@Override
	protected void readImpl()
	{
		_id = readD();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance player = getClient().getActiveChar(); 
		if (player == null) 
			return; 
		
		RecipeItemMakeInfo response = new RecipeItemMakeInfo(_id, player); 
		sendPacket(response);
	}

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _C__AE_REQUESTRECIPEITEMMAKEINFO;
    }
}
