/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.HennaTreeTable;
import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.HennaEquipList;

/**
 * RequestHennaList - 0xba
 *
 */
public final class RequestHennaDrawList extends L2GameClientPacket
{
	private static final String _C__BA_RequestHennaDrawList = "[C] ba RequestHennaDrawList";

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

		L2HennaInstance[] henna = HennaTreeTable.getInstance().getAvailableHenna(activeChar.getClassId());
		activeChar.sendPacket(new HennaEquipList(activeChar, henna));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__BA_RequestHennaDrawList;
	}
}