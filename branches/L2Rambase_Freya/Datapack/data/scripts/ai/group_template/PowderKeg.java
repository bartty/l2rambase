package ai.group_template;


import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Rnd;


/**
 * Powder Keg's AI for Pailaka Devils Legacy
 * @author L2Rambase Project Project
 */
public class PowderKeg extends L2AttackableAIScript
{
	private static final int POWDERK = 18622;
	
	private static final int BOOM_CHANCE = 70;
	
	public PowderKeg(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addAttackId(POWDERK);
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("Boom"))
		{
			npc.setTarget(npc);
			npc.doCast(SkillTable.getInstance().getInfo(5714,1));
			npc.setCurrentHp(0);
			npc.decayMe();
		}
		return super.onAdvEvent(event, npc, player);
	}

	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet)
	{
		int npcId;
		npcId = npc.getNpcId();
		if (npcId == POWDERK && Rnd.get(100) < BOOM_CHANCE)
		{
			startQuestTimer("Boom", 3000, npc, player);			
		}
		return super.onAttack(npc, player, damage, isPet);
	}


    public static void main(String[] args)
    {
		new PowderKeg(-1,"powderkeg","ai");
    }
}