/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

/**
 * This interface provides method to handle triggered skills from other objects.<br>
 * For example, other skill, an effect, etc...
 */
public interface IChanceSkillTrigger
{
	/**
	 * Just a flag
	 */
	public boolean triggersChanceSkill();
	
	/**
	 * Triggered Id
	 */
	public int getTriggeredChanceId();
	
	/**
	 * Triggered level
	 */
	public int getTriggeredChanceLevel();
	
	/**
	 * Chance condition object
	 */
	public ChanceCondition getTriggeredChanceCondition();
}