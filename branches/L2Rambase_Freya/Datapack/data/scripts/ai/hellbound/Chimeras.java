/*
 * L2Rambase Project Project
 */
package ai.hellbound;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2Rambase ProjectonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;

/**
 * Chimeras 
 * @author L2Rambase Project Project
 */
public class Chimeras extends L2AttackableAIScript
{
	
	/*
	 * List of Monsters
	 */
	private static final int[] Chimera = { 22349, 22350, 22351, 22352 };
	private static final int Celtus = 22353;
	/*
	 * List of items
	 */
	private static final int Dim_Life_Force = 9680;
	private static final int Life_Force = 9681;
	private static final int Contained_Life_Force = 9682;
	/*
	 * List of Skills	 * 
	 */
	private static final int Magic_Bottle = 2359;
	/*
	 * Localization list
	 */
	private final int _Location[][] = { 
			{ 4276, 237245, -3310 },
			{ 11437, 236788, -1949 },
			{ 7647, 235672, -1977 },
			{ 1882, 233520, -3315 }	
	};
	
	public Chimeras(int questId, String name, String descr)
	{        
		super(questId, name, descr);		
		addSkillSeeId(Celtus);
		for (int id : Chimera)
		{
			addSkillSeeId(id);
		}
		if (HellboundManager.getInstance().getLevel() >= 7)
		{
			int respTime = (18 + Rnd.get(36)) * 100;
			switch(Rnd.get(4))
			{
				case 1:
					HellboundManager.getInstance().addSpawn(Celtus, _Location[0][0], _Location[0][1], _Location[0][2], 0, respTime);
					break;
				case 2:
					HellboundManager.getInstance().addSpawn(Celtus, _Location[1][0], _Location[1][1], _Location[1][2], 0, respTime);
					break;
				case 3:
					HellboundManager.getInstance().addSpawn(Celtus, _Location[2][0], _Location[2][1], _Location[2][2], 0, respTime);
					break;					
				case 4:
					HellboundManager.getInstance().addSpawn(Celtus, _Location[3][0], _Location[3][1], _Location[3][2], 0, respTime);
					break;
				default:
					break;
			}
		}
	}
	
	public String onSkillSee (L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet) 
    { 
		if (targets[0] == null)
		{
			return super.onSkillSee(npc,caster,skill,targets,isPet);
		}
		int npcId = npc.getNpcId();
		int maxHp = npc.getMaxHp();
		double nowHp = npc.getCurrentHp();
		if (nowHp < maxHp *0.1)
		{
			if (skill.getId() == Magic_Bottle)
			{
				if (HellboundManager.getInstance().getLevel() >= 7)
				{
					if (contains(Chimera, npcId))
					{
						if (Rnd.get(100) < 10)
						{
							((L2Rambase ProjectonsterInstance)npc).dropItem(caster, Life_Force, 1);
						}
						((L2Rambase ProjectonsterInstance)npc).dropItem(caster, Dim_Life_Force, 1);
						npc.onDecay();
					}
					if (npcId == Celtus)
					{
						((L2Rambase ProjectonsterInstance)npc).dropItem(caster, Contained_Life_Force, 1);
						npc.onDecay();
					}
				}
			}
		}		
        return super.onSkillSee(npc,caster,skill,targets,isPet);
    }

	public static void main(String[] args)
    {
		new Chimeras(-1,"Chimeras","ai");
    }
}