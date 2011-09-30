/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import gnu.trove.TIntObjectHashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.model.L2TeleportLocation;


public class TeleportLocationTable
{
	private static Logger _log = Logger.getLogger(TeleportLocationTable.class.getName());
	
	private TIntObjectHashMap<L2TeleportLocation> _teleports;
	
	public static TeleportLocationTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private TeleportLocationTable()
	{
		reloadAll();
	}
	
	public void reloadAll()
	{
		_teleports = new TIntObjectHashMap<L2TeleportLocation>();
		
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT id, loc_x, loc_y, loc_z, price, fornoble, itemId FROM teleport");
			ResultSet rset = statement.executeQuery();
			L2TeleportLocation teleport;
			
			while (rset.next())
			{
				teleport = new L2TeleportLocation();
				
				teleport.setTeleId(rset.getInt("id"));
				teleport.setLocX(rset.getInt("loc_x"));
				teleport.setLocY(rset.getInt("loc_y"));
				teleport.setLocZ(rset.getInt("loc_z"));
				teleport.setPrice(rset.getInt("price"));
				teleport.setIsForNoble(rset.getInt("fornoble") == 1);
				teleport.setItemId(rset.getInt("itemId"));
				
				_teleports.put(teleport.getTeleId(), teleport);
			}
			
			rset.close();
			statement.close();
			
			_log.info("TeleportLocationTable: Loaded " + _teleports.size() + " Teleport Location Templates.");
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Error loading Teleport Table.", e);
		}
		finally
		{
				L2DatabaseFactory.close(con);
		}
		
		if (Config.CUSTOM_TELEPORT_TABLE)
		{
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT id, loc_x, loc_y, loc_z, price, fornoble, itemId FROM custom_teleport");
				ResultSet rset = statement.executeQuery();
				L2TeleportLocation teleport;
				int _cTeleCount = _teleports.size();
				while (rset.next())
				{
					teleport = new L2TeleportLocation();
					teleport.setTeleId(rset.getInt("id"));
					teleport.setLocX(rset.getInt("loc_x"));
					teleport.setLocY(rset.getInt("loc_y"));
					teleport.setLocZ(rset.getInt("loc_z"));
					teleport.setPrice(rset.getInt("price"));
					teleport.setIsForNoble(rset.getInt("fornoble") == 1);
					teleport.setItemId(rset.getInt("itemId"));
					
					_teleports.put(teleport.getTeleId(), teleport);
				}
				rset.close();
				statement.close();
				_cTeleCount = _teleports.size() - _cTeleCount;
				if (_cTeleCount > 0)
					_log.info("TeleportLocationTable: Loaded " + _cTeleCount + " Custom Teleport Location Templates.");
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "Error while creating custom teleport table " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
		}
	}
	
	/**
	 * @param template id
	 * @return
	 */
	public L2TeleportLocation getTemplate(int id)
	{
		return _teleports.get(id);
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final TeleportLocationTable _instance = new TeleportLocationTable();
	}
}
