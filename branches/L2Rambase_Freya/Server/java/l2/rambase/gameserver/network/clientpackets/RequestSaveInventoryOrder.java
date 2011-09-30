/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.ArrayList;
import java.util.List;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2ItemInstance.ItemLocation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.Inventory;


/**
 * Format:(ch) d[dd]
 */
public final class RequestSaveInventoryOrder extends L2GameClientPacket
{
    private List<InventoryOrder> _order;
    
    /** client limit */
    private static final int LIMIT  = 125;
    
    /**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
     */
    @Override
    protected void readImpl()
    {
        int sz = readD();
        sz = Math.min(sz, LIMIT);
        _order = new ArrayList<InventoryOrder>(sz);
        for (int i = 0; i < sz; i++)
        {
            int objectId = readD();
            int order = readD();
            _order.add(new InventoryOrder(objectId, order));
        }
    }
    
    /**
     * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
        L2PcInstance player = this.getClient().getActiveChar();
        if (player != null)
        {
            Inventory inventory = player.getInventory();
            for (InventoryOrder order : _order)
            {
                L2ItemInstance item = inventory.getItemByObjectId(order.objectID);
                if (item != null && item.getLocation() == ItemLocation.INVENTORY)
                {
                    item.setLocation(ItemLocation.INVENTORY, order.order);
                }
            }
        }
    }
    
    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return "[C] D0:49 RequestSaveInventoryOrder";
    }
    
    private class InventoryOrder
    {
        int order;
        
        int objectID;
        
        /**
         * 
         */
        public InventoryOrder(int id, int ord)
        {
            objectID = id;
            order = ord;
        }
    }
}
