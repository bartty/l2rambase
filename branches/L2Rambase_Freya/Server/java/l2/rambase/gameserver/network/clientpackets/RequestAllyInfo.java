/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 * This class ...
 *
 * @version $Revision: 1479 $ $Date: 2005-11-09 00:47:42 +0100 (mer., 09 nov. 2005) $
 */
public final class RequestAllyInfo extends L2GameClientPacket
{
	private static final String _C__8E_REQUESTALLYINFO = "[C] 8E RequestAllyInfo";


	@Override
	public void readImpl()
	{

	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		SystemMessage sm;
		if (activeChar.getAllyId() == 0)
		{
			sm = new SystemMessage(SystemMessageId.NO_CURRENT_ALLIANCES);
			sendPacket(sm);
			return;
		}

		sm = new SystemMessage(SystemMessageId.ALLIANCE_INFO_HEAD);
		sendPacket(sm);
		sm = new SystemMessage(SystemMessageId.ALLIANCE_NAME_S1);
		sm.addString(activeChar.getClan().getAllyName());
		sendPacket(sm);

		int clanCount = 0;
		int totalMembers = 0;
		int onlineMembers = 0;
		for (L2Clan clan : ClanTable.getInstance().getClans())
		{
			if (clan.getAllyId() != activeChar.getAllyId())
				continue;

			clanCount++;
			totalMembers += clan.getMembersCount();
			onlineMembers += clan.getOnlineMembersCount();
		}
		sm = new SystemMessage(SystemMessageId.CONNECTION_S1_TOTAL_S2);
		sm.addNumber(onlineMembers);
		sm.addNumber(totalMembers);
		sendPacket(sm);

		final L2Clan leaderClan = ClanTable.getInstance().getClan(activeChar.getAllyId());
		sm = new SystemMessage(SystemMessageId.ALLIANCE_LEADER_S2_OF_S1);
		sm.addString(leaderClan.getName());
		sm.addString(leaderClan.getLeaderName());
		sendPacket(sm);

		sm = new SystemMessage(SystemMessageId.ALLIANCE_CLAN_TOTAL_S1);
		sm.addNumber(clanCount);
		sendPacket(sm);

		sm = new SystemMessage(SystemMessageId.CLAN_INFO_HEAD);
		for (L2Clan clan : ClanTable.getInstance().getClans())
		{
			if (clan.getAllyId() != activeChar.getAllyId())
				continue;

			sendPacket(sm); // send head or separator
			sm = new SystemMessage(SystemMessageId.CLAN_INFO_NAME);
			sm.addString(clan.getName());
			sendPacket(sm);
			sm = new SystemMessage(SystemMessageId.CLAN_INFO_LEADER);
			sm.addString(clan.getLeaderName());
			sendPacket(sm);
			sm = new SystemMessage(SystemMessageId.CLAN_INFO_LEVEL);
			sm.addNumber(clan.getLevel());
			sendPacket(sm);
			sm = new SystemMessage(SystemMessageId.CLAN_INFO_SEPARATOR);
		}
		sm = new SystemMessage(SystemMessageId.CLAN_INFO_FOOT);
		sendPacket(sm);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__8E_REQUESTALLYINFO;
	}
}
