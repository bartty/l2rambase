/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ManufactureItem;
import l2.rambase.gameserver.model.L2ManufactureList;
import l2.rambase.gameserver.model.L2RecipeList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RecipeShopManageList  extends L2GameServerPacket
{

	private static final String _S__D8_RecipeShopManageList = "[S] de RecipeShopManageList";
	private L2PcInstance _seller;
	private boolean _isDwarven;
	private L2RecipeList[] _recipes;

	public RecipeShopManageList(L2PcInstance seller, boolean isDwarven)
	{
		_seller = seller;
		_isDwarven = isDwarven;

		if (_isDwarven && _seller.hasDwarvenCraft())
			_recipes = _seller.getDwarvenRecipeBook();
		else
			_recipes = _seller.getCommonRecipeBook();

		// clean previous recipes
		if (_seller.getCreateList() != null)
		{
			L2ManufactureList list = _seller.getCreateList();
			for (L2ManufactureItem item : list.getList())
			{
				if (item.isDwarven() != _isDwarven || !seller.hasRecipeList(item.getRecipeId()))
					list.getList().remove(item);
			}
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xde);
		writeD(_seller.getObjectId());
		writeD((int)_seller.getAdena());
		writeD(_isDwarven ? 0x00 : 0x01);

		if (_recipes == null)
		{
			writeD(0);
		}
		else
		{
			writeD(_recipes.length);//number of items in recipe book

			for (int i = 0; i < _recipes.length; i++)
			{
				L2RecipeList temp = _recipes[i];
				writeD(temp.getId());
				writeD(i+1);
			}
		}

        if (_seller.getCreateList() == null)
        {
            writeD(0);
        }
        else
        {
            L2ManufactureList list = _seller.getCreateList();
            writeD(list.size());

            for (L2ManufactureItem item : list.getList())
            {
                writeD(item.getRecipeId());
                writeD(0x00);
                writeQ(item.getCost());
            }
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D8_RecipeShopManageList;
	}
}