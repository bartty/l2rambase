# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest              import State
from l2.rambase.gameserver.model.quest              import QuestState
from l2.rambase.gameserver.model.quest.jython       import QuestJython as JQuest
from l2.rambase.util                                import Rnd

qn = "10276_MutatedKaneusGludio"

#NPCs
Bathis = 30332
Rohmer = 30344
Tomlan = 18554
Ariosh = 18555

#items
TomlanTissue = 13830
ArioshTissue = 13831


class Quest (JQuest):
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [TomlanTissue,ArioshTissue]

    def onAdvEvent (self,event,npc, player):
        htmltext = event
        st = player.getQuestState(qn)
        if not st: return
        if event == "30332-03.htm":
            st.set("cond","1")
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
        elif event == "30344-02.htm":
            st.giveItems(57,8500)
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
            if npcId == Bathis:
                htmltext = "30332-0a.htm"
        elif id == State.CREATED and npcId == Bathis:
            if player.getLevel() >= 18:
                htmltext = "30332-01.htm"
            else:
                htmltext = "30332-00.htm"
        else:
            if npcId == Bathis:
                if st.getQuestItemsCount(TomlanTissue) == 0 or st.getQuestItemsCount(ArioshTissue) == 0:
                   htmltext = "30332-04.htm"
                elif st.getQuestItemsCount(TomlanTissue) > 0 and st.getQuestItemsCount(ArioshTissue) > 0:
                   htmltext = "30332-05.htm"
            elif npcId == Rohmer:
                if player.getLevel() < 18:
                   return htmltext
                if st.getQuestItemsCount(TomlanTissue) > 0 and st.getQuestItemsCount(ArioshTissue) > 0:
                   htmltext = "30344-01.htm"
                else:
                   htmltext = "30344-01a.htm"
        return htmltext

    def onKill(self,npc,player,isPet):
        npcId = npc.getNpcId()
        party = player.getParty()
        if party:
            PartyQuestMembers = []
            if npcId == Tomlan:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(TomlanTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(TomlanTissue,1)
                st.playSound("ItemSound.quest_middle")
            elif npcId == Ariosh:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(ArioshTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(ArioshTissue,1)
                st.playSound("ItemSound.quest_middle")
        else: # in case that party members disconnected or so
            st = player.getQuestState(qn)
            if not st: return
            if npcId == Tomlan:
                if st.getState() == State.STARTED and st.getQuestItemsCount(TomlanTissue) == 0:
                    st.giveItems(TomlanTissue,1)
                    st.playSound("ItemSound.quest_middle")
            elif npcId == Ariosh:
                if st.getState() == State.STARTED and st.getQuestItemsCount(ArioshTissue) == 0:
                    st.giveItems(ArioshTissue,1)
                    st.playSound("ItemSound.quest_middle")
        return

QUEST       = Quest(10276,qn,"Mutated Kaneus")

QUEST.addStartNpc(Bathis)
QUEST.addTalkId(Bathis)
QUEST.addTalkId(Rohmer)
QUEST.addKillId(Tomlan)
QUEST.addKillId(Ariosh)