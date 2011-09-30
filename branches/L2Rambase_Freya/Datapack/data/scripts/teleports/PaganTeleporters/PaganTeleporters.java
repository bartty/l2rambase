package teleports.PaganTeleporters;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class PaganTeleporters extends Quest
{
	private final static String qn = "1630_PaganTeleporters";
	
	private static final int[] npcs = 
	{
		32034,32035,32036,32037,32039,32040
	};
	
	public PaganTeleporters(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npc: npcs)
		{
		    addStartNpc(npc);
		    addTalkId(npc);
		}
		addFirstTalkId(32039);
		addFirstTalkId(32040);
	}
	
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (event == "Close_Door1")
		{
			DoorTable.getInstance().getDoor(19160001).closeMe();
		}
		else if (event == "Close_Door2")
		{
			DoorTable.getInstance().getDoor(19160010).closeMe();
			DoorTable.getInstance().getDoor(19160011).closeMe();
		}
		return "";
	}
	
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		int npcId = npc.getNpcId();
	    if (npcId == 32039)
	    {
	    	player.teleToLocation(-12766,-35840,-10856);
	    }
	    else if (npcId == 32040)
	    {
	    	player.teleToLocation(36640,-51218,718);
	    }
		return "";
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		int npcId = npc.getNpcId();
	    if (npcId == 32034)
	    {
	    	if (st.getQuestItemsCount(8064) == 0 && st.getQuestItemsCount(8065) == 0 && st.getQuestItemsCount(8067) == 0)
	    	{
	    		return "<html><body>The Temple Gatekeeper:<br>You have nothing that would cover the holes.<br>(You must have a Visitor's Mark, a Faded Visitor's Mark, or a Pagan's Mark in order to open this door.)</body></html>";
	    	}
	    	htmltext = "<html><body>The Temple Gatekeeper:<br>";
	    	htmltext += "The moment the mark touches the hole, the door swings open with a mechanical whir.";
	    	htmltext += "</body></html>";
	    	DoorTable.getInstance().getDoor(19160001).openMe();
	    	startQuestTimer("Close_Door1",10000,npc,player);
	    }
	    else if (npcId == 32035)
	    {
	    	if (st.getQuestItemsCount(8064) == 0 && st.getQuestItemsCount(8065) == 0 && st.getQuestItemsCount(8067) == 0)
	    	{
	    		return "<html><body>The Temple Gatekeeper:<br>You have nothing that would cover the holes.<br>(You must have a Visitor's Mark, a Faded Visitor's Mark, or a Pagan's Mark in order to open this door.)</body></html>";
	    	}
	    	DoorTable.getInstance().getDoor(19160001).openMe();
	    	startQuestTimer("Close_Door1",10000,npc,player);
	    	htmltext = "<html><body>The Temple Gatekeeper:<br>";
	    	htmltext += "The moment the mark touches the hole, the door swings open with a mechanical whir.";
	    	htmltext += "</body></html>";
	    }
	    else if (npcId == 32036)
	    {
	    	if (st.getQuestItemsCount(8067) == 0)
	    	{
	    		htmltext = "<html><body>The Temple Gatekeeper:<br>Show your Mark or be gone from my sight!<br>Only those who possess the Pagan's Mark may pass through this gate!</body></html>";
	    	}
	    	else
	    	{
	    		htmltext = "<html><body>The Temple Gatekeeper:<br>On seeing the Pagan's Mark, the statue's probing eyes go blank.<br>With the quiet whir of an engine, the gate swings open...</body></html>";
	    		startQuestTimer("Close_Door2",15000,npc,player);
	    		DoorTable.getInstance().getDoor(19160010).openMe();
	    		DoorTable.getInstance().getDoor(19160011).openMe();
	    	}
	    }
	    else if (npcId == 32037)
	    {
	    	if (st.getQuestItemsCount(8067) == 0)
	    	{
	    		htmltext = "<html><body>The Temple Gatekeeper:<br>Show your Mark or be gone from my sight!<br>Only those who possess the Pagan's Mark may pass through this gate!</body></html>";
	    	}
	    	else
	    	{
	    		DoorTable.getInstance().getDoor(19160010).openMe();
	    		DoorTable.getInstance().getDoor(19160011).openMe();
	    		startQuestTimer("Close_Door2",15000,npc,player);
	    		htmltext = "<html><body>The Temple Gatekeeper:<br>";
	    		htmltext += "The moment the mark touches the hole, the door swings open with a mechanical whir.";
	    		htmltext += "</body></html>";
	    	}
	    }
	    st.exitQuest(true);
		return htmltext;
	}

	public static void main(String[] args)
	{
		new PaganTeleporters(-1, qn, "teleports");
	}
}