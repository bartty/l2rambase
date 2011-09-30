package instances.Gracia;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.GrandBossManager;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.InstanceManager.InstanceWorld;
import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2CommandChannel;
import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.zone.type.L2BossZone;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.MagicSkillUse;
import l2.rambase.gameserver.network.serverpackets.MoveToLocation;
import l2.rambase.gameserver.network.serverpackets.SocialAction;
import l2.rambase.gameserver.network.serverpackets.SpecialCamera;
import l2.rambase.gameserver.util.Util;

/**
 * @author L2Rambase Project
 */

public class SeedOfInfinity extends Quest
{
	private class SoIWorld extends InstanceWorld
	{
		// public long endTime = 0;
		public SoIWorld(Long time)
		{
			InstanceManager.getInstance().super();
			// endTime = time;
		}
	}
	
	private static final String qn = "SeedOfInfinity";
	private static final int INSTANCEID = 119;
	private static final int INSTANCEPENALTY = 86400000; // reenter time: 86400000ms(24h)
	private static final boolean debug = false;
	
	private static L2BossZone _Zone;

	private L2Npc camera1, camera3, preawakened, ekimus, hound1, hound2, realhound1, realhound2;
	// NPC IDs
	private static final int TEPIOS = 32530;
	// private static final int TUMOR_OF_DEATH_1 = 18704;
	// private static final int TUMOR_OF_DEATH_2 = 18708;
	// private static final int DESTROYED_TUMOR = 18705;
	// private static final int SOUL_COFFIN_MAIN = 18706;
	// private static final int SOUL_COFFIN_1 = 18709;
	// private static final int SOUL_COFFIN_2 = 18710;
	// private static final int SOUL_COFFIN_3 = 18711;
	// private static final int SOUL_COFFIN_4 = 18712;
	// private static final int SOUL_COFFIN_5 = 18713;
	// private static final int SOUL_COFFIN_6 = 18714;
	// private static final int CONQUERED_SOUL_1 = 18715;
	// private static final int CONQUERED_SOUL_2 = 18716;
	// private static final int CONQUERED_SOUL_3 = 18717;
	// MOB IDs
	// private static final int FANATIC_OF_INFINITY = 22509;
	// private static final int ROTTEN_MESSENGER_A = 22510;
	// private static final int ZEALOT_OF_INFINITY = 22511;
	// private static final int BODY_SEVERER = 22512;
	// private static final int BODY_HARVESTER_A = 22513;
	// private static final int SOUL_EXPLOITER_A = 22514;
	// private static final int SOUL_DEVOURER_A = 22515;
	// private static final int BUTCHER_OF_INFINITY = 22516;
	// private static final int DISCIPLE_OF_INFINITY = 22517;
	// private static final int ROTTEN_MESSENGER_B = 22518;
	// private static final int BODY_HARVESTER_B = 22519;
	// private static final int BODY_SEVERER = 22520;
	// private static final int SOUL_EXPLOITER_B = 22521;
	// private static final int SOUL_DEVOURER_B = 22522;
	// private static final int SOUL_DEVOURER_C = 22523;
	// private static final int EMISSARY_OF_DEATH_A = 22524;
	// private static final int EMISSARY_OF_DEATH_B = 22525;
	// private static final int EMISSARY_OF_DEATH_C = 22526;
	// private static final int EMISSARY_OF_DEATH_D = 22527;
	// private static final int CURSED_PREFECT_A = 22528;
	// private static final int CURSED_PREFECT_B = 22529;
	// private static final int CURSED_PREFECT_C = 22530;
	// private static final int CURSED_PREFECT_D = 22531;
	// private static final int LAW_SCHOLAR_OF_CONCLUSIONS_A = 22532;
	// private static final int LAW_SCHOLAR_OF_CONCLUSIONS_B = 22533;
	// private static final int LAW_SCHOLAR_OF_CONCLUSIONS_C = 22534;
	// private static final int LAW_SCHOLAR_OF_CONCLUSIONS_D = 22535;
	// RAID IDs
	// private static final int PREAWAKENED = 29161;
	private static final int EKIMUS = 29150;
	private static final int HOUND = 29151;
	// private static final int HOUND_B = 29152;
	
