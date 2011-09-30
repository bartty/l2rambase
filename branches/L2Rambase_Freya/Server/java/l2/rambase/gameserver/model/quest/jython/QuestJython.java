/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.quest.jython;

import l2.rambase.gameserver.model.quest.Quest;

public abstract class QuestJython extends Quest
{
	/**
	 * Constructor used in jython files.
	 * @param questId : int designating the ID of the quest
	 * @param name : String designating the name of the quest
	 * @param descr : String designating the description of the quest
	 */
	public QuestJython(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}
}