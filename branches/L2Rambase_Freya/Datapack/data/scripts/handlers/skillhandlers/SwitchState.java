/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.skillhandlers;

//import java.util.Collection;
//import java.util.logging.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import javolution.util.FastList;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.handler.ISkillHandler;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
//import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.SkillHolder;
import l2.rambase.gameserver.templates.skills.L2SkillType;
import l2.rambase.util.Rnd;

/**
 * @author: L2Rambase Project
 * This is test project for pet skill Switch State
 */
public class SwitchState implements ISkillHandler
{
	private FastList<SkillHolder> _buffs = null; 
	private SkillHolder _majorHeal = null; 
	private SkillHolder _minorHeal = null; 
	private SkillHolder _recharge = null; 
	private static final int SwitchState = 6054;
	private Future<?> _castTask;

	private int _buffControlReuseHashcode = 0;
	
	private static final L2SkillType[] SKILL_IDS =
	{
		L2SkillType.BUFF
	};
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#useSkill(l2.rambase.gameserver.model.actor.L2Character, l2.rambase.gameserver.model.L2Skill, l2.rambase.gameserver.model.L2Object[])
	 */
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{	
		if (!(activeChar instanceof L2PetInstance))
		{
			return;
		}
		L2PetInstance pet = (L2PetInstance) activeChar;
		if (skill.getId() == SwitchState)
		{	
			if (pet.getFirstEffect(SwitchState) != null)
			{
				
				if (_castTask != null)
				{
					_castTask.cancel(false);
					_castTask = null;
				}
				pet.getFirstEffect(SwitchState).exit();			
			}
			else
			{
				if ((_majorHeal != null || _buffs != null || _recharge != null) 
						&& _castTask == null && !pet.getOwner().isDead()) // cast task is not yet started and not dead (will start on revive)
					_castTask = ThreadPoolManager.getInstance().scheduleEffectAtFixedRate(new CastTask(pet), 3000, 1000);
			}
		}
	}
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#getSkillIds()
	 */
	public L2SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
	/**
	 *  
	 */
	
	private class CastTask implements Runnable
	{
		private final L2PetInstance _pet;
		private List<L2Skill> _currentBuffs = new FastList<L2Skill>();

		public CastTask(L2PetInstance pet)
		{
			_pet = pet;
		}

