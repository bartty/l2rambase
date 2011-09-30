/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import java.util.logging.Level;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.FortSiegeManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2SiegeSummonInstance;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.model.zone.L2SpawnZone;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

import javolution.util.FastList;


public class L2FortZone extends L2SpawnZone
{
	private int _fortId;
	private Fort _fort = null;
	private boolean _isActiveSiege = false;
	private static final int DISMOUNT_DELAY = 5;
	
	public L2FortZone(int id)
	{
		super(id);
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("fortId"))
		{
			_fortId = Integer.parseInt(value);
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_FORT, true);
		if (getFort() != null && _isActiveSiege)
		{
			character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			
			if (character instanceof L2PcInstance)
			{
				((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
				if (((L2PcInstance) character).isRegisteredOnThisSiegeField(getFort().getFortId()))
				{
					((L2PcInstance) character).startFameTask(Config.FORTRESS_ZONE_FAME_TASK_FREQUENCY * 1000, Config.FORTRESS_ZONE_FAME_AQUIRE_POINTS);
					((L2PcInstance) character).setIsInSiege(true);
				}
				if (!Config.ALLOW_WYVERN_DURING_SIEGE && ((L2PcInstance) character).getMountType() == 2)
				{
					character.sendPacket(new SystemMessage(SystemMessageId.AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN));
					((L2PcInstance) character).enteredNoLanding(DISMOUNT_DELAY);
				}
			}
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_FORT, false);
		character.setInsideZone(L2Character.ZONE_PVP, false);
		character.setInsideZone(L2Character.ZONE_SIEGE, false);
		character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, false);
		if (getFort() != null && _isActiveSiege)
		{
			if (character instanceof L2PcInstance)
			{
				((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));

				if (((L2PcInstance) character).getMountType() == 2)
				{
					((L2PcInstance) character).exitedNoLanding();
				}
				// Set pvp flag
				if (((L2PcInstance) character).getPvpFlag() == 0)
					((L2PcInstance) character).startPvPFlag();
			}
		}
		if (character instanceof L2SiegeSummonInstance)
		{
			((L2SiegeSummonInstance) character).unSummon(((L2SiegeSummonInstance) character).getOwner());
		}
		if (character instanceof L2PcInstance)
		{
			L2PcInstance activeChar = ((L2PcInstance)character);
			
			activeChar.stopFameTask();
			activeChar.setIsInSiege(false);
			
			if (activeChar.getInventory().getItemByItemId(9819) != null)
			{
				Fort fort = FortManager.getInstance().getFort(activeChar);
				if (fort != null)
				{
					FortSiegeManager.getInstance().dropCombatFlag(activeChar,fort.getFortId());
				}
				else
				{
					int slot = activeChar.getInventory().getSlotFromItem(activeChar.getInventory().getItemByItemId(9819));
					activeChar.getInventory().unEquipItemInBodySlotAndRecord(slot);
					activeChar.destroyItem("CombatFlag", activeChar.getInventory().getItemByItemId(9819), null, true);
				}
			}
		}
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
		if (getFort() != null && _isActiveSiege)
		{
			// debuff participants only if they die inside siege zone
			if (character instanceof L2PcInstance && ((L2PcInstance)character).isRegisteredOnThisSiegeField(getFort().getFortId()))
			{
				int lvl = 1;
				final L2Effect e = character.getFirstEffect(5660);
				if (e != null)
					lvl = Math.min(lvl + e.getLevel(), 5);

				final L2Skill skill = SkillTable.getInstance().getInfo(5660, lvl);
				if (skill != null)
					skill.getEffects(character, character);
			}
		}
	}

	@Override
	public void onReviveInside(L2Character character) {}

	public void updateZoneStatusForCharactersInside()
	{
		if (getFort() != null && _isActiveSiege)
		{
			for (L2Character character : _characterList.values())
			{
				try
				{
					onEnter(character);
				}
				catch (NullPointerException e)
				{
					_log.log(Level.WARNING, "Exception on onEnter(): " + e.getMessage(), e);
				}
			}
		}
		else
		{
			for (L2Character character : _characterList.values())
			{
				try
				{
					character.setInsideZone(L2Character.ZONE_PVP, false);
					character.setInsideZone(L2Character.ZONE_SIEGE, false);
					character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, false);
					
					if (character instanceof L2PcInstance)
					{
						((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
						((L2PcInstance) character).stopFameTask();
						if (((L2PcInstance) character).getMountType() == 2)
						{
							((L2PcInstance) character).exitedNoLanding();
						}
					}
					if (character instanceof L2SiegeSummonInstance)
					{
						((L2SiegeSummonInstance) character).unSummon(((L2SiegeSummonInstance) character).getOwner());
					}
				}
				catch (NullPointerException e)
				{
					_log.log(Level.WARNING, "Exception on updateZoneStatusForCharactersInside(): " + e.getMessage(), e);
				}
			}
		}
	}
	
	/**
	 * Removes all foreigners from the fort
	 * @param owningClan
	 */
	public void banishForeigners(L2Clan owningClan)
	{
		for (L2Character temp : _characterList.values())
		{
			if (!(temp instanceof L2PcInstance))
				continue;
			if (((L2PcInstance) temp).getClan() == owningClan)
				continue;
			
			((L2PcInstance) temp).teleToLocation(MapRegionTable.TeleportWhereType.Town);
		}
	}
	
	/**
	 * Sends a message to all players in this zone
	 * @param message
	 */
	public void announceToPlayers(String message)
	{
		for (L2Character temp : _characterList.values())
		{
			if (temp instanceof L2PcInstance)
				((L2PcInstance) temp).sendMessage(message);
		}
	}
	
	/**
	 * Returns all players within this zone
	 * @return
	 */
	public FastList<L2PcInstance> getAllPlayers()
	{
		FastList<L2PcInstance> players = new FastList<L2PcInstance>();
		
		for (L2Character temp : _characterList.values())
		{
			if (temp instanceof L2PcInstance)
				players.add((L2PcInstance) temp);
		}
		
		return players;
	}
	
	public int getFortId()
	{
		return _fortId;
	}
	
	private final Fort getFort()
	{
		if (_fort == null)
			_fort = FortManager.getInstance().getFortById(_fortId);
		return _fort;
	}
	
	public boolean isActive()
	{
		return _isActiveSiege;
	}
	
	public void setIsActive(boolean val)
	{
		_isActiveSiege = val;
	}
}