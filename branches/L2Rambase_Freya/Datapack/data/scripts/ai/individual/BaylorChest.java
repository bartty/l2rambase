package ai.individual;

import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;

import ai.group_template.L2AttackableAIScript;

public class BaylorChest extends L2AttackableAIScript
{
	private final int CHEST = 29116;
	
	public BaylorChest(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addKillId(CHEST);
	}

	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		int npcId = npc.getNpcId();
		if (npcId == CHEST)
		{
			int chance = Rnd.get(100);
			if (chance <= 1)
			{	
				dropItem(npc,9470,1);
			}
			else if (chance >= 2 && chance <= 32)
			{
				dropItem(npc,6578,2);
			}
			else
			{
				dropItem(npc,6704,10);
			}
		}
		return super.onKill(npc, player, isPet);
	}

	private void dropItem(L2Npc npc, int itemId, int count) 
	{
		L2ItemInstance ditem = ItemTable.getInstance().createItem("Loot", itemId, count, null);
		ditem.dropMe(npc, npc.getX(), npc.getY(), npc.getZ());		
	}

	public static void main(String[] args)
	{
		new Baylor(-1, "baylorChest", "ai");
	}
}