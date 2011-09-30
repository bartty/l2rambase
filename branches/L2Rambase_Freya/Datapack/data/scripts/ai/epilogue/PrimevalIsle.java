/**
 * 
 * @author L2Rambase Project Project
 */
package ai.epilogue;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.Elementals;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.templates.skills.L2SkillType;
import l2.rambase.util.Rnd;

import ai.group_template.L2AttackableAIScript;

public class PrimevalIsle extends L2AttackableAIScript
{	
	private static final int[] mobs = 
	{
		22196,22197,22198,22199,22200,
		22201,22202,22203,22204,22205,
		22208,22209,22210,22211,22212,
		22213,22214,22215,22216,22217,
		22218,22219,22220,22221,22222,
		22223,22224,22225,22226,22227,
		22742,22743,22744,22745
	};

	/**
	 * Debuff Skills
	 */
	private static final int CANCEL_SKILL = 4673;
	private static final int ROOT_SKILL = 4658;
	private static final int SILENCE_SKILL = 5177;
	private static final int HEAL_SELF_SKILL = 4065;
	/**
	 * Force Attack
	 */
	private static final int FORCE_ATTACK = 4032;
	/**
	 * AntiElemental Skills
	 */
	private static final int ANTI_ELEMENTAL_SKILL_1 = 1353;
	private static final int ANTI_ELEMENTAL_SKILL_2 = 1352;	
	private static final int ANTI_ELEMENTAL_SKILL_3 = 1189;
	/**
	 * Drain Skill
	 */
	private static final int DRAIN_MP = 4039;
	private static final int DRAIN_HP = 4002;
	
