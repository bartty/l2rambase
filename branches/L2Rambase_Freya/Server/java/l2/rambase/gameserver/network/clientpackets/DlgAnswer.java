package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;


/**
 * @author L2Rambase Project
 * Format: cddd
 */
public final class DlgAnswer extends L2GameClientPacket
{
	private static final String _C__C5_DLGANSWER = "[C] C5 DlgAnswer";
	private static Logger _log = Logger.getLogger(DlgAnswer.class.getName());

	private int _messageId;
	private int _answer;
	private int _requesterId;

	@Override
	protected void readImpl()
	{
		_messageId = readD();
		_answer = readD();
		_requesterId = readD();
	}

	@Override
	public void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		if (Config.DEBUG)
			_log.fine(getType()+": Answer accepted. Message ID "+_messageId+", answer "+_answer+", Requester ID "+_requesterId);
		if (_messageId == SystemMessageId.RESSURECTION_REQUEST_BY_C1_FOR_S2_XP.getId()
				|| _messageId == SystemMessageId.RESURRECT_USING_CHARM_OF_COURAGE.getId())
			activeChar.reviveAnswer(_answer);
		else if (_messageId==SystemMessageId.C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT.getId())
			activeChar.teleportAnswer(_answer, _requesterId);
		else if (_messageId == SystemMessageId.S1.getId() && Config.MOD_ALLOW_WEDDING)
			activeChar.engageAnswer(_answer);
		else if (_messageId == SystemMessageId.WOULD_YOU_LIKE_TO_OPEN_THE_GATE.getId())
			activeChar.gatesAnswer(_answer, 1);
		else if (_messageId == SystemMessageId.WOULD_YOU_LIKE_TO_CLOSE_THE_GATE.getId())
			activeChar.gatesAnswer(_answer, 0);
	}

	@Override
	public String getType()
	{
		return _C__C5_DLGANSWER;
	}
}
