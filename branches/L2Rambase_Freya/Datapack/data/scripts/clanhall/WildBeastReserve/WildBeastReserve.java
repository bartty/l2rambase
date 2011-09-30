/**
 * @author L2Rambase Project
*/
package clanhall.WildBeastReserve;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


public class WildBeastReserve extends Quest
{
	private static final String qn = "WildBeastReserve";
	
	//NPCs	
	private static final int DOORMAN_1 = 35625;
	private static final int DOORMAN_2 = 35626;
	
	private static final int DOORMAN_3 = 35623;
	private static final int DOORMAN_4 = 35624;
		
	//private static final int MESSENGER;
	//private static final int CHAMBERLAIN;
	
	//DOORs
	private static final int FIRST_DOOR_1 = 21150003;
	private static final int FIRST_DOOR_2 = 21150004;
	private static final int SECOND_DOOR_1 = 21150002;
	private static final int SECOND_DOOR_2 = 21150001;
	//private static final int FENCE_1;
	//private static final int FENCE_2;

	//Conditions
	public ClanHall clanhall = ClanHallManager.getInstance().getClanHallById(63);
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		
		int npcId = npc.getNpcId();
		if (player.isInSiege())
		{
			return "<html><body>I don't have time for you. Get lost ...</body></html>";
		}
		if (npcId == DOORMAN_1 || npcId == DOORMAN_2)
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
					htmltext += "<br><button action=\"bypass -h Quest "+getName()+" open_door\" value=\"Open a Gate\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+getName()+" close_door\" value=\"Close a Gate\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
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
		if (npcId == DOORMAN_3 || npcId == DOORMAN_4)
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
					htmltext += "<br><button action=\"bypass -h Quest "+getName()+" open_door\" value=\"Open Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
					htmltext += "<br><button action=\"bypass -h Quest "+getName()+" close_door\" value=\"Close Door\" width=80 height=27 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\">";
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
		if (event.equalsIgnoreCase("open_door") && (npcId == DOORMAN_1 || npcId == DOORMAN_2))
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && (npcId == DOORMAN_1 || npcId == DOORMAN_2))
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(FIRST_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(FIRST_DOOR_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}
		else if (event.equalsIgnoreCase("open_door") && (npcId == DOORMAN_3 || npcId == DOORMAN_4))
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(SECOND_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(SECOND_DOOR_2);
			firstdoor.openMe();
			seconddoor.openMe();
		}
		else if (event.equalsIgnoreCase("close_door") && (npcId == DOORMAN_3 || npcId == DOORMAN_4))
		{
			L2DoorInstance firstdoor = DoorTable.getInstance().getDoor(SECOND_DOOR_1);
			L2DoorInstance seconddoor = DoorTable.getInstance().getDoor(SECOND_DOOR_2);
			firstdoor.closeMe();
			seconddoor.closeMe();
		}		
       	return htmltext;
	}
	public WildBeastReserve(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(DOORMAN_1);
		addStartNpc(DOORMAN_2);
		addStartNpc(DOORMAN_3);
		addStartNpc(DOORMAN_4);
		//addStartNpc(MESSENGER);
		//addFirstTalkId(MESSENGER);
		addFirstTalkId(DOORMAN_1);
		addFirstTalkId(DOORMAN_2);
		addFirstTalkId(DOORMAN_3);
		addFirstTalkId(DOORMAN_4);
		//addTalkId(MESSENGER);
		addTalkId(DOORMAN_1);
		addTalkId(DOORMAN_2);
		addTalkId(DOORMAN_3);
		addTalkId(DOORMAN_4);
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new WildBeastReserve(-1,qn,"clanhall");
		_log.warning("WildBeastReserve: Doormans Implementation ... Loaded 4 Doorman's ...");
	}
}