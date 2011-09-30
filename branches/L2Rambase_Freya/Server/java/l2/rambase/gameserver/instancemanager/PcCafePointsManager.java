/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExPCCafePointInfo;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.Rnd;
import java.util.logging.Logger;

public class PcCafePointsManager implements Runnable
{
    private static final Logger _log = Logger.getLogger(PcCafePointsManager.class.getName());
    private static PcCafePointsManager _instance;
    
	public static PcCafePointsManager getInstance()
    {
        if(_instance == null)
            _instance = new PcCafePointsManager();
        return _instance;
    }

    private PcCafePointsManager()
    {
        _log.info("PcBang point event started.");
    }

	public void run()
    {
        int score = 0;
        for(L2PcInstance i: L2World.getInstance().getAllPlayers().values())
        {
            L2PcInstance activeChar = i;
            if(activeChar.getLevel() <= Config.PCB_MIN_LEVEL)
                continue;
            if (activeChar.isOnline()<1)
            {
            	if (Config.PCB_OFFLINE_SHOPS)
            	{
            		activeChar.addPcBangScore(50);
                    SystemMessage sm = new SystemMessage(SystemMessageId.ACQUIRED_S1_PCPOINT);
                    sm.addNumber(score);
                    activeChar.sendPacket(sm);
                    sm = null;
                    activeChar.sendPacket(new ExPCCafePointInfo(activeChar.getPcBangPoints(), 50, true, false, 1));                    
            	}
            	if (Config.PCB_RECHARGE_SHOPS)
            	{
            		if (activeChar.getClassId().getId() == 56 || activeChar.getClassId().getId() == 57 || activeChar.getClassId().getId() == 118)
            		{
            			activeChar.setCurrentMp(activeChar.getMaxMp());
            		}
            	}
            	continue;
            }            
            score = Rnd.get(Config.PCB_POINT_MIN, Config.PCB_POINT_MAX);
            if(Rnd.get(100) <= Config.PCB_CHANCE_DUAL_POINT)
            {
                score *= 2;
                activeChar.addPcBangScore(score);
                SystemMessage sm = new SystemMessage(SystemMessageId.ACQUIRED_S1_PCPOINT_DOUBLE);
                sm.addNumber(score);
                activeChar.sendPacket(sm);
                sm = null;
                activeChar.sendPacket(new ExPCCafePointInfo(activeChar.getPcBangPoints(), score, true, true, 1));
            }
            else
            {
                activeChar.addPcBangScore(score);
                SystemMessage sm = new SystemMessage(SystemMessageId.ACQUIRED_S1_PCPOINT);
                sm.addNumber(score);
                activeChar.sendPacket(sm);
                sm = null;
                activeChar.sendPacket(new ExPCCafePointInfo(activeChar.getPcBangPoints(), score, true, false, 1));
            }
        }
    }
}