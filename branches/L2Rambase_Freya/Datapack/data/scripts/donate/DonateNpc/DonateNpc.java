package donate.DonateNpc;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

import donate.DonateConfig;

public class DonateNpc extends Quest
{
	private static final String qn = "DonateNpc";

	private static final int Donate = 31213;

	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
       	if(!DonateConfig.DONATE_NPC_STARTED)
       	{
       		htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">Zone in the process of programming.</font></body></html>";
       	}
       	else
       	{
       		if (event.equalsIgnoreCase("items"))
       		{
       			if (DonateConfig.DONATE_ITEMS)
       			{
       				htmltext = "items.htm";
       			}
       			else
       			{
       				htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">This donate option is disabled.</font></body></html>";
       			}
       		}
       		if (event.equalsIgnoreCase("option"))
       		{
       			htmltext = "<html><body><center><img src=\"L2UI_CH3.onscrmsg_pattern01_1\" width=300 height=32 align=left><br>";
       			htmltext +="<font color=LEVEL><font color=15AB33>DONATE SHOP </font><br1> Please select prize:</font><br></center><center>";
       			htmltext +="<table>";
       			htmltext +="<tr>";
       			htmltext +="<td>";
       			if (DonateConfig.GLOBALGK_STARTED)
       			{
       				htmltext +="<button value=\"GlobalGk Mark ["+String.valueOf(DonateConfig.GK_PAY)+"]\" action=\"bypass -h Quest DonateNpc GkMark\" width=135 height=25 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br>";
       			}
       			if (DonateConfig.AIOBUFFER_STARTED)
       			{
       				htmltext += "<button value=\"GlobalAio Mark ["+String.valueOf(DonateConfig.AIO_PAY)+"]\" action=\"bypass -h Quest DonateNpc AioMark\" width=135 height=25 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br>";
       			}
       			if (DonateConfig.MAMMON_NPC_STARTED)
       			{
       				htmltext += "<button value=\"Merchant Mark ["+String.valueOf(DonateConfig.MERCHANT_PAY)+"]\" action=\"bypass -h Quest DonateNpc MerchMark\" width=135 height=25 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">";
       			}
       			htmltext += "</td></tr></table><br>";
       			htmltext += "<img src=\"L2UI_CH3.onscrmsg_pattern01_2\" width=300 height=32 align=left></center></body></html>";
       			
       			if (!DonateConfig.ABONAMENT_OPTIONS)
       			{
       				htmltext = "This donate option is disabled";
       			}       			
       		}
       		if (event.equalsIgnoreCase("gkmark"))
       		{
       			if (DonateConfig.GLOBALGK_STARTED)
       			{
       				if (st.getQuestItemsCount(DonateConfig.GK_MARK) == 0 && st.getQuestItemsCount(DonateConfig.DONATOR_COIN)<DonateConfig.GK_PAY && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You dont have coins for Special GlobalGk Mark.</font></body></html>"; 
       				}
       				else if (st.getQuestItemsCount(DonateConfig.GK_MARK) == 0 && st.getQuestItemsCount(DonateConfig.DONATOR_COIN) >= DonateConfig.GK_PAY && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{	
       					st.takeItems(DonateConfig.DONATOR_COIN,DonateConfig.GK_PAY); 
       					st.giveItems(DonateConfig.GK_MARK,1);
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have GlobalGk mark - please use command .globalgk</font></body></html>";
       				}
       				else if (st.getQuestItemsCount(DonateConfig.GK_MARK) == 1 && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have GlobalGk mark - please use command .globalgk</font></body></html>";
       				}
       				else if (st.getQuestItemsCount(DonateConfig.GK_MARK) == 0 && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 1)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have event free GK mark. You cannot donate my option - with event mark use command .globalgk</font></body></html>";
       				}
       			}
       			else
       			{
       				htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">This option is disabled.</font></body></html>";
       			}
       		}
       		if (event.equalsIgnoreCase("aiomark"))
       		{
       			if (DonateConfig.AIOBUFFER_STARTED)
       			{
       				if (st.getQuestItemsCount(DonateConfig.AIO_MARK) == 0 && st.getQuestItemsCount(DonateConfig.DONATOR_COIN)<DonateConfig.AIO_PAY && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You dont have coins for Special GlobalAio Mark.</font></body></html>"; 
       				}
       				else if (st.getQuestItemsCount(DonateConfig.AIO_MARK) == 0 && st.getQuestItemsCount(DonateConfig.DONATOR_COIN) >= DonateConfig.AIO_PAY && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{	
       					st.takeItems(DonateConfig.DONATOR_COIN,DonateConfig.AIO_PAY); 
       					st.giveItems(DonateConfig.AIO_MARK,1);
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have GlobalAio mark. </font><br><br>Use now command .aiobuff";
       					htmltext +="</body></html>";
       				}
       				else if (st.getQuestItemsCount(DonateConfig.AIO_MARK) == 1 && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 0)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have GlobalAio mark. </font><br><br>Use now command .aiobuff";
       					htmltext +="</body></html>";
       				}
       				else if (st.getQuestItemsCount(DonateConfig.AIO_MARK) == 0 && st.getQuestItemsCount(DonateConfig.BUFF_MARK2) == 1)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You cannot use my option because you have Event mark. </font><br><br>Use now command .aiobuff";
       					htmltext +="</body></html>";
       				}
       			}
       			else
       			{
       				htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">This option is disabled.</font></body></html>";
       			}
       		}
       		if (event.equalsIgnoreCase("merchmark"))
       		{
       			if (DonateConfig.MAMMON_NPC_STARTED)
       			{
       				if (st.getQuestItemsCount(DonateConfig.MERCH_MARK) == 1)
       				{
       					htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have Merchant mark. </font><br><br>Use now command .merchant";
       					htmltext +="</body></html>";
       				}
       				else
       				{
       					if (st.getQuestItemsCount(DonateConfig.DONATOR_COIN) >= DonateConfig.MERCHANT_PAY)
       					{
       						st.takeItems(DonateConfig.DONATOR_COIN, DonateConfig.MERCHANT_PAY);
       						st.giveItems(DonateConfig.MERCH_MARK, 1);
       						htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">You have Merchant mark. </font><br><br>Use now command .merchant";
           					htmltext +="</body></html>";
       					}
       					else
       					{
       						htmltext = "<html><body><font color=FF0000> You cannot have 10 donator coins for Merchant Mark</font></body></html>";
       					}
       				}      
       			}
       			else
       			{
       				htmltext = "<html><body>Donator Npc:<br><font color=\"FF0000\">This option is disabled.</font></body></html>";
       			}
       		}       		      		
       	}
       	return htmltext;
	}
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		String htmltext = "welcome.htm";
		return htmltext;
	}
	public DonateNpc(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(Donate);
		addFirstTalkId(Donate);
		addTalkId(Donate);

	}
	public static void main(String[] args)
	{
		new DonateNpc(-1,qn,"donate");
		_log.warning("Donate System: Special Npc for Coins Loaded");
	}
}