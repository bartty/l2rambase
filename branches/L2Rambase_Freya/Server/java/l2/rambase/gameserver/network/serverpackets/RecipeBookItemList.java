/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


import l2.rambase.gameserver.model.L2RecipeList;


public class RecipeBookItemList extends L2GameServerPacket
{
	private static final String _S__D6_RECIPEBOOKITEMLIST = "[S] dc RecipeBookItemList";
	private L2RecipeList[] _recipes;
	private boolean _isDwarvenCraft;
 	private int _maxMp;

	public RecipeBookItemList(boolean isDwarvenCraft, int maxMp)
	{
		_isDwarvenCraft = isDwarvenCraft;
	 	_maxMp = maxMp;
	}

	public void addRecipes(L2RecipeList[] recipeBook)
	{
		_recipes = recipeBook;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xdc);

		writeD(_isDwarvenCraft ? 0x00 : 0x01); //0 = Dwarven - 1 = Common
	 	writeD(_maxMp);

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
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D6_RECIPEBOOKITEMLIST;
	}
}