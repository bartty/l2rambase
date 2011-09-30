/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager.Territory;


public class ExReplyDominionInfo extends L2GameServerPacket
{
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	private int _warTime = (int) (TerritoryWarManager.getInstance().getTWStartTimeInMillis() / 1000);

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x92);
		FastList<Territory> territoryList = TerritoryWarManager.getInstance().getAllTerritories();
		writeD(territoryList.size()); // Territory Count
		for (Territory t : territoryList)
		{
			writeD(t.getTerritoryId()); // Territory Id
			writeS(CastleManager.getInstance().getCastleById(t.getCastleId()).getName().toLowerCase() + "_dominion"); // territory name
			writeS(t.getOwnerClan().getName());
			writeD(t.getOwnedWardIds().size()); // Emblem Count
			for(int i:t.getOwnedWardIds())
			{
				writeD(i); // Emblem ID - should be in for loop for emblem count
			}
			writeD(_warTime);
		}
	}

	@Override
	public String getType()
	{
		return "[S] FE:92 ExReplyDominionInfo";
	}
}