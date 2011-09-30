/**
 * Friend Manager
 * 
 * Information:
 * Manager for friend quests
 */
package services.FriendManager;

import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;


public class FriendManager extends Quest
{
	private static final String qn = "FriendManager";	
	
	private static final int[] MONSTERS_ID =
	{
		7000,7003,7006,7009,7012,7015,7018,7021,
		7001,7004,7007,7010,7013,7016,7019,7022,
		7002,7005,7008,7011,7014,7017,7020,7023
	};
	
	/**
	 * List of Quests
	 * @param QuestName
	 */
	public static final String[][] QUEST_LIST =
	{
		{"",	""},                    										
		{"Help My Friend",		"250  Friend Points"}, 							
		{"Looking for Friend",	"500  Friend Points"},			
		{"Find emotion",		"1000 Friend Points"},				
		{"I lost friend",		"1500 Friend Points"}
			
	};
	private static final int FRIEND_RECOMENDATION_PROOF = 15279;
	private static final String[] KILL_COUNT =
	{
		"",
		"500",
		"1000",
		"1500",
		"2000"
	};
	
	/**
	 * Html texts of Npc
	 * @param String 
	 */
	private static final String start = "<html><body><font color=LEVEL>Friend Manager</font><br>Oh i don't have friends here now. I see you have friends so you can help your friends and gain friend points for your friend. For gain friend points you must kill monsters from mission dungeon.<br><a action=\"bypass -h Quest FriendManager select\">Select special task</a><br><a action=\"bypass -h Quest FriendManager cancel\">Cancel last job</a><br><a action=\"bypass -h Quest FriendManager reward\">Exchange Friend Points</a></body></html>";
	private static final String not_friends = "<html><body><font color=LEVEL>Friend Manager</font><br>I'm looking for friends. You don't have friend so i cannot be your friend.</body></html>";
	private static final String q_cancel = "<html><body><font color=LEVEL>Friend Manager</font><br>So you are bad friend and don't want help others? Ok so i cancel your task.</body></html>";

	private final static String getQuestList(String[][] list)
	{
		String text = "";
		for (int i = 0; i<list.length;i++)
		{
			if (list[i][0] != "")
			{
				text += "<font color=LEVEL>Quest Reward:" + list[i][1] + "</font><br>";
				text += "<a action=\"bypass -h Quest FriendManager " + i + "_start\">Quest: " + list[i][0] + "</a><br1>";
			}
		}
		return text;
	}
	
