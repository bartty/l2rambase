/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Summon;


public final class ExPartyPetWindowAdd extends L2GameServerPacket
{
    private final L2Summon _summon;
    
    public ExPartyPetWindowAdd(L2Summon summon)
    {
        _summon = summon;
    }
    
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:18 ExPartyPetWindowAdd";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x18);
        writeD(_summon.getObjectId());
        writeD(_summon.getTemplate().idTemplate + 1000000);
        writeD(_summon.getSummonType());
        writeD(_summon.getOwner().getObjectId());
        writeS(_summon.getName());
        writeD((int) _summon.getCurrentHp());
        writeD(_summon.getMaxVisibleHp());
        writeD((int) _summon.getCurrentMp());
        writeD(_summon.getMaxMp());
        writeD(_summon.getLevel());
    }    
}