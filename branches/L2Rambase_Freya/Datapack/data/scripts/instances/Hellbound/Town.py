import sys
from java.lang import System
from l2.rambase.gameserver.ai import CtrlIntention
from l2.rambase.gameserver.datatables import DoorTable
from l2.rambase.gameserver.datatables import SpawnTable
from l2.rambase.gameserver.instancemanager import HellboundManager
from l2.rambase.gameserver.instancemanager import InstanceManager
from l2.rambase.gameserver.model.actor import L2Summon
from l2.rambase.gameserver.model.entity import Instance
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.gameserver.network import SystemMessageId
from l2.rambase.gameserver.network.serverpackets import CreatureSay
from l2.rambase.gameserver.network.serverpackets import SystemMessage
from l2.rambase.gameserver.util import Util
from l2.rambase.util import Rnd

qn = "HellboundTown"

debug = False

#NPCs
KANAF = 32346
PRISONER = 32358

#Mobs
AMASKARI = 22449
KEYMASTER = 22361
LIST = [22359, 22360]

KLOCS = [
[14264, 250333, -1935, 15133],
[19961, 256249, -2086, 47344],
[17271, 252888, -2010, 64381],
[15784, 252413, -2010, 49254],
[22029, 254160, -2005, 60246]
]

class PyObject:
	pass
	
def checkPrimaryConditions(player):
	if not player.getParty():
		player.sendPacket(SystemMessage.sendString("To attempt to enter the town by yourself would be suicide! You must enter with the rest of your party members."))
		return False
	if not player.getLevel() >= 78:
		player.sendPacket(SystemMessage.sendString("You do not meet the level requirement."))
		return False
	return True
	
def checkNewInstanceConditions(player):
	if not player.getParty().isLeader(player):
		player.sendPacket(SystemMessage.sendString("Only a party leader can try to enter."))
		return False
	party = player.getParty()
	if party == None:
		return True
	for partyMember in party.getPartyMembers().toArray():
		if not partyMember.getLevel() >= 78:
			sm = SystemMessage(2101)
			sm.addCharName(partyMember)
			player.sendPacket(sm)
			return False
	for partyMember in player.getParty().getPartyMembers().toArray():
		if not partyMember.isInsideRadius(player, 500, False, False):
			sm = SystemMessage(2101)
			sm.addCharName(partyMember)
			player.sendPacket(sm)
			return False
	return True

def getExistingInstanceId(player):
	instanceId = 0
	party = player.getParty()
	if party == None:
		return 0
	for partyMember in party.getPartyMembers().toArray():
		if partyMember.getInstanceId() != 0:
			instanceId = partyMember.getInstanceId()
	return instanceId

def teleportPlayer(self, player, teleto):
	player.setInstanceId(teleto.instanceId)
	player.teleToLocation(teleto.x, teleto.y, teleto.z)
	pet = player.getPet()
	if pet != None :
		pet.setInstanceId(teleto.instanceId)
		pet.teleToLocation(teleto.x, teleto.y, teleto.z)
	return

def exitInstance(player, tele):
	player.setInstanceId(0)
	player.teleToLocation(tele.x, tele.y, tele.z)
	pet = player.getPet()
	if pet != None :
		pet.setInstanceId(0)
		pet.teleToLocation(tele.x, tele.y, tele.z)

