/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

/**
 * Format: (c)
 * (no data, trigger)
 * 
 */
public class RequestSiegeInfo extends L2GameClientPacket
{

    @Override
    protected void readImpl()
    {
    }

    @Override
    protected void runImpl()
    {
    }

    @Override
    public String getType()
    {
        return "[C] 0x47 RequestSiegeInfo";
    }
}
