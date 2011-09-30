/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExStartScenePlayer extends L2GameServerPacket
{
    private static final String _S__FE99_PLAYQUESTMOVIE = "[S] FE:99 ExStartScenePlayer";

    private int _movieId;
    
    public ExStartScenePlayer(int id)
    {
    	_movieId = id;
    }

    @Override
	public void writeImpl()
    {
        writeC(0xfe);
        writeH(0x99);
        writeD(_movieId);
    }

    @Override
	public String getType()
    {
        return _S__FE99_PLAYQUESTMOVIE;
    }
}