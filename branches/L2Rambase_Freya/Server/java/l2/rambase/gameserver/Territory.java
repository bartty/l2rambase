/**
 * @author L2Rambase Project
 */

package l2.rambase.gameserver;

import java.util.Map;
import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Territory;
import l2.rambase.util.lib.SqlUtils;

import javolution.util.FastMap;

public class Territory
{
	private static Logger _log = Logger.getLogger(TradeController.class.getName());
	private static Map<Integer, L2Territory> _territory;
	
	public static Territory getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private Territory()
	{
		// load all data at server start
		reload_data();
	}
	
	public int[] getRandomPoint(int terr)
	{
		return _territory.get(terr).getRandomPoint();
	}
	
	public int getProcMax(int terr)
	{
		return _territory.get(terr).getProcMax();
	}
	
	public void reload_data()
	{
		_territory = new FastMap<Integer, L2Territory>();
		
		Integer[][] point = SqlUtils.get2DIntArray(new String[] { "loc_id", "loc_x", "loc_y", "loc_zmin", "loc_zmax", "proc" }, "locations", "loc_id > 0");
		for (Integer[] row : point)
		{
			//			_log.info("row = "+row[0]);
			Integer terr = row[0];
			if (terr == null)
			{
				_log.warning("Null territory!");
				continue;
			}
			
			if (_territory.get(terr) == null)
			{
				L2Territory t = new L2Territory(terr);
				_territory.put(terr, t);
			}
			_territory.get(terr).add(row[1], row[2], row[3], row[4], row[5]);
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final Territory _instance = new Territory();
	}
}
