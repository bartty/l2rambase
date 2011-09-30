/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2Clan.SubPledge;


public class PledgeReceiveSubPledgeCreated extends L2GameServerPacket
{
	private static final String _S__FE_3F_PLEDGERECEIVESUBPLEDGECREATED = "[S] FE:40 PledgeReceiveSubPledgeCreated";
	private static Logger _log = Logger.getLogger(PledgeReceiveSubPledgeCreated.class.getName());
	private SubPledge _subPledge;
	private L2Clan _clan;

	/**
	 * @param member
	 */
	public PledgeReceiveSubPledgeCreated(SubPledge subPledge, L2Clan clan)
	{
		_subPledge = subPledge;
		_clan = clan;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x40);

		writeD(0x01);
        writeD(_subPledge.getId());
        writeS(_subPledge.getName());
        writeS(getLeaderName());
	}
	
	private String getLeaderName()
	{
		int LeaderId = _subPledge.getLeaderId();
		if (_subPledge.getId() == L2Clan.SUBUNIT_ACADEMY || LeaderId == 0)
			return "";
		else if (_clan.getClanMember(LeaderId) == null)
		{
			_log.warning("SubPledgeLeader: "+ LeaderId + " is missing from clan: "+ _clan.getName()+"["+_clan.getClanId()+"]");
			return "";
		}
		else
			return _clan.getClanMember(LeaderId).getName(); 
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_3F_PLEDGERECEIVESUBPLEDGECREATED;
	}
}