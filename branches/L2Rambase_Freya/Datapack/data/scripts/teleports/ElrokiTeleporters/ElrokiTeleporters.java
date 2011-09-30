package teleports.ElrokiTeleporters;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;

public class ElrokiTeleporters extends Quest
{
	private final static String qn = "6111_ElrokiTeleporters";
	
	public ElrokiTeleporters(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(32111);
		addTalkId(32111);
		addStartNpc(32112);
		addTalkId(32112);
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		int npcId = npc.getNpcId();
		if (npcId == 32111)
		{
			if (player.isInCombat())
			{
				htmltext = "<html><body>Orahochin<br>";
				htmltext += "Oh ho -- you have sought me out while battling the dinosaurs, have you?";
				htmltext += "And now you ask me to teleport you to that place?";
				htmltext += "That island was created as a sancturary; it is my duty to protect its inhabitants from external harm.";
				htmltext += "I certainly have no intention of making that place a refuge for reckless adventurers!";
				htmltext += "It is my policy to not teleport any who are engaged in battle, so return after the battle has ended.";
				htmltext += "</body></html>";
				return htmltext;
			}
			else
			{
				player.teleToLocation(4990,-1879,-3178);
			}
		}
	    if (npcId == 32112)
	    {
	        player.teleToLocation(7557,-5513,-3221);
	    }
		return htmltext;
	}

	public static void main(String[] args)
	{
		new ElrokiTeleporters(-1, qn, "teleports");
	}
}