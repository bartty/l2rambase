/**
 * @author L2Rambase Project
 * TODO: RequestPVPMatchRecord - packet used to underground tournaments
 */
package l2.rambase.gameserver.network.clientpackets;


public class RequestPVPMatchRecord extends L2GameClientPacket
{

    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[C] D0:4C RequestPVPMatchRecord";
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
    }    
}