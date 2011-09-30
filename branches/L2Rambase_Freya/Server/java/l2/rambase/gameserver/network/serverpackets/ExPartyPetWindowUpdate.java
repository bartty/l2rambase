/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Summon;


public class ExPartyPetWindowUpdate extends L2GameServerPacket
{
    private final L2Summon _summon;
    
    public ExPartyPetWindowUpdate(L2Summon summon)
    {
        _summon = summon;
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:19 ExPartyPetWindowUpdate";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x19);
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