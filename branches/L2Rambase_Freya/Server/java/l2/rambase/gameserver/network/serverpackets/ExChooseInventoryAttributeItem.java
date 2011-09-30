/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExChooseInventoryAttributeItem extends L2GameServerPacket
{
    private int _itemId;
    
    public ExChooseInventoryAttributeItem(int itemId)
    {
        _itemId = itemId;
    }
    @Override
    public String getType()
    {
    	return "[S] FE:62 ExChooseInventoryAttributeItem";
    }
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x62);
        writeD(_itemId);
    }
}