# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "262_BringMeMushrooms1"

FUNGUS_SAC = 707
ADENA = 57

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [FUNGUS_SAC]

 def onAdvEvent (self,event,npc, player) :
    htmltext = event
    st = player.getQuestState(qn)
    if not st : return
    if event == "30137-03.htm" :
      st.set("cond","1")
      st.setState(State.STARTED)
      st.playSound("ItemSound.quest_accept")
    return htmltext

 def onTalk (self,npc,player):
   htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
   st = player.getQuestState(qn)
   if not st : return htmltext

   npcId = npc.getNpcId()
   id = st.getState()

   if id == State.CREATED :
     st.set("cond","0")
   if st.getInt("cond")==0 :
     if player.getLevel() >= 8 :
       htmltext = "30137-02.htm"
     else:
       htmltext = "30137-01.htm"
       st.exitQuest(1)
   else :
     if st.getQuestItemsCount(FUNGUS_SAC)<10 :
       htmltext = "30137-04.htm"
     else :
       st.giveItems(ADENA,3000)
       st.takeItems(FUNGUS_SAC,-1)
       st.exitQuest(1)
       st.playSound("ItemSound.quest_finish")
       htmltext = "30137-05.htm"
   return htmltext

 def onKill(self,npc,player,isPet):
   st = player.getQuestState(qn)
   if not st : return 
   if st.getState() != State.STARTED : return 
   
   count = st.getQuestItemsCount(FUNGUS_SAC)   
   if count < 10 :
     st.giveItems(FUNGUS_SAC,2)
     if count == 8 :
       st.playSound("ItemSound.quest_middle")
       st.set("cond","2")
     else :
       st.playSound("ItemSound.quest_itemget")
   return

QUEST       = Quest(262,qn,"Trade with the Ivory Tower")

QUEST.addStartNpc(30137)

QUEST.addTalkId(30137)

QUEST.addKillId(20400)
QUEST.addKillId(20007)