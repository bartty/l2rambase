/**
 * L2Rambase Project 
*/
package clanhall.FortressOfTheDead;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


public class FortressOfTheDead extends Quest
{
	private static final String qn = "FortressOfTheDead";
	
	//NPCs
	private static final int DOORMAN_1 = 35641;
	private static final int DOORMAN_2 = 35642;
	//private static final int MESSENGER;
	//private static final int CHAMBERLAIN;
	
	//Doors
	private static final int FIRST_DOOR_1 = 21170001;
	private static final int FIRST_DOOR_2 = 21170002;
	private static final int SECOND_DOOR_1 = 21170005;
	private static final int SECOND_DOOR_2 = 21170006;
	//Conditions
	public ClanHall clanhall = ClanHallManager.getInstance().getClanHallById(64);
	
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
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" open_door\" value=\"Open a Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" close_door\" value=\"Close a Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
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
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" open_door\" value=\"Open a Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+qn+" close_door\" value=\"Close a Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
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
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && npcId == DOORMAN_1)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}
		else if (event.equalsIgnoreCase("open_door") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(SECOND_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(SECOND_DOOR_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && npcId == DOORMAN_2)
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(SECOND_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(SECOND_DOOR_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}
       	return htmltext;
	}
	public FortressOfTheDead(int questId, String name, String descr)
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
		new FortressOfTheDead(-1,qn,"clanhall");
		_log.warning("FortressOfTheDead: Doormans Implementation ... Loaded 2 Doorman's ...");
	}
}