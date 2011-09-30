# L2Rambase Project
# need check
# checked 11.08.2010

import sys
from l2.rambase.gameserver.ai                 import CtrlIntention
from l2.rambase.gameserver.model.quest        import State
from l2.rambase.gameserver.model.quest        import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.gameserver.network.serverpackets import CreatureSay
from l2.rambase.gameserver.network.serverpackets import ExStartScenePlayer

qn = "193_SevenSignDyingMessage"

#NPCs 
Hollint = 30191
Cain = 32569
Eric = 32570
SirGustavAthebaldt = 30760
#MOBS
ShilensEvilThoughts = 27402
#ITEMS
JacobsNecklace = 13814
DeadmansHerb = 13816
SculptureOfDoubt = 14352

class Quest (JQuest):

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [JacobsNecklace,DeadmansHerb,SculptureOfDoubt]

 def onAdvEvent (self,event,npc, player):
   htmltext = event
   st = player.getQuestState(qn)
   if not st: return
   if event == "30191-1.htm":
     st.giveItems(JacobsNecklace,1)
     st.set("cond","1")
     st.setState(State.STARTED)
     st.playSound("ItemSound.quest_accept")
   elif event == "32569-4.htm":
     st.takeItems(JacobsNecklace,-1)
     st.set("cond","2")
     st.playSound("ItemSound.quest_middle")
   elif event == "32570-1.htm":
     st.giveItems(DeadmansHerb,1)
     st.set("cond","3")
     st.playSound("ItemSound.quest_middle")
   elif event == "32569-6.htm":
      st.takeItems(DeadmansHerb,-1)
      player.showQuestMovie(9)
      self.startQuestTimer("normal_world",28000,None,player)
      return
   elif event == "32569-7.htm":
     npc.broadcastPacket(CreatureSay(npc.getObjectId(),0,npc.getName(),player.getName() + "! That stranger must be defeated. Here is the unlimate help!"))
     Evil = self.addSpawn(ShilensEvilThoughts,82500,47485,-3225,53495,False,300000)
     Evil.broadcastPacket(CreatureSay(Evil.getObjectId(),0,Evil.getName(),"You are not the owner of that item."))
     Evil.addDamageHate(player,0,999)
     Evil.setRunning()
     Evil.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player)
   elif event == "32569-11.htm":
     st.takeItems(SculptureOfDoubt,1)
     st.set("cond","5")
     st.playSound("ItemSound.quest_middle")
   elif event == "30760-1.htm":
     st.addExpAndSp(52518015, 5817677)
     st.unset("cond") 
     st.exitQuest(False)
     st.playSound("ItemSound.quest_finish")
   elif event == "normal_world":
     player.teleToLocation(82707,47019,-3235)
     return "32569-6.htm"
   return htmltext

 def onTalk (self,npc,player):
   st = player.getQuestState(qn)
   st1 = player.getQuestState("192_SevenSignSeriesOfDoubt")
   htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>" 
   if not st: return htmltext
   npcId = npc.getNpcId()
   id = st.getState()
   cond = st.getInt("cond")
   if id == State.COMPLETED:
     htmltext = "<html><body>This quest has already been completed.</body></html>"
   elif npcId == Hollint:
     if st1:
       if st1.getState() == State.COMPLETED:
         if cond == 0:
           if player.getLevel() >= 79: htmltext = "30191-0.htm"
           else:
             htmltext = "30191-0a.htm"
             st.exitQuest(1)
         elif cond >= 1: htmltext = "30191-1a.htm"
       else: return "30191-2.htm"
     if not st1: return "30191-2.htm"
   elif npcId == Cain:
     if cond == 1:
       htmltext = "32569-0.htm"
     elif cond == 2:
       htmltext = "32569-4a.htm"
     elif cond == 3:
       htmltext = "32569-5.htm"
     elif cond == 4:
       htmltext = "32569-8.htm"
   elif npcId == Eric:
     if cond == 2:
       htmltext = "32570-0.htm"
     elif cond >= 3:
       htmltext = "32570-1a.htm"
   elif npcId == SirGustavAthebaldt:
     if cond == 5:
       htmltext = "30760-0.htm"
   return htmltext

 def onKill (self,npc,player,isPet):
   st = player.getQuestState(qn)
   if not st or st.getInt("cond") != 3: return
   npcId = npc.getNpcId()
   npc.broadcastPacket(CreatureSay(npc.getObjectId(),0,npc.getName(),player.getName() + "! You may have won this time... But next time, I will surely capture you!"))
   if st.getQuestItemsCount(SculptureOfDoubt) == 0: st.giveItems(SculptureOfDoubt,1)
   st.set("cond","4")
   st.playSound("ItemSound.quest_middle")
   return

QUEST     = Quest(193,qn,"Seven Sign Dying Message") 

QUEST.addStartNpc(Hollint)
QUEST.addTalkId(Hollint)
QUEST.addTalkId(Cain)
QUEST.addTalkId(Eric)
QUEST.addTalkId(SirGustavAthebaldt)
QUEST.addKillId(ShilensEvilThoughts)