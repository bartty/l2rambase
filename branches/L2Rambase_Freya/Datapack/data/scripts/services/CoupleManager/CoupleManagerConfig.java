/**
 * Couple Manager Config
 * 
 * Information:
 * Couple Manager Config
 */
package services.CoupleManager;

import l2.rambase.gameserver.model.quest.Quest;

public class CoupleManagerConfig extends Quest
{
	public static final String partnerIsNotOnline = "<html><body><font color=LEVEL>Couple Manager</font><br> Sorry but your partner is not <font color=13FA13> in game </font> so you cannot start or continue that couple quest.</body></html>";
	
	public static final String q_cancel = "<html><body><font color=LEVEL>Couple Manager</font><br> All activated quests are now canceled by you. Now you can select another quest and start this quest.</body></html>";
	/**
	 * Quest 1 - Help My Love
	 * @param Monsters
	 * @param Rewards
	 * @param KillCount
	 */
	public static final int[] Q1_HOT_SPRINGS_MONSTERS =
	{
		21314,21315,21316,21317,21318,21319,21320,21321,21322,21323
	};
	public static final int Q1_VITALITY_REWARD = 5000;
	public static final int Q1_KILL_COUNT = 50;
	
	public static final String q1_0 = "<html><body><font color=LEVEL>Couple Manager</font><br> Ok go to <font color=13FA13> Hot Springs </font> only with your partner in party. You must kill 50 monsters with self power, and your partner must kill also 50 monsters with self power. When you and your partner finish your job come back for vitality reward.</font></body></html>";
	public static final String q1_1 = "<html><body><font color=LEVEL>Couple Manager</font><br> Go to Hot Springs and finish job with killing monsters you and your partner.</body></html>";
	public static final String q1_finished = "<html><body><font color=LEVEL>Couple Manager</font><br> Thanks for help with Hot Springs plague. Here rewards for you and your partner.</body></html>";
	
	/**
	 * Quest 2 - Defend Your Feelings
	 * @param Monsters
	 * @param Rewards
	 * @param KillCount
	 */
	public static final int[] Q2_DEVILS_ISLE_MONSTERS =
	{
		20847,21637,20846,20986,20987,20988
	};
	public static final int[] Q2_GARDEN_OF_EVA_MONSTERS =
	{
		20803,20802,20994,20995
	};
	public static final int Q2_KILL_COUNT = 10;
	public static final int Q2_ADENA = 57;
	public static final long Q2_ADENA_COUNT = 15000;
	public static final int Q2_EXP_REWARD = 2500000;
	public static final int Q2_SP_REWARD = 350000;
	
	public static final String q2_0 = "<html><body><font color=LEVEL>Couple Manager</font><br> Now friends female partner must help me in <font color=LEVEL>Garden of Eva Last Hall</font> kill 10 monsters, and male partner must help me in <font color=LEVEL>Devils Isle Cavern Ship</font> kill 10 monsters. You don't need party there. When you and your partner finish job come back for money and experience rewards.</body></html>";
	public static final String q2_1 = "<html><body><font color=LEVEL>Couple Manager</font><br> You don't finish yet your job.</body></html>";
	public static final String q2_finished = "<html><body><font color=LEVEL>Couple Manager</font><br> Thanks for help with horrific thiefs. Here rewards for you and your partner.</body></html>";
	
	/**
	 * Quest 3 - Sweet Home
	 * @param Monsters
	 * @param Rewards
	 * @param MatsCount
	 */
	public static final int Q3_COAL = 1870;
	public static final long Q3_COAL_COUNT = 15;
	public static final int Q3_CHARCOAL = 1871;
	public static final long Q3_CHARCOAL_COUNT = 15;
	public static final int Q3_ORIHARUKON_ORE = 1874;
	public static final long Q3_ORIHARUKON_ORE_COUNT = 15;
	public static final int Q3_SOP = 1875;
	public static final long Q3_SOP_COUNT = 15;
	public static final int Q3_SP = 1000000;	
	
