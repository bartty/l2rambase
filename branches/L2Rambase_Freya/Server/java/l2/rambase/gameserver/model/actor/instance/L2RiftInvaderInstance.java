/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class L2RiftInvaderInstance extends L2MonsterInstance
{
	// Not longer needed since rift monster targeting control now is handled by the room zones for any mob
	public L2RiftInvaderInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2RiftInvaderInstance);
	}
}