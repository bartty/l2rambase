/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.HennaRemoveList;

public final class RequestHennaRemoveList extends L2GameClientPacket
{
	private static final String _C__BA_RequestHennaRemoveList = "[C] ba RequestHennaRemoveList";

	@SuppressWarnings("unused")
	private int _unknown;

	@Override
	protected void readImpl()
	{
		_unknown = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		activeChar.sendPacket(new HennaRemoveList(activeChar));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__BA_RequestHennaRemoveList;
	}
}