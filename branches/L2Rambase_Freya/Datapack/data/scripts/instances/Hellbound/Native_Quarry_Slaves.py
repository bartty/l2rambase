import sys
from java.lang import System
from l2.rambase.gameserver.ai import CtrlIntention
from l2.rambase.gameserver.datatables import ItemTable
from l2.rambase.gameserver.datatables import SpawnTable
from l2.rambase.gameserver.instancemanager import HellboundManager
from l2.rambase.gameserver.model.actor.instance import L2NpcInstance
from l2.rambase.gameserver.model import L2CharPosition
from l2.rambase.gameserver.model import L2World
from l2.rambase.gameserver.model import L2ItemInstance
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.gameserver.network.serverpackets import CreatureSay
from l2.rambase.util import Rnd

SlaveWalkTo = [-25451, 252291, -3252]

NativeSlave = [22322, 22323]
QuarrySlave = 32299
SlaveMasters = [22320, 22321]
QuarryMasters = [22344, 22345, 22346, 22347]

QuarryDropItems = [1876, 1885, 9628]


QuarrySlavesSpawns = {
0:[ -6781, 241040, -1845, 43629 ],
1:[ -6656, 241013, -1838, 42932 ],
2:[ -6337, 242017, -2035, 16865 ],
3:[ -7311, 242725, -2030, 62145 ],
4:[ -6630, 242634, -2085, 32348 ],
5:[ -9115, 243232, -1845, 40387 ],
6:[ -9230, 243278, -1848, 36315 ],
7:[ -9384, 244176, -1859, 33839 ],
8:[ -9367, 244281, -1854, 32725 ],
9:[ -9260, 244864, -1845, 31809 ],
10:[ -9121, 245029, -1850, 9081  ],
11:[ -7540, 244000, -2042, 48351 ],
12:[ -6533, 243238, -2079, 62208 ],
13:[ -7063, 244310, -2044, 64625 ],
14:[ -5090, 243213, -2037, 62334 ],
15:[ -5935, 241772, -2013, 12068 ],
16:[ -5413, 243548, -2055, 34599 ],
17:[ -6382, 244647, -2047, 50680 ],
18:[ -6228, 245361, -2072, 2078  ],
19:[ -4202, 243779, -2018, 19948 ],
20:[ -4712, 244319, -2080, 51707 ],
21:[ -4466, 245445, -2018, 47512 ],
22:[ -4323, 245393, -2022, 46990 ],
23:[ -5567, 245504, -2049, 38083 ],
24:[ -5108, 243938, -2008, 33115 ],
25:[ -3684, 246080, -1864, 39254 ],
26:[ -4663, 242664, -2080, 20560 ],
27:[ -3567, 241491, -1847, 55775 ],
28:[ -3659, 241318, -1849, 58964 ],
29:[ -4674, 240783, -1872, 50670 ],
30:[ -4767, 240878, -1880, 24465 ]
}

QuarryMastersSpawns = {
0:[ 22345, -7978, 241337, -1819, 16966 ],
1:[ 22345, -7217, 241670, -1912, 31485 ],
2:[ 22345, -8265, 244648, -2046, 57832 ],
3:[ 22347, -8719, 244877, -1855, 27070 ],
4:[ 22347, -8666, 244799, -1871, 34628 ],
5:[ 22346, -8817, 244739, -1886, 21592 ],
6:[ 22344, -5859, 244908, -2053, 7793 ],
7:[ 22347, -5555, 244988, -2067, 24288 ],
8:[ 22347, -5607, 244905, -2074, 24617 ],
9:[ 22344, -5232, 244725, -2090, 8886 ],
10:[ 22344, -3852, 246836, -1938, 12010 ],
11:[ 22345, -5667, 246400, -2011, 14560 ],
12:[ 22345, -4733, 241049, -1878, 10305 ],
13:[ 22345, -4795, 241190, -1883, 61270 ],
14:[ 22344, -4663, 241176, -1873, 4362 ],
15:[ 22347, -4947, 241286, -1889, 945 ],
16:[ 22345, -3399, 242185, -1892, 11702 ]
}

class Native_Quarry_Slaves (JQuest):

    def __init__(self, id, name, descr):
        JQuest.__init__(self, id, name, descr)
        self.Slaves = {}
        self.startQuestTimer("SpawnSlaves", 10000, None, None)
        self.rescued = 0
        try:
            self.rescued = int(self.loadGlobalQuestVar("rescued_slaves"))
        except:
            pass
        self.saveGlobalQuestVar("rescued_slaves", str(self.rescued))
        if HellboundManager.getInstance().getLevel() > 5: return
        for i in range(17):
            master, xx, yy, zz, headg = QuarryMastersSpawns[i]
            newMaster = HellboundManager.getInstance().addSpawn(master, xx, yy, zz, headg, 170)
        for i in range(31):
            xx, yy, zz, headg = QuarrySlavesSpawns[i]
            newSlave = HellboundManager.getInstance().addSpawn(QuarrySlave, xx, yy, zz, headg, 170)

    def onSpawn(self, npc):
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

    def onAdvEvent (self, event, npc, player) :
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

    def onKill(self, npc, player, isPet):
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

    def onFirstTalk (self, npc, player):
        st = player.getQuestState("Native_Quarry_Slaves")
        hellboundLevel = HellboundManager.getInstance().getLevel()
        if not st:
            st = self.newQuestState(player)
        npcId = npc.getNpcId()
        if npcId == QuarrySlave:
            if hellboundLevel != 5: return "<html><body>Quarry Slave:<br>Who are you ? Do not interrupting me from work.</body></html>"
            else:
                htmltext = "<html><body>Quarry Slave:<br>"
                htmltext += "Ermm, are you here to rescue me?<br><br>"
                htmltext += "<a action=\"bypass -h Quest Native_Quarry_Slaves FollowMe\">Yes, follow me quietly.</a><br1>"
                htmltext += "</body></html>"
        return htmltext

QUEST = Native_Quarry_Slaves(-1, "Native_Quarry_Slaves", "instances")

for i in SlaveMasters :
    QUEST.addKillId(i)
for i in SlaveMasters :
    QUEST.addAttackId(i)
for i in SlaveMasters :
    QUEST.addSpawnId(i)

QUEST.addFirstTalkId(QuarrySlave)
QUEST.addTalkId(QuarrySlave)
QUEST.addStartNpc(QuarrySlave)
QUEST.addKillId(QuarrySlave)
