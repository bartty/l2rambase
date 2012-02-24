package events.TheValentineEvent;

import l2.Rambase.gameserver.instancemanager.QuestManager;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.util.Rnd;

import events.EventsConfig;

/**
 * @author  L2RamBase Project
 * @recoded for Hi5 ShadowMan
 */
public class TheValentineEvent extends Quest
{
        private static final String qn = "TheValentineEvent";
        private static final int _npc           = 4301;

        public TheValentineEvent(int questId, String name, String descr)
        {
                super(questId, name, descr);
                addStartNpc(_npc);
                addFirstTalkId(_npc);
                addTalkId(_npc);                
        }

        public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
        {
                String htmltext = "";
                QuestState st = player.getQuestState(qn);
                htmltext = event;
                if(event.equalsIgnoreCase("recipe"))
                {
                    st.giveItems(EventsConfig.TVE_RECIPE, 1);
                }
                else if (event.equalsIgnoreCase("completeheart"))
                {
                    checkHeart(player,st);          
                }
			return htmltext;
        }

        private void checkHeart(L2PcInstance player, QuestState st) 
        {
                long count1 = st.getQuestItemsCount(4209);
                long count2 = st.getQuestItemsCount(4210);
                long count3 = st.getQuestItemsCount(4211);
                long count4 = st.getQuestItemsCount(4212);
                long count5 = st.getQuestItemsCount(4213);
                long count6 = st.getQuestItemsCount(4214);
                long count7 = st.getQuestItemsCount(4215);
                long count8 = st.getQuestItemsCount(4216);
                long count9 = st.getQuestItemsCount(4217);
                if (count1 >= 1 && count2 >= 1 && count3 >= 1 && count4 >= 1 && count5 >= 1 && count6 >= 1 && count7 >= 1 && count8 >= 1 && count9 >= 1)
                {
                        int chance;
                        chance = Rnd.get(1,4);
                        st.takeItems(4209, 1);
                        st.takeItems(4210, 1);
                        st.takeItems(4211, 1);
                        st.takeItems(4212, 1);
                        st.takeItems(4213, 1);
                        st.takeItems(4214, 1);
                        st.takeItems(4215, 1);
                        st.takeItems(4216, 1);
                        st.takeItems(4217, 1);
                        switch(chance)
                        {
                                case 1:                                 
                                        st.giveItems(EventsConfig.TVE_BOW, 1);
                                        break;
                                case 2:
                                        st.giveItems(EventsConfig.TVE_BOX, 1);
                                        break;
                                case 3:
                                        st.giveItems(EventsConfig.TVE_CAKE1, 1);
                                        break;
                                case 4:
                                        st.giveItems(EventsConfig.TVE_CAKE2, 1);
                                        break;
                        }                       
                }               
        }
		
        public String onFirstTalk(L2Npc npc, L2PcInstance player)
        {
                String htmltext = "";
                QuestState st = player.getQuestState(getName());
                if (st == null)
                {
                        Quest q = QuestManager.getInstance().getQuest(getName());
                        st = q.newQuestState(player);
                }
                if (EventsConfig.TVE_STARTED)
                {
                        htmltext = npc.getNpcId() + ".htm";
                }
                else
                {
                        htmltext = EventsConfig.EVENT_DISABLED;
                }
                return htmltext;
        }       
        
    	public static void main(String[] args)
    	{
    		new TheValentineEvent(-1,qn,"events");
    		if (EventsConfig.TVE_STARTED)
    			_log.info("Event System: The Valentine Event loaded ...");
    	}
    	
    	
}