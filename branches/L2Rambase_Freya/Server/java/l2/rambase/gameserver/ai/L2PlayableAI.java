/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.L2Character.AIAccessor;

import l2.rambase.gameserver.network.SystemMessageId;


public abstract class L2PlayableAI extends L2CharacterAI
{

	/**
     * @param accessor
     */
    public L2PlayableAI(AIAccessor accessor)
    {
	    super(accessor);
    }
    
    
    /**
     * @see l2.rambase.gameserver.ai.L2CharacterAI#onIntentionAttack(l2.rambase.gameserver.model.actor.L2Character)
     */
    @Override
    protected void onIntentionAttack(L2Character target)
    {
    	if (target instanceof L2Playable)
		{
			if (target.getActingPlayer().getProtectionBlessing()
			        && (_actor.getActingPlayer().getLevel() - target.getActingPlayer().getLevel()) >= 10
			        && _actor.getActingPlayer().getKarma() > 0
			        && !(target.isInsideZone(L2Character.ZONE_PVP)))
			{
				// If attacker have karma and have level >= 10 than his target and target have
				// Newbie Protection Buff,
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				return;
			}
			
			if (_actor.getActingPlayer().getProtectionBlessing()
			        && (target.getActingPlayer().getLevel() - _actor.getActingPlayer().getLevel()) >= 10
			        && target.getActingPlayer().getKarma() > 0
			        && !(target.isInsideZone(L2Character.ZONE_PVP)))
			{
				// If target have karma and have level >= 10 than his target and actor have
				// Newbie Protection Buff,
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				return;
			}
			
			if (target.getActingPlayer().isCursedWeaponEquipped()
			        && _actor.getActingPlayer().getLevel() <= 20)
			{
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				return;
			}
			
			if (_actor.getActingPlayer().isCursedWeaponEquipped()
			        && target.getActingPlayer().getLevel() <= 20)
			{
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				return;
			}
		}
    	
        super.onIntentionAttack(target);
    }
    
    /**
     * @see l2.rambase.gameserver.ai.L2CharacterAI#onIntentionCast(l2.rambase.gameserver.model.L2Skill, l2.rambase.gameserver.model.L2Object)
     */
    @Override
    protected void onIntentionCast(L2Skill skill, L2Object target)
    {
    	if (target instanceof L2Playable && skill.isOffensive())
		{
			if (target.getActingPlayer().getProtectionBlessing()
			        && (_actor.getActingPlayer().getLevel() - target.getActingPlayer().getLevel()) >= 10
			        && _actor.getActingPlayer().getKarma() > 0
			        && !(((L2Playable) target).isInsideZone(L2Character.ZONE_PVP)))
			{
				// If attacker have karma and have level >= 10 than his target and target have
				// Newbie Protection Buff,
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				_actor.setIsCastingNow(false);
				return;
			}
			
			if (_actor.getActingPlayer().getProtectionBlessing()
			        && (target.getActingPlayer().getLevel() - _actor.getActingPlayer().getLevel()) >= 10
			        && target.getActingPlayer().getKarma() > 0
			        && !(((L2Playable) target).isInsideZone(L2Character.ZONE_PVP)))
			{
				// If target have karma and have level >= 10 than his target and actor have
				// Newbie Protection Buff,
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				_actor.setIsCastingNow(false);
				return;
			}
			
			if (target.getActingPlayer().isCursedWeaponEquipped()
			        && _actor.getActingPlayer().getLevel() <= 20)
			{
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				_actor.setIsCastingNow(false);
				return;
			}
			
			if (_actor.getActingPlayer().isCursedWeaponEquipped()
			        && target.getActingPlayer().getLevel() <= 20)
			{
				_actor.getActingPlayer().sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
				clientActionFailed();
				_actor.setIsCastingNow(false);
				return;
			}
		}
    	
        super.onIntentionCast(skill, target);
    }
	
}
