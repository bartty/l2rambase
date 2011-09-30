# Update by pmq 25-09-2010
# fixed by L2Rambase Project
# FIXME: Need text of

import sys
from java.lang                                      import System
from l2.rambase.gameserver.ai                    import CtrlIntention
from l2.rambase.gameserver.datatables            import DoorTable
from l2.rambase.gameserver.datatables            import ItemTable
from l2.rambase.gameserver.datatables            import SkillTable
from l2.rambase.gameserver.instancemanager       import InstanceManager
from l2.rambase.gameserver.instancemanager       import InstanceManager as InstanceWorld
from l2.rambase.gameserver.model                 import L2ItemInstance
from l2.rambase.gameserver.model.actor.instance  import L2PcInstance
from l2.rambase.gameserver.model.entity          import Instance
from l2.rambase.gameserver.model.itemcontainer   import PcInventory
from l2.rambase.gameserver.model.quest           import State
from l2.rambase.gameserver.model.quest           import QuestState
from l2.rambase.gameserver.model.quest.jython    import QuestJython as JQuest
from l2.rambase.gameserver.network               import SystemMessageId
from l2.rambase.gameserver.network.serverpackets import NpcSay
from l2.rambase.gameserver.network.serverpackets import SystemMessage
from l2.rambase.gameserver.network.serverpackets import InventoryUpdate
from l2.rambase.gameserver.network.serverpackets import ExShowScreenMessage


qn = "NornilsGarden"

InstanceTemplate = "NGarden.xml"
Class = [0, 10, 18, 25, 31, 38, 44, 49, 53, 123, 124]

def dropItem(player,npc,itemId,count):
    ditem = ItemTable.getInstance().createItem("Loot", itemId, count, player)
    ditem.dropMe(npc, npc.getX(), npc.getY(), npc.getZ()); 

def getGardenIndex(level):
    if level == 19:
        return 0

def isWithinLevel(player):
     if player.getLevel() > 21:
         return False
     if player.getLevel() < 17:
         return False
     return True

def checkPrimaryConditions(player):
    if not player.getParty():
        return False
    return True

def checkNewInstanceConditions(player):
    party = player.getParty()
    if party == None:
        return True
    if not party.isLeader(player):
        player.sendPacket(SystemMessage(2185))
        return False
    for partyMember in party.getPartyMembers().toArray():
        if not partyMember.isInsideRadius(player, 500, False, False):
            sm = SystemMessage(2096)
            sm.addCharName(partyMember)
            player.sendPacket(sm)
            return False
    for partyMember in party.getPartyMembers().toArray():
        if not isWithinLevel(partyMember):
            sm = SystemMessage(2097)
            sm.addCharName(partyMember)
            player.sendPacket(sm)
            return False
    for partyMember in party.getPartyMembers().toArray():
        if not partyMember.getClassId().getId() in Class:
            sm = SystemMessage(2098)
            sm.addCharName(partyMember)
            player.sendPacket(sm)
            return False
    return True

def NextTimeInstance(player):
    INSTANCEID = 11
    party = player.getParty()
    for partyMember in party.getPartyMembers().toArray():
        InstanceManager.getInstance().setInstanceTime(partyMember.getObjectId(), INSTANCEID, ((System.currentTimeMillis() + 18000000))) # 18000000
        sm = SystemMessage(SystemMessageId.INSTANT_ZONE_RESTRICTED)
        sm.addString(InstanceManager.getInstance().getInstanceIdName(INSTANCEID))
        partyMember.sendPacket(sm)

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

def enterInstance(self,player,template,tele,quest):
    instanceId = getExistingInstanceId(player)
    if instanceId == 0:
        if not checkNewInstanceConditions(player):
            return
        instanceId = InstanceManager.getInstance().createDynamicInstance(template)    
        if not self.worlds.has_key(instanceId):
            world = PyObject()
            world.rewarded = []
            world.instanceId = instanceId
            self.worlds[instanceId] = world
            self.world_ids.append(instanceId)
            self.currentWorld = instanceId
            player.stopAllEffects()
            print " " + template + " " + str(instanceId) + " " + str(player.getName()) 
            runStartRoom(self, world)
            tele.instanceId = instanceId
            teleportPlayer(self, player, tele)
            party = player.getParty()
            if party != None:
                for partyMember in party.getPartyMembers().toArray():
                    partyMember.stopAllEffects()
                    teleportPlayer(self, partyMember, tele)
                    world.status = 1
    else:
        foundworld = False
        for worldid in self.world_ids:
            if worldid == instanceId:
                foundworld = True
        if not foundworld:
            player.sendPacket(SystemMessage.sendString("Not foundworld"))    
            return
        player.stopAllEffects()
        tele.instanceId = instanceId
        teleportPlayer(self, player, tele)
    return

