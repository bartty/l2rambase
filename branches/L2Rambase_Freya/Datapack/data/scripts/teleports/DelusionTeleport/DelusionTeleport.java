package teleports.DelusionTeleport;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class DelusionTeleport extends Quest
{
	private final static int REWARDER_ONE = 32658;
	private final static int REWARDER_TWO = 32659;
	private final static int REWARDER_THREE = 32660;
	private final static int REWARDER_FOUR = 32661;
	private final static int REWARDER_FIVE = 32663;
	private final static int REWARDER_SIX = 32662;
	private final static int START_NPC = 32484;
	private static String qn = "DelusionTeleport";

	public DelusionTeleport(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(START_NPC);
		addStartNpc(REWARDER_ONE);
		addStartNpc(REWARDER_TWO);
		addStartNpc(REWARDER_THREE);
		addStartNpc(REWARDER_FOUR);
		addStartNpc(REWARDER_FIVE);
		addStartNpc(REWARDER_SIX);
		addTalkId(START_NPC);
		addTalkId(REWARDER_ONE);
		addTalkId(REWARDER_TWO);
		addTalkId(REWARDER_THREE);
		addTalkId(REWARDER_FOUR);
		addTalkId(REWARDER_FIVE);
		addTalkId(REWARDER_SIX);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		int npcId = npc.getNpcId();
		if (npcId == START_NPC)
		{
			st.set("x", String.valueOf(player.getX()));  
			st.set("y", String.valueOf(player.getY()));
			st.set("z", String.valueOf(player.getZ()));
			player.teleToLocation(-114592,-152509,-6723);
			if (player.getPet() != null)
			player.getPet().teleToLocation(-114592,-152509,-6723);	
		}
		else if (npcId == REWARDER_ONE || npcId == REWARDER_TWO || npcId == REWARDER_THREE || npcId == REWARDER_FOUR || npcId == REWARDER_FIVE || npcId == REWARDER_SIX)
		{
			int x = 0;
			int y = 0;
			int z = 0;
			try
			{
				x = Integer.valueOf(st.get("x"));
				y = Integer.valueOf(st.get("y"));
				z = Integer.valueOf(st.get("z"));
				player.teleToLocation(x,y,z);
				if (player.getPet() != null)
					player.getPet().teleToLocation(x,y,z);
			}
			catch (Exception e)
			{
				x = 42713;
				y = -47952;
				z = -799;				
				player.teleToLocation(x,y,z);
				if (player.getPet() != null)
					player.getPet().teleToLocation(x,y,z);
			}
			st.exitQuest(true);
		} 
		return "";
	}
	public static void main(String[] args)
	{
		new DelusionTeleport(-1, "DelusionTeleport", "teleports");
	}
}