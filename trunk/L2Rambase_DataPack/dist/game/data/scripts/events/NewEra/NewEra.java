/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package events.NewEra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import events.EventsConfig;

import l2.Rambase.gameserver.Announcements;
import l2.Rambase.gameserver.datatables.EventDroplist;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.gameserver.script.DateRange;
import l2.Rambase.util.Rnd;


public class NewEra extends Quest
{
	private static final String EVENT_DATE = "01 09 2011-30 09 2011";	//match the date in events.sql
	private static final DateRange EVENT_DATES = DateRange.parse(EVENT_DATE, new SimpleDateFormat("dd MM yyyy", Locale.US));
	private static final String[] EVENT_ANNOUNCE = {"New Era Event is currently active."};
	private static final Date EndDate = EVENT_DATES.getEndDate();
	private static final Date currentDate = new Date();
	
	// Items
	private final static int letterL = 3882;
	private final static int letterI = 3881;
	private final static int letterN = 3883;
	private final static int letterE = 3877;
	private final static int letterA = 3875;
	private final static int letterG = 3879;
	private final static int letterII = 3888;
	private final static int letterT = 3887;
	private final static int letterH = 3880;
	private final static int letterR = 3885;
	private final static int letterO = 3884;
	private final static int[] dropList = { letterL, letterI, letterN, letterE, letterA, letterG, letterII, letterT, letterH, letterR, letterO, letterN };
	private int[] dropCount = {1,1};
	private final static int dropChance = 25000;	// actually 2.5%

	private final static int[] EventSpawnX = { 147698,147443,81921,82754,15064,111067,-12965,87362,-81037,117412,43983,-45907,12153,-84458,114750,-45656,-117195 };
	private final static int[] EventSpawnY = { -56025,26942,148638,53573,143254,218933,122914,-143166,150092,76642,-47758,49387,16753,244761,-178692,-113119,46837 };
	private final static int[] EventSpawnZ = { -2775,-2205,-3473,-1496,-2668,-3543,-3117,-1293,-3044,-2695,-797,-3060,-4584,-3730,-820,-240,367 };

	private final static int EventNPC = 31854;
	
	private static List<L2Npc> eventManagers = new ArrayList<L2Npc>();

	private static boolean NewEraEvent = false;
	
	public NewEra(int questId, String name, String descr)
	{
		super(questId, name, descr);

        EventDroplist.getInstance().addGlobalDrop(dropList, dropCount, dropChance, EVENT_DATES);
        
        Announcements.getInstance().addEventAnnouncement(EVENT_DATES,EVENT_ANNOUNCE); 

        addStartNpc(EventNPC);
		addFirstTalkId(EventNPC);
		addTalkId(EventNPC);
		startQuestTimer("EventCheck",1800000,null,null);
		
		if (EVENT_DATES.isWithinRange(currentDate))
			NewEraEvent = true;

		if (NewEraEvent)
		{
			_log.info("New Era Event - ON");

			for (int i = 0; i < EventSpawnX.length; i++)
			{
				L2Npc eventManager = addSpawn(EventNPC,EventSpawnX[i],EventSpawnY[i],EventSpawnZ[i],0,false,0);
				eventManagers.add(eventManager);
			}
		}
		else
		{
			_log.info("New Era Event - OFF");

			Calendar endWeek = Calendar.getInstance();
			endWeek.setTime(EndDate);
			endWeek.add(Calendar.DATE, 7);
			
			if (EndDate.before(currentDate) && endWeek.getTime().after(currentDate))
			{
				for (int i = 0; i < EventSpawnX.length; i++)
				{
					L2Npc eventManager = addSpawn(EventNPC,EventSpawnX[i],EventSpawnY[i],EventSpawnZ[i],0,false,0);
					eventManagers.add(eventManager);
				}
			}
		}
	}
	
	@Override
	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
	    QuestState st = player.getQuestState(getName());
	    int prize, l2day;