class HellboundTown(JQuest):
	def __init__(self, id, name, descr):
		JQuest.__init__(self, id, name, descr)
		self.worlds = {}
		self.world_ids = []
		self.Slaves = {}
		self.currentWorld = 0
		self.Lock = 0
		self.hellboundLevel = 0
		self.trustp = 0
		try:
		    self.trustp = int(self.loadGlobalQuestVar("trust10p"))
		except:
		    pass
		self.saveGlobalQuestVar("trust10p", str(self.trustp))
		if HellboundManager.getInstance().getLevel() == 10: self.startQuestTimer("CheckTrustP", 60000, None, None, True)

	def onAdvEvent (self, event, npc, player):
	    if event == "CheckTrustP":
		    if self.trustp >= 500000:
			    HellboundManager.getInstance().changeLevel(11)
			    self.trustp = 0
			    self.saveGlobalQuestVar("trust10p", str(self.trustp))
			    self.cancelQuestTimers("CheckTrustP")

	def onSpawn(self, npc):
	    npcId = npc.getNpcId()
	    objId = npc.getObjectId()
	    if npcId == AMASKARI:
		    self.Prisonslaves = []
		    self.Slaves[objId] = []
		    self.Slaves[objId].append("noSlaves")
		    xx, yy, zz = npc.getX(), npc.getY(), npc.getZ()
		    self.Slaves[objId] = []
		    for i in range(9):
			    offsetX = xx + (50 - Rnd.get(250))
			    offsetY = yy + (50 - Rnd.get(250))
			    newSlave = self.addSpawn(22450, offsetX, offsetY, zz, 0, False, 0, False, npc.getInstanceId())
			    newSlave.setRunning()
			    newSlave.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, npc)
			    self.Slaves[objId].append(newSlave)

	def onTalk (self, npc, player):
		npcId = npc.getNpcId()
		hellboundLevel = HellboundManager.getInstance().getLevel()
		if hellboundLevel < 10: return "<html><body>Who are you?...<br>Go away from here, I do not want to talk with you!</body></html>"
		if npcId == KANAF:
			if not checkPrimaryConditions(player):
			    return
			tele = PyObject()
			tele.x = 13881
			tele.y = 255491
			tele.z = -2025
			instanceId = getExistingInstanceId(player)
			if instanceId == 0:
			    if not checkNewInstanceConditions(player):
				    return
			    instanceId = InstanceManager.getInstance().createDynamicInstance("HBTown.xml")
			    if not self.worlds.has_key(instanceId):
				    world = PyObject()			
				    world.rewarded = []
				    world.instanceId = instanceId
				    self.worlds[instanceId] = world
				    self.world_ids.append(instanceId)
				    self.currentWorld = instanceId
				    print "HellboundTown: started Hellbound Town Instance: " + str(instanceId) + " created by player: " + str(player.getName())
				    KLOC = KLOCS[Rnd.get(len(KLOCS))]
				    newKeymaster = self.addSpawn(KEYMASTER, KLOC[0], KLOC[1], KLOC[2], KLOC[3], False, 0, False, world.instanceId)
				    tele.instanceId = instanceId
				    teleportPlayer(self, player, tele)
				    party = player.getParty()
				    if party != None:
					    for partyMember in party.getPartyMembers().toArray():
						    teleportPlayer(self, partyMember, tele)
			else:
			    for worldid in self.world_ids:
				    if worldid == instanceId:
					    foundworld = True
				    if not worldid == instanceId:
					    foundworld = False
			    if not foundworld:
				    player.sendPacket(SystemMessage.sendString("Your Party Members are in another Instance."))	
				    return
			    tele.instanceId = instanceId
			    teleportPlayer(self, player, tele)
		elif npcId == PRISONER:
		    htmltext = "<html><body>Native Prisoner:<br>An exempt Native Prisoner hardly regains consciousness. Till to dash to hurry, he tries to express you the gratitude.</body></html>"
		    npc.decayMe()
		    if hellboundLevel == 10:
			    HellboundManager.getInstance().increaseTrust(50)
			    self.trustp += 50
			    self.saveGlobalQuestVar("trust10p", str(self.trustp))
		    return htmltext
		return
   
	def onKill(self, npc, player, isPet):
	    npcId = npc.getNpcId()
	    objId = npc.getObjectId()
	    if npcId == KEYMASTER:
		    newAmaskari = self.addSpawn(AMASKARI, 19347, 253103, -2019, 41941, False, 0, False, npc.getInstanceId())
		    HellboundManager.getInstance().increaseTrust(250)
		    self.trustp += 250
		    self.saveGlobalQuestVar("trust10p", str(self.trustp))
	    if npcId == AMASKARI:
		  if HellboundManager.getInstance().getLevel() <= 11:
		    HellboundManager.getInstance().increaseTrust(500)
		    self.trustp += 500
		    self.saveGlobalQuestVar("trust10p", str(self.trustp))
		  try:
		    if self.Slaves[objId][0] != "noSlaves":
			  for i in self.Slaves[objId]:
			    try:
				  i.setIsInvul(1)
				  i.broadcastPacket(CreatureSay(i.getObjectId(), 0, i.getName(), "Thank you for saving me!"))
				  i.decayMe()
			    except:
				  pass
			  self.Slaves[objId] = []
		  except:
		    pass
	    if npcId == PRISONER:
		    HellboundManager.getInstance().increaseTrust(-10)
		    self.trustp -= 10
		    self.saveGlobalQuestVar("trust10p", str(self.trustp))
	    if npcId in LIST:
		    HellboundManager.getInstance().increaseTrust(20)
		    self.trustp += 20
		    self.saveGlobalQuestVar("trust10p", str(self.trustp))
	    if self.worlds.has_key(npc.getInstanceId()):
		    world = self.worlds[npc.getInstanceId()]
	    return

	def onAttack(self, npc, player, damage, isPet, skill):
		npcId = npc.getNpcId()
		objId = npc.getObjectId()
		maxHp = npc.getMaxHp()
		nowHp = npc.getStatus().getCurrentHp()
		if npc.getNpcId() == AMASKARI:
		    if (nowHp < maxHp * 0.1):
			    if self.Lock == 0:
					npc.broadcastPacket(CreatureSay(objId, 0, npc.getName(), "I will make everyone feel the same suffering as me!"))
					self.Lock = 1
		if self.worlds.has_key(npc.getInstanceId()):
			world = self.worlds[npc.getInstanceId()]
		return
	
	def onFirstTalk (self, npc, player):
		npcId = npc.getNpcId()
		objId = npc.getObjectId()
		if HellboundManager.getInstance().getLevel() < 10: return "<html><body>How did you get inward? The gate of lock is closed... A patrol can appear at any moment. Run!</body></html>"
		npc.showChatWindow(player)
		if self.worlds.has_key(npc.getInstanceId()): world = self.worlds[npc.getInstanceId()]

QUEST = HellboundTown(-1, qn, "instances")
QUEST.addSpawnId(AMASKARI)
QUEST.addAttackId(AMASKARI)
QUEST.addFirstTalkId(KANAF)
QUEST.addKillId(KEYMASTER)
QUEST.addKillId(AMASKARI)
for i in LIST:
    QUEST.addKillId(i)
QUEST.addStartNpc(KANAF)
QUEST.addTalkId(KANAF)
QUEST.addTalkId(PRISONER)
