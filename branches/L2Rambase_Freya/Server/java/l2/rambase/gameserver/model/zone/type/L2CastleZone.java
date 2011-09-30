/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2SiegeSummonInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.zone.L2SpawnZone;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

import javolution.util.FastList;


public class L2CastleZone extends L2SpawnZone
{
	private int _castleId;
	private Castle _castle = null;
	private boolean _isActiveSiege = false;
	private static final int DISMOUNT_DELAY = 5;
	
	public L2CastleZone(int id)
	{
		super(id);
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("castleId"))
		{
			_castleId = Integer.parseInt(value);
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (getCastle() != null)
		{
			if (_isActiveSiege)
			{
				character.setInsideZone(L2Character.ZONE_PVP, true);
				character.setInsideZone(L2Character.ZONE_SIEGE, true);
				character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
				
				if (character instanceof L2PcInstance)
				{
					if (((L2PcInstance) character).isRegisteredOnThisSiegeField(getCastle().getCastleId()))
					{
						((L2PcInstance) character).setIsInSiege(true);
						((L2PcInstance) character).startFameTask(Config.CASTLE_ZONE_FAME_TASK_FREQUENCY * 1000, Config.CASTLE_ZONE_FAME_AQUIRE_POINTS);
					}
					((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
					if (!Config.ALLOW_WYVERN_DURING_SIEGE && ((L2PcInstance) character).getMountType() == 2)
					{
						character.sendPacket(new SystemMessage(SystemMessageId.AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN));
						((L2PcInstance) character).enteredNoLanding(DISMOUNT_DELAY);
					}
				}
			}
			character.setInsideZone(L2Character.ZONE_CASTLE, true);
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (getCastle() != null)
		{
			if (_isActiveSiege)
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
			if (character instanceof L2PcInstance)
			{
				((L2PcInstance) character).stopFameTask();
				((L2PcInstance) character).setIsInSiege(false);
			}
			character.setInsideZone(L2Character.ZONE_PVP, false);
			character.setInsideZone(L2Character.ZONE_SIEGE, false);
			character.setInsideZone(L2Character.ZONE_CASTLE, false);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, false);
		}
		if (character instanceof L2SiegeSummonInstance)
		{
			((L2SiegeSummonInstance) character).unSummon(((L2SiegeSummonInstance) character).getOwner());
		}
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
		if (getCastle() != null && _isActiveSiege)
		{
			// debuff participants only if they die inside siege zone
			if (character instanceof L2PcInstance && ((L2PcInstance) character).isRegisteredOnThisSiegeField(getCastle().getCastleId()))
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
	public void onReviveInside(L2Character character)
	{
	}
	
	public void updateZoneStatusForCharactersInside()
	{
		if (getCastle() != null && _isActiveSiege)
		{
			for (L2Character character : _characterList.values())
			{
				try
				{
					onEnter(character);
				}
				catch (NullPointerException e)
				{
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
				}
			}
		}
	}
	
	/**
	 * Removes all foreigners from the castle
	 * @param owningClanId
	 */
	public void banishForeigners(int owningClanId)
	{
		for (L2Character temp : _characterList.values())
		{
			if (!(temp instanceof L2PcInstance))
				continue;
			if (((L2PcInstance) temp).getClanId() == owningClanId)
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
	
	public int getCastleId()
	{
		return _castleId;
	}
	
	public boolean isActive()
	{
		return _isActiveSiege;
	}
	
	public void setIsActive(boolean val)
	{
		_isActiveSiege = val;
	}
	
	private final Castle getCastle()
	{
		if (_castle == null)
			_castle = CastleManager.getInstance().getCastleById(_castleId);
		return _castle;
	}
}
