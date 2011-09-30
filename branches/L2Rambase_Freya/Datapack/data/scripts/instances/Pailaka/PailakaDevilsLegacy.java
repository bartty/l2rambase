/**
 * Pailaka 2nd
 * @author L2Rambase Project
 */
package instances.Pailaka;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.InstanceManager.InstanceWorld;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Instance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.Rnd;

public class PailakaDevilsLegacy extends Quest
{
	private static final String qn = "129_PailakaDevilsLegacy";

	private static boolean debug = false;
	private static final int INSTANCE_ID = 44;
	private static final int[] TELEPORT = { 76206, -219386, -3752 };
	private static final int[] BACK = { 83385,148626,-3400 };
	private static final int ZONE = 20109;
	/**
	 * NPC
	 */
	private static final int SURVIVOR = 32498;
	private static final int SUPPORTER = 32501;
	private static final int DWARF = 32508;
	private static final int DWARF2 = 32511;
	/**
	 * Raids
	 */
	private static final int KAMS = 18629;
	private static final int HIKORO = 18630;
	private static final int ALKASO = 18631;
	private static final int GERBERA = 18632;
	private static final int LEMATAN = 18633; //respawn 52212 217256 -3344 h16380
	/**
	 * Special Monsters
	 */
	private static final int CHEST = 32495;
	private static final int[] PAILAKA_MOBS  = { 18623,18624,18625,18626,18627 };
	/**
	 * Drop List
	 */
	private static final int[] HERBS = { 8601,8602,8604,8605 };
	private static final int[] CHESTDROP = { 13033,13048,13049,13059 };
	private static final int[] AMOUNTS1  = { 1,2 };
	private static final int[] AMOUNTS2  = { 1,2,3,4,5,6,7,8,9,10 };
	/**
	 * Items
	 */
	private static final int SWORD = 13042;
	private static final int ENHANCED_SWORD = 13043;
	private static final int COMPLETE_SWORD = 13044;
	private static final int UPGRADE1 = 13046;
	private static final int UPGRADE2 = 13047;
	private static final int PAILAKA_BRACELET = 13295;
	private static final int PSOE = 13129;
	
