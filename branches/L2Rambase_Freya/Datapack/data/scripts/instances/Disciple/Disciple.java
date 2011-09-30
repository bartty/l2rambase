/**
 * @author L2Rambase Project
 * OpenTeamFree 14.09.2010, Based on PTS Freya.
 * TODO: Disciple - Lilith and Anakim Attack.
 * TODO: Disciple - Fix in sql rb's 32715,32716,32717,32718,32719,32720,32721
*/
package instances.Disciple;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.InstanceManager.InstanceWorld;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class Disciple extends Quest
{
	private class DiSWorld extends InstanceWorld
	{
		public  long[]  storeTime = {0,0}; 
		public DiSWorld()
		{
			InstanceManager.getInstance().super();
		}
	}

	private static final String qn = "Disciple";
	private static final int INSTANCEID = 112;

	private static final int PROMISE = 32585;
	private static final int LEON = 32587;
	private static final int DOOR = 17240111;
	private static final int GATEKEEPER = 32657;
	private static final int SEALDEVICE = 27384;
	private static final int EINHASAD_STRIKE = 8357;

	private class teleCoord {int instanceId; int x; int y; int z;}

	private void teleportplayer(L2PcInstance player, teleCoord teleto)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		return;
	}

	protected void exitInstance(L2PcInstance player, teleCoord tele)
	{
		player.setInstanceId(0);
		player.teleToLocation(tele.x, tele.y, tele.z);
	}

	protected int enterInstance(L2PcInstance player, String template, teleCoord teleto)
	{
		int instanceId = 0;
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			if (!(world instanceof DiSWorld))
			{
				player.sendPacket(new SystemMessage(SystemMessageId.ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER));
				return 0;
			}
			teleto.instanceId = world.instanceId;
			teleportplayer(player,teleto);
			return instanceId;
		}
		else
		{
			instanceId = InstanceManager.getInstance().createDynamicInstance(template);
			world = new DiSWorld();
			world.instanceId = instanceId;
			world.templateId = INSTANCEID;
			world.status = 0;
			((DiSWorld)world).storeTime[0] = System.currentTimeMillis();
			InstanceManager.getInstance().addWorld(world);
			_log.info("Disciple started " + template + " Instance: " + instanceId + " created by player: " + player.getName());
			teleto.instanceId = instanceId;
			teleportplayer(player,teleto);
			world.allowed.add(player.getObjectId());
			return instanceId;
		}
	}

	protected void openDoor(int doorId,int instanceId)
	{
		for (L2DoorInstance door : InstanceManager.getInstance().getInstance(instanceId).getDoors())
		{
			if (door.getDoorId() == doorId)
				door.openMe();
		}
	}

	public String onSkillSee(L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
	{
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		if (tmpworld instanceof DiSWorld)
		{
			for (L2Object obj : targets)
			{
				if (npc != obj)
					return null;
			}
			if (skill.getId() == EINHASAD_STRIKE && npc.getNpcId() == SEALDEVICE)
			{
				npc.doCast(SkillTable.getInstance().getInfo(5980, 1));
			}
		}
		return super.onSkillSee(npc,caster,skill,targets,isPet);
	}

	public String onTalk (L2Npc npc, L2PcInstance player)
	{
		int npcId = npc.getNpcId();
		QuestState st = player.getQuestState(qn);
		if (st == null)
		st = newQuestState(player);
		if (npcId == PROMISE)
		{
			teleCoord tele = new teleCoord();
			tele.x = -89559;      
			tele.y = 216030;
			tele.z = -7488;
			enterInstance(player, "Disciple.xml", tele);
		}
		else if (npcId == LEON)
		{
			InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
			world.allowed.remove(world.allowed.indexOf(player.getObjectId()));
			teleCoord tele = new teleCoord();
			tele.instanceId = 0;
			tele.x = 171782;
			tele.y = -17612;
			tele.z = -4901;
			exitInstance(player,tele);
		}
		else if (npcId == GATEKEEPER)
		{
			InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
			if (tmpworld instanceof DiSWorld)
			{
				DiSWorld world = (DiSWorld) tmpworld;
				{
					openDoor(DOOR,world.instanceId);
					for(int objId : world.allowed)
					{
						L2PcInstance pl = L2World.getInstance().getPlayer(objId);
						if (pl != null)
							pl.showQuestMovie(12);
					}
				}
			}
		}
		return "";
	
	}

	public Disciple(int questId, String name, String descr)
	{
		super(questId, name, descr);

		addStartNpc(PROMISE);
		addTalkId(PROMISE);
		addTalkId(LEON);
		addTalkId(GATEKEEPER);
		addSkillSeeId(SEALDEVICE);
	}

	public static void main(String[] args)
	{
		new Disciple(-1,qn,"instances");
	}
}