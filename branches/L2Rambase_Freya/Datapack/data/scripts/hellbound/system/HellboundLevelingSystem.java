/**
 * @author Deyoun
 * @category 
 * 
 * FIXME: HellboundLevelingSystem - Script need rewrite and test
 */
package hellbound.system;

import java.util.Collection;
import java.util.List;
import javolution.util.FastList;

import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.util.Rnd;

public class HellboundLevelingSystem extends Quest
{
	private static final String qn = "HellboundLevelingSystem";
	
	/**
	 * Npc or Monsters Id
	 */
	private static final int ghostofderek = 18465;
	private static final int Captain = 18466;
	private static final int WLGuard = 18467;
	private static final int[] chimeras = { 22349, 22350, 22351, 22352 };
	private static final int celtus = 22353;
	private static final int Solomon = 32355;
	private static final int Traitor = 32364;
	private static final int Kief = 32354;
	private static final int Buron = 32345;
	private static final int KeltasMonster = 22341;
	/**
	 * Localisations
	 */
	private static final int[] KeltasSpawn = { -29569, 252834, -3518 };
	private static final int[] KeltasMinions = { 22342, 22343 };
	private static final int[][] KeltasMinionsSpawns = {
	{ -23553, 251677, -3361 },
	{ -24381, 251555, -3351 },
	{ -25367, 252178, -3252 },
	{ -25390, 252405, -3252 },
	{ -24222, 252270, -3140 },
	{ -24441, 252295, -3085 },
	{ -24106, 252711, -3054 },
	{ -26930, 251951, -3518 },
	{ -27918, 251498, -3518 },
	{ -27764, 251124, -3521 },
	{ -28415, 251995, -3521 },
	{ -28640, 250998, -3518 },
	{ -28389, 250050, -3473 },
	{ -28610, 250034, -3473 },
	{ -28719, 252281, -3518 },
	{ -29477, 253089, -3518 },
	{ -29360, 252953, -3518 },
	{ -29689, 252685, -3518 },
	{ -29117, 252925, -3518 },
	{ -23496, 251924, -3364 },
	{ -23812, 251522, -3368 },
	{ -25035, 251961, -3295 },
	{ -23869, 252040, -3306 },
	{ -24689, 252583, -3043 },
	{ -23887, 253034, -3038 },
	{ -27273, 251871, -3518 },
	{ -27211, 251139, -3518 },
	{ -28683, 251590, -3518 },
	{ -29615, 253045, -3518 },
	{ -29345, 252660, -3518 },
	{ -29145, 252709, -3518 }, };

	private static final int[][] LOCS = {
	{4276, 237245, -3310},
	{11437, 236788, -1949},
	{7647, 235672, -1977}, 
	{1882, 233520, -3315} };
	/**
	 * Points List
	 */
	protected int hellboundTrust = 0;
    private int hellboundLevel = 0;
    private static final int[][] hellboundMobs = {
            { 22320,10},
            { 22321,10},
            { 22324,1},
            { 22325,1},
            { 22327,3},
            { 22328,3},
            { 22329,3},
    };
    private static final int[][] hellboundMobs1 = {
            { 22322,-10},
            { 22323,-10},
            { 22342,3},
            { 22343,3},
            { 22361,20},
            { 22449,50},
            { 22450,-10},
            { 25536,200}
    };
    /**
     * Npc List
     */
    private List<L2Npc> buronloc = new FastList<L2Npc>();
    private List<L2Npc> kiefloc = new FastList<L2Npc>();
    private List<L2Npc> keltasmin = new FastList<L2Npc>();
    private List<L2Npc> keltasloc = new FastList<L2Npc>();
    
