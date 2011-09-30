package custom.RaidbossInfo;

import l2.rambase.gameserver.instancemanager.RaidBossSpawnManager;
import l2.rambase.gameserver.instancemanager.RaidBossSpawnManager.StatusEnum;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.ShowMiniMap;


/**
 * 
 * @author L2Rambase Project
 *
 */
public class RaidbossInfo extends Quest
{
	private static final String qn = "8000_RaidbossInfo";
	
	private static int locX = 0;
	private static int locY = 0;
	private static int locZ = 0;

	private static final int[] NPCS	= { 
		31729,31730,31731,31732,31733,31734,
		31735,31736,31737,31738,31775,31776,
		31777,31778,31779,31780,31781,31782,
		31783,31784,31785,31786,31787,31788,
		31789,31790,31791,31792,31793,31794,
		31795,31796,31797,31798,31799,31800,
		31801,31802,31803,31804,31805,31806,
		31807,31808,31809,31810,31811,31812,
		31813,31814,31815,31816,31817,31818,
		31819,31820,31821,31822,31823,31824,
		31825,31826,31827,31828,31829,31830,
		31831,31832,31833,31834,31835,31836,
		31837,31838,31839,31840,31841,32337,
		32338,32339,32340
	};
			
	public RaidbossInfo(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int i : NPCS)
		{
			addStartNpc(i);
			addTalkId(i);
		}
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("level20.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel < 30)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel < 30)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level30.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 30 && raidLevel < 40)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 30 && raidLevel < 40)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level40.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 40 && raidLevel < 50)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 40 && raidLevel < 50)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level50.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 50 && raidLevel < 60)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 50 && raidLevel < 60)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level60.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 60 && raidLevel < 70)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 60 && raidLevel < 70)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level70.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 70 && raidLevel < 80)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 70 && raidLevel < 80)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}				
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("level80.htm"))
		{
			htmltext = "<html><body>Raid Monster Information:<br>";
			for (int i = 25001; i < 25695; i++)
			{
				try
				{
					int raidLevel = RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel();
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(i) == StatusEnum.DEAD)
					{
						if (raidLevel >= 80)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+ " <font color=FF0000>" + RaidBossSpawnManager.getInstance().getBosses().get(i).getName()+"</font><br1>";
						}
					}
					else
					{
						if (raidLevel >= 80)
						{
							htmltext += "<font color=LEVEL>[LvL " + String.valueOf(RaidBossSpawnManager.getInstance().getBosses().get(i).getLevel()) +"]</font> "+" <a action=\"bypass -h Quest 8000_RaidbossInfo " + String.valueOf(i);
							htmltext += "\">"+ RaidBossSpawnManager.getInstance().getBosses().get(i).getName() + "</a><br1>";
						}
					}
				}
				catch (Exception e)
				{				
				}
			}
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("radar"))
		{
			player.getRadar().removeAllMarkers();
			player.getRadar().addMarker(locX, locY, locZ);
		}
		else
		{			
			try
			{
				int bossId = Integer.valueOf(event);
				locX = RaidBossSpawnManager.getInstance().getBosses().get(bossId).getSpawn().getLocx();
				locY = RaidBossSpawnManager.getInstance().getBosses().get(bossId).getSpawn().getLocy();
				locZ = RaidBossSpawnManager.getInstance().getBosses().get(bossId).getSpawn().getLocz();
				player.sendPacket(new ShowMiniMap(1665));
				startQuestTimer("radar", 500, npc, player);
				htmltext = "";
			}
			catch (Exception e)
			{
				htmltext = "";
				player.sendMessage("Failed to find raidboss spawn location.");
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
			newQuestState(player);
		}
		htmltext = "<html><body>Raid Monster Information:<br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level20.htm\">Raid Monsters [20-29]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level30.htm\">Raid Monsters [30-39]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level40.htm\">Raid Monsters [40-49]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level50.htm\">Raid Monsters [50-59]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level60.htm\">Raid Monsters [60-69]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level70.htm\">Raid Monsters [70-79]</a><br>";
		htmltext += "<a action=\"bypass -h Quest 8000_RaidbossInfo level80.htm\">Raid Monsters [80+]</a>";
		htmltext += "</body></html>";
		return htmltext;
	}

	public static void main(String[] args)
	{
		new RaidbossInfo(-1, qn, "custom");
	}
}