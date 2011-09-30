/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class L2WyvernManagerInstance extends L2Npc
{
	public L2WyvernManagerInstance (int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2WyvernManagerInstance);
	}

	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket( ActionFailed.STATIC_PACKET );
		String filename = "data/html/wyvernmanager/wyvernmanager-no.htm";

		if (isOwnerClan(player))
			filename = "data/html/wyvernmanager/wyvernmanager.htm";      // Owner message window

		NpcHtmlMessage html = new NpcHtmlMessage(1);
		html.setFile(player.getHtmlPrefix(), filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcname%", getName());
		player.sendPacket(html);
	}

	public boolean isOwnerClan(L2PcInstance player)
	{
		return true;
	}
	
	public boolean isInSiege()
	{
		return false;
	}
}