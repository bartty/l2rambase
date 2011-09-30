/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.GmListTable;

public final class RequestGmList extends L2GameClientPacket
{
	private static final String _C__81_REQUESTGMLIST = "[C] 81 RequestGmList";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		if (getClient().getActiveChar() == null)
		    return;
		GmListTable.getInstance().sendListToPlayer(getClient().getActiveChar());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__81_REQUESTGMLIST;
	}
}
