/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.itemcontainer.ItemContainer;


public class CastleUpdater implements Runnable
{
	protected static final Logger _log = Logger.getLogger(CastleUpdater.class.getName());
	private L2Clan _clan;
	private int _runCount = 0;
	
	public CastleUpdater(L2Clan clan, int runCount)
	{
		_clan = clan;
		_runCount = runCount;
	}
	
	public void run()
	{
		try
		{
			// Move current castle treasury to clan warehouse every 2 hour
			ItemContainer warehouse = _clan.getWarehouse();
			if ((warehouse != null) && (_clan.getHasCastle() > 0))
			{
				Castle castle = CastleManager.getInstance().getCastleById(_clan.getHasCastle());
				if (!Config.ALT_MANOR_SAVE_ALL_ACTIONS)
				{
					if (_runCount % Config.ALT_MANOR_SAVE_PERIOD_RATE == 0)
					{
						castle.saveSeedData();
						castle.saveCropData();
						if (Config.DEBUG)
							_log.info("Manor System: all data for " + castle.getName() + " saved");
					}
				}
				CastleUpdater cu = new CastleUpdater(_clan, ++_runCount);
				ThreadPoolManager.getInstance().scheduleGeneral(cu, 3600000);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}