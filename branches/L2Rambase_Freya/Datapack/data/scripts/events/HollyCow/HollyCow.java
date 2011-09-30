package events.HollyCow;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.util.Rnd;

import events.EventsConfig;


public class HollyCow extends Quest
{
	private static final String qn = "HollyCow";

	/*
	 * Special Farmer of Holly Cows
	 */
	private static final int Farmer = 13183;
	/*
	 * List of Summon Mobs
	 */
	private static final int Milk_Cow = 13187;
	private static final int Head_Milk_Cow = 13188;
	private static final int Gloom_Milk_Cow = 13191;
	private static final int Gloom_Head_Milk_Cow = 13192;
	/*
	 * List of Monsters
	 */
	private static final int Bulk = 13189;
	private static final int Head_Bulk = 13190;
	/*
	 * Configuration for Despawn
	 * 
	 */
	private static final int DespawnDelay = 15000;
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (st.getState() != State.STARTED)
		{
			st.setState(State.STARTED);
		}
		String htmltext = "";
		if (npc.getNpcId() == Farmer)
		{
			if(EventsConfig.HC_STARTED)
			{
				htmltext = "farmer.htm";
			}
			else
			{
				htmltext = EventsConfig.EVENT_DISABLED;
			}
		}
		else
		{
			htmltext = "cows.htm";
		}
		return htmltext;
	}
	/*
	 * Feeding and Milking
	 * @author: L2Rambase Project
	 */
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		/*
		 * String builder for text
		 */
		String htmltext = "";		
		int chance = st.getInt("chance");
		/*
		 * Script for check is mob spawned
		 */
		if (npc.getNpcId() == Farmer)
		{
			if (event.equalsIgnoreCase("getscroll"))
			{
				if (st.getQuestItemsCount(EventsConfig.HC_ADENA) > 45000)
				{
					st.takeItems(EventsConfig.HC_ADENA, 45000);
					st.giveItems(EventsConfig.HC_MILK_COW_SCROLL, 1);
					htmltext ="farmer.htm";
					player.sendMessage("Farmer: I give you a scroll for summon cow for 2 minutes.");
				}
				else
				{
					htmltext ="farmer.htm";
					player.sendMessage("Farmer: I don't give you anything if you don't have a money. Give me 45k Adena.");
				}
			}				
		}
		else
		{
			if (!EventsConfig.HC_STARTED)
			{
				return "<html>Myam ... fresh air myam.</html>";
			}
			else
			{
				//	Milk Cow - finished
				if (npc.getNpcId() == Milk_Cow)
				{				
					if (event.equalsIgnoreCase("feed"))
					{			
						if (chance >= 0 && chance < 6)
						{
							switch(chance)
							{
								case 1:
									st.set("chance","2");
									break;
								case 2:
									st.set("chance","3");
									break;
								case 3:
									st.set("chance","4");
									break;
								case 4:
									st.set("chance","5");
									break;
								case 5:
									st.set("chance","6");
									break;
								default:
									st.set("chance","1");
									break;
							}						
							if (Rnd.get(1,10) > 8)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Bulk, DespawnDelay);
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
						else
						{
							if (Rnd.get(1,10) > 5)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
					}
					if (event.equalsIgnoreCase("milking"))
					{											
						if (chance < 6)
						{					
							if (Rnd.get(1,4) > 3)
							{
								player.sendMessage("Ohhh this is nice.");
								st.giveItems(EventsConfig.HC_MILK, 1);
								htmltext = "cows.htm";
							}
							else
							{
								switch(chance)
								{
									case 1:
										st.set("chance","2");									
										break;
									case 2:
										st.set("chance","3");									
										break;
									case 3:
										st.set("chance","4");									
										break;
									case 4:
										st.set("chance","5");									
										break;
									case 5:
										st.set("chance","6");									
										break;
									default:
										st.set("chance","1");									
										break;
								}
								player.sendMessage("Do this more ...");	
								htmltext = "cows.htm";
							}
						}	
						else
						{
							st.set("chance","0");
							player.sendMessage("This hurt me ... help");
							htmltext = "";
							npc.deleteMe();
							st.addSpawn(Head_Bulk, DespawnDelay);							
						}
					}	
				}
				//	Head Milk Cow
				if (npc.getNpcId() == Head_Milk_Cow)
				{
					if (event.equalsIgnoreCase("feed"))
					{			
						if (chance >= 0 && chance < 6)
						{
							switch(chance)
							{
								case 1:
									st.set("chance","2");
									break;
								case 2:
									st.set("chance","3");
									break;
								case 3:
									st.set("chance","4");
									break;
								case 4:
									st.set("chance","5");
									break;
								case 5:
									st.set("chance","6");
									break;
								default:
									st.set("chance","1");
									break;
							}						
							if (Rnd.get(1,10) > 8)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);								
							}
							else
							{
								htmltext = "cows.htm";
								player.sendMessage("Myam ... myam ...");
							}
						}
						else
						{
							if (Rnd.get(1,10) > 5)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
					}
					if (event.equalsIgnoreCase("milking"))
					{						
						if (chance < 6)
						{					
							if (Rnd.get(1,4) > 3)
							{							
								player.sendMessage("Ohhh this is nice.");
								st.giveItems(EventsConfig.HC_MILK, 1);
								htmltext = "cows.htm";
							}
							else
							{
								switch(chance)
								{
									case 1:
										st.set("chance","2");
										break;
									case 2:
										st.set("chance","3");
										break;
									case 3:
										st.set("chance","4");
										break;
									case 4:
										st.set("chance","5");
										break;
									case 5:
										st.set("chance","6");
										break;
									default:
										st.set("chance","1");
										break;
								}
								player.sendMessage("Do this more ...");
								htmltext = "cows.htm";
							}
						}	
						else
						{
							st.set("chance","0");
							player.sendMessage("This hurt me ... help");
							htmltext = "";
							npc.deleteMe();
							st.addSpawn(Head_Bulk, DespawnDelay);
						}
					}	
				}
				//	Gloom Milk Cow - finished
				if (npc.getNpcId() == Gloom_Milk_Cow)
				{
					if (event.equalsIgnoreCase("feed"))
					{			
						if (chance >= 0 && chance < 6)
						{
							switch(chance)
							{
								case 1:
									st.set("chance","2");
									break;
								case 2:
									st.set("chance","3");
									break;
								case 3:
									st.set("chance","4");
									break;
								case 4:
									st.set("chance","5");
									break;
								case 5:
									st.set("chance","6");
									break;
								default:
									st.set("chance","1");
									break;
							}						
							if (Rnd.get(1,10) > 9)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Bulk, DespawnDelay);							
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
						else
						{						
							if (Rnd.get(1,10) > 6)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}	
					}
					if (event.equalsIgnoreCase("milking"))
					{					
						if (chance < 6)
						{					
							if (Rnd.get(1,4) > 3)
							{
								player.sendMessage("Ohhh this is nice.");
								st.giveItems(EventsConfig.HC_MILK, 1);
								htmltext = "cows.htm";
							}
							else
							{
								switch(chance)
								{
									case 1:
										st.set("chance","2");
										break;
									case 2:
										st.set("chance","3");
										break;
									case 3:
										st.set("chance","4");
										break;
									case 4:
										st.set("chance","5");
										break;
									case 5:
										st.set("chance","6");
										break;
									default:
										st.set("chance","1");
										break;
								}
								player.sendMessage("Do this more ...");
								htmltext = "cows.htm";
							}
						}	
						else
						{
							st.set("chance","0");
							player.sendMessage("This hurt me ... help");
							htmltext = "";
							npc.deleteMe();
							st.addSpawn(Head_Bulk, DespawnDelay);
						}
					}	
				}
				//	Gloom Head Milk Cow
				if (npc.getNpcId() == Gloom_Head_Milk_Cow)
				{
					if (event.equalsIgnoreCase("feed"))
					{			
						if (chance >= 0 && chance < 6)
						{
							switch(chance)
							{
								case 1:
									st.set("chance","2");
									break;
								case 2:
									st.set("chance","3");
									break;
								case 3:
									st.set("chance","4");
									break;
								case 4:
									st.set("chance","5");
									break;
								case 5:
									st.set("chance","6");
									break;
								default:
									st.set("chance","1");
									break;
							}							
							if (Rnd.get(1,10) > 9)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
						else
						{						
							if (Rnd.get(1,10) > 6)
							{
								st.set("chance","0");
								player.sendMessage("I eat too much .... prepare for fight");
								htmltext = "";
								npc.deleteMe();
								st.addSpawn(Head_Bulk, DespawnDelay);								
							}
							else
							{
								player.sendMessage("Myam ... myam ...");
								htmltext = "cows.htm";
							}
						}
					}
					if (event.equalsIgnoreCase("milking"))
					{					
						if (chance < 6)
						{
							if (Rnd.get(1,4) > 3)
							{							
								player.sendMessage("Ohhh this is nice.");
								st.giveItems(EventsConfig.HC_MILK, 1);
								htmltext = "cows.htm";
							}
							else
							{
								switch(chance)
								{
									case 1:
										st.set("chance","2");
										break;
									case 2:
										st.set("chance","3");
										break;
									case 3:
										st.set("chance","4");
										break;
									case 4:
										st.set("chance","5");
										break;
									case 5:
										st.set("chance","6");
										break;
									default:
										st.set("chance","1");
										break;
								}
								player.sendMessage("Do this more ...");
								htmltext = "cows.htm";
							}
						}	
						else
						{
							st.set("chance","0");
							player.sendMessage("This hurt me ... help");
							htmltext = "";
							npc.deleteMe();
							st.addSpawn(Head_Bulk, DespawnDelay);
						}
					}	
				}
			}
		}
      	return htmltext;
	}
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{		
		QuestState st = player.getQuestState(qn);
		if (st.getState() != State.STARTED)
		{
			return super.onKill(npc, player, isPet);
		}		
		if (!EventsConfig.HC_STARTED)
		{
			// nothing to do ...
		}
		else
		{
			//Bulk		
			if (npc.getNpcId() == Bulk)
			{
				int random_prize = Rnd.get(1,10);
				switch (random_prize)
				{
					case 1:					
					case 2:
						st.giveItems(EventsConfig.HC_ADENA, Rnd.get(1500,5000));
						break;
					case 3:					
					case 4:					
					case 5:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_MILK_COW_SCROLL, 1);
						break;
					case 6:
					case 7:
					case 8:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_HEAD_MILK_COW_SCROLL, 1);
						break;
					case 9:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_GLOOM_MILK_COW_SCROLL, 1);
						break;
					case 10:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_GLOOM_HEAD_MILK_COW_SCROLL, 1);
						break;
					default:
						break;
				}
				npc.deleteMe();
			}
			//Head Bulk
			if (npc.getNpcId() == Head_Bulk)
			{
				int random_prize = Rnd.get(1,10);
				switch (random_prize)
				{
					case 1:					
					case 2:
						st.giveItems(EventsConfig.HC_ADENA, Rnd.get(4500,15000));
						break;
					case 3:					
					case 4:					
					case 5:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_MILK_COW_SCROLL, 2);
						break;
					case 6:
					case 7:
					case 8:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_HEAD_MILK_COW_SCROLL, 2);
						break;
					case 9:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_GLOOM_MILK_COW_SCROLL, 1);
						break;
					case 10:
						if (Rnd.get(10)>5)
							st.giveItems(EventsConfig.HC_GLOOM_HEAD_MILK_COW_SCROLL, 1);
						break;
					default:
						break;
				}
				npc.deleteMe();
			}
		}
		return super.onKill(npc, player, isPet);
	}
	
	public HollyCow(int questId, String name, String descr)
	{
		super(questId, name, descr);
		/*
		 *  Farmer Npc
		 */
		addStartNpc(Farmer);
		addFirstTalkId(Farmer);
		addTalkId(Farmer);
		/*
		 *  Npc's for eat
		 */
		addStartNpc(Milk_Cow);
		addFirstTalkId(Milk_Cow);
		addTalkId(Milk_Cow);
		addStartNpc(Head_Milk_Cow);
		addFirstTalkId(Head_Milk_Cow);
		addTalkId(Head_Milk_Cow);
		addStartNpc(Gloom_Milk_Cow);
		addFirstTalkId(Gloom_Milk_Cow);
		addTalkId(Gloom_Milk_Cow);
		addStartNpc(Gloom_Head_Milk_Cow);
		addFirstTalkId(Gloom_Head_Milk_Cow);
		addTalkId(Gloom_Head_Milk_Cow);
		/*
		 * Mob kill
		 */
		addKillId(Bulk);
		addKillId(Head_Bulk);		
	}	
	public static void main(String[] args)
	{
		new HollyCow(-1,qn,"events");
		if (EventsConfig.HC_STARTED)
			_log.warning("Event System: Holly Cow Event loaded ...");
	}
}