/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ItemInstance;


public final class ExRpItemLink extends L2GameServerPacket
{
    private final L2ItemInstance _item;
    
    public ExRpItemLink(L2ItemInstance item)
    {
        _item = item;
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:6C ExRpItemLink";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x6c);
        // guessing xD
        writeD(_item.getObjectId());
        writeD(_item.getItemId());
        writeQ(_item.getCount());
        writeH(_item.getItem().getType2());
        writeD(_item.getItem().getBodyPart());
        writeH(_item.getEnchantLevel());
        writeH(_item.getCustomType2());  // item type3
        writeH(0x00); // ??
        writeD(_item.isAugmented() ? _item.getAugmentation().getAugmentationId() : 0x00);
        writeD(_item.getMana());
        // T1
        writeH(_item.getAttackElementType());
        writeH(_item.getAttackElementPower());
        for (byte i = 0; i < 6; i++)
            writeH(_item.getElementDefAttr(i));

        writeH(0x00); // Enchant effect 1
		writeH(0x00); // Enchant effect 2
		writeH(0x00); // Enchant effect 3 
    }
}