/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import gnu.trove.TIntObjectHashMap;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2SummonItem;

public class SummonItemsData
{
	protected static final Logger _log = Logger.getLogger(SummonItemsData.class.getName());
	private TIntObjectHashMap<L2SummonItem> _summonitems;
	
	public static SummonItemsData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private SummonItemsData()
	{
		_summonitems = new TIntObjectHashMap<L2SummonItem>();
		
		Scanner s;
		
		try
		{
			s = new Scanner(new File(Config.DATAPACK_ROOT + "/data/summon_items.csv"));
		}
		catch (Exception e)
		{
			_log.warning("Summon items data: Can not find '" + Config.DATAPACK_ROOT + "/data/summon_items.csv'");
			return;
		}
		
		int lineCount = 0;
		
		while (s.hasNextLine())
		{
			lineCount++;
			
			String line = s.nextLine();
			
			if (line.startsWith("#"))
			{
				continue;
			}
			else if (line.isEmpty())
				continue;
			
			String[] lineSplit = line.split(";");
			boolean ok = true;
			int itemID = 0, npcID = 0;
			byte summonType = 0;
			int despawn = -1;
			
			try
			{
				itemID = Integer.parseInt(lineSplit[0]);
				npcID = Integer.parseInt(lineSplit[1]);
				summonType = Byte.parseByte(lineSplit[2]);
				if (summonType == 0)
					despawn = Integer.parseInt(lineSplit[3]);
			}
			catch (Exception e)
			{
				_log.warning("Summon items data: Error in line " + lineCount + " -> incomplete/invalid data or wrong seperator!");
				_log.warning("		" + line);
				ok = false;
			}
			
			if (!ok)
				continue;
			
			L2SummonItem summonitem = new L2SummonItem(itemID, npcID, summonType, despawn);
			_summonitems.put(itemID, summonitem);
		}
		
		_log.info("Summon items data: Loaded " + _summonitems.size() + " summon items.");
	}
	
	public L2SummonItem getSummonItem(int itemId)
	{
		return _summonitems.get(itemId);
	}
	
	public int[] itemIDs()
	{
		int size = _summonitems.size();
		int[] result = new int[size];
		int i = 0;
		for (Object si : _summonitems.getValues())
		{
			result[i++] = ((L2SummonItem) si).getItemId();
		}
		return result;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final SummonItemsData _instance = new SummonItemsData();
	}
}
