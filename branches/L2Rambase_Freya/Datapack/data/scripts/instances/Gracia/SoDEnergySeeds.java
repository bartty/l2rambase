/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package instances.Gracia;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;

import javolution.util.FastMap;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.instancemanager.GraciaSeedManager;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.Rnd;

public class SoDEnergySeeds extends L2AttackableAIScript
{
	private static final String qn = "SoDEnergySeeds";
	private static final int RATE = 1;
	private static final int RESPAWN = 480000;
	private static final int RANDOM_RESPAWN_OFFSET = 180000;
	private static boolean _isAIActive = false;
	private static Map<Integer, ESSpawn> _spawns = new FastMap<Integer, ESSpawn>();
	private static Map<L2Npc, Integer> _spawnedNpcs = new FastMap<L2Npc, Integer>();
	
	private static final int EDRIC_OFFICER = 32527;
	private static final int TEMPORARY_TELEPORTER = 32602;
	private static final int[] seeds = {18678, 18679, 18680, 18681, 18682, 18683 };
	private static int[] doors = 
	{
		12240003,12240004,12240005,12240006,12240007,12240008,
		12240009,12240010,12240011,12240012,12240013,12240014,
		12240015,12240016,12240017,12240018,12240019,12240020,
		12240021,12240022,12240023,12240024,12240025,12240026,
		12240027,12240028,12240029,12240030,12240031
	};
	
	private ScheduledFuture<?> _scheduleStartAITask = null;
	
	private class ESSpawn
	{
		private int _spawnId;
		private int[] _npcIds; 
		private int[] _spawnCoords;
		
		public ESSpawn(int spawnId, int[] spawnCoords, int[] npcIds)
		{
			_spawnId = spawnId;
			_spawnCoords = spawnCoords;
			_npcIds = npcIds;
		}
		
