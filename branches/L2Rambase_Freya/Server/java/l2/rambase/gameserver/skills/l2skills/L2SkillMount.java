/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.skills.l2skills;

import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.StatsSet;

public class L2SkillMount extends L2Skill
{
	private int _npcId;
	private int _itemId;

	public L2SkillMount(StatsSet set)
	{
		super(set);
		_npcId = set.getInteger("npcId", 0);
		_itemId = set.getInteger("itemId", 0);
	}

	@Override
	public void useSkill(L2Character caster, L2Object[] targets)
	{
		if (!(caster instanceof L2PcInstance))
			return;
		
		if (!TvTEvent.onItemSummon(caster.getObjectId()))
			return;
		if (!PvPEvent.onItemSummon(caster.getObjectId()))
			return;
		
		L2PcInstance activePlayer = (L2PcInstance)caster;

		if (!activePlayer.getFloodProtectors().getItemPetSummon().tryPerformAction("mount"))
			return;
		
		// Dismount Action
		if (_npcId == 0)
		{
			activePlayer.dismount();
			return;
		}
		
		if (activePlayer.isSitting())
		{
			activePlayer.sendPacket(new SystemMessage(SystemMessageId.CANT_MOVE_SITTING));
			return;
		}
		
		if (activePlayer.inObserverMode())
			return;
		
		if (activePlayer.isInOlympiadMode())
		{
			activePlayer.sendPacket(new SystemMessage(SystemMessageId.THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT));
			return;
		}

		if (activePlayer.getPet() != null || activePlayer.isMounted())
		{
			activePlayer.sendPacket(new SystemMessage(SystemMessageId.YOU_ALREADY_HAVE_A_PET));
			return;
		}
		
		if (activePlayer.isAttackingNow() || activePlayer.isCursedWeaponEquipped())
		{
			activePlayer.sendPacket(new SystemMessage(SystemMessageId.YOU_CANNOT_SUMMON_IN_COMBAT));
			return;
		}
		
		activePlayer.mount(_npcId, _itemId, false);
	}
}
