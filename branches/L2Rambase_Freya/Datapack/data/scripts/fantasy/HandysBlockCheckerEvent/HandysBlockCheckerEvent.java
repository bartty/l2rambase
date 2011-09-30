package fantasy.HandysBlockCheckerEvent;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager;
import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager.ArenaParticipantsHolder;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2BlockInstance;
import l2.rambase.gameserver.model.entity.BlockCheckerEngine;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameChangePoints;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameChangeTimeToStart;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameExtendedChangePoints;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameRequestReady;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameTeamList;
import l2.rambase.util.Rnd;

/**
 * @author L2Rambase Project
 */
public class HandysBlockCheckerEvent extends Quest 
{
	private static final String qn = "HandysBlockCheckerEvent";
	
	// Arena Managers
	private static final int A_MANAGER_1 = 32521;
	private static final int A_MANAGER_2 = 32522;
	private static final int A_MANAGER_3 = 32523;
	private static final int A_MANAGER_4 = 32524;
	// Block Id Used by retail
	private static final int BLOCK = 18672;
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (npc == null || player == null) return null;
		
		int npcId = npc.getNpcId();
		
		if(npcId == BLOCK) return null;
		
		int arena = -1;
		switch(npcId)
		{
		case A_MANAGER_1:
			arena = 0;
			break;
		case A_MANAGER_2:
			arena = 1;
			break;
		case A_MANAGER_3:
			arena = 2;
			break;
		case A_MANAGER_4:
			arena = 3;
			break;
		}
		
		if (arena != -1)
		{
			if (eventIsFull(arena))
			{
				player.sendMessage("The Arena " + (arena + 1) + " queue is full. Try again later");
				return null;
			}
			if (HandysBlockCheckerManager.getInstance().arenaIsBeingUsed(arena))
			{
				player.sendMessage("The Arena is alredy holding an event");
				return null;
			}
			if(HandysBlockCheckerManager.getInstance().addPlayerToArena(player, arena))
			{
				ArenaParticipantsHolder holder = HandysBlockCheckerManager.getInstance().getHolder(arena);
				
				final ExCubeGameTeamList tl = new ExCubeGameTeamList(holder.getRedPlayers(), holder.getBluePlayers(), arena);
				
				player.sendPacket(tl);

				int countBlue = holder.getBlueTeamSize();
				int countRed = holder.getRedTeamSize();
				int minMembers = Config.MIN_BLOCK_CHECKER_TEAM_MEMBERS;
				
				if(countBlue >= minMembers && countRed >= minMembers)
				{
					holder.updateEvent();
					holder.broadCastPacketToTeam(new ExCubeGameRequestReady());
					holder.broadCastPacketToTeam(new ExCubeGameChangeTimeToStart(10));
				}
			}
		}
		return null;
	}
		
	@Override
	public final String onSkillSee(L2Npc npc, L2PcInstance player, L2Skill skill, L2Object[] targets, boolean isPet)
	{
		if(player == null || npc == null) return null;
		
		if(npc instanceof L2BlockInstance && npc.getNpcId() == BLOCK && player.getTarget() == npc)
		{
			if(skill.getId() == 5852 || skill.getId() == 5853)
			{
				L2BlockInstance block = (L2BlockInstance)npc;
				ArenaParticipantsHolder holder = HandysBlockCheckerManager.getInstance().getHolder(player.getBlockCheckerArena());
				
				if(holder.getPlayerTeam(player) == 0 && !block.isRed())
				{
					block.changeColor(player);
					increaseTeamPointsAndSend(player, holder.getEvent());
				}
				else if(holder.getPlayerTeam(player) == 1 && block.isRed())
				{
					block.changeColor(player);
					increaseTeamPointsAndSend(player, holder.getEvent());
				}
				else
					return null;
				
				// 30% chance to drop the event items
				int random = Rnd.get(100);
				// Bond
				if(random > 69 && random <= 84)
					dropItem(block, 13787, holder.getEvent(), player);
				// Land Mine
				else if(random > 84)
					dropItem(block, 13788, holder.getEvent(), player);
			}
		}
		return null;
	}
	
	private void increaseTeamPointsAndSend(L2PcInstance player, BlockCheckerEngine eng)
	{
		int team = eng.getHolder().getPlayerTeam(player);
		eng.increasePlayerPoints(player, team);
		
		int timeLeft = (int)((eng.getStarterTime() - System.currentTimeMillis()) / 1000);
		boolean isRed = eng.getHolder().getRedPlayers().contains(player);
		
		ExCubeGameChangePoints changePoints = new ExCubeGameChangePoints(timeLeft, eng.getBluePoints(), eng.getRedPoints());
		ExCubeGameExtendedChangePoints secretPoints = new ExCubeGameExtendedChangePoints(timeLeft, eng.getBluePoints(), eng.getRedPoints(),
				isRed, player, eng.getPlayerPoints(player, isRed));
		
		eng.getHolder().broadCastPacketToTeam(changePoints);
		eng.getHolder().broadCastPacketToTeam(secretPoints);
	}
	
	private boolean eventIsFull(int arena)
	{
		if(HandysBlockCheckerManager.getInstance().getHolder(arena).getAllPlayers().size() == 12)
			return true;
		return false;
	}
	
	private void dropItem(L2BlockInstance block, int id, BlockCheckerEngine eng, L2PcInstance player)
	{
		L2ItemInstance drop = ItemTable.getInstance().createItem("Loot", id, 1, player, block);
		int x = block.getX() + Rnd.get(50);
		int y = block.getY() + Rnd.get(50);
		int z = block.getZ();
		
		drop.dropMe(block, x, y, z);
		
		eng.addNewDrop(drop);
	}
	
	public HandysBlockCheckerEvent(int questId, String name, String descr) 
	{
		super(questId, name, descr);
		addFirstTalkId(A_MANAGER_1);
		addFirstTalkId(A_MANAGER_2);
		addFirstTalkId(A_MANAGER_3);
		addFirstTalkId(A_MANAGER_4);
		addFirstTalkId(BLOCK);
		addSkillSeeId(BLOCK);
	}
	
	public static void main(String[] args)
	{
		if(!Config.ENABLE_BLOCK_CHECKER_EVENT)
		{
			_log.info("Handy's Block Checker Event is disabled");
			return;
		}
		
		new HandysBlockCheckerEvent(-1, qn, "Handy's Block Checker Event");
		HandysBlockCheckerManager.getInstance().startUpParticipantsQueue();
		_log.info("Handy's Block Checker: Event Loaded.");
	}
}