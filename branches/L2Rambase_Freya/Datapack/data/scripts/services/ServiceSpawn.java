package services;

import l2.rambase.gameserver.model.quest.Quest;

public class ServiceSpawn extends Quest
{
	private static final String qn = "ServiceSpawn";
		
	/**
	 * Couple Manager
	 * @param custom spawn
	 */
	private static final boolean CM_SPAWN = true;
	private static final int COUPLE_NPC = 7100;	
	private static final int CM_SpawnLocs[][] = 
	{
		{ 83684, 147992, -3413, 32767 }, 	// Giran
		{ 147852,25927,-2015,52358 }, 		// Aden
		{ 147396,-55385,-2736,58144 }, 		// Goddard
		{ 43225,-48483,-799,17899 }  		// Rune
	};
	private static final boolean WM_SPAWN = true;
	private static final int WEDDING_NPC = 50007;	
	private static final int WM_SpawnLocs[][] = 
	{
		{ 83695,148858,-3400,32767 } 		// Giran
	};
	
	/**
	 * Friend Manager
	 * @param custom spawn
	 */
	private static final boolean FM_SPAWN = true;
	private static final int FRIEND_NPC = 7108;
	private static final int FM_SpawnLocs[][] = 
	{
		{ 81938,148359,-3464,46596 }, 		// Giran
		{ 147910,25930,-2015,52358 }, 		// Aden
		{ 147420,-55349,-2736,58144 }, 		// Goddard
		{ 43170,-48482,-799,17899 }    		// Rune
	};
	/**
	 * Mission Map
	 * @param custom spawn
	 */
	private static final boolean MM_SPAWN = true;
	private static final int MISSION_NPC = 7101;
	private static final int MM_SpawnLocs[][] = 
	{
		{ 83661, 149252, -3413, 32189 }, 	// Giran
		{ 147962,25931,-2015,52358 }, 		// Aden
		{ 147338,-55417,-2736,58144 }, 		// Goddard
		{ 43139,-48490,-799,17899 }   		// Rune
	};
	/**
	 * PcCafe Manager
	 * @param custom spawn
	 */
	private static final boolean PC_SPAWN = true;
	private static final int PC_CAFE_NPC = 7109;
	private static final int PC_SpawnLocs[][] = 
	{
		{ 81916, 148847, -3469, 15689 }, 	// Giran
		{ 148029,25938,-2015,52358 }, 		// Aden
		{ 147331,-55511,-2728,58144 }, 		// Goddard
		{ 43083,-48498,-799,17899 }    		// Rune
	};
	/**
	 * Saga Manager
	 * @param custom spawn
	 */
	private static final boolean SAGA_SPAWN = true;
	private static final int SAGA_NPC = 70012;
	private static final int SAGA_SpawnLocs[][] = 
	{
		{ 44207,-47608,-905,53375 },		// Rune
		{ 147132,28585,-2277,54566 }, 		// Aden
		{ 149030,-55817,-2789,49151 },		// Goddard
		{ 81084,147742,-3477,7657 }    		// Giran
	};
	
	public ServiceSpawn(int questId, String name, String descr)
	{
		super(questId, name, descr);
		checkEvent(CM_SPAWN,COUPLE_NPC, CM_SpawnLocs);
		checkEvent(WM_SPAWN,WEDDING_NPC, WM_SpawnLocs);
		checkEvent(FM_SPAWN,FRIEND_NPC, FM_SpawnLocs);
		checkEvent(MM_SPAWN,MISSION_NPC, MM_SpawnLocs);
		checkEvent(PC_SPAWN,PC_CAFE_NPC, PC_SpawnLocs);
		checkEvent(SAGA_SPAWN,SAGA_NPC, SAGA_SpawnLocs);
	}
	private void checkEvent(boolean event,int npcId,int[][] loc)
	{
		if (event)
		{
			try
			{
				SpawnEventNpc(npcId,loc);				
			}
			catch (Exception e)
			{				
			}
		}
	}
	
	private void SpawnEventNpc(int npcId,int[][] loc) 
	{
		for (int i=0; i < loc.length; i++)
		{			
			try 
			{
				addSpawn(npcId, loc[i][0], loc[i][1], loc[i][2], loc[i][3], false, 0, false);
			}
			catch (Exception e) 
			{
				_log.warning("ServiceNpcSpawn: Spawns could not be initialized: "	+ e);
			}
		}
	}
	
	public static void main(String[] args)
	{
		new ServiceSpawn(-1,qn,"events");
		_log.warning("Service System: Spawn services Npcs");
	}
}