		public void scheduleRespawn(long waitTime)
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new Runnable()
			{
				public void run()
				{
					// if the AI is inactive, do not spawn the NPC
					if (_isAIActive)
					{
						//get a random NPC that should spawn at this location
						Integer spawnId = _spawnId; // the map uses "Integer", not "int"
						_spawnedNpcs.put(addSpawn(_npcIds[Rnd.get(_npcIds.length)], _spawnCoords[0], _spawnCoords[1], _spawnCoords[2], 0, false, 0), spawnId);
					}
				}
			}, waitTime);
		}
	}

	public SoDEnergySeeds(int questId, String name, String descr)
	{
		super(questId, name, descr);
		registerMobs(seeds);
		for(int i : seeds)
			addFirstTalkId(i);
		addFirstTalkId(TEMPORARY_TELEPORTER);
		addSpawnsToList();
		
		if (GraciaSeedManager.getInstance().getSoDState() == 1)
		{
			scheduleStartAI();
			return;
		}
		else if (GraciaSeedManager.getInstance().getSoDState() == 3)
		{
			// Defense state is not implemented yet, so set state to 1 now
			scheduleStartAI();
			GraciaSeedManager.getInstance().setSoDState(1, true);
			return;
		}
		
		// get the remaining time the AI should stop
		Long nextStateChange = GraciaSeedManager.getInstance().getSoDTimeForNextStateChange();

		// get how much time is left, until the AI stops, if the AI end time hasnt expired, start the AI
		if (nextStateChange >= 1000)
			startAI();
		else
			stopAI();
	}

	public String onSkillSee (L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
	{
		// if the AI is inactive, disable the gather ability
		if (!_isAIActive)
			return super.onSkillSee(npc, caster, skill, targets, isPet);
		
		if (targets[0] == npc && skill.getId() == 5780)
		{
			int itemId = 0;
			
			switch(npc.getNpcId())
			{
				case 18678: //Water
						itemId = 14016;
						break;
				case 18679: //Fire
						itemId = 14015;
						break;
				case 18680: //Wind
						itemId = 14017;
						break;
				case 18681: //Earth
						itemId = 14018;
						break;
				case 18682: //Divinity
						itemId = 14020;
						break;
				case 18683: //Darkness
						itemId = 14019;
						break;
				default:
					return super.onSkillSee(npc, caster, skill, targets, isPet);
			}
			
			if (Rnd.get(100) < 33)
			{
				caster.sendPacket(new SystemMessage(SystemMessageId.THE_COLLECTION_HAS_SUCCEEDED));
				caster.addItem("Loot", itemId, Rnd.get(RATE + 1, 2 * RATE), null, true);
			}
			else if ((skill.getLevel() == 1 && Rnd.get(100) < 15) ||
					(skill.getLevel() == 2 && Rnd.get(100) < 50) ||
					(skill.getLevel() == 3 && Rnd.get(100) < 75))
			{
				caster.sendPacket(new SystemMessage(SystemMessageId.THE_COLLECTION_HAS_SUCCEEDED));
				caster.addItem("Loot", itemId, Rnd.get(1, RATE), null, true);
			}
			else
				caster.sendPacket(new SystemMessage(SystemMessageId.THE_COLLECTION_HAS_FAILED));
			
			//_spawnedNpcs contains the ESSpawn ID of this NPC, and we call that ESSpawn from _spawns
			_spawns.get(_spawnedNpcs.get(npc)).scheduleRespawn(RESPAWN+Rnd.get(RANDOM_RESPAWN_OFFSET));
			
			_spawnedNpcs.remove(npc);
			npc.deleteMe();
		}
		return super.onSkillSee(npc, caster, skill, targets, isPet);
	}
	
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		//_spawnedNpcs contains the ESSpawn ID of this NPC, and we call that ESSpawn from _spawns
		_spawns.get(_spawnedNpcs.get(npc)).scheduleRespawn(RESPAWN+Rnd.get(RANDOM_RESPAWN_OFFSET));
		
		_spawnedNpcs.remove(npc);
		return super.onKill(npc, player, isPet);
	}
	
	public void startAI()
	{
		if (_isAIActive)//hmm? will this happen? just in case
			stopAI();
		
		_isAIActive = true;

		// spawn all NPCs
		for (ESSpawn spawn : _spawns.values())
			spawn.scheduleRespawn(0);

		if (_scheduleStartAITask != null)
		{
			_scheduleStartAITask.cancel(false);
			_scheduleStartAITask = null;
		}
		
		openDoors();

		// get how much time is left, until the AI stops, if the date has passed, stopAI
		long timeLeft = GraciaSeedManager.getInstance().getSoDTimeForNextStateChange();
		if (timeLeft <= 0)
			stopAI();
		else
			scheduleStopAI(timeLeft);
		
		_log.info("Seed of Destruction: Energy Seeds started");
		_log.info("Seed of Destruction: State will be changed at " + timeLeft);
	}
	
	private void scheduleStartAI()
	{
		if (_scheduleStartAITask != null)
		{
			_scheduleStartAITask.cancel(false);
			_scheduleStartAITask = null;
		}
		_scheduleStartAITask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new Runnable()
		{
			public void run() { if (GraciaSeedManager.getInstance().getSoDState() == 2) startAI(); }
		}, 600000, 60000);
	}
	
	private void scheduleStopAI(long timeLeft)
	{
		ThreadPoolManager.getInstance().scheduleGeneral(new Runnable()
		{
			public void run() {stopAI();}
		}, timeLeft);
	}
	
	public void stopAI()
	{
		//unspawn all NPCs
		for (Entry<L2Npc, Integer> es : _spawnedNpcs.entrySet())
			es.getKey().deleteMe();	
		_spawnedNpcs.clear();
		
		_isAIActive = false;
		
		closeDoors();
		
		// Defense state is not implemented yet, so set state to 1 now
		GraciaSeedManager.getInstance().setSoDState(1, true);
		scheduleStartAI();
		_log.info("Seed of Destruction: Energy Seeds stopped");
	}
	
	private void openDoors()
	{
		for (int doorId : doors)
		{
			L2DoorInstance doorInstance = DoorTable.getInstance().getDoor(doorId);
			
			if (doorInstance != null)
				doorInstance.openMe();
		}
	}
	
	private void closeDoors()
	{
		for (int doorId : doors)
		{
			L2DoorInstance doorInstance = DoorTable.getInstance().getDoor(doorId);
			
			if (doorInstance != null)
				doorInstance.closeMe();
		}
	}
	
	public String onFirstTalk (L2Npc npc, L2PcInstance player)
	{
		if (npc.getNpcId() == TEMPORARY_TELEPORTER)
			return "<html><body>Temporary Transfer Teacher:<br>I Kese Terrace fortress of the kinds of configuration in a temporary burst transmission division.<br><a action=\"bypass -h npc_%objectId%_goto 3260201\">To the Central Plaza </a><br><a action=\"bypass -h npc_%objectId%_goto 3260202\">To the fort </a><br><a action=\"bypass -h npc_%objectId%_goto 3260203\">To the real dragon throne</a></body></html>";

		player.sendPacket(ActionFailed.STATIC_PACKET);
		return null;
	}
	
	private void addSpawnsToList()
	{
		//Temporary Teleporters
		_spawns.put(1, new ESSpawn(1, new int[]{-245790,220320,-12104}, new int[]{TEMPORARY_TELEPORTER}));
		_spawns.put(2, new ESSpawn(2, new int[]{-249937,207224,-11968}, new int[]{TEMPORARY_TELEPORTER}));
		//Energy Seeds
		_spawns.put(3, new ESSpawn(3, new int[]{-248360,219272,-12448}, new int[]{18678,18679,18680}));
		_spawns.put(4, new ESSpawn(4, new int[]{-249448,219256,-12448}, new int[]{18678,18679,18680}));
		_spawns.put(5, new ESSpawn(5, new int[]{-249432,220872,-12448}, new int[]{18678,18679,18680}));
		_spawns.put(6, new ESSpawn(6, new int[]{-248360,220888,-12448}, new int[]{18678,18679,18680}));
		
		_spawns.put(7, new ESSpawn(7, new int[]{-250088,219256,-12448}, new int[]{18681,18682}));
		_spawns.put(8, new ESSpawn(8, new int[]{-250600,219272,-12448}, new int[]{18681,18682}));
		_spawns.put(9, new ESSpawn(9, new int[]{-250584,220904,-12448}, new int[]{18681,18682}));
		_spawns.put(10, new ESSpawn(10, new int[]{-250072,220888,-12448}, new int[]{18681,18682}));
		
		_spawns.put(11, new ESSpawn(11, new int[]{-253096,217704,-12296}, new int[]{18683,18678}));
		_spawns.put(12, new ESSpawn(12, new int[]{-253112,217048,-12288}, new int[]{18683,18678}));
		_spawns.put(13, new ESSpawn(13, new int[]{-251448,217032,-12288}, new int[]{18683,18678}));
		_spawns.put(14, new ESSpawn(14, new int[]{-251416,217672,-12296}, new int[]{18683,18678}));
		
		_spawns.put(15, new ESSpawn(15, new int[]{-251416,217672,-12296}, new int[]{18679,18680}));
		_spawns.put(16, new ESSpawn(16, new int[]{-251416,217016,-12280}, new int[]{18679,18680}));
		_spawns.put(17, new ESSpawn(17, new int[]{-249752,217016,-12280}, new int[]{18679,18680}));
		_spawns.put(18, new ESSpawn(18, new int[]{-249736,217688,-12296}, new int[]{18679,18680}));
		
		_spawns.put(19, new ESSpawn(19, new int[]{-252472,215208,-12120}, new int[]{18681,18682}));
		_spawns.put(20, new ESSpawn(20, new int[]{-252552,216760,-12248}, new int[]{18681,18682}));
		_spawns.put(21, new ESSpawn(21, new int[]{-253160,216744,-12248}, new int[]{18681,18682}));
		_spawns.put(22, new ESSpawn(22, new int[]{-253128,215160,-12096}, new int[]{18681,18682}));
		
		_spawns.put(23, new ESSpawn(23, new int[]{-250392,215208,-12120}, new int[]{18683,18678}));
		_spawns.put(24, new ESSpawn(24, new int[]{-250264,216744,-12248}, new int[]{18683,18678}));
		_spawns.put(25, new ESSpawn(25, new int[]{-249720,216744,-12248}, new int[]{18683,18678}));
		_spawns.put(26, new ESSpawn(26, new int[]{-249752,215128,-12096}, new int[]{18683,18678}));
		
		_spawns.put(27, new ESSpawn(27, new int[]{-250280,216760,-12248}, new int[]{18679,18680,18681}));
		_spawns.put(28, new ESSpawn(28, new int[]{-250344,216152,-12248}, new int[]{18679,18680,18681}));
		_spawns.put(29, new ESSpawn(29, new int[]{-252504,216152,-12248}, new int[]{18679,18680,18681}));
		_spawns.put(30, new ESSpawn(30, new int[]{-252520,216792,-12248}, new int[]{18679,18680,18681}));
		
		_spawns.put(31, new ESSpawn(31, new int[]{-242520,217272,-12384}, new int[]{18681,18682,18683}));
		_spawns.put(32, new ESSpawn(32, new int[]{-241432,217288,-12384}, new int[]{18681,18682,18683}));
		_spawns.put(33, new ESSpawn(33, new int[]{-241432,218936,-12384}, new int[]{18681,18682,18683}));
		_spawns.put(34, new ESSpawn(34, new int[]{-242536,218936,-12384}, new int[]{18681,18682,18683}));
		
		_spawns.put(35, new ESSpawn(35, new int[]{-240808,217272,-12384}, new int[]{18678,18679}));
		_spawns.put(36, new ESSpawn(36, new int[]{-240280,217272,-12384}, new int[]{18678,18679}));
		_spawns.put(37, new ESSpawn(37, new int[]{-240280,218952,-12384}, new int[]{18678,18679}));
		_spawns.put(38, new ESSpawn(38, new int[]{-240792,218936,-12384}, new int[]{18678,18679}));
		
		_spawns.put(39, new ESSpawn(39, new int[]{-239576,217240,-12640}, new int[]{18680,18681,18682}));
		_spawns.put(40, new ESSpawn(40, new int[]{-239560,216168,-12640}, new int[]{18680,18681,18682}));
		_spawns.put(41, new ESSpawn(41, new int[]{-237896,216152,-12640}, new int[]{18680,18681,18682}));
		_spawns.put(42, new ESSpawn(42, new int[]{-237912,217256,-12640}, new int[]{18680,18681,18682}));
		
		_spawns.put(43, new ESSpawn(43, new int[]{-237896,215528,-12640}, new int[]{18683,18678}));
		_spawns.put(44, new ESSpawn(44, new int[]{-239560,215528,-12640}, new int[]{18683,18678}));
		_spawns.put(45, new ESSpawn(45, new int[]{-239560,214984,-12640}, new int[]{18683,18678}));
		_spawns.put(46, new ESSpawn(46, new int[]{-237896,215000,-12640}, new int[]{18683,18678}));
		
		_spawns.put(47, new ESSpawn(47, new int[]{-237896,213640,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(48, new ESSpawn(48, new int[]{-239560,213640,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(49, new ESSpawn(49, new int[]{-239544,212552,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(50, new ESSpawn(50, new int[]{-237912,212552,-12768}, new int[]{18678,18679,18680}));
		
		_spawns.put(51, new ESSpawn(51, new int[]{-237912,211912,-12768}, new int[]{18681,18682}));
		_spawns.put(52, new ESSpawn(52, new int[]{-237912,211400,-12768}, new int[]{18681,18682}));
		_spawns.put(53, new ESSpawn(53, new int[]{-239560,211400,-12768}, new int[]{18681,18682}));
		_spawns.put(54, new ESSpawn(54, new int[]{-239560,211912,-12768}, new int[]{18681,18682}));
		
		_spawns.put(55, new ESSpawn(55, new int[]{-241960,214536,-12512}, new int[]{18683,18678,18679}));
		_spawns.put(56, new ESSpawn(56, new int[]{-241976,213448,-12512}, new int[]{18683,18678,18679}));
		_spawns.put(57, new ESSpawn(57, new int[]{-243624,213448,-12512}, new int[]{18683,18678,18679}));
		_spawns.put(58, new ESSpawn(58, new int[]{-243624,214520,-12512}, new int[]{18683,18678,18679}));
		
		_spawns.put(59, new ESSpawn(59, new int[]{-241976,212808,-12504}, new int[]{18680,18681}));
		_spawns.put(60, new ESSpawn(60, new int[]{-241960,212280,-12504}, new int[]{18680,18681}));
		_spawns.put(61, new ESSpawn(61, new int[]{-243624,212264,-12504}, new int[]{18680,18681}));
		_spawns.put(62, new ESSpawn(62, new int[]{-243624,212792,-12504}, new int[]{18680,18681}));
		
		_spawns.put(63, new ESSpawn(63, new int[]{-243640,210920,-12640}, new int[]{18682,18683,18678}));
		_spawns.put(64, new ESSpawn(64, new int[]{-243624,209832,-12640}, new int[]{18682,18683,18678}));
		_spawns.put(65, new ESSpawn(65, new int[]{-241976,209832,-12640}, new int[]{18682,18683,18678}));
		_spawns.put(66, new ESSpawn(66, new int[]{-241976,210920,-12640}, new int[]{18682,18683,18678}));
		
		_spawns.put(67, new ESSpawn(67, new int[]{-241976,209192,-12640}, new int[]{18679,18680}));
		_spawns.put(68, new ESSpawn(68, new int[]{-241976,208664,-12640}, new int[]{18679,18680}));
		_spawns.put(69, new ESSpawn(69, new int[]{-243624,208664,-12640}, new int[]{18679,18680}));
		_spawns.put(70, new ESSpawn(70, new int[]{-243624,209192,-12640}, new int[]{18679,18680}));
		
		_spawns.put(71, new ESSpawn(71, new int[]{-241256,208664,-12896}, new int[]{18681,18682,18683}));
		_spawns.put(72, new ESSpawn(72, new int[]{-240168,208648,-12896}, new int[]{18681,18682,18683}));
		_spawns.put(73, new ESSpawn(73, new int[]{-240168,207000,-12896}, new int[]{18681,18682,18683}));
		_spawns.put(74, new ESSpawn(74, new int[]{-241256,207000,-12896}, new int[]{18681,18682,18683}));
		
		_spawns.put(75, new ESSpawn(75, new int[]{-239528,208648,-12896}, new int[]{18678,18679}));
		_spawns.put(76, new ESSpawn(76, new int[]{-238984,208664,-12896}, new int[]{18678,18679}));
		_spawns.put(77, new ESSpawn(77, new int[]{-239000,207000,-12896}, new int[]{18678,18679}));
		_spawns.put(78, new ESSpawn(78, new int[]{-239512,207000,-12896}, new int[]{18678,18679}));
		
		_spawns.put(79, new ESSpawn(79, new int[]{-245064,213144,-12384}, new int[]{18680,18681,18682}));
		_spawns.put(80, new ESSpawn(80, new int[]{-245064,212072,-12384}, new int[]{18680,18681,18682}));
		_spawns.put(81, new ESSpawn(81, new int[]{-246696,212072,-12384}, new int[]{18680,18681,18682}));
		_spawns.put(82, new ESSpawn(82, new int[]{-246696,213160,-12384}, new int[]{18680,18681,18682}));
		
		_spawns.put(83, new ESSpawn(83, new int[]{-245064,211416,-12384}, new int[]{18683,18678}));
		_spawns.put(84, new ESSpawn(84, new int[]{-245048,210904,-12384}, new int[]{18683,18678}));
		_spawns.put(85, new ESSpawn(85, new int[]{-246712,210888,-12384}, new int[]{18683,18678}));
		_spawns.put(86, new ESSpawn(86, new int[]{-246712,211416,-12384}, new int[]{18683,18678}));
		
		_spawns.put(87, new ESSpawn(87, new int[]{-245048,209544,-12512}, new int[]{18679,18680,18681}));
		_spawns.put(88, new ESSpawn(88, new int[]{-245064,208456,-12512}, new int[]{18679,18680,18681}));
		_spawns.put(89, new ESSpawn(89, new int[]{-246696,208456,-12512}, new int[]{18679,18680,18681}));
		_spawns.put(90, new ESSpawn(90, new int[]{-246712,209544,-12512}, new int[]{18679,18680,18681}));
		
		_spawns.put(91, new ESSpawn(91, new int[]{-245048,207816,-12512}, new int[]{18682,18683}));
		_spawns.put(92, new ESSpawn(92, new int[]{-245048,207288,-12512}, new int[]{18682,18683}));
		_spawns.put(93, new ESSpawn(93, new int[]{-246696,207304,-12512}, new int[]{18682,18683}));
		_spawns.put(94, new ESSpawn(94, new int[]{-246712,207816,-12512}, new int[]{18682,18683}));
		
		_spawns.put(95, new ESSpawn(95, new int[]{-244328,207272,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(96, new ESSpawn(96, new int[]{-243256,207256,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(97, new ESSpawn(97, new int[]{-243256,205624,-12768}, new int[]{18678,18679,18680}));
		_spawns.put(98, new ESSpawn(98, new int[]{-244328,205608,-12768}, new int[]{18678,18679,18680}));
		
		_spawns.put(99, new ESSpawn(99, new int[]{-242616,207272,-12768}, new int[]{18681,18682}));
		_spawns.put(100, new ESSpawn(100, new int[]{-242104,207272,-12768}, new int[]{18681,18682}));
		_spawns.put(101, new ESSpawn(101, new int[]{-242088,205624,-12768}, new int[]{18681,18682}));
		_spawns.put(102, new ESSpawn(102, new int[]{-242600,205608,-12768}, new int[]{18681,18682}));
		
		_spawns.put(103, new ESSpawn(103, new int[]{-248525,250048,4307}, new int[]{EDRIC_OFFICER}));
	}
	
	public static void main(String[] args)
	{
		new SoDEnergySeeds(-1, qn, "instances");
	}
}
