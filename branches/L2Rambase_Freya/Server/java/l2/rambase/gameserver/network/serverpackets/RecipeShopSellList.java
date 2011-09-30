/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ManufactureItem;
import l2.rambase.gameserver.model.L2ManufactureList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RecipeShopSellList extends L2GameServerPacket
{

    private static final String _S__D9_RecipeShopSellList = "[S] df RecipeShopSellList";
    private L2PcInstance _buyer,_manufacturer;

    public RecipeShopSellList(L2PcInstance buyer,L2PcInstance manufacturer)
    {
        _buyer = buyer;
        _manufacturer = manufacturer;
    }

	@Override
	protected final void writeImpl()
    {
        L2ManufactureList createList = _manufacturer.getCreateList();

        if (createList != null)
        {
            writeC(0xdf);
            writeD(_manufacturer.getObjectId());
            writeD((int) _manufacturer.getCurrentMp());//Creator's MP
            writeD(_manufacturer.getMaxMp());//Creator's MP
            writeQ(_buyer.getAdena());//Buyer Adena

            int count = createList.size();
            writeD(count);
            L2ManufactureItem temp;

            for (int i = 0; i < count; i++)
            {
                temp = createList.getList().get(i);
                writeD(temp.getRecipeId());
                writeD(0x00); //unknown
                writeQ(temp.getCost());
            }
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__D9_RecipeShopSellList;
    }
}