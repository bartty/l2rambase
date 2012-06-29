package custom.VarkaSilenosSupport;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.WareHouseWithdrawalList;

public class VarkaSilenosSupport extends Quest
{
	private static final String qn = "VarkaSilenosSupport";
		
	private static final int Ashas = 31377; //Hierarch
	private static final int Naran = 31378; //Messenger
	private static final int Udan  = 31379; //Buffer
	private static final int Diyabu = 31380; //Grocer
	private static final int Hagos = 31381; //Warehouse Keeper
	private static final int Shikon = 31382; //Trader
	private static final int Teranu = 31383; //Teleporter
	private static final int[] NPCS = 
	{
		Ashas,Naran,Udan,Diyabu,Hagos,Shikon,Teranu
	};
	private static final int Seed = 7187;
		
	private static final int[] BuffList = {
		1,2,3,4,5,6,7,8
	};
	private static final int[][] Buff = {
		{ 4359,1,2 }, //Focus: Requires 2 Nepenthese Seeds
		{ 4360,1,2 }, //Death Whisper: Requires 2 Nepenthese Seeds
		{ 4345,1,3 }, //Might: Requires 3 Nepenthese Seeds
		{ 4355,1,3 }, //Acumen: Requires 3 Buffalo Horns
		{ 4352,1,3 }, //Berserker: Requires 3 Nepenthese Seeds
		{ 4354,1,3 }, //Vampiric Rage: Requires 3 Nepenthese Seeds
		{ 4356,1,6 }, //Empower: Requires 6 Nepenthese Seeds
		{ 4357,1,6 }  //Haste: Requires 6 Nepenthese Seeds
	};

