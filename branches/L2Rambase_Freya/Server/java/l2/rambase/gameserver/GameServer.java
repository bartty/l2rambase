/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.mmocore.network.SelectorConfig;
import org.mmocore.network.SelectorThread;

import l2.rambase.Config;
import l2.rambase.L2DatabaseFactory;
import l2.rambase.Server;
import l2.rambase.gameserver.cache.CrestCache;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.AccessLevels;
import l2.rambase.gameserver.datatables.AdminCommandAccessRights;
import l2.rambase.gameserver.datatables.ArmorSetsTable;
import l2.rambase.gameserver.datatables.AugmentationData;
import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.datatables.CharTemplateTable;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.datatables.EnchantGroupsTable; 
import l2.rambase.gameserver.datatables.EnchantHPBonusData;
import l2.rambase.gameserver.datatables.EventDroplist;
import l2.rambase.gameserver.datatables.ExtractableItemsData;
import l2.rambase.gameserver.datatables.ExtractableSkillsData;
import l2.rambase.gameserver.datatables.FishTable;
import l2.rambase.gameserver.datatables.GMSkillTable;
import l2.rambase.gameserver.datatables.HelperBuffTable;
import l2.rambase.gameserver.datatables.HennaTable;
import l2.rambase.gameserver.datatables.HennaTreeTable;
import l2.rambase.gameserver.datatables.HeroSkillTable;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.datatables.LevelUpData;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.datatables.MerchantPriceConfigTable;
import l2.rambase.gameserver.datatables.NobleSkillTable;
import l2.rambase.gameserver.datatables.NpcBufferTable;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.NpcWalkerRoutesTable;
import l2.rambase.gameserver.datatables.OfflineTradersTable;
import l2.rambase.gameserver.datatables.PetDataTable;
import l2.rambase.gameserver.datatables.PetSkillsTable;
import l2.rambase.gameserver.datatables.ResidentialSkillTable;
import l2.rambase.gameserver.datatables.SkillSpellbookTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SkillTreeTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.datatables.StaticObjects;
import l2.rambase.gameserver.datatables.SummonItemsData;
import l2.rambase.gameserver.datatables.TeleportLocationTable;
import l2.rambase.gameserver.datatables.UITable;
import l2.rambase.gameserver.geoeditorcon.GeoEditorListener;
import l2.rambase.gameserver.handler.AdminCommandHandler;
import l2.rambase.gameserver.handler.ChatHandler;
import l2.rambase.gameserver.handler.ItemHandler;
import l2.rambase.gameserver.handler.SkillHandler;
import l2.rambase.gameserver.handler.UserCommandHandler;
import l2.rambase.gameserver.handler.VoicedCommandHandler;
import l2.rambase.gameserver.idfactory.IdFactory;
import l2.rambase.gameserver.instancemanager.AirShipManager;
import l2.rambase.gameserver.instancemanager.AuctionManager;
import l2.rambase.gameserver.instancemanager.BoatManager;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.instancemanager.CoupleManager;
import l2.rambase.gameserver.instancemanager.CursedWeaponsManager;
import l2.rambase.gameserver.instancemanager.DayNightSpawnManager;
import l2.rambase.gameserver.instancemanager.DimensionalRiftManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.FortSiegeManager;
import l2.rambase.gameserver.instancemanager.FourSepulchersManager;
import l2.rambase.gameserver.instancemanager.GrandBossManager;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.ItemAuctionManager;
import l2.rambase.gameserver.instancemanager.ItemsOnGroundManager;
import l2.rambase.gameserver.instancemanager.KrateisCubeManager;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.instancemanager.MercTicketManager;
import l2.rambase.gameserver.instancemanager.PcCafePointsManager;
import l2.rambase.gameserver.instancemanager.PetitionManager;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.instancemanager.RaidBossPointsManager;
import l2.rambase.gameserver.instancemanager.RaidBossSpawnManager;
import l2.rambase.gameserver.instancemanager.SiegeManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.instancemanager.TransformationManager;
import l2.rambase.gameserver.instancemanager.ZoneManager;
import l2.rambase.gameserver.instancemanager.clanhallsiege.BanditStrongholdSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.DevastatedCastleSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfResistanceSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfTheDeadSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.WildBeastFarmSiege;
import l2.rambase.gameserver.instancemanager.games.FishermanManager;
import l2.rambase.gameserver.model.AutoChatHandler;
import l2.rambase.gameserver.model.AutoSpawnHandler;
import l2.rambase.gameserver.model.L2Rambaseanor;
import l2.rambase.gameserver.model.L2Rambaseultisell;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.PartyMatchRoomList;
import l2.rambase.gameserver.model.PartyMatchWaitingList;
import l2.rambase.gameserver.model.entity.Hero;
import l2.rambase.gameserver.model.olympiad.Olympiad;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.L2GamePacketHandler;
import l2.rambase.gameserver.pathfinding.PathFinding;
import l2.rambase.gameserver.script.faenor.FaenorScriptEngine;
import l2.rambase.gameserver.scripting.CompiledScriptCache;
import l2.rambase.gameserver.scripting.L2ScriptEngineManager;
import l2.rambase.gameserver.taskmanager.AutoAnnounceTaskManager;
import l2.rambase.gameserver.taskmanager.KnownListUpdateTaskManager;
import l2.rambase.gameserver.taskmanager.TaskManager;
import l2.rambase.gameserver.util.DynamicExtension;
import l2.rambase.status.Status;
import l2.rambase.util.DeadLockDetector;
import l2.rambase.util.IPv4Filter;


