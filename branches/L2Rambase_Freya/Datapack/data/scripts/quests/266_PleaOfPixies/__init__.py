# L2Rambase Project
# need check drop chance
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "266_PleaOfPixies"

PREDATORS_FANG = 1334
EMERALD = 1337
BLUE_ONYX = 1338
ONYX = 1339
GLASS_SHARD = 1336
REC_LEATHER_BOOT = 2176
REC_SPIRITSHOT = 3032

DROP={20530:[[0,11,5]],20534:[[0,11,5],[0,11,5]],20537:[[0,11,5]],20525:[[0,11,5],[0,11,5]]}

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [PREDATORS_FANG]

 def onAdvEvent (self,event,npc, player) :
    htmltext = event
    st = player.getQuestState(qn)
    if not st : return
    if event == "31852-03.htm" :
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
     if player.getRace().ordinal() != 1 :
       htmltext = "31852-00.htm"
       st.exitQuest(1)
     elif player.getLevel()<3 :
       htmltext = "31852-01.htm"
       st.exitQuest(1)
     else :
          htmltext = "31852-02.htm"
   else :
     if st.getQuestItemsCount(PREDATORS_FANG)<100 :
       htmltext = "31852-04.htm"
     else :
       st.takeItems(PREDATORS_FANG,-1)
       n = st.getRandom(100)
       if n<2 :
          st.giveItems(EMERALD,1)
          st.giveItems(REC_SPIRITSHOT,1)
          st.playSound("ItemSound.quest_jackpot")
       elif n<20 :
          st.giveItems(BLUE_ONYX,1)
          st.giveItems(REC_LEATHER_BOOT,1)
       elif n<45 :
          st.giveItems(ONYX,1)
       else:
          st.giveItems(GLASS_SHARD,1)
       htmltext = "31852-05.htm"
       st.exitQuest(1)
       st.playSound("ItemSound.quest_finish")
   return htmltext

 def onKill(self,npc,player,isPet):
   st = player.getQuestState(qn)
   if not st : return 
   if st.getState() != State.STARTED : return 
   
   if st.getInt("cond") == 1:
      npcId = npc.getNpcId()
      count = st.getQuestItemsCount(PREDATORS_FANG)
      if count+5==100 :
        st.playSound("ItemSound.quest_middle")
        st.set("cond","2")
      else :
        st.playSound("ItemSound.quest_itemget")
      st.giveItems(PREDATORS_FANG,5)
   return

QUEST       = Quest(266,qn,"Plea Of Pixies")

QUEST.addStartNpc(31852)

QUEST.addTalkId(31852)

QUEST.addKillId(20525)
QUEST.addKillId(20530)
QUEST.addKillId(20534)
QUEST.addKillId(20537)