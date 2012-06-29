package handlers.voicedcommandhandlers;

import l2.rambase.gameserver.SevenSigns;
import l2.rambase.gameserver.datatables.SkillTable;
import java.util.StringTokenizer;
import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.entity.ClanHall;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;



import donate.DonateConfig;


public class Donate implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS = { "aiobuff","globalgk","merchant" };

	private static int DONATE_NPC = 31213;
	
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (command.startsWith("aiobuff"))
		{			
			L2ItemInstance preItem = activeChar.getInventory().getItemByItemId(DonateConfig.AIO_MARK);
			L2ItemInstance preItem2 = activeChar.getInventory().getItemByItemId(DonateConfig.BUFF_MARK2);
			L2ItemInstance specialMemberItem = activeChar.getInventory().getItemByItemId(DonateConfig.SPECIAL_MEMBER_ITEM);
			
			if (specialMemberItem == null && preItem2 == null)
			{
				if (preItem != null) 
				{ 
					if (activeChar.isInsideZone(L2Character.ZONE_PEACE) && !activeChar.isInOlympiadMode())
					{
						if (params == null)
						{
							if (command.equalsIgnoreCase("aiobuff"))
							{
								if (DonateConfig.AIOBUFFER_STARTED)
								{
									NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
									npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
									activeChar.sendPacket(npcHtmlMessage);
								}
								else
								{
									activeChar.sendMessage("This option is disabled");
								}
							}
						}
						else
						{
							if (DonateConfig.AIOBUFFER_STARTED)
							{						
								StringTokenizer st = new StringTokenizer(params);
								try
								{
									String event = null;
									if (st.hasMoreTokens()) event = st.nextToken();
									useAioBufferCommand(activeChar,event);
									
									activeChar.broadcastStatusUpdate();													
								}
								catch (Exception e)
								{
								}
							}
							else
							{
								activeChar.sendMessage("This option is disabled");
							}
						}
					}
					else
					{
						if (DonateConfig.AIOBUFFER_STARTED)
						{
							activeChar.sendMessage("You must be in Peace Zone");
						}
						else
						{
							activeChar.sendMessage("This option is disabled");
						}
					}
				}
				else
				{
					if (DonateConfig.AIOBUFFER_STARTED)
					{
						activeChar.sendMessage("You cannot use this command. You don't have donate item in your inventory.");
					}
				}
			}
			else
			{
				if (preItem2 != null) 
				{ 
					if (!activeChar.isInsideZone(L2Character.ZONE_PEACE) || activeChar.isInOlympiadMode())
					{
						activeChar.sendMessage("You must be in Peace Zone");
						return true;
					}
				}						
				if (params == null)
				{
					NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
					npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
					activeChar.sendPacket(npcHtmlMessage);
				}
				else
				{
					StringTokenizer st = new StringTokenizer(params);
					try
					{
						String event = null;
						if (st.hasMoreTokens()) event = st.nextToken();
						useAioBufferCommand(activeChar,event);					
						activeChar.broadcastStatusUpdate();													
					}
					catch (Exception e)
					{
					}
				}
			}
		}
		else if (command.startsWith("globalgk"))
		{
			L2ItemInstance preItem = activeChar.getInventory().getItemByItemId(DonateConfig.GK_MARK);
			L2ItemInstance preItem2 = activeChar.getInventory().getItemByItemId(DonateConfig.BUFF_MARK2);
			L2ItemInstance specialMemberItem = activeChar.getInventory().getItemByItemId(DonateConfig.SPECIAL_MEMBER_ITEM);
			
			if (specialMemberItem == null && preItem2 == null)
			{
				if (preItem != null) 
				{				
					if (activeChar.isInsideZone(L2Character.ZONE_PEACE) && !activeChar.isInOlympiadMode())
					{			
						if (params == null)
						{
							if (command.equalsIgnoreCase("globalgk"))
							{
								if (DonateConfig.GLOBALGK_STARTED)
								{
									NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
									npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/start.htm");
									activeChar.sendPacket(npcHtmlMessage);
								}
								else
								{
									activeChar.sendMessage("This option is disabled");
								}
							}
						}
						else
						{
							if (DonateConfig.GLOBALGK_STARTED)
							{
								StringTokenizer st = new StringTokenizer(params);
								try
								{
									String event = null;
									if (st.hasMoreTokens()) event = st.nextToken();
									useGlobalGkCommand(activeChar,event);														
								}
								catch (Exception e)
								{					
								}
							}
							else
							{
								activeChar.sendMessage("This option is disabled");
							}
						}
					}				
					else
					{
						activeChar.sendMessage("You must be in Peace Zone");
					}	
				}
				else
				{
					if (DonateConfig.GLOBALGK_STARTED)
					{
						activeChar.sendMessage("You cannot use this command. You don't have donate item in your inventory.");
					}
				}
			}
			else
			{
				if (preItem2 != null) 
				{ 
					if (!activeChar.isInsideZone(L2Character.ZONE_PEACE) || activeChar.isInOlympiadMode())
					{
						activeChar.sendMessage("You must be in Peace Zone");
						return true;
					}
				}	
				if (params == null)
				{
					NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
					npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/start.htm");
					activeChar.sendPacket(npcHtmlMessage);
				}
				else
				{
					StringTokenizer st = new StringTokenizer(params);
					try
					{
						String event = null;
						if (st.hasMoreTokens()) event = st.nextToken();
						useGlobalGkCommand(activeChar,event);														
					}
					catch (Exception e)
					{					
					}
				}
			}
		}
		else if (command.startsWith("merchant"))
		{
			L2ItemInstance preItem = activeChar.getInventory().getItemByItemId(DonateConfig.MERCH_MARK);
			L2ItemInstance preItem2 = activeChar.getInventory().getItemByItemId(DonateConfig.BUFF_MARK2);
			L2ItemInstance specialMemberItem = activeChar.getInventory().getItemByItemId(DonateConfig.SPECIAL_MEMBER_ITEM);
			
			if (specialMemberItem == null && preItem2 == null)
			{
				if (preItem == null) 
				{ 
					if (DonateConfig.MAMMON_NPC_STARTED)
					{
						activeChar.sendMessage("You cannot use this command. You don't have donate item in your inventory.");
					}
				}		
				else if (activeChar.isInsideZone(L2Character.ZONE_PEACE) && !activeChar.isInOlympiadMode())
				{			
					if (params == null)
					{
						if (command.equalsIgnoreCase("merchant"))
						{
							if (DonateConfig.MAMMON_NPC_STARTED)
							{
								NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
								npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/start.htm");
								activeChar.sendPacket(npcHtmlMessage);
							}
							else
							{
								activeChar.sendMessage("This option is disabled");
							}
						}
					}
					else
					{
						if (DonateConfig.MAMMON_NPC_STARTED)
						{
							StringTokenizer st = new StringTokenizer(params);
							try
							{
								String event = null;
								if (st.hasMoreTokens()) event = st.nextToken();
								useMerchMammonCommand(activeChar,event);
							}
							catch (Exception e)
							{						
							}
						}
						else
						{
							activeChar.sendMessage("This option is disabled");
						}
					}
				}
				else
				{
					activeChar.sendMessage("You must be in Peace Zone");
				}
			}
			else
			{
				if (preItem2 != null) 
				{ 
					if (!activeChar.isInsideZone(L2Character.ZONE_PEACE) || activeChar.isInOlympiadMode())
					{
						activeChar.sendMessage("You must be in Peace Zone");
						return true;
					}
				}
				if (params == null)
				{
					NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
					npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/start.htm");
					activeChar.sendPacket(npcHtmlMessage);
				}
				else
				{
					StringTokenizer st = new StringTokenizer(params);
					try
					{
						String event = null;
						if (st.hasMoreTokens()) event = st.nextToken();
						useMerchMammonCommand(activeChar,event);
					}
					catch (Exception e)
					{						
					}
				}
			}
		}
		return true;
	}
	
	private void useMerchMammonCommand(L2PcInstance activeChar, String event) 
	{			
		if (event.equalsIgnoreCase("bmammon"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/" + event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("mmammon"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/" + event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("exchange"))
		{
			L2ItemInstance blueStonesAll = activeChar.getInventory().getItemByItemId(SevenSigns.SEAL_STONE_BLUE_ID);
			L2ItemInstance greenStonesAll = activeChar.getInventory().getItemByItemId(SevenSigns.SEAL_STONE_GREEN_ID);
			L2ItemInstance redStonesAll = activeChar.getInventory().getItemByItemId(SevenSigns.SEAL_STONE_RED_ID);
			
			long blueStoneCountAll = blueStonesAll == null ? 0 : blueStonesAll.getCount();
			long greenStoneCountAll = greenStonesAll == null ? 0 : greenStonesAll.getCount();
			long redStoneCountAll = redStonesAll == null ? 0 : redStonesAll.getCount();
			long ancientAdenaRewardAll = 0;
			
			ancientAdenaRewardAll = SevenSigns.calcAncientAdenaReward(blueStoneCountAll, greenStoneCountAll, redStoneCountAll);
			
			if (ancientAdenaRewardAll != 0)
			{
				if (blueStoneCountAll > 0)
					activeChar.destroyItemByItemId("SevenSigns", SevenSigns.SEAL_STONE_BLUE_ID, blueStoneCountAll, activeChar, true);
				if (greenStoneCountAll > 0)
					activeChar.destroyItemByItemId("SevenSigns", SevenSigns.SEAL_STONE_GREEN_ID, greenStoneCountAll, activeChar, true);
				if (redStoneCountAll > 0)
					activeChar.destroyItemByItemId("SevenSigns", SevenSigns.SEAL_STONE_RED_ID, redStoneCountAll, activeChar, true);
				activeChar.addAncientAdena("SevenSigns", ancientAdenaRewardAll, activeChar, true);
			}
			activeChar.sendMessage("All your Seal Stones are exchanged to Ancient Adena");
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("warehouse"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/warehouse.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("start"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/Merchant/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.startsWith("31"))
		{
			if (activeChar.getTarget() == null)
			{
				activeChar.sendMessage("For this command you must be near Celestina(Giran) and get that NPC on your target.");
			}
			else if (activeChar.getTarget().getInstanceType() != InstanceType.L2NpcInstance)
			{
				activeChar.sendMessage("For this command you must be near Celestina(Giran) and get that NPC on your target.");
			}
			else
			{
				if (!activeChar.getTarget().getName().equalsIgnoreCase("Celestina"))
				{
					activeChar.sendMessage("You don't have in target Celestina from Giran.");
				}
				else if (event.equalsIgnoreCase("311262503"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, false, 0.0);
				}
				else if (event.equalsIgnoreCase("311262501"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262504"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262505"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262502"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, false, 0.0);
				}
				else if (event.equalsIgnoreCase("311262510"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262506"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262507"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262508"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262514"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262515"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262509"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262511"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262516"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262512"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, true, 0.0);
				}
				else if (event.equalsIgnoreCase("311262513"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, false, 0.0);
				}
				else if (event.equalsIgnoreCase("311132501"))
				{
					L2Multisell.getInstance().separateAndSend(Integer.valueOf(event), activeChar, DONATE_NPC, false, 0.0);
				}
			}
		}
		else
		{
				activeChar.sendMessage("Incorrect command: use .merchant and select option");
		}
	}

	private void useGlobalGkCommand(L2PcInstance activeChar, String event) 
	{
		if (event.equalsIgnoreCase("7signs"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-aden"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-dion"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-giran"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-gludin"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-gludio"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-goddard"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-heine"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-hunters"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-oren"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-rune"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("area-schuttgart"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("primeval"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("start"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("epilogue"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("toi"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-aden"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-dion"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-giran"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-gludin"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-gludio"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-goddard"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-heine"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-hunters"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-oren"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-rune"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("towns"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("town-schuttgart"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("110"))
			activeChar.teleToLocation(82698,148638,-3473); 
		else if (event.equalsIgnoreCase("111"))
			activeChar.teleToLocation(79768,146559,-3525); 
		else if (event.equalsIgnoreCase("112"))
			activeChar.teleToLocation(80385,147867,-3512); 
		else if (event.equalsIgnoreCase("113"))
			activeChar.teleToLocation(79681,150641,-3524); 
		else if (event.equalsIgnoreCase("114"))
			activeChar.teleToLocation(79711,145488,-3499); 
		else if (event.equalsIgnoreCase("115"))
			activeChar.teleToLocation(80402,149209,-3515); 
		else if (event.equalsIgnoreCase("116"))
			activeChar.teleToLocation(77528,148183,-3596); 
		else if (event.equalsIgnoreCase("117"))
			activeChar.teleToLocation(83096,146659,-3469); 
		else if (event.equalsIgnoreCase("118"))
			activeChar.teleToLocation(80518,144416,-3537); 
		else if (event.equalsIgnoreCase("119"))
			activeChar.teleToLocation(78790,149358,-3601); 
		else if (event.equalsIgnoreCase("120"))
			activeChar.teleToLocation(85439,146446,-3405);
		else if (event.equalsIgnoreCase("121"))
			activeChar.teleToLocation(80882,150572,-3532); 
		else if (event.equalsIgnoreCase("122"))
			activeChar.teleToLocation(86674,148630,-3406); 
		else if (event.equalsIgnoreCase("123"))
			activeChar.teleToLocation(85824,153086,-3500);						
		else if (event.equalsIgnoreCase("124"))
			activeChar.teleToLocation(147450,27064,-2208); 
		else if (event.equalsIgnoreCase("125"))
			activeChar.teleToLocation(146086,26302,-2195);
		else if (event.equalsIgnoreCase("126"))
			activeChar.teleToLocation(146754,28968,-2259); 
		else if (event.equalsIgnoreCase("127"))
			activeChar.teleToLocation(148205,28984,-2259); 
		else if (event.equalsIgnoreCase("128"))
			activeChar.teleToLocation(150631,28341,-2252); 
		else if (event.equalsIgnoreCase("129"))
			activeChar.teleToLocation(148925,26664,-2195); 
		else if (event.equalsIgnoreCase("130"))
			activeChar.teleToLocation(144179,25574,-2130); 
		else if (event.equalsIgnoreCase("131"))
			activeChar.teleToLocation(143961,23944,-2124); 
		else if (event.equalsIgnoreCase("132"))
			activeChar.teleToLocation(149200,25193,-2127); 
		else if (event.equalsIgnoreCase("133"))
			activeChar.teleToLocation(144082,22710,-2140); 
		else if (event.equalsIgnoreCase("134"))
			activeChar.teleToLocation(147441,22883,-1996); 
		else if (event.equalsIgnoreCase("135"))
			activeChar.teleToLocation(145661,23154,-2122); 
		else if (event.equalsIgnoreCase("136"))
			activeChar.teleToLocation(147725,-56517,-2780);
		else if (event.equalsIgnoreCase("137"))
			activeChar.teleToLocation(148909,-58773,-2966); 
		else if (event.equalsIgnoreCase("138"))
			activeChar.teleToLocation(149216,-57550,-2967);
		else if (event.equalsIgnoreCase("139"))
			activeChar.teleToLocation(148909,-58773,-2966); 
		else if (event.equalsIgnoreCase("140"))
			activeChar.teleToLocation(146603,-58858,-2973); 
		else if (event.equalsIgnoreCase("141"))
			activeChar.teleToLocation(146329,-57662,-2966); 
		else if (event.equalsIgnoreCase("142"))
			activeChar.teleToLocation(144360,-55751,-2966); 
		else if (event.equalsIgnoreCase("143"))
			activeChar.teleToLocation(145015,-54680,-2966); 
		else if (event.equalsIgnoreCase("144"))
			activeChar.teleToLocation(148783,-53170,-2732); 
		else if (event.equalsIgnoreCase("145"))
			activeChar.teleToLocation(151283,-55638,-2967); 
		else if (event.equalsIgnoreCase("146"))
			activeChar.teleToLocation(147705,-53066,-2731); 
		else if (event.equalsIgnoreCase("147"))
			activeChar.teleToLocation(146577,-53114,-2732); 
		else if (event.equalsIgnoreCase("148"))
			activeChar.teleToLocation(44070,-50243,-796); 
		else if (event.equalsIgnoreCase("149"))
			activeChar.teleToLocation(44770,-47412,-796); 
		else if (event.equalsIgnoreCase("150"))
			activeChar.teleToLocation(45093,-47447,-796);
		else if (event.equalsIgnoreCase("151"))
			activeChar.teleToLocation(44770,-47412,-796); 
		else if (event.equalsIgnoreCase("152"))
			activeChar.teleToLocation(45342,-49374,-796);
		else if (event.equalsIgnoreCase("153"))
			activeChar.teleToLocation(43532,-49123,-796); 
		else if (event.equalsIgnoreCase("154"))
			activeChar.teleToLocation(37350,-47043,-1144); 
		else if (event.equalsIgnoreCase("155"))
			activeChar.teleToLocation(42956,-51453,-792); 
		else if (event.equalsIgnoreCase("156"))
			activeChar.teleToLocation(37342,-49538,-1128); 
		else if (event.equalsIgnoreCase("157"))
			activeChar.teleToLocation(18748,145437,-3132); 
		else if (event.equalsIgnoreCase("158"))
			activeChar.teleToLocation(18087,144594,-3051); 
		else if (event.equalsIgnoreCase("159"))
			activeChar.teleToLocation(19274,146132,-3074); 
		else if (event.equalsIgnoreCase("160"))
			activeChar.teleToLocation(18087,144594,-3051); 
		else if (event.equalsIgnoreCase("161"))
			activeChar.teleToLocation(19675,143153,-3032); 
		else if (event.equalsIgnoreCase("162"))
			activeChar.teleToLocation(20861,144576,-3074); 
		else if (event.equalsIgnoreCase("163"))
			activeChar.teleToLocation(20062,143178,-3053);
		else if (event.equalsIgnoreCase("164"))
			activeChar.teleToLocation(17229,146773,-3091); 
		else if (event.equalsIgnoreCase("165"))
			activeChar.teleToLocation(19565,144624,-3094);
		else if (event.equalsIgnoreCase("166"))
			activeChar.teleToLocation(16497,146244,-3113); 
		else if (event.equalsIgnoreCase("167"))
			activeChar.teleToLocation(16028,142329,-2697); 
		else if (event.equalsIgnoreCase("168"))
			activeChar.teleToLocation(82321,55139,-1529); 
		else if (event.equalsIgnoreCase("169"))
			activeChar.teleToLocation(79297,53618,-1550); 
		else if (event.equalsIgnoreCase("170"))
			activeChar.teleToLocation(79581,54930,-1551); 
		else if (event.equalsIgnoreCase("171"))
			activeChar.teleToLocation(79297,53618,-1550);
		else if (event.equalsIgnoreCase("172"))
			activeChar.teleToLocation(83666,55537,-1516); 
		else if (event.equalsIgnoreCase("173"))
			activeChar.teleToLocation(81660,54855,-1514); 
		else if (event.equalsIgnoreCase("174"))
			activeChar.teleToLocation(83656,56079,-1529); 
		else if (event.equalsIgnoreCase("175"))
			activeChar.teleToLocation(83189,54961,-1515); 
		else if (event.equalsIgnoreCase("176"))
			activeChar.teleToLocation(79288,56771,-1516);
		else if (event.equalsIgnoreCase("177"))
			activeChar.teleToLocation(83457,54464,-1520); 
		else if (event.equalsIgnoreCase("178"))
			activeChar.teleToLocation(83636,53103,-1461);
		else if (event.equalsIgnoreCase("179"))
			activeChar.teleToLocation(-14225,123540,-3121); 
		else if (event.equalsIgnoreCase("180"))
			activeChar.teleToLocation(-15176,123389,-3112); 
		else if (event.equalsIgnoreCase("181"))
			activeChar.teleToLocation(-14831,124488,-3118); 
		else if (event.equalsIgnoreCase("182"))
			activeChar.teleToLocation(-15176,123389,-3112); 
		else if (event.equalsIgnoreCase("183"))
			activeChar.teleToLocation(-15258,124736,-3119); 
		else if (event.equalsIgnoreCase("184"))
			activeChar.teleToLocation(-13136,124848,-3122); 
		else if (event.equalsIgnoreCase("185"))
			activeChar.teleToLocation(-12762,124771,-3131); 
		else if (event.equalsIgnoreCase("186"))
			activeChar.teleToLocation(-12698,123146,-3106); 
		else if (event.equalsIgnoreCase("187"))
			activeChar.teleToLocation(-13587,122840,-3109); 
		else if (event.equalsIgnoreCase("188"))
			activeChar.teleToLocation(-13520,125337,-3131); 
		else if (event.equalsIgnoreCase("189"))
			activeChar.teleToLocation(-12982,121706,-2972);
		else if (event.equalsIgnoreCase("190"))
			activeChar.teleToLocation(-83063,150791,-3133); 
		else if (event.equalsIgnoreCase("191"))
			activeChar.teleToLocation(-80392,155301,-3165);
		else if (event.equalsIgnoreCase("192"))
			activeChar.teleToLocation(-79423,153550,-3165); 
		else if (event.equalsIgnoreCase("193"))
			activeChar.teleToLocation(-80392,155301,-3165); 
		else if (event.equalsIgnoreCase("194"))
			activeChar.teleToLocation(-82971,155479,-3180); 
		else if (event.equalsIgnoreCase("195"))
			activeChar.teleToLocation(-81970,153402,-3178); 
		else if (event.equalsIgnoreCase("196"))
			activeChar.teleToLocation(-80466,153281,-3182); 
		else if (event.equalsIgnoreCase("197"))
			activeChar.teleToLocation(-84534,149772,-3132); 
		else if (event.equalsIgnoreCase("198"))
			activeChar.teleToLocation(-82085,149369,-3129); 
		else if (event.equalsIgnoreCase("199"))
			activeChar.teleToLocation(-84746,153384,-3204); 
		else if (event.equalsIgnoreCase("200"))
			activeChar.teleToLocation(-79310,150930,-3045); 
		else if (event.equalsIgnoreCase("201"))
			activeChar.teleToLocation(87358,-141982,-1341); 
		else if (event.equalsIgnoreCase("202"))
			activeChar.teleToLocation(86361,-139805,-1526);
		else if (event.equalsIgnoreCase("203"))
			activeChar.teleToLocation(85810,-141206,-1528); 
		else if (event.equalsIgnoreCase("204"))
			activeChar.teleToLocation(86361,-139805,-1526);
		else if (event.equalsIgnoreCase("205"))
			activeChar.teleToLocation(88172,-139704,-1533); 
		else if (event.equalsIgnoreCase("206"))
			activeChar.teleToLocation(88737,-141034,-1526); 
		else if (event.equalsIgnoreCase("207"))
			activeChar.teleToLocation(90154,-143219,-1526); 
		else if (event.equalsIgnoreCase("208"))
			activeChar.teleToLocation(90403,-141984,-1526);
		else if (event.equalsIgnoreCase("209"))
			activeChar.teleToLocation(86264,-145540,-1292); 
		else if (event.equalsIgnoreCase("210"))
			activeChar.teleToLocation(90216,-144253,-1537); 
		else if (event.equalsIgnoreCase("211"))
			activeChar.teleToLocation(87358,-145246,-1292); 
		else if (event.equalsIgnoreCase("212"))
			activeChar.teleToLocation(88510,-145588,-1292);
		else if (event.equalsIgnoreCase("213"))
			activeChar.teleToLocation(111115,219017,-3547); 
		else if (event.equalsIgnoreCase("214"))
			activeChar.teleToLocation(115783,217942,-3587); 
		else if (event.equalsIgnoreCase("215"))
			activeChar.teleToLocation(107107,216871,-3600);
		else if (event.equalsIgnoreCase("216"))
			activeChar.teleToLocation(115783,217942,-3587); 
		else if (event.equalsIgnoreCase("217"))
			activeChar.teleToLocation(107905,218858,-3613);
		else if (event.equalsIgnoreCase("218"))
			activeChar.teleToLocation(109759,220155,-3520); 
		else if (event.equalsIgnoreCase("219"))
			activeChar.teleToLocation(108613,218940,-3676); 
		else if (event.equalsIgnoreCase("220"))
			activeChar.teleToLocation(115775,219952,-3631); 
		else if (event.equalsIgnoreCase("221"))
			activeChar.teleToLocation(114164,218612,-3630); 
		else if (event.equalsIgnoreCase("222"))
			activeChar.teleToLocation(113391,218304,-3630); 
		else if (event.equalsIgnoreCase("223"))
			activeChar.teleToLocation(111374,217010,-3463); 
		else if (event.equalsIgnoreCase("224"))
			activeChar.teleToLocation(111332,224636,-3555); 
		else if (event.equalsIgnoreCase("225"))
			activeChar.teleToLocation(116589,76268,-2734); 
		else if (event.equalsIgnoreCase("226"))
			activeChar.teleToLocation(115344,75620,-2587); 
		else if (event.equalsIgnoreCase("227"))
			activeChar.teleToLocation(116270,74838,-2588); 
		else if (event.equalsIgnoreCase("228"))
			activeChar.teleToLocation(115344,75620,-2587);
		else if (event.equalsIgnoreCase("229"))
			activeChar.teleToLocation(117540,75360,-2654); 
		else if (event.equalsIgnoreCase("230"))
			activeChar.teleToLocation(115429,76754,-2656);
		else if (event.equalsIgnoreCase("231"))
			activeChar.teleToLocation(119986,74742,-2378);
		else if (event.equalsIgnoreCase("232"))
			activeChar.teleToLocation(120120,76018,-2268); 
		else if (event.equalsIgnoreCase("233"))
			activeChar.teleToLocation(119652,77450,-2249); 
		else if (event.equalsIgnoreCase("234"))
			activeChar.teleToLocation(116001,79061,-2389); 
		else if (event.equalsIgnoreCase("235"))
			activeChar.teleToLocation(118924,78397,-2166);
		else if (event.equalsIgnoreCase("236"))
			activeChar.teleToLocation(70000,126636,-3804); 
		else if (event.equalsIgnoreCase("237"))
			activeChar.teleToLocation(47114,187152,-3485); 
		else if (event.equalsIgnoreCase("238"))
			activeChar.teleToLocation(42006,208234,-3756); 
		else if (event.equalsIgnoreCase("239"))
			activeChar.teleToLocation(41298,200350,-4583);
		else if (event.equalsIgnoreCase("240"))
			activeChar.teleToLocation(122881,110792,-3727); 
		else if (event.equalsIgnoreCase("241"))
			activeChar.teleToLocation(131355,114451,-3718);
		else if (event.equalsIgnoreCase("242"))
			activeChar.teleToLocation(152913,110504,-5527);
		else if (event.equalsIgnoreCase("243"))
			activeChar.teleToLocation(154623,121134,-3809);
		else if (event.equalsIgnoreCase("244"))
			activeChar.teleToLocation(165584,85997,-2338);
		else if (event.equalsIgnoreCase("245"))
			activeChar.teleToLocation(146828,-12859,-4455);
		else if (event.equalsIgnoreCase("246"))
			activeChar.teleToLocation(109699,-7908,-2902);
		else if (event.equalsIgnoreCase("247"))
			activeChar.teleToLocation(114172,-18034,-1875);
		else if (event.equalsIgnoreCase("248"))
			activeChar.teleToLocation(172136,20325,-3326);
		else if (event.equalsIgnoreCase("249"))
			activeChar.teleToLocation(150086,46733,-3412);
		else if (event.equalsIgnoreCase("250"))
			activeChar.teleToLocation(185395,20359,-3270);
		else if (event.equalsIgnoreCase("251"))
			activeChar.teleToLocation(150477,85907,-2753);
		else if (event.equalsIgnoreCase("252"))
			activeChar.teleToLocation(147475,19775,-2005);
		else if (event.equalsIgnoreCase("253"))
			activeChar.teleToLocation(174528,52683,-4369);
		else if (event.equalsIgnoreCase("254"))
			activeChar.teleToLocation(121685,15749,-3852);
		else if (event.equalsIgnoreCase("255"))
			activeChar.teleToLocation(114665,12697,-3609);
		else if (event.equalsIgnoreCase("256"))
			activeChar.teleToLocation(111249,16031,-2127);
		else if (event.equalsIgnoreCase("257"))
			activeChar.teleToLocation(114605,19371,-645);
		else if (event.equalsIgnoreCase("258"))
			activeChar.teleToLocation(117996,16103,843);
		else if (event.equalsIgnoreCase("259"))
			activeChar.teleToLocation(114743,19707,1947);
		else if (event.equalsIgnoreCase("260"))
			activeChar.teleToLocation(114552,12354,2957);
		else if (event.equalsIgnoreCase("261"))
			activeChar.teleToLocation(110963,16147,3967);
		else if (event.equalsIgnoreCase("262"))
			activeChar.teleToLocation(117356,18462,4977);
		else if (event.equalsIgnoreCase("263"))
			activeChar.teleToLocation(118250,15858,5897);
		else if (event.equalsIgnoreCase("264"))
			activeChar.teleToLocation(115824,17242,6760);
		else if (event.equalsIgnoreCase("265"))
			activeChar.teleToLocation(113288,14692,7997);
		else if (event.equalsIgnoreCase("266"))
			activeChar.teleToLocation(115322,16756,9007);
		else if (event.equalsIgnoreCase("267"))
			activeChar.teleToLocation(112787,14158,10077);
		else if (event.equalsIgnoreCase("268"))
			activeChar.teleToLocation(107944,-87728,-2917);
		else if (event.equalsIgnoreCase("269"))
			activeChar.teleToLocation(149616,-112428,-2065);
		else if (event.equalsIgnoreCase("270"))
			activeChar.teleToLocation(152180,-126093,-2282);
		else if (event.equalsIgnoreCase("271"))
			activeChar.teleToLocation(108275,-53785,-2524);
		else if (event.equalsIgnoreCase("272"))
			activeChar.teleToLocation(170723,-116207,-2067);
		else if (event.equalsIgnoreCase("273"))
			activeChar.teleToLocation(180151,-111688,-5833);
		else if (event.equalsIgnoreCase("274"))
			activeChar.teleToLocation(188191,-74959,-2738);
		else if (event.equalsIgnoreCase("275"))
			activeChar.teleToLocation(178293,-83983,-7209);
		else if (event.equalsIgnoreCase("276"))
			activeChar.teleToLocation(149774,-81243,-5624);
		else if (event.equalsIgnoreCase("277"))
			activeChar.teleToLocation(176886,-50812,-3394);
		else if (event.equalsIgnoreCase("278"))
			activeChar.teleToLocation(168505,-86606,-2992);
		else if (event.equalsIgnoreCase("279"))
			activeChar.teleToLocation(38015,-38305,-3609);
		else if (event.equalsIgnoreCase("280"))
			activeChar.teleToLocation(93078,-58289,-2854);
		else if (event.equalsIgnoreCase("281"))
			activeChar.teleToLocation(89685,-44666,-2147);
		else if (event.equalsIgnoreCase("282"))
			activeChar.teleToLocation(52063,-54448,-3166);
		else if (event.equalsIgnoreCase("283"))
			activeChar.teleToLocation(59425,-47753,-2562);
		else if (event.equalsIgnoreCase("284"))
			activeChar.teleToLocation(52270,-81456,-2767);
		else if (event.equalsIgnoreCase("285"))
			activeChar.teleToLocation(67992,-72012,-3748);
		else if (event.equalsIgnoreCase("286"))
			activeChar.teleToLocation(80471,-84022,-3646);
		else if (event.equalsIgnoreCase("287"))
			activeChar.teleToLocation(81165,-89726,-3283);
		else if (event.equalsIgnoreCase("288"))
			activeChar.teleToLocation(40723,-92245,-3747);
		else if (event.equalsIgnoreCase("289"))
			activeChar.teleToLocation(36603,-51202,712);
		else if (event.equalsIgnoreCase("290"))
			activeChar.teleToLocation(51055,141959,-2869);
		else if (event.equalsIgnoreCase("291"))
			activeChar.teleToLocation(17192,114178,-3439);
		else if (event.equalsIgnoreCase("292"))
			activeChar.teleToLocation(17724,114004,-11672);
		else if (event.equalsIgnoreCase("293"))
			activeChar.teleToLocation(17730,108301,-9057);
		else if (event.equalsIgnoreCase("294"))
			activeChar.teleToLocation(17719,115430,-6582);
		else if (event.equalsIgnoreCase("295"))
			activeChar.teleToLocation(62425,30856,-3779);
		else if (event.equalsIgnoreCase("296"))
			activeChar.teleToLocation(85332,16186,-3673);
		else if (event.equalsIgnoreCase("297"))
			activeChar.teleToLocation(70833,6426,-3639);
		else if (event.equalsIgnoreCase("298"))
			activeChar.teleToLocation(-56235,106668,-3773);
		else if (event.equalsIgnoreCase("299"))
			activeChar.teleToLocation(-20043,137688,-3896);
		else if (event.equalsIgnoreCase("300"))
			activeChar.teleToLocation(-26111,173692,-4152);
		else if (event.equalsIgnoreCase("301"))
			activeChar.teleToLocation(-16730,209417,-3664);
		else if (event.equalsIgnoreCase("302"))
			activeChar.teleToLocation(-23403,186599,-4317);
		else if (event.equalsIgnoreCase("303"))
			activeChar.teleToLocation(-56742,140569,-2625);
		else if (event.equalsIgnoreCase("304"))
			activeChar.teleToLocation(-66931,120296,-3651);
		else if (event.equalsIgnoreCase("305"))
			activeChar.teleToLocation(-53838,179285,-4640);
		else if (event.equalsIgnoreCase("306"))
			activeChar.teleToLocation(-94841,147430,-2675);
		else if (event.equalsIgnoreCase("307"))
			activeChar.teleToLocation(-89199,149962,-3586);
		else if (event.equalsIgnoreCase("308"))
			activeChar.teleToLocation(-90562,108182,-3546);
		else if (event.equalsIgnoreCase("309"))
			activeChar.teleToLocation(56095,-118952,-3290);
		else if (event.equalsIgnoreCase("310"))
			activeChar.teleToLocation(76860,-125169,-3414);
		else if (event.equalsIgnoreCase("311"))
			activeChar.teleToLocation(108090,-120925,-3628);
		else if (event.equalsIgnoreCase("312"))
			activeChar.teleToLocation(109060,-128655,-3084);
		else if (event.equalsIgnoreCase("313"))
			activeChar.teleToLocation(113487,-109888,-865);
		else if (event.equalsIgnoreCase("314"))
			activeChar.teleToLocation(23006,-126115,-870);
		else if (event.equalsIgnoreCase("315"))
			activeChar.teleToLocation(88275,-125690,-3815);
		else if (event.equalsIgnoreCase("316"))
			activeChar.teleToLocation(113900,-154175,-1488);
		else if (event.equalsIgnoreCase("317"))
			activeChar.teleToLocation(48336,-107734,-1577);
		else if (event.equalsIgnoreCase("318"))
			activeChar.teleToLocation(69762,-111260,-1807);
		else if (event.equalsIgnoreCase("319"))
			activeChar.teleToLocation(40825,-90317,-3095);
		else if (event.equalsIgnoreCase("320"))
			activeChar.teleToLocation(87475,-109835,-3330);
		else if (event.equalsIgnoreCase("321"))
			activeChar.teleToLocation(117715,-141750,-2700);
		else if (event.equalsIgnoreCase("322"))
			activeChar.teleToLocation(124585,-160240,-1180);
		else if (event.equalsIgnoreCase("323"))
			activeChar.teleToLocation(101712,174198,-2457);
		else if (event.equalsIgnoreCase("324"))
			activeChar.teleToLocation(84904,182410,-3670);
		else if (event.equalsIgnoreCase("325"))
			activeChar.teleToLocation(86519,211911,-3764);
		else if (event.equalsIgnoreCase("326"))
			activeChar.teleToLocation(149361,172327,-945);
		else if (event.equalsIgnoreCase("327"))
			activeChar.teleToLocation(159111,183721,-3720);
		else if (event.equalsIgnoreCase("328"))
			activeChar.teleToLocation(152857,149040,-3280);
		else if (event.equalsIgnoreCase("329"))
			activeChar.teleToLocation(85170,241576,-6848);
		else if (event.equalsIgnoreCase("330"))
			activeChar.teleToLocation(80688,245566,-8926);
		else if (event.equalsIgnoreCase("331"))
			activeChar.teleToLocation(80629,246420,-9331);
		else if (event.equalsIgnoreCase("332"))
			activeChar.teleToLocation(87750,252422,-9851);
		else if (event.equalsIgnoreCase("333"))
			activeChar.teleToLocation(82506,255978,-10363);
		else if (event.equalsIgnoreCase("334"))
			activeChar.teleToLocation(82158,252376,-10592);
		else if (event.equalsIgnoreCase("335"))
			activeChar.teleToLocation(85531,256976,-11671);
		else if (event.equalsIgnoreCase("336"))
			activeChar.teleToLocation(105751,113370,-3199);
		else if (event.equalsIgnoreCase("337"))
			activeChar.teleToLocation(104413,33734,-3795);
		else if (event.equalsIgnoreCase("338"))
			activeChar.teleToLocation(124899,61995,-3915);
		else if (event.equalsIgnoreCase("339"))
			activeChar.teleToLocation(83357,209207,-5437);
		else if (event.equalsIgnoreCase("340"))
			activeChar.teleToLocation(-19176,13504,-4899);
		else if (event.equalsIgnoreCase("341"))
			activeChar.teleToLocation(172600,-17599,-4899);
		else if (event.equalsIgnoreCase("342"))
			activeChar.teleToLocation(12521,-248481,-9585);
		else if (event.equalsIgnoreCase("343"))
			activeChar.teleToLocation(118576,132800,-4832);
		else if (event.equalsIgnoreCase("344"))
			activeChar.teleToLocation(140690,79679,-5429);
		else if (event.equalsIgnoreCase("345"))
			activeChar.teleToLocation(-51942,79096,-4739);
		else if (event.equalsIgnoreCase("346"))
			activeChar.teleToLocation(-20470,-250784,-8160);
		else if (event.equalsIgnoreCase("347"))
			activeChar.teleToLocation(-21423,77375,-5171);
		else if (event.equalsIgnoreCase("348"))
			activeChar.teleToLocation(46542,170305,-4979);
		else if (event.equalsIgnoreCase("349"))
			activeChar.teleToLocation(111552,174014,-5440);
		else if (event.equalsIgnoreCase("350"))
			activeChar.teleToLocation(-53174,-250275,-7911);
		else if (event.equalsIgnoreCase("351"))
			activeChar.teleToLocation(45249,123548,-5411);
		else if (event.equalsIgnoreCase("352"))
			activeChar.teleToLocation(-41569,210082,-5085);
		else if (event.equalsIgnoreCase("356"))
			activeChar.teleToLocation(10468,-24569,-3650);
		else if (event.equalsIgnoreCase("357"))
			activeChar.teleToLocation(6229,-2924,-2965);
		else if (event.equalsIgnoreCase("358"))
			activeChar.teleToLocation(26174,-17134,-2747);
		else if (event.equalsIgnoreCase("359"))
			activeChar.teleToLocation(8264,-14431,-3696);
		else if (event.equalsIgnoreCase("400"))
			activeChar.teleToLocation(-84289,244458,-3737);
		else if (event.equalsIgnoreCase("401"))
			activeChar.teleToLocation(46928,51394,-2985);
		else if (event.equalsIgnoreCase("402"))
			activeChar.teleToLocation(9797,15565,-4583);
		else if (event.equalsIgnoreCase("403"))
			activeChar.teleToLocation(-45096,-112640,-247);
		else if (event.equalsIgnoreCase("404"))
			activeChar.teleToLocation(115321,-178176,-936);
		else if (event.equalsIgnoreCase("405"))
			activeChar.teleToLocation(-116934,46616,368);
		else if (event.equalsIgnoreCase("500"))
			activeChar.teleToLocation(169018,-116303,-2432);
		else if (event.equalsIgnoreCase("501"))
			activeChar.teleToLocation(11235,-24026,-3640);
		else if (event.equalsIgnoreCase("502"))
			activeChar.teleToLocation(174491,50942,-4360);
		else if (event.equalsIgnoreCase("503"))
			activeChar.teleToLocation(68693,-120438,-1904);
		else if (event.equalsIgnoreCase("504"))
			activeChar.teleToLocation(171946,-173352,3440);
		else if (event.equalsIgnoreCase("505"))
			activeChar.teleToLocation(178591,-184615,360);
		else if (event.equalsIgnoreCase("506"))
			activeChar.teleToLocation(87691,162835,-3520);
		else if (event.equalsIgnoreCase("507"))
			activeChar.teleToLocation(82192,226128,-3656);
		else if (event.equalsIgnoreCase("508"))
			activeChar.teleToLocation(47692,-115745,-3744);
		else if (event.equalsIgnoreCase("509"))
			activeChar.teleToLocation(89513,-44800,-2136);
		else if (event.equalsIgnoreCase("residence") || event.equalsIgnoreCase("fortress") || event.equalsIgnoreCase("castle") || event.equalsIgnoreCase("clanhall"))
		{
			if (activeChar.getClan() != null)
			{
				Fort fortOwner = FortManager.getInstance().getFortByOwner(activeChar.getClan());
				Castle castleOwner = CastleManager.getInstance().getCastleByOwner(activeChar.getClan());
				ClanHall clanHallOwner = ClanHallManager.getInstance().getClanHallByOwner(activeChar.getClan());          		
				if (event.equalsIgnoreCase("residence"))
				{
					String voiceText = "<html><title>Global GateKeeper</title><body>";
					voiceText += "<center><table width=260><tr><td width=40>&nbsp;</td>";
					voiceText += "<td width=180><center>Clan Residences</center></td>";
					voiceText += "<td width=40><button value=\"Back\" action=\"bypass -h voice .globalgk start\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>";
					voiceText += "</tr></table></center><br><br><center>";
					if (clanHallOwner != null)
					{
						voiceText += "<button action=\"bypass -h voice .globalgk clanhall\" value=\"ClanHall\" width=150 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br1>";
					}
					if (fortOwner != null)
					{
						voiceText += "<button action=\"bypass -h voice .globalgk fortress\" value=\"Fortress\" width=150 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br1>";
					}
					if (castleOwner != null)
					{
						voiceText += "<button action=\"bypass -h voice .globalgk castle\" value=\"Castle\" width=150 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br1>";
					}
					if (fortOwner == null && castleOwner == null && clanHallOwner == null)
					{
						activeChar.sendMessage("You don't have any residences.");
						NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
						npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/start.htm");
						activeChar.sendPacket(npcHtmlMessage);
					}
					else
					{
						voiceText += "</center></body></html>";
						NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
						npcHtmlMessage.setHtml(voiceText);
						activeChar.sendPacket(npcHtmlMessage);
					}
				}
				else if (event.equalsIgnoreCase("clanhall"))
				{
					activeChar.teleToLocation(clanHallOwner.getZone().getSpawnLoc().getX(),clanHallOwner.getZone().getSpawnLoc().getY(), clanHallOwner.getZone().getSpawnLoc().getZ());
				}
				else if (event.equalsIgnoreCase("fortress"))
				{
					activeChar.teleToLocation(fortOwner.getFortZone().getSpawnLoc().getX(),fortOwner.getFortZone().getSpawnLoc().getY(), fortOwner.getFortZone().getSpawnLoc().getZ());
				}
				else if (event.equalsIgnoreCase("castle"))
				{
					activeChar.teleToLocation(castleOwner.getCastleZone().getSpawnLoc().getX(),castleOwner.getCastleZone().getSpawnLoc().getY(), castleOwner.getCastleZone().getSpawnLoc().getZ());
				}
			}
			else
			{       		
				activeChar.sendMessage("You don't have clan.");
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
				npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/GlobalGk/start.htm");
				activeChar.sendPacket(npcHtmlMessage);
			}
		}
		else
		{
			activeChar.sendMessage("Incorrect command: use .globalgk to select teleport");
		}
	}

	private void useAioBufferCommand(L2PcInstance activeChar, String event) 
	{
		if (event.equalsIgnoreCase("bd"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("es"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("prophet"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("resists"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("se"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("special"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("start"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("sws"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("wc"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("ws"))
		{
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/"+ event + ".htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Shield lvl 3
		else if (event.equalsIgnoreCase("1040"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1040,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Might lvl 3
		else if (event.equalsIgnoreCase("1068"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1068,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Mental Shield lvl 4
		else if (event.equalsIgnoreCase("1035"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 4;
			}
			SkillTable.getInstance().getInfo(1035,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Holy Weapon lvl 1
		else if (event.equalsIgnoreCase("1043"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1043,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Regeneration lvl 3
		else if (event.equalsIgnoreCase("1044"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1044,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Kiss of Eva lvl 2
		else if (event.equalsIgnoreCase("1073"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1073,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Focus lvl 3
		else if (event.equalsIgnoreCase("1077"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1077,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Concentration lvl 6
		else if (event.equalsIgnoreCase("1078"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 6;
			}
			SkillTable.getInstance().getInfo(1078,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Acumen lvl 3
		else if (event.equalsIgnoreCase("1085"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1085,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Wind Walk lvl 2
		else if (event.equalsIgnoreCase("1204"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1204,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Invigor lvl 3
		else if (event.equalsIgnoreCase("1032"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1032,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Magic Barrier lvl 2
		else if (event.equalsIgnoreCase("1036"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1036,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Bless the Body lvl 6
		else if (event.equalsIgnoreCase("1045"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 6;
			}
			SkillTable.getInstance().getInfo(1045,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Bless the Soul lvl 6
		else if (event.equalsIgnoreCase("1048"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 6;
			}
			SkillTable.getInstance().getInfo(1048,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Haste lvl 2
		else if (event.equalsIgnoreCase("1086"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1086,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Guidance lvl 3
		else if (event.equalsIgnoreCase("1240"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1240,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Death Whisper lvl 3
		else if (event.equalsIgnoreCase("1242"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1242,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Bless Shield lvl 6
		else if (event.equalsIgnoreCase("1243"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 6;
			}
			SkillTable.getInstance().getInfo(1243,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Resist Fire lvl 3
		else if (event.equalsIgnoreCase("1191"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1191,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Resist Poison lvl 3
		else if (event.equalsIgnoreCase("1033"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1033,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Resist Aqua lvl 3
		else if (event.equalsIgnoreCase("1182"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1182,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Resist Wind lvl 3
		else if (event.equalsIgnoreCase("1189"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1189,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Holy Resistance lvl 3
		else if (event.equalsIgnoreCase("1392"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 330;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1392,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Unholy Resistance lvl 3
		else if (event.equalsIgnoreCase("1393"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 330;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1393,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Elemental Protection lvl 1
		else if (event.equalsIgnoreCase("1352"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1352,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/resists.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Greater Might lvl 3
		else if (event.equalsIgnoreCase("1388"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 230;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1388,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/special.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Greater Shield lvl 3
		else if (event.equalsIgnoreCase("1389"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 230;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1389,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/special.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Prophecy of Fire lvl 1
		else if (event.equalsIgnoreCase("1356"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1356,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/special.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Berserker Spirit lvl 2
		else if (event.equalsIgnoreCase("1062"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1062,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/special.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Agility lvl 3
		else if (event.equalsIgnoreCase("1087"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1087,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Decrease Weight lvl 3
		else if (event.equalsIgnoreCase("1257"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1257,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Resist Shock lvl 4
		else if (event.equalsIgnoreCase("1259"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 4;
			}
			SkillTable.getInstance().getInfo(1259,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Wild Magic lvl 2
		else if (event.equalsIgnoreCase("1303"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1303,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Advanced Block lvl 3
		else if (event.equalsIgnoreCase("1304"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1304,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Clarity lvl 3
		else if (event.equalsIgnoreCase("1397"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 430;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1397,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Divine Protection lvl 1
		else if (event.equalsIgnoreCase("1353"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1353,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Arcane Protection lvl 1
		else if (event.equalsIgnoreCase("1354"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1354,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Prophecy of Water lvl 1
		else if (event.equalsIgnoreCase("1355"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1355,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Mana Gain lvl 1
		else if (event.equalsIgnoreCase("1460"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1460,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/es.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Empower lvl 3
		else if (event.equalsIgnoreCase("1059"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1059,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/se.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Vampiric Rage lvl 4
		else if (event.equalsIgnoreCase("1268"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 4;
			}
			SkillTable.getInstance().getInfo(1268,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/se.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Prophecy of Wind lvl 1
		else if (event.equalsIgnoreCase("1357"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1357,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/se.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Earth lvl 1
		else if (event.equalsIgnoreCase("264"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(264,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Life lvl 1
		else if (event.equalsIgnoreCase("265"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(265,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Water lvl 1
		else if (event.equalsIgnoreCase("266"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(266,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Warding lvl 1
		else if (event.equalsIgnoreCase("267"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(267,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Wind lvl 1
		else if (event.equalsIgnoreCase("268"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(268,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Hunter lvl 1
		else if (event.equalsIgnoreCase("269"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(269,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Invocation lvl 1
		else if (event.equalsIgnoreCase("270"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(270,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Vitality lvl 1
		else if (event.equalsIgnoreCase("304"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(304,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Vengeance lvl 1
		else if (event.equalsIgnoreCase("305"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(305,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Flame Guard lvl 1
		else if (event.equalsIgnoreCase("306"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(306,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Storm Guard lvl 1
		else if (event.equalsIgnoreCase("308"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(308,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Renewal lvl 1
		else if (event.equalsIgnoreCase("349"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(349,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Song of Meditation lvl 1
		else if (event.equalsIgnoreCase("363"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(363,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Champion lvl 1
		else if (event.equalsIgnoreCase("364"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(364,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Song of Elemental lvl 1
		else if (event.equalsIgnoreCase("529"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(529,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/sws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Dance of the Warrior lvl 1
		else if (event.equalsIgnoreCase("271"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(271,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Inspiration lvl 1
		else if (event.equalsIgnoreCase("272"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(272,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Dance of the Mystic lvl 1
		else if (event.equalsIgnoreCase("273"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(273,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Fire lvl 1
		else if (event.equalsIgnoreCase("274"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(274,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Dance of Fury lvl 1
		else if (event.equalsIgnoreCase("275"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(275,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Concentration lvl 1
		else if (event.equalsIgnoreCase("276"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(276,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Light lvl 1
		else if (event.equalsIgnoreCase("277"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(277,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Aqua Guard lvl 1
		else if (event.equalsIgnoreCase("307"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(307,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Earth Guard lvl 1
		else if (event.equalsIgnoreCase("309"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(309,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of the Vampire lvl 1
		else if (event.equalsIgnoreCase("310"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(310,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Dance of Protection lvl 1
		else if (event.equalsIgnoreCase("311"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(310,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Siren's Dance lvl 1
		else if (event.equalsIgnoreCase("365"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(365,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Shadows lvl 1
		else if (event.equalsIgnoreCase("366"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
			skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(366,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Dance of Alignment lvl 1
		else if (event.equalsIgnoreCase("530"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(530,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/bd.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Sharp Edge lvl 1
		else if (event.equalsIgnoreCase("825"))
		{			
			SkillTable.getInstance().getInfo(825,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Spike lvl 1
		else if (event.equalsIgnoreCase("826"))
		{
			SkillTable.getInstance().getInfo(826,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Restring lvl 1
		else if (event.equalsIgnoreCase("827"))
		{
			SkillTable.getInstance().getInfo(827,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Case Harden lvl 1
		else if (event.equalsIgnoreCase("828"))
		{
			SkillTable.getInstance().getInfo(828,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Hard Tanning lvl 1
		else if (event.equalsIgnoreCase("829"))
		{
			SkillTable.getInstance().getInfo(829,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Embroider lvl 1
		else if (event.equalsIgnoreCase("830"))
		{
			SkillTable.getInstance().getInfo(830,1).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/ws.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Chant of Battle lvl 3
		else if (event.equalsIgnoreCase("1007"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1007,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Chant of Shielding lvl 3
		else if (event.equalsIgnoreCase("1009"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1009,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Flame Chant lvl 3
		else if (event.equalsIgnoreCase("1002"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1002,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Fire lvl 3
		else if (event.equalsIgnoreCase("1006"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1006,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Fury lvl 2
		else if (event.equalsIgnoreCase("1251"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 2;
			}
			SkillTable.getInstance().getInfo(1251,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Chant of Evasion lvl 3
		else if (event.equalsIgnoreCase("1252"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1252,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Chant of Rage lvl 3
		else if (event.equalsIgnoreCase("1253"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1253,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Revenge lvl 3
		else if (event.equalsIgnoreCase("1284"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1284,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Chant of Predator lvl 3
		else if (event.equalsIgnoreCase("1308"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1308,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Eagle lvl 3
		else if (event.equalsIgnoreCase("1309"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1309,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Vampire lvl 4
		else if (event.equalsIgnoreCase("1310"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 4;
			}
			SkillTable.getInstance().getInfo(1310,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Spirit lvl 1
		else if (event.equalsIgnoreCase("1362"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 130;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1362,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: War Chant lvl 3
		else if (event.equalsIgnoreCase("1390"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 230;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1390,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Earth Chant lvl 3
		else if (event.equalsIgnoreCase("1391"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 230;
			}
			else
			{
				skillLevel = 3;
			}
			SkillTable.getInstance().getInfo(1391,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// 	Skill: Magnus' Chant lvl 1
		else if (event.equalsIgnoreCase("1413"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1413,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Protection lvl 1
		else if (event.equalsIgnoreCase("1461"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1461,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		// Skill: Chant of Victory lvl 1
		else if (event.equalsIgnoreCase("1363"))
		{
			int skillLevel = 1;
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				skillLevel = 115;
			}
			else
			{
				skillLevel = 1;
			}
			SkillTable.getInstance().getInfo(1363,skillLevel).getEffects(activeChar,activeChar);
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/wc.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("fighter"))
		{
			// 	Fighter Skills
			if (DonateConfig.AIO_DOUBLE_TIME)
			{				
				SkillTable.getInstance().getInfo(1040,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1068,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1035,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1077,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1204,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1268,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1036,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1045,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1048,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1086,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1240,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1242,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1388,230).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1356,115).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(271,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(274,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(275,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(310,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(264,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(265,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(267,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(268,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(269,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(304,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(349,115).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(364,115).getEffects(activeChar,activeChar);
			}
			else
			{
				SkillTable.getInstance().getInfo(1040,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1068,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1035,4).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1077,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1204,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1268,4).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1036,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1045,6).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1048,6).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1086,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1240,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1242,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1388,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1356,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(271,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(274,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(275,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(310,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(264,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(265,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(267,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(268,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(269,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(304,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(349,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(364,1).getEffects(activeChar,activeChar);
			}
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("mage"))
		{
			// 	Mage Skills
			if (DonateConfig.AIO_DOUBLE_TIME)
			{
				SkillTable.getInstance().getInfo(1040,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1035,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1059,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1078,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1085,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1204,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1036,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1045,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1048,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1240,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1303,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1389,230).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1460,115).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(273,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(276,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(264,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(265,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(267,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(268,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(304,130).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(349,115).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(363,115).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1413,115).getEffects(activeChar,activeChar);
			}
			else
			{
				SkillTable.getInstance().getInfo(1040,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1035,4).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1059,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1078,6).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1085,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1204,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1036,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1045,6).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1048,6).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1240,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1303,2).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1389,3).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1460,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(273,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(276,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(264,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(265,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(267,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(268,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(304,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(349,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(363,1).getEffects(activeChar,activeChar);
				SkillTable.getInstance().getInfo(1413,1).getEffects(activeChar,activeChar);	
			}
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("regen"))
		{
			// 	regeneration HP, MP and CP
			activeChar.setCurrentHp(activeChar.getMaxHp());
			activeChar.setCurrentMp(activeChar.getMaxMp());
			activeChar.setCurrentCp(activeChar.getMaxCp());
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);
		}
		else if (event.equalsIgnoreCase("pfighter"))
		{
			if (activeChar.getPet() != null)
			{
				// 	Summons or pets - fighter buffs
				if (DonateConfig.AIO_DOUBLE_TIME)
				{
					SkillTable.getInstance().getInfo(1040,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1068,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1035,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1077,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1204,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1268,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1036,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1045,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1048,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1086,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1240,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1242,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1388,230).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1356,115).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(271,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(274,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(275,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(310,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(264,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(265,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(267,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(268,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(269,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(304,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(349,115).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(364,115).getEffects(activeChar.getPet(),activeChar.getPet());
				}
				else
				{
					SkillTable.getInstance().getInfo(1040,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1068,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1035,4).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1077,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1204,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1268,4).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1036,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1045,6).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1048,6).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1086,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1240,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1242,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1388,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1356,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(271,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(274,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(275,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(310,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(264,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(265,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(267,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(268,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(269,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(304,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(349,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(364,1).getEffects(activeChar.getPet(),activeChar.getPet());
				}
				activeChar.getPet().broadcastStatusUpdate();	
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
				npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
				activeChar.sendPacket(npcHtmlMessage);
			}
			else
			{
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
				npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/petneeded.htm");
				activeChar.sendPacket(npcHtmlMessage);
			}
		}
		else if (event.equalsIgnoreCase("pmage"))
		{
			if (activeChar.getPet() != null)
			{
				// 	Summons or pets - mage buffs
				if (DonateConfig.AIO_DOUBLE_TIME)
				{
					SkillTable.getInstance().getInfo(1040,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1035,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1059,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1078,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1085,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1204,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1036,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1045,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1048,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1240,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1303,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1389,230).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1460,115).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(273,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(276,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(264,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(265,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(267,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(268,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(304,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(349,115).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(363,130).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1413,130).getEffects(activeChar.getPet(),activeChar.getPet());
				}
				else
				{
					SkillTable.getInstance().getInfo(1040,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1035,4).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1059,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1078,6).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1085,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1204,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1036,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1045,6).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1048,6).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1240,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1303,2).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1389,3).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1460,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(273,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(276,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(264,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(265,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(267,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(268,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(304,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(349,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(363,1).getEffects(activeChar.getPet(),activeChar.getPet());
					SkillTable.getInstance().getInfo(1413,1).getEffects(activeChar.getPet(),activeChar.getPet());
				}
				activeChar.getPet().broadcastStatusUpdate();
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
				npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
				activeChar.sendPacket(npcHtmlMessage);
			}
			else
			{
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
				npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/petneeded.htm");
				activeChar.sendPacket(npcHtmlMessage);
			}
		}
		else if (event.equalsIgnoreCase("cancel"))
		{
			activeChar.stopAllEffects();								
			NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 ); 
			npcHtmlMessage.setFile(activeChar.getHtmlPrefix(), "data/html/donate/AioBuffer/start.htm");
			activeChar.sendPacket(npcHtmlMessage);							
		}
		else
		{
			activeChar.sendMessage("Incorrect command: use .aiobuff to select buff command");
		}		
	}

	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}