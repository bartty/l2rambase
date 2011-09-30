/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.ExShowFortressSiegeInfo;

public class RequestFortressSiegeInfo extends L2GameClientPacket
{

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[C] D0:42 RequestFortressSiegeInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
     */
    @Override
    protected void readImpl()
    {
    }

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
        L2GameClient client = this.getClient();
        if (client != null)
        {
        	for (Fort fort : FortManager.getInstance().getForts())
        	{
        		if (fort != null && fort.getSiege().getIsInProgress())
        		{
        			client.sendPacket(new ExShowFortressSiegeInfo(fort));
        		}
        	}
        }
    }
}
