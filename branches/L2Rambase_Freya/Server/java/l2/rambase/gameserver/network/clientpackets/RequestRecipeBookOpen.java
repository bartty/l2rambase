/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.RecipeController;


public final class RequestRecipeBookOpen extends L2GameClientPacket
{
    private static final String _C__AC_REQUESTRECIPEBOOKOPEN = "[C] AC RequestRecipeBookOpen";
	private static Logger _log = Logger.getLogger(RequestRecipeBookOpen.class.getName());

    private boolean _isDwarvenCraft;

	@Override
	protected void readImpl()
	{
        _isDwarvenCraft = (readD() == 0);
        if (Config.DEBUG)
        {
        	_log.info("RequestRecipeBookOpen : " + (_isDwarvenCraft ? "dwarvenCraft" : "commonCraft"));
        }
	}

	@Override
	protected void runImpl()
	{
	    if (getClient().getActiveChar() == null)
	        return;

        if (getClient().getActiveChar().getPrivateStoreType() != 0)
        {
            getClient().getActiveChar().sendMessage("Cannot use recipe book while trading");
            return;
        }

        RecipeController.getInstance().requestBookOpen(getClient().getActiveChar(), _isDwarvenCraft);
	}

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _C__AC_REQUESTRECIPEBOOKOPEN;
    }
}