public class GameServer
{
	private static final Logger _log = Logger.getLogger(GameServer.class.getName());
	
	private final SelectorThread<L2GameClient> _selectorThread;
	private final DeadLockDetector _deadDetectThread;
	private final IdFactory _idFactory;
	public static GameServer gameServer;
	private LoginServerThread _loginThread;
	private static Status _statusServer;
	public static boolean _instanceOk = false;
	public static final Calendar dateTimeServerStarted = Calendar.getInstance();
	
	public long getUsedMemoryMB()
	{
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576; // ;
	}
	
	public SelectorThread<L2GameClient> getSelectorThread()
	{
		return _selectorThread;
	}
	
	public DeadLockDetector getDeadLockDetectorThread()
	{
		return _deadDetectThread;
	}
	
	public GameServer() throws Exception
	{
		long serverLoadStart = System.currentTimeMillis();
		
		gameServer = this;
		_log.finest("used mem:" + getUsedMemoryMB() + "MB");
		
		if (Config.SERVER_VERSION != null)
		{
			_log.info("Server Version:    " + Config.SERVER_VERSION);
		}
		if (Config.DATAPACK_VERSION != null)
		{
			_log.info("Datapack Version:  " + Config.DATAPACK_VERSION);
		}
		
		_idFactory = IdFactory.getInstance();
		
		if (!_idFactory.isInitialized())
		{
			_log.severe("Could not read object IDs from DB. Please Check Your Data.");
			throw new Exception("Could not initialize the ID factory");
		}
		
		ThreadPoolManager.getInstance();
		
		new File(Config.DATAPACK_ROOT, "data/crests").mkdirs();
		new File("log/game").mkdirs();
		
		// load script engines
		printSection("Engines");
		L2ScriptEngineManager.getInstance();
		
		printSection("World");
		// start game time control early
		GameTimeController.getInstance();
		
		InstanceManager.getInstance(); 
		L2World.getInstance(); 
		MapRegionTable.getInstance(); 
		Announcements.getInstance(); 
	 		                 
		printSection("Skills");
		EnchantGroupsTable.getInstance(); 
		SkillTable.getInstance();		
		SkillTreeTable.getInstance();
		PetSkillsTable.getInstance();		
		NobleSkillTable.getInstance();
		GMSkillTable.getInstance();
		HeroSkillTable.getInstance();
		ResidentialSkillTable.getInstance();
		SkillSpellbookTable.getInstance(); 
	 		                 
		printSection("Items"); 
		ItemTable.getInstance(); 
		ExtractableItemsData.getInstance(); 
		ExtractableSkillsData.getInstance(); 
		SummonItemsData.getInstance(); 
		EnchantHPBonusData.getInstance(); 
		MerchantPriceConfigTable.getInstance().loadInstances(); 
		TradeController.getInstance(); 
		L2Rambaseultisell.getInstance(); 
		RecipeController.getInstance(); 
		ArmorSetsTable.getInstance(); 
		FishTable.getInstance(); 
		SkillSpellbookTable.getInstance(); 
		
		printSection("Characters"); 
		CharTemplateTable.getInstance(); 
		CharNameTable.getInstance(); 
		LevelUpData.getInstance(); 
		AccessLevels.getInstance(); 
		AdminCommandAccessRights.getInstance(); 
		GmListTable.getInstance(); 
		PetDataTable.getInstance().loadPetsData(); 
		
		printSection("Clans");
		ClanTable.getInstance();
		ClanHallManager.getInstance(); 
		AuctionManager.getInstance(); 
				
		printSection("NPCs"); 
		NpcTable.getInstance();
		ZoneManager.getInstance();
		DoorTable.getInstance(); 
	 	StaticObjects.getInstance();
	 	ItemAuctionManager.getInstance();
		CastleManager.getInstance().loadInstances();		
		FortManager.getInstance().loadInstances();		
		NpcWalkerRoutesTable.getInstance().load();		 
		NpcBufferTable.getInstance();
		SpawnTable.getInstance();
		RaidBossSpawnManager.getInstance();
		DayNightSpawnManager.getInstance().notifyChangeMode();
		GrandBossManager.getInstance().initZones();
		RaidBossPointsManager.init();
		FourSepulchersManager.getInstance().init();
		DimensionalRiftManager.getInstance();
		EventDroplist.getInstance();
		if(Config.PCB_ENABLE)
        {            
            ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(PcCafePointsManager.getInstance(), Config.PCB_INTERVAL * 1000, Config.PCB_INTERVAL * 1000);
        }
		KrateisCubeManager.getInstance().init();
	 		                 
		printSection("Siege"); 
		SiegeManager.getInstance().getSieges(); 
		FortSiegeManager.getInstance(); 
		TerritoryWarManager.getInstance();
		CastleManorManager.getInstance();
		MercTicketManager.getInstance();
		BanditStrongholdSiege.getInstance();
        DevastatedCastleSiege.getInstance();
        FortressOfResistanceSiege.getInstance();
        FortressOfTheDeadSiege.getInstance();
        WildBeastFarmSiege.getInstance();       		
		
		L2Rambaseanor.getInstance(); 
	 		                 
		printSection("Olympiad"); 
		Olympiad.getInstance(); 
		Hero.getInstance(); 
	 		                 
		// Call to load caches 
		printSection("Cache"); 
		HtmCache.getInstance(); 
		CrestCache.getInstance(); 
		TeleportLocationTable.getInstance(); 
		UITable.getInstance();
		PartyMatchWaitingList.getInstance(); 
		PartyMatchRoomList.getInstance();
		PetitionManager.getInstance();
		HennaTable.getInstance(); 
		HennaTreeTable.getInstance(); 
		HelperBuffTable.getInstance(); 
		AugmentationData.getInstance(); 
		CursedWeaponsManager.getInstance(); 
		
		printSection("Geodata"); 
		GeoData.getInstance(); 
		if (Config.GEODATA == 2)
		{
			PathFinding.getInstance();
		}
		
		printSection("Scripts");
		QuestManager.getInstance();
		TransformationManager.getInstance();
		BoatManager.getInstance();
		AirShipManager.getInstance();
		
		try
		{
			_log.info("Loading Server Scripts");
			File scripts = new File(Config.DATAPACK_ROOT + "/data/scripts.cfg");
			if(!Config.ALT_DEV_NO_HANDLERS || !Config.ALT_DEV_NO_QUESTS)
				L2ScriptEngineManager.getInstance().executeScriptList(scripts);
		}
		catch (IOException ioe)
		{
			_log.severe("Failed loading scripts.cfg, no script going to be loaded");
		}
		try
		{
			CompiledScriptCache compiledScriptCache = L2ScriptEngineManager.getInstance().getCompiledScriptCache();
			if (compiledScriptCache == null)
			{
				_log.info("Compiled Scripts Cache is disabled.");
			}
			else
			{
				compiledScriptCache.purge();
				
				if (compiledScriptCache.isModified())
				{
					compiledScriptCache.save();
					_log.info("Compiled Scripts Cache was saved.");
				}
				else
				{
					_log.info("Compiled Scripts Cache is up-to-date.");
				}
			}
			
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "Failed to store Compiled Scripts Cache.", e);
		}
		QuestManager.getInstance().report();
		TransformationManager.getInstance().report();
		
