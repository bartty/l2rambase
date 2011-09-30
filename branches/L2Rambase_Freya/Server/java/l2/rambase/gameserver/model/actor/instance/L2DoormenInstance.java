/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.util.StringTokenizer;

import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.datatables.TeleportLocationTable;
import l2.rambase.gameserver.model.L2TeleportLocation;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2DoormenInstance extends L2NpcInstance
{
	/**
	 * @param template
	 */
	public L2DoormenInstance(int objectID, L2NpcTemplate template)
	{
		super(objectID, template);
		setInstanceType(InstanceType.L2DoormenInstance);
	}
	
	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (command.startsWith("Chat"))
		{
			showChatWindow(player);
			return;
		}
		else if (command.startsWith("open_doors"))
		{
			if (isOwnerClan(player))
			{
				if (isUnderSiege())
					cannotManageDoors(player);
				else
					openDoors(player, command);
			}
			return;
		}
		else if (command.startsWith("close_doors"))
		{
			if (isOwnerClan(player))
			{
				if (isUnderSiege())
					cannotManageDoors(player);
				else
					closeDoors(player, command);
			}
			return;
		}
		else if (command.startsWith("tele"))
		{
			if (isOwnerClan(player))
				doTeleport(player, command);
			return;
		}
		super.onBypassFeedback(player, command);
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		
		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		
		if (!isOwnerClan(player))
			html.setFile(player.getHtmlPrefix(), "data/html/doormen/"+ getTemplate().npcId + "-no.htm");
		else
			html.setFile(player.getHtmlPrefix(), "data/html/doormen/"+ getTemplate().npcId + ".htm");
		
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}
	
	protected void openDoors(L2PcInstance player, String command)
	{
		StringTokenizer st = new StringTokenizer(command.substring(10), ", ");
		st.nextToken();
		
		while (st.hasMoreTokens())
		{
			DoorTable.getInstance().getDoor(Integer.parseInt(st.nextToken())).openMe();
		}
	}
	
	protected void closeDoors(L2PcInstance player, String command)
	{
		StringTokenizer st = new StringTokenizer(command.substring(11), ", ");
		st.nextToken();
		
		while (st.hasMoreTokens())
		{
			DoorTable.getInstance().getDoor(Integer.parseInt(st.nextToken())).closeMe();
		}
	}
	
	protected void cannotManageDoors(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		
		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getHtmlPrefix(), "data/html/doormen/"+ getTemplate().npcId + "-busy.htm");
		player.sendPacket(html);
	}
	
	protected void doTeleport(L2PcInstance player, String command)
	{
		final int whereTo = Integer.parseInt(command.substring(5).trim());
		L2TeleportLocation list = TeleportLocationTable.getInstance().getTemplate(whereTo);
		if (list != null)
		{
			if (!player.isAlikeDead())
				player.teleToLocation(list.getLocX(), list.getLocY(), list.getLocZ(), false);
		}
		else
			_log.warning("No teleport destination with id:" + whereTo);
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	protected boolean isOwnerClan(L2PcInstance player)
	{
		return true;
	}
	
	protected boolean isUnderSiege()
	{
		return false;
	}
}