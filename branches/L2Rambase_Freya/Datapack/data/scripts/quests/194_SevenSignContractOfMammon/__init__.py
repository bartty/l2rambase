# L2Rambase Project
# need check
# last checked 11.08.2010

import sys
from l2.rambase.gameserver.datatables         import SkillTable
from l2.rambase.gameserver.model.actor.instance import L2PcInstance
from l2.rambase.gameserver.model.quest        import State
from l2.rambase.gameserver.model.quest        import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.gameserver.network.serverpackets import ExStartScenePlayer

qn = "194_SevenSignContractOfMammon"

#NPCs 
SirGustavAthebaldt = 30760
Kollin = 32571
FrogKing = 32572
Tess = 32573
Kuta = 32574
ClaudiaAthebalt = 31001
#ITEMS
AthebaltsIntroduction = 13818
FrogKingsBead = 13820
GrandmaTessCandyPouch = 13821
NativesGlove = 13819
#Transformation's skills
Frog = 6201
YoungChild = 6202
Native = 6203


 
class Quest (JQuest):

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [AthebaltsIntroduction,FrogKingsBead,GrandmaTessCandyPouch,NativesGlove]

 def onAdvEvent (self,event,npc,player):
   htmltext = event
   st = player.getQuestState(qn)
   if not st: return
   if event == "30760-1.htm":
     st.set("cond","1")
     st.setState(State.STARTED)
     st.playSound("ItemSound.quest_accept")
   if event == "30760-3.htm":
     st.set("cond","2")
     st.playSound("ItemSound.quest_middle")
   elif event == "30760-4.htm":
     player.showQuestMovie(10)
     self.startQuestTimer("normal_world",100000,None,player)
     return
   elif event == "30760-6.htm":
     st.giveItems(AthebaltsIntroduction,1)
     st.set("cond","3")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-3.htm":
     st.takeItems(AthebaltsIntroduction,-1)
     player.stopAllEffects()
     player.doCast(SkillTable.getInstance().getInfo(Frog,1))
     st.set("cond","4")
     st.playSound("ItemSound.quest_middle")
   elif event == "32572-3.htm":
     st.giveItems(FrogKingsBead,1)
     st.set("cond","5")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-5.htm":
     st.takeItems(FrogKingsBead,-1)
     st.set("cond","6")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-7.htm":
     player.stopAllEffects()
     player.doCast(SkillTable.getInstance().getInfo(YoungChild,1))
     st.set("cond","7")
     st.playSound("ItemSound.quest_middle")
   elif event == "32573-2.htm":
     st.giveItems(GrandmaTessCandyPouch,1)
     st.set("cond","8")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-9.htm":
     st.takeItems(GrandmaTessCandyPouch,-1)
     st.set("cond","9")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-11.htm":
     player.stopAllEffects()
     player.doCast(SkillTable.getInstance().getInfo(Native,1))
     st.set("cond","10")
     st.playSound("ItemSound.quest_middle")
   elif event == "32574-3.htm":
     st.giveItems(NativesGlove,1)
     st.set("cond","11")
     st.playSound("ItemSound.quest_middle")
   elif event == "32571-13.htm":
     st.takeItems(NativesGlove,-1)
     player.stopAllEffects()
     st.set("cond","12")
     st.playSound("ItemSound.quest_middle")
   elif event == "31001-2.htm":
     st.addExpAndSp(52518015, 5817677)
     st.unset("cond") 
     st.exitQuest(False)
     st.playSound("ItemSound.quest_finish")
   elif event == "normal_world":
     return "30760-4.htm"
   return htmltext

 def onTalk (self,npc,player):
   st = player.getQuestState(qn)
   st1 = player.getQuestState("193_SevenSignDyingMessage")
   htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>" 
   if not st: return htmltext
   npcId = npc.getNpcId()
   id = st.getState()
   cond = st.getInt("cond")
   if id == State.COMPLETED: htmltext = "<html><body>This quest has already been completed.</body></html>"
   elif npcId == SirGustavAthebaldt:
     if st1:
       if st1.getState() == State.COMPLETED:
         if cond == 0:
           if player.getLevel() >= 79: htmltext = "30760-0.htm"
           else:
             htmltext = "30760-0a.htm"
             st.exitQuest(1)
         elif cond == 1: htmltext = "30760-1.htm"
         elif cond == 2: htmltext = "30760-3.htm"
         elif cond == 3: htmltext = "30760-6a.htm"
       else: return "30760-0b.htm"
     if not st1: return "30760-0b.htm"
   elif npcId == Kollin:
     if cond == 3: htmltext = "32571-0.htm"
     elif cond == 4 and player.getFirstEffect(Frog) != None: htmltext = "32571-3a.htm"
     elif cond == 4 and player.getFirstEffect(Frog) == None:
       player.doCast(SkillTable.getInstance().getInfo(Frog,1))
       htmltext = "32571-3a.htm"
     elif cond == 5: htmltext = "32571-4.htm"
     elif cond == 6: htmltext = "32571-6.htm"
     elif cond == 7 and player.getFirstEffect(Frog) != None: htmltext = "32571-7a.htm"
     elif cond == 7 and player.getFirstEffect(Frog) == None:
       player.doCast(SkillTable.getInstance().getInfo(YoungChild,1))
       htmltext = "32571-7a.htm"
     elif cond == 8: htmltext = "32571-8.htm"
     elif cond == 9: htmltext = "32571-10.htm"
     elif cond == 10 and player.getFirstEffect(Native) != None: htmltext = "32571-11a.htm"
     elif cond == 10 and player.getFirstEffect(Native) == None:
       player.doCast(SkillTable.getInstance().getInfo(Native,1))
       htmltext = "32571-11a.htm"
     elif cond == 11: htmltext = "32571-12.htm"
     elif cond == 12: htmltext = "32571-13a.htm"	 
   elif npcId == FrogKing:
     if cond == 4 and player.getFirstEffect(Frog) != None: htmltext = "32572-0.htm"
     elif cond == 4 and player.getFirstEffect(Frog) == None: htmltext = "32572-0a.htm"
   elif npcId == Tess:
     if cond == 7 and player.getFirstEffect(YoungChild) != None: htmltext = "32573-0.htm"
     elif cond == 7 and player.getFirstEffect(YoungChild) == None: htmltext = "32573-0a.htm"
   elif npcId == Kuta:
     if cond == 10 and player.getFirstEffect(Native) != None: htmltext = "32574-0.htm"
     elif cond == 10 and player.getFirstEffect(Native) == None: htmltext = "32574-0a.htm"
   elif npcId == ClaudiaAthebalt:
     if cond == 12: htmltext = "31001-0.htm"
   return htmltext

QUEST     = Quest(194,qn,"Seven Sign Contract of Mammon") 

QUEST.addStartNpc(SirGustavAthebaldt)
QUEST.addTalkId(SirGustavAthebaldt)
QUEST.addTalkId(Kollin)
QUEST.addTalkId(FrogKing)
QUEST.addTalkId(Tess)
QUEST.addTalkId(Kuta)
QUEST.addTalkId(ClaudiaAthebalt)