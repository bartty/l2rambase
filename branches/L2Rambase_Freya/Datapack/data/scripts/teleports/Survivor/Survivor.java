package teleports.Survivor;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class Survivor extends Quest
{	 
	private final static int npcId = 32632;
	private final static String qn = "1003_Survivor";

	public Survivor(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(npcId);
		addTalkId(npcId);
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		if (event != null)
		{
			if (player.getLevel() < 75)
			{
				htmltext = "<html><body>Gracia Survivor:<br>";
				htmltext += "I understand. But Gracia is not a place to be taken lightly.";
				htmltext += "Given your inexperience, even if you go there you would only be a liability to the effort.<br>";
				htmltext += "Aden also has many monsters and the need for adventurers to fight them. Build skills here, then go to Gracia.<br>";
				htmltext += "(It is recommended that only characters who are level 75 or higher travel to Gracia.)";
				htmltext += "</body></html>";
				return htmltext;
			}
			if (st.getQuestItemsCount(57) >= 150000)
			{
				st.takeItems(57,150000);
				player.teleToLocation(-149406, 255247, -80);
		        htmltext = "";
			}
			else
			{
				htmltext = "<html><body>Gracia Survivor:<br>";
				htmltext += "I understand. But as I told you earlier, if you're short of money,";
				htmltext += "you can still get to Gracia through another route.<br>";
				htmltext += "Visit the gatekeeper in Gludio Castle Village or Talking Island,";
				htmltext += "and they can send you to the Airship Wharf. I hope you will do so. We can use everyone we can!";
				htmltext += "</body></html>";
			}
		}
		return htmltext;
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			return "";
		}
		htmltext = "<html><body>Gracia Survivor:<br>";
		htmltext += "There is a place at the western end of Aden,";
		htmltext += "on the shores of the Sea of Gludio, where airships depart for Gracia.";
		htmltext += "It's a wharf created through ancient Giant technology and the best Aden engineers.<br>";
		htmltext += "Since it floats in mid-air, however, there's no way to go there directly. The only way is to teleport there directly.";
		htmltext += "A gatekeeper from Talking Island or Gludio Castle Village can send you there.<br>";
		htmltext += "We are desperate for help. If you are willing, I can send you up to Gludio Airship Wharf myself.";
		htmltext += "But because some items are consumed, I must charge you something. I feel almost ashamed to ask.<br>";
		htmltext += "I only hope you will see it as a donation to Gracia. If you can't afford it, the Gatekeeper in Gludio Castle Village or Talking Island can also send you there. What would you like to do?<br>";
		htmltext += "<a action=\"bypass -h Quest 1003_Survivor 32632-2.htm\">Move to Gludio Airship Wharf (150,000 Adena).</a>";
		htmltext += "</body></html>";
		return htmltext;
	}

	public static void main(String[] args)
	{
		new Survivor(-1, qn, "teleports");
	}
}