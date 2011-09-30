/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.instancemanager.RaidBossPointsManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.entity.Hero;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public final class L2GrandBossInstance extends L2MonsterInstance
{
	private static final int BOSS_MAINTENANCE_INTERVAL = 10000;
 
	/**
	 * Constructor for L2GrandBossInstance. This represent all grandbosses.
	 * 
	 * @param objectId ID of the instance
	 * @param template L2NpcTemplate of the instance
	 */
	public L2GrandBossInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2GrandBossInstance);
		setIsRaid(true);
	}
	
	@Override
	protected int getMaintenanceInterval() { return BOSS_MAINTENANCE_INTERVAL; }
	
	@Override
	public void onSpawn()
	{
		setIsNoRndWalk(true);
		if (getNpcId() == 29020 || getNpcId() == 29028) // baium and valakas are all the time in passive mode, theirs attack AI handled in AI scripts
			super.disableCoreAI(true);
		super.onSpawn();
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.model.actor.instance.L2MonsterInstance#doDie(l2.rambase.gameserver.model.actor.L2Character)
	 */
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
			broadcastPacket(new SystemMessage(SystemMessageId.RAID_WAS_SUCCESSFUL));
			if (player.getParty() != null)
			{
				for (L2PcInstance member : player.getParty().getPartyMembers())
				{
					RaidBossPointsManager.addPoints(member, getNpcId(), (getLevel() / 2) + Rnd.get(-5, 5));
					if(member.isNoble())
						Hero.getInstance().setRBkilled(member.getObjectId(), getNpcId());
				}
			}
			else
			{
				RaidBossPointsManager.addPoints(player, getNpcId(), (getLevel() / 2) + Rnd.get(-5, 5));
				if(player.isNoble())
					Hero.getInstance().setRBkilled(player.getObjectId(), getNpcId());
			}
		}
		return true;
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
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.model.actor.L2Character#giveRaidCurse()
	 */
	@Override
	public boolean giveRaidCurse()
	{
		return getLevel() < 74;
	}
}