	public String onSkillSee (L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet) 
    { 
        int npcId = npc.getNpcId();
        if (!contains(targets,npc))
    	{
    		return super.onSkillSee(npc,caster,skill,targets,isPet);
    	}
        L2Character originalAttackTarget = (isPet? caster.getPet(): caster);
		if (contains(mobs,npcId))
        {
	        if (npc.isCastingNow())
	        {
	        	return super.onSkillSee(npc,caster,skill,targets,isPet);
	        }
			// 1.If player cast debuff (root,silence,fear,paralyze) - mob use force attack on this player
			L2Skill npcSkill;
			L2SkillType skillType = skill.getEffectType();
			if (skillType == L2SkillType.DEBUFF 
					|| skillType == L2SkillType.ROOT
					|| skillType == L2SkillType.FEAR
					|| skillType == L2SkillType.MUTE
			) 				
			{
				npc.setTarget(originalAttackTarget);
				// Npc cast skill: NPC Strike (4032 lvl 10)
				npcSkill = SkillTable.getInstance().getInfo(FORCE_ATTACK, 10);
				npc.doCast(npcSkill);
				return super.onSkillSee(npc,caster,skill,targets,isPet);
			}
			// 2.If player use heal on another player mob use cancel
			else if (skillType == L2SkillType.HEAL
					|| skillType == L2SkillType.HEAL_PERCENT
					|| skillType == L2SkillType.HPMPCPHEAL_PERCENT
					|| skillType == L2SkillType.HPMPHEAL_PERCENT)
			{
				npc.setTarget(originalAttackTarget.getTarget());
				// Npc cast skill: Npc Dispel Offensive Buff (4673 lvl 10)
				npcSkill = SkillTable.getInstance().getInfo(CANCEL_SKILL, 10);
				npc.doCast(npcSkill);
				return super.onSkillSee(npc,caster,skill,targets,isPet);
			}
			// 3.If Npc have 30% hp or lower then npc have 15% of chance to cast 30 second UD
			int maxNpcHP = npc.getMaxHp();
			double nowNpcHp = npc.getCurrentHp();
			double percent30NpcHp = maxNpcHP / 0.3;			
			if (nowNpcHp < percent30NpcHp)
			{
				// Npc cast skill: Silence 
				npcSkill = SkillTable.getInstance().getInfo(SILENCE_SKILL, 10);				
				if (Rnd.get(100)<15)
				{
					npc.setTarget(caster);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
			}			
			// 4.Npc cast anti-elemental skill when player uses elemental skill on npc.
			if (skill.getElementPower() > 0)
			{
				if (skill.getElement() == Elementals.DARK)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1353 lvl 1)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_1, 1);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
				else if(skill.getElement() == Elementals.EARTH)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1352 lvl 1)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_2, 1);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
				else if(skill.getElement() == Elementals.FIRE)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1352 lvl 1)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_2, 1);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
				else if(skill.getElement() == Elementals.HOLY)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1353 lvl 1)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_1, 1);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
				else if(skill.getElement() == Elementals.WATER)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1189 lvl 3)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_3, 3);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
				else if(skill.getElement() == Elementals.WIND)
				{
					npc.setTarget(npc);
					// Npc cast skill:  (1352 lvl 1)
					npcSkill = SkillTable.getInstance().getInfo(ANTI_ELEMENTAL_SKILL_2, 1);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}				
			}
			//5. If player is magician drain mp, if fighter give debuffs - 50% chance
			if (caster.isMageClass())
			{
				int randomDrain = Rnd.get(100);
				if (randomDrain <50)
				{
					npc.setTarget(originalAttackTarget.getTarget());
					// Cast drain on player 
					npcSkill = SkillTable.getInstance().getInfo(DRAIN_MP, 12);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
			}
			else
			{
				int randomDrain = Rnd.get(100);
				if (randomDrain <50)
				{
					npc.setTarget(originalAttackTarget.getTarget());
					// Cast drain on player
					npcSkill = SkillTable.getInstance().getInfo(DRAIN_HP, 12);
					npc.doCast(npcSkill);
					return super.onSkillSee(npc,caster,skill,targets,isPet);
				}
			}
			
        }
        return super.onSkillSee(npc,caster,skill,targets,isPet);
    }
	
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet, L2Skill skill)
	{
		int npcId = npc.getNpcId();
        if (!contains(mobs,npcId))
    	{
        	return super.onAttack(npc, player, damage, isPet);
    	}
        L2Character originalAttackTarget = (isPet? player.getPet(): player);
		if (contains(mobs,npcId))
        {
			if (npc.isCastingNow())
	        {
				return super.onAttack(npc, player, damage, isPet);
	        }
	        int random = Rnd.get(100);
			// 1. npc debuff player when npc have 70-50%
			int maxNpcHP = npc.getMaxHp();
			double nowNpcHp = npc.getCurrentHp();
			double percent70NpcHp = maxNpcHP / 0.7;
			double percent50NpcHp = maxNpcHP / 0.5;			
			if (nowNpcHp > percent50NpcHp && nowNpcHp < percent70NpcHp)
			{
				if (random < 50)
				{
					npc.setTarget(originalAttackTarget);
					int randomDebuff = Rnd.get(0,5);
					switch(randomDebuff)
					{
						case 0:
						case 1:
						case 2:
							npc.doCast(SkillTable.getInstance().getInfo(CANCEL_SKILL, 10));
							break;
						case 3:
							npc.doCast(SkillTable.getInstance().getInfo(ROOT_SKILL, 10));
							break;
						case 4:
						case 5:
							npc.doCast(SkillTable.getInstance().getInfo(SILENCE_SKILL, 10));
							break;
						default:
							break;
					}
				}
			}
			// 2. npc use heal self when have lower than 30% -- chance 70%
			double percent30NpcHp = maxNpcHP / 0.3;
			if (nowNpcHp < percent30NpcHp)
			{
				if (random < 70)
				{
					npc.doCast(SkillTable.getInstance().getInfo(HEAL_SELF_SKILL, 10));
					return super.onAttack(npc, player, damage, isPet);
				}
			}			
			// 3. npc search healer in party and give skill damage -- chance 60%
			if (player.getParty() != null)
			{
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					if (random < 60)
					{
						if (partyMember.getClassId().getId() == 16
								|| partyMember.getClassId().getId() == 97
								|| partyMember.getClassId().getId() == 30
								|| partyMember.getClassId().getId() == 105
								|| partyMember.getClassId().getId() == 43
								|| partyMember.getClassId().getId() == 112
								|| partyMember.getClassId().getId() == 51
								|| partyMember.getClassId().getId() == 115)
						{
							npc.setTarget(partyMember);
							npc.doCast(SkillTable.getInstance().getInfo(FORCE_ATTACK, 10));
							return super.onAttack(npc, player, damage, isPet);
						}
					}
				}
			}
			//4. If player is magician drain mp, if fighter give debuffs - 50% chance
			int randomDrain = Rnd.get(100);
			if (randomDrain <50)
			{
				npc.setTarget(originalAttackTarget.getTarget());
				// Cast drain on player
				npc.doCast(SkillTable.getInstance().getInfo(DRAIN_HP, 12));
				return super.onAttack(npc, player, damage, isPet);
			}			
        }
		return super.onAttack(npc, player, damage, isPet);
	}
	
	public PrimevalIsle(int questId, String name, String descr)
	{
		super(questId, name, descr);
		this.registerMobs(mobs);
	}

	public static void main(String[] args)
	{
		new PrimevalIsle(-1, "PrimevalIsle", "ai");
	}
}