    protected L2Npc newFalk;
    private L2Npc oldKief;
    private L2Npc oldBuron;
    protected L2Spawn newCaptain;
    protected L2Spawn newCeltus;
    protected L2Npc newDerek;
    protected L2Npc newSolomon;
    protected L2Npc newTraitor;
    protected L2Npc newKief;
    protected L2Npc newBuron;
    private L2Npc newKeltas;
    private L2Npc newKeltasMinion;
    protected int Keltas;
    protected L2Spawn newNpc;
    protected L2Npc curNpc;
	
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
        if (event.equalsIgnoreCase("keltasrespawn"))
        {
        	if (hellboundLevel >= 5)
        	{
        		return "";
        	}
            keltasloc.clear();
            newKeltas = addSpawn(KeltasMonster, KeltasSpawn[0], KeltasSpawn[1], KeltasSpawn[2], 0, false, 0);
            keltasloc.add(newKeltas);
            keltasmin.clear();
            Keltas = 0;
            for (int i=0; i<31; i++)
            {
                int RndMobSpawn = KeltasMinions[Rnd.get(0,1)];
                L2Npc newKeltasMinion = addSpawn(RndMobSpawn, KeltasMinionsSpawns[i][0],KeltasMinionsSpawns[i][1], KeltasMinionsSpawns[i][2], 0, false, 0);
                keltasmin.add(newKeltasMinion);
            }
        }
        if (event.equalsIgnoreCase("LevelCheck"))
        {
              HellboundManager.getInstance().checkHellboundLevel();
              int newLevel = HellboundManager.getInstance().getLevel();
              hellboundTrust = HellboundManager.getInstance().getTrust();
              if (newLevel > hellboundLevel)
              {
                  hellboundLevel = newLevel;
                  String announce = "Hellbound now has now reached level: " + String.valueOf(hellboundLevel);
                  Announcements.getInstance().announceToAll(announce,2);
                  if (newLevel == 2)
                  {
                      newFalk = addSpawn(32297, -19904, 250016, -3240, 12288, false, 0);
                  }
                  if (newLevel == 3)
                  {
                	  keltasloc.clear();
                      newKeltas = addSpawn(KeltasMonster, KeltasSpawn[0], KeltasSpawn[1], KeltasSpawn[2], 0, false, 0);
                      keltasloc.add(newKeltas);
                      keltasmin.clear();
                      for (int i=0; i<31; i++)
                      {
                          int RndMobSpawn = KeltasMinions[Rnd.get(0,1)];
                          L2Npc newKeltasMinion = addSpawn(RndMobSpawn, KeltasMinionsSpawns[i][0],KeltasMinionsSpawns[i][1], KeltasMinionsSpawns[i][2], 0, false, 0);
                          keltasmin.add(newKeltasMinion);
                      }
                  }
                  if (newLevel == 4)
                  {
                      newDerek = addSpawn(ghostofderek, -28058, 256885, -1934, 0, false, 0);
                  }
                  if (newLevel == 5)
                  {
                      for (L2Npc i: buronloc)
                      {
                          i.deleteMe();
                      }
                      for (L2Npc i: kiefloc)
                      {
                          i.deleteMe();
                      }
                      for (L2Npc i: keltasloc)
                      {
                          i.deleteMe();
                      }
                      for (L2Npc i: keltasmin)
                      {
                          i.deleteMe();
                      }
                      newSolomon = addSpawn(Solomon, -28916, 249381, -3472, 0, false, 0);
                      newTraitor = addSpawn(Traitor, -27352, 252387, -3520, 5416, false, 0);
                      newKief = addSpawn(Kief, -28357, 248993, -3472, 16384, false, 0);
                      newBuron = addSpawn(Buron, -28567, 248994, -3472, 16384, false, 0);
                  }
                  if (newLevel == 6)
                  {
                      changeBoxesSpawnState(true);
                  }
                  if (newLevel == 7)
                  {
                      changeChimeraSpawnState(true);
                      int[] LOC = LOCS[Rnd.get(LOCS.length)];
                      int CeltrespTime = (18 + Rnd.get(36)) * 100; //between 30m and 1h
                      newCeltus = HellboundManager.getInstance().addSpawn(celtus, LOC[0], LOC[1], LOC[2], 0, CeltrespTime);                      
                  }
                  if (newLevel == 8)
                  {
                      newCaptain = HellboundManager.getInstance().addSpawn(Captain, 4766, 243995, -1928, 36561, 0);
                  }
              }
              if (hellboundLevel < 7)
              {
                  DoorTable.getInstance().getDoor(20250002).closeMe();
              }
              if (hellboundLevel >= 7)
              {
                  DoorTable.getInstance().getDoor(20250002).openMe();
              }
              if (hellboundLevel < 9)
              {
                  DoorTable.getInstance().getDoor(20250001).closeMe();
              }
              if (hellboundLevel >= 9)
              {
                  DoorTable.getInstance().getDoor(20250001).openMe();
              }
        }
		return htmltext;		
	}

	public String onAttack (L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
    {
		int id = npc.getNpcId();
		if (id == KeltasMonster)
		{
			if (Keltas == 0)
			{
				Keltas = 1;
				npc.broadcastPacket(new CreatureSay(npc.getObjectId(), 1, npc.getName(), "Boys! Show them our power!!!!"));
			}
		}
		return super.onAttack(npc,attacker,damage,isPet);
    }
	
	public String onKill (L2Npc npc, L2PcInstance player, boolean isPet)
	{
		int id = npc.getNpcId();
        QuestState st = player.getQuestState(qn);
        if (st == null)
        {
            st = newQuestState(player);
        }
        if (id == KeltasMonster)
        {
               HellboundManager.getInstance().increaseTrust(100);
               int respTime = 0;
               if (HellboundManager.getInstance().getLevel() > 4)
               {
            	   return "";
               }               
               if (HellboundManager.getInstance().getLevel() == 3)
               {
            	   respTime = (72 + Rnd.get(144)) * 100; //between 2 and 4 hours respawn retail-like
               }
               if (HellboundManager.getInstance().getLevel() == 4)
               {
            	   respTime = (288 + Rnd.get(576)) * 100; //between 8 and 16 hours respawn retail-like
               }
               startQuestTimer("keltasRespawn", respTime, null, null);
               for (L2Npc i: keltasmin)
               {
                   i.deleteMe();
               }
        }
        for (int i = 0; i<hellboundMobs.length; i++)
        {
        	if (hellboundMobs[i][0] == id)
        	{
        		if (HellboundManager.getInstance().getLevel() > 1)
        		{
        			return "";
        		}
        		HellboundManager.getInstance().increaseTrust(hellboundMobs[i][1]);
        	}
        }
        for (int i = 0; i<hellboundMobs1.length; i++)
        {
        	if (hellboundMobs1[i][0] == id)
        	{
        		HellboundManager.getInstance().increaseTrust(hellboundMobs1[i][1]);
        	}
        }             
        if (id == WLGuard)
        {
        	if (HellboundManager.getInstance().getLevel() == 7)
        	{
        		Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
                for (L2Spawn i:  worldObjects)
             	{
                       if (i.getNpcid() == WLGuard)
                       {
                           i.stopRespawn();
                       }
             	}
        	}
        }
		return super.onKill(npc, player, isPet);
	}

	public HellboundLevelingSystem(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i = 0; i<hellboundMobs.length; i++)
		{
		    addKillId(i);
		}

		for (int i = 0; i<hellboundMobs.length; i++)
		{
		    addKillId(i);
		}

		addKillId(KeltasMonster);
		addKillId(WLGuard);
		addAttackId(KeltasMonster);
        hellboundLevel = HellboundManager.getInstance().getLevel();
        hellboundTrust = HellboundManager.getInstance().getTrust();
        if (hellboundLevel >= 2)
        {
            newFalk = addSpawn(32297, -19904, 250016, -3240, 12288, false, 0);
        }
        if (hellboundLevel < 7)
        {
            changeChimeraSpawnState(false);
        }
        if (hellboundLevel < 5)
        {
            buronloc.clear();
            kiefloc.clear();
            oldKief = addSpawn(Kief, -21271, 250238, -3314, 16384, false, 0);
            oldBuron = addSpawn(Buron, -11173, 236537, -3236, 41760, false, 0);
            buronloc.add(oldBuron);
            kiefloc.add(oldKief);
        }
        if (hellboundLevel >= 5)
        {
            newSolomon = addSpawn(Solomon, -28916, 249381, -3472, 0, false, 0);
            newTraitor = addSpawn(Traitor, -27352, 252387, -3520, 5416, false, 0);
            newKief = addSpawn(Kief, -28357, 248993, -3472, 16384, false, 0);
            newBuron = addSpawn(Buron, -28567, 248994, -3472, 16384, false, 0);
        }
        if (hellboundLevel < 6)
        {
            changeBoxesSpawnState(false);
        }
        if (hellboundLevel < 7)
        {
            changeChimeraSpawnState(false);
        }
        if (hellboundLevel >= 3 && hellboundLevel < 5)
        {
            keltasloc.clear();
            newKeltas = addSpawn(Keltas, KeltasSpawn[0], KeltasSpawn[1], KeltasSpawn[2], 0, false, 0);
            keltasloc.add(newKeltas);
            keltasmin.clear();
            for (int i=0; i<31; i++)
            {
                int RndMobSpawn = KeltasMinions[Rnd.get(0,1)];
                newKeltasMinion = addSpawn(RndMobSpawn, KeltasMinionsSpawns[i][0],KeltasMinionsSpawns[i][1], KeltasMinionsSpawns[i][2], 0, false, 0);
                keltasmin.add(newKeltasMinion);
            }
        }
        if (hellboundLevel == 4)
        {
            newDerek = addSpawn(ghostofderek, -28058, 256885, -1934, 0, false, 0);
        }
        if (hellboundLevel >= 7)
        {
        	Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
            for (L2Spawn i:  worldObjects)
         	{
            	int npcId = i.getNpcid();
            	if (npcId == WLGuard)
            	{
            		i.stopRespawn();
            		L2Npc curNpc = i.getLastSpawn();
                    curNpc.onDecay();
                }
         	}
        }
        startQuestTimer("LevelCheck", 60000, null, null, true);
	}
	
	private void changeBoxesSpawnState(boolean value) 
	{
		Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
        for (L2Spawn i:  worldObjects)
     	{
		    int npcId = i.getNpcid();
	        if (npcId == 32361)
	        {
	            if (value == true)
	            {
	            	newNpc = HellboundManager.getInstance().addSpawn(npcId, i.getLocx(), i.getLocy(), i.getLocz(), i.getHeading(), i.getRespawnDelay());
	            }
	            if (value == false)
	            {
	                i.stopRespawn();
	                curNpc = i.getLastSpawn();
	                curNpc.onDecay();
	            }
	        }
     	}
	}

	private void changeChimeraSpawnState(boolean value) 
	{
		Collection<L2Spawn> worldObjects = SpawnTable.getInstance().getSpawnTable().values();
        for (L2Spawn i:  worldObjects)
     	{	        
	        for (int npcId: chimeras)
	        {
	        	npcId = i.getNpcid();
	        	if (value == true)
	            {
	                newNpc = HellboundManager.getInstance().addSpawn(npcId, i.getLocx(), i.getLocy(), i.getLocz(), i.getHeading(), i.getRespawnDelay());
	            }
	            if (value == false)
	            {
	                i.stopRespawn();
	                curNpc = i.getLastSpawn();
	                curNpc.onDecay();
	            }
	        }	                
     	}
	}

	public static void main(String[] args)
	{
		new HellboundLevelingSystem(-1,qn,"system");
		_log.warning("Hellbound: Leveling System Loaded ...");
	}
}