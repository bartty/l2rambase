/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Party;


public class ExMPCCPartyInfoUpdate extends L2GameServerPacket
{
	
	private static final String _S__FE_5B_EXMPCCPARTYINFOUPDATE = "[S] FE:5B ExMPCCPartyInfoUpdate";
	private L2Party _party;
	private int _mode, _LeaderOID, _memberCount;
	private String _name;
	
	/**
	 * 
	 * @param party
	 * @param mode 0 = Remove, 1 = Add
	 */
	public ExMPCCPartyInfoUpdate(L2Party party, int mode)
	{
		_party = party;
		_name = _party.getLeader().getName();
		_LeaderOID = _party.getPartyLeaderOID();
		_memberCount = _party.getMemberCount();
		_mode = mode;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x5b);
		writeS(_name);
		writeD(_LeaderOID);
		writeD(_memberCount);
		writeD(_mode); //mode 0 = Remove Party, 1 = AddParty, maybe more...
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_5B_EXMPCCPARTYINFOUPDATE;
	}	
}