package custom.KetraOrcSupport;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.WareHouseWithdrawalList;

public class KetraOrcSupport extends Quest
{
	private static final String qn = "KetraOrcSupport";
	
	private static final int Kadun = 31370; //Hierarch
	private static final int Wahkan= 31371; //#Messenger
	private static final int Asefa = 31372; //Soul Guide
	private static final int Atan  = 31373; //Grocer
	private static final int Jaff  = 31374; //Warehouse Keeper
	private static final int Jumara= 31375; //Trader
	private static final int Kurfa = 31376; //Gate Keeper
	private static final int[] NPCS = {
		Kadun,Wahkan,Asefa,Atan,Jaff,Jumara,Kurfa
	};
	
	private static final int Horn = 7186;
	
	private static final int[] BuffList = {
		1,2,3,4,5,6,7,8
	};
	private static final int[][] Buff = {
		{ 4359,1,2 }, //Focus: Requires 2 Buffalo Horns
		{ 4360,1,2 }, //Death Whisper: Requires 2 Buffalo Horns
		{ 4345,1,3 }, //Might: Requires 3 Buffalo Horns
		{ 4355,1,3 }, //Acumen: Requires 3 Buffalo Horns
		{ 4352,1,3 }, //Berserker: Requires 3 Buffalo Horns
		{ 4354,1,3 }, //Vampiric Rage: Requires 3 Buffalo Horns
		{ 4356,1,6 }, //Empower: Requires 6 Buffalo Horns
		{ 4357,1,6 }  //Haste: Requires 6 Buffalo Horns
	};

