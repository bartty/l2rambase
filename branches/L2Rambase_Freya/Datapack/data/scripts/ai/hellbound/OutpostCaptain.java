/*
 * L2Rambase Project Project
 */
package ai.hellbound;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * OutpostCaptain 
 * @author L2Rambase Project Project
 */
public class OutpostCaptain extends L2AttackableAIScript
{	
	/*
	 * Mobs & Npcs
	 */
	private static final int Captain = 18466;
	private static final int EnceinteDefender = 22358;
	/*
	 * Location List
	 */
	private static final int[] CaptainLoc = { 4881, 244038, -1927};
	private static final int[][] MinionsLocs = 
	{
	 { 5231, 243750, -1925 },
	 { 4866, 244463, -1925 },
	 { 4680, 244813, -1593 },
	 { 4788, 244792, -1589 },
	 { 5453, 243425, -1586 },
	 { 5496, 243558, -1588 }
	 };
	 
	/*
	 * Options
	 */
	private int captainState;
	
	public OutpostCaptain(int questId, String name, String descr)
	{        
		super(questId, name, descr);		
		addKillId(Captain);
		addAttackId(Captain);
		addTalkId(Captain);
		addFirstTalkId(Captain);
		addStartNpc(Captain);
		captainState = 0;
        int hellboundLevel = HellboundManager.getInstance().getLevel();
        if (hellboundLevel != 8)
        {
        	
        }
        else
        {
        	HellboundManager.getInstance().addSpawn(Captain, CaptainLoc[0], CaptainLoc[1], CaptainLoc[2], 36561, 0);
        }
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		if (event.equalsIgnoreCase("checkState"))
		{
            if (captainState == 2)
            {
            	return htmltext;
            }
            startQuestTimer("checkState", 10000, npc, null);
            try
            {
              if (!npc.isInCombat())
                captainState = 0;
            }
            catch (Exception e)
            {}
		}
        if (event.equalsIgnoreCase("attackPlayer"))
        {
        	npc.setRunning();
        	L2MonsterInstance npcAi = ((L2MonsterInstance)npc);
        	npcAi.addDamageHate(player, 0, 999);
        	//npc.addDamageHate(player, true, 999);
        	npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
        	if (captainState != 2)
        	{
        		for (int i=0; i < 6; i++)
        		{
        			L2MonsterInstance tempMinion = (L2MonsterInstance) addSpawn(EnceinteDefender, MinionsLocs[i][0], MinionsLocs[i][1], MinionsLocs[i][2], 0, false, 900000);
        			tempMinion.addDamageHate(player, 0, 999);
        			tempMinion.setIsNoRndWalk(true);
        			tempMinion.setRunning();
        			tempMinion.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
        			captainState = 2;
        		}
        	}
        }
		return htmltext;
	}
	
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{		
		int npcId = npc.getNpcId();
        if (npcId == Captain)
        {
        	HellboundManager.getInstance().changeLevel(9);
            DoorTable.getInstance().getDoor(20250001).openMe();
        }
        return super.onKill(npc, player, isPet);
	}
	
	public String onAttack (L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
    {
		int npcId = npc.getNpcId();
		if (npcId == Captain)
		{
			if (captainState == 0)
			{
				captainState = 1;
				startQuestTimer("attackPlayer", 100, npc, attacker);
			}
		}
		return super.onAttack(npc,attacker,damage,isPet);
    }
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		int npcId = npc.getNpcId();
		if (npcId == Captain)
		{
			if (npc.isInCombat() || captainState >= 1)
				return htmltext;
			htmltext = "<html><body>Outpost Captain:<br>Hello young one.<br1>Please come closer so I can take a good look at you.</body></html>";
			startQuestTimer("attackPlayer", 5000, npc, player);
			startQuestTimer("checkState", 10000, npc, null);
			captainState = 1;
		}
		return htmltext;
	}

	public static void main(String[] args)
    {
		new EnchantedMegaliths(-1,"OutpostCaptain","ai");
    }
}