/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.Collection;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.taskmanager.AttackStanceTaskManager;


public final class RequestStopPledgeWar extends L2GameClientPacket
{
	private static final String _C__4F_REQUESTSTOPPLEDGEWAR = "[C] 4F RequestStopPledgeWar";
	
	private String _pledgeName;

	@Override
	protected void readImpl()
	{
		_pledgeName = readS();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null) return;
		L2Clan playerClan = player.getClan();
		if (playerClan == null) return;

		L2Clan clan = ClanTable.getInstance().getClanByName(_pledgeName);

		if (clan == null)
		{
			player.sendMessage("No such clan.");
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (!playerClan.isAtWarWith(clan.getClanId()))
		{
			player.sendMessage("You aren't at war with this clan.");
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Check if player who does the request has the correct rights to do it
		if ((player.getClanPrivileges() & L2Clan.CP_CL_PLEDGE_WAR) != L2Clan.CP_CL_PLEDGE_WAR )
		{
			player.sendPacket(new SystemMessage(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT));
			return;
		}
		
		for (L2ClanMember member : playerClan.getMembers())
		{
			if (member == null || member.getPlayerInstance() == null)
				continue;
			if (AttackStanceTaskManager.getInstance().getAttackStanceTask(member.getPlayerInstance()))
			{
				player.sendPacket(new SystemMessage(SystemMessageId.CANT_STOP_CLAN_WAR_WHILE_IN_COMBAT));
				return;
			}
		}
		
		ClanTable.getInstance().deleteclanswars(playerClan.getClanId(), clan.getClanId());
		Collection<L2PcInstance> pls = L2World.getInstance().getAllPlayers().values();
		for (L2PcInstance cha : pls)
		{
			if (cha.getClan() == player.getClan() || cha.getClan() == clan)
				cha.broadcastUserInfo();
        }
	}

	@Override
	public String getType()
	{
		return _C__4F_REQUESTSTOPPLEDGEWAR;
	}
}