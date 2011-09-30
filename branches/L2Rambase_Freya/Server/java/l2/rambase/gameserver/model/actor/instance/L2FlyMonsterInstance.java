/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public final class L2FlyMonsterInstance extends L2MonsterInstance
{

	public L2FlyMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2FlyMonsterInstance);
	}

    @Override
	public void onSpawn()
    {
    	this.setIsFlying(true);
    	super.onSpawn();
    }
}