/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.SevenSigns;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.SSQStatus;

/**
 * Seven Signs Record Update Request
 *
 * packet type id 0xc7
 * format: cc
 */
public final class RequestSSQStatus extends L2GameClientPacket
{
	private static final String _C__C7_RequestSSQStatus = "[C] C7 RequestSSQStatus";

	private int _page;

	@Override
	protected void readImpl()
	{
		_page = readC();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;

        if ((SevenSigns.getInstance().isSealValidationPeriod() || SevenSigns.getInstance().isCompResultsPeriod()) && _page == 4)
            return;

		SSQStatus ssqs = new SSQStatus(activeChar.getObjectId(), _page);
		activeChar.sendPacket(ssqs);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__C7_RequestSSQStatus;
	}
}
