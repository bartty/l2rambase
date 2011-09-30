/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.skills.Stats;


public class NpcStat extends CharStat
{
    public NpcStat(L2Npc activeChar)
    {
        super(activeChar);
    }

    @Override
    public byte getLevel()
    {
    	return getActiveChar().getTemplate().level;
    }
    
    @Override
	public L2Npc getActiveChar() { return (L2Npc)super.getActiveChar(); }

    @Override
	public final int getMaxHp() { return (int)calcStat(Stats.MAX_HP, getActiveChar().getTemplate().baseHpMax , null, null); }
    
    @Override
	public int getWalkSpeed(){ return (int) calcStat(Stats.WALK_SPEED, getActiveChar().getTemplate().baseWalkSpd, null, null);}
    
    @Override
	public float getMovementSpeedMultiplier()
	{
    	if (getActiveChar() == null)
    		return 1;
    	if (getActiveChar().isRunning())
    		return getRunSpeed() * 1f / getActiveChar().getTemplate().baseRunSpd;
    	else
    		return getWalkSpeed() * 1f / getActiveChar().getTemplate().baseWalkSpd;
	}
}