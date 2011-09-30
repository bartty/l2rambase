/**
 * @author Deyoun
 * @category need rewrite
 * 
 * Script need rewrite and test
 */
package hellbound.system;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


/**
 * @author L2Rambase Project
 */

public class NativeQuarrySlaves extends Quest
{
	private static final String qn = "NativeQuarrySlaves";

	protected int[] SlaveWalkTo = { -25451, 252291, -3252 };

	protected static final int[] NativeSlave = { 22322, 22323 };
	private static final int QuarrySlave = 32299;
	private static final int[] SlaveMasters = { 22320, 22321 };
	protected static final int[] QuarryMasters = { 22344, 22345, 22346, 22347 };

	protected static final int[] QuarryDropItems = { 1876, 1885, 9628 };


	private static final int[][] QuarrySlavesSpawns = {
	               { -6781, 241040, -1845, 43629 },
	               { -6656, 241013, -1838, 42932 },
	               { -6337, 242017, -2035, 16865 },
	               { -7311, 242725, -2030, 62145 },
	               { -6630, 242634, -2085, 32348 },
	               { -9115, 243232, -1845, 40387 },
	               { -9230, 243278, -1848, 36315 },
	               { -9384, 244176, -1859, 33839 },
	               { -9367, 244281, -1854, 32725 },
	               { -9260, 244864, -1845, 31809 },
	               { -9121, 245029, -1850, 9081  },
	               { -7540, 244000, -2042, 48351 },
	               { -6533, 243238, -2079, 62208 },
	               { -7063, 244310, -2044, 64625 },
	               { -5090, 243213, -2037, 62334 },
	               { -5935, 241772, -2013, 12068 },
	               { -5413, 243548, -2055, 34599 },
	               { -6382, 244647, -2047, 50680 },
	               { -6228, 245361, -2072, 2078  },
	               { -4202, 243779, -2018, 19948 },
	               { -4712, 244319, -2080, 51707 },
	               { -4466, 245445, -2018, 47512 },
	               { -4323, 245393, -2022, 46990 },
	               { -5567, 245504, -2049, 38083 },
	               { -5108, 243938, -2008, 33115 },
	               { -3684, 246080, -1864, 39254 },
	               { -4663, 242664, -2080, 20560 },
	               { -3567, 241491, -1847, 55775 },
	               { -3659, 241318, -1849, 58964 },
	               { -4674, 240783, -1872, 50670 },
	               { -4767, 240878, -1880, 24465 }
	};

	private static final int[][] QuarryMastersSpawns = {
	               { 22345, -7978, 241337, -1819, 16966 },
	               { 22345, -7217, 241670, -1912, 31485 },
	               { 22345, -8265, 244648, -2046, 57832 },
	               { 22347, -8719, 244877, -1855, 27070 },
	               { 22347, -8666, 244799, -1871, 34628 },
	               { 22346, -8817, 244739, -1886, 21592 },
	               { 22344, -5859, 244908, -2053, 7793 },
	               { 22347, -5555, 244988, -2067, 24288 },
	               { 22347, -5607, 244905, -2074, 24617 },
	               { 22344, -5232, 244725, -2090, 8886 },
	               { 22344, -3852, 246836, -1938, 12010 },
	               { 22345, -5667, 246400, -2011, 14560 },
	               { 22345, -4733, 241049, -1878, 10305 },
	               { 22345, -4795, 241190, -1883, 61270 },
	               { 22344, -4663, 241176, -1873, 4362 },
	               { 22347, -4947, 241286, -1889, 945 },
	               { 22345, -3399, 242185, -1892, 11702 }
	};
	
