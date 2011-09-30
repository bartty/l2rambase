/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager.CropProcure;
import l2.rambase.gameserver.model.entity.Castle;

import javolution.util.FastMap;

/**
 * format(packet 0xFE) ch dd [dddc] c - id h - sub id
 * 
 * d - crop id d - size
 *  [ d - manor name d - buy residual d - buy price c - reward type ]
 */
public class ExShowProcureCropDetail extends L2GameServerPacket
{
    private static final String _S__FE_22_EXSHOWPROCURECROPDETAIL = "[S] FE:78 ExShowProcureCropDetail";
    
    private int _cropId;
    
    private FastMap<Integer, CropProcure> _castleCrops;
    
    public ExShowProcureCropDetail(int cropId)
    {
        _cropId = cropId;
        _castleCrops = new FastMap<Integer, CropProcure>();
        
        for (Castle c : CastleManager.getInstance().getCastles())
        {
            CropProcure cropItem = c.getCrop(_cropId,
                    CastleManorManager.PERIOD_CURRENT);
            if (cropItem != null && cropItem.getAmount() > 0)
            {
                _castleCrops.put(c.getCastleId(), cropItem);
            }
        }
    }
    
    @Override
    public void runImpl()
    {
    }
    
    @Override
    public void writeImpl()
    {
        writeC(0xFE);
        writeH(0x78);
        
        writeD(_cropId); // crop id
        writeD(_castleCrops.size()); // size
        
        for (int manorId : _castleCrops.keySet())
        {
            CropProcure crop = _castleCrops.get(manorId);
            writeD(manorId); // manor name
            writeQ(crop.getAmount()); // buy residual
            writeQ(crop.getPrice()); // buy price
            writeC(crop.getReward()); // reward type
        }
    }
    
    @Override
    public String getType()
    {
        return _S__FE_22_EXSHOWPROCURECROPDETAIL;
    }    
}