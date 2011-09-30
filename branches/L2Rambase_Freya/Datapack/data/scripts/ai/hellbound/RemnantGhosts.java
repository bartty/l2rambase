/*
 * L2Rambase Project Project
 */
package ai.hellbound;

import java.util.Collection;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;

/**
 * OutpostCaptain
 * @author L2Rambase Project Project 
 */
public class RemnantGhosts extends L2AttackableAIScript
{	
	/*
	 * Mobs & Npcs
	 */
	private static final int[] Remnants = { 18463, 18464 };
	private static final int Remnant_Wraith = 22330;
	private static final int Derek = 18435;
	
	/*
	 * Location List
	 */
	private static final int[][] RemnantSpawns = 
	{
			{ 18464, -28681, 255110, -2160, 17318, 120 },
			{ 18464, -26114, 254708, -2139, 6976, 120 },
			{ 18463, -28457, 256584, -1926, 59926, 120 },
			{ 18463, -26482, 257663, -1925, 35352, 120 },
			{ 18464, -26453, 256745, -1930, 50259, 120 },
			{ 18463, -27362, 256282, -1935, 47936, 120 },
			{ 18464, -25441, 256441, -2147, 48011, 120 }
	};
	 
	/*
	 * Options
	 */

	
	public RemnantGhosts(int questId, String name, String descr)
	{        
		super(questId, name, descr);
		for (int id: Remnants)
		{
		    addAttackId(id);
		    addSkillSeeId(id);
		}
		addSkillSeeId(Derek);
		addAttackId(Derek);
		addKillId(Remnant_Wraith);
		int hellboundLevel = HellboundManager.getInstance().getLevel();
        // I am leaving the remnants up until Derek gets killed at level 4
        // I know it's not exactly like on retail, but I think it makes more sense =)
        if (hellboundLevel <= 4)
        {
          for (int i=0; i < 7; i++)
          {
            HellboundManager.getInstance().addSpawn(RemnantSpawns[i][0], RemnantSpawns[i][1], RemnantSpawns[i][2], RemnantSpawns[i][3], RemnantSpawns[i][4], RemnantSpawns[i][5]);
          }
        }
        Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
        if (hellboundLevel > 4)
        {
        	for (L2Spawn i:  worldObjects)
     	   {
                int npcId = i.getNpcid();
                if (contains(Remnants,npcId))
                {
                    L2Npc curNpc = i.getLastSpawn();
                    curNpc.deleteMe();
                }
            }
        }
	}
	
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{		
		int npcId = npc.getNpcId();
		QuestState st = player.getQuestState("RemnantGhosts");
        if (st == null)
        {
            st = newQuestState(player);
        }
        if (npcId == Remnant_Wraith)
        {
        	if (HellboundManager.getInstance().getLevel() >= 4)
        		return super.onKill(npc, player, isPet);
        	HellboundManager.getInstance().increaseTrust(5);
        }
		return super.onKill(npc, player, isPet);
	}
	
	public String onAttack (L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
    {
		int npcId = npc.getNpcId();
        QuestState st = attacker.getQuestState("RemnantGhosts");
        if (st == null)
        {
            st = newQuestState(attacker);
        }
        if (contains(Remnants,npcId))
        {
            double npcHp = npc.getCurrentHp();
            double npcMaxHp = npc.getMaxHp();
            double triggerHp = (npcMaxHp * 3) / 100;
            if (npcHp <= triggerHp)
            {
                npc.setIsInvul(true);
            }
        }
		return super.onAttack(npc,attacker,damage,isPet);
    }
	
	public String onSkillSee (L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet) 
    {
		if (targets == null)
			return super.onSkillSee(npc,caster,skill,targets,isPet);
        int npcId = npc.getNpcId();
        if (skill.getId() == 2358)
        {
            npc.setIsInvul(false);
            npc.onDecay();
            if (contains(Remnants,npcId))
            {
                if (HellboundManager.getInstance().getLevel() < 4)
                {
                	if (contains (Remnants,npcId))
                	{
                		HellboundManager.getInstance().increaseTrust(5);
                		caster.sendMessage("The holy water affects Remnants Ghost. You have freed his soul.");
                	}
                }                	
                else if (HellboundManager.getInstance().getLevel() >= 4)
                {
                    caster.sendMessage("The holy water affects Remnants Ghost. You have freed his soul.");
                }
            }
            if (npcId == Derek)
            {
            	HellboundManager.getInstance().increaseTrust(10000);
                HellboundManager.getInstance().changeLevel(5);
                caster.sendMessage("The holy water affects Derek. You have freed his soul.");
            }
        }
		return super.onSkillSee(npc,caster,skill,targets,isPet);
    }
	public static void main(String[] args)
    {
		new EnchantedMegaliths(-1,"RemnantGhosts","ai");
    }
}