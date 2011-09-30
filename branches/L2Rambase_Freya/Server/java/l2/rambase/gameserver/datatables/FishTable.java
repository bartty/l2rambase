/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.model.FishData;

import javolution.util.FastList;


public class FishTable
{
	private static Logger _log = Logger.getLogger(FishTable.class.getName());
	
	private static List<FishData> _fishsNormal;
	private static List<FishData> _fishsEasy;
	private static List<FishData> _fishsHard;
	
	public static FishTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private FishTable()
	{
		//Create table that contains all fish datas
		int count = 0;
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			_fishsEasy = new FastList<FishData>();
			_fishsNormal = new FastList<FishData>();
			_fishsHard = new FastList<FishData>();
			FishData fish;
			PreparedStatement statement = con.prepareStatement("SELECT id, level, name, hp, hpregen, fish_type, fish_group, fish_guts, guts_check_time, wait_time, combat_time FROM fish ORDER BY id");
			ResultSet fishes = statement.executeQuery();
			
			while (fishes.next())
			{
				int id = fishes.getInt("id");
				int lvl = fishes.getInt("level");
				String name = fishes.getString("name");
				int hp = fishes.getInt("hp");
				int hpreg = fishes.getInt("hpregen");
				int type = fishes.getInt("fish_type");
				int group = fishes.getInt("fish_group");
				int fish_guts = fishes.getInt("fish_guts");
				int guts_check_time = fishes.getInt("guts_check_time");
				int wait_time = fishes.getInt("wait_time");
				int combat_time = fishes.getInt("combat_time");
				fish = new FishData(id, lvl, name, hp, hpreg, type, group, fish_guts, guts_check_time, wait_time, combat_time);
				switch (fish.getGroup())
				{
					case 0:
						_fishsEasy.add(fish);
						break;
					case 1:
						_fishsNormal.add(fish);
						break;
					case 2:
						_fishsHard.add(fish);
				}
			}
			fishes.close();
			statement.close();
			count = _fishsEasy.size() + _fishsNormal.size() + _fishsHard.size();
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Error while creating fish table" + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		_log.info("FishTable: Loaded " + count + " Fishes.");
	}
	
	/**
	 * @param Fish - lvl
	 * @param Fish - type
	 * @param Fish - group
	 * @return List of Fish that can be fished
	 */
	public List<FishData> getfish(int lvl, int type, int group)
	{
		List<FishData> result = new FastList<FishData>();
		List<FishData> _Fishs = null;
		switch (group)
		{
			case 0:
				_Fishs = _fishsEasy;
				break;
			case 1:
				_Fishs = _fishsNormal;
				break;
			case 2:
				_Fishs = _fishsHard;
		}
		if (_Fishs == null)
		{
			// the fish list is empty
			_log.warning("Fish are not defined !");
			return null;
		}
		for (FishData f : _Fishs)
		{
			if (f.getLevel() != lvl)
				continue;
			if (f.getType() != type)
				continue;
			
			result.add(f);
		}
		if (result.isEmpty())
			_log.warning("Cant Find Any Fish!? - Lvl: " + lvl + " Type: " + type);
		return result;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final FishTable _instance = new FishTable();
	}
}
