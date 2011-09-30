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
package teleports.GatekeeperSpirit;

import l2.rambase.gameserver.SevenSigns;
import l2.rambase.gameserver.instancemanager.GrandBossManager;
import l2.rambase.gameserver.instancemanager.RaidBossSpawnManager;
import l2.rambase.gameserver.instancemanager.RaidBossSpawnManager.StatusEnum;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.zone.type.L2BossZone;

public class GatekeeperSpirit extends Quest
{
	private final static int npcId = 31111;
	private final static int Lilith = 25283;
	private final static int Anakim = 25286;
	private final static int[] DawnLoc = { 185061,-9612,-5492 };
	private final static int[] DuskLoc = { 185076,-12601,-5494 };
	
	private static L2BossZone _Zone;

	public GatekeeperSpirit(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(npcId);
		addFirstTalkId(npcId);
		addTalkId(npcId);
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
			st = newQuestState(player);
		String htmltext = "";
		int playerCabal = SevenSigns.getInstance().getPlayerCabal(player.getObjectId());
		int sealAvariceOwner = SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_AVARICE);
		int compWinner = SevenSigns.getInstance().getCabalHighestScore();

		if (playerCabal == sealAvariceOwner && playerCabal == compWinner)
		{
			switch (sealAvariceOwner)
			{
			case SevenSigns.CABAL_DAWN:
				htmltext = "dawn.htm";
				break;
			case SevenSigns.CABAL_DUSK:
				htmltext = "dusk.htm";
				break;
			case SevenSigns.CABAL_NULL:
				npc.showChatWindow(player);
				break;
			}
		}
		else
			npc.showChatWindow(player);

		return htmltext;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.equalsIgnoreCase("dawn"))
		{
			if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(Lilith) == StatusEnum.ALIVE)
			{
				_Zone = GrandBossManager.getInstance().getZone(DawnLoc[0],DawnLoc[1],DawnLoc[2]);
				_Zone.allowPlayerEntry(player, _Zone.getTimeInvade());			
				player.teleToLocation(DawnLoc[0],DawnLoc[1],DawnLoc[2]);
			}
			else
			{
				htmltext = "dawn-no.htm";
			}
		}
		if (event.equalsIgnoreCase("dusk"))
		{
			if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(Anakim) == StatusEnum.ALIVE)
			{
				_Zone = GrandBossManager.getInstance().getZone(DuskLoc[0],DuskLoc[1],DuskLoc[2]);
				_Zone.allowPlayerEntry(player, _Zone.getTimeInvade());
				player.teleToLocation(DuskLoc[0],DuskLoc[1],DuskLoc[2]);
			}
			else
			{
				htmltext = "dusk-no.htm";
			}
		}		
		return htmltext;
	}

	public static void main(String[] args)
	{
		new GatekeeperSpirit(-1, "GatekeeperSpirit", "teleports");
	}
}