	/**
	 * Friend Npc Manager
	 * @param npc
	 */
	private static final int FRIEND_NPC = 7108;

	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
			st.setState(State.STARTED);
		}
		String htmltext = "";
		htmltext = start;
		if (player.getFriendList().isEmpty())
		{
			player.sendMessage("Look for friends first.");
			htmltext = not_friends;
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
		if (event.equalsIgnoreCase("fast_finish"))
		{
			st.set("questFinished", "1");
			st.set("killcount", "0");
		}
		if (event.startsWith("1_"))
		{
			htmltext = selectFriendQuest(player,1,event);
		}
		else if (event.startsWith("2_"))
		{
			htmltext = selectFriendQuest(player,2,event);
		}
		else if (event.startsWith("3_"))
		{
			htmltext = selectFriendQuest(player,3,event);	
		}
		else if (event.startsWith("4_"))
		{
			htmltext = selectFriendQuest(player,4,event);	
		}		
		else if (event.equalsIgnoreCase("cancel"))
		{			
			if (st.get("questStarted") == "1")
			{
				st.set("questStarted","0");
				st.set("questFinished","0");
				st.set("questNumber", "0");
				st.set("killcount","0");
			}
			htmltext = q_cancel;
		}
		else if (event.equalsIgnoreCase("select"))
		{
			int listFriendNumber = 0;			
			try
			{
				listFriendNumber = Integer.valueOf(st.get("questNumber"));
			}
			catch (Exception e)
			{
				st.set("questNumber","0");
			}			
			if (listFriendNumber != 0)
			{								
				htmltext = listOfFriendQuests(listFriendNumber);	
			}			
			else
			{
				htmltext = listOfFriendQuests(0);
			}
		}
		else if (event.startsWith("r "))
		{
			String friendName = event.substring(2);			
			L2PcInstance friend = L2World.getInstance().getPlayer(friendName);
			if (friend == null)
			{
				htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>You finished task and now select your friend from list.<br>";
				htmltext += "<center><edit var=\"name\" width=260 height=15></center>";
				htmltext += "<a action=\"bypass -h Quest FriendManager r $name\">Select that friend</a>";
				htmltext += "</body></html>";
				player.sendMessage("That player is not online");
			}
			else if (player.getFriendList().contains(friend.getObjectId()))
			{
				QuestState fst = friend.getQuestState(qn);
				if (fst == null)
				{
					fst = newQuestState(friend);
				}
				htmltext = rewardFriend(player,friend,fst,st);
			}
			else
			{
				htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>You finished task and now select your friend from list.<br>";
				htmltext += "<center><edit var=\"name\" width=260 height=15></center>";
				htmltext += "<a action=\"bypass -h Quest FriendManager r $name\">Select that friend</a>";
				htmltext += "</body></html>";
				player.sendMessage("That is not your friend or name don't exist");
			}
		}
		else if (event.equalsIgnoreCase("reward"))
		{
			long count = 0; 
			try
			{
				int countP = Integer.valueOf(st.get("friendPoints"));
				count = countP;
			}
			catch(Exception e)
			{				
			}
			if (count != 0)
			{				
				st.giveItems(FRIEND_RECOMENDATION_PROOF, count);
				st.set("friendPoints", "0");
			}
			htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>";
			htmltext += "If you have Friend Recomendation Proof you can buy special time limited items.<br>";
			htmltext += "<a action=\"bypass -h Quest FriendManager jewelery\">Jewelery</a><br>";
			htmltext += "<a action=\"bypass -h Quest FriendManager armors\">Armors</a><br>";
			htmltext += "<a action=\"bypass -h Quest FriendManager weapons\">Weapons</a><br>";
			htmltext += "<a action=\"bypass -h Quest FriendManager shields\">Shields</a><br>";
			htmltext += "<a action=\"bypass -h Quest FriendManager etc\">Others</a></body></html>";
		}
		else if (event.equalsIgnoreCase("armors"))
		{
			L2Multisell.getInstance().separateAndSend(7108002, player, npc.getNpcId(), false, 0.0);
		}
		else if (event.equalsIgnoreCase("weapons"))
		{
			L2Multisell.getInstance().separateAndSend(7108003, player, npc.getNpcId(), false, 0.0);
		}
		else if (event.equalsIgnoreCase("jewelery"))
		{
			L2Multisell.getInstance().separateAndSend(7108001, player, npc.getNpcId(), false, 0.0);
		}
		else if (event.equalsIgnoreCase("shields"))
		{
			L2Multisell.getInstance().separateAndSend(7108004, player, npc.getNpcId(), false, 0.0);
		}
		else if (event.equalsIgnoreCase("etc"))
		{
			L2Multisell.getInstance().separateAndSend(7108005, player, npc.getNpcId(), false, 0.0);
		}
		return htmltext;
	}
	
	private String rewardFriend(L2PcInstance player,L2PcInstance friend, QuestState fst,QuestState st)
	{
		int friendPoints = 0;;
		int friendPointsReward = 0;
		int questNumber = Integer.valueOf(st.get("questNumber"));
		switch (questNumber)
		{
			case 1:
				friendPointsReward = 250;
				break;
			case 2:
				friendPointsReward = 500;
				break;
			case 3:
				friendPointsReward = 1000;
				break;
			case 4:
				friendPointsReward = 1500;
				break;
			default:
				break;			
		}
		try
		{
			friendPoints = Integer.valueOf(fst.get("friendPoints"));
			fst.set("friendPoints", String.valueOf(friendPoints+friendPointsReward));
			friend.sendMessage("You gained "+ fst.get("friendPoints")+" friend points from "+player.getName()+".");
		}
		catch(Exception e)
		{
			fst.set("friendPoints", String.valueOf(friendPointsReward));
			friend.sendMessage("You gained "+ fst.get("friendPoints")+" friend points from "+player.getName()+".");			
		}
		st.set("questStarted","0");
		st.set("questFinished","0");
		st.set("questNumber", "0");
		st.set("killcount","0");
		return "<html><body><font color=LEVEL>Friend Manager</font><br>You are good friend, so your friend get a friend points.</body></html>";
	}
	
	private String listOfFriendQuests(int listNumber) 
	{
		String value = "";
		if (listNumber == 0)
		{
			value = "<html><body><font color=LEVEL>Friend Manager</font><br>You can gain friend points for your friend in:<br>" + getQuestList(QUEST_LIST) + "</body></html>";
		}
		else
		{
			value = "<html><body><font color=LEVEL>Friend Manager</font><br>You can gain friend points for your friend in:<br><a action=\"bypass -h Quest FriendManager " + String.valueOf(listNumber) + "_start\">" + QUEST_LIST[listNumber][0] + "</a><br1>";
		}
		return value;
	}

	private String selectFriendQuest(L2PcInstance player, int i, String event) 
	{
		QuestState st = player.getQuestState(qn);
		String htmltext = "";
		if (event.equalsIgnoreCase(String.valueOf(i)+"_start"))
		{
			boolean eventFinished = false;
			try
			{
				int finished = Integer.valueOf(st.get("questFinished"));						
				if (finished > 0)
				{
					eventFinished = true;
				}
			}
			catch(Exception e)
			{				
				eventFinished = false;
				st.set("questFinished", "0");
			}
			boolean eventStarted = false;
			try
			{
				int started = Integer.valueOf(st.get("questStarted"));						
				if (started > 0)
				{
					eventStarted = true;
				}						
			}
			catch (Exception e)
			{					
				eventStarted = false;
				st.set("questStarted", "0");
			}
			if (!eventStarted)
			{				
				st.set("questNumber",String.valueOf(i));
				//start quest procedure
				st.set("questStarted", "1");
				htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>To gain "+QUEST_LIST[i][1]+" you must kill "+KILL_COUNT[i]+" monsters in Mission Dungeon.</body></html>";				
			}
			else
			{
				//check finish quest
				if (!eventFinished)
				{
					htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>You don't kill "+KILL_COUNT[i]+" monsters in Mission Dungeon.</body></html>";
				}
				else
				{					
					htmltext = "<html><body><font color=LEVEL>Friend Manager</font><br>You finished task and now select your friend from list.<br>";
					htmltext += "<center><edit var=\"name\" width=260 height=15></center>";
					htmltext += "<a action=\"bypass -h Quest FriendManager r $name\">Select that friend</a>";
					htmltext += "</body></html>";
				}				
			}
		}		
		return htmltext;
	}


	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int questNumber = 0; 
		try
		{
			questNumber = Integer.valueOf(st.get("questNumber"));
		}
		catch (Exception e)
		{
			return super.onKill(npc, player, isPet);
		}
		if (questNumber >0)
		{
			if (Integer.valueOf(st.get("questFinished")) == 0 && Integer.valueOf(st.get("questStarted")) == 1)
			{
				int killcount = 0;
				try
				{
					killcount = Integer.valueOf(st.get("killcount"));
					killcount = killcount + 1;
					st.set("killcount",String.valueOf(killcount));
					player.sendMessage("You killed "+ st.get("killcount")+" of "+KILL_COUNT[questNumber]+" monsters.");
				}
				catch (Exception e)
				{
					st.set("killcount","1");
					player.sendMessage("You killed 1 of "+KILL_COUNT[questNumber]+" monsters.");
				}
				if (Integer.valueOf(st.get("killcount")) >= Integer.valueOf(KILL_COUNT[questNumber]))
				{
					st.set("questFinished", "1");
					st.set("killcount","0");
					player.sendMessage("You finished your task go back for get reward.");
				}
			}
		}
		return super.onKill(npc, player, isPet);
	}

	public FriendManager(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(FRIEND_NPC);
		addFirstTalkId(FRIEND_NPC);
		addTalkId(FRIEND_NPC);
		for (int npcId: MONSTERS_ID)
		{
			addKillId(npcId);
		}
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new FriendManager(-1,qn,"Friend Manager");
		_log.warning("FriendPoints System: 4 Friends Task Installed ...");
	}
}