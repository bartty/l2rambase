/**
 * @author L2Rambase Project Project
 */
package ai.group_template;

import l2.rambase.gameserver.instancemanager.KrateisCubeManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


import ai.group_template.L2AttackableAIScript;

public class KrateisCubeMobs extends L2AttackableAIScript
{
	private static final String qn = "KrateisCubeMobs";
	private static final int[] mobs = 
	{
		18579,18580,18581,18582,18583,18584,
		18585,18586,18587,18588,18589,18590,
		18591,18592,18593,18594,18595,18596,
		18597,18598,18599,18600,18602
	};

	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		KrateisCubeManager.getInstance().addKill(player);
		return super.onKill(npc, player, isPet);
	}
	
	public KrateisCubeMobs(int questId, String name, String descr)
	{
		super(questId, name, descr);
		this.registerMobs(mobs);
	}

	public static void main(String[] args)
	{
		new KrateisCubeMobs(-1, qn, "ai");
	}
}