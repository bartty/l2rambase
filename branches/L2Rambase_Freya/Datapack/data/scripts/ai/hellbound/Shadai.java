package ai.hellbound;

import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.GameTimeController;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
//import l2.rambase.gameserver.network.clientpackets.Say2;
//import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.util.Rnd;

/**
 * Shadai 
 * @author L2Rambase Project Project
 */
public class Shadai extends Quest
{
	/*
	 * List of values
	 */
	private static final int Shadai = 32347;
	private static final int[] spawnLoc = { 8962,253278,-1932,0 };
	private static final int spawnChance = 40;
	private static final int spawnHour = 24;
	private static final int spawnDuration = 3600000; // 1 hour
	
	
	public Shadai(int questId, String name, String descr)
	{		
		super(questId, name, descr);
		int targetHour = (((spawnHour - getTimeHour() * 60) * 60) * 1000);		
        startQuestTimer("shadai_spawn", targetHour, null, null);        
	}
	
	public int getTimeHour()
	{
		return (GameTimeController.getInstance().getGameTime() / 60) % 24;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";		
		if (event.equalsIgnoreCase("shadai_spawn"))
		{			
			int nextSpawnTime = 86400000;
			startQuestTimer("shadai_spawn", nextSpawnTime, null, null);
			int randChance = Rnd.get(100);
			if (randChance <= spawnChance && HellboundManager.getInstance().getLevel() > 2)
			{
				startQuestTimer("shadai_announce", 100, null, null);
				addSpawn(Shadai, spawnLoc[0], spawnLoc[1], spawnLoc[2], spawnLoc[3], false, spawnDuration);
			}
		}
        if (event.equalsIgnoreCase("shadai_announce"))
        {
        	//String text = "The Legendary Blacksmith has just appeared.";
        	//npc.broadcastPacket(new CreatureSay(npc.getObjectId(),Say2.SHOUT,npc.getName(),text));
        	Announcements.getInstance().announceToAll("The Legendary Blacksmith Shadai has just appeared.",1);
        }
		return htmltext;
	}

	public static void main(String[] args)
    {
		new Shadai(-1,"Shadai","ai");
    }
}