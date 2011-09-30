/**
 * @author Deyoun
 * @category need rewrite
 * 
 * Script need rewrite and test
 */
package hellbound.system;

import java.util.Collection;
import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


/**
 * @author L2Rambase Project
 */

public class HellboundNativesChat extends Quest
{
	private static final String qn = "HellboundNativesChat";

	private static final int NativeCorpse = 32306;
	private static final int Natives = 32362;
	private static final int Insurgents = 32363;
	private static final int[] NPCS = { 32362, 32363 };

	private static final int[][] CorpseLocs = {
		{ -26811, 251947, -3497, 254 },
		{ -27229, 251854, -3518, 172 },
		{ -28257, 252061, -3518, 64987 },
		{ -28586, 251747, -3518, 15103 },
		{ -27983, 251483, -3518, 15887 },
		{ -27884, 251049, -3518, 65199 },
		{ -27184, 251076, -3518, 22795 },
		{ -27152, 251310, -3518, 29167 },
		{ -28750, 251172, -3518, 16622 },
		{ -28686, 250827, -3518, 16092 },
		{ -28822, 252300, -3518, 54217 },
		{ -29553, 252712, -3518, 6711 },
		{ -29513, 252961, -3518, 58792 },
		{ -27330, 252382, -3518, 1894 },
		{ -28403, 249095, -3474, 20065 },
		{ -28673, 249067, -3477, 15990 }, 
	};
	/**
	 * Npc List
	 */
	private List<L2Npc> corpses = new FastList<L2Npc>();
	protected L2Npc newNpc;
	
	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		int npcId = npc.getNpcId();
		int hellboundLevel = HellboundManager.getInstance().getLevel();
        if (npcId == Insurgents)
        {
            if (hellboundLevel <= 5)
            {
            	htmltext = "<html><body>Hellbound Resistance:<br>Our comrades hardly survived, now they suffer from the wounds and the food shortage. We need the help!</body></html>";
            }
            if (hellboundLevel > 5)
            {
            	htmltext = "<html><body>Hellbound Resistance:<br>Thank you!<br>I fully understand my comrades' resentment of outsiders, but the fact remains that we are far too weak to confront the Steel Citadel head on without help. But if we can help defeat the Demons in any way, no matter how trivial, we will do it!</body></html>";
            }
        }
        if (npcId == Natives)
        {
            if (hellboundLevel <= 5)
            {
            	htmltext = "<html><body>Hellbound Native:<br>Ah... food... give me something to eat...</body></html>";
            }
            if (hellboundLevel > 5)
            {
            	htmltext = "<html><body>Hellbound Native:<br>Welcome!<br>Thanks to your efforts, we were able to get enough food from the Caravan in order to survive. Now, let's combine our strength and show those Steel Citadel bastards a thing or two!</body></html>";
            }
        }
		return htmltext;
	}
	
	/**
	 * On Advanced Event Script 
	 */
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("levelcheck"))
		{
            int hellboundLevel = HellboundManager.getInstance().getLevel();
            if (hellboundLevel == 5)
            {                
                changeNativeSpawn(true, corpses);
            }
            else
            {
                startQuestTimer("levelcheck", 30000, null, null);
            }
		}
       	return htmltext;
	}
	
	public HellboundNativesChat(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i: NPCS)
		{
		    addTalkId(i);
		    addFirstTalkId(i);
		    addStartNpc(i);
		}
		int hellboundLevel = HellboundManager.getInstance().getLevel();
        // if the level of hellbound is below level 5, this replaces
        // the natives with native corpses
		corpses.clear();
        if (hellboundLevel < 5)
        {
            startQuestTimer("levelCheck", 30000, null, null);
            corpses = changeNativeSpawn(false, null);
        }
	}
	
	private List<L2Npc> changeNativeSpawn(boolean value, Object object) 
	{
		Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
        if (value = false)
        {
        	for (L2Spawn i:  worldObjects)
         	{
        		int npcId = i.getNpcid();
        		corpses.clear();
        		if (npcId == NPCS[0] || npcId == NPCS[1])
        		{
        			L2Npc curNpc = i.getLastSpawn();
        			curNpc.deleteMe();
        		}
         	}
        	for (int n=0; n<16; n++)
        	{
	            L2Npc newCorpse = addSpawn(NativeCorpse, CorpseLocs[n][0], CorpseLocs[n][1], CorpseLocs[n][2], CorpseLocs[n][3], false, 0);
	            corpses.add(newCorpse);
        	}
        }
	    if (value == true)
	    {
	    	for (L2Spawn i:  worldObjects)
         	{
	    		int npcId = i.getNpcid();
	    		if (npcId == NPCS[0] || npcId == NPCS[1])
        		{
					newNpc = addSpawn(npcId, i.getLocx(), i.getLocy(), i.getLocz(), i.getHeading(), false, 0);
        		}
         	}
	    	for (L2Npc n: corpses)
	    	{
	    		n.deleteMe();
	    	}
	    	corpses.clear();
	    }
		return corpses;
	}

	public static void main(String[] args)
	{
		new HellboundNativesChat(-1,qn,"system");
		_log.info("Hellbound System: Natives Chat loaded ...");
	}
}