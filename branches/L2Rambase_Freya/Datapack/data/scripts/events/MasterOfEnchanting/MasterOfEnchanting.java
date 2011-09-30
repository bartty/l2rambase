package events.MasterOfEnchanting;

import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.Rnd;

import events.EventsConfig;

public class MasterOfEnchanting extends Quest
{
	private static final int _master_yogi = 32599;
	private static final String qn = "MasterOfEnchanting";

	public MasterOfEnchanting(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(_master_yogi);
		addFirstTalkId(_master_yogi);
		addTalkId(_master_yogi);		
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		Quest q = QuestManager.getInstance().getQuest(getName());

		htmltext = event;
		if (event.equalsIgnoreCase("buy_staff"))
		{
			if (st.getQuestItemsCount(EventsConfig.MY_STAFF) == 0 && st.getQuestItemsCount(EventsConfig.MY_ADENA) > EventsConfig.MY_STAFF_PRICE)
			{
				st.takeItems(EventsConfig.MY_ADENA, EventsConfig.MY_STAFF_PRICE);
				st.giveItems(EventsConfig.MY_STAFF, 1);
				htmltext = "32599-staffbuyed.htm";
			}
			else
				htmltext = "32599-staffcant.htm";
		}
		else if (event.equalsIgnoreCase("buy_scroll_24"))
		{
			long _curr_time = System.currentTimeMillis();
			String value = q.loadGlobalQuestVar(player.getAccountName());
			long _reuse_time = value == "" ? 0 : Long.parseLong(value);
			if (_curr_time > _reuse_time)
			{
				if (st.getQuestItemsCount(EventsConfig.MY_ADENA) > EventsConfig.MY_SCROLL_24_PRICE)
				{
					st.takeItems(EventsConfig.MY_ADENA, EventsConfig.MY_SCROLL_24_PRICE);
					st.giveItems(EventsConfig.MY_SCROLL, 24);
					q.saveGlobalQuestVar(player.getAccountName(), Long.toString(System.currentTimeMillis() + (EventsConfig.MY_SCROLL_24_TIME * 3600000)));
					htmltext = "32599-scroll24.htm";
				}
				else
					htmltext = "32599-s24-no.htm";
			}
			else
			{
				long _remaining_time = (_reuse_time - _curr_time) / 1000;
				int hours = (int) _remaining_time / 3600;
				int minutes = ((int) _remaining_time % 3600) / 60;
				if (hours > 0)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.ITEM_PURCHASABLE_IN_S1_HOURS_S2_MINUTES);
					sm.addNumber(hours);
					sm.addNumber(minutes);
					player.sendPacket(sm);
					htmltext = "32599-scroll24.htm";
				}
				else if (minutes > 0)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.ITEM_PURCHASABLE_IN_S1_MINUTES);
					sm.addNumber(minutes);
					player.sendPacket(sm);
					htmltext = "32599-scroll24.htm";
				}
				else
				{
					// Little glitch. There is no SystemMessage with seconds only.
					// If time is less than 1 minute player can buy scrolls
					if (st.getQuestItemsCount(EventsConfig.MY_ADENA) > EventsConfig.MY_SCROLL_24_PRICE)
					{
						st.takeItems(EventsConfig.MY_ADENA, EventsConfig.MY_SCROLL_24_PRICE);
						st.giveItems(EventsConfig.MY_SCROLL, 24);
						q.saveGlobalQuestVar(player.getAccountName(), Long.toString(System.currentTimeMillis() + (EventsConfig.MY_SCROLL_24_TIME * 3600000)));
						htmltext = "32599-scroll24.htm";
					}
					else
						htmltext = "32599-s24-no.htm";
				}
			}
		}
		else if (event.equalsIgnoreCase("buy_scroll_1"))
		{
			if (st.getQuestItemsCount(EventsConfig.MY_ADENA) > EventsConfig.MY_SCROLL_1_PRICE)
			{
				st.takeItems(EventsConfig.MY_ADENA, EventsConfig.MY_SCROLL_1_PRICE);
				st.giveItems(EventsConfig.MY_SCROLL, 1);
				htmltext = "32599-scroll-ok.htm";				
			}
			else
				htmltext = "32599-s1-no.htm";
		}
		else if (event.equalsIgnoreCase("buy_scroll_10"))
		{
			if (st.getQuestItemsCount(EventsConfig.MY_ADENA) > EventsConfig.MY_SCROLL_10_PRICE)
			{
				st.takeItems(EventsConfig.MY_ADENA, EventsConfig.MY_SCROLL_10_PRICE);
				st.giveItems(EventsConfig.MY_SCROLL, 10);
				htmltext = "32599-scroll-ok.htm";				
			}
			else
				htmltext = "32599-s10-no.htm";
		}
		else if (event.equalsIgnoreCase("receive_reward"))
		{
			if (st.getItemEquipped(EventsConfig.MY_HAND_SLOT) == EventsConfig.MY_STAFF && st.getEnchantLevel(EventsConfig.MY_STAFF) > 3)
			{
				switch(st.getEnchantLevel(EventsConfig.MY_STAFF))
				{
					case 4:
						st.giveItems(6406, 1);	// Firework
						break;
					case 5:
						st.giveItems(6406, 2);	// Firework
						st.giveItems(6407, 1);	// Large Firework
						break;
					case 6:
						st.giveItems(6406, 3);	// Firework
						st.giveItems(6407, 2);	// Large Firework
						break;
					case 7:
						st.giveItems(EventsConfig.MY_HAT_SHADOW_REWARD[Rnd.get(3)], 1);
						break;
					case 8:
						st.giveItems(955, 1);	// Scroll: Enchant Weapon (D)
						break;
					case 9:
						st.giveItems(955, 1);	// Scroll: Enchant Weapon (D)
						st.giveItems(956, 1);	// Scroll: Enchant Armor (D)
						break;
					case 10:
						st.giveItems(951, 1);	// Scroll: Enchant Weapon (C)
						break;
					case 11:
						st.giveItems(951, 1);	// Scroll: Enchant Weapon (C)
						st.giveItems(952, 1);	// Scroll: Enchant Armor (C)
						break;
					case 12:
						st.giveItems(948, 1);	// Scroll: Enchant Armor (B)
						break;
					case 13:
						st.giveItems(729, 1);	// Scroll: Enchant Weapon (A)
						break;
					case 14:
						st.giveItems(EventsConfig.MY_HAT_EVENT_REWARD[Rnd.get(3)], 1);
						break;
					default:
						if(st.getEnchantLevel(EventsConfig.MY_STAFF) > 14)
							st.giveItems(8762, 1);	// Top-Grade Life Stone: level 76
						break;
				}
				st.takeItems(EventsConfig.MY_STAFF, 1);
				htmltext = "32599-rewardok.htm";
			}
			else
				htmltext = "32599-rewardnostaff.htm";
		}

		return htmltext;
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			Quest q = QuestManager.getInstance().getQuest(getName());
			st = q.newQuestState(player);
		}
		if (EventsConfig.MY_STARTED)
		{
			htmltext = npc.getNpcId() + ".htm";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new MasterOfEnchanting(-1, qn, "events");
	}
}