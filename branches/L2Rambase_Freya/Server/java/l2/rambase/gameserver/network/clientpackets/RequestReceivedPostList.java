/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import static l2.rambase.gameserver.model.actor.L2Character.ZONE_PEACE;

import l2.rambase.Config;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExShowReceivedPostList;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestReceivedPostList extends L2GameClientPacket
{
	private static final String _C__D0_67_REQUESTRECEIVEDPOSTLIST = "[C] D0:67 RequestReceivedPostList";

	@Override
	protected void readImpl()
	{
	}

	@Override
	public void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null || !Config.ALLOW_MAIL)
			return;

		if (!activeChar.isInsideZone(ZONE_PEACE))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_USE_MAIL_OUTSIDE_PEACE_ZONE));
			return;
		}

		activeChar.sendPacket(new ExShowReceivedPostList(activeChar.getObjectId()));
	}

	@Override
	public String getType()
	{
		return _C__D0_67_REQUESTRECEIVEDPOSTLIST;
	}
}
