/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.Map;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.instancemanager.ClanHallManager;
import l2.rambase.gameserver.model.entity.ClanHall;


public class ExShowAgitInfo extends L2GameServerPacket
{
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:16 ExShowAgitInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x16);
        Map<Integer, ClanHall> clannhalls = ClanHallManager.getInstance().getAllClanHalls();
        writeD(clannhalls.size());
        for (ClanHall ch : clannhalls.values())
        {
            writeD(ch.getId());
            writeS(ch.getOwnerId() <= 0 ? "" : ClanTable.getInstance().getClan(ch.getOwnerId()).getName()); // owner clan name
            writeS(ch.getOwnerId() <= 0 ? "" : ClanTable.getInstance().getClan(ch.getOwnerId()).getLeaderName()); // leader name
            writeD(ch.getGrade()> 0 ? 0x00 : 0x01); // 0 - auction  1 - war clanhall  2 - ETC (rainbow spring clanhall)
        }
    }
}