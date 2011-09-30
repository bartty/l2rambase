/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.itemcontainer;

import l2.rambase.gameserver.model.L2ItemInstance.ItemLocation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class PcWarehouse extends Warehouse
{
	private L2PcInstance _owner;

	public PcWarehouse(L2PcInstance owner)
	{
		_owner = owner;
	}

	@Override
	public String getName() { return "Warehouse"; };

	@Override
	public L2PcInstance getOwner() { return _owner; }
	@Override
	public ItemLocation getBaseLocation() { return ItemLocation.WAREHOUSE; }
	public String getLocationId() { return "0"; }
	public int getLocationId(boolean dummy) { return 0; }
	public void setLocationId(L2PcInstance dummy) {}

	@Override
	public boolean validateCapacity(int slots)
	{
		return (_items.size() + slots <= _owner.getWareHouseLimit());
	}
}