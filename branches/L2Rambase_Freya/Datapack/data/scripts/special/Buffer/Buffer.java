/*
 * L2Rambase Project
 */
package special.Buffer;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class Buffer extends Quest
{
	private static final String qn = "Buffer";
	
	//Spawns
	private static final int SpawnLocs[][] = {
		{ -80360,149793,-3052,27353 }, // Gludin
		{ -12638,122495,-3119,15162 }, // Gludio
		{ 15539,142787,-2696,12366 }, // Dion
		{ 83054,148841,-3471,31613 }, // Giran
		{ 110974,218934,-3545,4653 }, // Heine
		{ 82508,53187,-1498,7008 }, // Oren
		{ 117043,77085,-2695,39893 }, // Hunters
		{ 146228,25762,-2008,61706 }, // Aden
		{ 148105,-56843,-2783,30402 }, // Goddard
		{ 44318,-48386,-799,18548 }, // Rune
		{ 87688,-142082,-1336,35323 }, // Schuttgart
		{ 11904,-23869,-3645,36206 },  // Primevale Isle
		{ 151210,46571,-3410,18664 }, //Colliseum 1
		{ 147787,46564,-3410,13828 } //Colliseum 2		
	};
	/*
	 * Config Event State 
	 */
	private static final boolean HR_STATE = false;	
	//NPCs
	private static final int BUFFER = 90001;
	//Cond
	private static final int ADENA = 57;
	private static final int BUFF_COST = 15000;
	private static final int FIGHTER_COST = 390000;
	private static final int MAGE_COST = 345000;
	private static final int REGEN_COST = 50000;
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("bd"))
		{
			htmltext = "bd.htm";
		}
		if (event.equalsIgnoreCase("es"))
		{
			htmltext = "es.htm";
		}
		if (event.equalsIgnoreCase("prophet"))
		{
			htmltext = "prophet.htm";
		}
		if (event.equalsIgnoreCase("resists"))
		{
			htmltext = "resists.htm";
		}
		if (event.equalsIgnoreCase("se"))
		{
			htmltext = "se.htm";
		}
		if (event.equalsIgnoreCase("special"))
		{
			htmltext = "special.htm";
		}
		if (event.equalsIgnoreCase("start"))
		{
			htmltext = "start.htm";
		}
		if (event.equalsIgnoreCase("sws"))
		{
			htmltext = "sws.htm";
		}
		if (event.equalsIgnoreCase("wc"))
		{
			htmltext = "wc.htm";
		}
		if (event.equalsIgnoreCase("ws"))
		{
			htmltext = "ws.htm";
		}
		// Skill: Shield lvl 3
		if (event.equalsIgnoreCase("1040"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Might lvl 3
		if (event.equalsIgnoreCase("1068"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1068,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Mental Shield lvl 4
		if (event.equalsIgnoreCase("1035"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Holy Weapon lvl 1
		if (event.equalsIgnoreCase("1043"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1043,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}	
		}
		// Skill: Regeneration lvl 3
		if (event.equalsIgnoreCase("1044"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1044,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}			
		}
		// Skill: Kiss of Eva lvl 2
		if (event.equalsIgnoreCase("1073"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1073,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}	
		}
		// Skill: Focus lvl 3
		if (event.equalsIgnoreCase("1077"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1077,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}
		}
		// Skill: Concentration lvl 6
		if (event.equalsIgnoreCase("1078"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1078,6).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}
		}
		// Skill: Acumen lvl 3
		if (event.equalsIgnoreCase("1085"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1085,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}
		}
		// Skill: Wind Walk lvl 2
		if (event.equalsIgnoreCase("1204"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}	
		}
		// Skill: Invigor lvl 3
		if (event.equalsIgnoreCase("1032"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1032,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
					htmltext = "no-adena.htm";
			}
		}
		// Skill: Magic Barrier lvl 2
		if (event.equalsIgnoreCase("1036"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Bless the Body lvl 6
		if (event.equalsIgnoreCase("1045"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Bless the Soul lvl 6
		if (event.equalsIgnoreCase("1048"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";
			}	
		}
		// Skill: Haste lvl 2
		if (event.equalsIgnoreCase("1086"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1086,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";				
			}
		}
		// Skill: Guidance lvl 3
		if (event.equalsIgnoreCase("1240"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Death Whisper lvl 3
		if (event.equalsIgnoreCase("1242"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1242,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}	
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Bless Shield lvl 6
		if (event.equalsIgnoreCase("1243"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1243,6).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Resist Fire lvl 3
		if (event.equalsIgnoreCase("1191"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1191,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Resist Poison lvl 3
		if (event.equalsIgnoreCase("1033"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1033,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Resist Aqua lvl 3
		if (event.equalsIgnoreCase("1182"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1182,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Resist Wind lvl 3
		if (event.equalsIgnoreCase("1189"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1189,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Holy Resistance lvl 3
		if (event.equalsIgnoreCase("1392"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1392,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Unholy Resistance lvl 3
		if (event.equalsIgnoreCase("1393"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1393,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Elemental Protection lvl 1
		if (event.equalsIgnoreCase("1352"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1352,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "resists.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Greater Might lvl 3
		if (event.equalsIgnoreCase("1388"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1388,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "special.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Greater Shield lvl 3
		if (event.equalsIgnoreCase("1389"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1389,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "special.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Prophecy of Fire lvl 1
		if (event.equalsIgnoreCase("1356"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1356,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "special.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Berserker Spirit lvl 2
		if (event.equalsIgnoreCase("1062"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1062,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "special.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Agility lvl 3
		if (event.equalsIgnoreCase("1087"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1087,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Decrease Weight lvl 3
		if (event.equalsIgnoreCase("1257"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1257,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Resist Shock lvl 4
		if (event.equalsIgnoreCase("1259"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1259,4).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Wild Magic lvl 2
		if (event.equalsIgnoreCase("1303"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1303,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Advanced Block lvl 3
		if (event.equalsIgnoreCase("1304"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1304,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Clarity lvl 3
		if (event.equalsIgnoreCase("1397"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1397,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Divine Protection lvl 1
		if (event.equalsIgnoreCase("1353"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1353,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Arcane Protection lvl 1
		if (event.equalsIgnoreCase("1354"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1354,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Prophecy of Water lvl 1
		if (event.equalsIgnoreCase("1355"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1355,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Mana Gain lvl 1
		if (event.equalsIgnoreCase("1460"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1460,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "es.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Empower lvl 3
		if (event.equalsIgnoreCase("1059"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1059,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "se.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Vampiric Rage lvl 4
		if (event.equalsIgnoreCase("1268"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1268,4).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "se.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Prophecy of Wind lvl 1
		if (event.equalsIgnoreCase("1357"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1357,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "se.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Earth lvl 1
		if (event.equalsIgnoreCase("264"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Life lvl 1
		if (event.equalsIgnoreCase("265"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(265,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Water lvl 1
		if (event.equalsIgnoreCase("266"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(266,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Warding lvl 1
		if (event.equalsIgnoreCase("267"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Wind lvl 1
		if (event.equalsIgnoreCase("268"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Hunter lvl 1
		if (event.equalsIgnoreCase("269"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(269,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Invocation lvl 1
		if (event.equalsIgnoreCase("270"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(270,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Vitality lvl 1
		if (event.equalsIgnoreCase("304"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Vengeance lvl 1
		if (event.equalsIgnoreCase("305"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(305,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Flame Guard lvl 1
		if (event.equalsIgnoreCase("306"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(306,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}			
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Storm Guard lvl 1
		if (event.equalsIgnoreCase("308"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(308,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Renewal lvl 1
		if (event.equalsIgnoreCase("349"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Meditation lvl 1
		if (event.equalsIgnoreCase("363"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(363,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Champion lvl 1
		if (event.equalsIgnoreCase("364"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(364,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Song of Elemental lvl 1
		if (event.equalsIgnoreCase("529"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(529,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "sws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of the Warrior lvl 1
		if (event.equalsIgnoreCase("271"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(271,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Inspiration lvl 1
		if (event.equalsIgnoreCase("272"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(272,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of the Mystic lvl 1
		if (event.equalsIgnoreCase("273"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(273,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Fire lvl 1
		if (event.equalsIgnoreCase("274"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(274,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Fury lvl 1
		if (event.equalsIgnoreCase("275"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(275,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Concentration lvl 1
		if (event.equalsIgnoreCase("276"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(276,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Light lvl 1
		if (event.equalsIgnoreCase("277"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(277,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Aqua Guard lvl 1
		if (event.equalsIgnoreCase("307"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(307,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Earth Guard lvl 1
		if (event.equalsIgnoreCase("309"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(309,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of the Vampire lvl 1
		if (event.equalsIgnoreCase("310"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(310,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Protection lvl 1
		if (event.equalsIgnoreCase("311"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(310,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Siren's Dance lvl 1
		if (event.equalsIgnoreCase("365"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(365,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Shadows lvl 1
		if (event.equalsIgnoreCase("366"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(366,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Dance of Alignment lvl 1
		if (event.equalsIgnoreCase("530"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(530,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "bd.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Sharp Edge lvl 1
		if (event.equalsIgnoreCase("825"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(825,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Spike lvl 1
		if (event.equalsIgnoreCase("826"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(826,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Restring lvl 1
		if (event.equalsIgnoreCase("827"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(827,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Case Harden lvl 1
		if (event.equalsIgnoreCase("828"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(828,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Hard Tanning lvl 1
		if (event.equalsIgnoreCase("829"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(829,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Embroider lvl 1
		if (event.equalsIgnoreCase("830"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(830,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "ws.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Battle lvl 3
		if (event.equalsIgnoreCase("1007"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1007,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Shielding lvl 3
		if (event.equalsIgnoreCase("1009"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1009,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Flame Chant lvl 3
		if (event.equalsIgnoreCase("1002"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1002,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Fire lvl 3
		if (event.equalsIgnoreCase("1006"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1006,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Fury lvl 2
		if (event.equalsIgnoreCase("1251"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1251,2).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Evasion lvl 3
		if (event.equalsIgnoreCase("1252"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1252,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Rage lvl 3
		if (event.equalsIgnoreCase("1253"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1253,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Revenge lvl 3
		if (event.equalsIgnoreCase("1284"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1284,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Predator lvl 3
		if (event.equalsIgnoreCase("1308"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1308,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Eagle lvl 3
		if (event.equalsIgnoreCase("1309"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1309,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Vampire lvl 4
		if (event.equalsIgnoreCase("1310"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1310,4).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Spirit lvl 1
		if (event.equalsIgnoreCase("1362"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1362,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: War Chant lvl 3
		if (event.equalsIgnoreCase("1390"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1390,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Earth Chant lvl 3
		if (event.equalsIgnoreCase("1391"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1391,3).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Magnus' Chant lvl 1
		if (event.equalsIgnoreCase("1413"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1413,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Protection lvl 1
		if (event.equalsIgnoreCase("1461"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1461,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		// Skill: Chant of Victory lvl 1
		if (event.equalsIgnoreCase("1363"))
		{
			if (st.getQuestItemsCount(ADENA)>=BUFF_COST)
			{
				SkillTable.getInstance().getInfo(1363,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "wc.htm";
				st.takeItems(ADENA, BUFF_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		if (event.equalsIgnoreCase("fighter"))
		{
			if (st.getQuestItemsCount(ADENA)>=FIGHTER_COST)
			{
				// Fighter Skills			
				SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1068,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1077,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1268,4).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1086,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1242,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1388,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1356,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(271,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(274,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(275,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(310,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(265,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(269,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(364,1).getEffects(st.getPlayer(),st.getPlayer());
				htmltext = "start.htm";	
				st.takeItems(ADENA, FIGHTER_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		if (event.equalsIgnoreCase("mage"))
		{
			if (st.getQuestItemsCount(ADENA)>=MAGE_COST)
			{
				// Mage Skills
				SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1059,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1078,6).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1085,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1303,2).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1389,3).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1460,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(273,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(276,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(265,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(363,1).getEffects(st.getPlayer(),st.getPlayer());
				SkillTable.getInstance().getInfo(1413,1).getEffects(st.getPlayer(),st.getPlayer());				
				htmltext = "start.htm";
				st.takeItems(ADENA, MAGE_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		if (event.equalsIgnoreCase("regen"))
		{
			if (st.getQuestItemsCount(ADENA)>=REGEN_COST)
			{
				// regeneration HP, MP and CP
				st.getPlayer().setCurrentHp(player.getMaxHp());
				st.getPlayer().setCurrentMp(player.getMaxMp());
				st.getPlayer().setCurrentCp(player.getMaxCp());
				htmltext = "start.htm";
				st.takeItems(ADENA, REGEN_COST);
			}
			else
			{
				htmltext = "no-adena.htm";
			}
		}
		if (event.equalsIgnoreCase("pfighter"))
		{
			if (player.getPet() != null)
			{
				if (st.getQuestItemsCount(ADENA)>=FIGHTER_COST)
				{
					// Summons or pets - fighter buffs
					SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1068,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1077,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1268,4).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1086,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1242,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1388,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1356,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(271,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(274,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(275,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(310,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(265,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(269,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(364,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					htmltext = "start.htm";
					st.takeItems(ADENA, FIGHTER_COST);
				}
				else
				{
					htmltext = "no-adena.htm";
				}
			}
			else
			{
				htmltext = "petneeded.htm";
			}
		}
		if (event.equalsIgnoreCase("pmage"))
		{
			if (player.getPet() != null)
			{
				if (st.getQuestItemsCount(ADENA)>=MAGE_COST)
				{
					// Summons or pets - mage buffs
					SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1059,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1078,6).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1085,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1303,2).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1389,3).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1460,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(273,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(276,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(265,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(363,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					SkillTable.getInstance().getInfo(1413,1).getEffects(st.getPlayer().getPet(),st.getPlayer().getPet());
					htmltext = "start.htm";
					st.takeItems(ADENA, MAGE_COST);
				}
				else
				{
					htmltext = "no-adena.htm";
				}				
			}
			else
			{
				htmltext = "petneeded.htm";
			}
		}
       	return htmltext;
	}
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		String htmltext = "";
		if (HR_STATE)
		{
			htmltext = "start.htm";
		}
		return htmltext;
	}
	public Buffer(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(BUFFER);
		addFirstTalkId(BUFFER);
		addTalkId(BUFFER);
		if (HR_STATE)
		{
			for (int i = 0; i < 12; i += 1)
			{
				addSpawn(BUFFER, SpawnLocs[i][0], SpawnLocs[i][1], SpawnLocs[i][2], SpawnLocs[i][3], false, 0, true);
			}
		}		
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new Buffer(-1,qn,"special");
		_log.warning("High Rate System: Buffer Implementation Loaded ");
	}
}