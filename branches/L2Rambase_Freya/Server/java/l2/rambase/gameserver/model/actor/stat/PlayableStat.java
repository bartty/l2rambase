/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import java.util.logging.Logger;

import l2.rambase.gameserver.instancemanager.ZoneManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.model.base.Experience;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.model.zone.type.L2SwampZone;


public class PlayableStat extends CharStat
{
    protected static final Logger _log = Logger.getLogger(PlayableStat.class.getName());

    public PlayableStat(L2Playable activeChar)
    {
        super(activeChar);
    }

    public boolean addExp(long value)
    {
		if ((getExp() + value) < 0 || (value > 0 && getExp() == (getExpForLevel(Experience.MAX_LEVEL) - 1)))
            return true;

		if (getExp() + value >= getExpForLevel(Experience.MAX_LEVEL))
 			value = getExpForLevel(Experience.MAX_LEVEL) - 1 - getExp();

        setExp(getExp() + value);

        byte minimumLevel = 1;
        if (getActiveChar() instanceof L2PetInstance)
        {
        	// get minimum level from L2NpcTemplate
        	minimumLevel = ((L2PetInstance)getActiveChar()).getTemplate().level;
        }

        byte level = minimumLevel; // minimum level

        for (byte tmp = level; tmp <= Experience.MAX_LEVEL; tmp++)
        {
            if (getExp() >= getExpForLevel(tmp))
            	continue;
            level = --tmp;
            break;
        }
        if (level != getLevel() && level >= minimumLevel) 
        	addLevel((byte)(level - getLevel()));

        return true;
    }

    public boolean removeExp(long value)
    {
        if ((getExp() - value) < 0 )
        	value = getExp()-1;

        setExp(getExp() - value);

        byte minimumLevel = 1;
        if (getActiveChar() instanceof L2PetInstance)
        {
        	// get minimum level from L2NpcTemplate
        	minimumLevel = ((L2PetInstance)getActiveChar()).getTemplate().level;
        }
        byte level = minimumLevel;
        
        for (byte tmp = level; tmp <= Experience.MAX_LEVEL; tmp++)
        {
            if (getExp() >= getExpForLevel(tmp))
            	continue;
            level = --tmp;
            break;
        }
        if (level != getLevel() && level >= minimumLevel)
        	addLevel((byte)(level - getLevel()));
        return true;
    }

    public boolean addExpAndSp(long addToExp, int addToSp)
    {
    	boolean expAdded = false;
    	boolean spAdded = false;
    	if (addToExp >= 0) expAdded = addExp(addToExp);
    	if (addToSp >= 0) spAdded = addSp(addToSp);

    	return expAdded || spAdded;
    }

    public boolean removeExpAndSp(long removeExp, int removeSp)
    {
        boolean expRemoved = false;
        boolean spRemoved = false;
        if (removeExp > 0) expRemoved = removeExp(removeExp);
        if (removeSp > 0) spRemoved = removeSp(removeSp);

        return expRemoved || spRemoved;
    }

    public boolean addLevel(byte value)
    {
		if (getLevel() + value > Experience.MAX_LEVEL - 1)
        {
			if (getLevel() < Experience.MAX_LEVEL - 1)
				value = (byte)(Experience.MAX_LEVEL - 1 - getLevel());
        	else
        		return false;
        }

        boolean levelIncreased = (getLevel() + value > getLevel());
        value += getLevel();
        setLevel(value);

        // Sync up exp with current level
        if (getExp() >= getExpForLevel(getLevel() + 1) || getExpForLevel(getLevel()) > getExp()) setExp(getExpForLevel(getLevel()));

        if (!levelIncreased) return false;

        getActiveChar().getStatus().setCurrentHp(getActiveChar().getStat().getMaxHp());
        getActiveChar().getStatus().setCurrentMp(getActiveChar().getStat().getMaxMp());        

        return true;
    }

    public boolean addSp(int value)
    {
    	if (value < 0)
    	{
    		_log.warning("wrong usage");
    		return false;
    	}
    	int currentSp = getSp();
    	if (currentSp == Integer.MAX_VALUE)
    		return false;

    	if (currentSp > Integer.MAX_VALUE - value)
    		value = Integer.MAX_VALUE - currentSp;

        setSp(currentSp + value);
        return true;
    }

    public boolean removeSp(int value)
    {
    	int currentSp = getSp();
    	if (currentSp < value)
    		value = currentSp;
        setSp(getSp() - value);
        return true;
    }

    public long getExpForLevel(int level) { return level; }
    
    @Override
    public int getRunSpeed()
    {
    	int val = super.getRunSpeed();
		if (getActiveChar().isInsideZone(L2Character.ZONE_WATER))
			val /= 2;

		if (getActiveChar().isInsideZone(L2Character.ZONE_SWAMP))
		{
			L2ZoneType zone = ZoneManager.getInstance().getZone(getActiveChar(), L2SwampZone.class);
			int bonus = zone == null ? 0 : ((L2SwampZone) zone).getMoveBonus();
			double dbonus = bonus / 100.0; //%
			val += val * dbonus;
		}
		
		return val;
    }

    @Override
	public L2Playable getActiveChar() { return (L2Playable)super.getActiveChar(); }
}