	public static final String q3_0 = "<html><body><font color=LEVEL>Couple Manager</font><br> I build house so i need materials. <br1>Female partner must find for me 15 coal, and 15 stone of purity, however a male partner must bring me 15 charcoal and 15 oriharukon ore. You can get this materials from 'Black Swan' quest. Then i give you a spell points.</body></html>";
	public static final String q3_1 = "<html><body><font color=LEVEL>Couple Manager</font><br> You don't have all materials for my house yet ?</body></html>";
	public static final String q3_finished = "<html><body><font color=LEVEL>Couple Manager</font><br> Great i can now finish house of love. Please take this spell points and love with your partner.</body></html>";
	
	/**
	 * Quest 4 - Count Your Love
	 * @param Monsters
	 * @param Rewards
	 * @param KillCount
	 */
	public static final int[] Q4_DUNGEON_MONSTERS =
	{
		7000,7003,7006,7009,7012,7015,7018,7021,
		7001,7004,7007,7010,7013,7016,7019,7022,
		7002,7005,7008,7011,7014,7017,7020,7023
	};
	
	public static final int Q4_KILL_COUNT = 50;
	public static final int Q4_AGATHION_OF_LOVE = 13023;
	
	public static final String q4_0 = "<html><body><font color=LEVEL>Couple Manager</font><br> Something lost from my lovelly place. Monsters from dungeon get that thing but now is too late. Please can you and your lovelly partner can kill 50 monsters in Mission Dungeon for revenge.</body></html>";
	public static final String q4_1 = "<html><body><font color=LEVEL>Couple Manager</font><br> Ouchh you don't get revenge on that monsters. Please come back when you and your nice partner kill 50 monsters in Mission dungeon.</body></html>";
	public static final String q4_finished = "<html><body><font color=LEVEL>Couple Manager</font><br> Thanks to you and your partner for help in revenge - now you and your partner get special Agathion of Love for one month.</body></html>";
	
	/**
	 * Quest 5 - Missing Hearth
	 * @param Items
	 * @param Rewards
	 */
	public static final int Q5_MUSICAL_THEME_OF_LOVE = 4408;
	public static final int Q5_ECHO_OF_LOVE = 4413;
	public static final int Q5_SONG_COUNT = 50;
	public static final int Q5_TEDDY_BOY_AGATHION = 14059;
	public static final int Q5_TEDDY_GIRL_AGATHION = 14060;
	
	public static final String q5_0 = "<html><body><font color=LEVEL>Couple Manager</font><br> I found nice music in my hearth but that music is writed on Musical Theme. I need <font color=LEVEL>50 Echo Crystals - Theme of Love</font>. Musical Theme you can get from monsters in mission dungeon. Then you must go create Echo Crystals in Dion Grocery.</body></html>";
	public static final String q5_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You dont get 50 Echo Crystals. Go for musical theme in mission dungeon and create Echo Crystals in Dion Grocery.</body></html>";
	public static final String q5_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Thanks for 50 Echo Crystals with Theme of Love. Take this reward for your help.</body></html>";
	
	/**
	 * Quest 6 - Kiss Me Please
	 * @param Items
	 * @param Rewards
	 */
	public static final int[] Q6_BAD_MONSTER = 
	{	
		22215,22216,22217
	};
	public static final int Q6_JUJU_AGATHION = 14776;
	
	public static final String q6_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>Always couples assume about a kiss. So only men can give word that he cen do everything for a kiss. So men must go and kill Tyrannosaurus for prove.</body></html>";
	public static final String q6_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>Ohh men don't kill Tyrannosaurus yet? Go ahead and kill that monster</html></body>";
	public static final String q6_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>You kill that beast so now i can reward you. Here your reward - for your love too</body></html>";
	
	/**
	 * Quest 7 - Pirate and Queen
	 * @param Monsters
	 * @param Rewards
	 */
	public static final int[] Q7_PIRATE = 
	{
		20836,20845
	};
	public static final int[] Q7_ANT = 
	{
		20084,20087	
	};
	public static final int Q7_PIRATE_COUNT = 300;
	public static final int Q7_ANT_COUNT = 300;
	
	public static final int Q7_QUEEN_ANT_RING = 20206;
	public static final int Q7_ZAKEN_EARRING = 20209;
	
