# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest              import State
from l2.rambase.gameserver.model.quest              import QuestState
from l2.rambase.gameserver.model.quest.jython       import QuestJython as JQuest
from l2.rambase.util                                import Rnd

qn = "10277_MutatedKaneusDion"

#NPCs
Lucas = 30071
Mirien = 30461
HatuOtis = 18558
Flouros = 18559

#items
HatuOtisTissue = 13832
FlourosTissue = 13833


class Quest (JQuest):
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [HatuOtisTissue,FlourosTissue]

    def onAdvEvent (self,event,npc, player):
        htmltext = event
        st = player.getQuestState(qn)
        if not st: return
        if event == "30071-03.htm":
            st.set("cond","1")
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
        elif event == "30461-02.htm":
            st.giveItems(57,20000)
            st.unset("cond")
            st.exitQuest(False)
            st.playSound("ItemSound.quest_finish")
        return htmltext

    def onTalk (self,npc,player):
        htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
        st = player.getQuestState(qn)
        if not st: return htmltext
        npcId = npc.getNpcId()
        id = st.getState()
        if id == State.COMPLETED:
            if npcId == Lucas:
                htmltext = "30071-0a.htm"
        elif id == State.CREATED and npcId == Lucas:
            if player.getLevel() >= 28:
                htmltext = "30071-01.htm"
            else:
                htmltext = "30071-00.htm"
        else:
            if npcId == Lucas:
                if st.getQuestItemsCount(HatuOtisTissue) == 0 or st.getQuestItemsCount(FlourosTissue) == 0:
                   htmltext = "30071-04.htm"
                elif st.getQuestItemsCount(HatuOtisTissue) > 0 and st.getQuestItemsCount(FlourosTissue) > 0:
                   htmltext = "30071-05.htm"
            elif npcId == Mirien:
                if player.getLevel() < 28:
                   return htmltext
                if st.getQuestItemsCount(HatuOtisTissue) > 0 and st.getQuestItemsCount(FlourosTissue) > 0:
                   htmltext = "30461-01.htm"
                else:
                   htmltext = "30461-01a.htm"
        return htmltext

    def onKill(self,npc,player,isPet):
        npcId = npc.getNpcId()
        party = player.getParty()
        if party:
            PartyQuestMembers = []
            if npcId == HatuOtis:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(HatuOtisTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(HatuOtisTissue,1)
                st.playSound("ItemSound.quest_middle")
            elif npcId == Flouros:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(FlourosTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(FlourosTissue,1)
                st.playSound("ItemSound.quest_middle")
        else: # in case that party members disconnected or so
            st = player.getQuestState(qn)
            if not st: return
            if npcId == HatuOtis:
                if st.getState() == State.STARTED and st.getQuestItemsCount(HatuOtisTissue) == 0:
                    st.giveItems(HatuOtisTissue,1)
                    st.playSound("ItemSound.quest_middle")
            elif npcId == Flouros:
                if st.getState() == State.STARTED and st.getQuestItemsCount(FlourosTissue) == 0:
                    st.giveItems(FlourosTissue,1)
                    st.playSound("ItemSound.quest_middle")
        return

QUEST       = Quest(10277,qn,"Mutated Kaneus")

QUEST.addStartNpc(Lucas)
QUEST.addTalkId(Lucas)
QUEST.addTalkId(Mirien)
QUEST.addKillId(HatuOtis)
QUEST.addKillId(Flouros)