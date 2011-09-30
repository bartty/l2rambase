/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2ClassMasterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;

public class RequestTutorialQuestionMark extends L2GameClientPacket
{
	
	int _number = 0;

	@Override
	protected void readImpl()
	{
		_number = readD();
	}
	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();

		if(player == null)
			return;

		L2ClassMasterInstance.onTutorialQuestionMark(player, _number);

		QuestState qs = player.getQuestState("255_Tutorial");
		if(qs != null)
			qs.getQuest().notifyEvent("QM" + _number + "",null,player);
	}

	@Override
	public String getType()
	{
		return "[C] 87 RequestTutorialQuestionMark";
	}
}