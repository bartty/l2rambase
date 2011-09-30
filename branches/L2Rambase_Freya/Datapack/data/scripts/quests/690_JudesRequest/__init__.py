# author L2Rambase Project
# need test
import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "690_JudesRequest"

# NPC
JUDE = 32356
MOBS = [22398, 22399]

# Items
REWARDS  = [10373,10374,10375,10376,10377,10378,10379,10380,10381]
REWARDS2 = [10385,10386,10387,10388,10389,10390,10391,10392,10393]
MAT = [10397,10398,10399,10400,10401,10402,10403,10404,10405]

EVIL = 10327

#Change this value to 1 if you wish 100% recipes, default 60%
ALT_RP100 = 0

class Quest (JQuest) :

  def __init__(self, id, name, descr) : 
    JQuest.__init__(self, id, name, descr)
    self.questItemIds = [EVIL]

  def onEvent (self, event, st) :
    htmltext = event
    evil = st.getQuestItemsCount(EVIL)
    if event == "32356-03.htm" :
      if st.getPlayer().getLevel() >= 78 :
        st.set("cond","1")
        st.setState(State.STARTED)
        st.playSound("ItemSound.quest_accept")
      else :
        htmltext = "32356-02.htm"
        st.exitQuest(1)
    elif event == "32356-07.htm" :
      if evil >= 200 :
        htmltext = "32356-07.htm"
        st.takeItems(EVIL, 200)
        if ALT_RP100 == 1 :
          st.giveItems(REWARDS2[st.getRandom(len(REWARDS2))], 1)
        else:
          st.giveItems(REWARDS[st.getRandom(len(REWARDS))], 1)
      else :
        htmltext = "32356-05.htm"
    elif event == "32356-08.htm" :
      st.exitQuest(1)
      st.playSound("ItemSound.quest_giveup")
    elif event == "32356-09.htm" :
      if evil >= 5 :
        htmltext = "32356-09.htm"
        st.takeItems(EVIL,5)
        st.giveItems(MAT[st.getRandom(len(MAT))], 1)
        st.giveItems(MAT[st.getRandom(len(MAT))], 1)
        st.giveItems(MAT[st.getRandom(len(MAT))], 1)
    return htmltext    

  def onTalk (self, npc, player) :
     htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
     st = player.getQuestState(qn)
     if st :
       id = st.getState()
       cond = st.getInt("cond")
       evil = st.getQuestItemsCount(EVIL)
       if id == State.CREATED :
         if player.getLevel() >= 78 :
           htmltext="32356-01.htm"
         else :
           htmltext="32356-02.htm"
           st.exitQuest(1)
       else :
         if cond == 1 and evil >= 200 :
           htmltext = "32356-04.htm"
         elif cond == 1 and evil >= 5 and evil <= 200 :
           htmltext = "32356-05.htm"
         else :
           htmltext = "32356-05a.htm"
     return htmltext

  def onKill(self, npc, player, isPet) :
    partyMember = self.getRandomPartyMember(player, "1")
    if not partyMember: return
    st = partyMember.getQuestState(qn)
    if st :
      if st.getState() == State.STARTED :
        if st.getRandom(100) < 101 :
          st.giveItems(EVIL, 1)
          st.playSound("ItemSound.quest_itemget")
    return

QUEST       = Quest(690, qn, "Judes Request")

QUEST.addStartNpc(JUDE)

QUEST.addTalkId(JUDE)

for mobId in MOBS :
  QUEST.addKillId(mobId)