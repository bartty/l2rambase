/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.L2SiegeClan;
import l2.rambase.gameserver.model.Location;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2SiegeFlagInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.network.SystemMessageId;


public final class RequestRestartPoint extends L2GameClientPacket
{
	private static final String _C__6d_REQUESTRESTARTPOINT = "[C] 6d RequestRestartPoint";
	private static Logger _log = Logger.getLogger(RequestRestartPoint.class.getName());

	protected int     _requestedPointType;
	protected boolean _continuation;

	@Override
	protected void readImpl()
	{
		_requestedPointType = readD();
	}

	class DeathTask implements Runnable
	{
		final L2PcInstance activeChar;

		DeathTask (L2PcInstance _activeChar)
		{
			activeChar = _activeChar;
		}

		@SuppressWarnings("synthetic-access")
		public void run()
		{
			Location loc = null;
			Castle castle = null;
			Fort fort = null;
			Boolean isInDefense = false;
			int instanceId = 0;

			// force jail
			if (activeChar.isInJail())
			{
				_requestedPointType = 27;
			}
			else if (activeChar.isFestivalParticipant())
			{
				_requestedPointType = 5;
			}
			switch (_requestedPointType)
			{
				case 1: // to clanhall
					if (activeChar.getClan() == null || activeChar.getClan().getHasHideout() == 0)
					{
						_log.warning("Player ["+activeChar.getName()+"] called RestartPointPacket - To Clanhall and he doesn't have Clanhall!");
						return;
					}
					loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.ClanHall);

					if (ClanHallManager.getInstance().getClanHallByOwner(activeChar.getClan())!= null &&
							ClanHallManager.getInstance().getClanHallByOwner(activeChar.getClan()).getFunction(ClanHall.FUNC_RESTORE_EXP) != null)
					{
						activeChar.restoreExp(ClanHallManager.getInstance().getClanHallByOwner(activeChar.getClan()).getFunction(ClanHall.FUNC_RESTORE_EXP).getLvl());
					}
					break;

				case 2: // to castle
					castle = CastleManager.getInstance().getCastle(activeChar);

					if (castle != null && castle.getSiege().getIsInProgress())
					{
						// Siege in progress
						if (castle.getSiege().checkIsDefender(activeChar.getClan()))
							loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.Castle);
						// Just in case you lost castle while being dead.. Port to nearest Town.
						else if (castle.getSiege().checkIsAttacker(activeChar.getClan()))
							loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.Town);
						else
						{
							_log.warning("Player ["+activeChar.getName()+"] called RestartPointPacket - To Castle and he doesn't have Castle!");
							return;
						}
					}
					else
					{
						if (activeChar.getClan() == null || activeChar.getClan().getHasCastle() == 0)
							return;
						else
							loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.Castle);
					}
					if (CastleManager.getInstance().getCastleByOwner(activeChar.getClan())!= null &&
							CastleManager.getInstance().getCastleByOwner(activeChar.getClan()).getFunction(Castle.FUNC_RESTORE_EXP) != null)
					{
						activeChar.restoreExp(CastleManager.getInstance().getCastleByOwner(activeChar.getClan()).getFunction(Castle.FUNC_RESTORE_EXP).getLvl());
					}
					break;

				case 3: // to fortress
					
					if ((activeChar.getClan() == null || activeChar.getClan().getHasFort() == 0) && !isInDefense)
					{
						_log.warning("Player ["+activeChar.getName()+"] called RestartPointPacket - To Fortress and he doesn't have Fortress!");
						return;
					}
					loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.Fortress);
					if (FortManager.getInstance().getFortByOwner(activeChar.getClan())!= null &&
							FortManager.getInstance().getFortByOwner(activeChar.getClan()).getFunction(Fort.FUNC_RESTORE_EXP) != null)
					{
						activeChar.restoreExp(FortManager.getInstance().getFortByOwner(activeChar.getClan()).getFunction(Fort.FUNC_RESTORE_EXP).getLvl());
					}
					break;

				case 4: // to siege HQ
					L2SiegeClan siegeClan = null;
					castle = CastleManager.getInstance().getCastle(activeChar);
					fort = FortManager.getInstance().getFort(activeChar);
					L2SiegeFlagInstance flag = TerritoryWarManager.getInstance().getFlagForClan(activeChar.getClan());

					if (castle != null && castle.getSiege().getIsInProgress())
						siegeClan = castle.getSiege().getAttackerClan(activeChar.getClan());
					else if (fort != null && fort.getSiege().getIsInProgress())
						siegeClan = fort.getSiege().getAttackerClan(activeChar.getClan());

					if ((siegeClan == null || siegeClan.getFlag().isEmpty()) && flag == null)
					{
						_log.warning("Player ["+activeChar.getName()+"] called RestartPointPacket - To Siege HQ and he doesn't have Siege HQ!");
						return;
					}
					loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.SiegeFlag);
					break;

				case 5: // Use Feather Fixed or Player is a festival participant
					boolean can = false;
	                if(activeChar.isGM() || activeChar.isFestivalParticipant())
	                    can = true;
	                else
	                if(activeChar.destroyItemByItemId("Fixed Resurrection", 10649, 1L, activeChar, false) || activeChar.destroyItemByItemId("Fixed Resurrection", 13300, 1L, activeChar, false) || activeChar.destroyItemByItemId("Fixed Resurrection", 13128, 1L, activeChar, false))
	                {
	                    activeChar.sendPacket(SystemMessageId.USED_FEATHER_TO_RESURRECT);
	                    can = true;
	                }
	                if(!can)
	                {
	                    RequestRestartPoint._log.warning((new StringBuilder()).append("Player [").append(activeChar.getName()).append("] called RestartPointPacket - Fixed and he isn't festival participant!").toString());
	                    return;
	                }
	                instanceId = activeChar.getInstanceId();
	                loc = new Location(activeChar.getX(), activeChar.getY(), activeChar.getZ());
	                break;
				case 27: // to jail
					if (!activeChar.isInJail()) return;
					loc = new Location(-114356, -249645, -2984);
					break;

				default:
					loc = MapRegionTable.getInstance().getTeleToLocation(activeChar, MapRegionTable.TeleportWhereType.Town);
					break;
			}

			// Teleport and revive
			activeChar.setInstanceId(instanceId);
			activeChar.setIsIn7sDungeon(false);
			activeChar.setIsPendingRevive(true);
			activeChar.teleToLocation(loc, true);
		}
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
			return;

		if (TvTEvent.isStarted() && TvTEvent.isPlayerParticipant(activeChar.getObjectId()))
			return;

		if (PvPEvent.isStarted() && PvPEvent.isPlayerParticipant(activeChar.getObjectId()))
			return;

		if (activeChar.isFakeDeath())
		{
			activeChar.stopFakeDeath(null);
			return;
		}
		else if(!activeChar.isDead())
		{
			_log.warning("Living player ["+activeChar.getName()+"] called RestartPointPacket! Ban this player!");
			return;
		}

		Castle castle = CastleManager.getInstance().getCastle(activeChar.getX(), activeChar.getY(), activeChar.getZ());
		if (castle != null && castle.getSiege().getIsInProgress())
		{
			if (activeChar.getClan() != null && castle.getSiege().checkIsAttacker(activeChar.getClan()))
			{
				// Schedule respawn delay for attacker
				ThreadPoolManager.getInstance().scheduleGeneral(new DeathTask(activeChar), castle.getSiege().getAttackerRespawnDelay());
				if (castle.getSiege().getAttackerRespawnDelay() > 0)
					activeChar.sendMessage("You will be re-spawned in " + castle.getSiege().getAttackerRespawnDelay()/1000 + " seconds");
				return;
			}
		}

		// run immediately (no need to schedule)
		new DeathTask(activeChar).run();
	}

	/* (non-Javadoc)
	* @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	*/
	@Override
	public String getType()
	{
		return _C__6d_REQUESTRESTARTPOINT;
	}
}