	public static final String q7_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I need help with queen guards and pirate guards. Male Partner must kill 300 Pirates Zombie or Pirate Zombie Captains, and female must kill 300 Ant Patrols or Ant Guards.</body></html>";
	public static final String q7_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You don't kill 300 monsters that you must. Male must kill 300 of Pirate Zombie or Pirate Zombie Captain, and female 300 Ant Patrols or Ant Guards</body></html>";
	public static final String q7_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Now i can go to Great Queen and Biggest Pirate in the Aden world. Thank you, this is your rewards.</body></html>";
	
	/**
	 * Quest 8 - Love Adventure
	 * @param Rewards
	 * @param Items
	 */
	public static final int Q8_RED_SOUL_CRYSTAL_5 = 4634;
	public static final int Q8_BLUE_SOUL_CRYSTAL_5 = 4656;
	public static final int Q8_GREEN_SOUL_CRYSTAL_5 = 4645;
	
	public static final int Q8_STEAM_BRACELET = 20535;
	public static final int Q8_LION_BRACELET = 20536;
	
	public static final String q8_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I need 3 types of crystals for special jewelery. When you get crystals i give you for : <br> 1 Red Soul Crystal - Stage 5 and 1 Green Soul Crystal - Stage 5  == Steam Bracelet <br> 1 Blue Soul Crystal - Stage 5 and 1 Green Soul Crystal - Stage 5 == Tawned Lion Bracelet. Please find for me that crystals.</body></html>";
	public static final String q8_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You don't have any crystals so i cannot give you any rewards. (1 Red and 1 Green for Steam Bracelet or 1 Blue and 1 Green for Tawned Lion Bracelet)</body></html>";
	public static final String q8_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Ok i give you a bracelet so what do you want from me?</body></html>";
	
	/**
	 * Quest 9 - Love Adventure
	 * @param Monsters
	 * @param Rewards
	 * @param Items
	 */
	public static final int[] Q9_MOS_MONSTERS =
	{
		22129,22130,22131,22133,22134,22135
	};
	public static final int[] Q9_PAGAN_MONSTERS =
	{
		22167,22157,22168,22164,22161,22170
	};
	public static final int Q9_KILL_COUNT = 50;
	public static final int Q9_AD_AGATHION_F = 20821;
	public static final int Q9_AD_AGATHION_M = 20819;
	
	public static final String q9_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>Big adventure with love is waiting. You must help finish job of your partner. Male partner must go and kill 50 monsters at end of Monastery of Silence, but female must kill 50 monsters at end of Pagan Temple.</body></html>";
	public static final String q9_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>What you want? You don't kill all monsters. Male must kill 50 monsters at end of Monastery of Silence, female must kill 50 monsters at end of Pagan Temple.</body></html>";
	public static final String q9_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Nice work your adventure is completed. There is your reward. Thanks to you and your partner.</body></html>";
	
	/**
	 * Quest 10 - Burn My Wallet
	 * @param Items
	 * @param Mats
	 */
	public static final int Q10_MAGMA_DUST = 6022;
	public static final int Q10_MOON_DUST = 6023;
	public static final int Q10_MAGMA_DUST_COUNT = 2;
	public static final int Q10_MOON_DUST_COUNT = 2;
	
	public static final int Q10_ADENA = 57;
	public static final long Q10_ADENA_COUNT = 1000000;
	public static final int Q10_ANCIENT_ADENA = 5575;
	public static final long Q10_ANCIENT_ADENA_COUNT = 200000;
	
	public static final String q10_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I must prepare something. First i must get 2 Magma Dust and 2 Moon Dust. You and your partner must get this reagents to finish my dishes.</body></html>";
	public static final String q10_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You don't have required 2 Magma Dust and 2 Moon Dust for my dishes.</body></html>";
	public static final String q10_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Hmmmm ..... Delicious ... I think this dishes is now realy good.</body></html>";
	
	/**
	 * Quest 11 - Hot Partner
	 * @param Monsters
	 * @param Rewards
	 */
	public static final int[] Q11_FORGE_OF_THE_GODS_MONSTERS =
	{
		21391,21392,21393,21395,21657
	};
	public static final int Q11_KILL_COUNT = 45;
	
