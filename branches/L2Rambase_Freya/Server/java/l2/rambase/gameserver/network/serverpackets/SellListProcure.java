/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;
import java.util.Map;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.CastleManorManager.CropProcure;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import javolution.util.FastList;
import javolution.util.FastMap;

public class SellListProcure extends L2GameServerPacket
{
    private static final String _S__E9_SELLLISTPROCURE = "[S] ef SellListProcure";

    private final L2PcInstance _activeChar;
    private long _money;
    private Map<L2ItemInstance,Long> _sellList = new FastMap<L2ItemInstance,Long>();
    private List<CropProcure> _procureList = new FastList<CropProcure>();
    private int _castle;

    public SellListProcure(L2PcInstance player, int castleId)
    {
        _money = player.getAdena();
        _activeChar = player;
        _castle = castleId;
        _procureList =  CastleManager.getInstance().getCastleById(_castle).getCropProcure(0);
        for(CropProcure c : _procureList)
        {
            L2ItemInstance item = _activeChar.getInventory().getItemByItemId(c.getId());
            if(item != null && c.getAmount() > 0)
            {
                _sellList.put(item,c.getAmount());
            }
        }
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0xef);
        writeQ(_money);         // money
        writeD(0x00);           // lease
        writeH(_sellList.size());         // list size

        for(L2ItemInstance item : _sellList.keySet())
        {
            writeH(item.getItem().getType1());
            writeD(item.getObjectId());
            writeD(item.getItemId());
            writeQ(_sellList.get(item));  // count
            writeH(item.getItem().getType2());
            writeH(0);  // unknown
            writeQ(0);  // price, u shouldnt get any adena for crops, only raw materials
        }
    }

    @Override
	public String getType()
    {
        return _S__E9_SELLLISTPROCURE;
    }
}