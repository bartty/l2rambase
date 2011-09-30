/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;

public class RequestTutorialPassCmdToServer extends L2GameClientPacket
{
	String _bypass = null;

	@Override
	protected void readImpl()
	{
		_bypass = readS();
	} 
	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();

		if(player == null)
			return;

		QuestState qs = player.getQuestState("255_Tutorial");
		if(qs != null)
			qs.getQuest().notifyEvent(_bypass, null, player);
       	}

	@Override
	public String getType()
	{
		return "[C] 86 RequestTutorialPassCmdToServer";
	}
}
