/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.HennaTable;
import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.HennaItemDrawInfo;
import l2.rambase.gameserver.templates.item.L2Henna;

public final class RequestHennaItemDrawInfo extends L2GameClientPacket
{
	private static final String _C__BB_RequestHennaItemDrawInfo = "[C] bb RequestHennaItemDrawInfo";

	private int _symbolId;
	// format  cd

	@Override
	protected void readImpl()
	{
		_symbolId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		L2Henna template = HennaTable.getInstance().getTemplate(_symbolId);
		if (template == null)
			return;

		L2HennaInstance henna = new L2HennaInstance(template);
		activeChar.sendPacket(new HennaItemDrawInfo(henna, activeChar));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__BB_RequestHennaItemDrawInfo;
	}
}
