package events.SuperStar;

import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.gameserver.model.quest.State;
import l2.Rambase.util.Rnd;

import events.EventsConfig;

/**
 * @author  L2RamBase Project
 * @recoded for Hi5 - ShadowMan
 */
public class SuperStar extends Quest
{
	private static final String qn = "SuperStar";

	/**
	 * Event Npc
	 * @param Npc
	 */	
	private static final int SuperStarNpc = 7107;
	/**
	 * List of Event Monsters
	 * @param Npc
	 */
	private static final int[] EventMonsters = 
	{ 
		7000,7001,7002,7003,7004,7005,7006,7007,7008,7009,
		7010,7011,7012,7013,7014,7015,7016,7017,7018,7019,
		7020,7021,7022,7023
	};
	
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
		if (EventsConfig.SS_STARTED)
		{
			htmltext = "welcome.htm";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}
		
	/**
	 * On Advanced Event Script 
	 */
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		int starInteger;
		try
		{
			starInteger = Integer.valueOf(st.get("starInteger"));
		}
		catch (Exception e)
		{
			starInteger = 0;
		}
		
		if (event.equalsIgnoreCase("prizes"))
		{
			htmltext = "prizes.htm";
		}
		if (event.equalsIgnoreCase("play_5"))
		{
			if (starInteger >= 5)
			{
				rewardPlayer(player,5);
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("play_25"))
		{
			if (starInteger >= 25)
			{
				rewardPlayer(player,25);
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("play_50"))
		{
			if (starInteger >= 50)
			{
				rewardPlayer(player,50);
				htmltext = "prizes.htm";
			}
			else
			{
				htmltext = "no.htm";
			}
		}
		if (event.equalsIgnoreCase("info"))
		{
			htmltext = "info.htm";
			if (starInteger >0)
			{
				player.sendMessage("You have "+ String.valueOf(starInteger)+" stars.");
			}
		}
		if (event.equalsIgnoreCase("back"))
		{
			htmltext = "welcome.htm";
		}
       	return htmltext;
	}

	/**
	 * RewardList
	 * 5 -] Adena 450-4500, Backup Stones - 1
	 * 25 -] Adena 750-4500, Vitality 5000, Backup Stones - 1
	 * 50 -] Adena 4000-4500, Vitality 10000,Backup Stones - 2
	 * @param player
	 * @param i
	 */
	private void rewardPlayer(L2PcInstance player, int i) 
	{
		QuestState st = player.getQuestState(qn);
		int starInteger;
		try
		{
			starInteger = Integer.valueOf(st.get("starInteger"));
		}
		catch (Exception e)
		{
			starInteger = 0;
		}
		int consumedValue = 0;
		if (i == 5)
		{
			consumedValue = starInteger - i;
			st.set("starInteger", String.valueOf(consumedValue));
			int randomChance = Rnd.get(100);
			if (randomChance < 50)
			{
				st.giveItems(57, Rnd.get(450,player.getMaxHp()));
			}
			else if (randomChance >=50 && randomChance < 70)
			{				
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_D, 1);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_C, 1);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_B, 1);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_A, 1);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_S, 1);
				}
			}
			else
			{
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_D, 1);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_C, 1);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_B, 1);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_A, 1);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_S, 1);
				}
			}
		}
		else if (i == 25)
		{
			consumedValue = starInteger - i;
			st.set("starInteger", String.valueOf(consumedValue));
			int randomChance = Rnd.get(100);
			if (randomChance < 50)
			{
				st.giveItems(57, Rnd.get(750,player.getMaxHp()));
			}
			else if (randomChance >=50 && randomChance < 70)
			{				
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_D, 1);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_C, 1);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_B, 1);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_A, 1);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_S, 1);
				}
			}
			else if (randomChance >=70 && randomChance < 90)
			{
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_D, 1);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_C, 1);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_B, 1);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_A, 1);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_S, 1);
				}
			}
			else
			{
				player.setVitalityPoints(player.getVitalityPoints() + 5000, false);
			}
		}
		else if (i == 50)
		{
			consumedValue = starInteger - i;
			st.set("starInteger", String.valueOf(consumedValue));
			int randomChance = Rnd.get(100);
			if (randomChance < 50)
			{
				st.giveItems(57, Rnd.get(1250,player.getMaxHp()));
			}
			else if (randomChance >=50 && randomChance < 70)
			{				
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_D, 2);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_C, 2);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_B, 2);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_A, 2);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_WEP_S, 2);
				}
			}
			else if (randomChance >=70 && randomChance < 90)
			{
				int level = player.getLevel();
				if (level < 40)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_D, 2);	
				}
				else if (level >=40 && level < 52)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_C, 2);
				}
				else if (level >=52 && level < 61)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_B, 2);
				}
				else if (level >=61 && level < 76)
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_A, 2);
				}
				else
				{
					st.giveItems(EventsConfig.SS_BACKUP_STONE_ARM_S, 2);
				}
			}
			else
			{
				player.setVitalityPoints(player.getVitalityPoints() + 10000, false);
			}
		}		
	}

	/**
	 * On Kill Monster Script
	 */
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int npcId = npc.getNpcId();
		if (EventsConfig.SS_STARTED)
		{
			for(int ID : EventMonsters)
			{ 
				if (npcId == ID)
				{
					int killedValue = 0;
					try
					{
						killedValue = Integer.valueOf(st.get("starInteger")) + 1;						
					}
					catch(Exception e)
					{
						killedValue = 1;
					}
					st.set("starInteger", String.valueOf(killedValue));
					player.sendMessage("You find 1 star and have " + st.get("starInteger")+" stars.");
				}
			}			
		}
		return super.onKill(npc, player, isPet);
	}	
	
	public SuperStar(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(SuperStarNpc);
		addFirstTalkId(SuperStarNpc);
		addTalkId(SuperStarNpc);
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}		
	}
	public static void main(String[] args)
	{
		new SuperStar(-1,qn,"events");
		if (EventsConfig.SS_STARTED)
			_log.info("Event System: SuperStar Event loaded ...");
	}
}