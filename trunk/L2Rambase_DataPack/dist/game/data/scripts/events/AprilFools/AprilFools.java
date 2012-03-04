package events.AprilFools;

import l2.Rambase.gameserver.datatables.SkillTable;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.util.Rnd;
import events.EventsConfig;

/**
 * @author  L2RamBase Project
 * @recoded for Hi5 - ShadowMan
 */
public class AprilFools extends Quest
{
        private static final String qn = "AprilFools";

        private static final int April_Npc = 32639;
        
        private static final String start = "start.htm";
        private static final String good = "good.htm";
        private static final String bad = "bad.htm";
        private static final String no_items = "no_items.htm";
        
        /**
         * Good Reward List:
         * - Vesper Weapon/Armour Box (20958 - 17161)   Chance 2% and 3%
         * - Formal Dress 7 days (20099)                Chance 10%
         * - Special Aghations (14093,14094)            Chance 15%
         * - Noblesse Oblige (20967)                    Chance 5%
		 * - Vesper Crown (20965)                       Chance 5%
         * - Vesper Circlet (20966)                     Chance 7%
		 * - Vitality full (20000)                      Chance 3%
         * Bad Reward List:
         * - give 5000 karma                            Chance 15%
         * - get 150k Adena                             Chance 3%
         * - Death Penalty - (skill 5076 lvl 15)        Chance 7%
         * - set Vitality to 0                          Chance 25%
         */
        
        private static final int GRACIAN_COIN = 13693;
        
        public final String onFirstTalk(L2Npc npc, L2PcInstance player)
        {
                QuestState st = player.getQuestState(qn);
                if (st == null)
                {
                        st = newQuestState(player);
                }
                String htmltext = "";
                if (EventsConfig.AP_STARTED)
                {
                        htmltext = start;
                }
                else
                {
                        htmltext = EventsConfig.EVENT_DISABLED;
                }               
                return htmltext;
        }

        public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
        {
                QuestState st = player.getQuestState(qn);
                if (st == null)
                {
                        st = newQuestState(player);
                }
                String htmltext = "";
                if (event.equalsIgnoreCase("play"))
                {
                        if (st.getQuestItemsCount(GRACIAN_COIN) >= 100)
                        {
                                st.takeItems(GRACIAN_COIN,100);
                                htmltext = giveReward(st,player);
                        }
                        else
                        {
                                htmltext = no_items;
                        }
                }
                if (event.endsWith(".htm"))
                {
                        htmltext = event;
                }
        return htmltext;
        }
        private String giveReward(QuestState st, L2PcInstance player) 
        {
                String text = "";
                int randomGet = Rnd.get(100);
                if (randomGet <= 50)
                {                       
                        if (randomGet <= 5)
                        {
                                if (Rnd.get(2) == 2)
                                {
                                        if (st.getQuestItemsCount(20958) < 1)
                                        {
                                                st.giveItems(20958, 1); // - Weapon Box Vesper
                                        }
                                        else
                                        {
                                                st.giveAdena(500000, true);
                                        }
                                }
                                else
                                {
                                        if (st.getQuestItemsCount(17161) < 1)
                                        {
                                                st.giveItems(17161, 1); // - Armour Box Vesper
                                        }
                                        else
                                        {
                                                st.giveAdena(150000, true);
                                        }
                                }
                        }
                        else if (randomGet > 5 && randomGet <= 15) // 10%
                        {
                                if (st.getQuestItemsCount(20099) < 1)
                                {
                                        st.giveItems(20099, 1); // - Formal Dress 7 day
                                }
                                else
                                {
                                        st.giveAdena(500000, true);
                                }
                        }
						else if (randomGet > 15 && randomGet <= 20)  // 5%
                        {
                                if (st.getQuestItemsCount(20967) < 1)
                                {
                                        st.giveItems(20967, 1); // - Noblesse Oblige
                                }
                                else
                                {
                                        st.giveAdena(250000, true);
                                }
                        }
						else if (randomGet > 20 && randomGet <= 25)  // 5%
                        {
                                if (st.getQuestItemsCount(20965) < 1)
                                {
                                        st.giveItems(20965, 1); // - Vesper Crown
                                }
                                else
                                {
                                        st.giveAdena(250000, true);
                                }
                        }
						else if (randomGet > 25 && randomGet <= 32)  // 7%
                        {
                                if (st.getQuestItemsCount(20966) < 1)
                                {
                                        st.giveItems(20966, 1); // - Vesper Circlet
                                }
                                else
                                {
                                        st.giveAdena(250000, true);
                                }
                        }
                        else if (randomGet > 32 && randomGet <=47) // 15%
                        {
                                if(Rnd.get(2) == 2)
                                {
                                        if (st.getQuestItemsCount(14093) < 1)
                                        {
                                            st.giveItems(14093,1); // - Sudden Agathion - 7-day limited period
                                        }
                                        else
                                        {
                                            st.giveAdena(150000, true);
                                        }
                                }
                                else
                                {
                                        if (st.getQuestItemsCount(14094) < 1)
                                        {
                                            st.giveItems(14094,1); // - Shiny Agathion - 7-day limited period
                                        }
                                        else
                                        {
                                            st.giveAdena(150000, true);
                                        }
                                }
                        }
                        else
                        {
                            player.setVitalityPoints(20000, true);
                        }
                        text = good;
                }
                else
                {                       
                        if (randomGet > 50 && randomGet <= 65)
                        {
                                player.setKarma(player.getKarma() + 5000);
                        }
                        else if (randomGet > 65 && randomGet <= 68)
                        {
                                if (st.getQuestItemsCount(57) < 150000)
                                {
                                        if (st.getQuestItemsCount(57) != 0)
                                        {
                                                st.takeItems(57,st.getQuestItemsCount(57));
                                        }
                                }
                                else
                                {
                                        st.takeItems(57,150000);
                                }
                        }
                        else if(randomGet > 68 && randomGet <= 75)
                        {
                                SkillTable.getInstance().getInfo(5076,15).getEffects(player, player);
                        }
                        else
                        {
                                player.setVitalityPoints(0, true);
                        }
                        text = bad;
                }
             return text;
        }
        
        public AprilFools(int questId, String name, String descr)
        {
            super(questId, name, descr);
            addStartNpc(April_Npc);
            addFirstTalkId(April_Npc);
            addTalkId(April_Npc);
        }       
        
    	public static void main(String[] args)
    	{
    		new AprilFools(-1,qn,"events");
    	}
}