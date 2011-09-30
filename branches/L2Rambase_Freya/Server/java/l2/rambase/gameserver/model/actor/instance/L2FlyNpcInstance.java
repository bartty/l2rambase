/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public final class L2FlyNpcInstance extends L2NpcInstance
{

	public L2FlyNpcInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2FlyNpcInstance); 
	}

    @Override
	public void onSpawn()
    {
    	this.setIsFlying(true);
    	super.onSpawn();
    }
}