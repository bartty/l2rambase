/**
 * @author L2Rambase Project
 */
package handlers.skillhandlers;

import l2.rambase.gameserver.handler.ISkillHandler;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2ControllableAirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.templates.skills.L2SkillType;

public class RefuelAirShip implements ISkillHandler
{
	private static final L2SkillType[] SKILL_IDS =
	{
		L2SkillType.REFUEL
	};

	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#useSkill(l2.rambase.gameserver.model.actor.L2Character, l2.rambase.gameserver.model.L2Skill, l2.rambase.gameserver.model.L2Object[])
	 */
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{
		if (!(activeChar instanceof L2PcInstance))
			return;

		L2AirShipInstance ship = ((L2PcInstance)activeChar).getAirShip();
		if (ship == null)
		{
			return;
		}
		if (!(ship instanceof L2ControllableAirShipInstance))
		{
			return;
		}
		if (ship.getFuel() >= ship.getMaxFuel())
		{
			return;
		}

		ship.setFuel(ship.getFuel() + (int)skill.getPower());
		ship.updateAbnormalEffect(); // broadcast new fuel
	}

	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#getSkillIds()
	 */
	public L2SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}