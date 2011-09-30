/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.QuestList;


public final class RequestQuestAbort extends L2GameClientPacket
{
	private static final String _C__64_REQUESTQUESTABORT = "[C] 64 RequestQuestAbort";
	private static Logger _log = Logger.getLogger(RequestQuestAbort.class.getName());

	private int _questId;


	@Override
	protected void readImpl()
	{
		_questId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		Quest qe = QuestManager.getInstance().getQuest(_questId);
		if (qe != null)
		{
			QuestState qs = activeChar.getQuestState(qe.getName());
			if (qs != null)
			{
				qs.exitQuest(true);
				activeChar.sendPacket(new QuestList());
			}
			else
			{
				if (Config.DEBUG)
					_log.info("Player '"+activeChar.getName()+"' try to abort quest "+qe.getName()+" but he didn't have it started.");
			}
		}
		else
		{
			if (Config.DEBUG)
				_log.warning("Quest (id='"+_questId+"') not found.");
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__64_REQUESTQUESTABORT;
	}
}