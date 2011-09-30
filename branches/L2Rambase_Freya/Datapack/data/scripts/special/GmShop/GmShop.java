/*
 * L2Rambase Project
 */
package special.GmShop;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class GmShop extends Quest
{
	private static final String qn = "GmShop";
	//NPCs
	private static final int GM_SHOP = 90002;
	//Spawns
	private static final int SpawnLocs[][] = {
		{ -80905,150680,-3046,52112 }, // Gludin
		{ -12830,122497,-3119,15162 }, // Gludio
		{ 15740,142748,-2708,14661 }, // Dion
		{ 83070,148397,-3464,32767 }, // Giran
		{ 111801,218948,-3536,24575 }, // Heine
		{ 82918,53093,-1488,22779 }, // Oren
		{ 116774,77439,-2702,44597 }, // Hunters
		{ 146378,25933,-2008,53706 }, // Aden
		{ 148066,-56699,-2776,28863 }, // Goddard
		{ 44311,-47640,-799,45409 }, // Rune
		{ 87751,-141915,-1336,33621 }, // Schuttgart
		{ 11850,-23753,-3640,37604 }  // Primevale Isle
	};
	//Check Value
	private static final boolean HR_STATE = false;	

	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("armor"))
		{
			htmltext = "armor.htm";
		}
		if (event.equalsIgnoreCase("weapon"))
		{
			htmltext = "weapon.htm";
		}
		if (event.equalsIgnoreCase("grocery"))
		{
			htmltext = "grocery.htm";	
		}
		if (event.equalsIgnoreCase("jewelery"))
		{
			htmltext = "jewelery.htm";	
		}
		if (event.equalsIgnoreCase("back"))
		{
			htmltext = "list.htm";	
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
			htmltext = "list.htm";
		}
		return htmltext;
	}
	public GmShop(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(GM_SHOP);
		addFirstTalkId(GM_SHOP);
		addTalkId(GM_SHOP);
		if (HR_STATE)
		{
			for (int i = 0; i < 12; i += 1)
			{
				addSpawn(GM_SHOP, SpawnLocs[i][0], SpawnLocs[i][1], SpawnLocs[i][2], SpawnLocs[i][3], false, 0, true);
			}
		}
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new GmShop(-1,qn,"special");
		_log.warning("High Rate System: Gm Shop loaded ...");
	}
}