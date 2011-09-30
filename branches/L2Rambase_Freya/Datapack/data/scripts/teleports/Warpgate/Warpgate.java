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
package teleports.Warpgate;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.model.quest.QuestState;

public class Warpgate extends Quest
{
	private final static int[] npcIds =
	{
		32314, 32315, 32316, 32317, 32318, 32319
	};

	public Warpgate(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int id : npcIds)
		{
			addStartNpc(id);
			addFirstTalkId(id);
			addTalkId(id);
		}
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st1 = player.getQuestState("133_ThatsBloodyHot");
		QuestState st2 = player.getQuestState("130_PathToHellbound");
		int npcId = npc.getNpcId();
		htmltext = "warpgate-locked.htm";
		if (st1 != null)
		{
			if (npcId == 32314)
			{
				htmltext = "32314.htm";
			}
			else if (npcId == 32315)
			{
				htmltext = "32315.htm";
			}
			else if (npcId == 32316)
			{
				htmltext = "32316.htm";
			}
			else if (npcId == 32317)
			{
				htmltext = "32317.htm";
			}
			else if (npcId == 32317)
			{
				htmltext = "32317.htm";
			}
			else if (npcId == 32318)
			{
				htmltext = "32318.htm";
			}
			else if (npcId == 32319)
			{
				htmltext = "32319.htm";
			}
		}
		else if (st2 != null)
		{
			if (npcId == 32314)
			{
				htmltext = "32314.htm";
			}
			else if (npcId == 32315)
			{
				htmltext = "32315.htm";
			}
			else if (npcId == 32316)
			{
				htmltext = "32316.htm";
			}
			else if (npcId == 32317)
			{
				htmltext = "32317.htm";
			}
			else if (npcId == 32317)
			{
				htmltext = "32317.htm";
			}
			else if (npcId == 32318)
			{
				htmltext = "32318.htm";
			}
			else if (npcId == 32319)
			{
				htmltext = "32319.htm";
			}
		}
		return htmltext;
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st1 = player.getQuestState("133_ThatsBloodyHot");
		QuestState st2 = player.getQuestState("130_PathToHellbound");

		if (st1 != null)
		{
			if (st1.getState() == State.COMPLETED)
				player.teleToLocation(-11272, 236464, -3248);
			else
				htmltext = "warpgate-no.htm";
		}
		else if (st2 != null)
		{
			if (st2.getState() == State.COMPLETED)
				player.teleToLocation(-11272, 236464, -3248);
			else
				htmltext = "warpgate-no.htm";
		}
		else
			htmltext = "warpgate-no.htm";

		return htmltext;
	}

	public static void main(String[] args)
	{
		new Warpgate(-1, "Warpgate", "teleports");
	}
}