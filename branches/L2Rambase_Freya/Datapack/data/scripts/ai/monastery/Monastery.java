/*
 * L2Rambase Project Project
 * FIXME: Create & Finish Monastery of Silence AI script - Anais
 */
package ai.monastery;
 
import java.util.Collection;

import ai.group_template.L2AttackableAIScript;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.network.serverpackets.NpcSay;
import l2.rambase.gameserver.templates.skills.L2SkillType;
import l2.rambase.gameserver.util.Util;
import l2.rambase.util.Rnd;

import javolution.util.FastList;
 
public class Monastery extends L2AttackableAIScript
{
	static final int[] mobs1 = {22124, 22125, 22126, 22127, 22129};
	static final int[] mobs2 = {22134, 22135};
	static final String[] text = {
		"You cannot carry a weapon without authorization!",
		"name, why would you choose the path of darkness?!",
		"name! How dare you defy the will of Einhasad!"
	};
    public Monastery(int questId, String name, String descr)
    {
        super(questId, name, descr);
        registerMobs(mobs1);
        registerMobs(mobs2);
    }
 
    public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isPet)
    {
    	if (contains(mobs1,npc.getNpcId()) && !npc.isInCombat() && npc.getTarget() == null)
    	{
    		if (player.getActiveWeaponInstance() != null)
    		{
    			npc.setTarget(player);
    			npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[0]));
    			switch (npc.getNpcId())
    			{
    				case 22124:
    				case 22126:
    				{
    					L2Skill skill = SkillTable.getInstance().getInfo(4589,8);
    	    			npc.doCast(skill);
    	    			break;
    				}
    				default:
    				{
    					npc.setIsRunning(true);
    	    			((L2Attackable) npc).addDamageHate(player, 0, 999);
    	    			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
    	    			break;
    				}
    			}
    		}
    		else if (((L2Attackable)npc).getMostHated() == null) 
    			return null;
    	}
        return super.onAggroRangeEnter(npc, player, isPet);
    }

    public String onSkillSee(L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
	{
    	if (contains(mobs2,npc.getNpcId()))
    	{
    		if (skill.getSkillType() == L2SkillType.AGGDAMAGE && targets.length != 0)
    		{
    			for (L2Object obj : targets)
    			{
    				if (obj.equals(npc))
    				{
    					npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[Rnd.get(2)+1].replace("name", caster.getName())));
	    				((L2Attackable) npc).addDamageHate(caster, 0, 999);
	    				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, caster);
	    				break;
    				}
    			}
    		}
    	}
		return super.onSkillSee(npc, caster, skill, targets, isPet);
	}
    
    public String onSpawn(L2Npc npc)
	{
    	if (contains(mobs1,npc.getNpcId()))
    	{
    		FastList<L2Playable> result = new FastList<L2Playable>();
    		Collection<L2Object> objs = npc.getKnownList().getKnownObjects().values();
    		for (L2Object obj : objs)
			{
				if (obj instanceof L2PcInstance || obj instanceof L2PetInstance)
				{
					if (Util.checkIfInRange(npc.getAggroRange(), npc, obj, true) && !((L2Character) obj).isDead())
						result.add((L2Playable) obj);
				}
			}
    		if (!result.isEmpty() && result.size() != 0)
    		{
    			Object[] characters = result.toArray();
    			for (Object obj : characters)
    			{
    	    		L2Playable target = (L2Playable) (obj instanceof L2PcInstance ? obj : ((L2Summon) obj).getOwner());
    	    		if (target.getActiveWeaponInstance() != null && !npc.isInCombat() && npc.getTarget() == null)
    	    		{
    	    			npc.setTarget(target);
    	    			npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[0]));
    	    			switch (npc.getNpcId())
    	    			{
    	    				case 22124:
    	    				case 22126:
    	    				case 22127:
    	    				{
    	    					L2Skill skill = SkillTable.getInstance().getInfo(4589,8);
    	    	    			npc.doCast(skill);
    	    	    			break;
    	    				}
    	    				default:
    	    				{
    	    					npc.setIsRunning(true);
    	    	    			((L2Attackable) npc).addDamageHate(target, 0, 999);
    	    	    			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
    	    	    			break;
    	    				}
    	    			}
    	    		}
    			}
    		}
    	}
		return super.onSpawn(npc);
	}
    
    public String onSpellFinished(L2Npc npc, L2PcInstance player, L2Skill skill)
    {
    	if (contains(mobs1,npc.getNpcId()) && skill.getId() == 4589)
    	{
    		npc.setIsRunning(true);
    		((L2Attackable) npc).addDamageHate(player, 0, 999);
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
    	}
    	return super.onSpellFinished(npc, player, skill);
    }
    
    public static void main(String[] args)
    {
        new Monastery(-1, "Monastery", "ai");
    }
}