		if (npc == null)
		{
			if (event.equalsIgnoreCase("EventCheck"))
			{
				startQuestTimer("EventCheck",1800000,null,null);
				boolean Event1 = false;
				
				if (EVENT_DATES.isWithinRange(currentDate))
					Event1 = true;

				if (!NewEraEvent && Event1)
				{
					NewEraEvent = true;
					_log.info("New Era Event - ON");
					Announcements.getInstance().announceToAll("New Era Event is currently active. See the Event NPCs to participate!");

					for (int i = 0; i < EventSpawnX.length; i++)
					{
						L2Npc eventManager = addSpawn(EventNPC,EventSpawnX[i],EventSpawnY[i],EventSpawnZ[i],0,false,0);
						eventManagers.add(eventManager);
					}
				}
				else if (NewEraEvent && !Event1)
				{
					NewEraEvent = false;
					_log.info("New Era Event - OFF");
					for (L2Npc eventManager : eventManagers)
					{
						eventManager.deleteMe();
					}
				}
			}
		}
		else if (event.equalsIgnoreCase("LINEAGEII"))
		{
			if (st.getQuestItemsCount(letterL) >= 1
				&& st.getQuestItemsCount(letterI) >= 1
				&& st.getQuestItemsCount(letterN) >= 1
				&& st.getQuestItemsCount(letterE) >= 2
				&& st.getQuestItemsCount(letterA) >= 1
				&& st.getQuestItemsCount(letterG) >= 1
				&& st.getQuestItemsCount(letterII) >= 1)
			{
				st.takeItems(letterL,1);
	            st.takeItems(letterI,1);
	            st.takeItems(letterN,1);
	            st.takeItems(letterE,2);
	            st.takeItems(letterA,1);
	            st.takeItems(letterG,1);
	            st.takeItems(letterII,1);
	            
	            prize = Rnd.get(1000);
	            l2day = Rnd.get(10);
	            
	            if (prize <= 5)
	                st.giveItems(6660,1); // 1 - Ring of Ant Queen
	            else if (prize <= 10)
	                st.giveItems(6662,1); // 1 - Ring of Core
	            else if (prize <= 25)
	                st.giveItems(8949,1); // 1 - Fairy Antennae
	            else if (prize <= 50)
	                st.giveItems(8950,1); // 1 - Feathered Hat
	            else if (prize <= 75)
	                st.giveItems(8947,1); // 1 - Rabbit Ears
	            else if (prize <= 100)
	                st.giveItems(729,1); // 1 - Scroll Enchant Weapon A Grade
	            else if (prize <= 200)
	                st.giveItems(947,2); // 2 - Scroll Enchant Weapon B Grade
	            else if (prize <= 300)
	                st.giveItems(951,3); // 3 - Scroll Enchant Weapon C Grade
	            else if (prize <= 400)
	            	st.giveItems(3936,1); // 1 - Blessed Scroll of Resurrection
	            else if (prize <= 500)
	            	st.giveItems(1538,1); // 1 - Blessed Scroll of Escape
	            else
	            {
	            	// 3 - Random L2 Day Buff Scrolls 2 of the same type
	            	switch (l2day)
	            	{
	            		case 1:
	            			st.giveItems(3926,3);
	            			break;
	            		case 2:
	            			st.giveItems(3927,3);
	            			break;
	            		case 3:
	            			st.giveItems(3928,3);
	            			break;
	            		case 4:
	            			st.giveItems(3929,3);
	            			break;
	            		case 5:
	            			st.giveItems(3930,3);
	            			break;
	            		case 6:
	            			st.giveItems(3931,3);
	            			break;
	            		case 7:
	            			st.giveItems(3932,3);
	            			break;
	            		case 8:
	            			st.giveItems(3933,3);
	            			break;
	            		case 9:
	            			st.giveItems(3934,3);
	            			break;
	            		default:
	            			st.giveItems(3935,3);
	            			break;
	            	}
	            }
			}
			else
				htmltext = "31854-03.htm";
		}
		else if (event.equalsIgnoreCase("THRONE"))
		{
	        if (st.getQuestItemsCount(letterT) >= 1
	        	&& st.getQuestItemsCount(letterH) >= 1
	        	&& st.getQuestItemsCount(letterR) >= 1
	        	&& st.getQuestItemsCount(letterO) >= 1
	        	&& st.getQuestItemsCount(letterN) >= 1
	        	&& st.getQuestItemsCount(letterE) >= 1)
	        {
	            st.takeItems(letterT,1);
	            st.takeItems(letterH,1);
	            st.takeItems(letterR,1);
	            st.takeItems(letterO,1);
	            st.takeItems(letterN,1);
	            st.takeItems(letterE,1);
	            
	            prize = Rnd.get(1000);
	            l2day = Rnd.get(10);
	            
	            if (prize <= 5)
	                st.giveItems(6660,1); // 1 - Ring of Ant Queen
	            else if (prize <= 10)
	                st.giveItems(6662,1); // 1 - Ring of Core
	            else if (prize <= 25)
	                st.giveItems(8951,1); // 1 - Artisans Goggles
	            else if (prize <= 50)
	                st.giveItems(8948,1); // 1 - Little Angel Wings
	            else if (prize <= 75)
	                st.giveItems(947,2); // 2 - Scroll Enchant Weapon B Grade
	            else if (prize <= 100)
	                st.giveItems(951,3); // 3 - Scroll Enchant Weapon C Grade
	            else if (prize <= 150)
	                st.giveItems(955,4); // 4 - Scroll Enchant Weapon D Grade
	            else if (prize <= 200)
	                st.giveItems(3936,1); // 1 - Blessed Scroll of Resurrection
	            else if (prize <= 300)
	                st.giveItems(1538,1); // 1 - Blessed Scroll of Escape
	            else
	            {
	            	// 2 - Random L2 Day Buff Scrolls 2 of the same type
	            	switch (l2day)
	            	{
	            		case 1:
	            			st.giveItems(3926,2);
	            			break;
	            		case 2:
	            			st.giveItems(3927,2);
	            			break;
	            		case 3:
	            			st.giveItems(3928,2);
	            			break;
	            		case 4:
	            			st.giveItems(3929,2);
	            			break;
	            		case 5:
	            			st.giveItems(3930,2);
	            			break;
	            		case 6:
	            			st.giveItems(3931,2);
	            			break;
	            		case 7:
	            			st.giveItems(3932,2);
	            			break;
	            		case 8:
	            			st.giveItems(3933,2);
	            			break;
	            		case 9:
	            			st.giveItems(3934,2);
	            			break;
	            		default:
	            			st.giveItems(3935,3);
	            			break;
	            	}
	            }
	        }
	        else
	        	htmltext =  "31854-03.htm";
		}
		else if (event.equalsIgnoreCase("chat0"))
			htmltext =  "31854.htm";
		else if (event.equalsIgnoreCase("chat1"))
			htmltext =  "31854-02.htm";
		
		return htmltext;
	}	

	@Override
	public String onFirstTalk(L2Npc npc,L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);

		return "31854.htm";
	}

	public static void main(String[] args)
	{
		new NewEra(-1, "NewEra", "events");
		if (EventsConfig.NE_STARTED)
			_log.info("Loaded Events: New Era.");
	}

}