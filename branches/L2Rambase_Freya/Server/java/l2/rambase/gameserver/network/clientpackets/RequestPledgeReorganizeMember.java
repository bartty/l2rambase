/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


/**
 * Format: (ch) dSdS
 * 
 */
public final class RequestPledgeReorganizeMember extends L2GameClientPacket
{
	private static final String _C__D0_24_REQUESTPLEDGEREORGANIZEMEMBER = "[C] D0:24 RequestPledgeReorganizeMember";

	private int _isMemberSelected;
	private String _memberName;
	private int _newPledgeType;
	private String _selectedMember;

	@Override
	protected void readImpl()
	{
		_isMemberSelected = readD();
		_memberName = readS();
		_newPledgeType = readD();
		_selectedMember = readS();
	}

	/**
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		if (_isMemberSelected == 0)
			return;
		L2PcInstance activeChar = getClient().getActiveChar();
		if(activeChar == null)
			return;

		L2Clan clan = activeChar.getClan();
		if(clan == null)
			return;
		L2ClanMember member1 = clan.getClanMember(_memberName);
		L2ClanMember member2 = clan.getClanMember(_selectedMember);
		if(member1 == null || member2 == null)
			return;
		int oldPledgeType = member1.getPledgeType();
		if (oldPledgeType == _newPledgeType)
			return;
		member1.setPledgeType(_newPledgeType);
		member2.setPledgeType(oldPledgeType);
		clan.broadcastClanStatus();
	}
	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_24_REQUESTPLEDGEREORGANIZEMEMBER;
	}

}
