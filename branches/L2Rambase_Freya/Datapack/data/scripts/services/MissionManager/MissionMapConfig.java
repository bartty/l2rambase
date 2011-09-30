/**
 * MissionMap Config
 * @author L2Rambase Project
 * @version 5.0
 */
package services.MissionManager;

import l2.rambase.gameserver.model.quest.Quest;

public class MissionMapConfig extends Quest
{
	/**
	 * Text of Npc
	 * @param html
	 */
	public static final String MissionMaster = "<html><body><font color=LEVEL>Mission Master: </font><br>" +
	"Welcome to my training area. there is 3 types od Dungeon.<br><br>"+
	"<a action=\"bypass -h Quest MissionMap select\">Enter Solo to Dungeon</a><br>"+
	"<a action=\"bypass -h Quest MissionMap list_raid\">Enter in Party to Dungeon</a><br>"+
	"<a action=\"bypass -h Quest MissionMap list_strategy\">Enter in Strategic Mode</a><br>"+
	"<a action=\"bypass -h Quest MissionMap reward\">I have special coins</a></body></html>";
	/**
	 * 
	 */
	public static final int[] MISSION_SOLO_VIT = 
	{
		0,150,170,200,220,250,270,300,320
	};
	
	public static final int MISSION_RAID_EXP = 1000000;
	public static final int MISSION_RAID_SP = 300000;

