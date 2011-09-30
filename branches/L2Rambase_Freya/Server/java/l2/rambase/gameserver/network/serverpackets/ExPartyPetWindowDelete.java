/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Summon;


public class ExPartyPetWindowDelete extends L2GameServerPacket
{
    private final L2Summon _summon;
    
    public ExPartyPetWindowDelete(L2Summon summon)
    {
        _summon = summon;
    }
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:6a ExPartyPetWindowDelete";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x6a);
        writeD(_summon.getObjectId());
        writeD(_summon.getOwner().getObjectId());
        writeS(_summon.getName());
    }    
}