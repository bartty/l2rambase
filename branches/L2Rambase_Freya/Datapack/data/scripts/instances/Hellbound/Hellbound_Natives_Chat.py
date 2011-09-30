import sys
from java.lang import System
from l2.rambase.gameserver.datatables import SpawnTable
from l2.rambase.gameserver.instancemanager import HellboundManager
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.util import Rnd

NativeCorpse = 32306
Natives = 32362
Insurgents = 32363
NPCS = [32362, 32363]


CorpseLocs = {
0:[ -26811, 251947, -3497, 254 ],
1:[ -27229, 251854, -3518, 172 ],
2:[ -28257, 252061, -3518, 64987 ],
3:[ -28586, 251747, -3518, 15103 ],
4:[ -27983, 251483, -3518, 15887 ],
5:[ -27884, 251049, -3518, 65199 ],
6:[ -27184, 251076, -3518, 22795 ],
7:[ -27152, 251310, -3518, 29167 ],
8:[ -28750, 251172, -3518, 16622 ],
9:[ -28686, 250827, -3518, 16092 ],
10:[ -28822, 252300, -3518, 54217 ],
11:[ -29553, 252712, -3518, 6711 ],
12:[ -29513, 252961, -3518, 58792 ],
13:[ -27330, 252382, -3518, 1894 ],
14:[ -28403, 249095, -3474, 20065 ],
15:[ -28673, 249067, -3477, 15990 ], 
}

def changeNativeSpawn(booleanValue, corpseList, self):
    worldObjects = SpawnTable.getInstance().getSpawnTable().values()
    if booleanValue == 0:
      for i in worldObjects:
        npcId = i.getNpcid()
        corpses = []
        if npcId in NPCS:
            curNpc = i.getLastSpawn()
            curNpc.deleteMe()
      for n in range(16):
            xx, yy, zz, head = CorpseLocs[n]
            newCorpse = self.addSpawn(NativeCorpse, xx, yy, zz, head, False, 0)
            corpses.append(newCorpse)
      return corpses
    if booleanValue == 1:
      for i in worldObjects:
        npcId = i.getNpcid()
        if npcId in NPCS:
            xx, yy, zz = i.getLocx(), i.getLocy(), i.getLocz()
            heading = i.getHeading()
            newNpc = self.addSpawn(npcId, xx, yy, zz, heading, False, 0)
      for n in corpseList:
        n.deleteMe()
    return

class Native_Chat (JQuest):

    def __init__(self, id, name, descr):
        JQuest.__init__(self, id, name, descr)
        hellboundLevel = HellboundManager.getInstance().getLevel()
        self.hellboundLevel = hellboundLevel
        self.corpses = []
        # if the level of hellbound is below level 5, this replaces
        # the natives with native corpses
        if hellboundLevel < 5:
            self.startQuestTimer("levelCheck", 30000, None, None)
            self.corpses = changeNativeSpawn(0, None, self)

    def onAdvEvent (self, event, npc, player) :
        if event == "levelCheck":
            hellboundLevel = HellboundManager.getInstance().getLevel()
            if hellboundLevel == 5 and self.hellboundLevel < hellboundLevel:
                self.hellboundLevel = hellboundLevel
                changeNativeSpawn(1, self.corpses, self)
            else:
                self.startQuestTimer("levelCheck", 30000, None, None)

    def onFirstTalk (self, npc, player):
        npcId = npc.getNpcId()
        hellboundLevel = HellboundManager.getInstance().getLevel()
        if npcId == Insurgents:
            if hellboundLevel <= 5: return "<html><body>Hellbound Resistance:<br>Our comrades hardly survived, now they suffer from the wounds and the food shortage. We need the help!</body></html>"
            if hellboundLevel > 5: return "<html><body>Hellbound Resistance:<br>Thank you!<br>I fully understand my comrades' resentment of outsiders, but the fact remains that we are far too weak to confront the Steel Citadel head on without help. But if we can help defeat the Demons in any way, no matter how trivial, we will do it!</body></html>"
        if npcId == Natives:
            if hellboundLevel <= 5: return "<html><body>Hellbound Native:<br>Ah... food... give me something to eat...</body></html>"
            if hellboundLevel > 5: return "<html><body>Hellbound Native:<br>Welcome!<br>Thanks to your efforts, we were able to get enough food from the Caravan in order to survive. Now, let's combine our strength and show those Steel Citadel bastards a thing or two!</body></html>"
        return

QUEST = Native_Chat(-1, "Native_Chat", "instances")

for i in NPCS:
    QUEST.addTalkId(i)
    QUEST.addFirstTalkId(i)
    QUEST.addStartNpc(i)

