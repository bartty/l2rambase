/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class RequestShortCutDel extends L2GameClientPacket
{
	private static final String _C__35_REQUESTSHORTCUTDEL = "[C] 35 RequestShortCutDel";

	private int _slot;
	private int _page;


	@Override
	protected void readImpl()
	{
		int id = readD();
		_slot = id % 12;
		_page = id / 12;
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;

		if (_page > 10 || _page < 0)
			return;

		activeChar.deleteShortCut(_slot, _page);
		// client needs no confirmation. this packet is just to inform the server
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__35_REQUESTSHORTCUTDEL;
	}
}
