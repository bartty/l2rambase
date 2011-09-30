/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.Collection;
import java.util.Iterator; 

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance.TimeStamp;


public class SkillCoolTime extends L2GameServerPacket
{
    public Collection<TimeStamp> _reuseTimeStamps;
    
    public SkillCoolTime(L2PcInstance cha)
    {
        _reuseTimeStamps = cha.getReuseTimeStamps();
     	Iterator<TimeStamp> iter = _reuseTimeStamps.iterator(); 
     	while (iter.hasNext()) 
     	{ 
     		if (!iter.next().hasNotPassed()) // remove expired timestamps 
     			iter.remove(); 
     	} 
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] C7 SkillCoolTime";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xc7);
        writeD(_reuseTimeStamps.size()); // list size
        for (TimeStamp ts : _reuseTimeStamps)
        {
            writeD(ts.getSkillId());
            writeD(0x00);
            writeD((int) ts.getReuse() / 1000);
            writeD((int) ts.getRemaining() / 1000);
        }
    }    
}