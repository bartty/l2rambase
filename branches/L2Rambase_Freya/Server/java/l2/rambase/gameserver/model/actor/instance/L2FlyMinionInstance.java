/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public final class L2FlyMinionInstance extends L2MinionInstance
{

	public L2FlyMinionInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2FlyMinionInstance);
	}

    @Override
	public void onSpawn()
    {
    	this.setIsFlying(true);
    	super.onSpawn();
    }
}