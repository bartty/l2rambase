package teleports.GrandBossTeleporters;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.GrandBossManager;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2GrandBossInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.zone.type.L2BossZone;
import l2.rambase.util.Rnd;

public class GrandBossTeleporters extends Quest
{
	private static final String qn = "6000_GrandBossTeleporters";
    
	private static final int[] NPCS =
	{
		13001, //Heart of Warding : Teleport into Lair of Antharas
	    31859, //Teleportation Cubic : Teleport out of Lair of Antharas
	    31384, //Gatekeeper of Fire Dragon : Opening some doors
	    31385, //Heart of Volcano : Teleport into Lair of Valakas
	    31540, //Watcher of Valakas Klein : Teleport into Hall of Flames
	    31686, //Gatekeeper of Fire Dragon : Opens doors to Heart of Volcano
	    31687, //Gatekeeper of Fire Dragon : Opens doors to Heart of Volcano
	    31759 //Teleportation Cubic : Teleport out of Lair of Valakas
	};
	
	private Quest antharasAI()
	{
		return QuestManager.getInstance().getQuest("antharas");
	}

	private Quest valakasAI()
	{
		return QuestManager.getInstance().getQuest("valakas");
	}
	
    private static int count = 0;
	
	public GrandBossTeleporters(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		for (int npcId: NPCS)
		{
			addStartNpc(npcId);
			addTalkId(npcId);
		}		
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (event.startsWith("31540"))
		{
			if (st.getQuestItemsCount(7267) > 0)
			{
				st.takeItems(7267,1);
				player.teleToLocation(183813,-115157,-3303);
				st.set("allowEnter","1");
				return htmltext;
			}
			else
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "In order to enter the Hall of Flames, you'll need a Floating Stone.";
				htmltext += "To obtain one, you must do a small favor for me. Agreed?";
				htmltext += "</body></html>";
			}
		}
		return htmltext;
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		int npcId = npc.getNpcId();
		if (npcId == 13001) // #heart of warding
		{
			if (antharasAI() != null)
			{
				int status = GrandBossManager.getInstance().getBossStatus(29019);
				int statusW = GrandBossManager.getInstance().getBossStatus(29066);
				int statusN = GrandBossManager.getInstance().getBossStatus(29067);
				int statusS = GrandBossManager.getInstance().getBossStatus(29068);
				if (status == 2 || statusW == 2 || statusN == 2 || statusS == 2)
				{
					htmltext = "<html><body>A voice whispers in your ear:<br>";
					htmltext += "<font color=LEVEL>Some have already entered the Lair of Antharas."; 
					htmltext += "You may not enter until they are finished.</font></body></html>";
				}
				else if (status == 3 || statusW == 3 || statusN == 3 || statusS == 3)
				{
					htmltext = "<html><body>A voice whispers in your ear:<br>";
					htmltext += "<font color=LEVEL>You may not confront Antharas now. Step back.</font></body></html>";
				}
				else if (status == 0 || status == 1) //If entrance to see Antharas is unlocked (he is Dormant or Waiting)
				{
					if (st.getQuestItemsCount(3865) > 0)
					{
						st.takeItems(3865,1);
						L2BossZone zone = GrandBossManager.getInstance().getZone(179700,113800,-7709);
						if (zone != null)
						{
							zone.allowPlayerEntry(player,30);
						}
						int x = 179700 + Rnd.get(700); 
						int y = 113800 + Rnd.get(2100); 
						player.teleToLocation(x,y,-7709);
						if (status == 0)
						{
							L2GrandBossInstance antharas = GrandBossManager.getInstance().getBoss(29019);
							antharasAI().startQuestTimer("1001", 1000, antharas, null);
							GrandBossManager.getInstance().setBossStatus(29019, 1);	
						}
						return "";
					}
					else
					{
						htmltext = "<html><body>A voice whispers in your ear:<br>";
						htmltext += "<font color=LEVEL>Only those with a Portal Stone may meet Antharas.";
						htmltext += "Step back.</font></body></html>";
					}
				}
			}
		}
		else if (npcId == 31859) //antharas teleport cube
		{
			int x = 79800 + Rnd.get(600);
			int y = 151200 + Rnd.get(1100);
			player.teleToLocation(x,y,-3534);
			return "";
		}
		else if (npcId == 31385) //heart of volcano
		{
			if (valakasAI() != null)
			{
				int status = GrandBossManager.getInstance().getBossStatus(29028);
				if (status == 0 || status == 1) //If entrance to see Valakas is unlocked (he is Dormant or Waiting)
				{
					if (count >= 200)
					{
						htmltext = "<html><body>[Whispering]<br>";
						htmltext += "<font color=LEVEL>Too many have entered, I can't let you go in.</font></body></html>";
					}
					else if (st.getInt("allowEnter") == 1)
					{
						st.unset("allowEnter");
						L2BossZone zone = GrandBossManager.getInstance().getZone(212852,-114842,-1632);
						if (zone != null)
						{
							zone.allowPlayerEntry(player,30);
						}
						int x = 204328 + Rnd.get(600);
						int y = -111874 + Rnd.get(600);
						player.teleToLocation(x,y,70);
						count = count+1;
						if (status == 0)
						{
							L2GrandBossInstance valakas = GrandBossManager.getInstance().getBoss(29028);
							valakasAI().startQuestTimer("1001",Config.Valakas_Wait_Time, valakas, null);
							GrandBossManager.getInstance().setBossStatus(29028,1);
						}
						return "";
					}
					else //player cheated, wasn't ported via npc Klein
					{
						htmltext = "31385-04.htm";
					}
				}
				else if (status == 2)
				{
					htmltext = "<html><body>[Whispering]<br>";
					htmltext += "<font color=LEVEL>The nest is full, you must wait.</font></body></html>";
				}
				else
				{
					htmltext = "<html><body>[Whispering]<br>";
					htmltext += "<font color=LEVEL>You can't confront Valakas yet. Away with you!</font>";
					htmltext += "</body></html>";
				}
			}
			else
			{
				htmltext = "<html><body>[Whispering]<br>";
				htmltext += "<font color=LEVEL>You can't confront Valakas yet. Away with you!</font>";
				htmltext += "</body></html>";
			}
		}
		else if (npcId == 31384) //Gatekeeper of Fire Dragon
		{
			DoorTable.getInstance().getDoor(24210004).openMe();
			return "";
		}
		else if (npcId == 31686) //Gatekeeper of Fire Dragon
		{
			DoorTable.getInstance().getDoor(24210006).openMe();
			return "";
		}
		else if (npcId == 31687) //Gatekeeper of Fire Dragon
		{
			DoorTable.getInstance().getDoor(24210005).openMe();
			return "";
		}
		else if (npcId == 31540) //Watcher of Valakas Klein
		{
			if (count < 50)
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "Until now, less than 50 people have attempted the Hall of Flames. Are you sure you want to enter it?<br>";
				htmltext += "<a action=\"bypass -h Quest 6000_GrandBossTeleporters 31540\">Enter</a>";
				htmltext += "</body></html>";
			}
			else if (count >= 50 && count < 100)
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "So far, between 50 and 100 people have entered the Hall of Flames. Do you wish to enter?<br>";
				htmltext += "<a action=\"bypass -h Quest 6000_GrandBossTeleporters 31540\">Enter</a>";
				htmltext += "</body></html>";
			}			
			else if (count >= 100 && count < 150)
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "So far, between 100 and 150 people have entered the Hall of Flames. Do you wish to enter?<br>";
				htmltext += "<a action=\"bypass -h Quest 6000_GrandBossTeleporters 31540\">Enter</a>";
				htmltext += "</body></html>";
			}
			else if (count >= 150 && count < 200)
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "So far, between 150 and 200 people have entered the Hall of Flames. Do you wish to enter?<br>";
				htmltext += "<a action=\"bypass -h Quest 6000_GrandBossTeleporters 31540\">Enter</a>";
				htmltext += "</body></html>";
			}
			else
			{
				htmltext = "<html><body>Watcher of Valakas Klein:<br>";
				htmltext += "So far, over 200 people have entered the Hall of Flames. Do you wish to enter?<br>";
				htmltext += "<a action=\"bypass -h Quest 6000_GrandBossTeleporters 31540\">Enter</a>";
				htmltext += "</body></html>";
			}
		}
		else if (npcId == 31759) //valakas teleport cube
		{
			int x = 150037 + Rnd.get(500);
			int y = -57720 + Rnd.get(500);
			player.teleToLocation(x,y,-2976);
			return "";
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new GrandBossTeleporters(-1, qn, "teleports");
	}
}