		if (Config.SAVE_DROPPED_ITEM)
			ItemsOnGroundManager.getInstance();
		
		if (Config.AUTODESTROY_ITEM_AFTER > 0 || Config.HERB_AUTO_DESTROY_TIME > 0)
			ItemsAutoDestroy.getInstance();
		
		MonsterRace.getInstance();
		
		SevenSigns.getInstance().spawnSevenSignsNPC();
		SevenSignsFestival.getInstance();
		AutoSpawnHandler.getInstance();
		AutoChatHandler.getInstance();
		
		FaenorScriptEngine.getInstance();
		// Init of a cursed weapon manager
				
		_log.info("AutoChatHandler: Loaded " + AutoChatHandler.getInstance().size() + " handlers in total.");
		_log.info("AutoSpawnHandler: Loaded " + AutoSpawnHandler.getInstance().size() + " handlers in total.");
		
		AdminCommandHandler.getInstance();
		ChatHandler.getInstance();
		ItemHandler.getInstance();
		SkillHandler.getInstance();
		UserCommandHandler.getInstance();
		VoicedCommandHandler.getInstance();
		
		if (Config.MOD_ALLOW_WEDDING)
			CoupleManager.getInstance();
		if(Config.MOD_FISHERMAN_ENABLED)
            FishermanManager.getInstance();
		