	public VarkaSilenosSupport(int id, String name, String descr)
	{
		super(id, name, descr);
		for ( int i :NPCS)
		{
			addFirstTalkId(i);
			addStartNpc(i);
		}
		addTalkId(Shikon);
		addTalkId(Udan);
		addTalkId(Hagos);
		addTalkId(Teranu);
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
		int npcId = npc.getNpcId();
	    int Alevel = player.getAllianceWithVarkaKetra();
	    long Seeds = st.getQuestItemsCount(Seed);
	    if (npcId == Ashas)
	    {
	        if (Alevel < 0)
	        {
	            htmltext = "<html><body>Elder Ashas Varka Durai:<br>";
	            htmltext += "Welcome, my friend!  I'm Ashas Varka Durai, an elder of the Varka tribe.  <br>"; 
	            htmltext += "To defend the land that was promised to us, we're at war with the arrogant Ketra Orcs.";
	            htmltext += "You've helped us to significantly damage them. They've proven to be much more formidable than we thought. ";
	            htmltext += "We've reached an intractable stalemate!  To bring our struggle to an end, you must make a long, hard journey.  ";
	            htmltext += "I wish you well! <br>"; 
	            htmltext += "But, be careful.  Whatever you do, you must remember our brothers are watching you.  ";
	            htmltext += "<font color=LEVEL>As soon as you lay a hand on our Varka tribe, all the promises between you and us will disappear! </font> <br> ";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>"; 
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Elder Ashas Varka Durai:<br> ";
	            htmltext += "Hold it right there, stranger! Unfriendly visitors aren't welcome in this land!<br>"; 
	            htmltext += "Traveler of the west wind! If you expect to win our trust, you must first visit <font color=LEVEL> Varka's messenger Naran Ashanuk</font>. He'll tell you what you must do."; 
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Naran)
	    {
	        if (Alevel < 0)
	        {
	            htmltext = "<html><body>Ketra's Messenger Naran Ashanuk:<br>";
	            htmltext += "The Silenos were a wandering tribe that followed the wind. I suppose if you've made it this far you already know that. We Varka are warriors! We're the toughest, most tenacious fighters ever! You're extremely brave!<br>";
	            htmltext += "You helped us to drive those arrogant Ketra Orcs from our territory!<br>";
	            htmltext += "Dear friend of the Varka, please stay and help us fight our enemy!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Varka's Messenger Naran Ashanuk:<br>";
	            htmltext += "Behold the promised land of the Varka! It was given to us as a reward for spilling our blood in defense of the kingdom of Elmore! Who do those pathetic Ketra Orcs think they are? Saying that this land has belonged to them for thousands of years! Rubbish!<br>";
	            htmltext += "You, traveler of the west wind! Will you help us defeat those arrogant Ketras? You'll be well-rewarded!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Udan)
	    {
	        if (Alevel > -1)
	        {
	            htmltext = "<html><body>Soul Guide Udan Mardui:<br>";
	            htmltext += "I'm Udan Mardui, guide of dead souls. Dear traveler of the west wind, there's nothing I can offer you.<br>";
	            htmltext += "You came here looking for peace, leave the same way!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel > -3 && Alevel < 0)
	        {
	            htmltext = "<html><body>Soul Guide Udan Mardui:<br>";
	            htmltext += "I'm Soul Guide Udan Mardui.<br>You're a friend of Varka, aren't you? Your reputation precedes you, my friend! Unfortunately, I can't offer you any just help yet.<br>";
	            htmltext += "All favors must be reciprocated with an appropriate price! When you've accumulated enough trust, come back and see me again! On behalf of our tribe, I'll give you a reward!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel < -2)
	        {
	            if (Seeds > 0)
	            {
	                htmltext = "<html><body>Soul Guide Udan Mardui:<br>";
	                htmltext += "I am Udan Mardui, guide of dead souls.<br>Dear friend of water, I thank you for the service you provided. May the good will and blessing of nature's goodness be with you always! I hope the reward I give you will help in your combat.<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 1\">Blessing of Wolf (Focus)</a> Req: 2 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 2\">Blessing of Hawk (Death Whisper)</a> Req: 2 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 3\">Blessing of Bear (Might)</a> Req: 3 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 4\">Blessing of Spider (Acumen)</a> Req: 3 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 5\">Blessing of Lion (Berserker)</a> Req: 3 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 6\">Blessing of Coyote (Vampiric Rage)</a> Req: 3 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 7\">Blessing of Owl (Empower)</a> Req: 6 NS<br>";
	                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 8\">Blessing of Wild Horse (Haste)</a> Req: 6 NS<br>NS - Nepenthes Seeds<br>";
	                htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	                htmltext += "</body></html>";
	            }
	            else
	            {
	                htmltext = "<html><body>Soul Guide Udan Mardui:<br>";
	                htmltext += "If you want to be blessed, give me the<font color=LEVEL> seed of Nepenthes</font>! Prove yourself!<br>";
	                htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	                htmltext += "</body></html>";
	            }
	        }
	    }
	    else if (npcId == Diyabu)
	    {
	        if (player.getKarma() >= 1)
	        {
	            htmltext = "<html><body>Grocer Diyabu:<br>";
	            htmltext += "Oh ... you're a wanted criminal. My man may report you to the village guards if he sees you. Escape to another place at once. I cannot sell to you now, but I trust you will be a future customer. When you are free once more, please pay our shop a visit. Don't forget us.";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel >= 0)
	        {
	            htmltext = "<html><body>Grocer Diyabu:<br>";
	            htmltext += "Dear traveler of the west wind. I must tell you! I deal with friends of Varka only!";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == -1 && Alevel == -2)
	        {
	            htmltext = "<html><body>Grocer Diyabu:<br>";
	            htmltext += "Oh! Are you the soldier I've been hearing so much about?!<br>";
	            htmltext += "My name is Diyabu. As you can see, I'm a businessman. If you ever need anything, just let me know. I'll try my best to accommodate you. A hero like you deserves the best!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Buy 382\">Buy</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Grocer Diyabu:<br>";
	            htmltext += "Dear Friend of Varka! You make me very sad! Friends shouldn't lie to each other! You should be more honest!<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Buy 382\">Buy</a><br>";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Hagos)
	    {
	        if (Alevel >= 0)
	        {
	            htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
	            htmltext += "Oh, traveler of the west wind! Haven't you heard? We Varka only do favors for our friends. We don't deal with strangers!"; 
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == -1)
	        {
	            htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
	            htmltext += "Welcome, friend of Varka!<br>"; 
	            htmltext += "I'm in charge of keeping the tribe's supplies. I can't help you now because I don't yet have the approval of the elder.<br>"; 
	            htmltext += "Try a little harder! Surely he'll allow you to use the warehouse.<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>"; 
	            htmltext += "</body></html>";
	        }
	        else if (player.getWarehouse().getSize() == 0 )
	        {
	            htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
	            htmltext += "Hello, friend of Varka! You haven't deposited any items here.<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>"; 
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport Withdraw\">Pick up an item.</a>"; 
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == -2 && Alevel == -3)
	        {
	            htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
	            htmltext += "Do they call you a criminal? I don't like labels like that... What's a criminal anyway? Someone who broke a Contract? Varkas always keep their word! So should you!<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>"; 
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport Withdraw\">Pick up an item.</a>"; 
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
	            htmltext += "Welcome, valiant soldier of Varka!<br>"; 
	            htmltext += "What can I do for you?<br>"; 
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_DepositP\">Deposit an item.</a><br>"; 
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport Withdraw\">Pick up an item.</a>"; 
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Shikon)
	    {
	        if (Alevel == -2)
	        {
	            htmltext = "<html><body>Trader Shikon:<br>";
	            htmltext += "Greetings, friend of the Varka!<br>";
	            htmltext += "Are you surprised to learn that traders such as myself exist within my warrior race? Don't you realize that"; 
	            htmltext += "rivers carry the treasures of many lands as they find their way to the sea?<br>What I can supply to you now"; 
	            htmltext += "is this low-level booty pouch! Someday, when you can open one of our looted boxes, you'll lay your hands on"; 
	            htmltext += "the strongest armor available!<br>Bring me a<font color=LEVEL> Nepenthes Seed</font> and I'll give you"; 
	            htmltext += "whatever you want!<br>";
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport sel1\">I want to buy one of the Varka's specialty products.</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a></body></html>";
	        }
	        else if (Alevel == -3 || Alevel == -4)
	        {
	            htmltext = "<html><body>Trader Shikon:<br>";
	            htmltext += "Your reputation as a valiant warrior grows everyday, along with the joy in my heart! I now hope that before long, we'll be able to settle in the promised land!<br>";
	            htmltext += "I've been ordered to supply a higher-level pouch to you, but unfortunately, I can't give you the strongest armor yet! Come back when you've had more experience, and are qualified to open the box.<br>";
	            htmltext += "Bring me a<font color=LEVEL> Nepenthes Seed</font> and I'll give you whatever you want! Care to take a look?<br>";
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport sel2\">I want to buy one of the Varka's specialty products.</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == -5)
	        {
	            htmltext = "<html><body>Trader Shikon:<br>";
	            htmltext += "Excellent! I've never seen a fighter as strong as you!<br>";
	            htmltext += "You're finally qualified to obtain the most valuable looted goods from our tribe! Now, open the box to reveal your path to the strongest armor!<br>";
	            htmltext += "Bring me a<font color=LEVEL> Nepenthes Seed</font> and I'll give you whatever you want! Care to give it a try?<br>";
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport sel3\">I want to buy one of the Varka's specialty products.</a><br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Trader Shikon:<br>";
	            htmltext += "Greetings, friend of the Varka!<br>";
	            htmltext += "You are not strong enough yet! Try again when you've had more experiences.";
	            htmltext += "</body></html>";
	        }
	    }
	    else if (npcId == Teranu)
	    {
	        if (Alevel >= 0)
	        {
	            htmltext = "<html><body>Gatekeeper Teranu Mardui:<br>";
	            htmltext += "I guide travelers through the gate of time and space! I don't deal with outsiders!";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel < 0 && Alevel > -4)
	        {
	            htmltext = "<html><body>Gatekeeper Teranu Mardui:<br>";
	            htmltext += "Hello there, old friend! <br>";
	            htmltext += "I'm dreadfully sorry, but you still are forbidden to use the gate of time and space. In the mean time, would you be willing to serve the Varka?<br>";
	            htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
	            htmltext += "</body></html>";
	        }
	        else if (Alevel == -4)
	        {
	            htmltext = "<html><body>Gatekeeper Teranu Mardui:<br>";
	            htmltext += "We Varka have no regard for the standards of the outside world. If a person is trustworthy, he is our friend. Where do you wish to travel, friend of the Varka?<br>";
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport Teleport\">Teleport</a>";
	            htmltext += "</body></html>";
	        }
	        else
	        {
	            htmltext = "<html><body>Gatekeeper Teranu Mardui:<br> ";
	            htmltext += "Welcome, friend of Varka!<br>My elder says that I can escort you to another village. Since you're our friend, I'll gladly open the gate of time and space for you.<br>"; 
	            htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport Teleport\">Teleport</a>"; 
	            htmltext += "</body></html>";
	        }
	    }
		return htmltext;
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		String htmltext = "";
		int Alevel = player.getAllianceWithVarkaKetra();
		if (npc.getNpcId() == Udan)
		{
			for (int i = 0; i < BuffList.length;i++)
			{
				if (event.equalsIgnoreCase(String.valueOf(BuffList[i])))
				{
					if (st.getQuestItemsCount(Seed) >= Buff[i][2])
					{
						st.takeItems(Seed,Buff[i][2]);
						npc.setTarget(player);
						npc.doCast(SkillTable.getInstance().getInfo(Buff[i][0],Buff[i][1]));
						npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp());
						htmltext = "<html><body>Soul Guide Udan Mardui:<br>";
		                htmltext += "I am Udan Mardui, guide of dead souls.<br>Dear friend of water, I thank you for the service you provided. May the good will and blessing of nature's goodness be with you always! I hope the reward I give you will help in your combat.<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 1\">Blessing of Wolf (Focus)</a> Req: 2 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 2\">Blessing of Hawk (Death Whisper)</a> Req: 2 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 3\">Blessing of Bear (Might)</a> Req: 3 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 4\">Blessing of Spider (Acumen)</a> Req: 3 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 5\">Blessing of Lion (Berserker)</a> Req: 3 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 6\">Blessing of Coyote (Vampiric Rage)</a> Req: 3 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 7\">Blessing of Owl (Empower)</a> Req: 6 NS<br>";
		                htmltext += "<a action=\"bypass -h Quest VarkaSilenosSupport 8\">Blessing of Wild Horse (Haste)</a> Req: 6 NS<br>NS - Nepenthes Seeds<br>";
						htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_Quest\">Quest</a>";
						htmltext += "</body></html>";
					}
				}
			}
		}
		else if (npc.getNpcId() == Hagos)
		{
			if (event.equalsIgnoreCase("Withdraw"))
			{
				if (player.getWarehouse().getSize() == 0)
				{
					htmltext = "<html><body>Warehouse Keeper Hagos:<br> ";
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
		else if (npc.getNpcId() == Teranu)
		{
			if (event.equalsIgnoreCase("Teleport"))
			{
				if (Alevel == -4)
				{
					htmltext = "<html><body>";
					htmltext += "&$556;<br><br>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10033\" msg=\"811;Goddard Castle Town\">Goddard Castle Town - 8000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10034\" msg=\"811;Rune Castle Town\">Rune Castle Town - 12000 Adena</a><br1>";
					htmltext += "</body></html>";
				}
				else if (Alevel == -5)
				{
					htmltext = "<html><body>";
					htmltext += "&$556;<br><br>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10033\" msg=\"811;Goddard Castle Town\">Goddard Castle Town - 8000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10034\" msg=\"811;Rune Castle Town\">Rune Castle Town - 12000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10035\" msg=\"811;Aden Castle Town\">Aden Castle Town - 17000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10036\" msg=\"811;Wall of Argos\">Wall of Argos - 11000 Adena</a><br1>";
					htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 10037\" msg=\"811;Hot Springs\">Hot Springs - 14000 Adena</a><br1>";
					htmltext += "</body></html>";
				}
			}
		}
		else if (npc.getNpcId() == Shikon)
		{
			if (event.equalsIgnoreCase("sel1"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313820001, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
	    	if (event.equalsIgnoreCase("sel2"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313820002, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
	    	if (event.equalsIgnoreCase("sel3"))
	    	{
	    		L2Multisell.getInstance().separateAndSend(313820003, player, npc.getNpcId(), false, 0.0);
	    		htmltext = "";
	    	}
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new VarkaSilenosSupport(-1, qn, "custom");
	}
}