		public void run()
		{
			L2PcInstance owner = _pet.getOwner();

			// if the owner is dead, merely wait for the owner to be resurrected
			// if the pet is still casting from the previous iteration, allow the cast to complete...
			if (owner != null
					&& !owner.isDead()
					&& !owner.isInvul()
					&& !_pet.isCastingNow()
					&& !_pet.isBetrayed()
					&& !_pet.isMuted()
					&& _pet.getAI().getIntention() != CtrlIntention.AI_INTENTION_CAST)
			{
				L2Skill skill = null;

				if (_majorHeal != null)
				{
					// if the owner's HP is more than 80%, do nothing.
					// if the owner's HP is very low (less than 20%) have a high chance for strong heal
					// otherwise, have a low chance for weak heal
					final double hpPercent = owner.getCurrentHp()/owner.getMaxHp();
					if (hpPercent < 0.15) 
					{ 
						skill = _majorHeal.getSkill(); 
						if (!_pet.isSkillDisabled(skill) 
								&& Rnd.get(100) <= 75) 
						{ 
							if (_pet.getCurrentMp() >= skill.getMpConsume()) 
							{ 
								castSkill(skill); 
								return; 
							} 
						} 
					} 
					if (hpPercent < 0.8) 
					{ 
						skill = _minorHeal.getSkill(); 
						if (!_pet.isSkillDisabled(skill) 
								&& Rnd.get(100) <= 25) 
						{ 
							if (_pet.getCurrentMp() >= skill.getMpConsume()) 
							{ 
								castSkill(skill); 
								return; 
							} 
						} 
					} 
				} 
				
				if (!_pet.isSkillDisabled(_buffControlReuseHashcode)) // Buff Control is not active
				{
					// searching for usable buffs
					if (_buffs != null && !_buffs.isEmpty())
					{
						for (SkillHolder i : _buffs) 
						{ 
							skill = i.getSkill(); 
							if (_pet.isSkillDisabled(skill)) 
								continue;
							if (_pet.getCurrentMp() >= skill.getMpConsume())
								_currentBuffs.add(skill);
						}
					}

					// buffs found, checking owner buffs
					if (!_currentBuffs.isEmpty())
					{
						L2Effect[] effects = owner.getAllEffects();
						Iterator<L2Skill> iter;
						L2Skill currentSkill;
						for (L2Effect e : effects)
						{
							if (e == null)
								continue;

							currentSkill = e.getSkill();
							// skipping debuffs, passives, toggles
							if (currentSkill.isDebuff()
									|| currentSkill.isPassive()
									|| currentSkill.isToggle())
								continue;

							// if buff does not need to be casted - remove it from list
							iter = _currentBuffs.iterator();
							while (iter.hasNext())
							{
								skill = iter.next();
								if (currentSkill.getId() == skill.getId()
										&& currentSkill.getLevel() >= skill.getLevel())
								{
									iter.remove();
								}
								else
								{
									// effect with same stacktype and greater or equal stackorder
									if (skill.hasEffects()
											&& !"none".equals(skill.getEffectTemplates()[0].stackType)
											&& e.getStackType().equals(skill.getEffectTemplates()[0].stackType)
											&& e.getStackOrder() >= skill.getEffectTemplates()[0].stackOrder)
									{
										iter.remove();
									}
								}
							}
							// no more buffs in list
							if (_currentBuffs.isEmpty())
								break;
						}
						// buffs list ready, casting random
						if (!_currentBuffs.isEmpty())
						{
							castSkill(_currentBuffs.get(Rnd.get(_currentBuffs.size())));
							_currentBuffs.clear();
							return;
						}
					}
				}

				// buffs/heal not casted, trying recharge, if exist
				if (_recharge != null 
						&& owner.isInCombat() // recharge casted only if owner in combat stance
						&& owner.getCurrentMp()/owner.getMaxMp() < 0.7						
						&& Rnd.get(100) <= 60)
				{
					skill = _recharge.getSkill(); 
					if (!_pet.isSkillDisabled(skill) 
							&& _pet.getCurrentMp() >= skill.getMpConsume())
					{
						castSkill(skill);
						return;
					}
				}
			}
		}
		/**
		 * 
		 */
		protected void castSkill(L2Skill skill)
		{
			// casting automatically stops any other action (such as autofollow or a move-to).
			// We need to gather the necessary info to restore the previous state.
			final boolean previousFollowStatus = _pet.getFollowStatus();

			// pet not following and owner outside cast range
			if (!previousFollowStatus && !_pet.isInsideRadius(_pet.getOwner(), skill.getCastRange(), true, true))
				return;

			_pet.useMagic(skill, false, false);

			SystemMessage msg = new SystemMessage(SystemMessageId.PET_USES_S1);
			msg.addSkillName(skill);
			_pet.getOwner().sendPacket(msg);

			// calling useMagic changes the follow status, if the babypet actually casts
			// (as opposed to failing due some factors, such as too low MP, etc).
			// if the status has actually been changed, revert it.  Else, allow the pet to
			// continue whatever it was trying to do.
			// NOTE: This is important since the pet may have been told to attack a target.
			// reverting the follow status will abort this attack!  While aborting the attack
			// in order to heal is natural, it is not acceptable to abort the attack on its own,
			// merely because the timer stroke and without taking any other action...
			if(previousFollowStatus != _pet.getFollowStatus())
				_pet.setFollowStatus(previousFollowStatus);
		}
	}
}