	public static final int Q11_EXP_REWARD = 3000000;
	public static final int Q11_SP_REWARD = 550000;
	
	public static final String q11_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I need help with fire monsters. You and your partner must kill 45 monsters in lower stage Forge of the Gods, then come back.</body></html>";
	public static final String q11_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You or your partner don't kill 45 monsters in lower stage of Forge of the Gods.</body></html>";
	public static final String q11_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Thank you for the fire power from Forge of the Gods. There is reward for you and your partner.</body></html>";
	
	/**
	 * Quest 12 - Energy Kiss
	 * @param Monsters
	 * @param Rewards
	 */
	public static final int[] Q12_IMPERIAL_TOMB_MONSTERS =
	{
		21407,21414,21418
	};
	
	public static final int Q12_KILL_COUNT = 15;
	
	public static final String q12_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>Please get energy souls from 15 monsters in Imperial Tomb lower stages.</body></html>";
	public static final String q12_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You and your partner don't kill 15 monsters in lower stage of Imperial Tomb.</body></html>.";
	public static final String q12_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Thank you for this energy souls, now i can prepare special hot kiss from recepture.</body></html>";
	
	/**
	 * Quest 13 - Write Your Feelings
	 * @param Monsters
	 * @param Rewards	  
	 */
	public static final int[] Q13_URSUS_MONSTERS = 
	{
		22095,22096
	};
	
	public static final int Q13_KILL_COUNT = 300;
	
	public static final int Q13_ADENA = 57;
	public static final long Q13_ADENA_COUNT = 400000;
	
	public static final String q13_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I need something to create a gold, but Ursus monsters have informations about gold mine. So you and your partner must kill 300 Ursus monsters in Frozen Labirynth.</body></html>";
	public static final String q13_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You or your partner must kill 300 monsters so finish this job first. Kill 300 Ursus Monsters in Frozen Labirynth.</body></html>";
	public static final String q13_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Thanks for informations about gold mine. There is yours adena. Bye....</body></html>";
	
	/**
	 * Quest 14 - Find Marriage Gift
	 * @param Items
	 * @param Rewards
	 */
	public static final int Q14_AGATHION_OF_LOVE = 20203;
	public static final int Q14_GEMSTONE_C = 2131;
	public static final int Q14_SPIRIT_BEAD = 8766;
	
	public static final String q14_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I need help. I create a special gift and i need 50 Gemstones C grade and 50 Spirit Bead from Seductive Whispers Quest. You and your partner must get this items, then i give you a special reward.</body></html>";
	public static final String q14_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>You or your partner don't have 50 Gemstones C grade and 50 Spirit Beads from Seductive Whispers Quest.</body></html>";
	public static final String q14_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Ohh this can be nice jewelery now. Thank you. There is reward for you and your partner</body></html>";
	
	/**
	 * Quest 15 - Summon Your Love
	 * @param Items
	 * @param Rewards
	 */
	public static final int Q15_COIN = 10639;
	public static final int Q15_SUMMON_LOVE_BRACELET = 20628;
	
	public static final String q15_0 = "<html><body><font color=LEVEL>Couple Manager</font><br>I know that you can summon to your love by using command .gotolove but if you and your partner pay me 150 Mouse Coins i give to you and your partner a special bracelet that can summon your partner, but you and your partner must be in party and have bracelet equipped.</body></html>";
	public static final String q15_1 = "<html><body><font color=LEVEL>Couple Manager</font><br>Ohh you or your partner don't have 150 Mouse Coins. Please come back to me with this coins, because i need this coins.</body></html>";
	public static final String q15_finished = "<html><body><font color=LEVEL>Couple Manager</font><br>Thank you for that. Remember you and your partner must have equipped that bracelet and only one of you can use summon skill</body></html>";
	
	public CoupleManagerConfig(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new CoupleManager(-1,"CoupleManagerConfig","Couple Manager");
		_log.warning("Couple Manager System: Configuration Loaded ...");
	}
}