/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.network.serverpackets.QuestList;


public final class RequestQuestList extends L2GameClientPacket
{
	private static final String _C__63_REQUESTQUESTLIST = "[C] 63 RequestQuestList";
	

	@Override
	protected void readImpl()
	{	
	}

	@Override
	protected void runImpl()
	{
		QuestList ql = new QuestList();
		sendPacket(ql);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__63_REQUESTQUESTLIST;
	}
}
