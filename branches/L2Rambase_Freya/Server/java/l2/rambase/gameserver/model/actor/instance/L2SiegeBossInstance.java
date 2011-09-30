/**
 * @author L2Rambase Project
 * FIXME: L2SiegeBossInstance - Script for Raid for own Outside ClanHall
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.instancemanager.clanhallsiege.DevastatedCastleSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfResistanceSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfTheDeadSiege;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public final class L2SiegeBossInstance extends L2MonsterInstance
{
	private static final int RAIDBOSS_MAINTENANCE_INTERVAL = 30000;

	public L2SiegeBossInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
	}

	@Override
	protected int getMaintenanceInterval()
	{
		return RAIDBOSS_MAINTENANCE_INTERVAL;
	}

	/**
	 * Spawn all minions at a regular interval Also if boss is too far from home location at the time of this check, teleport it home
	 */
	
	protected void manageMinions()
	{
		_minionList.spawnMinions();
		_maintenanceTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new Runnable() {
			public void run()
			{
				// teleport raid boss home if it's too far from home
				// location
				L2Spawn bossSpawn = getSpawn();
				if (!isInsideRadius(bossSpawn.getLocx(), bossSpawn.getLocy(), bossSpawn.getLocz(), 5000, true, false))
				{
					teleToLocation(bossSpawn.getLocx(), bossSpawn.getLocy(), bossSpawn.getLocz(), true);
					healFull(); // prevents minor exploiting with it
				}
				_minionList.maintainMinions();
			}
		}, 60000, getMaintenanceInterval() + Rnd.get(5000));
	}

	/**
	 * Reduce the current HP of the L2Attackable, update its _aggroList and launch the doDie Task if necessary.<BR>
	 * <BR>
	 */
	@Override
	public void reduceCurrentHp(double damage, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
	{
		super.reduceCurrentHp(damage, attacker, awake, isDOT, skill);
		if (this.getNpcId() == 35368)
		{
			if (attacker instanceof L2PcInstance && ((L2PcInstance) attacker).getClan() != null)
				FortressOfResistanceSiege.getInstance().addSiegeDamage(((L2PcInstance) attacker).getClan(), damage);
		}
		else
		{
			if (this.getNpcId() == 35410)
			{
				if (attacker instanceof L2PcInstance && ((L2PcInstance) attacker).getClan() != null)
					DevastatedCastleSiege.getInstance().addSiegeDamage(((L2PcInstance) attacker).getClan(), damage);
			}
		}
	}

	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
			return false;
		if (getNpcId() == 35368 && FortressOfResistanceSiege.getInstance().getIsInProgress()) // Bloody Lord Nurka
			FortressOfResistanceSiege.getInstance().endSiege(true);
		else if (getNpcId() == 35410 && DevastatedCastleSiege.getInstance().getIsInProgress()) // Gustav
			DevastatedCastleSiege.getInstance().endSiege(true);
		else if (getNpcId() == 35629 && FortressOfTheDeadSiege.getInstance().getIsInProgress()) // Lidia von Hellmann
			FortressOfTheDeadSiege.getInstance().endSiege(true);
		return true;
	}

	public void healFull()
	{
		super.setCurrentHp(super.getMaxHp());
		super.setCurrentMp(super.getMaxMp());
	}
}
