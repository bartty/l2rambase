/**
 * @author L2Rambase Project
 * 
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;
import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.games.FishermanManager;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * Format: (ch)
 *
 */
public final class RequestExFishRanking extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestExFishRanking.class.getName());
    private static final String _C__D0_1F_REQUESTEXFISHRANKING = "[C] D0:1F RequestExFishRanking";

    @Override
	protected void readImpl()
    {
    }

    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
	protected void runImpl()
    {
    	if(Config.MOD_FISHERMAN_ENABLED)
        {
            NpcHtmlMessage htm = new NpcHtmlMessage(0);
            htm.setHtml(FishermanManager.getInstance().showHtm(getClient().getActiveChar().getObjectId()));
            getClient().getActiveChar().sendPacket(htm);
        }
    	else
        {
            _log.info("C5: RequestExFishRanking");
        }
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_1F_REQUESTEXFISHRANKING;
    }
}