/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ChairSit extends L2GameServerPacket
{
    private static final String _S__e1_CHAIRSIT = "[S] ed ChairSit";

    private L2PcInstance _activeChar;
    private int _staticObjectId;

    /**
     */
    public ChairSit(L2PcInstance player, int staticObjectId)
    {
        _activeChar = player;
        _staticObjectId = staticObjectId;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0xed);
        writeD(_activeChar.getObjectId());
        writeD(_staticObjectId);
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__e1_CHAIRSIT;
    }
}