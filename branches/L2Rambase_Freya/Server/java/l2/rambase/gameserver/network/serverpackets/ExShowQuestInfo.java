/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExShowQuestInfo extends L2GameServerPacket
{
    private static final String _S__FE_19_EXQUESTINFO = "[S] FE:20 EXQUESTINFO";

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
     */
    @Override
	protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x20);
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _S__FE_19_EXQUESTINFO;
    }
}