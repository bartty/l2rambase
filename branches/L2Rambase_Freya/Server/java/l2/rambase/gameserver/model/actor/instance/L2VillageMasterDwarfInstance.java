/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.base.PlayerClass;
import l2.rambase.gameserver.model.base.Race;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public final class L2VillageMasterDwarfInstance extends L2VillageMasterInstance
{
	public L2VillageMasterDwarfInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected final boolean checkVillageMasterRace(PlayerClass pclass)
	{
		if (pclass == null)
			return false;

		return pclass.isOfRace(Race.Dwarf);
	}
}