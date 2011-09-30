/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import l2.rambase.Base64;
import l2.rambase.Config;
import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.GmListTable;
import l2.rambase.gameserver.LoginServerThread;
import l2.rambase.gameserver.SevenSigns;
import l2.rambase.gameserver.TaskPriority;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.communitybbs.Manager.RegionBBSManager;
import l2.rambase.gameserver.datatables.AdminCommandAccessRights;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.event.L2Event;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.instancemanager.CoupleManager;
import l2.rambase.gameserver.instancemanager.CursedWeaponsManager;
import l2.rambase.gameserver.instancemanager.DimensionalRiftManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.FortSiegeManager;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.instancemanager.PetitionManager;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.instancemanager.SiegeManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2ClassMasterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.entity.Couple;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.model.entity.FortSiege;
import l2.rambase.gameserver.model.entity.Hero;
//import l2.rambase.gameserver.model.entity.L2Event;
import l2.rambase.gameserver.model.entity.Siege;
//import l2.rambase.gameserver.model.entity.TvTEvent;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.Die;
import l2.rambase.gameserver.network.serverpackets.EtcStatusUpdate;
import l2.rambase.gameserver.network.serverpackets.ExBasicActionList;
import l2.rambase.gameserver.network.serverpackets.ExBirthdayPopup;
import l2.rambase.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2.rambase.gameserver.network.serverpackets.ExGetBookMarkInfoPacket;
import l2.rambase.gameserver.network.serverpackets.ExNoticePostArrived;
import l2.rambase.gameserver.network.serverpackets.ExPCCafePointInfo;
import l2.rambase.gameserver.network.serverpackets.ExShowScreenMessage;
import l2.rambase.gameserver.network.serverpackets.ExStorageMaxCount;
import l2.rambase.gameserver.network.serverpackets.FriendList;
import l2.rambase.gameserver.network.serverpackets.HennaInfo;
import l2.rambase.gameserver.network.serverpackets.ItemList;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.network.serverpackets.PledgeShowMemberListAll;
import l2.rambase.gameserver.network.serverpackets.PledgeShowMemberListUpdate;
import l2.rambase.gameserver.network.serverpackets.PledgeSkillList;
import l2.rambase.gameserver.network.serverpackets.PledgeStatusChanged;
import l2.rambase.gameserver.network.serverpackets.QuestList;
import l2.rambase.gameserver.network.serverpackets.ShortCutInit;
import l2.rambase.gameserver.network.serverpackets.SkillCoolTime;
import l2.rambase.gameserver.network.serverpackets.StopMove;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.UserInfo;
import l2.rambase.gameserver.skills.AbnormalEffect;
import l2.rambase.util.Rnd;


/**
 * Enter World Packet Handler<p>
 * <p>
 * 0000: 03 <p>
 * packet format rev87 bddddbdcccccccccccccccccccc
 * <p>
 */
public class EnterWorld extends L2GameClientPacket
{
	private static final String _C__03_ENTERWORLD = "[C] 03 EnterWorld";

	private static Logger _log = Logger.getLogger(EnterWorld.class.getName());
		
	private String _userHost;
	private String _routeHop1;
	private String _routeHop2;
	private String _routeHop3;
	private String _routeHop4;

	private String[] allChars =
	{
			"Q","W","E","R","T","Y","U","I",
			"O","P","L","K","J","H","G","F",
			"D","S","A","Z","X","C","V","B",
			"N","M","1","2","3","4","5","6",
			"7","8","9","0"
	};
	
	public TaskPriority getPriority()
	{
		return TaskPriority.PR_URGENT;
	}

