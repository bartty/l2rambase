/**
 * @author L2Rambase Project
 */

package l2.rambase.gameserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.entity.Fort;


/**
 *
 * Vice - 2008
 * Class managing periodical events with castle
 *
 */
public class FortUpdater implements Runnable
{
	protected static Logger _log = Logger.getLogger(FortUpdater.class.getName());
	private L2Clan _clan;
	private Fort _fort;
	private int _runCount;
	private UpdaterType _updaterType;
	
	public enum UpdaterType
	{
		MAX_OWN_TIME, // gives fort back to NPC clan
		PERIODIC_UPDATE // raise blood oath/supply level
	}
	
	public FortUpdater(Fort fort, L2Clan clan, int runCount, UpdaterType ut)
	{
		_fort = fort;
		_clan = clan;
		_runCount = runCount;
		_updaterType = ut;
	}
	
	public void run()
	{
		try
		{
			switch(_updaterType)
			{
				case PERIODIC_UPDATE:
					_runCount++;
					if (_fort.getOwnerClan() == null || _fort.getOwnerClan() != _clan)
						return;
					
					_fort.setBloodOathReward(_fort.getBloodOathReward() + Config.FS_BLOOD_OATH_COUNT);
					if (_fort.getFortState() == 2)
					{
						if (_clan.getWarehouse().getAdena() >= 25000)
						{
							_clan.getWarehouse().destroyItemByItemId("FS_fee_for_Castle", 57, 25000, null, null);
							CastleManager.getInstance().getCastleById(_fort.getCastleId()).addToTreasuryNoTax(25000);
							_fort.raiseSupplyLvL();
						}
						else
							_fort.setFortState(1, 0);
					}
					_fort.saveFortVariables();
					break;
				case MAX_OWN_TIME:
					if (_fort.getOwnerClan() == null || _fort.getOwnerClan() != _clan)
						return;
					if (_fort.getOwnedTime() > 168 * 3600)
					{
						_fort.removeOwner(true);
						_fort.setFortState(0, 0);
					}
					break;
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "", e);
		}
	}
}