/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.network.serverpackets.ItemList;

public final class RequestItemList extends L2GameClientPacket
{
	private static final String _C__0F_REQUESTITEMLIST = "[C] 0F RequestItemList";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
        if (getClient() != null && getClient().getActiveChar() != null && !getClient().getActiveChar().isInventoryDisabled())
        {
    		ItemList il = new ItemList(getClient().getActiveChar(), true);
    		sendPacket(il);
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__0F_REQUESTITEMLIST;
	}
}
