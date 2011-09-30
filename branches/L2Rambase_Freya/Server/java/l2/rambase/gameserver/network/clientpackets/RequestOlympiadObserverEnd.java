/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * format ch
 * c: (id) 0xD0
 * h: (subid) 0x12
 *
 */
public final class RequestOlympiadObserverEnd extends L2GameClientPacket
{
	private static final String _C__D0_12_REQUESTOLYMPIADOBSERVEREND = "[C] D0:12 RequestOlympiadObserverEnd";


	@Override
	protected void readImpl()
	{
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		if (activeChar.inObserverMode()) activeChar.leaveOlympiadObserverMode();
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */

	@Override
	public String getType()
	{
		return _C__D0_12_REQUESTOLYMPIADOBSERVEREND;
	}

}