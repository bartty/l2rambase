/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class L2CastleWyvernManagerInstance extends L2WyvernManagerInstance
{
	public L2CastleWyvernManagerInstance (int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public final boolean isOwnerClan(L2PcInstance player)
	{
		if (player.getClan() != null && getCastle() != null)
		{
			if (player.getClanId() == getCastle().getOwnerId() && player.isClanLeader())
				return true;
		}
		return false;
	}

	/**
	 * @see l2.rambase.gameserver.model.actor.instance.L2WyvernManagerInstance#isInSiege(l2.rambase.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public boolean isInSiege()
	{
		return getCastle().getZone().isActive();
	}
}