	@Override
	protected void readImpl()
	{
		readB(new byte[32]);	// Unknown Byte Array
		readD();				// Unknown Value
		readD();				// Unknown Value
		readD();				// Unknown Value
		readD();				// Unknown Value
		readB(new byte[32]);	// Unknown Byte Array
		readD();				// Unknown Value		
		_userHost  = readC()+"."+readC()+"."+readC()+"."+readC();
		_routeHop1 = readC()+"."+readC()+"."+readC()+"."+readC();
		_routeHop2 = readC()+"."+readC()+"."+readC()+"."+readC();
		_routeHop3 = readC()+"."+readC()+"."+readC()+"."+readC();
		_routeHop4 = readC()+"."+readC()+"."+readC()+"."+readC();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
		{
			_log.warning("EnterWorld failed! activeChar returned 'null'.");
			getClient().closeNow();
			return;
		}
		
		LoginServerThread.getInstance().sendClientTracert(activeChar.getAccountName(),
				_userHost, _routeHop1, _routeHop2, _routeHop3, _routeHop4);

		// Restore to instanced area if enabled
		if (Config.RESTORE_PLAYER_INSTANCE)
			activeChar.setInstanceId(InstanceManager.getInstance().getPlayerInstance(activeChar.getObjectId()));
		else
		{
			int instanceId = InstanceManager.getInstance().getPlayerInstance(activeChar.getObjectId());
			if (instanceId > 0)
			InstanceManager.getInstance().getInstance(instanceId).removePlayer(activeChar.getObjectId());
		}

		if (L2World.getInstance().findObject(activeChar.getObjectId()) != null)
		{
			if (Config.DEBUG)
				_log.warning("User already exists in Object ID map! User "+activeChar.getName()+" is a character clone.");
		}

		// Apply special GM properties to the GM when entering
		if (activeChar.isGM())
		{
			if (Config.GM_STARTUP_INVULNERABLE && AdminCommandAccessRights.getInstance().hasAccess("admin_invul", activeChar.getAccessLevel()))
				activeChar.setIsInvul(true);

			if (Config.GM_STARTUP_INVISIBLE && AdminCommandAccessRights.getInstance().hasAccess("admin_invisible", activeChar.getAccessLevel()))
				activeChar.getAppearance().setInvisible();
 
			if (Config.GM_STARTUP_SILENCE && AdminCommandAccessRights.getInstance().hasAccess("admin_silence", activeChar.getAccessLevel()))
				activeChar.setSilenceMode(true);

			if (Config.GM_STARTUP_DIET_MODE && AdminCommandAccessRights.getInstance().hasAccess("admin_diet", activeChar.getAccessLevel()))
			{
				activeChar.setDietMode(true);
				activeChar.refreshOverloaded();
			}
 
			if (Config.GM_STARTUP_AUTO_LIST && AdminCommandAccessRights.getInstance().hasAccess("admin_gmliston", activeChar.getAccessLevel()))
				GmListTable.getInstance().addGm(activeChar, false);
			else
				GmListTable.getInstance().addGm(activeChar, true);

		}

		// Set dead status if applies
		if (activeChar.getCurrentHp() < 0.5)
			activeChar.setIsDead(true);
		
		// Set Hero status if it applies
		if (Hero.getInstance().getHeroes() != null && Hero.getInstance().getHeroes().containsKey(activeChar.getObjectId()))
			activeChar.setHero(true);

		setPledgeClass(activeChar);


		// Clan related checks are here
		if (activeChar.getClan() != null)
		{
			activeChar.sendPacket(new PledgeSkillList(activeChar.getClan()));
			
			notifyClanMembers(activeChar);
			
			notifySponsorOrApprentice(activeChar);
			
			ClanHall clanHall = ClanHallManager.getInstance().getClanHallByOwner(activeChar.getClan());
			
			if (clanHall != null)
			{
				if (!clanHall.getPaid())
					activeChar.sendPacket(new SystemMessage(SystemMessageId.PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW));
			}
			
			for (Siege siege : SiegeManager.getInstance().getSieges())
			{
				if (!siege.getIsInProgress())
					continue;
				
				if (siege.checkIsAttacker(activeChar.getClan()))
				{
					activeChar.setSiegeState((byte)1);
					activeChar.setSiegeSide(siege.getCastle().getCastleId());
				}
				
				else if (siege.checkIsDefender(activeChar.getClan()))
				{
					activeChar.setSiegeState((byte)2);
					activeChar.setSiegeSide(siege.getCastle().getCastleId());
				}
			}
			
			for (FortSiege siege : FortSiegeManager.getInstance().getSieges())
			{
				if (!siege.getIsInProgress())
					continue;
				
				if (siege.checkIsAttacker(activeChar.getClan()))
				{
					activeChar.setSiegeState((byte)1);
					activeChar.setSiegeSide(siege.getFort().getFortId());
				}				
				else if (siege.checkIsDefender(activeChar.getClan()))
				{
					activeChar.setSiegeState((byte)2);
					activeChar.setSiegeSide(siege.getFort().getFortId());
				}
			}
			
			sendPacket(new PledgeShowMemberListAll(activeChar.getClan(), activeChar));
			sendPacket(new PledgeStatusChanged(activeChar.getClan()));
			
			// Residential skills support
			if (activeChar.getClan().getHasCastle() > 0)
				CastleManager.getInstance().getCastleByOwner(activeChar.getClan()).giveResidentialSkills(activeChar);
			
			if (activeChar.getClan().getHasFort() > 0)
				FortManager.getInstance().getFortByOwner(activeChar.getClan()).giveResidentialSkills(activeChar);
		}

		if (TerritoryWarManager.getInstance().getRegisteredTerritoryId(activeChar) > 0) 
		{ 
			if (TerritoryWarManager.getInstance().isTWInProgress()) 
				activeChar.setSiegeState((byte)1); 
			activeChar.setSiegeSide(TerritoryWarManager.getInstance().getRegisteredTerritoryId(activeChar)); 
		} 
	 	 		 
		// Updating Seal of Strife Buff/Debuff 
		if (SevenSigns.getInstance().isSealValidationPeriod() && SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_STRIFE) != SevenSigns.CABAL_NULL)
		{
			int cabal = SevenSigns.getInstance().getPlayerCabal(activeChar.getObjectId()); 
			if (cabal != SevenSigns.CABAL_NULL) 
			{ 
				if (cabal == SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_STRIFE))
					activeChar.addSkill(SkillTable.FrequentSkill.THE_VICTOR_OF_WAR.getSkill());
				else
					activeChar.addSkill(SkillTable.FrequentSkill.THE_VANQUISHED_OF_WAR.getSkill());
			}
		}
		else
		{
			activeChar.removeSkill(SkillTable.FrequentSkill.THE_VICTOR_OF_WAR.getSkill());
			activeChar.removeSkill(SkillTable.FrequentSkill.THE_VANQUISHED_OF_WAR.getSkill());
		}

		if (Config.ENABLE_VITALITY && Config.RECOVER_VITALITY_ON_RECONNECT)
		{
			float points = Config.RATE_RECOVERY_ON_RECONNECT * (System.currentTimeMillis() - activeChar.getLastAccess()) / 60000;
			if (points > 0)
				activeChar.updateVitalityPoints(points, false, true);
		}

		activeChar.sendPacket(new UserInfo(activeChar));

		sendPacket(new ExBrExtraUserInfo(activeChar));

		// Send Macro List
		activeChar.getMacroses().sendUpdate();

		// Send Item List
		sendPacket(new ItemList(activeChar, false));

		// Send GG check
		activeChar.queryGameGuard();

		// Send Teleport Bookmark List
		sendPacket(new ExGetBookMarkInfoPacket(activeChar));
		
		// Send Shortcuts
		sendPacket(new ShortCutInit(activeChar));

		// Send Action list
		activeChar.sendPacket(new ExBasicActionList(activeChar));

		// Send Skill list
		activeChar.sendSkillList();

		// Send Dye Information
		activeChar.sendPacket(new HennaInfo(activeChar));

		Quest.playerEnter(activeChar);

		if (!Config.DISABLE_TUTORIAL)
			loadTutorial(activeChar);

		for (Quest quest : QuestManager.getInstance().getAllManagedScripts())
		{
			if (quest != null && quest.getOnEnterWorld())
				quest.notifyEnterWorld(activeChar);
		}
		activeChar.sendPacket(new QuestList());

		if (Config.PLAYER_SPAWN_PROTECTION > 0)
			activeChar.setProtection(true);

		activeChar.spawnMe(activeChar.getX(), activeChar.getY(), activeChar.getZ());
		
		if (L2Event.active && L2Event.connectionLossData.containsKey(activeChar.getName()) && L2Event.isOnEvent(activeChar))
			L2Event.restoreChar(activeChar);
		else if (L2Event.connectionLossData.containsKey(activeChar.getName()))
			L2Event.restoreAndTeleChar(activeChar);
		
		// Wedding Checks
		if (Config.MOD_ALLOW_WEDDING)
		{
			engage(activeChar);
			notifyPartner(activeChar,activeChar.getPartnerId());
		}

		if (activeChar.isCursedWeaponEquipped()) 
		{
			CursedWeaponsManager.getInstance().getCursedWeapon(activeChar.getCursedWeaponEquippedId()).cursedOnLogin();
		}

		activeChar.updateEffectIcons();

		activeChar.sendPacket(new EtcStatusUpdate(activeChar));
		if(activeChar.getPcBangPoints() > 0)
			activeChar.sendPacket(new ExPCCafePointInfo(activeChar.getPcBangPoints(), 0, false, false, 1));
		else
			activeChar.sendPacket(new ExPCCafePointInfo());

		//Expand Skill
		activeChar.sendPacket(new ExStorageMaxCount(activeChar));

		sendPacket(new FriendList());
		
		SystemMessage sm = new SystemMessage(SystemMessageId.FRIEND_S1_HAS_LOGGED_IN);
		sm.addString(activeChar.getName());
		for (int id : activeChar.getFriendList())
		{
			L2Object obj = L2World.getInstance().findObject(id);
			if (obj != null)
				obj.sendPacket(sm);
		}

		sendPacket(new SystemMessage(SystemMessageId.WELCOME_TO_LINEAGE));
		activeChar.sendMessage("This server uses L2Millenium files, a project founded and developed by Deyoun");
		activeChar.sendMessage("Copyright 2008-2011");
		SevenSigns.getInstance().sendCurrentPeriodMsg(activeChar);
		Announcements.getInstance().showAnnouncements(activeChar);

		if (Config.PETITIONING_ALLOWED)
			PetitionManager.getInstance().checkPetitionMessages(activeChar);

		if (activeChar.isAlikeDead()) // dead or fake dead
		{
			// no broadcast needed since the player will already spawn dead to others
			sendPacket(new Die(activeChar));
		}
		
		//if(Config.LOGIN_ATTEMPT_CHECK_ENABLE)
        //    LoginServerThread.getInstance().displayFailedLoginAttemptsCount(activeChar.getAccountName());

		//notifyFriends(activeChar);
		activeChar.onPlayerEnter();

		sendPacket(new SkillCoolTime(activeChar));

		for (L2ItemInstance i : activeChar.getInventory().getItems())
		{
			if (i.isTimeLimitedItem())
				i.scheduleLifeTimeTask();
			if (i.isShadowItem() && i.isEquipped()) 
				i.decreaseMana(false);
		}
		
		for (L2ItemInstance i : activeChar.getWarehouse().getItems())
		{
			if (i.isTimeLimitedItem())
				i.scheduleLifeTimeTask();
		}

		if (DimensionalRiftManager.getInstance().checkIfInRiftZone(activeChar.getX(), activeChar.getY(), activeChar.getZ(), false))
			DimensionalRiftManager.getInstance().teleportToWaitingRoom(activeChar);

		if (activeChar.getClanJoinExpiryTime() > System.currentTimeMillis())
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CLAN_MEMBERSHIP_TERMINATED));

		// remove combat flag before teleporting
		if (activeChar.getInventory().getItemByItemId(9819) != null)
		{
			Fort fort = FortManager.getInstance().getFort(activeChar);
			
			if (fort != null)
			{
				FortSiegeManager.getInstance().dropCombatFlag(activeChar, fort.getFortId());
			}
			else
			{
				int slot = activeChar.getInventory().getSlotFromItem(activeChar.getInventory().getItemByItemId(9819));
				activeChar.getInventory().unEquipItemInBodySlot(slot);
				activeChar.destroyItem("CombatFlag", activeChar.getInventory().getItemByItemId(9819), null, true);
			}

		}

		// Attacker or spectator logging in to a siege zone. Actually should be checked for inside castle only?
		if (!activeChar.isGM()
				// inside siege zone
				&& activeChar.isInsideZone(L2Character.ZONE_SIEGE)
				// but non-participant or attacker
				&& (!activeChar.isInSiege() || activeChar.getSiegeState() < 2))
			activeChar.teleToLocation(MapRegionTable.TeleportWhereType.Town);

		if (Config.ALLOW_MAIL)
		{
			if (MailManager.getInstance().hasUnreadPost(activeChar))
				sendPacket(ExNoticePostArrived.valueOf(false));
		}

		RegionBBSManager.getInstance().changeCommunityBoard();

		/**
		 * Fight Events
		 */
		TvTEvent.onLogin(activeChar);
		PvPEvent.onLogin(activeChar);

		if (Config.WELCOME_MESSAGE_ENABLED)
			activeChar.sendPacket(new ExShowScreenMessage(Config.WELCOME_MESSAGE_TEXT, Config.WELCOME_MESSAGE_TIME));

		L2ClassMasterInstance.showQuestionMark(activeChar);
		
		int birthday = activeChar.checkBirthDay();
		if (birthday == 0)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOUR_BIRTHDAY_GIFT_HAS_ARRIVED));
			activeChar.sendPacket(new ExBirthdayPopup());
		}
		else if (birthday != -1)
		{
			sm = new SystemMessage(SystemMessageId.THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY);
			sm.addString(Integer.toString(birthday));
			activeChar.sendPacket(sm);
		}
		
		if (Config.MOD_DUALBOX_ENABLE)
		{
			Collection<L2PcInstance>  allPlayers = L2World.getInstance().getAllPlayers().values();  
			L2PcInstance[] players = allPlayers.toArray(new L2PcInstance[allPlayers.size()]);  
			
			Map<String,List<L2PcInstance>> ipMap = new HashMap<String, List<L2PcInstance>>();  
			
			String ip = "0.0.0.0";  
			L2GameClient client;  
			
			//final Map<String, String> dualboxIPs = new HashMap<String, String>();  
			
			for (L2PcInstance player: players)  
			{  
				client = player.getClient();
				if (player.isGM())
				{
					continue;
				}
				if (!client.isDetached())  
				{  
					String pcIp = getPcIP(player);
					String lastip = client.getConnection().getInetAddress().getHostAddress();
					ip = lastip + pcIp;
					if (ipMap.get(ip) == null)
					{
						ipMap.put(ip, new ArrayList<L2PcInstance>()); 
					}
					ipMap.get(ip).add(player);
					if (ipMap.get(ip).size() >= Config.MOD_COUNTBOXES)
					{
						player.closeNetConnection(true);
					}
				}
				else
				{
					continue;
				}
			}
		}		
        if(MailManager.getInstance().hasUnreadPost(activeChar))
        {
            activeChar.sendPacket(ExNoticePostArrived.valueOf(true));
            activeChar.sendPacket(SystemMessageId.UNREAD_MAIL);
        }
        if(activeChar.isTransformed() && activeChar.getTransformationId() == 105)
        	activeChar.untransform();        
        
        /** Activate onEnterWorld Captcha **/
        if (Config.MOD_CAPTCHA_ONLOGIN)
        {        	
        	if (activeChar.isGM())
        	{
        		if (!Config.MOD_CAPTCHA_GM_DISABLE)
        		{
        			String StringValue = generateCaptchaWord();
        			String StringChar = allChars[Rnd.get(allChars.length - 1)];
        			activeChar.setCaptchaName(countOccurrences(StringValue, StringChar));
        			activeChar.startAbnormalEffect(AbnormalEffect.REAL_TARGET);
        			activeChar.setIsParalyzed(true);
        			StopMove smP = new StopMove(activeChar);					
        			if (activeChar.isInvul())
        			{
        				activeChar.setIsInvul(false);
        				activeChar.setIsInvul(true);
        			}
        			else
        			{
        				activeChar.setIsInvul(true);
        			}
        			activeChar.sendPacket(smP);
        			String filename = "data/html/ecaptcha.htm";
        			String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), filename);
        			content = content.replaceAll("%int_char%",StringChar);
        			content = content.replaceAll("%code%", StringValue);
        			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 );
        			npcHtmlMessage.setHtml(content);
        			activeChar.broadcastUserInfo();
        			activeChar.sendPacket(npcHtmlMessage);        			
        		}
        	}
        	else
        	{
        		String StringValue = generateCaptchaWord();
        		String StringChar = allChars[Rnd.get(allChars.length - 1)];
        		activeChar.setCaptchaName(countOccurrences(StringValue, StringChar));
        		activeChar.startAbnormalEffect(AbnormalEffect.REAL_TARGET);
        		activeChar.setIsParalyzed(true);
        		StopMove smP = new StopMove(activeChar);					
        		if (activeChar.isInvul())
        		{
        			activeChar.setIsInvul(false);
        			activeChar.setIsInvul(true);
        		}
        		else
        		{
        			activeChar.setIsInvul(true);
        		}
        		activeChar.sendPacket(smP);
        		String filename = "data/html/ecaptcha.htm";
        		String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), filename);
        		content = content.replaceAll("%int_char%",StringChar);
        		content = content.replaceAll("%code%", StringValue);
        		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 );
        		npcHtmlMessage.setHtml(content);
        		activeChar.broadcastUserInfo();
        		activeChar.sendPacket(npcHtmlMessage);
        	}
        }
	}	

	private String generateCaptchaWord() 
	{
		String captchaWord = "";		
		for (int i=0;i<6;i++)
		{
			try
			{
				captchaWord += allChars[Rnd.get(allChars.length-1)];
			}
			catch(Exception e)
			{				
			}
		}
		return captchaWord;
	}

	private static String countOccurrences(String haystack, String needle)
	{
		int count = 0;
		for (int i=0; i < haystack.length(); i++)
		{
			if (haystack.charAt(i) == needle.charAt(0))
			{
				count++;
			}
		}
		return String.valueOf(count);
	}
	
	private String getPcIP(L2PcInstance plyr)
	{
		Connection con = null; 
		String status = "";
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT pcIp FROM accounts WHERE login = ? ORDER BY login ASC");
			statement.setString(1, plyr.getAccountName());
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				status = result.getString("pcIp");
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			_log.warning("Couldnt load account data: " + e.getMessage());
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
			}
		}               
		return status;
	}
	
	/**
	 * @param activeChar
	 */
	private void engage(L2PcInstance cha)
	{
		int _chaid = cha.getObjectId();

		for(Couple cl: CoupleManager.getInstance().getCouples())
		{
			if (cl.getPlayer1Id()==_chaid || cl.getPlayer2Id()==_chaid)
			{
				if (cl.getMaried())
					cha.setMarried(true);

				cha.setCoupleId(cl.getId());

				if (cl.getPlayer1Id()==_chaid)
					cha.setPartnerId(cl.getPlayer2Id());

				else
					cha.setPartnerId(cl.getPlayer1Id());
			}
		}
	}

	/**
	* @param activeChar partnerid
	*/
	private void notifyPartner(L2PcInstance cha, int partnerId) 
	{ 
		if (cha.getPartnerId() != 0) 
		{
			int objId = cha.getPartnerId();

			try
			{
				L2PcInstance partner = L2World.getInstance().getPlayer(objId);

				if (partner != null)
					partner.sendMessage("Your Partner has logged in.");

				partner = null;
			}
			catch (ClassCastException cce)
			{
				_log.warning("Wedding Error: ID "+objId+" is now owned by a(n) "+L2World.getInstance().findObject(objId).getClass().getSimpleName());
			}
		}
	}

	/**
	* @param activeChar
	*/
	private void notifyClanMembers(L2PcInstance activeChar)
	{
		L2Clan clan = activeChar.getClan();

		// This null check may not be needed anymore since notifyClanMembers is called from within a null check already. Please remove if we're certain it's ok to do so.
		if (clan != null)
		{
			clan.getClanMember(activeChar.getObjectId()).setPlayerInstance(activeChar);
			SystemMessage msg = new SystemMessage(SystemMessageId.CLAN_MEMBER_S1_LOGGED_IN);
			msg.addString(activeChar.getName());
			clan.broadcastToOtherOnlineMembers(msg, activeChar);
			msg = null;
			clan.broadcastToOtherOnlineMembers(new PledgeShowMemberListUpdate(activeChar), activeChar);
		}
	}

	/**
	* @param activeChar
	*/
	private void notifySponsorOrApprentice(L2PcInstance activeChar)
	{
		if (activeChar.getSponsor() != 0)
		{
			L2PcInstance sponsor = L2World.getInstance().getPlayer(activeChar.getSponsor());

			if (sponsor != null)
			{
				SystemMessage msg = new SystemMessage(SystemMessageId.YOUR_APPRENTICE_S1_HAS_LOGGED_IN);
				msg.addString(activeChar.getName());
				sponsor.sendPacket(msg);
			}
		}
		else if (activeChar.getApprentice() != 0)
		{
			L2PcInstance apprentice = L2World.getInstance().getPlayer(activeChar.getApprentice());

			if (apprentice != null)
			{
				SystemMessage msg = new SystemMessage(SystemMessageId.YOUR_SPONSOR_C1_HAS_LOGGED_IN);
				msg.addString(activeChar.getName());
				apprentice.sendPacket(msg);
			}
		}
	}

	/**
	* @param string
	* @return
	* @throws UnsupportedEncodingException
	*/
	@SuppressWarnings("unused")
	private String getText(String string)
	{
		try
		{
			String result = new String(Base64.decode(string), "UTF-8");
			return result;
		}
		catch (UnsupportedEncodingException e)
		{
			return null;
		}
	}

	private void loadTutorial(L2PcInstance player)
	{
		QuestState qs = player.getQuestState("255_Tutorial");

		if (qs != null)
			qs.getQuest().notifyEvent("UC", null, player);
	}

	/* (non-Javadoc)
	* @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	*/
	@Override
	public String getType()
	{
		return _C__03_ENTERWORLD;
	}

	private void setPledgeClass(L2PcInstance activeChar)
	{
		int pledgeClass = 0;

		// This null check may not be needed anymore since setPledgeClass is called from within a null check already. Please remove if we're certain it's ok to do so.
		if (activeChar.getClan() != null)
			pledgeClass = activeChar.getClan().getClanMember(activeChar.getObjectId()).calculatePledgeClass(activeChar);

		if (activeChar.isNoble() && pledgeClass < 5)
			pledgeClass = 5;

		if (activeChar.isHero() && pledgeClass < 8)
			pledgeClass = 8;

		activeChar.setPledgeClass(pledgeClass);
	}
}
