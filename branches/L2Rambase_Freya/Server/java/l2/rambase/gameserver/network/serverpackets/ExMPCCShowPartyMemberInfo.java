/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: ch d[Sdd]
 * 
 */
public class ExMPCCShowPartyMemberInfo extends L2GameServerPacket
{
	private static final String _S__FE_4A_EXMPCCSHOWPARTYMEMBERINFO = "[S] FE:4b ExMPCCShowPartyMemberInfo";
	private L2Party _party;
	
	public ExMPCCShowPartyMemberInfo(L2Party party)
	{
		this._party = party;
	}
	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x4b);
		writeD(_party.getMemberCount()); // Number of Members
		for(L2PcInstance pc : _party.getPartyMembers())
		{
			writeS(pc.getName()); // Membername
			writeD(pc.getObjectId()); // ObjId
			writeD(pc.getClassId().getId()); // Classid
		}
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4A_EXMPCCSHOWPARTYMEMBERINFO;
	}
}