	private static List<L2PcInstance> _PlayersInside = new FastList<L2PcInstance>();

	private class teleCoord
	{
		int instanceId;
		int x;
		int y;
		int z;
	}
	
	private boolean checkConditions(L2PcInstance player)
	{
		if (debug)
			return true;
		if (player.getParty() == null
				|| player.getParty().getLeader() != player
				|| player.getParty().getCommandChannel() == null
				|| player.getParty().getCommandChannel().getChannelLeader() != player)
		{
			player.sendMessage("Only Command Channel Leader can try to enter.");
			return false;
		}

		L2CommandChannel CC = player.getParty().getCommandChannel();
		_PlayersInside.add(player);
		for (L2Party party : CC.getPartys())
		{
			for (L2PcInstance member : party.getPartyMembers())
			{
				if (member == null || member.getLevel() < 75)
				{
					SystemMessage sm = new SystemMessage(2097);
					sm.addPcName(member);
					party.broadcastToPartyMembers(sm);
					return false;
				}
				_PlayersInside.add(member);
				if (_PlayersInside.size() < 36)
				{
					_PlayersInside.clear();
					member.sendMessage("The number of challenges should be 36 at least, so can not enter.");
					return false;
				}
				if (_PlayersInside.size() > 45)
				{
					_PlayersInside.clear();
					member.sendMessage("The number of challenges have been full, so can not enter.");
					return false;
				}
				if (!Util.checkIfInRange(1000, player, member, true))
				{
					SystemMessage sm = new SystemMessage(2096);
					sm.addPcName(member);
					party.broadcastToPartyMembers(sm);
					return false;
				}
				Long reentertime = InstanceManager.getInstance().getInstanceTime(member.getObjectId(), INSTANCEID);
				if (System.currentTimeMillis() < reentertime)
				{
					SystemMessage sm = new SystemMessage(2100);
					sm.addPcName(member);
					party.broadcastToPartyMembers(sm);
					return false;
				}
			}
		}
		return true;
	}
	
	private void teleportplayer(L2PcInstance player, teleCoord teleto)
	{
                player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		return;
	}
	
	protected int enterInstance(L2PcInstance player, String template, teleCoord teleto)
	{
		int instanceId = 0;
		// check for existing instances for this player
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		// existing instance
		if (world != null)
		{
			if (!(world instanceof SoIWorld))
			{
				player.sendPacket(SystemMessageId.ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER);
				return 0;
			}
			teleto.instanceId = world.instanceId;
			teleportplayer(player, teleto);
			return instanceId;
		}
		else
		{
			if (!checkConditions(player))
				return 0;
			L2Party party = player.getParty();
			instanceId = InstanceManager.getInstance().createDynamicInstance(template);
			world = new SoIWorld(System.currentTimeMillis() + 5400000);
			world.instanceId = instanceId;
			world.templateId = INSTANCEID;
			InstanceManager.getInstance().addWorld(world);
			// teleport players
			teleto.instanceId = instanceId;
			if (party == null)
			{
				// this can happen only if debug is true
				InstanceManager.getInstance().setInstanceTime(player.getObjectId(), INSTANCEID, ((System.currentTimeMillis() + INSTANCEPENALTY)));
				teleportplayer(player, teleto);
				world.allowed.add(player.getObjectId());
			}
			else
			{
				for (L2PcInstance p : _PlayersInside)
				{
					InstanceManager.getInstance().setInstanceTime(p.getObjectId(), INSTANCEID, ((System.currentTimeMillis() + INSTANCEPENALTY)));
					teleportplayer(p, teleto);
					world.allowed.add(p.getObjectId());
					_PlayersInside.clear();
				}
			}
			return instanceId;
		}
	}
	