def exitInstance(player, tele):
    player.setInstanceId(0)
    player.teleToLocation(tele.x, tele.y, tele.z)
    pet = player.getPet()
    if pet != None :
        pet.setInstanceId(0)
        pet.teleToLocation(tele.x, tele.y, tele.z)

def runStartRoom(self, world):
    world.status = 0
    world.startRoom = PyObject()
    world.startRoom.npclist = {}
    newNpc = self.addSpawn(18348,-109613,75659,-12629,34047,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-109610,76069,-12629,31728,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18347,-109742,74500,-12536,29412,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109793,75782,-12629,65119,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-109704,76569,-12629,63714,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-109695,76740,-12629,62467,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-109591,76820,-12629,63813,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109792,76620,-12629,5636,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-110890,76136,-12725,64032,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-110620,76050,-12725,64399,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-110954,75950,-12725,65242,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18358,-109127,76222,-12629,48636,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109046,76479,-12629,49568,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109261,76492,-12629,48441,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109711,77577,-12675,1480,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109815,77429,-12639,48131,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109605,77439,-12642,46843,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109609,77839,-12725,65039,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109864,78063,-12725,49538,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-109810,77810,-12725,50149,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18352,-109773,78312,-12725,48006,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109650,78563,-12725,48154,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109953,78519,-12725,52342,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111189,78042,-12821,65325,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111395,77902,-12821,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111472,77849,-12821,924,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111286,78005,-12821,61291,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-110594,78277,-12850,47308,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110356,78260,-12848,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110478,78423,-12888,48408,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110484,78619,-12917,47671,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110360,79079,-12917,49700,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110586,79178,-12917,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110579,78912,-12917,46671,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111774,77955,-12821,904,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112156,77861,-12821,63813,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112123,78061,-12824,64055,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111214,77010,-12725,16756,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111211,77227,-12725,16077,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111113,76447,-12725,15192,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111341,76393,-12725,14468,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111123,76847,-12725,15949,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111332,76806,-12725,16641,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111342,76079,-12725,15842,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18353,-111175,75631,-12728,16102,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111294,75451,-12725,18232,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-111014,75491,-12725,23899,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111968,80549,-12920,682,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-111955,80769,-12917,65001,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109063,80634,-12917,29983,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108813,80648,-12917,32605,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108969,80755,-12917,32024,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108936,80512,-12917,32767,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108163,80397,-12899,18763,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108036,80245,-12861,15961,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108294,80219,-12855,16250,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108167,79885,-12771,14970,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108036,79995,-12799,16850,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108038,80641,-12917,31497,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107798,80766,-12917,30880,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107802,80509,-12917,33101,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18354,-108584,78863,-12725,15985,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-108666,78743,-12725,16159,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-108457,78715,-12725,14325,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108438,78135,-12621,16651,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108434,77851,-12550,17251,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108662,77708,-12533,17138,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108461,77207,-12533,34290,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108707,77085,-12533,63092,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108723,77304,-12533,254,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108940,77195,-12533,63554,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109099,77193,-12536,204,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108670,76427,-12533,15794,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108442,76333,-12533,16635,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108442,76641,-12533,14402,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108657,76020,-12533,9501,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18355,-107790,75995,-12533,34826,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107688,76074,-12533,36736,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107696,75872,-12533,32259,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108290,82762,-12885,33252,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108189,83065,-12885,45640,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112333,80741,-12917,18259,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112565,80666,-12917,1515,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112699,80562,-12917,1006,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112848,80160,-12917,16837,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112720,79878,-12920,15502,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112878,79875,-12917,14465,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112806,79658,-12917,14903,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112757,79574,-12917,14364,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112393,79576,-12917,11703,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112856,79071,-12917,15063,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112649,79081,-12917,14729,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112299,79063,-12917,17658,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-112333,79262,-12917,28114,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-112553,79452,-12917,21220,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-112411,78903,-12917,16709,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112295,78557,-12917,15185,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112508,78568,-12917,11982,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112554,78071,-12821,5473,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-112659,77916,-12839,64238,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112962,77832,-12915,64884,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112931,78074,-12906,61198,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-113148,78015,-12917,336,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-113702,78113,-12917,62951,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-113716,77887,-12917,1221,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18356,-113530,78000,-12917,3014,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110474,83240,-12885,48979,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110577,83404,-12885,49277,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110368,83391,-12885,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110364,84217,-12981,47643,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110369,83979,-12981,48866,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110475,84112,-12978,50387,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110597,84297,-12981,46750,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-110596,83948,-12981,49495,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110596,83736,-12937,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-109988,84106,-12905,31385,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109832,84220,-12885,32408,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109830,83983,-12885,30084,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109422,84114,-12885,32124,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109178,84109,-12885,31689,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108958,84112,-12885,32767,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108970,84517,-12885,27752,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109153,84531,-12885,31971,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-109365,84532,-12885,32718,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108626,84488,-12885,33338,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108215,84504,-12885,33097,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108350,84596,-12885,33309,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107957,84613,-12885,33087,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107973,84366,-12885,31546,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108328,84393,-12888,54566,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107290,84205,-12885,16791,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107531,84222,-12885,11547,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107395,83529,-12885,18064,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107514,83836,-12885,16280,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107294,83855,-12885,14926,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-107504,82863,-12917,11200,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107209,83075,-12917,31685,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-106901,83070,-12917,31728,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-106960,81825,-12917,17251,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107312,81766,-12917,13028,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107496,81774,-12917,12090,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18357,-107407,81946,-12917,15732,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107303,80617,-12917,15086,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107521,80781,-12917,4554,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107528,85126,-12981,48112,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107301,85172,-12981,48441,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-107418,84998,-12965,51279,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18360,-107465,85672,-12981,46802,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107305,85815,-12981,49412,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18351,-107578,85810,-12981,47344,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110488,85639,-12984,49249,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110499,85989,-12981,49009,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112454,82643,-12885,55115,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112597,82694,-12885,55899,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112409,82765,-12885,50191,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112629,82950,-12885,46058,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112775,83014,-12885,52778,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-112554,83065,-12885,54955,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-112877,83937,-12885,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-112761,84141,-12885,46919,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-112535,84227,-12897,35347,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-112543,84000,-12895,30068,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-112148,84192,-12981,32928,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-111424,84002,-12981,30514,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-111134,84226,-12981,33221,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-111134,83996,-12981,32096,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-113398,83620,-12981,64106,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18348,-113409,83842,-12981,1154,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-114150,83662,-12981,64412,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-114148,83901,-12981,826,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18349,-108174,83590,-12883,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-108192,83723,-12883,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18361,-108111,83692,-12883,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18347,-107892,83731,-12883,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18359,-114005,83778,-12981,64682,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(32260,-113123,87082,-12880,1796,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(32261,-115009,87089,-12780,65101,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(32262,-116900,87083,-12687,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(32258,-120085,87176,-12594,65342,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(25528,-110935,87175,-12985,55000,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18437,-111287,74504,-12441,0,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    newNpc.setIsInvul(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-109975,86016,-12981,18574,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110502,87280,-12981,47257,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-114285,83809,-12981,64349,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110766,84249,-12981,33276,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-112907,84184,-12885,51239,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-107283,85571,-12981,24731,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-109426,84549,-12885,816,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-106881,81680,-12917,13028,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-107258,83605,-12885,37604,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110621,83447,-12885,49151,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-113895,78267,-12920,57825,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-108597,82711,-12885,41681,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-112904,79038,-12917,14103,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-107494,76160,-12533,31045,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-107466,75747,-12533,31888,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-108042,78657,-12725,15278,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110007,78662,-12725,51879,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110477,77870,-12821,64923,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-111344,75306,-12725,13828,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110049,80043,-12981,16383,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18478,-110901,80037,-12981,15229,False,0,False,world.instanceId)
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False

def runSecondRoom(self, world):
    world.status = 2
    world.startRoom = PyObject()
    world.startRoom.npclist = {}
    newNpc = self.addSpawn(18349,-110594,78277,-12850,47308,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110356,78260,-12848,49151,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110478,78423,-12888,48408,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110484,78619,-12917,47671,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110360,79079,-12917,49700,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110586,79178,-12917,49151,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False
    newNpc = self.addSpawn(18350,-110579,78912,-12917,46671,False,0,False,world.instanceId)#
    newNpc.setIsNoRndWalk(True)
    world.startRoom.npclist[newNpc] = False

class PyObject:
    pass

def openDoor(doorId, instanceId):
    for door in InstanceManager.getInstance().getInstance(instanceId).getDoors():
        if door.getDoorId() == doorId:
            door.openMe()

class NornilsGarden(JQuest) :

    def __init__(self, id, name, desc):
        JQuest.__init__(self, id, name, desc)
        self.worlds = {}
        self.world_ids = []
        self.currentWorld = 0

    def onAdvEvent (self,event,npc,player) :
        htmltext = event
        st = player.getQuestState(qn)
        if not st : return

        passwrd = st.getInt("pass")

        if event == "32260-02.htm" :
            st.set("pass","0")
        elif event == "32260-03a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32260-03.htm"
        elif event == "32260-04a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32260-04.htm"
        elif event == "32260-05a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32260-05.htm"
        elif event == "32260-06a.htm" :
            st.set("pass",str(passwrd+1))
            if st.getInt("pass") == 4:
                world = self.worlds[player.getInstanceId()]
                openDoor(16200014, world.instanceId)
                player.sendPacket(ExShowScreenMessage("Screen Message",15000))
                htmltext = "..."
            else:
                return "32260-06.htm"
        elif event == "32261-02.htm" :
            st.set("pass","0")
        elif event == "32261-03a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32261-03.htm"
        elif event == "32261-04a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32261-04.htm"
        elif event == "32261-05a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32261-05.htm"
        elif event == "32261-06a.htm" :
            st.set("pass",str(passwrd+1))
            if st.getInt("pass") == 4:
                world = self.worlds[player.getInstanceId()]
                openDoor(16200015, world.instanceId)
                player.sendPacket(ExShowScreenMessage("Screen Message",15000))
                htmltext = "..."
            else:
                return "32261-06.htm"
        elif event == "32262-02.htm" :
            st.set("pass","0")
        elif event == "32262-03a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32262-03.htm"
        elif event == "32262-04a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32262-04.htm"
        elif event == "32262-05a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32262-05.htm"
        elif event == "32262-06a.htm" :
            st.set("pass",str(passwrd+1))
            htmltext = "32262-06.htm"
        elif event == "32262-07a.htm" :
            st.set("pass",str(passwrd+1))
            if st.getInt("pass") == 5:
                world = self.worlds[player.getInstanceId()]
                openDoor(16200016, world.instanceId) 
                player.sendPacket(ExShowScreenMessage("Screen Message",15000))
                htmltext = "..."
            else:
                return "32262-07.htm"
        return htmltext

    def onTalk (self, npc, player):
        npcId = npc.getNpcId()
        st = player.getQuestState(qn)
        if not st :
            st = self.newQuestState(player)
        if npcId == 32330 :
            party = player.getParty()
            if not party :
                htmltext = "<html><body>...</body></html>"
                return htmltext
            if player.getLevel() < 17 or player.getLevel() > 21 :
                htmltext = "<html><body>...</body></html>"
                return htmltext
            if not checkPrimaryConditions(player):
                htmltext = "<html><body>...</body></html>"
                return htmltext
            for partyMember in party.getPartyMembers().toArray():
                st2 = partyMember.getQuestState("179_IntoTheLargeCavern")
                if not st2 and partyMember.getRace().ordinal() == 5 :
                    htmltext = "<html><body>...</body></html>"
                    return htmltext
            for partyMember in party.getPartyMembers().toArray():
                st2 = partyMember.getQuestState("179_IntoTheLargeCavern")
                if st2 and st2.getState() == State.COMPLETED:
                    htmltext = "<html><body>...</body></html>"
                    return htmltext
            tele = PyObject()
            tele.x = -111183
            tele.y = 74553
            tele.z = -12432
            enterInstance(self, player, "NGarden.xml", tele, self)
        if self.worlds.has_key(npc.getInstanceId()):
            world = self.worlds[npc.getInstanceId()]
            st2 = player.getQuestState("179_IntoTheLargeCavern")
            if st2 :
                if npcId == 32260 :
                    htmltext = "32260-01.htm"
                    return htmltext
                elif npcId == 32261 :
                    htmltext = "32261-01.htm"
                    return htmltext
                elif npcId == 32262 :
                    htmltext = "32262-01.htm"
                    return htmltext
            else :
                return "<html><body>...</body></html>"
        return ""

    def onAttack(self, npc, player, damage, isPet, skill):
        npcId = npc.getNpcId()
        if self.worlds.has_key(npc.getInstanceId()):
            world = self.worlds[npc.getInstanceId()]
            if npcId == 18347:
                if world.status == 1:
                    world.status = 2
                    newpath = self.addSpawn(18349, -110016, 74512, -12533, 0, False, 0, False, npc.getInstanceId())
                    newpath = self.addSpawn(18348, -109729, 74913, -12533, 0, False, 0, False, npc.getInstanceId())
                    newpath = self.addSpawn(18348, -109981, 74899, -12533, 0, False, 0, False, npc.getInstanceId())
                    openDoor(16200001, world.instanceId)
        return

    def onAggroRangeEnter(self, npc, player, isPet) : 
        if self.worlds.has_key(npc.getInstanceId()):
            world = self.worlds[npc.getInstanceId()]
            if npc.getNpcId() == 18437:
                npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE)
                npc.getAggroList().remove(player)
                npc.setTarget(player)
                npc.doCast(SkillTable.getInstance().getInfo(4322,1))
                npc.doCast(SkillTable.getInstance().getInfo(4327,1))
                npc.doCast(SkillTable.getInstance().getInfo(4329,1))
                npc.doCast(SkillTable.getInstance().getInfo(4324,1))
                return
        return

    def onKill(self,npc,player,isPet):
        npcId = npc.getNpcId()
        objId = npc.getObjectId()
        if self.worlds.has_key(npc.getInstanceId()):
            world = self.worlds[npc.getInstanceId()]
            if npcId == 25528 :              
                npc.broadcastPacket(NpcSay(objId, 0, npc.getNpcId(), "..."))
                dropItem(player,npc,9712,1)  
            elif npcId == 18352 :            
                NextTimeInstance(player)
                dropItem(player,npc,9703,1)  
            elif npcId == 18353 :            
                dropItem(player,npc,9704,1)  
            elif npcId == 18354 :            
                runSecondRoom(self, world)
                dropItem(player,npc,9705,1)  
            elif npcId == 18355 :            
                dropItem(player,npc,9706,1)  
            elif npcId == 18356 :            
                openDoor(16200018, world.instanceId)
                openDoor(16200024, world.instanceId)
                dropItem(player,npc,9707,1)  
            elif npcId == 18357 :            
                openDoor(16200019, world.instanceId)
                openDoor(16200025, world.instanceId)
                dropItem(player,npc,9708,1)  
            elif npcId == 18358 :            
                dropItem(player,npc,9713,1)  
            elif npcId == 18359 :            
                openDoor(16200013, world.instanceId)
                dropItem(player,npc,9709,1)  
            elif npcId == 18360 :            
                openDoor(16200017, world.instanceId)
                openDoor(16200023, world.instanceId)
                dropItem(player,npc,9710,1) 
            elif npcId == 18361 :            
                dropItem(player,npc,9711,1)  
        return

QUEST = NornilsGarden(-1, qn, "instances")

QUEST.addStartNpc(32330)

QUEST.addTalkId(32260)
QUEST.addTalkId(32261)
QUEST.addTalkId(32262)
QUEST.addTalkId(32330)

QUEST.addAttackId(18347)

QUEST.addAggroRangeEnterId(18437)

for mob in [25528,18352,18353,18354,18355,18356,18357,18358,18359,18360,18361,18478] :
	QUEST.addKillId(mob)