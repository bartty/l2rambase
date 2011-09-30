/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.Config;
import l2.rambase.gameserver.model.base.PlayerClass;
import l2.rambase.gameserver.model.base.Race;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public final class L2VillageMasterKamaelInstance extends L2VillageMasterInstance
{
	public L2VillageMasterKamaelInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	protected final String getSubClassMenu(Race pRace)
	{
		if (Config.ALT_GAME_SUBCLASS_EVERYWHERE
				|| pRace == Race.Kamael)
			return "data/html/villagemaster/SubClass.htm";

		return "data/html/villagemaster/SubClass_NoKamael.htm";
	}

	@Override
	protected final String getSubClassFail()
	{
		return "data/html/villagemaster/SubClass_Fail_Kamael.htm";
	}

	@Override
	protected final boolean checkQuests(L2PcInstance player)
	{
		QuestState qs = player.getQuestState("234_FatesWhisper");
		if (qs == null || !qs.isCompleted())
			return false;

		qs = player.getQuestState("236_SeedsOfChaos");
		if (qs == null || !qs.isCompleted())
			return false;

		return true;
	}

	@Override
	protected final boolean checkVillageMasterRace(PlayerClass pclass)
	{
		if (pclass == null)
			return false;

		return pclass.isOfRace(Race.Kamael);
	}
}