/*
 * L2Rambase Project Project
 */
package ai.hellbound;

import java.util.Map;

import javolution.util.FastMap;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;

/**
 * Enchanted Megaliths 
 * @author L2Rambase Project Project
 */
public class EnchantedMegaliths extends L2AttackableAIScript
{	
	private Map<Integer,L2Spawn> SpawnedMobs;
	
	/*
	 * Mobs & Npcs
	 */
	private static final int Megalith = 18484; 
	private static final int Hellinark = 22326;
	private static final int[] KillMobsIds = { 18484, 22326 };
	/*
	 * Location List
	 */
	private static final int[][] MegalithSpawnLocs = 
	{
		{ -24542, 245792, -3133, 19078 },
		{ -23839, 246056, -3133, 17772 },
		{ -23713, 244358, -3133, 53369 },
		{ -23224, 244524, -3133, 57472 },
		{ -24709, 245186, -3133, 63974 },
		{ -24394, 244379, -3133, 5923 }
	};
	private static final int[] HellinarkSpawn = { -24430, 244764, -3133 };
	
	/*
	 * Options
	 */
	private int HellboundLeveL = HellboundManager.getInstance().getLevel();
	
	public EnchantedMegaliths(int questId, String name, String descr)
	{        
		super(questId, name, descr);		
		addAttackId(Megalith);
		for (int id : KillMobsIds)
		{
			addKillId(id);
		}
		SpawnedMobs = null;
        if (HellboundLeveL < 6)
            startQuestTimer("levelCheckMeg", 60000, null, null, true);
        int megaliths = 6;
        try
        {
            megaliths = Integer.valueOf(loadGlobalQuestVar("megaliths_portals"));
        }
        catch (Exception e)
        {        	
        }
        saveGlobalQuestVar("megaliths_portals", String.valueOf(megaliths));
        if (megaliths <= 0)
        {
            HellboundManager.getInstance().setMegalithsCompleted(true);
        }
        if (HellboundLeveL == 6)
        {
            //spawn the megaliths and hellinark
            HellboundManager.getInstance().addSpawn(Hellinark, HellinarkSpawn[0], HellinarkSpawn[1], HellinarkSpawn[2], 5000, 0);
            if (megaliths > 0)
            {
            	for (int i=0; i < MegalithSpawnLocs.length; i++)
            	{
            		Map <Integer,L2Spawn> SpawnedMobs = new FastMap <Integer,L2Spawn>();
            		int respawnTime = Rnd.get(90, 180); // between 90 seconds and 3 minutes
                    L2Spawn newMegalith = HellboundManager.getInstance().addSpawn(Megalith, MegalithSpawnLocs[i][0], MegalithSpawnLocs[i][1], MegalithSpawnLocs[i][1], MegalithSpawnLocs[i][2], respawnTime);
                    SpawnedMobs.put(i,newMegalith);
            	}
            }
        }
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		if (event.equalsIgnoreCase("levelCheckMeg"))
		{				
			int hellboundLevel = HellboundManager.getInstance().getLevel();
			if (hellboundLevel > HellboundLeveL && hellboundLevel == 6)
			{
				HellboundLeveL = 6;	        	  
				int megaliths = 6;
				try
				{
					megaliths = Integer.valueOf(loadGlobalQuestVar("megaliths_portals"));
				}	
				catch (Exception e)
				{	            	
				}
				saveGlobalQuestVar("megaliths_portals", String.valueOf(megaliths));
				if (megaliths <= 0)
				{
					HellboundManager.getInstance().setMegalithsCompleted(true);
					for (int i=0; i < SpawnedMobs.size(); i++)
					{
						SpawnedMobs.get(Integer.valueOf(i)).stopRespawn();
					}
				}                    
				if (HellboundLeveL == 6)
	            {
	                cancelQuestTimers("levelCheckMeg");	                
	                HellboundManager.getInstance().addSpawn(Hellinark, HellinarkSpawn[0], HellinarkSpawn[1], HellinarkSpawn[2], 5000, 0);
	                if (megaliths > 0)
	                {
	                	for (int i=0; i < MegalithSpawnLocs.length; i++)
	                	{
	                		Map <Integer,L2Spawn> SpawnedMobs = new FastMap <Integer,L2Spawn>();
	                		int respawnTime = Rnd.get(90, 180); // between 90 seconds and 3 minutes
	                        L2Spawn newMegalith = HellboundManager.getInstance().addSpawn(Megalith, MegalithSpawnLocs[i][0], MegalithSpawnLocs[i][1], MegalithSpawnLocs[i][1], MegalithSpawnLocs[i][2], respawnTime);
	                        SpawnedMobs.put(i,newMegalith);
	                	}
	                }
	            }
	          }
		}
		return htmltext;
	}
	
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{		
		int hellboundLevel = HellboundManager.getInstance().getLevel();
        if (hellboundLevel != 6)
        {
        	return super.onKill(npc, player, isPet);
        }
        int npcId = npc.getNpcId();
        if (npcId == Hellinark)
        {
            HellboundManager.getInstance().increaseTrust(10000);
            HellboundManager.getInstance().changeLevel(7);
            HellboundManager.getInstance().setMegalithsCompleted(true);            
            return super.onKill(npc, player, isPet);
        }
        if (npcId == 18484)
        {
            int delNum = 4000;
            int mobsKilled = 0;
            try
            {
              // add the mobs to the leveling system(points)
              mobsKilled = Integer.valueOf(loadGlobalQuestVar("megaliths_killed"));
            }
            catch (Exception e)
            {            	
            }             
            mobsKilled += 1;
            
            if (mobsKilled >= 500)
            {
                mobsKilled = 0;
                // actual number saved will be 1++, when in the array it will be 0++
                int megaliths = Integer.valueOf(loadGlobalQuestVar("megaliths_portals"));
                delNum = megaliths - 1;
                for (int i=0; i < delNum; i++)
				{
					SpawnedMobs.get(Integer.valueOf(i)).stopRespawn();
				}
                megaliths = megaliths - 1;
                if (megaliths < 0)
                {
                	return super.onKill(npc, player, isPet);
                }
                saveGlobalQuestVar("megaliths_portals", String.valueOf(megaliths));
            }
            boolean megalithCompleted = HellboundManager.getInstance().checkMegalithsCompleted();
            if (megalithCompleted)
            {
            	return super.onKill(npc, player, isPet);
            }
            saveGlobalQuestVar("megaliths_killed", String.valueOf(mobsKilled));
        }
        return super.onKill(npc, player, isPet);
	}		

	public static void main(String[] args)
    {
		new EnchantedMegaliths(-1,"EnchantedMegaliths","ai");
    }
}