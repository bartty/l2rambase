/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.knownlist.FriendlyMobKnownList;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2FriendlyMobInstance extends L2Attackable
{
	public L2FriendlyMobInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2FriendlyMobInstance);
	}

    @Override
	public final FriendlyMobKnownList getKnownList()
    {
    	return (FriendlyMobKnownList)super.getKnownList();
    }
    
	@Override
    public void initKnownList()
    {
		setKnownList(new FriendlyMobKnownList(this));
    }

	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		if (attacker instanceof L2PcInstance)
			return ((L2PcInstance)attacker).getKarma() > 0;
		return false;
	}

	@Override
	public boolean isAggressive()
	{
		return true;
	}
}