		TaskManager.getInstance();		
		
		MerchantPriceConfigTable.getInstance().updateReferences();
		CastleManager.getInstance().activateInstances();
		FortManager.getInstance().activateInstances();
		HellboundManager.getInstance();

		if (Config.ALLOW_MAIL)
			MailManager.getInstance();

		//Universe.getInstance();
		
		if (Config.ACCEPT_GEOEDITOR_CONN)
			GeoEditorListener.getInstance();
		
		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
		
		_log.info("IdFactory: Free ObjectID's remaining: " + IdFactory.getInstance().size());
		
		// initialize the dynamic extension loader
		try
		{
			DynamicExtension.getInstance();
		}
		catch (Exception ex)
		{
			_log.log(Level.WARNING, "DynamicExtension could not be loaded and initialized", ex);
		}
				
		KnownListUpdateTaskManager.getInstance();
		if ((Config.OFFLINE_TRADE_ENABLE || Config.OFFLINE_CRAFT_ENABLE) && Config.RESTORE_OFFLINERS)
			OfflineTradersTable.restoreOfflineTraders();
		if (Config.DEADLOCK_DETECTOR)
		{
			_deadDetectThread = new DeadLockDetector();
			_deadDetectThread.setDaemon(true);
			_deadDetectThread.start();
		}
		else
			_deadDetectThread = null;
		System.gc();
		// maxMemory is the upper limit the jvm can use, totalMemory the size of
		// the current allocation pool, freeMemory the unused memory in the
		// allocation pool
		long freeMem = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1048576;
		long totalMem = Runtime.getRuntime().maxMemory() / 1048576;
		_log.info("GameServer Started, free memory " + freeMem + " Mb of " + totalMem + " Mb");
		
		_loginThread = LoginServerThread.getInstance();
		_loginThread.start();
		
		final SelectorConfig sc = new SelectorConfig();
		sc.MAX_READ_PER_PASS = Config.MMO_MAX_READ_PER_PASS;
		sc.MAX_SEND_PER_PASS = Config.MMO_MAX_SEND_PER_PASS;
		sc.SLEEP_TIME = Config.MMO_SELECTOR_SLEEP_TIME;
		sc.HELPER_BUFFER_COUNT = Config.MMO_HELPER_BUFFER_COUNT;
		
		final L2GamePacketHandler gph = new L2GamePacketHandler();
		_selectorThread = new SelectorThread<L2GameClient>(sc, gph, gph, gph, new IPv4Filter());
		
		InetAddress bindAddress = null;
		if (!Config.GAMESERVER_HOSTNAME.equals("*"))
		{
			try
			{
				bindAddress = InetAddress.getByName(Config.GAMESERVER_HOSTNAME);
			}
			catch (UnknownHostException e1)
			{
				_log.log(Level.SEVERE, "WARNING: The GameServer bind address is invalid, using all avaliable IPs. Reason: " + e1.getMessage(), e1);
			}
		}
		
		try
		{
			_selectorThread.openServerSocket(bindAddress, Config.PORT_GAME);
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "FATAL: Failed to open server socket. Reason: " + e.getMessage(), e);
			System.exit(1);
		}
		_selectorThread.start();
		_log.info("Maximum Numbers of Connected Players: " + Config.MAXIMUM_ONLINE_USERS);
		long serverLoadEnd = System.currentTimeMillis();
		_log.info("Server Loaded in " + ((serverLoadEnd - serverLoadStart) / 1000) + " seconds");
		
		AutoAnnounceTaskManager.getInstance();
	}
	
	public static void main(String[] args) throws Exception
	{
		Server.serverMode = Server.MODE_GAMESERVER;
		// Local Constants
		final String LOG_FOLDER = "log"; // Name of folder for log file
		final String LOG_NAME = "./log.cfg"; // Name of log file
		
		/*** Main ***/
		// Create log folder
		File logFolder = new File(Config.DATAPACK_ROOT, LOG_FOLDER);
		logFolder.mkdir();
		
		// Create input stream for log file -- or store file data into memory
		InputStream is = new FileInputStream(new File(LOG_NAME));
		LogManager.getLogManager().readConfiguration(is);
		is.close();
		
		// Initialize config
		Config.load();
		printSection("Database");
		L2DatabaseFactory.getInstance();
		gameServer = new GameServer();
		
		if (Config.IS_TELNET_ENABLED)
		{
			_statusServer = new Status(Server.serverMode);
			_statusServer.start();
		}
		else
		{
			_log.info("Telnet server is currently disabled.");
		}
	}
	 
	public static void printSection(String s) 
	{ 
		s = "=[ " + s + " ]"; 
		while (s.length() < 78) 
			s = "-" + s; 
		_log.info(s); 
	} 
}