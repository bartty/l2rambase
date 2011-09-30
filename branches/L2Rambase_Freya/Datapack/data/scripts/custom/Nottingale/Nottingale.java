/**
 * @author L2Rambase Project
 */
package custom.Nottingale;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.network.serverpackets.RadarControl;

public class Nottingale extends Quest
{
	private static final String qn = "1002_Nottingale";

	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		player.setLastQuestNpcObject(npc.getObjectId());
		npc.showChatWindow(player);
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
		QuestState qs = player.getQuestState("10273_GoodDayToFly");
	    if (qs == null || (qs != null && qs.getState() != State.COMPLETED))
	    {
	    	player.sendPacket(new RadarControl(2,2,0,0,0));
	    	player.sendPacket(new RadarControl(0,2,-184545,243120,1581));
	    	htmltext = "<html><body>Mage Notingale:<br>";
	    	htmltext += "First of all, you must acquire either an Aurabird or an Airship in order to fight against the enemies who populate our skies.";
	    	htmltext += "Since the number of Airships is limited, I recommend you acquire an Aurabird.<br>";
	    	htmltext += "To do so, go meet Engineer Lekon and do what he tells you. Even if you decide to acquire an Airship, Lekon is worth talking to.<br>";
	    	htmltext += "Check your map and I will tell you his location.</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-1.htm"))
	    {
	    	htmltext = "<html><body>Mage Notingale:<br>";
	    	htmltext += "Once you have acquired transportaion, the next thing is to fight against those who are attacking us.<br>";
	    	htmltext += "Our chief enemies are the monsters of the skies and the Seeds. Since you cannot possible handle either of these by yourself, you should start by gathering a party of your fellows.<br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-2.htm\">Ask about Aerial Raid Monsters.</a><br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-7.htm\">Ask about major hunting grounds.</a>";
	    	htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-2.htm"))
	    {
	    	htmltext = "<html><body>Mage Notingale:<br>";
	    	htmltext += "The following enemies have attacked us from the skies nearby:<br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-3.htm\">Valdstone the Master Rider</a><br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-4.htm\">Rok the King of Birds</a><br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-5.htm\">Enira the Banshee Queen</a><br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-6.htm\">Dius the Messenger of Destruction</a>";
	    	htmltext += "</body></html>";
	    }	    
	    else if (event.equalsIgnoreCase("32627-3.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-192361,254528,3598));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "Valdstone the Master Rider used to be a lord of this land. He gained the power to control wild Aurabirds in exchange for yielding his territory to the Seed of Infinity.<br>";
	       htmltext += "His Elite Riders always watch our fortress. Look at the map to see where he and his minions most frequently appear.";
	       htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-4.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-174600,219711,4424));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "Rok, the King of Birds, used to be the largest and most intelligent bird in Gracia. But since the Seed of Destruction and Storm Dragon Lindvior appeared, he has been in hiding.<br>";
	       htmltext += "As the power of the Seed of Destruction has waned, however, he appears to be regaining his former control over birds. When the power of the Seed of Destruction is weak,";
	       htmltext += "you may see Rok if you attack birds nearby. I will mark the areas on the map where he most often appears.";
	       htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-5.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-181989,208968,4424));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "It is said that Enira the Banshee Queen is a subordinate of Ekimus, the ruler of the Seed of Infinity. But I believe differently. It seems as though she is keeping an eye on the Seed of Infinity.<br>";
	       htmltext += "Of course, I'm not saying Enira is on our side. She is still very much a threat. If we hope to travel the skies of Gracia unmolested, she must be defeated. I have marked the areas where she frequently appears on your map.";
	       htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-6.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-252898,235845,5343));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "Dius the Messenger of Destruction is a huge Drake of the Sky. He scours the skies for Tiat, the lord of the Seed of Destruction.<br>";
	       htmltext += "Fortunately, he is normally scarce. If you apply a special <font color=LEVEL>bait for Drakes</font> to <font color=LEVEL>Baby Drake Wings</font>, however, you can lure him out. I will mark the area where he appears on your map.";
	       htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-7.htm"))
	    {
	    	htmltext = "<html><body>Mage Notigale:<br>";
	    	htmltext += "The following Seeds are the main sites of enemy power nearby:<br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-8.htm\">Seed of Infinity</a><br>";
	    	htmltext += "<a action=\"bypass -h Quest 1002_Nottingale 32627-9.htm\">Seed of Destruction</a>";
	    	htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-8.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-212819,209813,4288));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "I will mark the location of the Seed of Infinity on your map.<br>";
	       htmltext += "It is controlled by Ekimus, the lord of Eternity. His ability to control both living and dead has transformed the whole Seed of Infinity into a living organism...<br>";
	       htmltext += "General Dilios can give you more details.";
	       htmltext += "</body></html>";
	    }
	    else if (event.equalsIgnoreCase("32627-9.htm"))
	    {
	       player.sendPacket(new RadarControl(2,2,0,0,0));
	       player.sendPacket(new RadarControl(0,2,-246899,251918,4352));
	       htmltext = "<html><body>Mage Notingale:<br>";
	       htmltext += "I will mark the location of the Seed of Destruction on your map.<br>";
	       htmltext += "It is controlled by Tiat, the lord of the Mounted Troop. The Seed is like a one huge fortress, thwarting any attempts to enter. <br>";
	       htmltext += "General Dilios can give you more details.";
	       htmltext += "</body></html>";
	    }
       	return htmltext;
	}
	
	public Nottingale(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(32627);
		addFirstTalkId(32627);
		addTalkId(32627);
	}
	
	public static void main(String[] args)
	{
		new Nottingale(-1,qn,"custom");		
	}
}