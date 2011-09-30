/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class GameGuardQuery extends L2GameServerPacket
{
    private static final String _S__F9_GAMEGUARDQUERY = "[S] 74 GameGuardQuery";

    public GameGuardQuery()
    {        
    }

    @Override
	public void runImpl()
    {        
    }

    @Override
	public void writeImpl()
    {
        writeC(0x74);
        writeD(0x27533DD9);
        writeD(0x2E72A51D);
        writeD(0x2017038B);
        writeD(0xC35B1EA3);
    }

    @Override
	public String getType()
    {
        return _S__F9_GAMEGUARDQUERY;
    }
}