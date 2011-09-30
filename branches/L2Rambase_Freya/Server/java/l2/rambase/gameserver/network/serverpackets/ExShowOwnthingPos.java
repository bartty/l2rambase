/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.TerritoryWard;

/**
 * Format: (ch) d[dddd]
 */
public class ExShowOwnthingPos extends L2GameServerPacket
{
	private static final String _S__FE_93_EXSHOWOWNTHINGPOS = "[S] FE:93 ExShowOwnthingPos";
	
	public ExShowOwnthingPos()
	{
	}
	
	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x93);
		
		if (TerritoryWarManager.getInstance().isTWInProgress())
		{
			List<TerritoryWard> territoryWardList = TerritoryWarManager.getInstance().getAllTerritoryWards();
			writeD(territoryWardList.size());
			for(TerritoryWard ward : territoryWardList)
			{
				writeD(ward.getTerritoryId());
				
				if (ward.getNpc() != null)
				{
					writeD(ward.getNpc().getX());
					writeD(ward.getNpc().getY());
					writeD(ward.getNpc().getZ());
				}
				else if (ward.getPlayer() != null)
				{
					writeD(ward.getPlayer().getX());
					writeD(ward.getPlayer().getY());
					writeD(ward.getPlayer().getZ());
				}
				else
				{
					writeD(0);
					writeD(0);
					writeD(0);
				}
			}
		}
		else
		{
			writeD(0);
			//writeD(0);
		}
	}
	
	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_93_EXSHOWOWNTHINGPOS;
	}
}