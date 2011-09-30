/*
 * L2Rambase Project Project
 */
package ai.individual;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.GameTimeController;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.GrandBossManager;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.PlaySound;
import l2.rambase.gameserver.templates.StatsSet;
import l2.rambase.util.Rnd;

import ai.group_template.L2AttackableAIScript;


public class Zaken extends L2AttackableAIScript
{
	protected static final Logger log = Logger.getLogger(Zaken.class.getName());

	private static final int ZAKEN = 29022;
	private static final int ZAKEN_DAY = 29176;
	
	
	//ZAKEN Status Tracking :
	private static final byte ALIVE = 0;
	private static final byte DEAD = 1;

	public Zaken(int questId, String name, String descr)
	{
		super(questId, name, descr);

		int[] mobs =
		{
				ZAKEN_DAY, ZAKEN
		};
		registerMobs(mobs);

		StatsSet info = GrandBossManager.getInstance().getStatsSet(ZAKEN);
		int status = GrandBossManager.getInstance().getBossStatus(ZAKEN);
		if (status == DEAD)
		{
			// load the unlock date and time for zaken from DB
			long temp = info.getLong("respawn_time") - System.currentTimeMillis();
			// if zaken is locked until a certain time, mark it so and start the unlock timer
			// the unlock time has not yet expired.
			if (temp > 0)
				startQuestTimer("zaken_unlock", temp, null, null);
			else
			{
				GrandBossManager.getInstance().setBossStatus(ZAKEN, ALIVE);
			}
		}
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		int status = GrandBossManager.getInstance().getBossStatus(ZAKEN);
		if (status == DEAD && !event.equalsIgnoreCase("zaken_unlock"))
			return super.onAdvEvent(event, npc, player);

		if (event.equalsIgnoreCase("zaken_unlock"))
		{
			GrandBossManager.getInstance().setBossStatus(ZAKEN, ALIVE);
		}
		return super.onAdvEvent(event, npc, player);
	}

	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
	{
		int npcId = npc.getNpcId();
		if (npcId == ZAKEN || npcId == ZAKEN_DAY)
		{
			if (attacker.getMountType() == 1)
			{
				int sk_4258 = 0;
				L2Effect[] effects = attacker.getAllEffects();
				if (effects != null && effects.length != 0)
				{
					for (L2Effect e : effects)
					{
						if (e.getSkill().getId() == 4258)
						{
							sk_4258 = 1;
						}
					}
				}
				if (sk_4258 == 0)
				{
					npc.setTarget(attacker);
					npc.doCast(SkillTable.getInstance().getInfo(4258, 1));
				}
			}
			L2Character originalAttacker = isPet ? attacker.getPet() : attacker;
			int hate = (int) (((damage / npc.getMaxHp()) / 0.05) * 20000);
			((L2Attackable) npc).addDamageHate(originalAttacker, 0, hate);
			if (Rnd.get(10) < 1)
			{
				int i0 = Rnd.get((15 * 15));
				if (i0 < 4)
				{
					npc.setTarget(attacker);
					npc.doCast(SkillTable.getInstance().getInfo(4219, 1));
				}
				else if (i0 < 8)
				{
					npc.setTarget(attacker);
					npc.doCast(SkillTable.getInstance().getInfo(4218, 1));
				}
				else if (i0 < 15)
				{
					for (L2Character character : npc.getKnownList().getKnownCharactersInRadius(100))
					{
						if (character != attacker)
							continue;
						if (attacker != ((L2Attackable) npc).getMostHated())
						{
							npc.setTarget(attacker);
							npc.doCast(SkillTable.getInstance().getInfo(4221, 1));
						}
					}
				}
				if (Rnd.get(2) < 1)
				{
					if (attacker == ((L2Attackable) npc).getMostHated())
					{
						npc.setTarget(attacker);
						npc.doCast(SkillTable.getInstance().getInfo(4220, 1));
					}
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isPet);
	}

	public String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		int npcId = npc.getNpcId();
		if (npcId == ZAKEN || npcId == ZAKEN_DAY)
		{
			npc.broadcastPacket(new PlaySound(1, "BS02_D", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
			GrandBossManager.getInstance().setBossStatus(ZAKEN, DEAD);
			//time is 36hour	+/- 17hour
			long respawnTime = (long) Config.Interval_Of_Zaken_Spawn + Rnd.get(Config.Random_Of_Zaken_Spawn);
			startQuestTimer("zaken_unlock", respawnTime, null, null);
			// also save the respawn time so that the info is maintained past reboots
			StatsSet info = GrandBossManager.getInstance().getStatsSet(ZAKEN);
			info.set("respawn_time", System.currentTimeMillis() + respawnTime);
			GrandBossManager.getInstance().setStatsSet(ZAKEN, info);
		}
		return super.onKill(npc, killer, isPet);
	}

	public int getTimeHour()
	{
		return (GameTimeController.getInstance().getGameTime() / 60) % 24;
	}

	public static void main(String[] args)
	{
		// now call the constructor (starts up the ai)
		new Zaken(-1, "zaken", "ai");
	}
}