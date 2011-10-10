package intelligence.monsters;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class RemnantsInstance extends L2MonsterInstance
{
	public RemnantsInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void reduceCurrentHp(double i, L2Character attacker, L2Skill skill, boolean awake, boolean standUp, boolean directHp, boolean canReflect)
	{
		super.reduceCurrentHp(Math.min(i, getCurrentHp() - 1), attacker, skill, awake, standUp, directHp, canReflect);
	}

	public void onUseHolyWater(L2Character user)
	{
		if(getCurrentHp() < 100)
			doDie(user);
	}
}