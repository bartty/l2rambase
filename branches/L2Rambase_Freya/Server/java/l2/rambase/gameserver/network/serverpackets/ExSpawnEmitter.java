/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExSpawnEmitter extends L2GameServerPacket
{
    private final int _playerObjectId;
    private final int _npcObjectId;
    
    public ExSpawnEmitter(int playerObjectId, int npcObjectId)
    {
        _playerObjectId = playerObjectId;
        _npcObjectId = npcObjectId;
    }
    
    public ExSpawnEmitter(L2PcInstance player, L2Npc npc)
    {
        this(player.getObjectId(), npc.getObjectId());
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:5D ExSpawnEmitter";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x5d);
        
        writeD(_npcObjectId);
        writeD(_playerObjectId);
        writeD(0x00); // ?
    }    
}