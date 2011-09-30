/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public class L2DungeonBossInstance extends L2MonsterInstance
{
	private static final int DUNGEON_BOSS_MAINTENANCE_INTERVAL = 30000; // 30 sec

	/**
	 * Constructor of L2DungeonBossInstance (use L2Character and L2NpcInstance constructor).<BR><BR>
	 *
	 * <B><U> Actions</U> :</B><BR><BR>
	 * <li>Call the L2Character constructor to set the _template of the L2RaidBossInstance (copy skills from template to object and link _calculators to NPC_STD_CALCULATOR) </li>
	 * <li>Set the name of the L2DungeonBossInstance</li>
	 * <li>Create a RandomAnimation Task that will be launched after the calculated delay if the server allow it </li><BR><BR>
	 *
	 * @param objectId Identifier of the object to initialized
	 * @param L2NpcTemplate Template to apply to the NPC
	 */
	public L2DungeonBossInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2DungeonBossInstance);
		setIsRaid(false);
	}

	@Override
	public void onSpawn()
	{
		setIsNoRndWalk(true);
		super.onSpawn();
	}

	@Override
	protected int getMaintenanceInterval()
	{
		return DUNGEON_BOSS_MAINTENANCE_INTERVAL;
	}

	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
			return false;

		L2PcInstance player = null;
		if (killer instanceof L2PcInstance)
			player = (L2PcInstance) killer;
		else if (killer instanceof L2Summon)
			player = ((L2Summon) killer).getOwner();

		if (player != null)
		{
			if (player.getParty() != null)
			{
				for (L2PcInstance member : player.getParty().getPartyMembers())
				{
					member.sendMessage("Congratulations!!");
					member.sendMessage("You killed Dungeon Raid.");
				}				
			}
			else
			{
				player.sendMessage("Congratulations!!");
				player.sendMessage("You killed Dungeon Raid.");
			}
			
		}
		return true;
	}

	/**
	 * Spawn all minions at a regular interval Also if boss is too far from home
	 * location at the time of this check, teleport it home
	 * 
	 */
	@Override
	protected void startMaintenanceTask()
	{
		if (_minionList != null)
			_minionList.spawnMinions();

		_maintenanceTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new Runnable() {
			public void run()
			{
				if (_minionList != null)
					_minionList.maintainMinions();
			}
		}, 60000, getMaintenanceInterval()+Rnd.get(5000));
	}	

	/**
     * Reduce the current HP of the L2Attackable, update its _aggroList and launch the doDie Task if necessary.<BR><BR>
     *
     */
	@Override
    public void reduceCurrentHp(double damage, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
    {
    	super.reduceCurrentHp(damage, attacker, awake, isDOT, skill);
    }

	@Override
	public float getVitalityPoints(int damage)
	{
		return - super.getVitalityPoints(damage) / 100;
	}

	@Override
	public boolean useVitalityRate()
	{
		return false;
	}
}
