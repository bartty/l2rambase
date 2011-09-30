package teleports.TeleportToRaceTrack;

import javolution.util.FastMap;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.NpcSay;

public class TeleportToRaceTrack extends Quest
{
	private static final String qn = "1101_teleport_to_race_track";
	
	private FastMap<Integer,CurrentLocation> teleportLocs = new FastMap<Integer,CurrentLocation>();
	
	private class CurrentLocation
	{
		private int _x;
		private int _y;
		private int _z;
		
		private CurrentLocation(int x,int y,int z)
		{
			_x=x;
			_y=y;
			_z=z;
		}
		
		private int getZ()
		{
			return _z;
		}
		
		private int getY()
		{
			return _y;
		}
		
		private int getX()
		{
			return _x;
		}
	}
	
	private static final int RACE_MANAGER = 30995;
	private static final int[] TELEPORTERS =
	{
		30059,30080,30177,30233,30256,
		30320,30848,30899,31320,31275,
	    31964,31210
	};	

	public TeleportToRaceTrack(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npcId: TELEPORTERS)
		{
			addStartNpc(npcId);
			addTalkId(npcId);
		}
		addStartNpc(RACE_MANAGER);
		addTalkId(RACE_MANAGER);
	}
	
	private void saveLoc(L2PcInstance player)
	{
		if (teleportLocs.get(player.getObjectId()) != null)
		{
			CurrentLocation _loc = null;
			_loc = new CurrentLocation(player.getX(),player.getY(),player.getZ());
			teleportLocs.put(player.getObjectId(),_loc);
			player.teleToLocation(12661,181687,-3560);
		}
		else
		{
			teleportLocs.remove(player.getObjectId());
			CurrentLocation _currentLocation = new CurrentLocation(player.getX(),player.getY(),player.getZ());
			teleportLocs.put(player.getObjectId(),_currentLocation);
			player.teleToLocation(12661,181687,-3560);
		}
	}
	
	private void teleportBack(L2Npc npc,L2PcInstance player)
	{
		if (teleportLocs.get(player.getObjectId()) != null)
		{
			CurrentLocation _loc = null;
			_loc = teleportLocs.get(player.getObjectId());
			player.teleToLocation(_loc.getX(),_loc.getY(),_loc.getZ());
		}
		else
		{
			player.sendPacket(new NpcSay(npc.getObjectId(),0,npc.getNpcId(),"You've arrived here from a different way. I'll send you to Rune Township which is the nearest town."));
	        player.teleToLocation(15670,142983,-2700);
		}
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		for (int npcId: TELEPORTERS)
		{		     
			if (npc.getNpcId() == npcId)
			{
				saveLoc(player);
			}
		}
		if (npc.getNpcId() == RACE_MANAGER)
		{
			teleportBack(npc,player);
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new TeleportToRaceTrack(-1, qn, "teleports");
	}
}