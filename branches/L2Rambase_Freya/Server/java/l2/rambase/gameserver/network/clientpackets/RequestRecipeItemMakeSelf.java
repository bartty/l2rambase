/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.RecipeController;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class RequestRecipeItemMakeSelf extends L2GameClientPacket
{
	private static final String _C__AF_REQUESTRECIPEITEMMAKESELF = "[C] AF RequestRecipeItemMakeSelf";
	
	private int _id;

	@Override
	protected void readImpl()
	{
		_id = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		if (activeChar.getPrivateStoreType() != 0)
        {
        	activeChar.sendMessage("Cannot make items while trading");
        	return;
        }
		
		if (activeChar.isInCraftMode())
		{
        	activeChar.sendMessage("Currently in Craft Mode");
        	return;
		}

		RecipeController.getInstance().requestMakeItem(activeChar, _id);
	}

    @Override
	public String getType()
    {
    	return _C__AF_REQUESTRECIPEITEMMAKESELF;
    }
}