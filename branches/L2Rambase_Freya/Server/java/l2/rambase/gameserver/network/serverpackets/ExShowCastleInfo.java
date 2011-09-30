/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;
import java.util.logging.Logger;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.entity.Castle;


public class ExShowCastleInfo extends L2GameServerPacket
{
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
	private static Logger _log = Logger.getLogger(ExShowCastleInfo.class.getName());

    @Override
    public String getType()
    {
        return "[S] FE:14 ExShowCastleInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x14);
        List<Castle> castles = CastleManager.getInstance().getCastles();
        writeD(castles.size());
        for (Castle castle : castles)
        {
            writeD(castle.getCastleId());
            if (castle.getOwnerId() > 0)
            {
                if (ClanTable.getInstance().getClan(castle.getOwnerId()) != null)
                    writeS(ClanTable.getInstance().getClan(castle.getOwnerId()).getName());
                else
                {
                    _log.warning("Castle owner with no name! Castle: " + castle.getName() + " has an OwnerId = " + castle.getOwnerId() + " who does not have a  name!");
                    writeS("");
                }
            }
            else
                writeS("");
            writeD(castle.getTaxPercent());
            writeD((int)(castle.getSiege().getSiegeDate().getTimeInMillis()/1000));
        }
    }
}