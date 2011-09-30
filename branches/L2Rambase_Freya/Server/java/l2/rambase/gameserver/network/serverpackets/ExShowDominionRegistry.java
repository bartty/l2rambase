/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.Calendar;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager.Territory;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * format: dSSSdddddddd (dd(d))
 */
public class ExShowDominionRegistry extends L2GameServerPacket
{
	private final static int MINID = 80;
	private final int _castleId;
	private int _clanReq = 0x00;
	private int _mercReq = 0x00;
	private int _isMercRegistered = 0x00;
	private int _isClanRegistered = 0x00;
	private int _warTime = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
	private int _currentTime = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
	
	public ExShowDominionRegistry(int castleId, L2PcInstance player)
	{
		_castleId = castleId;
		if (TerritoryWarManager.getInstance().getRegisteredClans(castleId) != null)
		{
			_clanReq = TerritoryWarManager.getInstance().getRegisteredClans(castleId).size();
			if (player.getClan() != null)
				_isClanRegistered = (TerritoryWarManager.getInstance().getRegisteredClans(castleId).contains(player.getClan()) ? 0x01 : 0x00);
		}
		if (TerritoryWarManager.getInstance().getRegisteredMercenaries(castleId) != null)
		{
			_mercReq = TerritoryWarManager.getInstance().getRegisteredMercenaries(castleId).size();
			_isMercRegistered = (TerritoryWarManager.getInstance().getRegisteredMercenaries(castleId).contains(player.getObjectId()) ? 0x01 : 0x00);
		}
		_warTime = (int) (TerritoryWarManager.getInstance().getTWStartTimeInMillis() / 1000);
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:90 ExShowDominionRegistry";
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x90);
		writeD(MINID+_castleId); // Current Territory Id
		if (TerritoryWarManager.getInstance().getTerritory(_castleId) == null)
		{
			// something is wrong
			writeS("No Owner"); // Owners Clan
			writeS("No Owner"); // Owner Clan Leader
			writeS("No Ally"); // Owner Alliance
		}
		else
		{
			L2Clan clan = TerritoryWarManager.getInstance().getTerritory(_castleId).getOwnerClan();
			writeS(clan.getName()); // Owners Clan
			writeS(clan.getLeaderName()); // Owner Clan Leader
			writeS(clan.getAllyName()); // Owner Alliance
		}
		writeD(_clanReq); // Clan Request
		writeD(_mercReq); // Merc Request
		writeD(_warTime); // War Time
		writeD(_currentTime); // Current Time
		writeD(_isClanRegistered); // is Cancel clan registration
		writeD(_isMercRegistered); // is Cancel mercenaries registration
		writeD(0x01); // unknown
		FastList<Territory> territoryList = TerritoryWarManager.getInstance().getAllTerritories();
		writeD(territoryList.size()); // Territory Count
		for (Territory t : territoryList)
		{
			writeD(t.getTerritoryId()); // Territory Id
			writeD(t.getOwnedWardIds().size()); // Emblem Count
			for(int i:t.getOwnedWardIds())
				writeD(i); // Emblem ID - should be in for loop for emblem count
		}
	}
}
