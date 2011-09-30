/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.base.ClassType;
import l2.rambase.gameserver.model.base.PlayerClass;
import l2.rambase.gameserver.model.base.Race;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public final class L2VillageMasterPriestInstance extends L2VillageMasterInstance
{
	public L2VillageMasterPriestInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected final boolean checkVillageMasterRace(PlayerClass pclass)
	{
		if (pclass == null)
			return false;

		return pclass.isOfRace(Race.Human) || pclass.isOfRace(Race.Elf);
	}

	@Override
	protected final boolean checkVillageMasterTeachType(PlayerClass pclass)
	{
		if (pclass == null)
			return false;

		return pclass.isOfType(ClassType.Priest);
	}
}