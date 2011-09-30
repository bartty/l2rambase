/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import static l2.rambase.gameserver.model.actor.L2Character.ZONE_PEACE;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Message;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExChangePostState;
import l2.rambase.gameserver.network.serverpackets.ExShowReceivedPost;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;


public final class RequestReceivedPost extends L2GameClientPacket
{
	private static final String _C__D0_69_REQUESTRECEIVEDPOST = "[C] D0:69 RequestReceivedPost";

	private int _msgId;

	@Override
	protected void readImpl()
	{
		_msgId = readD();
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

		final Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null)
			return;

		if (msg.getReceiverId() != activeChar.getObjectId())
		{
			Util.handleIllegalPlayerAction(activeChar,
					"Player "+activeChar.getName()+" tried to receive not own post!", Config.DEFAULT_PUNISH);
			return;
		}

		if (msg.isDeletedByReceiver())
			return;

		activeChar.sendPacket(new ExShowReceivedPost(msg));
		activeChar.sendPacket(new ExChangePostState(true, _msgId, Message.READED));
		msg.markAsRead();
	}

	@Override
	public String getType()
	{
		return _C__D0_69_REQUESTRECEIVEDPOST;
	}
}
