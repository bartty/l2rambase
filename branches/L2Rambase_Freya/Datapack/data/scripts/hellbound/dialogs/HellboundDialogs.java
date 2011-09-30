/**
 **
 ** Hellbound project all in one - Script created by L2Rambase Project
 ** build from l2jtw
 **
 ** List of Npc that is implemented
 ** Bernarde, Budenka, Buron, Falk, Hude, Kief, Jude, Solomon, Jude, NativesNpc
 **/

package hellbound.dialogs;

import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class HellboundDialogs extends Quest
{
	private static final String qn = "HellboundDialogs";
	
	//NPCs
	private static final int BERNARDE = 32300;
	private static final int BURON = 32345;
	private static final int FALK = 32297;	
	private static final int HUDE  = 32298;
	private static final int KIEF = 32354;
	private static final int SOLOMON = 32355;
	private static final int BUDENKA = 32294;
	private static final int NATIVES_NPC = 32357;
	private static final int JUDE = 32356;

	//ITEMs
	private static final int NATIVE_HELMET  = 9669;
	private static final int NATIVE_TUNIC  = 9670;
	private static final int NATIVE_PANTS  = 9671;
	private static final int DARIONS_BADGE = 9674;
	private static final int MAGIC_BOTTLE = 9672;
	private static final int HOLY_WATER = 9673;
	private static final int MARK_OF_BETRAYAL = 9676;	
	private static final int CONTAINED_LIFE_FORCE = 9682;
	private static final int LIFE_FORCE = 9681;
	private static final int DIM_LIFE_FORCE = 9680;
	private static final int BASIC_CARAVAN_CERTIFICATE = 9850;
	private static final int STANDARD_CARAVAN_CERTIFICATE = 9851;
	private static final int PREMIUM_CARAVAN_CERTIFICATE = 9852;
	private static final int NATIVE_TREASURE= 9684;
	private static final int SCORPION_POISON_STINGER = 10012;
	private static final int MAP_OF_HELLBOUND = 9994;
	
	//SKILLs
	private static final int NATIVE_TRANSFORMATION_ID = 101;

	//SPECIALs
	private int condition = 0;
	private static final int[] CaravanCertificates =
	{
		9850, 9851, 9852
	};
	private int badgesAmount = 0;
	private boolean checked = false;
	private int points = 0;
	
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		int hellboundLevel = HellboundManager.getInstance().getLevel();
		int NPC = npc.getNpcId();
		if (NPC == BERNARDE)
		{
			if (event.equalsIgnoreCase("HolyWater"))
			{
				long BadgesCount = st.getQuestItemsCount(DARIONS_BADGE);
				if (BadgesCount < 5)
					return "<html><body>Bernarde:<br>I am afraid, that you have the enough Darion's Badges.</body></html>";
				st.takeItems(DARIONS_BADGE, 5);
				st.giveItems(HOLY_WATER, 1);
				return "<html><body>Bernarde:<br>Here. Now, please, help our ancestors to sleep and rests in world!</body></html>";
			}
			else if (event.equalsIgnoreCase("Treasure"))
			{
				if (condition == 1)
					return "<html><body>Bernarde:<br>Seems you already gave treasure to me?</body></html>";
				long treasures = st.getQuestItemsCount(NATIVE_TREASURE);
				if (treasures < 1)
					return "<html><body>Bernarde:<br>O, and where is treasure?</body></html>";
				st.takeItems(NATIVE_TREASURE, 1);
				condition = 1;
				return "<html><body>Bernarde:<br>Thank you! This treasure very help for natives.</body></html>";
			}
			else if (event.equalsIgnoreCase("rumors"))
			{
				if (hellboundLevel == 6)
					return "<html><body>Bernarde:<br>Demons have been very active in the Magical Field lately. There must be something going on for them to only increase security in that area. Would you investigate? Perhaps you will find something that will help us reduce Beleth's power.</body></html>";
				if (hellboundLevel == 7)
					return "<html><body>Bernarde:<br>I knew, why the Battered Lands was left, why even caravans abandoning hope are afraid to go there. Possibly, when you will be ready, you will want to find out is it independent? Speak, it is possible to use Magic Bottle, to collect Magic Souls of monsters which in same queue are very valued by oversea magicians. Why not to make you to attempt?</body></html>";
				if (hellboundLevel == 8)
					return "<html><body>Bernarde:<br>I heard that Resistance and main habitants of continent have forced out Devils back to the external gate of Steel Citadel! A breach through gate is now only the question of time. True, one of erected hostile captains strengthening before a gate, but associations of our forces it must be it is enough, to manage with him even!</body></html>";
			}
			else if (event.equalsIgnoreCase("alreadysaid"))
				return "<html><body>Bernarde:<br>I said enough; now I must go back to work. Please, help to bring rest to those my ancestors which ferment among the ruins of Ancient Temple.</body></html>";
			else if (event.equalsIgnoreCase("abouthelp"))
				return "<html><body>Bernarde:<br>Derek is obvious, first priest, from time to time appears among the Ruins of Ancient Temple. How is tragic, that his spirit still roams there, incapable to rest! While he ferments, I, am afraid, there will never be a world between a temple and local village...</body></html>";
			else if (event.equalsIgnoreCase("quarry"))
				return "<html><body>Bernarde:<br>They speak that the slaves of caravan are forced to work on a career. If you will be able to rescue them, a caravan is rather in all to consent to enter into with local. Please, help us to free the Quarry Slaves!</body></html>";
			
			return null;
		}
		else if (NPC == BURON)
		{
			long BadgesCount = st.getQuestItemsCount(DARIONS_BADGE);
			String text = "<html><body>Buron:<br>It's a pity, but you do not have the necessary items.</body></html>";

			if (event.equalsIgnoreCase("Tunic"))
			{
				if (BadgesCount < 10)
					return text;
				st.takeItems(DARIONS_BADGE, 10);
				st.giveItems(NATIVE_TUNIC, 1);
			}
			else if (event.equalsIgnoreCase("Helmet"))
			{
				if (BadgesCount < 10)
					return text;
				st.takeItems(DARIONS_BADGE, 10);
				st.giveItems(NATIVE_HELMET, 1);
			}
			else if (event.equalsIgnoreCase("Pants"))
			{
				if (BadgesCount < 10)
					return text;
				st.takeItems(DARIONS_BADGE, 10);
				st.giveItems(NATIVE_PANTS, 1);
			}
			else if (event.equalsIgnoreCase("rumor"))
			{
				if (hellboundLevel == 1)
				{
					return "<html><body>Buron:<br>Because press is broken, we sometimes encounter travelers from the continent. You also can meet some of our brothers, whom brought down from the way the Darion's myrmidons. Please, rescue them!</body></html>";
				}
				else if (hellboundLevel == 2)
				{
					return "<html><body>Buron:<br>The inhabitants of continent are probably strong - they moved to the oasis. Walks rumor, that on the southwest of oasis, in the ruins of ancient temple, the spectres of monks and soldiers from time to time appear. In details tell about this can Bernard. It must you warn that Bernard does not entrust to strangers, so that better dress as the local resident, when you go to it.</body></html>";
				}
				else if (hellboundLevel == 3)
				{
					return "<html><body>Buron:<br>The inhabitants of village resistances continue to suffer from the frequent attacks of devils, which they fear, that the inhabitants continent and villages will combine their forces in the fight with them. Please, save village!</body></html>";
				}
				else if (hellboundLevel == 4)
				{
					return "<html><body>Buron:<br>Our village still undergoes the frequent raids of demons, but now rarely it succeeds by them to break through defense, since local residents and inhabitants of continent combined their force. But we must apply decisive attack to our enemy... Possibly, Bernard knows, as this to make.</body></html>";
				}
				else if (hellboundLevel == 5)
				{
					return "<html><body>Buron:<br>Village resistances is rescued, and we again can name our house our! Now we should compose the plan the rescuing of slaves and prisoners of stone quarry. Beleth selected this place because it is rich in rare minerals. If we save these slaves, I it is confident, caravan will be on our side. Without their aid we will starve...</body></html>";
				}
				else if (hellboundLevel == 6)
				{
					return "<html><body>Buron:<br>Darion's influence has grown weak in the face of the united opposition of the newcomers from the continent, the natives and the Caravan. His army has already abandoned most of Hellbound to the Resistance. Still, Darion's forces are doing everything possible to defeat the Magical Field. I wonder -- is there something important buried in there?</body></html>";
				}
				else if (hellboundLevel == 7)
				{
					return "<html><body>Buron:<br>I know, why even greedy to the money caravans fear to go into the covered with wounds plateau. As generally it can exist this terrible place… I myself was not there, but everyone indicates that the plateau by terrible monsters. They indicate moreover that in these monsters it is possible to obtain the magic beverages, for which strange magicians pay well. We would be very they are grateful, if you reached for us several.</body></html>";
				}
				else if (hellboundLevel == 8)
				{
					return "<html><body>Buron:<br>It's amazing! I heard, that the inhabitants of continent and village Resistances combined their forces and drove off devils to the external gates. Gates captain guards, but we will break through defense because of the numerical superiority!</body></html>";
				}
				else if (hellboundLevel == 9)
				{
					return "<html><body>Buron:<br>I heard, that the defense of external gates fall, and our forces systematically move into the Steel Citadel! Walks rumor, that the gnome, whom projected steel citadel, it still lives somewhere in the lock. Many of our brothers also live in the oasis or in the city, located in the limits of the walls lock. If you please, save them! If you have one of Darion's Badges, you will be able to travel everywhere without any limitations.</body></html>";
				}
				else if (hellboundLevel == 10)
				{
					return "<html><body>Buron:<br>Steel Citadel occupied by the monsters, which it is necessary to destroy level after the level. In order to take steel citadel, you must all kill them!</body></html>";
				}
				else if (hellboundLevel == 11)
				{
					return "<html><body>Buron:<br>Now, need to defeat Darion and Beleth - and return peace to the Hellbound Island.</body></html>";
				}
			}
		}
		else if (NPC == HUDE)
		{
			if (event.equalsIgnoreCase("scertif"))
			{
				if (st.getQuestItemsCount(SCORPION_POISON_STINGER) >= 60 && st.getQuestItemsCount(MARK_OF_BETRAYAL) >= 30)
				{
					st.takeItems(MARK_OF_BETRAYAL, 30);
					st.takeItems(SCORPION_POISON_STINGER, 60);
					st.takeItems(BASIC_CARAVAN_CERTIFICATE, 1);
					st.giveItems(STANDARD_CARAVAN_CERTIFICATE, 1);
					return "<html><body>Hude:<br>Remarkably! You pleasantly surprised me.</body></html>";
				}
				else
					return "<html><body>Hude:<br>I do not see them! Do not try to deceive me!</body></html>";
			}
			if (event.equalsIgnoreCase("pcertif"))
			{
				if (st.getQuestItemsCount(LIFE_FORCE) >= 56 && st.getQuestItemsCount(CONTAINED_LIFE_FORCE) >= 14)
				{
					st.takeItems(LIFE_FORCE, 56);
					st.takeItems(CONTAINED_LIFE_FORCE, 14);
					st.takeItems(STANDARD_CARAVAN_CERTIFICATE, 1);
					st.giveItems(PREMIUM_CARAVAN_CERTIFICATE, 1);
					st.giveItems(MAP_OF_HELLBOUND, 1);
					return "<html><body>Hude:<br>Fine! With such high-quality materials, as these, I will be able well to begin to work. Take this map which was found by a Caravan's near Hellbound. I hope, it will be useful for you.</body></html>";
				}
				else
					return "<html><body>Hude:<br>I know, whatever you would begin to cheat me, but in behalf of our business relations I think that was better, if you give me proofs...</body></html>";
			}
		}
		else if (NPC == NATIVES_NPC)
		{
			if (event.equalsIgnoreCase("FreeSlaves"))
			{
				long badges = st.getQuestItemsCount(DARIONS_BADGE);
				if (badges >= 5)
				{
					st.takeItems(DARIONS_BADGE, 5);
					badgesAmount++;
					if (badgesAmount == 6)
					{
						HellboundManager.getInstance().changeLevel(10);
						return "<html><body>Thank you!<br>Now I can leave this place!</body></html>";
					}
				}
				else
					return "<html><body>You do not have enough Darion's Badges. You need at least 5 of that Badges to leave this place.</body></html>";
			}
		}
		else if (NPC == JUDE)
		{
			if (event.equalsIgnoreCase("TreasureSacks"))
			{
				if (st.getQuestItemsCount(NATIVE_TREASURE) >= 40)
				{
					st.takeItems(NATIVE_TREASURE, 40);
					return "<html><body>Jude:<br>Oh, treasure... Where it was?</body></html>";
				}
				else
					return "<html><body>Jude:<br>You do not have enough amount of Native Treasures.</body></html>";
			}
		}
		else if (NPC == KIEF)
		{
			if (event.equalsIgnoreCase("Badges"))
			{
				long badges = st.getQuestItemsCount(DARIONS_BADGE);
				if (badges >= 1)
				{
					long trustam = (10 * badges);
					HellboundManager.getInstance().increaseTrust((int) trustam);
					return "<html><body>Kief:<br>Thank you. Your help in the Beleth's banishment from this place.</body></html>";
				}
				else
					return "<html><body>Kief:<br>I can not find the Darion's Badges.</body></html>";
			}
			else if (event.equalsIgnoreCase("bottle"))
			{
				return "<html><body>Kief:<br>Magic Bottle was created by magicians from Ivory Tower. In Battered Lands the monsters dwell with by strong magic force and at when them there is a not enough health, their magic force can be sharpened in Magic Bottle. It is possible to do only then, when they are very weak. Speak, if to do it, got Life Force help magicians from Ivory Tower in researches. Lets even unsuccessful, but they however the Beleth's creations. Do you want to make attempt to collect Life forces? I would give you her free of charge, but magicians from Ivory Tower also take for her the pay and I need... Excuse me. I will take at you only 20 Scorpions Stinger.<br><a action=\"bypass -h Quest HellboundDialogs getbottle\">To get Magic Bottle.</a></body></html>";
			}
			else if (event.equalsIgnoreCase("getbottle"))
			{
				long stinger = st.getQuestItemsCount(SCORPION_POISON_STINGER);
				if (stinger >= 20)
				{
					st.takeItems(SCORPION_POISON_STINGER, 20);
					st.giveItems(MAGIC_BOTTLE, 1);
					return "<html><body>Kief:<br>Take.</body></html>";
				}
				else
					return "<html><body>Kief:<br>Excuse me, but that you brought it is not sufficient.</body></html>";
			}
			else if (event.equalsIgnoreCase("dlf"))
			{
				long dimlf = st.getQuestItemsCount(DIM_LIFE_FORCE);
				if (dimlf >= 1)
				{
					long trustam = (10 * dimlf);
					st.takeItems(DIM_LIFE_FORCE, dimlf);
					HellboundManager.getInstance().increaseTrust((int) trustam);
					points += trustam;
					saveGlobalQuestVar("life_points", String.valueOf(points));
					return "<html><body>Kief:<br>Thank you. Your help in the Beleth's banishment from this place.</body></html>";
				}
				else
					return "<html><body>Kief:<br>Excuse me, but you brought nothing.</body></html>";
			}
			else if (event.equalsIgnoreCase("lf"))
			{
				long lifef = st.getQuestItemsCount(LIFE_FORCE);
				if (lifef >= 1)
				{
					long trustam = (20 * lifef);
					st.takeItems(LIFE_FORCE, lifef);
					HellboundManager.getInstance().increaseTrust((int) trustam);
					points += trustam;
					saveGlobalQuestVar("life_points", String.valueOf(points));
					return "<html><body>Kief:<br>Thank you. Your help in the Beleth's banishment from this place.</body></html>";
				}
				else
					return "<html><body>Kief:<br>Excuse me, but you brought nothing.</body></html>";
			}
			else if (event.equalsIgnoreCase("clf"))
			{
				long conlf = st.getQuestItemsCount(CONTAINED_LIFE_FORCE);
				if (conlf >= 1)
				{
					long trustam = (50 * conlf);
					st.takeItems(CONTAINED_LIFE_FORCE, conlf);
					HellboundManager.getInstance().increaseTrust((int) trustam);
					points += trustam;
					saveGlobalQuestVar("life_points", String.valueOf(points));
					return "<html><body>Kief:<br>Thank you. Your help in the Beleth's banishment from this place.</body></html>";
				}
				else
					return "<html><body>Kief:<br>Excuse me, but you brought nothing.</body></html>";
			}
			else if (event.equalsIgnoreCase("CheckPoints"))
			{
				if (!checked && points >= 1000000)
				{
					checked = true;
					HellboundManager.getInstance().changeLevel(8);
					points = 0;
					saveGlobalQuestVar("life_points", String.valueOf(points));
					cancelQuestTimers("CheckPoints");
				}
			}
		}
		return htmltext;		
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);

		int hellboundLevel = HellboundManager.getInstance().getLevel();
		int NPC = npc.getNpcId();
		if (NPC == BERNARDE)
		{
			if (hellboundLevel < 2)
				return "<html><body>Bernarde:<br>Who are you? Seems from a continent. You already took away all at us, what only it is possible! Go away from here and leave us at peace!</body></html>";
			else if (hellboundLevel == 2)
			{
				if (!player.isTransformed())
					return "<html><body>Bernarde:<br>Who are you? Seems from a continent. You already took away all at us, what only it is possible! Go away from here and leave us at peace!</body></html>";
				if (player.getTransformation().getId() != NATIVE_TRANSFORMATION_ID)
					return "<html><body>Bernarde:<br>Who are you? Seems from a continent. You already took away all at us, what only it is possible! Go away from here and leave us at peace!</body></html>";
				return "<html><body>Bernarde:<br>He, seems, from our island - at him such healthy color of person, which I did not see anymore nowhere! Rumors are about, that Demons intrude in local village  and beggar them. They, seems, those treasures which are especially honored local were ransacked even. I will be incredibly thankful if you could rescue these treasures and bring them to me. Certainly, I must also ask you to make off the task in  Ruins of Ancient Temple.<br><a action=\"bypass -h Quest HellboundDialogs Ruins\">To ask about the task in Ruins of Ancient Temple.</a><br><a action=\"bypass -h Quest HellboundDialogs Treasure\">To give treasures.</a></body></html>";
			}
			else if (hellboundLevel == 3)
			{
				if (!player.isTransformed())
					return "<html><body>Bernarde:<br>He, seems, from our island. He looks very strong.</body></html>";
				if (player.getTransformation().getId() != NATIVE_TRANSFORMATION_ID)
					return "<html><body>Bernarde:<br>He, seems, from our island. He looks very strong.</body></html>";
				return "<html><body>Bernarde:<br>He, seems, from our island - at him such healthy color of person, which I did not see anymore nowhere! Rumors are about, that Demons intrude in local village  and beggar them. They, seems, those treasures which are especially honored local were ransacked even. I will be incredibly thankful if you could rescue these treasures and bring them to me. Certainly, I must also ask you to make off the task in  Ruins of Ancient Temple.<br><a action=\"bypass -h Quest HellboundDialogs Ruins\">To ask about the task in Ruins of Ancient Temple.</a><br><a action=\"bypass -h Quest HellboundDialogs Treasure\">To give treasures.</a></body></html>";
			}
			else if (hellboundLevel == 4)
			{
				if (!player.isTransformed())
					return "<html><body>Bernarde:<br>He, seems, from our island. He looks extraordinarily healthy and strong.<br><a action=\"bypass -h Quest HellboundDialogs abouthelp\">To ask about help.</a></body></html>";
				if (player.getTransformation().getId() != NATIVE_TRANSFORMATION_ID)
					return "<html><body>Bernarde:<br>He, seems, from our island. He looks extraordinarily healthy and strong.<br><a action=\"bypass -h Quest HellboundDialogs abouthelp\">To ask about help.</a></body></html>";
				return "<html><body>Bernarde:<br>He, seems, from our island - only look, what he strong! I have news from those, who explored Ruins of Ancient Temple. They speak that the Derek's soul there roams. I do not know, true this or no, but I think we can believe to these people. Will you explore Ruins of Ancient Temple? If you will find Derek, please, help him, what you will be able by.<br><a action=\"bypass -h Quest HellboundDialogs alreadysaid\">To ask about the task in Ruins of Ancient Temple.</a></body></html>";
			}
			else if (hellboundLevel == 5)
			{
				if (!player.isTransformed())
					return "<html><body>Bernarde:<br>Ah, it's you! I am glad to see you again. Due to your efforts, Derek was able to enter in Nirvana, and habitants of the village now under his guard. However many souls still roam among Ancient Ruins of Temple.<br><a action=\"bypass -h Quest HellboundDialogs quarry\">To ask about work in Hellbound Quarry.</a></body></html>";
				if (player.getTransformation().getId() != NATIVE_TRANSFORMATION_ID)
					return "<html><body>Bernarde:<br>Ah, it's you! I am glad to see you again. Due to your efforts, Derek was able to enter in Nirvana, and habitants of the village now under his guard. However many souls still roam among Ancient Ruins of Temple.<br><a action=\"bypass -h Quest HellboundDialogs quarry\">To ask about work in Hellbound Quarry.</a></body></html>";
				return "<html><body>Bernarde:<br>Ah, glad to meeting, my friend! Thank you to you, that helped the Derek's soul to attain Nirvana. Now habitants of the village under his guardianship.</body></html>";
			}
			else if (hellboundLevel >= 6 && hellboundLevel < 9)
			{
				if (!player.isTransformed())
					return "<html><body>Bernarde:<br>The heap of time passed enough, since we were seen for the last time, my friend. How are you?<br><a action=\"bypass -h Quest HellboundDialogs rumors\">To ask about rumors.</a></body></html>";
				if (player.getTransformation().getId() != NATIVE_TRANSFORMATION_ID)
					return "<html><body>Bernarde:<br>The heap of time passed enough, since we were seen for the last time, my friend. How are you?<br><a action=\"bypass -h Quest HellboundDialogs rumors\">To ask about rumors.</a></body></html>";
				return "<html><body>Bernarde:<br>I will no reveal our secrets to the natives. But if it concerns Hellbound Isle, I would be willing to tell you what I know.<br><a action=\"bypass -h Quest HellboundDialogs rumors\">Ask about current rumors.</a></body></html>";
			}
			else if (hellboundLevel == 9)
				return "<html><body>Bernarde:<br>There are a lot of Darion's followers in Steel Citadel, and easier to us from it does not become. We must find a method to kill all of them!</body></html>";
			else if (hellboundLevel == 10)
				return "<html><body>Bernarde:<br>I heard that the habitants of continent and Resistance have moved up deeply in the heart of Steel Citadel. o, this  frigging earth can, finally, see sunset of a new era! At me on eyes the tears from one are piled up thoughts about it! But while it is early to celebrate - we must go and help our brothers in a castle.</body></html>";
			else if (hellboundLevel == 11)
				return "<html><body>Bernarde:<br>Now we must to defeat Beleth and Darion. And, in addition, to bring a peace on Hellbound.</body></html>";
			
			return null;
		}
		else if (NPC == BUDENKA)
		{
			long standartcert = st.getQuestItemsCount(STANDARD_CARAVAN_CERTIFICATE);
			long premiumcert = st.getQuestItemsCount(PREMIUM_CARAVAN_CERTIFICATE);
			if (standartcert >= 1 && premiumcert < 1)
				return "<html><body>Budenka:<br>Oh, it's you?! I heard about you from Guild. Did you analyze that, Bernard is engaged in what, to give the estimation?</body></html>";
			else if (premiumcert >= 1)
				return "<html><body>Budenka:<br>Well, you already have enough high reputation in Guild. How are you?</body></html>";
			npc.showChatWindow(player);
			return null;
		}
		else if (NPC == BURON)
		{
			if (hellboundLevel < 2)
				return "<html><body>Buron:<br>You arrived to us from the continent! Did you already see my small brother?<br><a action=\"bypass -h Quest HellboundDialogs rumor\">Ask about the rumor.</a></body></html>";
			else if (hellboundLevel >= 2 && hellboundLevel < 5)
				return "<html><body>Buron:<br>Now I can trust you! For each 10 Darion's Badges I will prepare to you clothing into the sign of my gratitude. Put on it, and the inhabitants of village will accept you.<br><a action=\"bypass -h Quest HellboundDialogs Helmet\">Craft Native Helmet.</a><br><a action=\"bypass -h Quest HellboundDialogs Tunic\">Craft Native Tunic.</a><br><a action=\"bypass -h Quest HellboundDialogs Pants\">Craft Native Pants.</a><br><a action=\"bypass -h Quest HellboundDialogs rumor\">Ask about the rumor.</a></body></html>";
			else if (hellboundLevel >= 5)
				return "<html><body>Buron:<br>Finally, we have regained our home! Please give me regards to Jirrone, who left the island some time ago.<br>There is obviously a great deal of work for me to do now that we have regained our village, but I will gladly create native clothes for you whenever you wish it.<br><a action=\"bypass -h Quest HellboundDialogs Tunic\">Craft Native Helmet.</a><br><a action=\"bypass -h Quest HellboundDialogs Pants\">Craft Native Tunic.</a><br><a action=\"bypass -h Quest HellboundDialogs Helmet\">Craft Native Pants.</a><br><a action=\"bypass -h Quest HellboundDialogs rumor\">Ask about the rumor.</a></body></html>";
			return null;
		}
		else if (NPC == FALK)
		{
			return "<html><body>Falk:<br>I see that you're an adventurer from a foreign land. Would you consider helping me? If you do, I'll help smooth your dealings with others in my Caravan group.<br><a action=\"bypass -h npc_"+npc.getObjectId()+"_Quest HellboundDialogs\">Help Falk.</a><br></body></html>";
		}
		else if (NPC == HUDE)
		{
			if (hellboundLevel >= 4 && st.getQuestItemsCount(BASIC_CARAVAN_CERTIFICATE) >= 1 && st.getQuestItemsCount(STANDARD_CARAVAN_CERTIFICATE) < 1)
				return "<html><body>Hude:<br>Ah, this - you! I thought, this Falk... Already a week is not no more news from him. If you lucky enough to see him pass that I searching him. I know that he left on the searches of Scorpion Poison Stinger, which is so useful in to medicine...<br>Possibly, would you consent to go and obtain 60 Scorpion Poison Stinger and 30 Mark of Betrayal for me? And I will give To you the coupon, and you will be able to trade with me.<br><a action=\"bypass -h Quest HellboundDialogs scertif\">To say that you have all, what he wants.</a></body></html>";
			else if (hellboundLevel < 7 && hellboundLevel > 3 && st.getQuestItemsCount(STANDARD_CARAVAN_CERTIFICATE) >= 1)
				return "<html><body>Hude:<br>Ah, this - you! I thought, this Falk... Already a week is not no more news from him. If you lucky enough to see him pass that I searching him. I know that he left on the searches of Scorpion Poison Stinger, which is so useful in to medicine...<br><a action=\"bypass -h npc_"+npc.getObjectId()+"_multisell 322980001\">To look at items.</a></body></html>";
			else if (hellboundLevel >= 7 && st.getQuestItemsCount(STANDARD_CARAVAN_CERTIFICATE) >= 1)
				return "<html><body>Hude:<br>Oh, nice to meet you! If you want to earn our immortal friendship, use Magic Bottle, to collect 56 Life Forces and 14 Contained Life Force at Chimeras in Battered Land. Before you  will go away, to see at some of the special items, that we offer.<br><a action=\"bypass -h Quest HellboundDialogs pcertif\">To say that you returned magic souls and essences.</a><br><a action=\"bypass -h npc_"+npc.getObjectId()+"_multisell 322980001\">To look at items.</a></body></html>";
			else if (hellboundLevel >= 7 && st.getQuestItemsCount(PREMIUM_CARAVAN_CERTIFICATE) >= 1)
				return "<html><body>Hude:<br>Welcome, my friend! What can I do for you today?<br><a action=\"bypass -h npc_"+npc.getObjectId()+"_multisell 322980001\">To look at items.</a><br><a action=\"bypass -h npc_"+npc.getObjectId()+"_multisell 322980002\">To look at special items.</a></body></html>";
			return "<html><body>Hude:<br>I've never seen them before, but they're dressed in really awful clothes.</body></html>";
		}
		else if (NPC == NATIVES_NPC)
		{
			int x = npc.getX();
			int y = npc.getY();

			if (hellboundLevel == 9)
			{
				boolean xIsOk = false;
				for (int i = 4475; i <= 10960; i++)
				{
					if (i == x)
						xIsOk = true;
				}
				boolean yIsOk = false;
				for (int i = 247925; i <= 254415; i++)
				{
					if (i == y)
						yIsOk = true;
				}
				if (xIsOk && yIsOk)
					return "<html><body>Native Slave:<br>Please, rescue me! You must have the Darion's Badges, to left this place. Any aboriginal which tries to left External Gates without permission will be immediately executed. Would not you give me the Badges? I ask you...<br><a action=\"bypass -h Quest HellboundDialogs FreeSlaves\">Give him Darion's Badges.</a></body></html>";
			}
			else if (hellboundLevel < 9)
				return "<html><body>Native Slave:<br>How did you get inward? The gate of lock is closed... A patrol can appear at any moment. Run!</body></html>";
			else if (hellboundLevel > 9)
				return "<html><body>Native Slave:<br>Thank you!<br>To my mind, Hellbound island has earned its freedom. Many thanks!</body></html>";
			return null;
		}
		else if (NPC == JUDE)
		{
			if (hellboundLevel < 3)
				return "<html><body>Jude:<br>Novices, yes? From where you arrived?</body></html>";
			else if (hellboundLevel == 3)
				return "<html><body>Jude:<br>I heard that the Native Village lately was often exposed by the attacks. I do not know, as aborigines collected all those treasures and hid them, but that's why they hunt for Keltas. Do me the service and bring me these treasures while them fully ransacked. The reward will be adequate more than, I promise to you. Bring me 40 Native Treasures as quick as possible.<br><a action=\"bypass -h Quest HellboundDialogs TreasureSacks\">To give him 40 Native Treasure.</a></body></html>";
			else if (hellboundLevel == 4)
				return "<html><body>Jude:<br>I heard that the Native Village still exposed by the attacks?</body></html>";
			else if (hellboundLevel == 5)
				return "<html><body>Jude:<br>Oh, Natives did ask you to do it? That, I certainly feel with their situation, but we are merchants, eventually. Would you say that the purchase-sale is the course of natures! Actually I would like to ask you about the favor. I do not know, as Natives, but you, I am sure, can handle with by this task.<br>If go from here on a north, you will find a place, where minerals are obtaining for Darion. This place is named Quarry, and many representatives of my people were there turned in slaves, and compelled them to work. Please, free them! It was yet better, if b you succeeded to free the country of Caravan, but it is the long and difficult trip. Take exempt slaves in Dolmen at the entrance in Quarry. Natives consider it as a sacred place, but we often use him for the movement. Since you you will deliver them there, we will care about them after.<br>At the guards of Quarry order to kill every slave which will try to break. You must at any cost protect our people from such fate! Avoid guards and guard our people on a way to freedom.</body></html>";
			else if (hellboundLevel >= 6)
				return "<html><body>Jude:<br>Our thanks to you! Because of your help, many of our people have found a new life. On the other hand, I hear that Darion's situation is rapidly deteriorating.<br><a action=\"bypass -h npc_"+npc.getObjectId()+"_Quest\">Quest</a></body></html>";
		}
		else if (NPC == KIEF)
		{
			if (hellboundLevel < 2)
				return "<html><body>Kief:<br>Who are you? Do not approach to me!!!!</body></html>";
			else if (hellboundLevel == 2 || hellboundLevel == 3)
				return "<html><body>Kief:<br>Did you come to rescue us? If yes, I must about something to ask you. To walk on this island you needed the Darion's Badges. If you haven't it, you will not be able nowhere to get. Help us.<br><a action=\"bypass -h Quest HellboundDialogs Badges\">Give him all Darion's Badges.</a></body></html>";
			else if (hellboundLevel == 4)
				return "<html><body>Kief:<br>Walk rumors, that Derek appeared in Ruins of Ancient Temple. Did not I see him by the eyes, as do you think, is it a true?</body></html>";
			else if (hellboundLevel == 5)
				return "<html><body>Kief:<br>We, finally, came home. I am so happy.</body></html>";
			else if (hellboundLevel == 6)
				return "<html><body>Kief:<br>Due to the newcomers from the continent, Darion's military power has been weakened. But it is rumored that they are attempting to after the Magic Fields.</body></html>";
			else if (hellboundLevel == 7)
				return "<html><body>Kief:<br>Monsters which were created by Beleth by the magic force dwell in Battered Lands. If to use one special item, it is possible to get magic force from the dying body of monster. It is that item. Change Chimeras on Force of Life and bring me her in this bottle. I try to begin to work money because magicians from Ivory Tower very expensive pay for it. I ask you. Let it be poor, but ask you, bring to me Dim Life Force or Life Force. Well and Contained Life Force is the best variant.<br><a action=\"bypass -h Quest HellboundDialogs bottle\">Ask him about Magic Bottle.</a><br><a action=\"bypass -h Quest HellboundDialogs Kief dlf\">Give him Dim Life Force.</a><br><a action=\"bypass -h Quest HellboundDialogs lf\">Give him Life Force.</a><br><a action=\"bypass -h Quest HellboundDialogs clf\">Give him Contained Life Force.</a></body></html>";
			else if (hellboundLevel >= 8)
				return "<html><body>Kief:<br>Magic Bottle was created by magicians from Ivory Tower. In Battered Lands the monsters dwell with by strong magic force and at when them there is a not enough health, their magic force can be sharpened in Magic Bottle. It is possible to do only then, when they are very weak. Speak, if to do it, got Life Force help magicians from Ivory Tower in researches. Lets even unsuccessful, but they however the Beleth's creations. Do you want to make attempt to collect Life forces? I would give you her free of charge, but magicians from Ivory Tower also take for her the pay and I need... Excuse me. I will take at you only 20 Scorpions Stinger.<br><a action=\"bypass -h Quest HellboundDialogs getbottle\">To get Magic Bottle.</a></body></html>";
		}
		else if (NPC == SOLOMON)
		{
			if (hellboundLevel == 5)
				return "<html><body>Solomon:<br>Our resistance will intend to establish good relations with Caravans, but for this to us are necessary many forces; therefore if you want to help us, go to the Caravan Encampment and speak with Jude to obtain a Caravan Certificate.</body></html>";
			else if (hellboundLevel == 6 || hellboundLevel == 7)
				return "<html><body>Solomon:<br>With your help, we have finally begun to establish friendly relations with Caravan. The food we receive from them is a great help to us, and I am confident that sooner or later we will reach the outer gate of Steel Citadel -- after passing the Battered Lands, of course.</body></html>";
			else if (hellboundLevel >= 9)
				return "<html><body>Solomon:<br>With your help, we finally approached our main goal - Steel Ciradel. Seems, it will be a great battle. I no longer doubt in our victory. You are our heros...</body></html>";
		}
		return htmltext;
	}
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		int NPC = npc.getNpcId();
		if (NPC == FALK)
		{
			boolean haveCertificates = false;
			for (int i : CaravanCertificates)
			{
				if (st.getQuestItemsCount(i) >= 1)
					haveCertificates = true;
			}
			
			if (haveCertificates)
				return "<html><body>Falk:<br>Did you already give me the Darion's Badges, remember? Now you must deal with somebody other.</body></html>";
			
			if (st.getQuestItemsCount(DARIONS_BADGE) >= 20)
			{
				st.takeItems(DARIONS_BADGE, 20);
				st.giveItems(BASIC_CARAVAN_CERTIFICATE, 1);
				return "<html><body>Falk:<br>Thank you! Take it. Now you will be able to trade with other Caravan's groups.</body></html>";
			}
			else
				return "<html><body>Falk:<br>I am afraid, it is not enough of Darion's Badges. Please, find yet and come back.</body></html>";
		}
		return htmltext;
	}
	public HellboundDialogs(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(BERNARDE);
		addTalkId(BERNARDE);
		addFirstTalkId(BERNARDE);
		addStartNpc(BUDENKA);
		addTalkId(BUDENKA);
		addFirstTalkId(BUDENKA);
		addStartNpc(BURON);
		addTalkId(BURON);
		addFirstTalkId(BURON);
		addStartNpc(FALK);
		addTalkId(FALK);
		addFirstTalkId(FALK);
		addStartNpc(HUDE);
		addTalkId(HUDE);
		addFirstTalkId(HUDE);
		addStartNpc(NATIVES_NPC);
		addTalkId(NATIVES_NPC);
		addFirstTalkId(NATIVES_NPC);
		addStartNpc(JUDE);
		addTalkId(JUDE);
		addFirstTalkId(JUDE);
		addStartNpc(KIEF);
		addTalkId(KIEF);
		addFirstTalkId(KIEF);
		addStartNpc(SOLOMON);
		addTalkId(SOLOMON);
		addFirstTalkId(SOLOMON);
		try
		{
			points = Integer.valueOf(loadGlobalQuestVar("life_points"));
		}
		catch (Exception e){}

		saveGlobalQuestVar("life_points", String.valueOf(points));
		int hellboundLevel = HellboundManager.getInstance().getLevel();
		if (hellboundLevel == 7)
			startQuestTimer("CheckPoints", 180000, null, null, true);
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new HellboundDialogs(-1,qn,"dialogs");
		_log.warning("Hellbound: Npc's Dialogs Loaded ...");
	}
}