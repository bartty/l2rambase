/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.entity.Fort;


/**
 * format: d (dSdd)
 * cnt:%d (fortressID:%d ownerName:%s, siegeState:%d, lastOwnedTime:%d)
 */
public class ExShowFortressInfo extends L2GameServerPacket
{
	/**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:15 ExShowFortressInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x15);
        List<Fort> forts = FortManager.getInstance().getForts();
        writeD(forts.size());
        for (Fort fort : forts)
        {
        	L2Clan clan = fort.getOwnerClan();
            writeD(fort.getFortId());
            if (clan != null)
            {
            	writeS(clan.getName());
            }
            else
            {
                writeS("");
            }
            
            if (fort.getSiege().getIsInProgress())
            {
                writeD(1);
            }
            else
            {
            	writeD(0);
            }
            
            // Time of possession
            writeD(fort.getOwnedTime());
        }
    }    
}