package teleports.TeleportToFantasyIsle;

import javolution.util.FastMap;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.NpcSay;
import l2.rambase.util.Rnd;

public class TeleportToFantasyIsle extends Quest
{
	private static final String qn = "1106_teleport_to_fantasy_isle";
	
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
	
	private static final int PADDIES = 32378;
	private static final int[][] ISLE_LOCS = 
	{
		{-58752,-56898,-2032},
		{-59716,-57868,-2032},
		{-60691,-56893,-2032},
		{-59720,-55921,-2032}
	};
	private static final int[] TELEPORTERS =
	{
		30059,30080,30177,30233,30256,
		30320,30848,30899,31320,31275,
	    31964
	};	

	public TeleportToFantasyIsle(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int npcId: TELEPORTERS)
		{
			addStartNpc(npcId);
			addTalkId(npcId);
		}
		addStartNpc(PADDIES);
		addTalkId(PADDIES);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		for (int npcId : TELEPORTERS)
		{	
			if( npc.getNpcId() == npcId)
			{
				if (teleportLocs.get(player.getObjectId()) == null)
				{
					CurrentLocation _loc = null;
					_loc = new CurrentLocation(player.getX(),player.getY(),player.getZ());
					teleportLocs.put(player.getObjectId(),_loc);
					int random = Rnd.get(3);
					player.teleToLocation(ISLE_LOCS[random][0],ISLE_LOCS[random][1],ISLE_LOCS[random][2]);			
				}
				else
				{
					teleportLocs.remove(player.getObjectId());
					CurrentLocation _currentLocation = new CurrentLocation(player.getX(),player.getY(),player.getZ());
					teleportLocs.put(player.getObjectId(),_currentLocation);
					int random = Rnd.get(3);
					player.teleToLocation(ISLE_LOCS[random][0],ISLE_LOCS[random][1],ISLE_LOCS[random][2]);
				}
			}
		}
		if (npc.getNpcId() == PADDIES)
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
		st.exitQuest(true);
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new TeleportToFantasyIsle(-1, qn, "teleports");
	}
}