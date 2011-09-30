/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Clan.RankPrivs;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.PledgePowerGradeList;

/**
 * Format: (ch)
 *
 */
public final class RequestPledgePowerGradeList extends L2GameClientPacket
{
    private static final String _C__D0_1A_REQUESTPLEDGEPOWERGRADELIST = "[C] D0:1A RequestPledgePowerGradeList";


    @Override
	protected void readImpl()
    {
    }

    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
	protected void runImpl()
    {
    	L2PcInstance player = getClient().getActiveChar();
        L2Clan clan = player.getClan();
        if (clan != null)
        {
            RankPrivs[] privs = clan.getAllRankPrivs();
            player.sendPacket(new PledgePowerGradeList(privs));            
        }
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_1A_REQUESTPLEDGEPOWERGRADELIST;
    }

}