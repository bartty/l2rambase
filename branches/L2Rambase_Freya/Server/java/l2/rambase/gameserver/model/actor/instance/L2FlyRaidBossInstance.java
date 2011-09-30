/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public final class L2FlyRaidBossInstance extends L2RaidBossInstance
{

	public L2FlyRaidBossInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2FlyRaidBossInstance);
	}

    @Override
	public void onSpawn()
    {
    	this.setIsFlying(true);
    	super.onSpawn();
    }
}