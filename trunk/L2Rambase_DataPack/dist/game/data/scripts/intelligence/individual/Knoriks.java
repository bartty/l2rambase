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
package intelligence.individual;

import l2.Rambase.gameserver.ai.CtrlIntention;
import l2.Rambase.gameserver.model.L2CharPosition;
import l2.Rambase.gameserver.model.actor.L2Attackable;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import intelligence.group_template.L2AttackableAIScript;

/*
 * Part: 1/7 - Knoriks
 */

public class Knoriks extends L2AttackableAIScript
{
	private static final int KNORIKS = 22857;
	
	final private static String qn = "AntaharasLairWalkers_Knoriks_Part_1";
	
	private L2Npc knoriks1;
	
	private static final L2CharPosition knoriks1_1  = new L2CharPosition(148419, 111967, -3725, 0);//1*
	private static final L2CharPosition knoriks1_2  = new L2CharPosition(149207, 114197, -3725, 0);//2*
	private static final L2CharPosition knoriks1_3  = new L2CharPosition(148917, 115610, -3725, 0);//3*
	private static final L2CharPosition knoriks1_4  = new L2CharPosition(147112, 116543, -3704, 0);//4*
	private static final L2CharPosition knoriks1_5  = new L2CharPosition(144576, 114674, -3716, 0);//5*
	private static final L2CharPosition knoriks1_6  = new L2CharPosition(145714, 112653, -3728, 0);//6*
	private static final L2CharPosition knoriks1_7  = new L2CharPosition(148419, 111967, -3725, 0);//7*
	
	public Knoriks(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addKillId(KNORIKS);
		addAggroRangeEnterId(KNORIKS);
		startQuestTimer("knoriks1_spawn", 1000, null, null);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("knoriks1_spawn") && knoriks1 == null)
		{
			knoriks1 = addSpawn(KNORIKS, 85137, 111995, -3083, 0, false, 0);
			knoriks1.setIsNoRndWalk(true);
			knoriks1.setRunning();
			startQuestTimer("knoriks1_move1", 15000, knoriks1, null);
		}
		else if (event.equalsIgnoreCase("knoriks1_move1") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move1", 60000, knoriks1, null);
			else
			{
				if (!knoriks1.isInsideRadius(knoriks1_1.x, knoriks1_1.y, knoriks1_1.z, 100, true, false))
					knoriks1.teleToLocation(knoriks1_1.x, knoriks1_1.y, knoriks1_1.z);
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_2);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move15", 13000, knoriks1, null);
			}
		}
		else if (event.equalsIgnoreCase("knoriks1_move15") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move15", 60000, knoriks1, null);
			else
			{
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_3);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move2", 9000, knoriks1, null);
			}
		}
		else if (event.equalsIgnoreCase("knoriks1_move2") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move2", 60000, knoriks1, null);
			else
			{
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_4);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move3", 12000, knoriks1, null);
			}
		}
		else if (event.equalsIgnoreCase("knoriks1_move3") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move3", 60000, knoriks1, null);
			else
			{
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_5);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move4", 15000, knoriks1, null);
			}
		}
		else if (event.equalsIgnoreCase("knoriks1_move4") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move4", 60000, knoriks1, null);
			else
			{
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_6);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move5", 14000, knoriks1, null);
			}
		}
		else if (event.equalsIgnoreCase("knoriks1_move5") && knoriks1 != null)
		{
			if (npc.isCastingNow() || npc.isAttackingNow() || npc.isInCombat())
				startQuestTimer("knoriks1_move5", 60000, knoriks1, null);
			else
			{
				knoriks1.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, knoriks1_7);
				knoriks1.setRunning();
				startQuestTimer("knoriks1_move1", 13000, knoriks1, null);
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onKill (L2Npc npc, L2PcInstance killer, boolean isPet) 
	{
		if (npc == null)
			return super.onKill(npc,killer,isPet);
		if (npc.getNpcId() == KNORIKS)
		{
			if (npc == knoriks1)
			{
				startQuestTimer("knoriks1_spawn", 300000, null, null);
				knoriks1 = null;
			}
		}
		return super.onKill(npc,killer,isPet);
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		if (!npc.isCastingNow() && !npc.isAttackingNow() && !npc.isInCombat() && !player.isDead())
		{
			((L2Attackable) npc).addDamageHate(player, 0, 999);
			((L2Attackable) npc).getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
		}
		return super.onAggroRangeEnter(npc, player, isPet);
	}
	
	public static void main(String[] args)
	{
		new Knoriks(-1,qn,"ai");
	}
}