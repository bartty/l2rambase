package custom.PurchaseBracelet;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class PurchaseBracelet extends Quest
{
	private static final int Angel_Bracelet = 10320;
	private static final int Devil_Bracelet = 10326;
	
	private static final String qn = "8004_PurchaseBracelet";

	public PurchaseBracelet(int id, String name, String descr)
	{
		super(id, name, descr);
		addStartNpc(30098);
		addTalkId(30098);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		htmltext = "<html><body>Trader Alexandria:<br>";
		htmltext += "Ah, you have excellent taste! This bracelet is truly unique. In fact, it is said that it contains an <font color=LEVEL>Agathion</font>. Quite a find, eh? Wondering if it for sale? Of course it is -- that's why I'm here!<br>";
		htmltext += "Both this <font color=LEVEL>Little Angel Agathion Bracelet</font> and this <font color=LEVEL>Little Devil Agathion Bracelet</font> were discovered by another group of adventurers while exploring some ancient ruins. I am positive that they contain Agathions! Of course, no one knows for sure the power an Agathion possesses or how long it lasts. I have been reluctant to experiment on them because it's clear that some of the bracelets possess only limited magic power.<br>";
		htmltext += "Other have more, however. What you get will depend on your luck, I'm afraid.<br>";
		htmltext += "If you wish to buy either the Little Angel bracelet or the Little Devil Bracelet, bring me <font color=LEVEL>25 Big Red Nimble Fish, 50 Great Codrans, 4 Messengers' Skeletons in Crystal of Life, 5 Earth Eggs and 5 Nonliving Nuclei, 3 Dragon Hearts</font>, and <font color=LEVEL>7,500,000 Adena</font>. I wish I could part with them in exchange for crystals as normally would, but the adventurers who delivered the bracelets here insisted on these other items for payment. Even so, I still believe this is a reasonable price. After all, an Agathion isn't something you run across every day, eh? So, would you like to buy one?<br>";
		htmltext += "<a action=\"bypass -h Quest 8004_PurchaseBracelet Little_Angel\">Purchase the Little Angel Agathion Bracelet.</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8004_PurchaseBracelet Little_Devil\">Purchace the Little Devil Agathion Bracelet.</a>";
		htmltext += "</body></html>";
		return htmltext;
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		if (st.getQuestItemsCount(6471) >= 20 && st.getQuestItemsCount(5094) >= 50 && st.getQuestItemsCount(9814) >= 4 && st.getQuestItemsCount(9816) >= 5 && st.getQuestItemsCount(9817) >= 5 && st.getQuestItemsCount(9815) >= 3 && st.getQuestItemsCount(57) >= 7500000)
		{
	        st.takeItems(6471,25);
	        st.takeItems(5094,50);
	        st.takeItems(9814,4);
	        st.takeItems(9816,5);
	        st.takeItems(9817,5);
	        st.takeItems(9815,3);
	        st.takeItems(57,7500000);
	        htmltext = "";
	        if (event.equalsIgnoreCase("Little_Devil"))
	        {
	        	st.giveItems(Devil_Bracelet,1);
	        }
	        else if (event.equalsIgnoreCase("Little_Angel"))
	        {
	           st.giveItems(Angel_Bracelet,1);
	        }
		}
	    else
	    {
	        htmltext = "<html><body>Trader Alexandria:<br>";
	        htmltext += "Oh, you don't have the items required to buy either the Angel's Bracelet or the Devil's Bracelet. In order to obtain one, you must first bring me <font color=LEVEL>20 Big Red Nimble Fish</font>, <font color=LEVEL>50 Great Codrans</font>, and <font color=LEVEL>4 Memento Moris, 5 Earth Eggs, 5 Nonliving Nuclei, 3 Dragon Hearts</font>, and <font color=LEVEL>7,500,000 Adena</font>.";
	        htmltext += "</body></html>";
	    }
	    st.exitQuest(true);
		return htmltext;
	}

	public static void main(String[] args)
	{
		new PurchaseBracelet(-1, qn, "custom");
	}
}