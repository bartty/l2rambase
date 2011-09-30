/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.handler.ISkillHandler;
import l2.rambase.gameserver.handler.SkillHandler;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.network.serverpackets.MagicSkillLaunched;
import l2.rambase.gameserver.network.serverpackets.MagicSkillUse;
import l2.rambase.gameserver.skills.effects.EffectChanceSkillTrigger;
import l2.rambase.gameserver.templates.skills.L2SkillType;

import javolution.util.FastMap;


public class ChanceSkillList extends FastMap<IChanceSkillTrigger, ChanceCondition>
{
	private static final long serialVersionUID = 1L;

	private final L2Character _owner;

	public ChanceSkillList(L2Character owner)
	{
		super();
		shared();
		_owner = owner;
	}

	public L2Character getOwner()
	{
		return _owner;
	}

	public void onHit(L2Character target, boolean ownerWasHit, boolean wasCrit)
	{
		int event;
		if (ownerWasHit)
		{
			event = ChanceCondition.EVT_ATTACKED | ChanceCondition.EVT_ATTACKED_HIT;
			if (wasCrit)
				event |= ChanceCondition.EVT_ATTACKED_CRIT;
		}
		else
		{
			event = ChanceCondition.EVT_HIT;
			if (wasCrit)
				event |= ChanceCondition.EVT_CRIT;
		}

		onEvent(event, target, Elementals.NONE);
	}

	public void onEvadedHit(L2Character attacker)
	{
		onEvent(ChanceCondition.EVT_EVADED_HIT, attacker, Elementals.NONE);
	}

	public void onSkillHit(L2Character target, boolean ownerWasHit, boolean wasMagic, boolean wasOffensive, byte element)
	{
		int event;
		if (ownerWasHit)
		{
			event = ChanceCondition.EVT_HIT_BY_SKILL;
			if (wasOffensive)
			{
				event |= ChanceCondition.EVT_HIT_BY_OFFENSIVE_SKILL;
				event |= ChanceCondition.EVT_ATTACKED;
			}
			else
			{
				event |= ChanceCondition.EVT_HIT_BY_GOOD_MAGIC;
			}
		}
		else
		{
			event = ChanceCondition.EVT_CAST;
			event |= wasMagic ? ChanceCondition.EVT_MAGIC : ChanceCondition.EVT_PHYSICAL;
			event |= wasOffensive ? ChanceCondition.EVT_MAGIC_OFFENSIVE : ChanceCondition.EVT_MAGIC_GOOD;
		}

		onEvent(event, target, element);
	}

	public void onStart(byte element)
	{
		onEvent(ChanceCondition.EVT_ON_START, _owner, element);
	}

	public void onActionTime(byte element)
	{
		onEvent(ChanceCondition.EVT_ON_ACTION_TIME, _owner, element);
	}

	public void onExit(byte element)
	{
		onEvent(ChanceCondition.EVT_ON_EXIT, _owner, element);
	}

	public void onEvent(int event, L2Character target, byte element)
	{
		if (_owner.isDead())
			return;

		final boolean playable = target instanceof L2Playable;
		for (FastMap.Entry<IChanceSkillTrigger, ChanceCondition> e = head(), end = tail(); (e = e.getNext()) != end;)
		{
			if (e.getValue() != null && e.getValue().trigger(event, element, playable))
			{
				if (e.getKey() instanceof L2Skill)
					makeCast((L2Skill)e.getKey(), target);
				else if (e.getKey() instanceof EffectChanceSkillTrigger)
					makeCast((EffectChanceSkillTrigger)e.getKey(), target);
			}
		}
	}

	private void makeCast(L2Skill skill, L2Character target)
	{
		try
		{
			if(skill.getWeaponDependancy(_owner,true) && skill.checkCondition(_owner, target, false))
			{
				if(skill.triggersChanceSkill()) //skill will trigger another skill, but only if its not chance skill
				{
					skill = SkillTable.getInstance().getInfo(skill.getTriggeredChanceId(), skill.getTriggeredChanceLevel());
					if(skill == null || skill.getSkillType() == L2SkillType.NOTDONE)
						return;
				} 

				if (_owner.isSkillDisabled(skill))
					return;

				if (skill.getReuseDelay() > 0)
					_owner.disableSkill(skill, skill.getReuseDelay());

				L2Object[] targets = skill.getTargetList(_owner, false, target);

				if (targets.length == 0)
					return;

				L2Character firstTarget = (L2Character)targets[0];

				ISkillHandler handler = SkillHandler.getInstance().getSkillHandler(skill.getSkillType());

				_owner.broadcastPacket(new MagicSkillLaunched(_owner, skill.getDisplayId(), skill.getLevel(), targets));
				_owner.broadcastPacket(new MagicSkillUse(_owner, firstTarget, skill.getDisplayId(), skill.getLevel(), 0, 0));

				// Launch the magic skill and calculate its effects
				if (handler != null)
					handler.useSkill(_owner, skill, targets);
				else
					skill.useSkill(_owner, targets);
			}
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void makeCast(EffectChanceSkillTrigger effect, L2Character target)
	{
		try
		{
			if (effect == null || !effect.triggersChanceSkill())
				return;

			L2Skill triggered = SkillTable.getInstance().getInfo(effect.getTriggeredChanceId(), effect.getTriggeredChanceLevel());
			if (triggered == null) 
				return;
			L2Character caster = triggered.getTargetType() == L2Skill.SkillTargetType.TARGET_SELF ? _owner : effect.getEffector();

			if (caster == null
					|| triggered.getSkillType() == L2SkillType.NOTDONE
					|| caster.isSkillDisabled(triggered))
				return;

			if (triggered.getReuseDelay() > 0)
				caster.disableSkill(triggered, triggered.getReuseDelay());

			L2Object[] targets = triggered.getTargetList(caster, false, target);

			if (targets.length == 0)
				return;

			L2Character firstTarget = (L2Character)targets[0];

			ISkillHandler handler = SkillHandler.getInstance().getSkillHandler(triggered.getSkillType());

			_owner.broadcastPacket(new MagicSkillLaunched(_owner, triggered.getDisplayId(), triggered.getLevel(), targets));
			_owner.broadcastPacket(new MagicSkillUse(_owner, firstTarget, triggered.getDisplayId(), triggered.getLevel(), 0, 0));

			// Launch the magic skill and calculate its effects
			if (handler != null)
				handler.useSkill(caster, triggered, targets);
			else
				triggered.useSkill(caster, targets);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}