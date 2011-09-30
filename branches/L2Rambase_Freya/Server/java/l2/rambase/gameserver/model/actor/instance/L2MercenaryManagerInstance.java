/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2MercenaryManagerInstance extends L2Npc
{
	// private int[] TW_BADGE_IDS = { 13757, 13758, 13759, 13760, 13761, 13762, 13763, 13764, 13765 };
	/**
	 * @param objectId
	 * @param template
	 */
	public L2MercenaryManagerInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2MercenaryManagerInstance);
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		if (player.getLevel() < 40 || player.getClassId().level() < 2)
			super.showChatWindow(player, 2);
		else if (TerritoryWarManager.getInstance().isTWInProgress())
			super.showChatWindow(player, 10);
		else
			super.showChatWindow(player, 0);
	}
	
	@Override
	public String getHtmlPath(int npcId, int val)
	{
		String temp = "";
		if (val == 0)
			temp = "data/html/mercmanager/" + npcId + ".htm";
		else
			temp = "data/html/mercmanager/" + npcId + "-" + val + ".htm";
		
		if (!Config.LAZY_CACHE)
		{
			// If not running lazy cache the file must be in the cache or it doesnt exist
			if (HtmCache.getInstance().contains(temp))
				return temp;
		}
		else
		{
			if (HtmCache.getInstance().isLoadable(temp))
				return temp;
		}
		
		// If the file is not found, the standard message "I have nothing to say to you" is returned
		return "data/html/npcdefault.htm";
	}
}
