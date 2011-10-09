/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package teleports.SeparatedSoul;

import l2.Rambase.Config;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;

public class SeparatedSoul extends Quest
{
	//NPCs
	private static final int[] SeparatedSouls = {32864,32865,32866,32867,32868,32869,32870,32891};
	private static final int WillOfAntharas       = 17266;
	private static final int SealedBloodCrystal   = 17267;
    private static final int AntharasBloodCrystal = 17268;
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		
		if (st == null)
			return getNoQuestMsg(player);
		
		if (event.equalsIgnoreCase("AntharasLair"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(131116,114333,-3704);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("AntharasLairDeep"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(148447,110582,-3944);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("AntharasLairMagicForceFieldBridge"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(146129,111232,-3568);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("DragonValley"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(73122,118351,-3714);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("DragonValleyCenter"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(99218,110283,-3696);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("DragonValleyNorth"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(116992,113716,-3056);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("DragonValleySouth"))
		{
			if (player.getLevel() >= 80)
				player.teleToLocation(113203,121063,-3712);
			else
				return "no-level.htm";
		}
		else if (event.equalsIgnoreCase("Synthesis"))
		{
			st.takeItems(WillOfAntharas, -1);
			st.takeItems(SealedBloodCrystal, -1);
			st.giveItems(AntharasBloodCrystal, 1);
		}
		else if (st.getQuestItemsCount(WillOfAntharas) == 0 && st.getQuestItemsCount(SealedBloodCrystal) == 0 )
		{
				return "no-items.htm";
		}
		else if (st.getQuestItemsCount(WillOfAntharas) == 1 && st.getQuestItemsCount(SealedBloodCrystal) == 0 )
		{
			    return "no-items.htm";
		}
		else if (st.getQuestItemsCount(WillOfAntharas) == 0 && st.getQuestItemsCount(SealedBloodCrystal) == 1 )
		{
			    return "no-items.htm";
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		
		if (st == null)
			return null;
		
		return htmltext;
	}
	
	public SeparatedSoul(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		for (int teleporters : SeparatedSouls)
		{
			addStartNpc(teleporters);
			addTalkId(teleporters);
		}
	}
	
	public static void main(String[] args)
	{
		new SeparatedSoul(-1, SeparatedSoul.class.getSimpleName(), "teleports");
		if (Config.ENABLE_LOADING_INFO_FOR_SCRIPTS)
			_log.info("Loaded Teleports: Separated Soul");
	}
}

