/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class SnoopQuit extends L2GameClientPacket
{
	private static final String _C__AB_SNOOPQUIT = "[C] AB SnoopQuit";

	private int _snoopID;


	@Override
	protected void readImpl()
	{
		_snoopID = readD();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance player = L2World.getInstance().getPlayer(_snoopID);
		if (player == null)
			return;
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		player.removeSnooper(activeChar);
		activeChar.removeSnooped(player);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__AB_SNOOPQUIT;
	}
}