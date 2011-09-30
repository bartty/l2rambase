/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.model.L2AccessLevel;
import l2.rambase.gameserver.model.L2AdminCommandAccessRight;

import javolution.util.FastMap;


public class AdminCommandAccessRights
{
	/** The logger<br> */
	private static Logger _log = Logger.getLogger(AdminCommandAccessRights.class.getName());
	
	private Map<String, L2AdminCommandAccessRight> _adminCommandAccessRights;
	
	/**
	 * Returns the one and only instance of this class<br><br>
	 * 
	 * @return AdminCommandAccessRights: the one and only instance of this class<br>
	 */
	public static AdminCommandAccessRights getInstance()
	{
		return SingletonHolder._instance;
	}
	
	/** The access rights<br> */
	private AdminCommandAccessRights()
	{
		loadAdminCommandAccessRights();
	}
	
	/**
	 * Loads admin command access rights from database<br>
	 */
	private void loadAdminCommandAccessRights()
	{
		_adminCommandAccessRights = new FastMap<String, L2AdminCommandAccessRight>();
		
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM admin_command_access_rights");
			ResultSet rset = stmt.executeQuery();
			String adminCommand = null;
			String accessLevels = null;
			
			while (rset.next())
			{
				adminCommand = rset.getString("adminCommand");
				accessLevels = rset.getString("accessLevels");
				_adminCommandAccessRights.put(adminCommand, new L2AdminCommandAccessRight(adminCommand, accessLevels));
			}
			rset.close();
			stmt.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "AdminCommandAccessRights: Error loading from database:" + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		
		_log.info("AdminCommandAccessRights: Loaded " + _adminCommandAccessRights.size() + " from database.");
	}
	
	public boolean hasAccess(String adminCommand, L2AccessLevel accessLevel)
	{
		if (accessLevel.getLevel() == AccessLevels._masterAccessLevelNum)
			return true;
		
		L2AdminCommandAccessRight acar = _adminCommandAccessRights.get(adminCommand);
		
		if (acar == null)
		{
			_log.info("AdminCommandAccessRights: No rights defined for admin command " + adminCommand + ".");
			return false;
		}
		
		return acar.hasAccess(accessLevel);
	}
	
	public void reloadAdminCommandAccessRights()
	{
		loadAdminCommandAccessRights();
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final AdminCommandAccessRights _instance = new AdminCommandAccessRights();
	}
}