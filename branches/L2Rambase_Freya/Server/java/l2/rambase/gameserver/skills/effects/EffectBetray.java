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
package l2.rambase.gameserver.skills.effects;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.skills.Env;
import l2.rambase.gameserver.templates.effects.EffectTemplate;
import l2.rambase.gameserver.templates.skills.L2EffectType;

/**
 * @author L2Rambase Project
 */
public class EffectBetray extends L2Effect
{
	public EffectBetray(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#getEffectType()
	 */
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.BETRAY;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public boolean onStart()
	{
		if (getEffector() instanceof L2PcInstance && 
				getEffected() instanceof L2Summon)
		{
			L2PcInstance targetOwner = getEffected().getActingPlayer();
			getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, targetOwner);
			getEffected().setIsBetrayed(true);
			if (targetOwner != null)
				targetOwner.setIsBetrayed(true);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onExit()
	 */
	@Override
	public void onExit()
	{
		getEffected().setIsBetrayed(false);
		getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		L2PcInstance targetOwner = getEffected().getActingPlayer();
		if (targetOwner != null)
			targetOwner.setIsBetrayed(false);
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		return false;
	}
}