	private static final int ITEMS[] = { SWORD,ENHANCED_SWORD,COMPLETE_SWORD,UPGRADE1,UPGRADE2 };
	/**
	 * Check Party
	 * @param player
	 * @return
	 */
	private boolean checkCondition(L2PcInstance player)
	{
	    if (player.getParty() == null)
	    {
	    	return true;
	    }
	    else
	    {
	        player.sendPacket(SystemMessage.sendString("Pailaka only for one person."));
	        return false;
	    }
	}
	/**
	 * Teleport Player
	 * @param player
	 * @param coords
	 * @param instanceId
	 */
	private static final void teleportPlayer(L2PcInstance player, int[] coords, int instanceId)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(instanceId);
		player.teleToLocation(coords[0], coords[1], coords[2], true);		
	}
	/**
	 * EnterInstance
	 * @param player
	 */
	private final synchronized void enterInstance(L2PcInstance player)
	{
		//check for existing instances for this player
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			if (world.templateId != INSTANCE_ID)
			{
				player.sendPacket(new SystemMessage(SystemMessageId.ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER));
				return;
			}
			Instance inst = InstanceManager.getInstance().getInstance(world.instanceId);
			if (inst != null)
				teleportPlayer(player, TELEPORT, world.instanceId);
			return;
		}
		//New instance
		else
		{
			final int instanceId = InstanceManager.getInstance().createDynamicInstance("PailakaDevilsLegacy.xml");

			world = InstanceManager.getInstance().new InstanceWorld();
			world.instanceId = instanceId;
			world.templateId = INSTANCE_ID;
			InstanceManager.getInstance().addWorld(world);

			world.allowed.add(player.getObjectId());
			teleportPlayer(player, TELEPORT, instanceId);
		}
	}
	/**
	 * OnAdvEvent 
	 */
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{		
		String htmltext = event;
        QuestState st = player.getQuestState(qn);
        if (st == null)
        {
        	st = newQuestState(player);
        }
        int npcId = npc.getNpcId();

        if (npcId == SURVIVOR)
        {
        	if (event.equalsIgnoreCase("32498-01.htm"))
        	{
        		if (player.getLevel() < 61 || player.getLevel() > 67)
        		{
        			htmltext = "32498-08.htm";
        		}
        	}
        	if (event.equalsIgnoreCase("32498-00.htm"))
       	 	{
        		htmltext = "32498-00.htm";
       	 	}
        	if (event.equalsIgnoreCase("32498-04.htm"))
        	{
        		if (checkCondition(player) == false)
        		{
        			return "";
        		}
        		st.set("cond","1");
        		st.setState(State.STARTED);
        		st.playSound("ItemSound.quest_accept");
        	}
        	if (event.equalsIgnoreCase("32498-06.htm"))
        	{
        		enterInstance(player);
        		st.set("cond","2");
        		st.playSound("ItemSound.quest_middle");
        	}
        	if (event.equalsIgnoreCase("32498-00.htm"))
            {
            	htmltext = "32498-00.htm";
            }
        }
        if (npcId == SUPPORTER)
        {
        	if (event.equalsIgnoreCase("32501-02.htm"))
        	{
        		st.playSound("ItemSound.quest_middle");
        		st.giveItems(SWORD, 1);
        		st.set("cond","3");
        	}
        }       
        if (npcId == DWARF)
        {
        	if (event.equalsIgnoreCase("enchance"))
        	{
        		L2Summon pet = player.getPet();
        		if (pet != null)
        		{
        			htmltext = "32508-01.htm";
        		}
        		else
        		{
        			int stateKams = st.getInt("kams");
        			if (st.getQuestItemsCount(SWORD) == 1 && st.getQuestItemsCount(ENHANCED_SWORD) < 1)
        			{
        				if (st.getQuestItemsCount(UPGRADE1) == 0)
        				{
        					htmltext = "32508-04.htm";
        				}
        				else if (stateKams == 1 && st.getQuestItemsCount(UPGRADE1) == 1)
        				{
        					st.playSound("ItemSound.quest_itemget");
        					st.takeItems(SWORD, -1);
        					st.takeItems(UPGRADE1, -1);
        					st.giveItems(ENHANCED_SWORD, 1);
        					htmltext = "32508-02.htm";
        				}
        			}
                }
            }
        }
        if (npcId == DWARF2)
        {
        	if (event.equalsIgnoreCase("32511-01.htm"))
        	{
        		L2Summon pet = player.getPet();
        		if (pet != null)
        		{
        			htmltext = "32511.htm";
        		}
        		else
        		{
        			st.takeItems(COMPLETE_SWORD,-1);
        			st.takeItems(SWORD,-1);
        			st.takeItems(ENHANCED_SWORD,-1);
        			st.takeItems(UPGRADE1, -1);
        			st.takeItems(UPGRADE2, -1);
        			st.playSound("ItemSound.quest_finish");
        			st.giveItems(PSOE, 1);
        			st.giveItems(PAILAKA_BRACELET, 1);
        			player.setVitalityPoints(20000, true);
        			st.addExpAndSp(10800000, 950000);
        			st.exitQuest(false);
        			InstanceManager.getInstance().getInstance(player.getInstanceId()).setDuration(1);
        			takePailakaItems(player);
        			teleportPlayer(player,BACK,0);
        		}
        	}
        }
        return htmltext;
	}
	/**
	 * OnFirstTalk
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
	    QuestState st = player.getQuestState(qn);
	    if (st == null)
	    {
	    	st = newQuestState(player);
	    }
	    if (debug && !player.isGM())
	    {
	    	st.takeItems(SWORD, -1);
	    	st.takeItems(ENHANCED_SWORD, -1);
	    	st.takeItems(COMPLETE_SWORD, -1);
	    	st.takeItems(UPGRADE1, -1);
	    	st.takeItems(UPGRADE2, -1);
	    	return "";
	    }
	    int npcId = npc.getNpcId();
	    byte state = st.getState();
	    if (state == State.COMPLETED)
	    {
	        htmltext = "<html><body>This quest has already been completed.</body></html>";
	        return htmltext;
	    }
	    int cond = st.getInt("cond");
	    if (npcId == SURVIVOR)
	    {
	    	if (state == State.COMPLETED)
	    	{
	    		return "32498-07.htm";
	    	}	    	
	    	else if (cond == 1)
	    	{
	    		return "32498-05.htm";
	    	}	
	    	else
	    	{
	    		return "32498.htm";
	    	}
	    }
	    if (npcId == DWARF)
	    {
	    	if (cond == 3)
	    	{
	    		int stateKams = st.getInt("kams");
	    		int stateAlkaso = st.getInt("alkaso");
	    		if (stateKams == 1 && stateAlkaso == 1 && st.getQuestItemsCount(UPGRADE2) == 1 && st.getQuestItemsCount(UPGRADE1) == 0 && st.getQuestItemsCount(ENHANCED_SWORD) == 1)
	    		{
	    			L2Summon pet = player.getPet();
	    			if (pet != null)
	    			{
	    				return "32508-01.htm";
	    			}
	    			else
	    			{
	    				st.playSound("ItemSound.quest_itemget");
	    				st.giveItems(COMPLETE_SWORD, 1);
	    				st.takeItems(ENHANCED_SWORD, -1);
	    				st.takeItems(UPGRADE2, -1);
	    				return "32508-03.htm";
	    			}
	    		}
	    		else if (st.getQuestItemsCount(ENHANCED_SWORD) > 0)
	    		{
	    			return "32508-05.htm";
	    		}
	    		else
	    		{
	    			return "32508.htm";
	    		}
	    	}
	    	return "<html><body>I don't have any item for you.</body></html>";
	    }
	    if (npcId == DWARF2)
	    {
	    	if (cond == 4)
	    	{
	    		return "32511.htm";
	    	}
	    }	    
	    return htmltext;
	}

	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
		QuestState st = player.getQuestState(qn);
	    if (st == null)
	    {
	    	st = newQuestState(player);
	    }
	    int npcId = npc.getNpcId();
	    int cond = st.getInt("cond");
	    if (npcId == SUPPORTER)
	    {
	    	if (cond == 2)
	    	{
	    		htmltext = "32501.htm";
	    	}
	    	else if (cond > 2)
	    	{
	    		htmltext = "32501-04.htm";
	    	}
	    }	    
	    return htmltext;
	}

	public final String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null || st.getState() != State.STARTED)
		{
			return null;
		}
        int npcId = npc.getNpcId();
        int cond = st.getInt("cond");
        int stateKams = st.getInt("kams");
        int stateHikoro = st.getInt("hikoro");
        int stateAlkaso = st.getInt("alkaso");
        int stateGerbera = st.getInt("gerbera");
        if (npcId == KAMS)
        {
            if (cond == 3 && stateKams == 0)
            {
                st.playSound("ItemSound.quest_itemget");
                st.giveItems(UPGRADE1, 1);
                stateKams = 1;
                st.set("kams","1");
            }
        }
        else if (npcId == HIKORO)
        {
            if (cond == 3 && stateHikoro == 0)
            {
                st.playSound("ItemSound.quest_middle");
                stateHikoro = 1;
                st.set("hikoro","1");
            }
        }
        else if (npcId == ALKASO)
        {
            if (cond == 3 && stateAlkaso == 0)
            {
                st.playSound("ItemSound.quest_itemget");
                st.giveItems(UPGRADE2, 1);
                stateAlkaso = 1;
                st.set("alkaso","1");
            }
        }
        else if (npcId == GERBERA)
        {
            if (cond == 3 && stateGerbera == 0)
            {
                st.playSound("ItemSound.quest_middle");
                stateGerbera = 1;
                st.set("gerbera","1");
            }
        }
        else if (npcId == CHEST)
        {
        	if (st.getRandom(100) < 80)
        	{
       		     st.giveItems(CHESTDROP[st.getRandom(CHESTDROP.length)],AMOUNTS2[st.getRandom(AMOUNTS2.length)]);
        	}
        	else
        	{
       		     st.giveItems(13150,1);
        	}
        }
        for (int mob : PAILAKA_MOBS)
        {
        	if (npcId == mob)
        	{
        		if (st.getRandom(100) < 80)
        		{
       		     	dropHerb(npc,player,HERBS);
        		}
        	}
        }
        if (stateKams == 1 && stateHikoro == 1 && stateAlkaso == 1 && stateGerbera == 1 && cond == 3)
        {
            st.addSpawn(LEMATAN, 56452, 216038, -3509, 32760, false, 0, false);
        }
        if (npcId == LEMATAN && cond == 3)
        {
            st.set("cond","4");
            st.playSound("ItemSound.quest_middle");
            addSpawn(DWARF2,84990,-208376,-3342,55000,false,0,false,npc.getInstanceId());
        }
		return super.onKill(npc, player, isPet);
	}
	
	private static final void dropHerb(L2Npc mob, L2PcInstance player, int[] drop)
	{
		final int chance = Rnd.get(100);
		for (int i = 0; i < drop.length; i++)
		{
			if (chance < 30)
			{
				((L2MonsterInstance)mob).dropItem(player, drop[i], AMOUNTS1[Rnd.get(AMOUNTS1.length)]);
				return;
			}
		}
	}
	
	public String onExitZone(L2Character character, L2ZoneType zone)
	{
		if (character instanceof L2PcInstance
				&& !character.isDead()
				&& !character.isTeleporting()
				&& ((L2PcInstance)character).isOnline() > 0)
		{
			InstanceWorld world = InstanceManager.getInstance().getWorld(character.getInstanceId());
			if (world != null && world.templateId == INSTANCE_ID)
			{
				ThreadPoolManager.getInstance().scheduleGeneral(new Teleport(character, world.instanceId), 1000);
			}
			else
			{
				if (character instanceof L2PcInstance)
				{
					takePailakaItems(character);
				}
			}
		}		
		return super.onExitZone(character,zone);
	}
	
	static final class Teleport implements Runnable
	{
		private final L2Character _char;
		private final int _instanceId;

		public Teleport(L2Character c, int id)
		{
			_char = c;
			_instanceId = id;
		}

		public void run()
		{
			try
			{
				teleportPlayer((L2PcInstance)_char, TELEPORT, _instanceId);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void takePailakaItems(L2Character character) 
	{
		if (character instanceof L2PcInstance)
		{
			if (character.getInstanceId() != INSTANCE_ID)
			{
				for (int itemId: ITEMS)
				{
					try
					{
						L2PcInstance player = (L2PcInstance) character;
						long count = character.getInventory().getItemByItemId(itemId).getCount();
						player.destroyItemByItemId("PailakaItems", itemId, count, player, false);
					}
					catch (Exception e)
					{					
					}
				}
			}
		}		
	}

	public PailakaDevilsLegacy(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(SURVIVOR);
		addTalkId(SUPPORTER);
		addTalkId(SURVIVOR);
		addTalkId(DWARF);
		addTalkId(DWARF2);
		addFirstTalkId(SURVIVOR);
		addFirstTalkId(DWARF2);
		addStartNpc(DWARF);
		addStartNpc(DWARF2);
		addFirstTalkId(DWARF);
		for (int monster: PAILAKA_MOBS)
		{
			addKillId(monster);
		}
		addKillId(CHEST);
		addKillId(KAMS);
		addKillId(GERBERA);
		addKillId(LEMATAN);
		addKillId(HIKORO);
		addKillId(ALKASO);
		addExitZoneId(ZONE);
		questItemIds = ITEMS;
	}

	public static void main(String[] args)
	{
		new PailakaDevilsLegacy(129, qn, "Pailaka - Devil's Legacy");
	}
}