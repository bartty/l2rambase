/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;

public class RequestTutorialClientEvent extends L2GameClientPacket
{
	private static final String _C__88_REQUESTTUTORIALCLIENTEVENT = "[C] 88 RequestTutorialClientEvent";
	int eventId = 0;

	@Override
	protected void readImpl()
	{
		eventId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();

		if(player == null)
			return;

		QuestState qs = player.getQuestState("255_Tutorial");
		if(qs != null)
			qs.getQuest().notifyEvent("CE" + eventId + "",null,player);
	}

	@Override
	public String getType()
	{
		return _C__88_REQUESTTUTORIALCLIENTEVENT;
	}
}
