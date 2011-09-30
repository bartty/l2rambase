/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.ExShowFortressInfo;

public class RequestAllFortressInfo extends L2GameClientPacket
{

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[C] D0:3D RequestAllFortressInfo";
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
            client.sendPacket(new ExShowFortressInfo());
        }
    }    
}