	/**
	 * List of Monsters
	 * @param Solo,Raids Id and Solo Spawns 
	 */
	public static final int[][] Mission_Monsters = 
	{ 
		{ 0,7000,7003,7006,7009,7012,7015,7018,7021 },
		{ 0,7001,7004,7007,7010,7013,7016,7019,7022 },
		{ 0,7002,7005,7008,7011,7014,7017,7020,7023 }
	};
	public static final int[][] Mission_Spawns =
	{
		{ 48770, -219304, -8759 },
		{ 49036, -220190, -8759 },
		{ 49363, -219814, -8759 },
		{ 49393, -219102, -8759 },
		{ 49618, -220490, -8759 },
		{ 48526, -220493, -8759 }
	};
	public static final int[][] Mission_Raid_Spawns =
	{
			//x,y,z
			{-19177,15369,-4904},{-19169,14732,-4904},{-19100,17910,-4904},{-18151,17435,-4906},
			{-17944,16724,-4906},{-17786,15520,-4904},{-17723,17784,-4904},{-17671,14619,-4904},
			{-17274,13729,-4927},{-16506,13961,-4927},{-16391,16057,-4906},{-16021,16385,-4906},
			{-15435,14593,-4927},{-15194,15137,-4906},{-14902,15324,-4906},{-14678,18096,-4904},
			{-14484,13269,-4904},{-13525,16255,-4904},{-13407,17912,-4904},{-13390,13266,-4904},
			{-13193,15474,-4906},{-11757,15885,-4906},{-11505,18233,-4906},{-11342,15572,-4925},
			{-11286,13586,-4904},{-10888,15684,-4927},{-10637,13271,-4906},{-10511,17985,-4906},
			{-8779,14396,-4904},{-8777,17916,-4904},{-8586,13507,-4904},{-13454,21984,-4904},
			{-13401,23606,-4904},{-13399,19027,-4925},{-12961,20627,-4904},{-12434,20401,-4906},
			{-12369,22394,-4906},{-12321,20802,-4906},{-11870,21888,-4904},{-10582,23132,-4906},
			{-10270,23468,-4906},{-9611,21695,-4904},{-9407,21466,-4906},{-9064,23817,-4904},
			{-8943,16110,-4906},{-8782,19092,-4904},{-8666,16557,-4904},{-19334,18089,-4904},
			{-19020,15803,-4904},{-18903,15053,-4906},{-17949,14828,-4925},{-17816,15932,-4904},
			{-17804,17446,-4904},{-17695,18160,-4906},{-17688,16770,-4906},{-16979,13717,-4904},
			{-16252,13698,-4906},{-16022,16020,-4906},{-15884,15064,-4906},{-15439,16133,-4904},
			{-15229,14888,-4904},{-14478,13709,-4904},{-14377,14912,-4904},{-14308,18112,-4904},
			{-13423,15776,-4904},{-13403,13691,-4904},{-13388,18269,-4904},{-13208,16178,-4906},
			{-12200,15493,-4904},{-11385,15489,-4906},{-10978,17909,-4906},{-10968,15411,-4906},
			{-10751,13659,-4906},{-10618,13518,-4904},{-9056,13506,-4904},{-8794,18296,-4904},
			{-8766,14782,-4904},{-13573,19193,-4904},{-13180,23811,-4904},{-12980,20423,-4906},
			{-12888,21791,-4906},{-12683,22329,-4906},{-12639,20708,-4904},{-12249,20532,-4906},
			{-12006,21985,-4904},{-10387,22700,-4904},{-10258,23131,-4904},{-9936,21760,-4904},
			{-9013,19191,-4904},{-8873,21630,-4904},{-8833,15811,-4904},{-8773,23548,-4904},
			{-8726,16767,-4904},{-19253,15587,-4906},{-19170,15028,-4904},{-19082,18271,-4904},
			{-18154,14656,-4904},{-17996,18010,-4906},{-17992,15701,-4904},{-17888,16908,-4904},
			{-17419,17529,-4906},{-16935,13960,-4906},{-16471,16332,-4904},{-16239,13948,-4906},
			{-15888,14834,-4906},{-15547,16321,-4904},{-14946,14848,-4906},{-14760,15030,-4904},
			{-14480,18299,-4904},{-14226,13498,-4904},{-13579,15927,-4906},{-13215,15705,-4906},
			{-13211,18108,-4904},{-13197,13509,-4904},{-11705,15405,-4906},{-11332,13378,-4927},
			{-11244,15267,-4906},{-11136,18034,-4904},{-10830,17926,-4906},{-10708,15476,-4906},
			{-10282,13504,-4904},{-8765,13728,-4904},{-8634,18101,-4904},{-8546,14590,-4904},
			{-13592,23808,-4904},{-13476,20417,-4906},{-13181,19204,-4904},{-13167,21770,-4906},
			{-12561,22034,-4904},{-12367,20297,-4906},{-12272,22175,-4906},{-11928,20756,-4904},
			{-10556,22978,-4906},{-10525,23663,-4906},{-9643,21470,-4906},{-9248,21907,-4906},
			{-8923,16280,-4906},{-8773,24014,-4904},{-8604,19208,-4904},{-8581,16266,-4927},
			{-18967,14774,-4906},{-18933,18110,-4904},{-18855,15370,-4906},{-18142,17264,-4906},
			{-17928,14482,-4904},{-17689,17118,-4927},{-17631,15702,-4904},{-17433,17783,-4906},
			{-17250,13918,-4904},{-16720,13710,-4904},{-16187,16407,-4906},{-15778,16276,-4904},
			{-15634,14556,-4906},{-15578,14974,-4904},{-14735,13504,-4904},{-14650,15310,-4927},
			{-14478,17933,-4904},{-13638,13501,-4904},{-13596,18100,-4904},{-13449,15489,-4904},
			{-13320,16462,-4904},{-12274,15697,-4906},{-11502,15849,-4906},{-11497,15216,-4906},
			{-11488,18014,-4904},{-11081,13356,-4906},{-10563,13714,-4906},{-10499,18229,-4906},
			{-9027,18112,-4904},{-8981,14586,-4904},{-8775,13306,-4904},{-13392,19374,-4904},
			{-13384,24002,-4904},{-13382,20716,-4925},{-13033,22051,-4904},{-12624,20238,-4906},
			{-12306,21787,-4927},{-11924,20577,-4904},{-11892,22154,-4927},{-10488,23398,-4906},
			{-10258,22966,-4906},{-9877,21537,-4906},{-8957,16697,-4906},{-8828,21921,-4906},
			{-8819,19394,-4904},{-8576,15936,-4927},{-8565,23808,-4904}
	};
	public static final int[][] Strategy_Spawns =
	{
		{ -213853,245304,2040 },{ -213860,244489,2033 },{ -213039,244491,2040 },{-213087,245316,2040 }, // Center Defense
		{ -213439,245150,2020 },{ -213765,244919,2025 },{ -213472,244526,2032 },{-213145,244875,2027 }, // Special Defense
		{ -212859,244674,2037 },{ -212834,245216,2037 },{ -214146,245225,2037 },{-214147,244572,2037 }  // Left and Right Defense
	};
	/**
	 * Reward List
	 * @param items
	 */
	public static final int Special_Coin = 10639;
	public static final long Coin_Reward_Raid = 250;
	public static final long Coin_Reward_Strategy = 50;
	public static final long Coin_Reward = 1;
	
	public static final int Strategy_Exp = 2500000;
	public static final int Strategy_Sp = 750000;
	public static final int Strategy_Chance = 33;
	public static final int Strategy_Low_Chance = 15;

	public static final int Enchant_Armor_C = 952;
	public static final int Enchant_Armor_B = 948;
	public static final int Enchant_Armor_A = 730;
	public static final int Enchant_Armor_S = 960;
	public static final int Enchant_Weapon_C = 951;
	public static final int Enchant_Weapon_B = 947;
	public static final int Enchant_Weapon_A = 729;
	public static final int Enchant_Weapon_S = 959;
	
