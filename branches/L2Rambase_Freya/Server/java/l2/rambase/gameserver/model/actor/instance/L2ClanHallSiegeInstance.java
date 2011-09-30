/**
 * FIXME: L2ClanHallSiegeInstance - OnbypassFeedback need rewrite
 * Need add all war ClanHalls
 * @author: L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.text.SimpleDateFormat;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.instancemanager.clanhallsiege.BanditStrongholdSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.DevastatedCastleSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfResistanceSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.FortressOfTheDeadSiege;
import l2.rambase.gameserver.instancemanager.clanhallsiege.WildBeastFarmSiege;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.network.serverpackets.ValidateLocation;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2ClanHallSiegeInstance extends L2NpcInstance
{
	public L2ClanHallSiegeInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2ClanHallSiegeInstance); 
	}

	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		if (!canTarget(player))
			return;
		// Check if the L2PcInstance already target the L2NpcInstance
		if (this != player.getTarget())
		{
			// Set the target of the L2PcInstance player
			player.setTarget(this);
			// Send a Server->Client packet MyTargetSelected to the L2PcInstance
			// player
			MyTargetSelected my = new MyTargetSelected(getObjectId(), 0);
			player.sendPacket(my);
			// Send a Server->Client packet ValidateLocation to correct the
			// L2NpcInstance position and heading on the client
			player.sendPacket(new ValidateLocation(this));
		}
		else
		{
			// Calculate the distance between the L2PcInstance and the
			// L2NpcInstance
			if (!canInteract(player))
				// Notify the L2PcInstance AI with AI_INTENTION_INTERACT
				player.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, this);
			else
				showChatWindow(player, 0);
		}
		// Send a Server->Client ActionFailed to the L2PcInstance in order to
		// avoid that the client wait another packet
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}

	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (command.startsWith("Chat"))
		{
			int val = 0;
			try
			{
				val = Integer.parseInt(command.substring(5));
			}
			catch (IndexOutOfBoundsException ioobe)
			{
			}
			catch (NumberFormatException nfe)
			{
			}
			showChatWindow(player, val);
		}
		else if (command.startsWith("Registration"))
		{
			L2Clan playerClan = player.getClan();
			NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
			String str;
			str = "<html><body>Newspaper!<br>";
			switch (getTemplate().getNpcId())
			{
				//BanditStronghold
				case 35437:
					if (!BanditStrongholdSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						showChatWindow(player, 1);
						return;
					}
					if (BanditStrongholdSiege.getInstance().clanhall.getOwnerId() == playerClan.getClanId())
					{
						str += "Your clan is already registered for the siege, what more do you want from me?<br>";
						str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Add / remove a member of the siege</a><br>";
					}
					else
					{
						if (BanditStrongholdSiege.getInstance().isClanOnSiege(playerClan))
						{
							str += "Your clan is already registered for the siege, what more do you want from me?<br>";
							str += "<a action=\"bypass -h npc_%objectId%_UnRegister\">Unsubscribe</a><br>";
							str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Add / remove a member of the siege</a><br>";
						}
						else
						{
							int res = BanditStrongholdSiege.getInstance().registerClanOnSiege(player, playerClan);
							if (res == 0)
							{
								str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully registered for the siege clan hall.<br>";
								str += "Now you need to select no more than 18 igokov who will take part in the siege, a member of your clan.<br>";
								str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Select members of the siege</a><br>";
							}
							else if (res == 1)
							{
								str += "You have not passed the test and did not qualify for participation in the siege of Robbers<br>";
								str += "Come back when you're done.";
							}
							else if (res == 2)
							{
								str += "Unfortunately, you are late. Five tribal leaders have already filed an application for registration.<br>";
								str += "Next time be more powerful";
							}
						}
					}
					break;
				//WildBeastFarm
				case 35627:
					if (!WildBeastFarmSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						showChatWindow(player, 1);
						return;
					}
					if (WildBeastFarmSiege.getInstance().clanhall.getOwnerId() == playerClan.getClanId())
					{
						str += "Your clan is already registered for the siege, what more do you want from me?<br>";
						str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Add / remove a member of the siege</a><br>";
					}
					else
					{
						if (WildBeastFarmSiege.getInstance().isClanOnSiege(playerClan))
						{
							str += "Your clan is already registered for the siege, what more do you want from me?<br>";
							str += "<a action=\"bypass -h npc_%objectId%_UnRegister\">Unsubscribe</a><br>";
							str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Add / remove a member of the siege</a><br>";
						}
						else
						{
							int res = WildBeastFarmSiege.getInstance().registerClanOnSiege(player, playerClan);
							if (res == 0)
							{
								str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully registered for the siege clan hall.<br>";
								str += "Now you need to select no more than 18 igokov who will take part in the siege, a member of your clan.<br>";
								str += "<a action=\"bypass -h npc_%objectId%_PlayerList\">Select members of the siege</a><br>";
							}
							else if (res == 1)
							{
								str += "You have not passed the test and did not qualify for participation in the siege of Robbers<br>";
								str += "Come back when you're done.";
							}
							else if (res == 2)
							{
								str += "Unfortunately, you are late. Five tribal leaders have already filed an application for registration.<br>";
								str += "Next time be more raztoropny.";
							}
						}
					}
					break;
				//FortressOfTheDead
				case 35639:
					if (!FortressOfTheDeadSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						showChatWindow(player, 1);
						return;
					}
					if (FortressOfTheDeadSiege.getInstance().clanhall.getOwnerId() == playerClan.getClanId())
					{
						str += "Your clan is already registered for the siege, what more do you want from me?<br>";
					}
					else
					{
						if (FortressOfTheDeadSiege.getInstance().isClanOnSiege(playerClan))
						{
							str += "Your clan is already registered for the siege, what more do you want from me?<br>";
							str += "<a action=\"bypass -h npc_%objectId%_UnRegister\">Unsubscribe</a><br>";
						}
						else
						{
							int res = FortressOfTheDeadSiege.getInstance().registerClanOnSiege(player, playerClan);
							if (res == 0)
							{
								str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully registered for the siege clan hall.<br>";
								str += "Now you need to select no more than 18 igokov who will take part in the siege, a member of your clan.<br>";
							}
							else if (res == 1)
							{
								str += "You have participation in the siege";
							}
							else if (res == 2)
							{
								str += "Unfortunately, you are late. Five tribal leaders have already filed an application for registration.<br>";
							}
						}
					}
					break;
				//FortressOfResistance - need rewrite
				case 35382:
					if (!FortressOfResistanceSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						showChatWindow(player, 1);
						return;
					}
					if (FortressOfResistanceSiege.getInstance().clanhall.getOwnerId() == playerClan.getClanId())
					{
						str += "Your clan is already registered for the siege, what more do you want from me?<br>";
					}
					else
					{
						if (FortressOfResistanceSiege.getInstance().isClanOnSiege(playerClan))
						{
							str += "Your clan is already registered for the siege, what more do you want from me?<br>";
							str += "<a action=\"bypass -h npc_%objectId%_UnRegister\">Unsubscribe</a><br>";
						}
						else
						{
							int res = FortressOfResistanceSiege.getInstance().registerClanOnSiege(player, playerClan);
							if (res == 0)
							{
								str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully registered for the siege clan hall.<br>";
								str += "Now you need to select no more than 18 igokov who will take part in the siege, a member of your clan.<br>";
							}
							else if (res == 1)
							{
								str += "You have participation in the siege";
							}
							else if (res == 2)
							{
								str += "Unfortunately, you are late. Five tribal leaders have already filed an application for registration.<br>";
							}
						}
					}
					break;
				//DevastatedCastle - need rewrite
				case 35420:
					if (!DevastatedCastleSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						showChatWindow(player, 1);
						return;
					}
					if (DevastatedCastleSiege.getInstance().clanhall.getOwnerId() == playerClan.getClanId())
					{
						str += "Your clan is already registered for the siege, what more do you want from me?<br>";
					}
					else
					{
						if (DevastatedCastleSiege.getInstance().isClanOnSiege(playerClan))
						{
							str += "Your clan is already registered for the siege, what more do you want from me?<br>";
							str += "<a action=\"bypass -h npc_%objectId%_UnRegister\">Unsubscribe</a><br>";
						}
						else
						{
							int res = DevastatedCastleSiege.getInstance().registerClanOnSiege(player, playerClan);
							if (res == 0)
							{
								str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully registered for the siege clan hall.<br>";
								str += "Now you need to select no more than 18 igokov who will take part in the siege, a member of your clan.<br>";
							}
							else if (res == 1)
							{
								str += "You have participation in the siege";
							}
							else if (res == 2)
							{
								str += "Unfortunately, you are late. Five tribal leaders have already filed an application for registration.<br>";
							}
						}
					}
					break;
			}
			str += "</body></html>";
			html.setHtml(str);
			html.replace("%objectId%", String.valueOf(getObjectId()));
			player.sendPacket(html);
		}
		else if (command.startsWith("UnRegister"))
		{
			L2Clan playerClan = player.getClan();
			NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
			String str;		

			switch (getTemplate().getNpcId())
			{
				//BanditStronghold
				case 35437:
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						return;
					}
					if (!BanditStrongholdSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (BanditStrongholdSiege.getInstance().isClanOnSiege(playerClan))
					{
						if (BanditStrongholdSiege.getInstance().unRegisterClan(playerClan))
						{
							str = "<html><body>Newspaper!<br>";
							str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully removed from the register at the siege clan hall.<br>";
							str += "</body></html>";
							html.setHtml(str);
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					}
					break;
				//WildBeastFarm
				case 35627:
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						return;
					}
					if (!WildBeastFarmSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (WildBeastFarmSiege.getInstance().isClanOnSiege(playerClan))
					{
						if (WildBeastFarmSiege.getInstance().unRegisterClan(playerClan))
						{
							str = "<html><body>Newspaper!<br>";
							str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully removed from the register at the siege clan hall.<br>";
							str += "</body></html>";
							html.setHtml(str);
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					}
					break;					
				//FortressOfTheDead
				case 35639:
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						return;
					}
					if (!FortressOfTheDeadSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (FortressOfTheDeadSiege.getInstance().isClanOnSiege(playerClan))
					{
						if (FortressOfTheDeadSiege.getInstance().unRegisterClan(playerClan))
						{
							str = "<html><body>Newspaper!<br>";
							str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully removed from the register at the siege clan hall.<br>";
							str += "</body></html>";
							html.setHtml(str);
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					}
					break;
				//FortressOfResistance - need rewrite
				case 33333:
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						return;
					}
					if (!FortressOfResistanceSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (FortressOfResistanceSiege.getInstance().isClanOnSiege(playerClan))
					{
						if (FortressOfResistanceSiege.getInstance().unRegisterClan(playerClan))
						{
							str = "<html><body>Newspaper!<br>";
							str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully removed from the register at the siege clan hall.<br>";
							str += "</body></html>";
							html.setHtml(str);
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					}
					break;
				//DevastatedCastle -- need rewrite
				case 35420:
					if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
					{
						return;
					}
					if (!DevastatedCastleSiege.getInstance().isRegistrationPeriod())
					{
						showChatWindow(player, 3);
						return;
					}
					if (DevastatedCastleSiege.getInstance().isClanOnSiege(playerClan))
					{
						if (DevastatedCastleSiege.getInstance().unRegisterClan(playerClan))
						{
							str = "<html><body>Newspaper!<br>";
							str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>, successfully removed from the register at the siege clan hall.<br>";
							str += "</body></html>";
							html.setHtml(str);
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					}
					break;
				
			}
		}
		else if (command.startsWith("PlayerList"))
		{
			L2Clan playerClan = player.getClan();
			if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
			{
				return;
			}
			if (!BanditStrongholdSiege.getInstance().isRegistrationPeriod())
			{
				showChatWindow(player, 3);
				return;
			}
			if (BanditStrongholdSiege.getInstance().isClanOnSiege(playerClan))
			{
				showPlayersList(playerClan, player);
			}
		}
		else if (command.startsWith("addPlayer"))
		{
			L2Clan playerClan = player.getClan();
			if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
			{
				return;
			}
			if (!BanditStrongholdSiege.getInstance().isRegistrationPeriod())
			{
				showChatWindow(player, 3);
				return;
			}
			String val = command.substring(10);
			if (playerClan.getClanMember(val) == null)
				return;
			BanditStrongholdSiege.getInstance().addPlayer(playerClan, val);
			if (BanditStrongholdSiege.getInstance().isClanOnSiege(playerClan))
			{
				showPlayersList(playerClan, player);
			}
		}
		else if (command.startsWith("removePlayer"))
		{
			L2Clan playerClan = player.getClan();
			if (playerClan == null || playerClan.getLeaderName() != player.getName() || playerClan.getLevel() < 4)
			{
				return;
			}
			if (!BanditStrongholdSiege.getInstance().isRegistrationPeriod())
			{
				showChatWindow(player, 3);
				return;
			}
			String val = command.substring(13);
			if (playerClan.getClanMember(val) != null)
			{
				BanditStrongholdSiege.getInstance().removePlayer(playerClan, val);
			}
			if (BanditStrongholdSiege.getInstance().isClanOnSiege(playerClan))
			{
				showPlayersList(playerClan, player);
			}
		}
	}

	public void showPlayersList(L2Clan playerClan, L2PcInstance player)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		String str;		
		str = "<html><body>Newspaper!<br>";
		str += "Your clan : <font color=\"LEVEL\">" + player.getClan().getName() + "</font>. select participants for the siege.<br><br>";
		str += "<img src=\"L2UI.SquareWhite\" width=280 height=1>";
		str += "<table width=280 border=0 bgcolor=\"000000\"><tr><td width=170 align=center>Register bathrooms</td><td width=110 align=center>Action</td></tr></table>";
		str += "<img src=\"L2UI.SquareWhite\" width=280 height=1>";
		str += "<table width=280 border=0>";
		for (String temp : BanditStrongholdSiege.getInstance().getRegisteredPlayers(playerClan))
		{
			str += "<tr><td width=170>" + temp + "</td><td width=110 align=center><a action=\"bypass -h npc_%objectId%_removePlayer " + temp + "\"> Remove</a></td></tr>";
		}
		str += "</table>";
		str += "<img src=\"L2UI.SquareWhite\" width=280 height=1>";
		str += "<table width=280 border=0 bgcolor=\"000000\"><tr><td width=170 align=center>Clan Members</td><td width=110 align=center>Action</td></tr></table>";
		str += "<img src=\"L2UI.SquareWhite\" width=280 height=1>";
		str += "<table width=280 border=0>";
		for (L2ClanMember temp : playerClan.getMembers())
		{
			if (!BanditStrongholdSiege.getInstance().getRegisteredPlayers(playerClan).contains(temp.getName()))
				str += "<tr><td width=170>" + temp.getName() + "</td><td width=110 align=center><a action=\"bypass -h npc_%objectId%_addPlayer " + temp.getName() + "\"> Add</a></td></tr>";
		}
		str += "</table>";
		str += "</body></html>";
		html.setHtml(str);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}

	@Override
	public void showChatWindow(L2PcInstance player, int val)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		long startSiege = 0;
		int npcId = getTemplate().getNpcId();
		String filename;
		if (val == 0)
			filename = "data/html/siege/clanhall/" + npcId + ".htm";
		else
			filename = "data/html/siege/clanhall/" + npcId + "-" + val + ".htm";
		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getHtmlPrefix(), filename);
		ClanHall clanhall = null;
		String clans = "";
		clans += "<table width=280 border=0>";
		int clanCount = 0;
		switch (npcId)
		{
			//FortressOfResistance
			case 35382:
				startSiege = FortressOfResistanceSiege.getInstance().getSiegeDate().getTimeInMillis();
				break;
			//BanditStronghold
			case 35437:
				clanhall = ClanHallManager.getInstance().getClanHallById(35);
				startSiege = BanditStrongholdSiege.getInstance().getSiegeDate().getTimeInMillis();
				for (String a : BanditStrongholdSiege.getInstance().getRegisteredClans())
				{
					clanCount++;
					clans += "<tr><td><font color=\"LEVEL\">" + a + "</font>  (Number :" + BanditStrongholdSiege.getInstance().getPlayersCount(a) + "people.)</td></tr>";
				}
				break;
			//WildBeastFarm
			case 35627:
				clanhall = ClanHallManager.getInstance().getClanHallById(63);
				startSiege = WildBeastFarmSiege.getInstance().getSiegeDate().getTimeInMillis();
				for (String a : WildBeastFarmSiege.getInstance().getRegisteredClans())
				{
					clanCount++;
					clans += "<tr><td><font color=\"LEVEL\">" + a + "</font>  (Number :" + BanditStrongholdSiege.getInstance().getPlayersCount(a) + "people.)</td></tr>";
				}
				break;
			//Devastated Castle
			case 35420:
				clanhall = ClanHallManager.getInstance().getClanHallById(64);
				startSiege = DevastatedCastleSiege.getInstance().getSiegeDate().getTimeInMillis();
				for (String a : DevastatedCastleSiege.getInstance().getRegisteredClans())
				{
					clanCount++;
					clans += "<tr><td><font color=\"LEVEL\">" + a + "</font></td></tr>";
				}
				break;
		}
		while (clanCount < 5)
		{
			switch (npcId)
			{
				//FortressOfResistance
				case 35382:
					clans += "<tr><td><font color=\"LEVEL\">**Not logged**</font></td></tr>";
					clanCount++;
					break;
				//BanditStronghold
				case 35437:
					clans += "<tr><td><font color=\"LEVEL\">**Not logged**</font>  (Quantity : people.)</td></tr>";
					clanCount++;
					break;
				//WildBeastFarm
				case 35627:
					clans += "<tr><td><font color=\"LEVEL\">**Not logged**</font>  (Quantity : people.)</td></tr>";
					clanCount++;
					break;
				//Devastated Castle
				case 35420:	
					clans += "<tr><td><font color=\"LEVEL\">**Not logged**</font></td></tr>";
					clanCount++;
					break;
			}
		}
		clans += "</table>";
		html.replace("%clan%", String.valueOf(clans));
		int clan = clanhall.getOwnerId();
		String clanName;
		if (clan == 0)
			clanName = "NPC";
		else
			clanName = ClanTable.getInstance().getClan(clanhall.getOwnerId()).getName();			
		html.replace("%clanname%", String.valueOf(clanName));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		html.replace("%SiegeDate%", String.valueOf(format.format(startSiege)));
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}
}