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
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;


public final class RequestDeleteSentPost extends L2GameClientPacket
{
	private static final String _C__D0_6C_REQUESTDELETESENTPOST = "[C] D0:6D RequestDeleteSentPost";

	private static final int BATCH_LENGTH = 4; // length of the one item

	int[] _msgIds = null;

	@Override
	protected void readImpl()
	{
		int count = readD();
		if (count <= 0
				|| count > Config.MAX_ITEM_IN_PACKET
				|| count * BATCH_LENGTH != _buf.remaining())
			return;

		_msgIds = new int[count];
		for (int i = 0; i < count; i++)
			_msgIds[i] = readD();
	}

	@Override
	public void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null || _msgIds == null || !Config.ALLOW_MAIL)
			return;

		if (!activeChar.isInsideZone(ZONE_PEACE))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_USE_MAIL_OUTSIDE_PEACE_ZONE));
			return;
		}

		for (int msgId : _msgIds)
		{
			Message msg = MailManager.getInstance().getMessage(msgId);
			if (msg == null)
				continue;
			if (msg.getSenderId() != activeChar.getObjectId())
			{
				Util.handleIllegalPlayerAction(activeChar,
						"Player "+activeChar.getName()+" tried to delete not own post!", Config.DEFAULT_PUNISH);
				return;
			}

			if (msg.hasAttachments() || msg.isDeletedBySender())
				return;

			msg.setDeletedBySender();
		}
		activeChar.sendPacket(new ExChangePostState(false, _msgIds, Message.DELETED));
	}

	@Override
	public String getType()
	{
		return _C__D0_6C_REQUESTDELETESENTPOST;
	}
}
