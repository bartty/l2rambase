# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest              import State
from l2.rambase.gameserver.model.quest              import QuestState
from l2.rambase.gameserver.model.quest.jython       import QuestJython as JQuest
from l2.rambase.util                                import Rnd

qn = "10280_MutatedKaneusSchuttgart"

#NPCs
Vishotsky = 31981
Atraxia = 31972
VenomousStorace = 18571
KelBilette = 18573

#items
VenomousStoraceTissue = 13838
KelBiletteTissue = 13839


class Quest (JQuest):
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [VenomousStoraceTissue,KelBiletteTissue]

    def onAdvEvent (self,event,npc, player):
        htmltext = event
        st = player.getQuestState(qn)
        if not st: return
        if event == "31981-03.htm":
            st.set("cond","1")
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
        elif event == "31972-02.htm":
            st.giveItems(57,210000)
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
            if npcId == Vishotsky:
                htmltext = "31981-0a.htm"
        elif id == State.CREATED and npcId == Vishotsky:
            if player.getLevel() >= 58:
                htmltext = "31981-01.htm"
            else:
                htmltext = "31981-00.htm"
        else:
            if npcId == Vishotsky:
                if st.getQuestItemsCount(VenomousStoraceTissue) == 0 or st.getQuestItemsCount(KelBiletteTissue) == 0:
                   htmltext = "31981-04.htm"
                elif st.getQuestItemsCount(VenomousStoraceTissue) > 0 and st.getQuestItemsCount(KelBiletteTissue) > 0:
                   htmltext = "31981-05.htm"
            elif npcId == Atraxia:
                if player.getLevel() < 58:
                   return htmltext
                if st.getQuestItemsCount(VenomousStoraceTissue) > 0 and st.getQuestItemsCount(KelBiletteTissue) > 0:
                   htmltext = "31972-01.htm"
                else:
                   htmltext = "31972-01a.htm"
        return htmltext

    def onKill(self,npc,player,isPet):
        npcId = npc.getNpcId()
        party = player.getParty()
        if party:
            PartyQuestMembers = []
            if npcId == VenomousStorace:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(VenomousStoraceTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(VenomousStoraceTissue,1)
                st.playSound("ItemSound.quest_middle")
            elif npcId == KelBilette:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(KelBiletteTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(KelBiletteTissue,1)
                st.playSound("ItemSound.quest_middle")
        else: # in case that party members disconnected or so
            st = player.getQuestState(qn)
            if not st: return
            if npcId == VenomousStorace:
                if st.getState() == State.STARTED and st.getQuestItemsCount(VenomousStoraceTissue) == 0:
                    st.giveItems(VenomousStoraceTissue,1)
                    st.playSound("ItemSound.quest_middle")
            elif npcId == KelBilette:
                if st.getState() == State.STARTED and st.getQuestItemsCount(KelBiletteTissue) == 0:
                    st.giveItems(KelBiletteTissue,1)
                    st.playSound("ItemSound.quest_middle")
        return

QUEST       = Quest(10280,qn,"Mutated Kaneus")

QUEST.addStartNpc(Vishotsky)
QUEST.addTalkId(Vishotsky)
QUEST.addTalkId(Atraxia)
QUEST.addKillId(VenomousStorace)
QUEST.addKillId(KelBilette)