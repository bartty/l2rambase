/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.network.serverpackets.ExShowFortressMapInfo;

public class RequestFortressMapInfo extends L2GameClientPacket
{
    private int _fortressId;
    
    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[C] D0:4B RequestFortressMapInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
     */
    @Override
    protected void readImpl()
    {
        _fortressId = readD();
    }

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
        Fort fort = FortManager.getInstance().getFortById(_fortressId);
        sendPacket(new ExShowFortressMapInfo(fort));
    }
    
}