	public KetraOrcSupport(int id, String name, String descr)
	{
		super(id, name, descr);
		for ( int i :NPCS)
		{
			addFirstTalkId(i);
			addStartNpc(i);
		}
		addTalkId(Jumara);
		addTalkId(Asefa);
		addTalkId(Kurfa);
		addTalkId(Jaff);
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
		int npcId = npc.getNpcId();
	    int Alevel = player.getAllianceWithVarkaKetra();
	    long Horns = st.getQuestItemsCount(Horn);
	    if (npcId == Kadun)
	    {
	        if (Alevel > 0)
	        {
	            htmltext = "<html><body>Hierarch Kadun Zu Ketra:<br>"; 
	            htmltext += "We Ketras have come here to reclaim the land of our ancestors! We've discovered that the Varka Silenos, our loathsome enemy, defile this holy land! We will never forgive them this insult!<br>";
	            htmltext += "Help us return to our homeland! May the flame of Paagrio burn within your heart eternally!<br>";
	            htmltext += "But, be careful! You must remember our brothers are always watching you.  <font color=LEVEL>As soon as you lay a hand on our Ketra tribe, all the promises between you and us will disappear! </font><br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+npc.getObjectId()+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else 
	        {
	            htmltext = "<html><body>Hierarch Kadun Zu Ketra:<br>";
	            htmltext += "Hold it, stranger!  This is the sacred land of the Ketra! You defile it with your presence!<br>";
	            htmltext += "We're different from the vile Orcs to the north... We'll surely reclaim this land of our ancestors! If you seriously want to help us return to our homeland, visit Messenger Wahkan!";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Wahkan)
	    {
	        if (Alevel > 0)
	        {
	            htmltext = "<html><body>Ketra's Messenger Wahkan:<br>";
	            htmltext += "Welcome, brave comrade!<br>";
	            htmltext += "You've brought many heads of Varka Silenos! We're very impressed! A few more friends like you and we can put those Varkas down for good!<br>";
	            htmltext += "Tejakar Ketra!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Ketra's Messenger Wahkan:<br>";
	            htmltext += "We Ketra are the bravest of all Orc tribes! We're here to reclaim the land of our ancestors! Prepare to die, all who stand in our way!<br>";
	            htmltext += "Don't take us for fools! We're well aware of the fact that in order to defeat the enemy, we must ally with outside forces! Like you! Will you be our comrade in arms? Are you ready to prove your worth alongside us on the battlefield?<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Asefa)
	    {
	        if (Alevel < 1)
	        {
	            htmltext = "<html><body>Soul Guide Asefa:<br>";
	            htmltext += "You, nameless foreigner! Get away from here! Don't defile the land of our ancestors!<br>";
	            htmltext += "I'll bring down the curse of flames upon the enemies of Ketra!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel < 3 && Alevel > 0)
	        {
	            htmltext = "<html><body>Soul Guide Asefa:<br>";
	            htmltext += "Welcome, comrade of Ketra! Until the day comes when you win an honorable name on the battlefield, may the souls of our ancestors bless you and protect you!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel > 2)
	        {
	            if (Horns > 0)
	            {
	                htmltext = "<html><body>Soul Guide Asefa:<br>";
	                htmltext += "Welcome, sworn friend of Ketra! I pray that the Flame of Paagrio will shed its light on your soul!<br>";
	                htmltext += "The blood of the Varka Silenos you drew has been dedicated to the souls of our great ancestors in the midst of sacred flames in heaven! You'll receive the blessings of our ancestors!<br>";
	                htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 1\">Blessing of Wolf (Focus)</a> Req: 2 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 2\">Blessing of Hawk (Death Whisper)</a> Req: 2 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 3\">Blessing of Bear (Might)</a> Req: 3 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 4\">Blessing of Spider (Acumen)</a> Req: 3 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 5\">Blessing of Lion (Berserker)</a> Req: 3 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 6\">Blessing of Coyote (Vampiric Rage)</a> Req: 3 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 7\">Blessing of Owl (Empower)</a> Req:6 BH<br>";
					htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 8\">Blessing of Wild Horse (Haste)</a> Req: 6 BH<br>BH - Buffalo Horns<br>";
	                htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	                htmltext += "</body></html>";
	            }
	            else
	            {
	                htmltext = "<html><body>Soul Guide Asefa:<br>";
	                htmltext += "In order to receive the blessing of the ancestors, you must dedicate a<font color=LEVEL> Buffalo horn</font> to them! Go to the battleground and return victorious, you'll receive a Buffalo horn as a reward!<br>";
	                htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	                htmltext += "</body></html>";
	            }
	        }
	    }
	    else if (npcId == Atan)
	    {
	        if (player.getKarma() >= 1)
	        {
	            htmltext = "<html><body>Grocer Atan:<br>";
	            htmltext += "Oh ... you're a wanted criminal. My man may report you to the village guards if he sees you. Escape to another place at once. I cannot sell to you now, but I trust you will be a future customer. When you are free once more, please pay our shop a visit. Don't forget us.";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel <= 0)
	        {
	            htmltext = "<html><body>Grocer Atan:<br>";
	            htmltext += "I can't sell supplies to someone who is not a comrade of Ketra! Go away!";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == 1 || Alevel == 2)
	        {
	            htmltext = "<html><body>Grocer Atan:<br>";
	            htmltext += "Come on in, comrade of Ketra! I'm responsible for providing supplies to the honorable warriors of our tribe. Under special orders from the chief of our tribe, I can now offer supplies to outsiders like you!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Buy 383\">Buy</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Grocer Atan:<br>";
	            htmltext += "Dear comrade of Ketra! How come what you told me and what you now show me are two different things?<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Buy 383\">Buy</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_TerritoryStatus\">Who is the lord of the manor and what is the tax rate?</a>";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Jaff)
	    {
	        if (Alevel <= 0)
	        {
	            htmltext = "<html><body>Warehouse Keeper Jaff:<br>";
	            htmltext += "Go away! This warehouse is reserved for the glorious warriors of Ketra! It's not for outsiders!";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == 1)
	        {
	            htmltext = "<html><body>Warehouse Keeper Jaff:<br> ";
	            htmltext += "Yo, comrade of Ketra! Welcome! This warehouse is where our war supplies are stored!<br>"; 
	            htmltext += "I know you've worked hard, but I can't yet open the warehouse to a foreigner such as yourself! Keep up the good work! One day our chief will allow you to use the warehouse!<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>"; 
	            htmltext += "</body></html>";
	        }
	        else if (player.getWarehouse().getSize() == 0)
	        {
	            htmltext = "<html><body>Warehouse Keeper Jaff:<br> ";
	            htmltext += "I don't care about your reputation among outsiders! You treat us Ketra fairly, we'll do the same!<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>"; 
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport Withdraw\">Pick up an item.</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == 2 || Alevel == 3)
	        {
	            htmltext = "<html><body>Warehouse Keeper Jaff:<br>";
	            htmltext += "Have you found all you need? If there's anything else, just ask me!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport Withdraw\">Pick up an item.</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Warehouse Keeper Jaff:<br>";
	            htmltext += "Come here, friend of Ketra!<br>";
	            htmltext += "You've been given permission to use our warehouse by our chief! A valued warrior like you shouldn't have to carry around a heavy load! Leave anything here you don't need for combat!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport Withdraw\">Pick up an item.</a>";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Jumara)
	    {
	        if (Alevel == 2)
	        {
	           htmltext = "<html><body>Trader Jumara:<br>";
	           htmltext += "Tejakar Ketra! Welcome, comrade! I'm a supplier of specialty items! The elder tells me that you're interested in buying something!<br>";
	           htmltext += "How about this low-level booty pouch? Someday, when you're qualified to open it, you'll have the strongest armor available!<br>";
	           htmltext += "Bring me a<font color=LEVEL> Buffalo Horn</font> and I'll give you whatever you want!<br>";
	           htmltext += "What would you like?<br>";
	           htmltext += "<a action=\"bypass -h Quest KetraOrcSupport sel1\">I'd like one of the Ketra's specialty products.</a><br>";
	           htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	           htmltext += "</body></html>";
	        }
	        else if (Alevel == 3 || Alevel == 4)
	        {
	            htmltext = "<html><body>Trader Jumara:<br>";
	            htmltext += "Tejakar Ketra! Welcome, dear friend!<br>";
	            htmltext += "I've been ordered to supply a higher-level pouch to you, but you won't get the strongest armor yet! Try again when you've had more experiences.<br>";
	            htmltext += "Bring me a<font color=LEVEL> Buffalo Horn</font> and I'll give you whatever you want. Care to take a look?<br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport sel2\">I'd like one of the Ketra's specialty products.</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == 5)
	        {
	            htmltext = "<html><body>Trader Jumara:<br>";
	            htmltext += "Tejakar Ketra! Welcome, dear friend!<br>";
	            htmltext += "You're finally qualified to get the most valuable looted goods from our tribe!<br>";
	            htmltext += "Now, open the box that holds the strongest armor!<br>Bring me a<font color=LEVEL> Buffalo Horn</font> and I'll give you anything you want!<br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport sel3\">I'd like one of the Ketra's specialty products.</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Trader Jumara:<br>";
	            htmltext += "Tejakar Ketra! Welcome, dear friend!<br>";
	            htmltext += "You are not strong enough yet! Try again when you've had more experiences.";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Kurfa)
	    {
	        if (Alevel <= 0)
	        {
	            htmltext = "<html><body>Gatekeeper Kurfa:<br>";
	            htmltext += "I guide travelers through the gate of time and space! I don't deal with outsiders!";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel > 0 && Alevel < 4)
	        {
	            htmltext = "<html><body>Gatekeeper Kurfa:<br>";
	            htmltext += "You're a true friend of my people. My ancestors guard your every step!<br>";
	            htmltext += "Stories of your valor grow everyday! Unfortunately, I can't allow you to use the gate of time and space yet. Keep up the good work!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+ String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == 4)
	        {
	            htmltext = "<html><body>Gatekeeper Kurfa:<br>";
	            htmltext += "You, comrade of the Ketra. May the divine protection of the ancestors be with you always!<br>";
	            htmltext += "Our chief has approved of your valor and will allow you to travel to a bigger battleground. So, tell me where you wish to go! I, Kurfa, will open the gate of time and space for you!<br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport Teleport\">Teleport</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Gatekeeper Kurfa:<br>";
	            htmltext += "Dear sworn friend of Ketra. May the divine blessing and protection of our ancestors be with you always!<br>";
	            htmltext += "Your bravery is renowned! Everybody says that when you battle, you are as hot as an exploding volcano! Our chief has given his approval for you to travel to another village! You, tell me where you wish to go! I, Kurfa will open the gate of time and space for you!<br>";
	            htmltext += "<a action=\"bypass -h Quest KetraOrcSupport Teleport\">Teleport</a>";
	            htmltext += "</body></html>";
	        }
	    }
		return htmltext;
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		int Alevel = player.getAllianceWithVarkaKetra();
		if (npc.getNpcId() == Asefa)
		{
			for (int i = 0; i < BuffList.length;i++)
			{
				if (event.equalsIgnoreCase(String.valueOf(BuffList[i])))
				{
					if (st.getQuestItemsCount(Horn) >= Buff[i][2])
					{
						st.takeItems(Horn,Buff[i][2]);
						npc.setTarget(player);
						npc.doCast(SkillTable.getInstance().getInfo(Buff[i][0],Buff[i][1]));
						npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp());
						htmltext = "<html><body>Soul Guide Asefa:<br>";
						htmltext += "Welcome, sworn friend of Ketra! I pray that the Flame of Paagrio will shed its light on your soul!<br>";
						htmltext += "The blood of the Varka Silenos you drew has been dedicated to the souls of our great ancestors in the midst of sacred flames in heaven! You'll receive the blessings of our ancestors!<br>";
		                htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 1\">Blessing of Wolf (Focus)</a> Req: 2 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 2\">Blessing of Hawk (Death Whisper)</a> Req: 2 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 3\">Blessing of Bear (Might)</a> Req: 3 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 4\">Blessing of Spider (Acumen)</a> Req: 3 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 5\">Blessing of Lion (Berserker)</a> Req: 3 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 6\">Blessing of Coyote (Vampiric Rage)</a> Req: 3 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 7\">Blessing of Owl (Empower)</a> Req:6 BH<br>";
						htmltext += "<a action=\"bypass -h Quest KetraOrcSupport 8\">Blessing of Wild Horse (Haste)</a> Req: 6 BH<br>BH - Buffalo Horns<br>";
						htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
						htmltext += "</body></html>";
					}
				}
			}
		}
		else if (npc.getNpcId() == Jaff)
	    {
	    	if (event.equalsIgnoreCase("withdraw"))
	    	{
	    		if (player.getWarehouse().getSize() == 0)
	    		{
	    			htmltext = "<html><body>Warehouse Keeper Jaff:<br>";
	    			htmltext += "Ha! You never deposited anything here! Your memory is not as good as your reputation!"; 
	    			htmltext += "</body></html>";
	    		}
	    		else
	    		{
	    			player.sendPacket(ActionFailed.STATIC_PACKET);
	    			player.setActiveWarehouse(player.getWarehouse());
	    			player.sendPacket(new WareHouseWithdrawalList(player, 1));
	    		}
	    	}
	    }
		else if (npc.getNpcId() == Kurfa)
		{
			if (event.equalsIgnoreCase("teleport"))
			{
				if (Alevel == 4)
				{
					htmltext = "<html><body>";
					htmltext += "&$556;<br><br>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10028\" msg=\"811;Goddard Castle Town\">Goddard Castle Town - 4900 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10029\" msg=\"811;Rune Castle Town\">Rune Castle Town - 22000 Adena</a><br1>";
					htmltext += "</body></html>";
				}
				else if (Alevel == 5)
				{
					htmltext = "<html><body>";
					htmltext += "&$556;<br><br>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10028\" msg=\"811;Goddard Castle Town\">Goddard Castle Town - 4900 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10029\" msg=\"811;Rune Castle Town\">Rune Castle Town - 22000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10030\" msg=\"811;Aden Castle Town\">Aden Castle Town - 21000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10031\" msg=\"811;Wall of Argos\">Wall of Argos - 7000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10032\" msg=\"811;Hot Springs\">Hot Springs - 6700 Adena</a><br1>";
					htmltext += "</body></html>";
				}
			}
		}
	    else if (npc.getNpcId() == Jumara)
	    {
	    	if (event.equalsIgnoreCase("sel1"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313750001, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
	    	if (event.equalsIgnoreCase("sel2"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313750002, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
	    	if (event.equalsIgnoreCase("sel3"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313750003, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
	    }
		return htmltext;
	}

	public static void main(String[] args)
	{
		new KetraOrcSupport(-1, qn, "custom");
	}
}