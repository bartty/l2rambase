/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class HellboundManager
{
	private boolean _megalithsCompleted = false;
	private int _level = 0;
	private int _currentTrust = 0;
	private static final Logger _log = Logger.getLogger(HellboundManager.class.getName());

	private HellboundManager()
	{
		_log.info(getClass().getSimpleName()+": Initializing");
		init();
	}

	private void init()
	{
		checkHellboundLevel();
		_log.info(getClass().getSimpleName()+": Current Level - "+_level);
		_log.info(getClass().getSimpleName()+": Current Trust - "+_currentTrust);
	}

	public static final HellboundManager getInstance()
	{
		return SingletonHolder._instance;
	}

	public boolean checkMegalithsCompleted()
	{
		return _megalithsCompleted;
	}

	public void setMegalithsCompleted(boolean value)
	{
		_megalithsCompleted = value;
	}

	public boolean checkIsOpen()
	{
		return _level > 0;
	}

	public int getLevel()
	{
		return _level;
	}

	public int getTrust()
	{
		return _currentTrust;
	}

	public L2Spawn addSpawn(int mobId, int x, int y, int z, int headg, int respTime)
	{
		L2NpcTemplate template1;
		template1 = NpcTable.getInstance().getTemplate(mobId);
		L2Spawn spawn = null;
		try
		{
			spawn = new L2Spawn(template1);
			spawn.setLocx(x);
			spawn.setLocy(y);
			spawn.setLocz(z);
			spawn.setAmount(1);
			spawn.setHeading(headg);
			spawn.setRespawnDelay(respTime);
			spawn.setInstanceId(0);
			spawn.setOnKillDelay(0);
			SpawnTable.getInstance().addNewSpawn(spawn, false);
			spawn.init();
			spawn.startRespawn();
			if (respTime == 0)
				spawn.stopRespawn();
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Cannot spawn that npc in Hellbound: "+ e.getMessage(), e);
		}
		return spawn;
	}

	public synchronized void increaseTrust(int points)
	{
		if (!checkIsOpen()) 
			return;
		_currentTrust += points;
		if (_currentTrust < 0) 
			_currentTrust = 0;
		updateTrust(_currentTrust);
	}

	public void updateTrust(int newTrust)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE hellbound SET trustLevel=?");
			statement.setInt(1, _currentTrust);
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{			
			_log.log(Level.WARNING, "HellboundManager: Could not save Hellbound trust points: " + e.getMessage(), e);
		}
		catch (Exception e)
		{			
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (Exception e)
			{				
			}
		}
	}

	public void changeLevel(int newLevel)
	{
		_level = newLevel;
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE hellbound SET zonesLevel=?");
			statement.setInt(1, _level);
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "HellboundManager: Could not save Hellbound level" + e.getMessage(), e);
		}
		catch (Exception e)
		{			
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public void checkHellboundLevel()
	{
		int _tempTrust = 0;
		int tempLevel = 0;
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT trustLevel, zonesLevel FROM hellbound");
			ResultSet rset = statement.executeQuery();
			while (rset.next())
			{
				// Read all info from DB, and store it for AI to read and decide what to do
				// faster than accessing DB in real time
				_tempTrust = rset.getInt("trustLevel");
				tempLevel = rset.getInt("zonesLevel");
				_currentTrust = rset.getInt("trustLevel");
				if ((_tempTrust > 0 && _currentTrust < 300000) || tempLevel > 0)
					_level = 1;
				if (_tempTrust >= 300000 && _currentTrust < 600000)
					_level = 2;
				if (_tempTrust >= 600000 && _currentTrust < 1000000)
					_level = 3;
				if (tempLevel == 4)
					_level = 4;
				if (tempLevel == 5)
					_level = 5;
				if (tempLevel == 6)
					_level = 6;
				if (tempLevel == 7)
					_level = 7;
				if (tempLevel == 8)
					_level = 8;
				if (tempLevel == 9)
					_level = 9;
				if (tempLevel == 10)
					_level = 10;
				if (_tempTrust >= 2000000 && tempLevel >= 10)
					_level = 11;
			}
			rset.close();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "HellboundManager: Could not load the hellbound table: "+ e.getMessage(), e);			
		}
		catch (Exception e)
		{			
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (Exception e)
			{				
			}
		}
		if (tempLevel != _level && tempLevel != 5)
			changeLevel(_level);
	}
	
	/**
	 * Unlock hellbound zone
	 */
	public void unlock()
	{
		if (!checkIsOpen())
			changeLevel(1);
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final HellboundManager _instance = new HellboundManager();
	}
}
