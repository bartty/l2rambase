/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class PartySmallWindowAll extends L2GameServerPacket
{
	private static final String _S__63_PARTYSMALLWINDOWALL = "[S] 4e PartySmallWindowAll";
	private L2Party _party;
	private L2PcInstance _exclude;
	private int _dist, _LeaderOID;
	
	public PartySmallWindowAll(L2PcInstance exclude, L2Party party)
	{
		_exclude = exclude;
		_party = party;
		_LeaderOID = _party.getPartyLeaderOID();
		_dist = _party.getLootDistribution();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x4e);
		writeD(_LeaderOID);
		writeD(_dist);
		writeD(_party.getMemberCount() - 1);
		
		for (L2PcInstance member : _party.getPartyMembers())
		{
			if ((member != null) && (member != _exclude))
			{
				writeD(member.getObjectId());
				writeS(member.getName());
				
				writeD((int) member.getCurrentCp()); // c4
				writeD(member.getMaxCp()); // c4
				
				writeD((int) member.getCurrentHp());
				writeD(member.getMaxVisibleHp());
				writeD((int) member.getCurrentMp());
				writeD(member.getMaxMp());
				writeD(member.getLevel());
				writeD(member.getClassId().getId());
				writeD(0x00);// writeD(0x01); ??
				writeD(member.getRace().ordinal());
				writeD(0x00); // T2.3
				writeD(0x00); // T2.3
				if (member.getPet() != null)
				{
					writeD(member.getPet().getObjectId());
					writeD(member.getPet().getNpcId() + 1000000);
					writeD(member.getPet().getSummonType());
					writeS(member.getPet().getName());
					writeD((int) member.getPet().getCurrentHp());
					writeD(member.getPet().getMaxHp());
					writeD((int) member.getPet().getCurrentMp());
					writeD(member.getPet().getMaxMp());
					writeD(member.getPet().getLevel());
				}
				else
				{
					writeD(0x00);
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__63_PARTYSMALLWINDOWALL;
	}
}