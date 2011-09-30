/**
 * PcCafe Manager
 * 
 * Information:
 * PcCafe Manager is PcCafe points manager for pc cafe service.
 */
package services.PcCafeManager;

import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.stat.PcStat;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.network.serverpackets.ExPCCafePointInfo;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public class PcCafeManager extends Quest
{
	private static final String qn = "PcCafeManager";	
	
	/**
	 * PcCafe Npc Manager
	 * @param npc
	 */
	private static final int PC_CAFE_NPC = 7109;
	private static final int PC_CAFE_VIT_REWARD = 1000;
	private static final int PC_CAFE_ADENA_REWARD = 2500;
	private static final int PC_CAFE_SP_REWARD = 1500;
	private static final int PC_CAFE_COIN_REWARD = 750;
	                         
	
	private static final String start = "<html><body><font color=LEVEL>PcCafe Manager</font><br>"+
	"Welcome to Cafeteria. There is greates power, experience and knowledge that you can obtain if you have enough <font color=LEVEL>Pc Bang Points</font>.So you can obtain:<br>"+
	"<a action=\"bypass -h Quest PcCafeManager storage\">About Storage</a><br>"+
	"<a action=\"bypass -h Quest PcCafeManager vitality\">Recover Vitality ("+String.valueOf(PC_CAFE_VIT_REWARD)+" Pc Cafe Points)</a><br>"+
	"<a action=\"bypass -h Quest PcCafeManager adena\">Search for Adena Box ("+String.valueOf(PC_CAFE_ADENA_REWARD)+" Pc Cafe Points)</a><br>"+
	"<a action=\"bypass -h Quest PcCafeManager sp\">Find my Sp Points ("+String.valueOf(PC_CAFE_SP_REWARD)+" Pc Cafe Points)</a><br>"+
	"<a action=\"bypass -h Quest PcCafeManager mousecoin\">Get 50 Mouse Coins ("+String.valueOf(PC_CAFE_COIN_REWARD)+" Pc Cafe Points)</a><br>"+
	"<a action=\"bypass -h Quest PcCafeManager exchange\">Exchange Points to Items</a>"+
	"</body></html>";
	
	/**
	 * Storage World
	 */
	private static final int Storage_Tree = 7110;
	private static final int Storage_Stone = 7111;
	private static final int Storage_Bear = 7112;	
	
	private static final int[] Storage_Loc =
	{
		110425,-82151,-1584
	};
	private static final int[] Storage_Tree_Loc =
	{
		110734,-82330,-1597
	};
	private static final int[] Storage_Stone_Loc =
	{
		110299,-82393,-1584
	};
	private static final int[] Storage_Bear_Loc =
	{
		110299,-81933,-1597
	};
	private static final int Storage_Tree_Tool = 8589;
	private static final int Storage_Stone_Tool = 8588;
	private static final int Storage_Fabric_Tool = 8578;
	private static final int[] Storage_Tree_Materials =
	{
		1662,1685,1684,1741
	};
	private static final int[] Storage_Stone_Materials =
	{
		1679,1681,1683,1780
	};
	private static final int[] Storage_Fabric_Materials =
	{
		1729,1779,1732,1734
	};
	private static final int[] Storage_Craft_Style_1 = 
	{
		8185,8186,8187,8562,8563,8564
	};
	private static final int[] Storage_Craft_Style_2 = 
	{
		10243,10246,10247,10248,12372
	};
	private static final int[] Storage_Craft_Style_3 = 
	{
		13505,13513,20020,20021,20024,20634,20666
	};
	
	public static final int[] GiranLoc = { 83385,148626,-3400 };
	public static final int STORAGE_SPAWN_TIME = 1000;
	public static final int STORAGE_RE_ENTER_TIME = 300;
	private final FastMap<Integer, Storage> _storage = new FastMap<Integer, Storage>();
	
	private class Storage 
	{		
		private int _leaderId;
		private int _instanceId;
		private boolean _canEnter;
		private long _enterTime;

		private class StorageReEntrenceTimerTask implements Runnable 
		{
			public void run()
			{
				_canEnter = true;
			}
		}

		private class StorageSpawnTask implements Runnable 
		{
			public void run() 
			{
				StorageSpawn(Storage_Tree,Storage_Tree_Loc);
				StorageSpawn(Storage_Stone,Storage_Stone_Loc);
				StorageSpawn(Storage_Bear,Storage_Bear_Loc);
			}
		}
		
		private Storage(int id) 
		{
			try 
			{
				_leaderId = id;
				_canEnter = false;
				_enterTime = System.currentTimeMillis();				
				_instanceId = InstanceManager.getInstance().createDynamicInstance("Storage.xml");
				ThreadPoolManager.getInstance().scheduleGeneral(new StorageReEntrenceTimerTask(), (STORAGE_RE_ENTER_TIME*60)*1000);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}

		private void StorageSpawn(int npcId,int[] loc) 
		{
			try 
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(npcId);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(loc[0]);
					spawnDat.setLocy(loc[1]);
					spawnDat.setLocz(loc[2]);
					spawnDat.setHeading(0);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(30);
					spawnDat.startRespawn();
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();
				} 
				else 
				{
					_log.warning("Storage: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("Storage: Spawns could not be initialized: "	+ e);
			}
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new StorageSpawnTask(), STORAGE_SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}
		
		private int getLeaderId() 
		{
			return _leaderId;
		}

		private long getEnterTime()
		{
			return _enterTime;
		}

		private boolean getCanEnter()
		{
			return _canEnter;
		}
	}

	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
			st.setState(State.STARTED);
		}
		String htmltext = "";
		if (npc.getNpcId() == PC_CAFE_NPC)
		{
			htmltext = start;
		}
		if (npc.getNpcId() == Storage_Tree || npc.getNpcId() == Storage_Stone || npc.getNpcId() == Storage_Bear)
		{
			player.sendPacket(ActionFailed.STATIC_PACKET);
		}
		return htmltext;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (npc.getNpcId() == PC_CAFE_NPC)
		{
			if (event.equalsIgnoreCase("vitality"))
			{
				int countPcPoints = player.getPcBangPoints();
				if (countPcPoints > PC_CAFE_VIT_REWARD)
				{
					countPcPoints = countPcPoints - PC_CAFE_VIT_REWARD;
					player.setPcBangPoints(countPcPoints);
					SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
					smsg.addNumber(PC_CAFE_VIT_REWARD); 
					player.sendPacket(smsg); 
					player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), PC_CAFE_VIT_REWARD, false, false, 1));
					player.setVitalityPoints(PcStat.MAX_VITALITY_POINTS, true); 
				}
				else
				{
					player.sendMessage("You don't have more than "+String.valueOf(PC_CAFE_VIT_REWARD)+" PcBang Points");
				}
				htmltext = start;
			}
			else if (event.equalsIgnoreCase("adena"))
			{
				int countPcPoints = player.getPcBangPoints();
				if (countPcPoints > PC_CAFE_ADENA_REWARD)
				{
					countPcPoints = countPcPoints - PC_CAFE_ADENA_REWARD;
					player.setPcBangPoints(countPcPoints);
					SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
					smsg.addNumber(PC_CAFE_ADENA_REWARD); 
					player.sendPacket(smsg); 
					player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), PC_CAFE_ADENA_REWARD, false, false, 1));
					st.giveAdena(Rnd.get(50000,150000), true); 
				}
				else
				{
					player.sendMessage("You don't have more than "+String.valueOf(PC_CAFE_ADENA_REWARD)+" PcBang Points");
				}
				htmltext = start;
			}
			else if (event.equalsIgnoreCase("sp"))
			{
				int countPcPoints = player.getPcBangPoints();
				if (countPcPoints > PC_CAFE_SP_REWARD)
				{
					countPcPoints = countPcPoints - PC_CAFE_SP_REWARD;
					player.setPcBangPoints(countPcPoints);
					SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
					smsg.addNumber(PC_CAFE_SP_REWARD); 
					player.sendPacket(smsg); 
					player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), PC_CAFE_SP_REWARD, false, false, 1));
					st.addExpAndSp(0, Rnd.get(50000,300000));
				}
				else
				{
					player.sendMessage("You don't have more than "+String.valueOf(PC_CAFE_SP_REWARD)+" PcBang Points");
				}
				htmltext = start;
			}
			else if (event.equalsIgnoreCase("mousecoin"))
			{
				int countPcPoints = player.getPcBangPoints();
				if (countPcPoints > PC_CAFE_COIN_REWARD)
				{
					countPcPoints = countPcPoints - PC_CAFE_COIN_REWARD;
					player.setPcBangPoints(countPcPoints);
					SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
					smsg.addNumber(PC_CAFE_COIN_REWARD); 
					player.sendPacket(smsg); 
					player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), PC_CAFE_COIN_REWARD, false, false, 1));
					st.giveItems(10639, 50);
				}
				else
				{
					player.sendMessage("You don't have more than "+String.valueOf(PC_CAFE_COIN_REWARD)+" PcBang Points");
				}
				htmltext = start;
			}
			else if (event.equalsIgnoreCase("exchange"))
			{
				L2Multisell.getInstance().separateAndSend(7109001, player, npc.getNpcId(), false, 0.0);
			}
			else if (event.equalsIgnoreCase("storage"))
			{
				if (player.getParty() != null)
				{
					player.sendMessage("You cannot enter here in party.");
					return start;
				}
				else
				{
					String selecttext = "";
					selecttext += "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					selecttext += "[<font color=LEVEL>Storage</font>]<br>";
					selecttext += "I create a special place where you can get some materials (wood, mine and fabric materials)."; 
					selecttext += "There you find tree, stone and old animal, so you need get tool and gain materials by attacking.";
					selecttext += "With this materials you can craft for PcBang Points some hair accesories.<br>";
					selecttext += "<a action=\"bypass -h Quest "+qn+" start\">Enter my storage</a><br1>";
					selecttext += "<a action=\"bypass -h Quest "+qn+" buytool\">Buy specific Tool</a><br1>";
					selecttext += "<a action=\"bypass -h Quest "+qn+" craftshop\">Craft Shop</a><br>";
					selecttext += "</body></html>";
					return selecttext;
				}
			}
			else if (event.startsWith("buytool"))
			{
				if (event.equalsIgnoreCase("buytool"))
				{
					htmltext = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					htmltext += "[<font color=LEVEL>Tool Shop</font>]<br>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" buytool_tree\">Buy Tree Tool (500 Pc Cafe Points)</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" buytool_stone\">Buy Stone Tool (500 Pc Cafe Points)</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" buytool_fabric\">Buy Fabric Tool (500 Pc Cafe Points)</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" storage\">Back</a><br>";
					htmltext += "</body></html>";
					return htmltext;
				}
				else
				{
					if(event.equalsIgnoreCase("buytool_tree"))
					{
						int countPcPoints = player.getPcBangPoints();
						if (countPcPoints > 500)
						{
							countPcPoints = countPcPoints - 500;
							player.setPcBangPoints(countPcPoints);
							SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
							smsg.addNumber(500); 
							player.sendPacket(smsg); 
							player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), 500, false, false, 1));
							st.giveItems(Storage_Tree_Tool,1);
						}
						else
						{
							player.sendMessage("You don't have more than "+String.valueOf(500)+" PcBang Points");
						}
					}
					else if (event.equalsIgnoreCase("buytool_stone"))
					{
						int countPcPoints = player.getPcBangPoints();
						if (countPcPoints > 500)
						{
							countPcPoints = countPcPoints - 500;
							player.setPcBangPoints(countPcPoints);
							SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
							smsg.addNumber(500); 
							player.sendPacket(smsg); 
							player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), 500, false, false, 1));
							st.giveItems(Storage_Stone_Tool,1);
						}
						else
						{
							player.sendMessage("You don't have more than "+String.valueOf(500)+" PcBang Points");
						}
					}
					else if (event.equalsIgnoreCase("buytool_fabric"))
					{
						int countPcPoints = player.getPcBangPoints();
						if (countPcPoints > 500)
						{
							countPcPoints = countPcPoints - 500;
							player.setPcBangPoints(countPcPoints);
							SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
							smsg.addNumber(500); 
							player.sendPacket(smsg); 
							player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), 500, false, false, 1));
							st.giveItems(Storage_Fabric_Tool,1);
						}
						else
						{
							player.sendMessage("You don't have more than "+String.valueOf(500)+" PcBang Points");
						}						
					}								
				}
			}
			else if (event.startsWith("craftshop"))
			{
				if (event.equalsIgnoreCase("craftshop"))
				{
					htmltext = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					htmltext += "[<font color=LEVEL>Craft Shop</font>]<br>";
					htmltext += "Click on icon for select: <br>";
					htmltext += "For 1000 Pc Cafe Points:<br>";
					htmltext += "<table><tr>";
					for(int itemId : Storage_Craft_Style_1)
					{
						htmltext += "<td><button action=\"bypass -h Quest "+qn+" craftshop_"+String.valueOf(itemId)+"\" width=32 height=32 back=\""+getIconName(itemId)+"\" fore=\""+getIconName(itemId)+"\"></td>";						
					}
					htmltext += "</tr></table>";
					htmltext += "<br>For 2000 Pc Cafe Points:<br>";
					htmltext += "<table><tr>";
					for(int itemId : Storage_Craft_Style_2)
					{
						htmltext += "<td><button action=\"bypass -h Quest "+qn+" craftshop_"+String.valueOf(itemId)+"\" width=32 height=32 back=\""+getIconName(itemId)+"\" fore=\""+getIconName(itemId)+"\"></td>";
					}
					htmltext += "</tr></table>";
					htmltext += "<br>For 3500 Pc Cafe Points:<br>";
					htmltext += "<table><tr>";
					for(int itemId : Storage_Craft_Style_3)
					{
						htmltext += "<td><button action=\"bypass -h Quest "+qn+" craftshop_"+String.valueOf(itemId)+"\" width=32 height=32 back=\""+getIconName(itemId)+"\" fore=\""+getIconName(itemId)+"\"></td>";
					}
					htmltext += "</tr></table>";
					htmltext += "</body></html>";
					return htmltext;
				}
				else
				{
					// First Class
					if (event.equalsIgnoreCase("craftshop_8185"))
					{
						htmltext = craftItem(player,st,8185,1000);
					}
					if (event.equalsIgnoreCase("craftshop_8186"))
					{
						htmltext = craftItem(player,st,8186,1000);
					}
					if (event.equalsIgnoreCase("craftshop_8187"))
					{
						htmltext = craftItem(player,st,8187,1000);
					}
					if (event.equalsIgnoreCase("craftshop_8562"))
					{
						htmltext = craftItem(player,st,8562,1000);
					}
					if (event.equalsIgnoreCase("craftshop_8563"))
					{
						htmltext = craftItem(player,st,8563,1000);
					}
					if (event.equalsIgnoreCase("craftshop_8564"))
					{
						htmltext = craftItem(player,st,8564,1000);
					}
					// Second Class
					if (event.equalsIgnoreCase("craftshop_10243"))
					{
						htmltext = craftItem(player,st,10243,2000);
					}
					if (event.equalsIgnoreCase("craftshop_10246"))
					{
						htmltext = craftItem(player,st,10246,2000);
					}
					if (event.equalsIgnoreCase("craftshop_10247"))
					{
						htmltext = craftItem(player,st,10247,2000);
					}
					if (event.equalsIgnoreCase("craftshop_10248"))
					{
						htmltext = craftItem(player,st,10248,2000);
					}
					if (event.equalsIgnoreCase("craftshop_12372"))
					{
						htmltext = craftItem(player,st,12372,2000);
					}
					// Third Class
					if (event.equalsIgnoreCase("craftshop_13505"))
					{
						htmltext = craftItem(player,st,13505,3500);
					}
					if (event.equalsIgnoreCase("craftshop_13513"))
					{
						htmltext = craftItem(player,st,13513,3500);
					}
					if (event.equalsIgnoreCase("craftshop_20020"))
					{
						htmltext = craftItem(player,st,20020,3500);
					}
					if (event.equalsIgnoreCase("craftshop_20021"))
					{
						htmltext = craftItem(player,st,20021,3500);
					}
					if (event.equalsIgnoreCase("craftshop_20024"))
					{
						htmltext = craftItem(player,st,20024,3500);
					}
					if (event.equalsIgnoreCase("craftshop_20634"))
					{
						htmltext = craftItem(player,st,20634,3500);
					}
					if (event.equalsIgnoreCase("craftshop_20666"))
					{
						htmltext = craftItem(player,st,20666,3500);
					}					
				}
			}
			else if (event.startsWith("start")) 
			{
				Storage cafeClub = null;
				if (player.getParty() != null)
				{
					player.sendMessage("You cannot enter here in party.");
					return start;
				}
				else
				{
					if (!_storage.isEmpty())
					{
						if (_storage.containsKey(player.getObjectId()))
						{							
							cafeClub = _storage.get(player.getObjectId());
							if (cafeClub.getCanEnter() == false)
							{
								player.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
								long canEnterTime = ((STORAGE_RE_ENTER_TIME*60000) - (System.currentTimeMillis() - cafeClub.getEnterTime()));
								if (canEnterTime < 60000)
								{
									canEnterTime = 1;
								}
								else
								{
									canEnterTime = canEnterTime / 60000;
								}
								player.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");
								return start; 
							}
							else
							{									
								_storage.remove(cafeClub);
								cafeClub = new Storage(player.getObjectId());
								_storage.put(cafeClub.getLeaderId(), cafeClub);
							}
						}
					}
					if (cafeClub == null) 
					{ 
						cafeClub = new Storage(player.getObjectId());
						_storage.put(cafeClub.getLeaderId(), cafeClub);
					}					
					if (player.getPet() != null)
					{
						L2Summon pet = player.getPet();
						pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
						pet.setInstanceId(cafeClub.getInstanceId());
						pet.teleToLocation(Storage_Loc[0],Storage_Loc[1],Storage_Loc[2], true);
					}
					player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					player.setInstanceId(cafeClub.getInstanceId());
					player.teleToLocation(Storage_Loc[0],Storage_Loc[1],Storage_Loc[2], true);
					cafeClub.initSpawn();
				}
			}
		}
		return htmltext;
	}
	
	private String getIconName(int itemId) 
	{
		String iconName = "";
		switch(itemId)
		{
			case 8185:
				iconName = "icon.accessory_archer_hat_i00";
				break;
			case 8186:
				iconName = "icon.accessory_dwarf_goggle_i00";
				break;
			case 8187:
				iconName = "icon.accessory_hair_cornu_i00";
				break;
			case 8562:
				iconName = "icon.accessory_jester_cap_i00";
				break;
			case 8563:
				iconName = "icon.accessory_magic_cap_i00";
				break;
			case 8564:
				iconName = "icon.accessory_dandy_cap_i00";
				break;
			case 10243:
				iconName = "icon.accessory_panda_cap_i00";
				break;
			case 10246:
				iconName = "icon.accessory_frog_cap_i00";
				break;
			case 10247:
				iconName = "icon.accessory_fish_cap_i00";
				break;
			case 10248:
				iconName = "icon.accessory_straw_hat_i00";
				break;
			case 12372:
				iconName = "icon.monkey_cap_i00";
				break;
			case 13505:
				iconName = "icon.accessory_fish_cap2_i00";
				break;
			case 13513:
				iconName = "icon.accessory_mining_cap_i00";
				break;
			case 20020:
				iconName = "branchsys.icon.br_uniform_hat_i00";
				break;
			case 20021:
				iconName = "branchsys.icon.br_bamboo_hat_i00";
				break;
			case 20024:
				iconName = "branchsys.icon.br_eye_visor_i00";
				break;
			case 20634:
				iconName = "branchsys.icon.br_watermelon_cap_a_i00";
				break;
			case 20666:
				iconName = "branchsys.icon.br_valkyrie_cap_i00";
				break;
			default :
				break;
		}
		return iconName;
	}

	private String craftItem(L2PcInstance player, QuestState st, int itemId, int cafePoints) 
	{
		String text = "";
		int currentCafePoints = player.getPcBangPoints();
		switch(itemId)
		{
			case 8185:
			case 8186:
			case 8187:
			case 8562:
			case 8563:
			case 8564:
				if (currentCafePoints < cafePoints && !player.isGM())
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "You need "+ String.valueOf(cafePoints)+" PcBang Points to create that item.";
					text += "</body></html>";
					return text;
				}
				if (notHaveItems(player,st,1))
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[0]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[1]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[0]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[1]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[0]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[1]).getName();
					text += "For create that item you need "+"10 "+material_1+", 3 "+material_2+",10 "+material_3+", 3 "+material_4+",10 "+material_5+" and 3 "+material_6+".";
					text += "</body></html>";
					return text;
				}
				if (Rnd.get(100)>70)
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Failed.<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[0]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[1]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[0]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[1]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[0]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[1]).getName();
					text += "Failed creation takes "+"5 "+material_1+", 1 "+material_2+",5 "+material_3+", 1 "+material_4+",5 "+material_5+" and 1 "+material_6+".";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[0], 5);
					st.takeItems(Storage_Tree_Materials[1], 1);
					st.takeItems(Storage_Stone_Materials[0], 5);
					st.takeItems(Storage_Stone_Materials[1], 1);
					st.takeItems(Storage_Fabric_Materials[0], 5);
					st.takeItems(Storage_Fabric_Materials[1], 1);					
				}
				else
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Completed.<br1>";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[0], 10);
					st.takeItems(Storage_Tree_Materials[1], 3);
					st.takeItems(Storage_Stone_Materials[0], 10);
					st.takeItems(Storage_Stone_Materials[1], 3);
					st.takeItems(Storage_Fabric_Materials[0], 10);
					st.takeItems(Storage_Fabric_Materials[1], 3);
					if (!player.isGM())
					{
						currentCafePoints = currentCafePoints - cafePoints;
						player.setPcBangPoints(currentCafePoints);
						SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
						smsg.addNumber(cafePoints); 
						player.sendPacket(smsg); 
						player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), cafePoints, false, false, 1));
					}
					st.giveItems(itemId, 1);
				}
				break;
			case 10243:
			case 10246:
			case 10247:
			case 10248:
			case 12372:
				if (currentCafePoints < cafePoints && !player.isGM())
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "You need "+ String.valueOf(cafePoints)+" PcBang Points to create that item.";
					text += "</body></html>";
					return text;
				}
				if (notHaveItems(player,st,2))
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[1]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[2]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[1]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[2]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[1]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[2]).getName();
					text += "For create that item you need "+"10 "+material_1+", 3 "+material_2+",10 "+material_3+", 3 "+material_4+",10 "+material_5+" and 3 "+material_6+".";
					text += "</body></html>";
					return text;
				}
				if (Rnd.get(100)>70)
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Failed.<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[1]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[2]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[1]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[2]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[1]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[2]).getName();
					text += "Failed creation takes "+"5 "+material_1+", 1 "+material_2+",5 "+material_3+", 1 "+material_4+",5 "+material_5+" and 1 "+material_6+".";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[1], 5);
					st.takeItems(Storage_Tree_Materials[2], 1);
					st.takeItems(Storage_Stone_Materials[1], 5);
					st.takeItems(Storage_Stone_Materials[2], 1);
					st.takeItems(Storage_Fabric_Materials[1], 5);
					st.takeItems(Storage_Fabric_Materials[2], 1);					
				}
				else
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Completed.<br1>";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[1], 10);
					st.takeItems(Storage_Tree_Materials[2], 3);
					st.takeItems(Storage_Stone_Materials[1], 10);
					st.takeItems(Storage_Stone_Materials[2], 3);
					st.takeItems(Storage_Fabric_Materials[1], 10);
					st.takeItems(Storage_Fabric_Materials[2], 3);
					if (!player.isGM())
					{
						currentCafePoints = currentCafePoints - cafePoints;
						player.setPcBangPoints(currentCafePoints);
						SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
						smsg.addNumber(cafePoints); 
						player.sendPacket(smsg); 
						player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), cafePoints, false, false, 1));
					}
					st.giveItems(itemId, 1);
				}
				break;
			case 13505:
			case 13513:
			case 20020:
			case 20021:
			case 20024:
			case 20634:
			case 20666:
				if (currentCafePoints < cafePoints)
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "You need "+ String.valueOf(cafePoints)+" PcBang Points to create that item.";
					text += "</body></html>";
					return text;
				}
				if (notHaveItems(player,st,2))
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[2]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[3]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[2]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[3]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[2]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[3]).getName();
					text += "For create that item you need "+"10 "+material_1+", 3 "+material_2+",10 "+material_3+", 3 "+material_4+",10 "+material_5+" and 3 "+material_6+".";
					text += "</body></html>";
					return text;
				}
				if (Rnd.get(100)>70)
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Failed.<br1>";
					String material_1 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[2]).getName();
					String material_2 = ItemTable.getInstance().getTemplate(Storage_Tree_Materials[3]).getName();
					String material_3 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[2]).getName();
					String material_4 = ItemTable.getInstance().getTemplate(Storage_Stone_Materials[3]).getName();
					String material_5 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[2]).getName();
					String material_6 = ItemTable.getInstance().getTemplate(Storage_Fabric_Materials[3]).getName();
					text += "Failed creation takes "+"5 "+material_1+", 1 "+material_2+",5 "+material_3+", 1 "+material_4+",5 "+material_5+" and 1 "+material_6+".";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[2], 5);
					st.takeItems(Storage_Tree_Materials[3], 1);
					st.takeItems(Storage_Stone_Materials[2], 5);
					st.takeItems(Storage_Stone_Materials[3], 1);
					st.takeItems(Storage_Fabric_Materials[2], 5);
					st.takeItems(Storage_Fabric_Materials[3], 1);					
				}
				else
				{
					text = "<html><body><font color=LEVEL>PcCafe Manager</font><br>";
					text += "[<font color=LEVEL>Craft Shop</font>]<br1>";
					text += "Creation Completed.<br1>";
					text += "</body></html>";
					st.takeItems(Storage_Tree_Materials[2], 10);
					st.takeItems(Storage_Tree_Materials[3], 3);
					st.takeItems(Storage_Stone_Materials[2], 10);
					st.takeItems(Storage_Stone_Materials[3], 3);
					st.takeItems(Storage_Fabric_Materials[2], 10);
					st.takeItems(Storage_Fabric_Materials[3], 3);
					if (!player.isGM())
					{
						currentCafePoints = currentCafePoints - cafePoints;
						player.setPcBangPoints(currentCafePoints);
						SystemMessage smsg = new SystemMessage(SystemMessageId.USING_S1_PCPOINT); 
						smsg.addNumber(cafePoints); 
						player.sendPacket(smsg); 
						player.sendPacket(new ExPCCafePointInfo(player.getPcBangPoints(), cafePoints, false, false, 1));
					}
					st.giveItems(itemId, 1);
				}
				break;
			default:
				text = "<html><body>Error please contact administrator.</body></html>";
				break;
		}
		return text;
	}

	private boolean notHaveItems(L2PcInstance player, QuestState st, int grade) 
	{
		long woodElement_1 = st.getQuestItemsCount(Storage_Tree_Materials[0]);
		long woodElement_2 = st.getQuestItemsCount(Storage_Tree_Materials[1]);
		long woodElement_3 = st.getQuestItemsCount(Storage_Tree_Materials[2]);
		long woodElement_4 = st.getQuestItemsCount(Storage_Tree_Materials[3]);
		long stoneElement_1 = st.getQuestItemsCount(Storage_Stone_Materials[0]);
		long stoneElement_2 = st.getQuestItemsCount(Storage_Stone_Materials[1]);
		long stoneElement_3 = st.getQuestItemsCount(Storage_Stone_Materials[2]);
		long stoneElement_4 = st.getQuestItemsCount(Storage_Stone_Materials[3]);
		long fabricElement_1 = st.getQuestItemsCount(Storage_Fabric_Materials[0]);
		long fabricElement_2 = st.getQuestItemsCount(Storage_Fabric_Materials[1]);
		long fabricElement_3 = st.getQuestItemsCount(Storage_Fabric_Materials[2]);
		long fabricElement_4 = st.getQuestItemsCount(Storage_Fabric_Materials[3]);
		switch(grade)
		{
			case 1:				
				if (woodElement_1 < 10 || woodElement_2 < 3
						|| stoneElement_1 < 10 || stoneElement_2 < 3
						|| fabricElement_1 < 10 || fabricElement_2 < 3)
				{
					return true;
				}
				break;
			case 2:
				if (woodElement_2 < 10 || woodElement_3 < 3
						|| stoneElement_2 < 10 || stoneElement_3 < 3
						|| fabricElement_2 < 10 || fabricElement_3 < 3)
				{
					return true;
				}
				break;
			case 3:
				if (woodElement_3 < 10 || woodElement_4 < 3
						|| stoneElement_3 < 10 || stoneElement_4 < 3
						|| fabricElement_3 < 10 || fabricElement_4 < 3)
				{
					return true;
				}
				break;
			default:
				break;
		}
		return false;
	}
	
	public String onAttack (L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
    {
		QuestState st = attacker.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(attacker);
		}
		if (npc.getNpcId() == Storage_Tree)
		{
			if(attacker.getActiveWeaponItem() != null && attacker.getActiveWeaponItem().getItemId() == Storage_Tree_Tool)
			{
				if(st.get("toolhit") != null)
				{
					int toolhit = Integer.valueOf(st.get("toolhit"));
					if (toolhit == 5)
					{
						int random = Rnd.get(100);
						if (random < 70)
						{
							st.giveItems(Storage_Tree_Materials[Rnd.get(0,3)],1);
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "You found some materials"));
						}
						else
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "Nothing found"));
						}
						st.set("toolhit", "0");						
					}
					else
					{
						st.set("toolhit", String.valueOf(toolhit+1));
						if (Rnd.get(100)<20)
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " cutting tree"));
						}
					}
				}
				else
				{
					st.set("toolhit", "1");
					if (Rnd.get(100)<20)
					{
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " cutting tree"));
					}
				}
				npc.setIsInvul(false);
				attacker.stopAllEffects();
				return null;
			}
			else
			{				
				attacker.sendMessage("You need a "+ItemTable.getInstance().getTemplate(Storage_Tree_Tool).getName()+" to cut this tree.");
				npc.setIsInvul(true);
			}
		}
		else if (npc.getNpcId() == Storage_Stone)
		{
			if(attacker.getActiveWeaponItem() != null && attacker.getActiveWeaponItem().getItemId() == Storage_Stone_Tool)
			{
				if(st.get("toolhit") != null)
				{
					int toolhit = Integer.valueOf(st.get("toolhit"));
					if (toolhit == 5)
					{
						int random = Rnd.get(100);
						if (random < 70)
						{
							st.giveItems(Storage_Stone_Materials[Rnd.get(0,3)],1);
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "You found some materials"));
						}
						else
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "Nothing found"));
						}
						st.set("toolhit", "0");						
					}
					else
					{
						st.set("toolhit", String.valueOf(toolhit+1));
						if (Rnd.get(100)<20)
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " smashing stone"));
						}
					}
				}
				else
				{
					st.set("toolhit", "1");
					if (Rnd.get(100)<20)
					{
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " smashing stone"));
					}
				}
				npc.setIsInvul(false);
				attacker.stopAllEffects();
				return null;
			}
			else
			{				
				attacker.sendMessage("You need a "+ItemTable.getInstance().getTemplate(Storage_Stone_Tool).getName()+" to smash this stone.");
				npc.setIsInvul(true);
			}
		}
		else if (npc.getNpcId() == Storage_Bear)
		{
			if(attacker.getActiveWeaponItem() != null && attacker.getActiveWeaponItem().getItemId() == Storage_Fabric_Tool)
			{
				if(st.get("toolhit") != null)
				{
					int toolhit = Integer.valueOf(st.get("toolhit"));
					if (toolhit == 5)
					{
						int random = Rnd.get(100);
						if (random < 70)
						{
							st.giveItems(Storage_Fabric_Materials[Rnd.get(0,3)],1);
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "You found some materials"));
						}
						else
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), "Nothing found"));
						}
						st.set("toolhit", "0");						
					}
					else
					{
						st.set("toolhit", String.valueOf(toolhit+1));
						if (Rnd.get(100)<20)
						{
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " cutting fabrics"));
						}
					}
				}
				else
				{
					st.set("toolhit", "1");
					if (Rnd.get(100)<20)
					{
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), attacker.getName() + " cutting fabrics"));
					}
				}
				npc.setIsInvul(false);
				attacker.stopAllEffects();
				return null;
			}
			else
			{				
				attacker.sendMessage("You need a "+ItemTable.getInstance().getTemplate(Storage_Fabric_Tool).getName()+" to cut off fabric.");
				npc.setIsInvul(true);
			}
		}
		return super.onAttack(npc, attacker, damage, isPet);
    }
	
	public String onSpawn(L2Npc npc) 
	{
		npc.setIsImmobilized(true);
		npc.disableCoreAI(true);
		return null;
	}

	public PcCafeManager(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(PC_CAFE_NPC);
		addFirstTalkId(PC_CAFE_NPC);
		addTalkId(PC_CAFE_NPC);
		addAttackId(Storage_Tree);
		addSpawnId(Storage_Tree);
		addAttackId(Storage_Stone);
		addSpawnId(Storage_Stone);
		addAttackId(Storage_Bear);
		addSpawnId(Storage_Bear);
	}	
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new PcCafeManager(-1,qn,"PcCafe Manager");
		_log.warning("PcCafe Manager System: PcCafe Npc Loaded ...");
	}
}