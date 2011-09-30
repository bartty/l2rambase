package ai.hotsprings;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;

/**
 * Hot Spring Disease AI
 * 
 * @author L2Rambase Project Project
 */
public class Hotsprings extends L2AttackableAIScript
{
	//NPCs Implementation
	static final int[] malaria_mobs = { 21314, 21316, 21317, 21319, 21321, 21322 }; // Monsters which cast Hot Spring Malaria (4554)
	static final int[] flu_mobs = { 21317, 21322 }; // Monsters which cast Hot Springs Flu (4553)
	static final int[] cholera_mobs = { 21316, 21319 }; // Monsters which cast Hot Springs Cholera (4552)
	static final int[] rheumatism_mobs = { 21314, 21321 }; // Monsters which cast Hot Springs Rheumatism (4551)
	
	//SKILLs Implementation
	private static final int MALARIA_CHANCE = 15;
	private static final int FLU_CHANCE = 15;
	private static final int CHOLERA_CHANCE = 15;
	private static final int RHEUMATISM_CHANCE = 15;
	private static final int MALARIA = 4554;
	private static final int FLU = 4553;
	private static final int CHOLERA = 4552;
	private static final int RHEUMATISM = 4551;

	public Hotsprings(int questId, String name, String descr)
	{
		super(questId, name, descr);
		registerMobs(malaria_mobs);
		registerMobs(flu_mobs);
		registerMobs(cholera_mobs);
		registerMobs(rheumatism_mobs);
	}

	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
	{
		if (contains(malaria_mobs, npc.getNpcId()))
		{
			if (attacker.getFirstEffect(MALARIA) != null)
			{
		        int malaria_level = attacker.getFirstEffect(MALARIA).getLevel();
		        if (malaria_level == 1 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		          SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 2 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 3 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 4 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 5 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 6 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 7 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 8 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 9 && Rnd.get(1000) < (MALARIA_CHANCE - malaria_level))
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,malaria_level + 1).getEffects(attacker,attacker);
		        }
		        if (malaria_level == 10)
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,10).getEffects(attacker,attacker);
		        }
			}
			else
			{
		        if (Rnd.get(1000) < MALARIA_CHANCE)
		        {
		        	SkillTable.getInstance().getInfo(MALARIA,1).getEffects(attacker,attacker);
		        }
			}
		}
		if (contains(flu_mobs, npc.getNpcId()))
		{
			if (attacker.getFirstEffect(FLU) != null)
			{
		        int flu_level = attacker.getFirstEffect(FLU).getLevel();
		        if (flu_level == 1 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		          SkillTable.getInstance().getInfo(FLU,2).getEffects(attacker,attacker);
		        }
		        if (flu_level == 2 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,3).getEffects(attacker,attacker);
		        }
		        if (flu_level == 3 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 4 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 5 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 6 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 7 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 8 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 9 && Rnd.get(1000) < (FLU_CHANCE - flu_level))
		        {
		        	SkillTable.getInstance().getInfo(FLU,flu_level + 1).getEffects(attacker,attacker);
		        }
		        if (flu_level == 10)
		        {
		        	SkillTable.getInstance().getInfo(FLU,10).getEffects(attacker,attacker);
		        }
			}
			else
			{
		        if (Rnd.get(1000) < FLU_CHANCE)
		        {
		        	SkillTable.getInstance().getInfo(FLU,1).getEffects(attacker,attacker);
		        }
			}
		}
		if (contains(cholera_mobs, npc.getNpcId()))
		{
			if (attacker.getFirstEffect(CHOLERA) != null)
			{
		        int cholera_level = attacker.getFirstEffect(CHOLERA).getLevel();
		        if (cholera_level == 1 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		          SkillTable.getInstance().getInfo(CHOLERA,2).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 2 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,3).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 3 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 4 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 5 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 6 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 7 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 8 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 9 && Rnd.get(1000) < (CHOLERA_CHANCE - cholera_level))
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,cholera_level + 1).getEffects(attacker,attacker);
		        }
		        if (cholera_level == 10)
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,10).getEffects(attacker,attacker);
		        }
			}
			else
			{
		        if (Rnd.get(1000) < CHOLERA_CHANCE)
		        {
		        	SkillTable.getInstance().getInfo(CHOLERA,1).getEffects(attacker,attacker);
		        }
			}
		}
		if (contains(rheumatism_mobs, npc.getNpcId()))
		{
			if (attacker.getFirstEffect(RHEUMATISM) != null)
			{
		        int rheumatism_level = attacker.getFirstEffect(RHEUMATISM).getLevel();
		        if (rheumatism_level == 1 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		          SkillTable.getInstance().getInfo(RHEUMATISM,2).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 2 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,3).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 3 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 4 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 5 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 6 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 7 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 8 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 9 && Rnd.get(1000) < (RHEUMATISM_CHANCE - rheumatism_level))
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,rheumatism_level + 1).getEffects(attacker,attacker);
		        }
		        if (rheumatism_level == 10)
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,10).getEffects(attacker,attacker);
		        }
			}
			else
			{
		        if (Rnd.get(1000) < RHEUMATISM_CHANCE)
		        {
		        	SkillTable.getInstance().getInfo(RHEUMATISM,1).getEffects(attacker,attacker);
		        }
			}
		}
		return super.onAttack(npc, attacker, damage, isPet);
	}

	public static void main(String[] args)
	{
		new Hotsprings(-1, "Hotsprings", "ai");
	}
}