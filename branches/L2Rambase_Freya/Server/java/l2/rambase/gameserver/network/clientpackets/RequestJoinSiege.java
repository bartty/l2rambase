/**
 * @author L2Rambase Project
 */
 
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public final class RequestJoinSiege extends L2GameClientPacket
{
	private static final String _C__A4_RequestJoinSiege = "[C] a4 RequestJoinSiege";	

	private int _castleId;
	private int _isAttacker;
	private int _isJoining;

	@Override
	protected void readImpl()
	{
		_castleId = readD();
		_isAttacker = readD();
		_isJoining = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)	return;
		
		if ((activeChar.getClanPrivileges() & L2Clan.CP_CS_MANAGE_SIEGE) != L2Clan.CP_CS_MANAGE_SIEGE)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT));
			return;
		}
		
		L2Clan clan = activeChar.getClan();
		if (clan == null) return;
		
		Castle castle = CastleManager.getInstance().getCastleById(_castleId);
		if (castle == null)	return;
		
		if (_isJoining == 1)
		{
			if (System.currentTimeMillis() < clan.getDissolvingExpiryTime())
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS));
				return;
			}
			if (_isAttacker == 1)
				castle.getSiege().registerAttacker(activeChar);
			else
				castle.getSiege().registerDefender(activeChar);
		}
		else
			castle.getSiege().removeSiegeClan(activeChar);
		castle.getSiege().listRegisterClan(activeChar);
	}

	@Override
	public String getType()
	{
		return _C__A4_RequestJoinSiege;
	}
}