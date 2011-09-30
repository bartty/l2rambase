/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2.rambase.gameserver.network.serverpackets.InventoryUpdate;
import l2.rambase.gameserver.network.serverpackets.ItemList;
import l2.rambase.gameserver.network.serverpackets.UserInfo;

public class RequestBuySellUIClose extends L2GameClientPacket
{
	private static final String _C__D0_76_REQUESTBUYSELLUICLOSE = "[C] D0:76 RequestBuySellUIClose";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null || activeChar.isInventoryDisabled())
			return;

		activeChar.sendPacket(new ItemList(activeChar, true));
		activeChar.sendPacket(new InventoryUpdate());
		activeChar.sendPacket(new UserInfo(activeChar));
		activeChar.sendPacket(new ExBrExtraUserInfo(activeChar));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_76_REQUESTBUYSELLUICLOSE;
	}
}