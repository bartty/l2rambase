/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.primeval_isle;

import lineage2.gameserver.ai.DefaultAI;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.tables.SkillTable;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class SprigantPoison extends DefaultAI
{
	/**
	 * Field SKILL.
	 */
	private final Skill SKILL = SkillTable.getInstance().getInfo(5086, 1);
	/**
	 * Field _waitTime.
	 */
	private long _waitTime;
	/**
	 * Field TICK_IN_MILISECONDS. (value is 15000)
	 */
	private static final int TICK_IN_MILISECONDS = 15000;
	
	/**
	 * Constructor for SprigantPoison.
	 * @param actor NpcInstance
	 */
	public SprigantPoison(NpcInstance actor)
	{
		super(actor);
	}
	
	/**
	 * Method thinkActive.
	 * @return boolean
	 */
	@Override
	protected boolean thinkActive()
	{
		if (System.currentTimeMillis() > _waitTime)
		{
			final NpcInstance actor = getActor();
			actor.doCast(SKILL, actor, false);
			_waitTime = System.currentTimeMillis() + TICK_IN_MILISECONDS;
			return true;
		}
		return false;
	}
}