	public static final int Top_Life_Stone_C = 8753;
	public static final int Top_Life_Stone_B = 8756;
	public static final int Top_Life_Stone_A = 8760;
	public static final int Top_Life_Stone_S = 8762;	
	/**
	 * Location List
	 * @param x y z
	 */
	public static final int[] MissionSoloLoc = { 48137, -219716, -8759 };
	public static final int[] MissionRaidLoc = { -10018,19193,-4896 }; // End of Dark Omen
	public static final int[] raidLoc = { -19068,13495,-4907 }; // Enter of Dark Omen
	public static final int[] MissionStrategyLoc = { -213456,246395,1997 };//Rim Pailaka - Rune
	public static final int[] raidStrategyLoc = 
	{ 
		-213459,244894,2017 // Arieal Cleft
	}; 	
	public static final int[] GiranLoc = { 83385,148626,-3400 };
	/**
	 * Spawn Time of Dungeons
	 * @param miliseconds
	 */
	public static final int SOLO_SPAWN_TIME = 30000;
	public static final int RAID_SPAWN_TIME = 15000;
	public static final int STRATEGY_SPAWN_TIME = 30000;
	/**
	 * Re Enter time of Dungeons
	 * @param minutes
	 * @param Solo is a 1 hour
	 * @param Party is a 5 hours
	 * @param Strategy is a 10 hours
	 */
	public static final int SOLO_RE_ENTER_TIME = 60;
	public static final int RAID_RE_ENTER_TIME = 300;
	public static final int STRATEGY_RE_ENTER_TIME = 600;
	/**
	 * Raid Mobs List
	 * @param npcId
	 */
	public static final int[][] Mobs =
	{
		{ 0,0,0,0 },             // Null Line
		{ 7009,7010,7011,7024 }, // Levels 40-49 - Last is Raid
		{ 7012,7013,7014,7025 }, // Levels 50-59 - Last is Raid
		{ 7015,7016,7017,7026 }, // Levels 60-69 - Last is Raid
		{ 7018,7019,7020,7027 }, // Levels 70-79 - Last is Raid
		{ 7021,7022,7023,7028 }, // Levels 80+   - Last is Raid
	};
	/**
	 * Strategy Mobs List
	 * @param npcId
	 */
	public static final int[][] StrategyMobs_1 =
	{
		{ 0,0,0,0 },             // Null Line
		{ 7009,7010,7011,7029 }, // Levels 40-49 - Last is Raid
		{ 7012,7013,7014,7030 }, // Levels 50-59 - Last is Raid
		{ 7015,7016,7017,7031 }, // Levels 60-69 - Last is Raid
		{ 7018,7019,7020,7032 }, // Levels 70-79 - Last is Raid
		{ 7021,7022,7023,7033 }, // Levels 80+   - Last is Raid
	};
	public static final int[][] StrategyMobs_2 =
	{
		{ 0,0,0,0 },             // Null Line
		{ 7009,7010,7011,7034 }, // Levels 40-49 - Last is Raid
		{ 7012,7013,7014,7035 }, // Levels 50-59 - Last is Raid
		{ 7015,7016,7017,7036 }, // Levels 60-69 - Last is Raid
		{ 7018,7019,7020,7037 }, // Levels 70-79 - Last is Raid
		{ 7021,7022,7023,7038 }, // Levels 80+   - Last is Raid
	};
	public static final int[][] StrategyMobs_3 =
	{
		{ 0,0,0,0 },             // Null Line
		{ 7009,7010,7011,7039 }, // Levels 40-49 - Last is Raid
		{ 7012,7013,7014,7040 }, // Levels 50-59 - Last is Raid
		{ 7015,7016,7017,7041 }, // Levels 60-69 - Last is Raid
		{ 7018,7019,7020,7042 }, // Levels 70-79 - Last is Raid
		{ 7021,7022,7023,7043 }, // Levels 80+   - Last is Raid
	};
	public static final int[][] StrategyMobs_4 =
	{
		{ 0,0,0,0 },             // Null Line
		{ 7009,7010,7011,7044 }, // Levels 40-49 - Last is Raid
		{ 7012,7013,7014,7045 }, // Levels 50-59 - Last is Raid
		{ 7015,7016,7017,7046 }, // Levels 60-69 - Last is Raid
		{ 7018,7019,7020,7047 }, // Levels 70-79 - Last is Raid
		{ 7021,7022,7023,7048 }, // Levels 80+   - Last is Raid
	};
	/**
	 * Specyfic configuration
	 * @param Party Raid Configuration
	 */
	public static final int MIN_RAID_PARTY_MEMBER_COUNT = 2;
	public static final int MAX_RAID_PARTY_MEMBER_COUNT = 6;
	
	
	public MissionMapConfig(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new MissionMapConfig(-1,"MissionMapConfig","Mission Map");
		_log.warning("Mission Map Dungeons System: Configuration Loaded ...");
	}
}