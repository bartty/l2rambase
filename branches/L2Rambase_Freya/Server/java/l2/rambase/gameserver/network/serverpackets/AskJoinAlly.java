/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class AskJoinAlly extends L2GameServerPacket
{
    private static final String _S__A8_ASKJOINALLY_0XA8 = "[S] bb AskJoinAlly 0xa8";
        
    private String _requestorName;
    private int _requestorObjId;
    
    public AskJoinAlly(int requestorObjId, String requestorName)
    {
        _requestorName = requestorName;
        _requestorObjId = requestorObjId;
    }
    
    @Override
    protected final void writeImpl()
    {
        writeC(0xbb);
        writeD(_requestorObjId);
        writeS(_requestorName);
    }
    
    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _S__A8_ASKJOINALLY_0XA8;
    }    
}