/**
 * @author L2Rambase Project 
*/
package clanhall.RainbowSpringsChateau;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


public class RainbowSpringsChateau extends Quest
{
	private static final String qn = "RainbowSpringsChateau";
	
	//NPCs	
	private static final int DOORMAN_1 = 35602;
	private static final int DOORMAN_2 = 35601;
		
	//private static final int MESSENGER;
	//private static final int CHAMBERLAIN;
	
	//DOORs
	private static final int GATE_1 = 24140007;
	private static final int GATE_2 = 24140008;
	private static final int FIRST_DOOR_1 = 24140003;
	private static final int FIRST_DOOR_2 = 24140004;
	private static final int OTHER_DOOR_1 = 24140001;
	private static final int OTHER_DOOR_2 = 24140002;
	private static final int OTHER_DOOR_3 = 24140005;
	private static final int OTHER_DOOR_4 = 24140006;
	//Conditions
	public ClanHall clanhall = ClanHallManager.getInstance().getClanHallById(62);
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		String htmltext = "";
		
		int npcId = npc.getNpcId();
		if (player.isInSiege())
		{
			return "<html><body>I don't have time for you. Get lost ...</body></html>";
		}
		if (npcId == DOORMAN_1)
		{
			if (player.getClan() != null)
			{
				if (clanhall.getOwnerId() == player.getClanId())
				{
					if ((player.getClanPrivileges() & L2Clan.CP_CH_OPEN_DOOR) != L2Clan.CP_CH_OPEN_DOOR)
					{
						return "<html><body>You don't have access to open door's, go to your clan leader</body></html>";			
					}
					htmltext = "<html><body>";
					htmltext += "Oh, how are you?<br>";
					htmltext += "It is an honor to serve you.<br>";
					htmltext += "What may I do for you?<br>";
					htmltext += "<center>";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" open_door\" value=\"Open a Gate\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" close_door\" value=\"Close a Gate\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "</center>";
					htmltext += "</body></html>";
				}
				else
				{
					htmltext = "<html><body>I don't have time for you. Get lost ...</body></html>";
				}
			}
			else
			{
				htmltext = "<html><body>I don't have time for you. Get lost ...</body></html>";
			}
		}	
		if (npcId == DOORMAN_2)
		{
			if (player.getClan() != null)
			{
				if (clanhall.getOwnerId() == player.getClanId())
				{
					if ((player.getClanPrivileges() & L2Clan.CP_CH_OPEN_DOOR) != L2Clan.CP_CH_OPEN_DOOR)
					{
						return "<html><body>You don't have access to open door's, go to your clan leader</body></html>";			
					}
					htmltext = "<html><body>";
					htmltext += "Oh, how are you?<br>";
					htmltext += "It is an honor to serve you.<br>";
					htmltext += "What may I do for you?<br>";
					htmltext += "<center>";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" open_door\" value=\"Open Main Door\" width=120 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" close_door\" value=\"Close Main Door\" width=120 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" open_inner\" value=\"Open Inner Doors\" width=120 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" close_inner\" value=\"Close Inner Doors\" width=120 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "</center>";
					htmltext += "</body></html>";
				}
				else
				{
					htmltext = "<html><body>I don't have time for you. Get lost ...</body></html>";
				}
			}
			else
			{
				htmltext = "<html><body>I don't have time for you. Get lost ...</body></html>";
			}
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
		int npcId = npc.getNpcId();
		if (event.equalsIgnoreCase("open_door") && npcId == DOORMAN_1)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(GATE_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(GATE_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && npcId == DOORMAN_1)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(GATE_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(GATE_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}
		else if (event.equalsIgnoreCase("open_door") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}
		else if (event.equalsIgnoreCase("open_inner") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(OTHER_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(OTHER_DOOR_2);
			L2DoorInstance thirddoor = DoorTable.getInstance().getDoor(OTHER_DOOR_3);
			L2DoorInstance fourdoor = DoorTable.getInstance().getDoor(OTHER_DOOR_4);
			firstdoor.openMe();
			seconddoor.openMe();
			thirddoor.openMe();
			fourdoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_inner") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(OTHER_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(OTHER_DOOR_2);
			L2DoorInstance thirddoor = DoorTable.getInstance().getDoor(OTHER_DOOR_3);
			L2DoorInstance fourdoor = DoorTable.getInstance().getDoor(OTHER_DOOR_4);
			firstdoor.closeMe();
			seconddoor.closeMe();
			thirddoor.closeMe();
			fourdoor.closeMe();
		}		
       	return htmltext;
	}
	public RainbowSpringsChateau(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(DOORMAN_1);
		addStartNpc(DOORMAN_2);
		//addStartNpc(MESSENGER);
		//addFirstTalkId(MESSENGER);
		addFirstTalkId(DOORMAN_1);
		addFirstTalkId(DOORMAN_2);
		//addTalkId(MESSENGER);
		addTalkId(DOORMAN_1);
		addTalkId(DOORMAN_2);
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new RainbowSpringsChateau(-1,qn,"clanhall");
		_log.warning("RainbowSpringsChateau: Doormans Implementation ... Loaded 2 Doorman's ...");
	}
}