	protected void exitInstance(L2PcInstance player, teleCoord tele)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(0);
		player.teleToLocation(tele.x, tele.y, tele.z);
	}
	
	public SeedOfInfinity(int id,String name,String descr)
	{
		super(id, name, descr);
		_Zone = GrandBossManager.getInstance().getZone(-179539, 209312, -15499);
		addEventId(TEPIOS, Quest.QuestEventType.QUEST_START);
		addEventId(TEPIOS, Quest.QuestEventType.ON_FIRST_TALK);
		addEventId(EKIMUS, Quest.QuestEventType.ON_ATTACK);
		addEventId(HOUND, Quest.QuestEventType.ON_ATTACK);
		addEventId(EKIMUS, Quest.QuestEventType.ON_KILL);
		addEventId(HOUND, Quest.QuestEventType.ON_KILL);

	}

	public void broadcastCamera(L2Npc npc, int dist, int yaw, int pitch, int time, int duration, int turn, int rise)
	{
		for (L2Character cha : _Zone.getCharactersInside().values())
		{
			if (cha instanceof L2PcInstance)
			{
				cha.abortAttack();
				cha.abortCast();
				cha.setTarget(null);
				cha.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				cha.sendPacket(new SpecialCamera(npc.getObjectId(),dist,yaw,pitch,time,duration,turn,rise,1,0));
				L2Summon pet = cha.getPet();
				if (pet != null)
				{
					pet.abortAttack();
					pet.abortCast();
					pet.setTarget(null);
					pet.breakAttack();
					pet.breakCast();
					pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				}
			}
		}
	}

	public void broadcastSocialAction(L2Npc npc, int socialActionId)
	{
		for (L2Character cha : _Zone.getCharactersInside().values())
		{
			if (cha instanceof L2PcInstance)
				cha.sendPacket(new SocialAction(npc.getObjectId(),socialActionId));
		}
	}

	public void broadcastMSU(L2Npc npc, int skillId, int skillLevel)
	{
		L2Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
		MagicSkillUse MSU = new MagicSkillUse(npc, npc, skill.getId(), skill.getLevel(), skill.getHitTime(), 0);
		for (L2Character cha : _Zone.getCharactersInside().values())
		{
			if (cha instanceof L2PcInstance)
				cha.sendPacket(MSU);
		}
	}

	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}		
		if (npc.getNpcId() == TEPIOS)
		{
			if (event.equalsIgnoreCase("enter"))
			{
				teleCoord tele = new teleCoord();
				tele.x = -242760;
				tele.y = 219984;
				tele.z = -9985;
				enterInstance(player, "SeedOfInfinity.xml", tele);
				return "";
			}
			else if (event.equalsIgnoreCase("0"))
			{
				String voiceText = "";
				voiceText = "<html><body>Officer Tepios:<br>";
				voiceText += "Welcome to the Seed of Infinity. My name is Officer Tepios. My role is to build temporary shelters and guide newcomers around here.<br>";
				voiceText += "This is the place we call the <font color=LEVEL>Death Gate</font>. Many soldiers and unnamed adventurers have gone to their deaths through this entrance.<br>";
				voiceText += "However, we must continue to venture through it to face our enemies. It is our fate, I suppose. If you would help us, we will reward you on behalf of Admiral Keucereus.<br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity enter\">Enter to Seed of Infinity.</a><br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 1\">Ask about the Seed of Infinity.</a><br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 2\">Ask about a cycle of the Seed Immortality.</a><br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 3\">Ask about Tumors and Soul Coffins.</a><br>";
				voiceText += "</body></html>";
				return voiceText;
			}
			else if (event.equalsIgnoreCase("1"))
			{
				String voiceText = "";
				voiceText = "<html><body>Officer Tepios:<br>";
				voiceText += "<font color=LEVEL>The Seed of Infinity</font> is an infernal region ruled by Shilen's disciple, the undead fiend <font color=LEVEL>Ekimus</font>. We know little about him, unfortunately.<br>";
				voiceText += "But I can you something about this region itself. Here <font color=LEVEL>space itself is organically linked together like some vast living organism</font>. Just as your whole body is affected";
				voiceText += "if you injure one part, so the Seed of Infinity's overall power decreases as we slay monsters in this region.<br>";
				voiceText += "But if you are killed within, then your soul will feed and strengthen the power of the entire Seed.<br>";
				voiceText += "That is why it is so important that <font color=LEVEL>only parties of skilled adventurers be admitted to the Seed of Infinity</font>. <br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 0\">Back</a></body></html>";
				return voiceText;
			}
			else if (event.equalsIgnoreCase("2"))
			{
				String voiceText = "";
				voiceText = "<html><body>Officer Tepios:<br>";
				voiceText += "Status of the Seed of Infinity constantly changes by success or failure of an attacking party's attack, caught monsters, and etc...";
				voiceText += "Therefore, this becomes a one cycle, which can be divided into a total of 6 steps. So, sometimes we have to block their power by attacking because";
				voiceText += "the power of this Seed becomes too strong, but sometimes it is more important to defend efficiently when the power of the Seed becomes weak. And if this";
				voiceText += "region becomes stable you could acquire collections which couldn't be acquired at ordinary times.<br>Because information like this is considered very important,";
				voiceText += "so it always delivers to Keucereus Alliance Base. Thus, Admiral Keucereus and General Dilios get to know it. You can find about an occupational cycle of this place through them.<br>";
				voiceText += "Be sure to remember. All of your movements effect the cycle of this region. Therefore, don't die recklessly even if you want to die.<br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 0\">Back</a></body></html>";
				return voiceText;
			}
			else if (event.equalsIgnoreCase("3"))
			{
				String voiceText = "";
				voiceText = "<html><body>Officer Tepios:<br>";
				voiceText += "This entire area acts almost like a living organism. <br>";
				voiceText += "Just as blood supplies nutrients to a human body, so apparently do the souls who have perished here supply energy for the <font color=LEVEL>Tumors of Death and the Soul Coffins</font>.<br>";
				voiceText += "They in turn use that energy to strengthen nearby monsters or disadvantage attacking adventurers. Destroy those things whenever you encounter them. Of course, that's easier said than done -- the nearby monsters will defend it fiercely.<br>";
				voiceText += "But if you're able to destroy a Tumor, you will gain an advantage over the monsters. We don't know for sure, but we think that the newly-freed souls help us. It is possible to communicate with a <font color=LEVEL>Destroyed Tumor</font>,";
				voiceText += "and you can also use it to teleport to other Tumors, although you'll need something called <font color=LEVEL>Tears of a Freed Soul</font> to do so.<br>";
				voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 0\">Back</a></body></html>";
				return voiceText;
			}
		}
		else
		{
			if (event.equalsIgnoreCase("start"))
			{
				camera1 = addSpawn(29153, -179479, 208513, -15506, 0, false, 0);
				broadcastCamera(camera1, 0, 110, 350, 0, 15000, 0, 0);
				startQuestTimer("1", 6000, null, null);
				startQuestTimer("camera2", 14500, null, null);
				startQuestTimer("deleteNpc", 15000, camera1, null);
			}
			else if (event.equalsIgnoreCase("camera2"))
			{
				broadcastCamera(preawakened, 150, 90, 355, 0, 15000, 0, 0);
				startQuestTimer("camera3", 14500, null, null);
			}
			else if (event.equalsIgnoreCase("camera3"))
			{
				camera3 = addSpawn(29155, -179535, 208700, -15497, 0, false, 0);
				broadcastCamera(camera3, 0, 90, 0, 0, 6000, 0, 0);
				camera3.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO,new L2CharPosition(-179553,209338,-15495,0));
				for (L2Character cha : _Zone.getCharactersInside().values())
				{
					if (cha instanceof L2PcInstance)
						cha.sendPacket(new MoveToLocation(camera3));
				}
				startQuestTimer("camera4", 5000, null, null);
				startQuestTimer("deleteNpc", 5500, camera3, null);
			}
			else if (event.equalsIgnoreCase("camera4"))
			{
				broadcastCamera(ekimus, 100, 120, -10, 0, 4000, 0, 0);
				startQuestTimer("camera7", 9750, null, null);
			}
			else if (event.equalsIgnoreCase("camera7"))
			{
				broadcastCamera(ekimus, 500, 90, 0, 0, 8000, 0, 0);
			}
			else if (event.equalsIgnoreCase("1"))
			{
				broadcastMSU(preawakened, 5786, 1);
				startQuestTimer("2", 22000, null, null);
			}
			else if (event.equalsIgnoreCase("2"))
			{
				preawakened.deleteMe();
				ekimus = addSpawn(EKIMUS, -179539, 209312, -15499, 16383, false, 0);
				ekimus.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				startQuestTimer("anim1", 100, null, null);
				startQuestTimer("3", 19000, null, null);
				startQuestTimer("h1", 9250, null, null);
			}
			else if (event.equalsIgnoreCase("anim1"))
			{
				broadcastMSU(ekimus, 5787, 1);
			}
			else if (event.equalsIgnoreCase("3"))
			{
				broadcastMSU(ekimus, 5788, 1);
				startQuestTimer("4", 4400, null, null);
			}
			else if (event.equalsIgnoreCase("4"))
			{
				ekimus.deleteMe();
				ekimus = addSpawn(EKIMUS, -179536, 208892, -15497, 16383, false, 0);
				ekimus.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				startQuestTimer("anim2", 50, null, null);
				startQuestTimer("spawnHounds", 1000, null, null);
			}
			else if (event.equalsIgnoreCase("anim2"))
			{
				broadcastMSU(ekimus, 5790, 1);
			}
			else if (event.equalsIgnoreCase("h1"))
			{
				hound1 = addSpawn(HOUND, -179281, 209138, -15496, 21663, false, 0);
				hound1.setIsNoRndWalk(true);
				broadcastCamera(hound1, 250, 120, 10, 0, 4000, 0, 0);
				startQuestTimer("h2", 3250, null, null);
				startQuestTimer("hAnim", 50, hound1, null);
				startQuestTimer("deleteNpc", 13500, hound1, null);
			}
			else if (event.equalsIgnoreCase("h2"))
			{
				hound2 = addSpawn(HOUND, -179787, 209136, -15496, 9346, false, 0);
				hound2.setIsNoRndWalk(true);
				broadcastCamera(hound2, 150, 80, 10, 0, 4000, 0, 0);
				startQuestTimer("hAnim", 50, hound2, null);
				startQuestTimer("deleteNpc", 11000, hound2, null);
			}
			else if (event.equalsIgnoreCase("hAnim"))
			{
				broadcastMSU(npc, 5791, 1);
			}
			else if (event.equalsIgnoreCase("deleteNpc"))
			{
				npc.deleteMe();
			}
			else if (event.equalsIgnoreCase("spawnHounds"))
			{
				realhound1 = addSpawn(HOUND, -178344, 209395, -15497, 27439, false, 0);
				realhound1.setIsRaidMinion(true);
				realhound1.setIsNoRndWalk(true);
				realhound1.setIsInvul(true);
				realhound2 = addSpawn(HOUND, -180695, 209387, -15497, 320, false, 0);
				realhound2.setIsRaidMinion(true);
				realhound2.setIsNoRndWalk(true);
				realhound2.setIsInvul(true);
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			newQuestState(player);
		}
		String htmltext = "";
		if (npc.getNpcId() == TEPIOS)
		{
			String voiceText = "";
			voiceText = "<html><body>Officer Tepios:<br>";
			voiceText += "Welcome to the Seed of Infinity. My name is Officer Tepios. My role is to build temporary shelters and guide newcomers around here.<br>";
			voiceText += "This is the place we call the <font color=LEVEL>Death Gate</font>. Many soldiers and unnamed adventurers have gone to their deaths through this entrance.<br>";
			voiceText += "However, we must continue to venture through it to face our enemies. It is our fate, I suppose. If you would help us, we will reward you on behalf of Admiral Keucereus.<br>";
			voiceText += "<a action=\"bypass -h Quest SeedOfInfinity enter\">Enter to Seed of Infinity.</a><br>";
			voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 1\">Ask about the Seed of Infinity.</a><br>";
			voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 2\">Ask about a cycle of the Seed Immortality.</a><br>";
			voiceText += "<a action=\"bypass -h Quest SeedOfInfinity 3\">Ask about Tumors and Soul Coffins.</a><br>";
			voiceText += "</body></html>";
			return voiceText;
		}
		return htmltext;
	}

	public String onKill (L2Npc npc, L2PcInstance killer, boolean isPet) 
	{
		if (npc.getNpcId() == EKIMUS)
		{
			realhound1.deleteMe();
			realhound2.deleteMe();
		}
		return null;
	}

	public static void main(String[] args)
	{
		new SeedOfInfinity(-1,qn,"instances");
		System.out.println("Instance: Seed of Infinity");
	}
}
