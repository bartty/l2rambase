/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2ItemInstance.ItemLocation;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.Warehouse;

public final class ClanWarehouse extends Warehouse
{
	//private static final Logger _log = Logger.getLogger(PcWarehouse.class.getName());

	private L2Clan _clan;

	public ClanWarehouse(L2Clan clan)
	{
		_clan = clan;
	}

	@Override
	public String getName() { return "ClanWarehouse"; };

	@Override
	public int getOwnerId() { return _clan.getClanId(); }
	@Override
	public L2PcInstance getOwner() { return _clan.getLeader().getPlayerInstance(); }
	@Override
	public ItemLocation getBaseLocation() { return ItemLocation.CLANWH; }
	public String getLocationId() { return "0"; }
    public int getLocationId(boolean dummy) { return 0; }
    public void setLocationId(L2PcInstance dummy) { }
	@Override
	public boolean validateCapacity(int slots)
	{
		return (_items.size() + slots <= Config.WAREHOUSE_SLOTS_CLAN);
	}
}