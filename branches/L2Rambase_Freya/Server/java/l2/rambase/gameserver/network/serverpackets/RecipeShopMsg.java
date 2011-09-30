/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RecipeShopMsg extends L2GameServerPacket
{
	private static final String _S__DB_RecipeShopMsg = "[S] e1 RecipeShopMsg";
	private L2PcInstance _activeChar;

	public RecipeShopMsg(L2PcInstance player)
	{
		_activeChar = player;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xe1);
		writeD(_activeChar.getObjectId());
		writeS(_activeChar.getCreateList().getStoreName());//_activeChar.getTradeList().getSellStoreName());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__DB_RecipeShopMsg;
	}
}