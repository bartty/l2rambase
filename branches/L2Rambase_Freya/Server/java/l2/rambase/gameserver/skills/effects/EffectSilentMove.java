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
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.Env;
import l2.rambase.gameserver.templates.effects.EffectTemplate;
import l2.rambase.gameserver.templates.skills.L2EffectType;
import l2.rambase.gameserver.templates.skills.L2SkillType;

public class EffectSilentMove extends L2Effect
{
	public EffectSilentMove(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public boolean onStart()
	{
		super.onStart();
		
		L2Character effected = getEffected();
		if (effected instanceof L2Playable)
			((L2Playable) effected).setSilentMoving(true);
		return true;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onExit()
	 */
	@Override
	public void onExit()
	{
		super.onExit();
		
		L2Character effected = getEffected();
		if (effected instanceof L2Playable)
			((L2Playable) effected).setSilentMoving(false);
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#getEffectType()
	 */
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.SILENT_MOVE;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		// Only cont skills shouldn't end
		if (getSkill().getSkillType() != L2SkillType.CONT)
			return false;
		
		if (getEffected().isDead())
			return false;
		
		double manaDam = calc();
		
		if (manaDam > getEffected().getCurrentMp())
		{
			getEffected().sendPacket(new SystemMessage(SystemMessageId.SKILL_REMOVED_DUE_LACK_MP));
			return false;
		}
		
		getEffected().reduceCurrentMp(manaDam);
		return true;
	}
}
