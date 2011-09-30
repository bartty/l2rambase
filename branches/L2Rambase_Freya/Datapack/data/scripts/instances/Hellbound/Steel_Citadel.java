package instances.Hellbound;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.InstanceManager.InstanceWorld;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;
import ai.group_template.L2AttackableAIScript;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.util.Rnd;

/**
 * @author L2Rambase Project
 */

public class Steel_Citadel extends L2AttackableAIScript
{

	private class SCWorld extends InstanceWorld
	{
		public int cleaned;
	    private List<L2PcInstance> _players = new FastList<L2PcInstance>();

		public SCWorld(Long time)
		{
			InstanceManager.getInstance().super();
		}
	}

	private static final String qn = "Steel_Citadel";
	private static final boolean debug = false;
	private static final boolean log = true;

	private boolean DemonPrinceLocked = false;
	private boolean RankuLocked = false;

	//Min level of hellbound to enter in Base Tower instance. Retail: 10
	private static final int BTLVL = 10;
	//Min level of hellbound to enter in Tower of Infinitum instance. Retail: 11
	private static final int TOILVL = 11;
	//Mon's IDs
	private static final int GUZEN = 22362;
	private static final int DEMON_PRINCE = 25540;
	private static final int RANKU = 25542;
	private static final int RANKU_SCAGEPOAT = 32305;
	private static final int EIDOLON = 25543; //Spawned after Scagepoat's death
	private static final int[] TOIMOBS = { 22373, 22374, 22375, 22376 };
	//NPC IDs
	private static final int KENDAL = 32301; //Spawned after Guzen's death
	private static final int JERIAN = 32302; //Tower of Infinitum enter npc
	private static final int TOMBSTONE = 32343; //Base Tower enter npc
	private static final int TPDPRINCE = 32374; //Spawned after Demons Prince death
	private static final int TPRANKU = 32375; //Spawned after Rankus death
	//Item Ids
	private static final int KEYOTEE = 9714; //Key of the Evil Eye checks to enter in Base tower
	//Door Ids
	private static final int CLOSEDGATE = 20260004; //Opened after Guzen's death
	//Spell Ids
	private static final int DEMONBLOOD = 2357; //Fiery Demon Blood checks to enter in ToI enter


	public List<L2Npc> Bosses = new FastList<L2Npc>();
	public List<L2Npc> Ranku_npcs = new FastList<L2Npc>();
	private static long _lastAttack;
	private static final long _checkInterval = 600000; //time between ckecks attacks DP and Ranku

	private class teleCoord
	{
		int instanceId;
		int x;
		int y;
		int z;
	}

