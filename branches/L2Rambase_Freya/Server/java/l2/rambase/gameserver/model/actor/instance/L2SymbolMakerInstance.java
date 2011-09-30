/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2SymbolMakerInstance extends L2Npc
{
	public L2SymbolMakerInstance(int objectID, L2NpcTemplate template)
	{
		super(objectID, template);
		setInstanceType(InstanceType.L2SymbolMakerInstance);
	}

	@Override
	public String getHtmlPath(int npcId, int val)
	{
		return "data/html/symbolmaker/SymbolMaker.htm";
	}

	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
}