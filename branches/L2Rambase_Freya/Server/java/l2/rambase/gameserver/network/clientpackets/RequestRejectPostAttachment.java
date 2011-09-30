/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import static l2.rambase.gameserver.model.actor.L2Character.ZONE_PEACE;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Message;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExChangePostState;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;


public final class RequestRejectPostAttachment extends L2GameClientPacket
{
	private static final String _C__D0_6B_REQUESTREJECTPOSTATTACHMENT = "[C] D0:6B RequestRejectPostAttachment";

	private int _msgId;

	@Override
	protected void readImpl()
	{
		_msgId = readD();
	}

	@Override
	public void runImpl()
	{
		if (!Config.ALLOW_MAIL || !Config.ALLOW_ATTACHMENTS)
			return;

		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (!activeChar.getFloodProtectors().getTransaction().tryPerformAction("rejectattach"))
			return;

		if (!activeChar.isInsideZone(ZONE_PEACE))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_USE_MAIL_OUTSIDE_PEACE_ZONE));
			return;
		}

		Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null)
			return;

		if (msg.getReceiverId() != activeChar.getObjectId())
		{
			Util.handleIllegalPlayerAction(activeChar,
					"Player "+activeChar.getName()+" tried to reject not own attachment!", Config.DEFAULT_PUNISH);
			return;
		}

		if (!msg.hasAttachments() || msg.isFourStars())
			return;

		MailManager.getInstance().sendMessage(new Message(msg));

		activeChar.sendPacket(new SystemMessage(SystemMessageId.MAIL_SUCCESSFULLY_RETURNED));
		activeChar.sendPacket(new ExChangePostState(true, _msgId, Message.REJECTED));

		final L2PcInstance sender = L2World.getInstance().getPlayer(msg.getSenderId());
		if (sender != null)
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_RETURNED_MAIL);
			sm.addCharName(activeChar);
			sender.sendPacket(sm);
		}
	}

	@Override
	public String getType()
	{
		return _C__D0_6B_REQUESTREJECTPOSTATTACHMENT;
	}
}