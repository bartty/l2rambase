/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import javolution.util.FastList;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.instancemanager.clanhallsiege.BanditStrongholdSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.DevastatedCastleSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfResistanceSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfTheDeadSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.WildBeastFarmSiege;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class L2ClanHallSiegeZone extends L2ZoneType
{
	private String _zoneName;

	public L2ClanHallSiegeZone(int id)
	{
		super(id);
	}

	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("name"))
		{
			_zoneName = value;
		}
		else
			super.setParameter(name, value);
	}

	@Override
	protected void onEnter(L2Character character)
	{
		if (character instanceof L2PcInstance && FortressOfResistanceSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Fortress of Resistance"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && BanditStrongholdSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Bandit Stronghold"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && DevastatedCastleSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Devastated Castle"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && WildBeastFarmSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Beast Farm"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && FortressOfTheDeadSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Fortress of the Dead"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.ENTERED_COMBAT_ZONE));
		}
		// if (character instanceof L2SiegeSummonInstance)
		// ((L2SiegeSummonInstance)character).unSummon(((L2SiegeSummonInstance)character).getOwner());
	}

	@Override
	protected void onExit(L2Character character)
	{
		if (character instanceof L2PcInstance && FortressOfResistanceSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Fortress of Resistance"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && BanditStrongholdSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Bandit Stronghold"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && DevastatedCastleSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Devastated Castle"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && WildBeastFarmSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Beast Farm"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
		}
		if (character instanceof L2PcInstance && FortressOfTheDeadSiege.getInstance().getIsInProgress())
		{
			if (_zoneName.equalsIgnoreCase("Fortress of the Dead"))
				character.setInsideZone(L2Character.ZONE_PVP, true);
			character.setInsideZone(L2Character.ZONE_SIEGE, true);
			character.setInsideZone(L2Character.ZONE_NOSUMMONFRIEND, true);
			((L2PcInstance) character).sendPacket(new SystemMessage(SystemMessageId.LEFT_COMBAT_ZONE));
		}
		// if (character instanceof L2SiegeSummonInstance)
		// ((L2SiegeSummonInstance)character).unSummon(((L2SiegeSummonInstance)character).getOwner());
	}

	@Override
	public void onDieInside(L2Character character)
	{
	}

	@Override
	public void onReviveInside(L2Character character)
	{
	}

	/**
	 * Removes all foreigners from the clanhall
	 *
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
	 *
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
	 *
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

	public String getZoneName()
	{
		return _zoneName;
	}
}