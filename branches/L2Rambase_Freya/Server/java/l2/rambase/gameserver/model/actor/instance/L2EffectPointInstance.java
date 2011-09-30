/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2EffectPointInstance extends L2Npc
{
	private L2Character _owner;

	public L2EffectPointInstance(int objectId, L2NpcTemplate template, L2Character owner)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2EffectPointInstance);
		setIsInvul(false);
		_owner = owner;
	}

	public L2Character getOwner()
	{
		return _owner;
	}

	/**
	 * this is called when a player interacts with this NPC
	 * @param player
	 */
	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}

	@Override
	public void onActionShift(L2PcInstance player)
	{
		if (player == null)
			return;

		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}