	//1st stage
	private static int[][] FIRST_STAGE = {
			{ 22373, -22764, 277708, -15046, 55979 },
            { 22373, -22737, 278373, -15046, 56606 },
            { 22373, -22861, 279126, -15046, 56114 },
            { 22373, -22705, 279227, -15046, 5992 },
            { 22373, -22853, 279854, -15046, 52423 },
            { 22373, -22492, 279926, -15043, 52265 } };
	//2nd stage
	private static int[][] SECOND_STAGE = {
			{ 22373, -22723, 277714, -13382, 56189 },
			{ 22373, -22738, 278378, -13382, 58985 },
			{ 22373, -22800, 279043, -13382, 58680 },
			{ 22373, -22696, 279135, -13382, 57697 },
			{ 22373, -22884, 279865, -13382, 56553 },
			{ 22373, -22513, 279970, -13382, 49343 },
			{ 22373, -21829, 280077, -13382, 46093 },
			{ 22373, -21668, 279867, -13382, 32678 },
			{ 22373, -21664, 278069, -13382, 37562 },
			{ 22373, -21788, 278975, -13382, 46871 },
			{ 22373, -21581, 279079, -13382, 44955 } };
	//3rd stage
	private static int[][] THIRD_STAGE = {
			{ 22373, -21720, 277701, -11654, 45034 },
			{ 22373, -21580, 278191, -11650, 44136 },
			{ 22373, -21817, 279103, -11654, 24470 },
			{ 22373, -21502, 279045, -11654, 44190 },
			{ 22373, -21706, 279812, -11654, 39960 },
			{ 22373, -21966, 280075, -11651, 33558 },
			{ 22374, -22751, 277735, -11654, 59325 },
			{ 22374, -22838, 278060, -11654, 59693 },
			{ 22374, -22871, 279046, -11654, 58261 },
			{ 22374, -22688, 279241, -11654, 49686 },
			{ 22374, -22743, 279862, -11654, 3463 },
			{ 22374, -22458, 279951, -11651, 50611 } };
	//4th stage
	private static int[][] FOURTH_STAGE = {
			{ 22373, -22881, 277685, -9926, 64841 },
			{ 22373, -22743, 278370, -9926, 55142 },
			{ 22373, -22855, 279095, -9926, 62244 },
			{ 22373, -22474, 279965, -9923, 49895 },
			{ 22373, -21485, 277736, -9926, 40430 },
			{ 22373, -21500, 278387, -9926, 45331 },
			{ 22373, -21580, 279053, -9926, 43936 },
			{ 22373, -22042, 280051, -9925, 49322 },
			{ 22374, -22865, 278089, -9926, 60513 },
			{ 22374, -22749, 279889, -9926, 47920 },
			{ 22374, -21701, 277637, -9926, 43460 },
			{ 22374, -21719, 278364, -9926, 41405 },
			{ 22374, -21785, 279071, -9926, 43346 },
			{ 22374, -21768, 279990, -9929, 44041 } };
	//6th stage
	private static int[][] SIXTH_STAGE = {
			{ 22375, -19563, 277624, -8262, 52507 },
			{ 22375, -19736, 278154, -8262, 60824 },
			{ 22375, -19715, 279098, -8262, 49364 },
			{ 22375, -19478, 279134, -8262, 51999 },
			{ 22375, -19599, 279810, -8262, 54496 },
			{ 22375, -19274, 279948, -8259, 55485 } };
	//7th stage
	private static int[][] SEVENTH_STAGE = {
			{ 22375, -19511, 277652, -9926, 56684 },
			{ 22375, -19755, 278067, -9926, 59603 },
			{ 22375, -19665, 279039, -9926, 56838 },
			{ 22375, -19495, 279149, -9926, 52156 },
			{ 22375, -19616, 279797, -9926, 54303 },
			{ 22375, -19265, 279914, -9923, 58498 },
			{ 22376, -18520, 277587, -9926, 43315 },
			{ 22376, -18309, 278153, -9926, 40308 },
			{ 22376, -18597, 279019, -9926, 41400 },
			{ 22376, -18257, 279150, -9926, 29960 },
			{ 22376, -18666, 279698, -9926, 41279 },
			{ 22376, -18273, 279942, -9926, 41397 } };
	//8th stage
	private static int[][] EIGHTH_STAGE = {
			{ 22375, -19533, 277526, -11654, 58733 },
			{ 22375, -19682, 278770, -11654, 58358 },
			{ 22375, -19601, 279744, -11654, 53191 },
			{ 22375, -18465, 277656, -11654, 40064 },
			{ 22375, -18511, 278754, -11654, 36194 },
			{ 22375, -18484, 279654, -11654, 37861 },
			{ 22376, -19682, 277995, -11654, 57947 },
			{ 22376, -19541, 279071, -11654, 55197 },
			{ 22376, -19207, 279826, -11653, 50874 },
			{ 22376, -18378, 278075, -11654, 40014 },
			{ 22376, -18513, 279118, -11654, 36828 },
			{ 22376, -18648, 280064, -11654, 44380 } };
	//9th stage
	private static int[][] NINETH_STAGE = {
			{ 22375, -19561, 277577, -13382, 57883 },
			{ 22375, -19530, 278731, -13382, 53001 },
			{ 22375, -19609, 279765, -13382, 53097 },
			{ 22375, -18384, 277631, -13382, 45618 },
			{ 22375, -18538, 279087, -13382, 31728 },
			{ 22375, -18474, 279779, -13382, 38880 },
			{ 22376, -19797, 277765, -13382, 57971 },
			{ 22376, -19615, 278044, -13382, 51418 },
			{ 22376, -19790, 278539, -13382, 54698 },
			{ 22376, -19783, 279223, -13382, 46038 },
			{ 22376, -19283, 279843, -13380, 51304 },
			{ 22376, -18515, 277995, -13382, 31654 },
			{ 22376, -18248, 278411, -13382, 8191 },
			{ 22376, -18564, 278689, -13382, 27497 },
			{ 22376, -18756, 279914, -13379, 45725 } };