	/**
	 * Npc List
	 */
	private List<L2Npc> Slaves = new FastList<L2Npc>();
	private int rescued;
	protected L2Spawn newMaster;
	protected L2Spawn newSlave;
	
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
        if (npcId == QuarrySlave)
        {
            if (hellboundLevel != 5)
            {
            	htmltext = "<html><body>Quarry Slave:<br>Who are you ? Do not interrupting me from work.</body></html>";
            }
            else
            {
                htmltext = "<html><body>Quarry Slave:<br>";
                htmltext += "Ermm, are you here to rescue me?<br><br>";
                htmltext += "<a action=\"bypass -h Quest NativeQuarrySlaves FollowMe\">Yes, follow me quietly.</a><br1>";
                htmltext += "</body></html>";
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
		/*
		hellboundLevel = HellboundManager.getInstance().getLevel()
        if npc:
            npcId = npc.getNpcId()
            npcObjId = npc.getObjectId()
        if "Delete" in event and npc:
            try:
                npc.setIsInvul(0)
                npc.deleteMe()
            except:
                pass
        if "followCheck" in event and npc:
            if npc.isDead(): return
            newObjId = npc.getObjectId()
            timerName = "followCheck" + str(newObjId)
            self.startQuestTimer(timerName, 1000, npc, None)
            xx, yy, zz, headg = npc.getX(), npc.getY(), npc.getZ(), npc.getHeading()
            npc.getKnownList().removeAllKnownObjects()
            npcKnownObjects = L2World.getInstance().getVisibleObjects(npc)
            if npcKnownObjects > 0:
              for i in npcKnownObjects:
                npc.getKnownList().addKnownObject(i)
            npcKnownPlayers = npc.getKnownList().getKnownPlayers().values()
#            npcKnownObjects = npc.getKnownList().getKnownCharacers()
#            npc.getTaget().sendMessage("X:"+str(xx)+" Y:"+str(yy))
            if yy in range(249000, 249880) and xx in range(-6230, -5230):
              try:
                  chat = CreatureSay(npc.getObjectId(), 0, "Quarry Slave", "Thank you for saving me! Here is a small gift.")
                  npc.broadcastPacket(chat)
                  aa = Rnd.get(3)
                  for i in range(aa):
                      xx1 = xx + (35 - Rnd.get(70))
                      yy1 = yy + (35 - Rnd.get(70))
                      ditem = ItemTable.getInstance().createItem("Gift", QuarryDropItems[Rnd.get(len(QuarryDropItems))], 1, None, None)
                      ditem.dropMe(npc, xx1, yy1, zz)
              except:
                  pass
              try:
                  npc.doDie(npc)
                  npc.decayMe()
              except:
                  pass
              HellboundManager.getInstance().increaseTrust(10)
              self.rescued += 1
              self.saveGlobalQuestVar("rescued_Slaves", str(self.rescued))
              if self.rescued == 1000:
                  HellboundManager.getInstance().changeLevel(6)
                  self.rescued = 0
                  self.saveGlobalQuestVar("rescued_Slaves", str(self.rescued))
              return
            minX = xx - 170
            maxX = xx + 170
            minY = yy - 170
            maxY = yy + 170
            for neighbor in npcKnownObjects:
              if not neighbor in npcKnownPlayers:
                if neighbor.getX() in range(minX, maxX):
                  if neighbor.getY() in range(minY, maxY):
                    try:
                      if neighbor.getNpcId() in QuarryMasters:
                        neighbor.setTarget(npc)
                        neighbor.addDamageHate(npc, 0, 999)
                        neighbor.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, npc)
                        neighbor.setRunning()
                        return
                    except:
                      pass
        if event == "FollowMe":
            npcSlave = player.getTarget()
#            npcSlave.setIsFollowingMaster(1)
            npcSlave.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player)
            newObjId = npcSlave.getObjectId()
            timerName = "followCheck" + str(newObjId)
            self.startQuestTimer(timerName, 1000, npcSlave, None)
        if event == "SpawnSlaves":
            worldObjects = SpawnTable.getInstance().getSpawnTable().values()
            for i in worldObjects:
                if i.getNpcid() in SlaveMasters:
                    curNpc = i.getLastSpawn()
                    objId = curNpc.getObjectId()
                    self.Slaves[objId] = []
                    self.Slaves[objId].append("noSlaves")
                    if int(hellboundLevel) >= 5: return
                    xx, yy, zz = i.getLocx(), i.getLocy(), i.getLocz()
                    self.Slaves[objId] = []
                    offsetX = xx + (50 - Rnd.get(100))
                    offsetY = yy + (50 - Rnd.get(100))
                    newSlave = self.addSpawn(NativeSlave[0], offsetX, offsetY, zz, 0, False, 0, 0)
                    newSlave1 = self.addSpawn(NativeSlave[1], offsetX + 20, offsetY + 10, zz, 0, False, 0, 0)
                    newSlave.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, curNpc)
                    newSlave1.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, curNpc)
                    self.Slaves[objId].append(newSlave)
                    self.Slaves[objId].append(newSlave1)
		 */
       	return htmltext;
	}
	
	public String onKill (L2Npc npc, L2PcInstance player, boolean isPet)
	{
		/*
		        id = npc.getNpcId()
        objId = npc.getObjectId()
        xx, yy, zz = SlaveWalkTo
        if id in SlaveMasters:
          try:
            if self.Slaves[objId][0] != "noSlaves":
              for i in self.Slaves[objId]:
                try:
                  i.setIsInvul(1)
                  i.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, L2CharPosition(xx, yy, zz, 3500))
                  timerName = "Delete" + str(i.getObjectId())
                  self.startQuestTimer(timerName, 3000, i, None)
                except:
                    pass
              self.Slaves[objId] = []
          except:
            pass
        if id == QuarrySlave:
            HellboundManager.getInstance().increaseTrust(-10)
            self.rescued -= 1
            self.saveGlobalQuestVar("rescued_Slaves", str(self.rescued))
		 */
		return super.onKill(npc, player, isPet);	
	}
	
	public String onSpawn(L2Npc npc)
	{
		/*
		npcId = npc.getNpcId()
        objId = npc.getObjectId()
        hellboundLevel = HellboundManager.getInstance().getLevel()
        if npcId in SlaveMasters:
            self.Slaves[objId] = []
            self.Slaves[objId].append("noSlaves")
            if int(hellboundLevel) >= 5:return
            xx, yy, zz = npc.getX(), npc.getY(), npc.getZ()
            self.Slaves[objId] = []
            offsetX = xx + (50 - Rnd.get(100))
            offsetY = yy + (50 - Rnd.get(100))
            newSlave = self.addSpawn(NativeSlave[0], offsetX, offsetY, zz, 0, False, 0, 0)
            newSlave1 = self.addSpawn(NativeSlave[1], offsetX + 20, offsetY + 10, zz, 0, False, 0, 0)
            newSlave.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, npc)
            newSlave1.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, npc)
            self.Slaves[objId].append(newSlave)
            self.Slaves[objId].append(newSlave1)
		 */
		return super.onSpawn(npc);
	}
	
	public NativeQuarrySlaves(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i: SlaveMasters)
		{
			addKillId(i);
			addAttackId(i);
			addSpawnId(i);
		}
		addFirstTalkId(QuarrySlave);
		addTalkId(QuarrySlave);
		addStartNpc(QuarrySlave);
		addKillId(QuarrySlave);
		
		Slaves.clear();
        startQuestTimer("spawnslaves", 10000, null, null);
        rescued = 0;
        try
        {
            rescued = Integer.valueOf(loadGlobalQuestVar("rescued_slaves"));
        }
        catch (Exception e)
        {
        }
        saveGlobalQuestVar("rescued_slaves", String.valueOf(rescued));
        if (HellboundManager.getInstance().getLevel() > 5)
        {
        	return;
        }
        else
        {
        	for (int i=0; i<17;i++)
        	{
        		newMaster = HellboundManager.getInstance().addSpawn(QuarryMastersSpawns[i][0], QuarryMastersSpawns[i][1], QuarryMastersSpawns[i][2], QuarryMastersSpawns[i][3], QuarryMastersSpawns[i][4], 170);
        	}
        	for (int i=0; i<31;i++)
        	{
        		newSlave = HellboundManager.getInstance().addSpawn(QuarrySlave, QuarrySlavesSpawns[i][0], QuarrySlavesSpawns[i][1], QuarrySlavesSpawns[i][2], QuarrySlavesSpawns[i][3], 170);
        	}
        }
	}
	
	public static void main(String[] args)
	{
		new NativeQuarrySlaves(-1,qn,"system");
		_log.info("Hellbound System: Natives Quarry loaded ...");
	}
}