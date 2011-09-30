/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public abstract class AbstractServerBasePacket extends L2GameServerPacket
{

    /**
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#runImpl()
     */
    @Override
	abstract public void runImpl();

    /**
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
     */
    @Override
	abstract protected void writeImpl();

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
	abstract public String getType();
}