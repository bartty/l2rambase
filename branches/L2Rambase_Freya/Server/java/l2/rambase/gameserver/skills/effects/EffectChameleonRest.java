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

/**
 * @author L2Rambase Project
 */
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.Env;
import l2.rambase.gameserver.templates.effects.EffectTemplate;
import l2.rambase.gameserver.templates.skills.L2EffectType;
import l2.rambase.gameserver.templates.skills.L2SkillType;

public class EffectChameleonRest extends L2Effect
{
	public EffectChameleonRest(Env env, EffectTemplate template)
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
		return L2EffectType.RELAXING;
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public boolean onStart()
	{
		if (getEffected() instanceof L2PcInstance)
		{
			setChameleon(true);
			((L2PcInstance) getEffected()).setSilentMoving(true);
			((L2PcInstance) getEffected()).sitDown();
		}
		else
			getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_REST);
		return super.onStart();
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onExit()
	 */
	@Override
	public void onExit()
	{
		setChameleon(false);
		if (getEffected() instanceof L2PcInstance)
			((L2PcInstance) getEffected()).setSilentMoving(false);
		
		super.onExit();
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.L2Effect#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		boolean retval = true;
		
		if (getEffected().isDead())
			retval = false;
		
		// Only cont skills shouldn't end
		if (getSkill().getSkillType() != L2SkillType.CONT)
			return false;
		
		if (getEffected() instanceof L2PcInstance)
		{
			if (!((L2PcInstance) getEffected()).isSitting())
				retval = false;
		}
		
		double manaDam = calc();
		
		if (manaDam > getEffected().getCurrentMp())
		{
			getEffected().sendPacket(new SystemMessage(SystemMessageId.SKILL_REMOVED_DUE_LACK_MP));
			return false;
		}
		
		if (!retval)
			setChameleon(retval);
		else
			getEffected().reduceCurrentMp(manaDam);
		
		return retval;
	}
	
	/**
	 * 
	 * @param val
	 */
	private void setChameleon(boolean val)
	{
		if (getEffected() instanceof L2PcInstance)
			((L2PcInstance) getEffected()).setRelax(val);
	}
}