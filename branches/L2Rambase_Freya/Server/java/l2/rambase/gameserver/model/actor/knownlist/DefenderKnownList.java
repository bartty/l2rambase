/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2DefenderInstance;
import l2.rambase.gameserver.model.actor.instance.L2FortCommanderInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.entity.Fort;

public class DefenderKnownList extends AttackableKnownList
{
	// =========================================================
	// Data Field

	// =========================================================
	// Constructor
	public DefenderKnownList(L2DefenderInstance activeChar)
	{
		super(activeChar);
	}

	// =========================================================
	// Method - Public
	@Override
	public boolean addKnownObject(L2Object object)
	{
		if (!super.addKnownObject(object)) return false;

		Castle castle = getActiveChar().getCastle();
		Fort fortress = getActiveChar().getFort();
		// Check if siege is in progress
		if ((fortress != null && fortress.getZone().isActive())
				|| (castle != null && castle.getZone().isActive()))
		{
			L2PcInstance player = null;
			if (object instanceof L2PcInstance)
				player = (L2PcInstance) object;
			else if (object instanceof L2Summon)
				player = ((L2Summon)object).getOwner();
			int activeSiegeId = (fortress != null ? fortress.getFortId() : (castle != null ? castle.getCastleId() : 0));

			// Check if player is an enemy of this defender npc
			if (player != null && ((player.getSiegeState() == 2 && !player.isRegisteredOnThisSiegeField(activeSiegeId))
					|| (player.getSiegeState() == 1 && !TerritoryWarManager.getInstance().isAllyField(player, activeSiegeId))
					|| player.getSiegeState() == 0))
			{
				if (getActiveChar().getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)
					getActiveChar().getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null);
			}
		}
		return true;
	}

	// =========================================================
	// Property - Public
	@Override
	public final L2DefenderInstance getActiveChar()
	{
		if (super.getActiveChar() instanceof L2FortCommanderInstance)
			return (L2FortCommanderInstance)super.getActiveChar();
		return (L2DefenderInstance)super.getActiveChar();
	}
}