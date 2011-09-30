/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan.RankPrivs;


public class PledgePowerGradeList extends L2GameServerPacket
{
	private static final String _S__FE_3B_PLEDGEPOWERGRADELIST = "[S] FE:3c PledgePowerGradeList";
    private RankPrivs[] _privs;

	public PledgePowerGradeList(RankPrivs[] privs)
	{
        _privs = privs;
	}

	@Override
	protected final void writeImpl()
	{
        writeC(0xFE);
        writeH(0x3c);
        writeD(_privs.length);
        for (RankPrivs temp: _privs)
        {
            writeD(temp.getRank());
            writeD(temp.getParty());           
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_3B_PLEDGEPOWERGRADELIST;
	}
}