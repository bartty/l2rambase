/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PartyMemberPosition extends L2GameServerPacket
{
	private L2Party _party;

	public PartyMemberPosition(L2PcInstance actor)
	{
		_party = actor.getParty();
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xba);
		if (_party != null)
		{
			writeD(_party.getMemberCount());

			for(L2PcInstance pm : _party.getPartyMembers())
			{
	            if (pm == null)
	            	continue;

				writeD(pm.getObjectId());
				writeD(pm.getX());
				writeD(pm.getY());
				writeD(pm.getZ());
			}
		}
		else
			writeD(0x00);
	}

	@Override
	public String getType()
	{
		return "[S] ba PartyMemberPosition";
	}
}