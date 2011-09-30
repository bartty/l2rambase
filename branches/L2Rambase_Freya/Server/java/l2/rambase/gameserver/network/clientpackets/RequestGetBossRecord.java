/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.Map;
import java.util.logging.Logger;

import l2.rambase.gameserver.instancemanager.RaidBossPointsManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExGetBossRecord;

/**
 * Format: (ch) d
 *
 */
public class RequestGetBossRecord extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestGetBossRecord.class.getName());
    private static final String _C__D0_18_REQUESTGETBOSSRECORD = "[C] D0:18 RequestGetBossRecord";
    private int _bossId;

    @Override
	protected void readImpl()
    {
        _bossId = readD();
    }

    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
    	L2PcInstance activeChar = getClient().getActiveChar();
    	if(activeChar == null)
    		return;

        if (_bossId != 0)
        {
            _log.info("C5: RequestGetBossRecord: d: "+_bossId+" ActiveChar: "+activeChar); // should be always 0, log it if isnt 0 for furture research
        }

        int points = RaidBossPointsManager.getPointsByOwnerId(activeChar.getObjectId());
        int ranking = RaidBossPointsManager.calculateRanking(activeChar.getObjectId());
        
        Map<Integer, Integer> list = RaidBossPointsManager.getList(activeChar);

       	activeChar.sendPacket(new ExGetBossRecord(ranking, points, list));
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_18_REQUESTGETBOSSRECORD;
    }

}