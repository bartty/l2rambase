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
import l2.rambase.gameserver.network.serverpackets.ExShowSentPost;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;


public final class RequestSentPost extends L2GameClientPacket
{
	private static final String _C__D0_6E_REQUESTSENTPOST = "[C] D0:6E RequestSentPost";

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

		Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null)
			return;

		if (msg.getSenderId() != activeChar.getObjectId())
		{
			Util.handleIllegalPlayerAction(activeChar,
					"Player "+activeChar.getName()+" tried to read not own post!", Config.DEFAULT_PUNISH);
			return;
		}

		if (msg.isDeletedBySender())
			return;

		activeChar.sendPacket(new ExShowSentPost(msg));
	}

	@Override
	public String getType()
	{
		return _C__D0_6E_REQUESTSENTPOST;
	}
} 
