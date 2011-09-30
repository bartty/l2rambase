package handlers.voicedcommandhandlers;

import java.util.StringTokenizer;
import javolution.util.FastList;
import javolution.util.FastMap;
import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.stat.PcStat;
import l2.rambase.gameserver.util.Broadcast;


public class Event implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS = { "event" };
	
	private boolean _isRegisterTime = false;
	private boolean _isStarted = false;
	
	private FastList<Integer> _registeredPlayers = new FastList<Integer>();
	private FastList<Integer> _startedPlayers = new FastList<Integer>();
	private FastList<Integer> _winPlayers = new FastList<Integer>();
	
	private FastMap<Integer,StartLocation> _playersLocations = new FastMap<Integer,StartLocation>();
	
	private class StartLocation
	{
		private int _x;
		private int _y;
		private int _z;
		private StartLocation(int x, int y, int z)
		{
			_x = x;
			_y = y;
			_z = z;
		}
		private int getX()
		{
			return _x;
		}
		private int getY()
		{
			return _y;
		}
		private int getZ()
		{
			return _z;
		}
	}
	
	private int _gmId = 0;
	private static final int Special_Coin = 10639;
	
	private static final int[] GiranLoc = { 83385,148626,-3400 };
	
	private void setRegisterTime(String time)
	{
		if (_isStarted == false && _isRegisterTime == false)
		{
			_isRegisterTime = true;
			String gmName = L2World.getInstance().getPlayer(_gmId).getName();
			Broadcast.announceToOnlinePlayers("|| " + gmName + " || Event registration opened for " + time + " minutes. Use .event to register",2);
		}
	}
	
	private void startEvent()
	{
		if (_isStarted == false && _isRegisterTime == true && _registeredPlayers.isEmpty() == false)
		{
			_isRegisterTime = false;
			int teleX, teleY, teleZ = 0;
			try
			{
				teleX = L2World.getInstance().getPlayer(_gmId).getX();
				teleY = L2World.getInstance().getPlayer(_gmId).getY();
				teleZ = L2World.getInstance().getPlayer(_gmId).getZ();
				Broadcast.announceToOnlinePlayers("|| " + L2World.getInstance().getPlayer(_gmId).getName() + " || Event started. Please wait for teleport.",2);
			}
			catch (Exception e)
			{
				return;
			}			
			for(int eventPlayerId: _registeredPlayers)
			{
				L2PcInstance eventPlayer = L2World.getInstance().getPlayer(eventPlayerId);
				if (eventPlayer != null)
				{
					if (checkConditions(eventPlayer))
					{
						if (_playersLocations.get(eventPlayer.getObjectId()) != null)
						{
							_playersLocations.remove(eventPlayer.getObjectId());
						}
						StartLocation _location = null;
						_location = new StartLocation(eventPlayer.getX(),eventPlayer.getY(),eventPlayer.getZ());
						_playersLocations.put(eventPlayer.getObjectId(), _location);
						eventPlayer.teleToLocation(teleX,teleY,teleZ);
						_startedPlayers.add(eventPlayerId);
					}
				}
				else
				{
					_registeredPlayers.remove(eventPlayerId);
				}
			}
			_registeredPlayers.clear();
			_winPlayers.clear();
			_isStarted = true;			
		}		
	}
	
	private boolean checkConditions(L2PcInstance eventPlayer) 
	{
		boolean value = true;
		if (eventPlayer.isIn7sDungeon()
				|| eventPlayer.isDead()
				|| eventPlayer.isFestivalParticipant()
				|| eventPlayer.isCursedWeaponEquipped()
				|| eventPlayer.isInBoat() || eventPlayer.isInAirShip()
				|| eventPlayer.isInDuel() || eventPlayer.isInJail()
				|| eventPlayer.isInOlympiadMode()
				|| eventPlayer.isInSiege()
				|| eventPlayer.isOlympiadStart()
				|| eventPlayer.isTransformed()
				|| eventPlayer.getInstanceId() > 0)
		{
			return false;
		}			
		return value;
	}

	private void stopEvent()
	{
		if (_isStarted == true && _isRegisterTime == false)
		{
			for(int eventPlayerId: _startedPlayers)
			{
				L2PcInstance eventPlayer = L2World.getInstance().getPlayer(eventPlayerId);
				if (eventPlayer != null)
				{
					StartLocation _location = null;
					if (_playersLocations.get(eventPlayer.getObjectId()) != null)
					{
						_location = _playersLocations.get(eventPlayer.getObjectId());
						eventPlayer.teleToLocation(_location.getX(),_location.getY(), _location.getZ());
						eventPlayer.atEvent = false;
					}
					else
					{
						eventPlayer.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
						eventPlayer.atEvent = false;
					}
				}				
			}
			_startedPlayers.clear();			
			_isStarted = false;
			Broadcast.announceToOnlinePlayers("|| " + L2World.getInstance().getPlayer(_gmId).getName() + " || Event finished. Have a nice day. Winners get price.",2);
		}
	}

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (command.startsWith("event"))
		{
			if (params == null)
			{
				if (activeChar.isGM())
				{
					activeChar.sendMessage("Use command: '.event register time' to start register time");
					activeChar.sendMessage("Use command: '.event unregister' to stop register time");
					activeChar.sendMessage("Use command: '.event start' to start event");
					activeChar.sendMessage("Use command: '.event stop' to stop event");
					activeChar.sendMessage("Use command: '.event winner' to give winner flag to player");	
					activeChar.sendMessage("Use command: '.event list' to list all registered players");
					activeChar.sendMessage("Use command: '.event reward' reward all winners from the list");
					activeChar.sendMessage("Use command: '.event info' show number of registered to all when registration is activated");
				}
				else
				{
					if (checkConditions(activeChar))
					{
						if (_isRegisterTime == true && _isStarted == false)
						{
							if (_registeredPlayers.equals(activeChar.getObjectId()))
							{
								_registeredPlayers.remove(activeChar.getObjectId());								
								if (_gmId > 0)
								{
									L2PcInstance gmPlayer = L2World.getInstance().getPlayer(_gmId);
									if (gmPlayer != null)
									{
										gmPlayer.sendMessage("Player "+activeChar.getName()+" is unregistered from event");
									}									
								}
								activeChar.sendMessage("You are now unregistered from event");
								activeChar.atEvent = false;
							}
							else
							{								
								_registeredPlayers.add(activeChar.getObjectId());								
								if (_gmId > 0)
								{
									L2PcInstance gmPlayer = L2World.getInstance().getPlayer(_gmId);
									if (gmPlayer != null)
									{
										gmPlayer.sendMessage("Player "+activeChar.getName()+" is registered to event");
									}
								}
								activeChar.sendMessage("You are now registered to event");
								activeChar.atEvent = true;
							}
						}
					}
					else
					{
						activeChar.sendMessage("Not registered:");
						if (activeChar.isIn7sDungeon())
						{
							activeChar.sendMessage("You are in Rifts or Festival.");
						}
						else if (activeChar.isDead())
						{
							activeChar.sendMessage("You are dead.");
						}
						else if (activeChar.isFestivalParticipant())
						{
							activeChar.sendMessage("You participate in festival.");
						}
						else if (activeChar.isCursedWeaponEquipped())
						{
							activeChar.sendMessage("You have cursed weapon.");
						}
						else if (activeChar.isInBoat())
						{
							activeChar.sendMessage("You are in boat.");
						}
						else if (activeChar.isInAirShip())
						{
							activeChar.sendMessage("You are airship.");
						}
						else if (activeChar.isInDuel())
						{
							activeChar.sendMessage("You fight in duel.");
						}
						else if (activeChar.isInJail())
						{
							activeChar.sendMessage("You are in jail.");
						}
						else if (activeChar.isInOlympiadMode())
						{
							activeChar.sendMessage("You are in Olimpiad Mode.");
						}
						else if (activeChar.isInSiege())
						{
							activeChar.sendMessage("You are now in siege.");
						}
						else if (activeChar.isOlympiadStart())
						{
							activeChar.sendMessage("You started Olympiad Fight.");
						}
						else if (activeChar.isTransformed())
						{
							activeChar.sendMessage("You are transformed.");
						}
						else if (activeChar.getInstanceId() > 0)
						{
							activeChar.sendMessage("You are in instance or specific dunegon.");
						}
					}
				}
			}
			else if (activeChar.isGM())
			{
				try
				{
					StringTokenizer st = new StringTokenizer(params);
					String event = null;
					String registerTime = null;					
					if (st.hasMoreTokens())
					{
						event = st.nextToken();						
					}										
					// List of Commands
					if (event.equalsIgnoreCase("register"))
					{
						int time = 0;
						try
						{
							registerTime = st.nextToken();
						}
						catch(Exception e)
						{							
						}
						try
						{
							if (registerTime != null)
							{
								time = Integer.valueOf(registerTime);
							}
						}
						catch (Exception e)
						{
							
						}
						if (time > 0)
						{
							_gmId = activeChar.getObjectId();
							setRegisterTime(String.valueOf(time));
						}
						else
						{
							activeChar.sendMessage("You must set time to register");
						}
					}
					else if (event.equalsIgnoreCase("unregister"))
					{
						if (_isRegisterTime)
						{
							_isRegisterTime = false;
							Broadcast.announceToOnlinePlayers("|| " + activeChar.getName() + " || Event canceled. Sorry.",2);
							_registeredPlayers.clear();
						}
					}
					else if (event.equalsIgnoreCase("start"))
					{
						startEvent();
					}
					else if (event.equalsIgnoreCase("stop"))
					{
						stopEvent();
					}
					else if (event.equalsIgnoreCase("info"))
					{
						if (_isRegisterTime)
						{
							Broadcast.announceToOnlinePlayers(String.valueOf(_registeredPlayers.size())+" is now registered.",2);
						}
						else 
						{
							activeChar.sendMessage("You can use that command only in register time");
						}
					}
					else if (event.equalsIgnoreCase("winner"))
					{
						if (activeChar.getTarget() != null)
						{							
							if (_isStarted && activeChar.getTarget() instanceof L2PcInstance)
							{
								String targetPlayerName = activeChar.getTarget().getName();
								if (targetPlayerName != null)
								{
									L2PcInstance eventPlayer = L2World.getInstance().getPlayer(targetPlayerName);
									if (eventPlayer != null)
									{
										if (_winPlayers.equals(eventPlayer.getObjectId()))
										{
											_winPlayers.remove(eventPlayer.getObjectId());
											activeChar.sendMessage("Player ["+eventPlayer.getName() +"] removed from winner list");
										}
										else
										{
											_winPlayers.add(eventPlayer.getObjectId());
											activeChar.sendMessage("Player ["+eventPlayer.getName() +"] added to winner list");
										}
									}
								}
								else
								{
									activeChar.sendMessage("You must write '.event winner nickname' to give nickname to winner list.");
								}
							}
							else
							{
								activeChar.sendMessage("Event not started yet or target is not a player.");
							}
						}
						else
						{
							activeChar.sendMessage("Select player to give winner flag.");
						}							
					}
					else if (event.equalsIgnoreCase("list"))
					{
						if (_isStarted || _isRegisterTime)
						{
							activeChar.sendMessage("Event Registration:");
							int count = _startedPlayers.size();
							activeChar.sendMessage(String.valueOf(count)+" players registered for event.");
						}
					}
					else if (event.equalsIgnoreCase("reward"))
					{
						if (_isStarted)
						{
							for(int eventPlayerId: _winPlayers)
							{
								L2PcInstance eventPlayer = L2World.getInstance().getPlayer(eventPlayerId);
								if (eventPlayer != null)
								{
									eventPlayer.getInventory().addItem("Admin", Special_Coin, 150, eventPlayer, null);
									eventPlayer.setVitalityPoints(PcStat.MAX_VITALITY_POINTS, true);
									eventPlayer.setPcBangPoints(eventPlayer.getPcBangPoints() + 1500);
								}	
							}
							_winPlayers.clear();
						}
					}
					else
					{
						activeChar.sendMessage("Use command .event to check event commands");
					}
				}
				catch (Exception e)
				{
					activeChar.sendMessage("Use command .event to check event commands");
				}
			}
			else
			{
				activeChar.sendMessage("Only gm can use advanced event command");
			}
		}
		return true;
	}
	
	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}