	protected void openDoor(int doorId, int instanceId)
	{
		for (L2DoorInstance door : InstanceManager.getInstance().getInstance(instanceId).getDoors())
			if (door.getDoorId() == doorId)
				door.openMe();
	}

    //Checks before enter in Base Tower
	private boolean checkConditions(L2PcInstance player)
	{
		if (debug)
			return true;
		L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(SystemMessageId.NOT_IN_PARTY_CANT_ENTER);
			return false;
		}
		if (party.getLeader() != player)
		{
			player.sendPacket(SystemMessageId.ONLY_PARTY_LEADER_CAN_ENTER);
			return false;
		}
		L2ItemInstance item = party.getLeader().getInventory().getItemByItemId(KEYOTEE);
		if (item == null)
		{
			player.sendMessage("Item requirement is not sufficient.");
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers())
		{
			if (partyMember.getLevel() < 78)
			{
				SystemMessage sm = new SystemMessage(2097);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				SystemMessage sm = new SystemMessage(2096);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
		}
		return true;
	}

    //Checks before enter in Tower of Infinitum
	private boolean checkTOIConditions(L2PcInstance player)
	{
		if (debug)
			return true;
		L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(new SystemMessage(2101));
			return false;
		}
		if (party.getLeader() != player)
		{
			player.sendPacket(new SystemMessage(2185));
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers())
		{
			if (partyMember.getFirstEffect(DEMONBLOOD) == null)
			{
				SystemMessage sm = new SystemMessage(2098);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				SystemMessage sm = new SystemMessage(2096);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
		}
		return true;
	}

