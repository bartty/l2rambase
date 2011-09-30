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

import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.skills.Env;
import l2.rambase.gameserver.templates.effects.EffectTemplate;
import l2.rambase.gameserver.templates.skills.L2EffectType;

/**
 * @author L2Rambase Project
 */
public class EffectPhoenixBless extends L2Effect
{
	public EffectPhoenixBless(Env env, EffectTemplate template)
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
		return L2EffectType.PHOENIX_BLESSING;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public boolean onStart()
	{
		if (getEffected() instanceof L2Playable)
		{
			((L2Playable) getEffected()).startPhoenixBlessing();
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
		((L2Playable) getEffected()).stopPhoenixBlessing(this);
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		// just stop this effect
		return false;
	}
}