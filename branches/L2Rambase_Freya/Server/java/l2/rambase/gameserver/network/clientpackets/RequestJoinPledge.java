/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.AskJoinPledge;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public final class RequestJoinPledge extends L2GameClientPacket
{
	private static final String _C__24_REQUESTJOINPLEDGE = "[C] 24 RequestJoinPledge";

	private int _target;
	private int _pledgeType;
	private String _pledgeName;
	private String _subPledgeName;

	@Override
	protected void readImpl()
	{
		_target = readD();
		_pledgeType = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;

		L2Object ob = L2World.getInstance().findObject(_target);
		if (!(ob instanceof L2PcInstance))
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_HAVE_INVITED_THE_WRONG_TARGET));
			return;
		}

		L2PcInstance target = (L2PcInstance) ob;
		L2Clan clan = activeChar.getClan();
		if (!clan.checkClanJoinCondition(activeChar, target, _pledgeType))
			return;

		if (!activeChar.getRequest().setRequest(target, this))
			return;

		_pledgeName = activeChar.getClan().getName();
		_subPledgeName = (activeChar.getClan().getSubPledge(_pledgeType) != null ? activeChar.getClan().getSubPledge(_pledgeType).getName() : null);
		target.sendPacket(new AskJoinPledge(activeChar.getObjectId(), _subPledgeName, _pledgeType, _pledgeName));
	}

	public int getPledgeType()
	{
		return _pledgeType;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__24_REQUESTJOINPLEDGE;
	}
}
