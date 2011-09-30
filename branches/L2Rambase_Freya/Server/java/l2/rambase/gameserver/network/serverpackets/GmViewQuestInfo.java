/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


public class GmViewQuestInfo extends L2GameServerPacket
{
	private static final String _S__AC_GMVIEWQUESTLIST = "[S] 99 GMViewQuestList";

    private L2PcInstance _activeChar;

	public GmViewQuestInfo(L2PcInstance cha)
	{
		_activeChar = cha;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x99);
		writeS(_activeChar.getName());

        Quest[] questList = _activeChar.getAllActiveQuests();

        if (questList.length == 0)
        {
            writeC(0);
            writeH(0);
            writeH(0);
            return;
        }

        writeH(questList.length); // quest count

        for (Quest q : questList)
        {
            writeD(q.getQuestIntId());

            QuestState qs = _activeChar.getQuestState(q.getName());

            if (qs == null)
            {
                writeD(0);
                continue;
            }

            writeD(qs.getInt("cond"));   // stage of quest progress
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__AC_GMVIEWQUESTLIST;
	}
}