    protected void run1stStage(SCWorld world)
    {
		world.cleaned = 5;
		world.status = 1;

		for (int[] spawn : FIRST_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run2ndStage(SCWorld world)
	{
		world.cleaned = 11;
		world.status = 2;

		for (int[] spawn : SECOND_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run3rdStage(SCWorld world)
	{
		world.cleaned = 12;
		world.status = 3;

		for (int[] spawn : THIRD_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run4thStage(SCWorld world)
	{
		world.cleaned = 14;
		world.status = 4;

		for (int[] spawn : FOURTH_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run5thStage(SCWorld world)
	{
		world.status = 5;

		Bosses.add(addSpawn(25540, -22645, 279629, -8262, 0, false, 0, false, world.instanceId));
	}

	protected void run6thStage(SCWorld world)
	{
		world.cleaned = 5;
		world.status = 6;

		for (int[] spawn : SIXTH_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run7thStage(SCWorld world)
	{
		world.cleaned = 12;
		world.status = 7;

		for (int[] spawn : SEVENTH_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run8thStage(SCWorld world)
	{
		world.cleaned = 12;
		world.status = 8;

		for (int[] spawn : EIGHTH_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run9thStage(SCWorld world)
	{
		world.cleaned = 15;
		world.status = 9;

		for (int[] spawn : NINETH_STAGE)
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.instanceId);
	}

	protected void run10thStage(SCWorld world)
	{
		world.status = 10;

		Bosses.add(addSpawn(25542, -19438, 279377, -15046, 0, false, 0, false, world.instanceId));
	}

	private void teleportplayer(L2PcInstance player, teleCoord teleto)
	{
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		L2Summon pet = player.getPet();
		if (pet != null)
		{
			pet.setInstanceId(teleto.instanceId);
			pet.teleToLocation(teleto.x, teleto.y, teleto.z);
		}
		return;
	}

	protected int enterInstance(L2PcInstance player, String template, teleCoord teleto)
	{
		//check for existing instances for this player
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		//existing instance
		if (world != null)
		{
			if (!(world instanceof SCWorld))
			{
				player.sendPacket(SystemMessageId.ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER);
				return 0;
			}
			teleto.instanceId = world.instanceId;
			teleportplayer(player, teleto);
			return world.instanceId;
		}
		else
		{
			if (!checkConditions(player))
				return 0;
			L2Party party = player.getParty();
			int instanceId = InstanceManager.getInstance().createDynamicInstance(template);
			world = new SCWorld(System.currentTimeMillis());
			world.instanceId = instanceId;
			InstanceManager.getInstance().addWorld(world);
			world.status = 0;
			//teleport players
			teleto.instanceId = instanceId;
			if (party == null)
			{
				//this can happen only if debug is true
				teleportplayer(player, teleto);
				world.allowed.add(player.getObjectId());
			}
			else
			{
				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					teleportplayer(partyMember, teleto);
					world.allowed.add(partyMember.getObjectId());
				}
			}
			return instanceId;
		}
	}

	protected void exitInstance(L2PcInstance player, teleCoord tele)
	{
		player.setInstanceId(0);
		player.teleToLocation(tele.x, tele.y, tele.z);
                player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
	}

	public Steel_Citadel(int id,String name,String descr)
	{
		super(id, name, descr);
		addEventId(TOMBSTONE, Quest.QuestEventType.QUEST_START);
		addEventId(TOMBSTONE, Quest.QuestEventType.ON_TALK);
		addEventId(KENDAL, Quest.QuestEventType.ON_FIRST_TALK);
		addEventId(JERIAN, Quest.QuestEventType.ON_TALK);
		addEventId(TPDPRINCE, Quest.QuestEventType.ON_TALK);
		addEventId(TPRANKU, Quest.QuestEventType.ON_TALK);
		addEventId(RANKU_SCAGEPOAT, Quest.QuestEventType.ON_SPAWN);
		addEventId(EIDOLON, Quest.QuestEventType.ON_SPAWN);
		addEventId(GUZEN, Quest.QuestEventType.ON_KILL);
		addEventId(DEMON_PRINCE, Quest.QuestEventType.ON_KILL);
		addEventId(RANKU, Quest.QuestEventType.ON_KILL);
		for (int mob : TOIMOBS)
			addEventId(mob, Quest.QuestEventType.ON_KILL);
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("unlock_prince"))
		   	DemonPrinceLocked = false;
		if (event.equalsIgnoreCase("unlock_ranku"))
		    DemonPrinceLocked = false;
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(player.getInstanceId());
		SCWorld world;
		if (tmpworld instanceof SCWorld)
		{
			world = (SCWorld) tmpworld;
		    if (event.equalsIgnoreCase("prince_despawn"))
		    {
			    long time = System.currentTimeMillis() - _lastAttack;
			    if (time >= _checkInterval)
			    {
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -9823); //tele to 4th stage
				    }
				    run4thStage(world);
				    if (!Bosses.isEmpty())
				    {
				        for (L2Npc boss : Bosses)
						    boss.deleteMe();
				    }
				    Bosses.clear();
				    DemonPrinceLocked = false;
				    cancelQuestTimers("prince_despawn");
			    }
		    }
		    else if (event.equalsIgnoreCase("ranku_despawn"))
		    {
			    long time = System.currentTimeMillis() - _lastAttack;
			    if (time >= _checkInterval)
			    {
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -13381); //tele to 9th stage
				    }
				    run9thStage(world);
				    if (!Bosses.isEmpty())
				    {
				        for (L2Npc boss : Bosses)
						    boss.deleteMe();
				    }
				    Bosses.clear();
				    if (!Ranku_npcs.isEmpty())
				    {
				        for (L2Npc npcs : Ranku_npcs)
						    npcs.deleteMe();
				    }
				    Ranku_npcs.clear();
				    RankuLocked = false;
				    cancelQuestTimers("ranku_despawn");
				    cancelQuestTimers("time_to_poison");
				    cancelQuestTimers("time_to_more_eidolon");
			    }
		    }
		}
		return "";
	}

    public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		int npcId = npc.getNpcId();
        if (npcId == KENDAL)
		{
			if (player.getClassId().getId() == 91 //only Hell Knights and Soultalkers can unders. him
			|| player.getClassId().getId() == 95)
			    return "<html><head>Kendal:<br>It was really horrible. They were tortured, abused, killed. That's how it was. Beleth is evil, and so is Darion. Hmph. Go teach them a lesson!</body></html>";
            npc.showChatWindow(player); //for other classes return normal text
		}
		return "";
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		int npcId = npc.getNpcId();
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		int hellboundLevel = HellboundManager.getInstance().getLevel();
		if (npcId == TOMBSTONE)
		{
		    if (hellboundLevel < BTLVL)
		        return "<html><body>Moonlight Tombstone:<br> Seems the level of hellbound is too low.</body></html>";
			teleCoord tele = new teleCoord();
			tele.x = 16285;
			tele.y = 283850;
			tele.z = -9703;
			enterInstance(player, "Steel_Citadel.xml", tele);
			return "";
		}
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		SCWorld world;
		if (tmpworld instanceof SCWorld)
		{
		    world = (SCWorld) tmpworld;
		    if (npcId == JERIAN)
		    {
		        if (hellboundLevel < TOILVL)
		            return "<html><body>Jerian:<br> Seems the level of hellbound is too low.</body></html>";
			    if (!checkTOIConditions(player))
                    return "";
			    run1stStage(world);
                world._players.clear();
			    L2Party party = player.getParty();
		        for (L2PcInstance partyMember : party.getPartyMembers())
		        {
		            partyMember.teleToLocation(-22208, 277155, -15045); //1st stage
                    world._players.add(partyMember);
				}
		        int[] returnLoc = { 13178, 281912, -7552 };
				InstanceManager.getInstance().getInstance(world.instanceId).setSpawnLoc(returnLoc); //set ret. port to Jerian
		    }
		    else if (npcId == TPDPRINCE)
		    {
                for (L2PcInstance p : world._players)
                {
                    if (p.isOnline() == 1 && p.getInstanceId() != 0)
                        p.teleToLocation(-19019, 277160, -8261); //6th stage
				}
				run6thStage(world);
		    }
		    else if (npcId == TPRANKU)
		    {
                for (L2PcInstance p : world._players)
                {
                    if (p.isOnline() == 1 && p.getInstanceId() != 0)
                        p.teleToLocation(13178, 281912, -7552);
				}
				run4thStage(world);
		    }
		}
		return "";
	}

	public String onSpawn(L2Npc npc)
	{
		int npcId = npc.getNpcId();
		if (npcId == RANKU_SCAGEPOAT)
		    Ranku_npcs.add(npc);
		else if (npcId == EIDOLON)
		    Ranku_npcs.add(npc);
		return "";
	}

	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
	{
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		SCWorld world;
		if (tmpworld instanceof SCWorld)
		{
			world = (SCWorld) tmpworld;
			if (world.status == 5 && npc.getNpcId() == DEMON_PRINCE)
                _lastAttack = System.currentTimeMillis();
			else if (world.status == 10 && npc.getNpcId() == RANKU)
                _lastAttack = System.currentTimeMillis();
		}
		return "";
	}

	public String onKill (L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		SCWorld world;
		if (tmpworld instanceof SCWorld)
		{
		    world = (SCWorld) tmpworld;
			if (npc.getNpcId() == GUZEN)
		    {
				addSpawn(KENDAL, 17038, 278654, -9704, 13270, false, 0, false, world.instanceId);
                openDoor(CLOSEDGATE, world.instanceId);
			}
			if (world.status == 1 && world.cleaned == 0)
			{
                for (L2PcInstance p : world._players)
                {
                    if (p.isOnline() == 1 && p.getInstanceId() != 0)
                        p.teleToLocation(-22208, 277155, -13375); //2nd stage
				}
			    run2ndStage(world);
			}
			if (world.status == 2 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -11705); //3rd stage
				    }
				    run3rdStage(world);
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -15045); //1st stage
				    }
				    run1stStage(world);
				}
			}
			if (world.status == 3 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -9823); //4th stage
				    }
				    run4thStage(world);
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -13375); //2nd stage
				    }
			        run2ndStage(world);
				}
			}
			if (world.status == 4 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
					if (!DemonPrinceLocked)
					{
                        for (L2PcInstance p : world._players)
                        {
                            if (p.isOnline() == 1 && p.getInstanceId() != 0)
                                p.teleToLocation(-22208, 277155, -8264); //5th stage
				        }
				        startQuestTimer("prince_despawn", _checkInterval, null, killer, true);
				        _lastAttack = System.currentTimeMillis();
                        DemonPrinceLocked = true;
				        run5thStage(world);
					}
					else //if Demon Prince already attacked by other parties or not respawn yet
					{
                        for (L2PcInstance p : world._players)
                        {
                            if (p.isOnline() == 1 && p.getInstanceId() != 0)
                                p.teleToLocation(-22208, 277155, -8264); //tele to 5th stage
				        }
				        addSpawn(TPDPRINCE, -22645, 279629, -8262, 0, false, 0, false, world.instanceId); //and spawn Teleportation Cubic
					}
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-22208, 277155, -11705); //3rd stage
				    }
				    run3rdStage(world);
				}
			}
			if (world.status == 5)
			{
				if (npc.getNpcId() == DEMON_PRINCE)
				{
				    if (log)
		                _log.info("Demon Prince defeated in instance: " + killer.getInstanceId() + ", last hit by player: " + killer.getName());
				    addSpawn(TPDPRINCE, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0, false, world.instanceId);
				    long resptime = Rnd.get(57600000,144000000);
				    cancelQuestTimers("prince_despawn");
				    startQuestTimer("unlock_prince", resptime, null, null);
				    DemonPrinceLocked = true;
				}
			}
			if (world.status == 6 && world.cleaned == 0)
			{
                for (L2PcInstance p : world._players)
                {
                    if (p.isOnline() == 1 && p.getInstanceId() != 0)
                        p.teleToLocation(-19019, 277160, -9925); //7th stage
				}
				run7thStage(world);
			}
			if (world.status == 7 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -11656); //8th stage
				    }
				    run8thStage(world);
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -8261); //6th stage
				    }
				    run6thStage(world);
				}
			}
			if (world.status == 8 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -13381); //9th stage
				    }
				    run9thStage(world);
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -9925); //7th stage
				    }
				    run7thStage(world);
				}
			}
			if (world.status == 9 && world.cleaned == 0)
			{
				if (Rnd.get(100) <= 60)
				{
					if (!RankuLocked)
					{
                        for (L2PcInstance p : world._players)
                        {
                            if (p.isOnline() == 1 && p.getInstanceId() != 0)
                                p.teleToLocation(-19019, 277160, -15045); //10th stage
				        }
                        startQuestTimer("ranku_despawn", _checkInterval, null, killer, true);
				        _lastAttack = System.currentTimeMillis();
                        RankuLocked = true;
				        run10thStage(world);
					}
					else //if Ranku already attacked by other parties or not respawn yet
					{
                        for (L2PcInstance p : world._players)
                        {
                            if (p.isOnline() == 1 && p.getInstanceId() != 0)
                                p.teleToLocation(-19019, 277160, -15045); //tele 10th stage
				        }
				        addSpawn(TPRANKU, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0, false, world.instanceId); //and spawn Teleportation Cubic
					}
				}
				else
				{
                    for (L2PcInstance p : world._players)
                    {
                        if (p.isOnline() == 1 && p.getInstanceId() != 0)
                            p.teleToLocation(-19019, 277160, -11656); //8th stage
				    }
				    run8thStage(world);
				}
			}
			if (world.status == 10)
			{
				if (npc.getNpcId() == RANKU)
				{
			        if (log)
			            _log.info("Ranku defeated in instance: " + killer.getInstanceId() + ", last hit by player: " + killer.getName());
				    addSpawn(TPRANKU, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0, false, world.instanceId);
				    long resptime = Rnd.get(57600000,144000000);
				    cancelQuestTimers("ranku_despawn");
				    startQuestTimer("unlock_ranku", resptime, null, null);
				    RankuLocked = true;
			    }
			}
		    world.cleaned --;
		}
		return "";
	}

	public static void main(String[] args)
	{
		new Steel_Citadel(-1,qn,"Instances");
		System.out.println("Instance: Steel Citadel: Base Tower and Tower of Infinity loaded.");
	}
}
