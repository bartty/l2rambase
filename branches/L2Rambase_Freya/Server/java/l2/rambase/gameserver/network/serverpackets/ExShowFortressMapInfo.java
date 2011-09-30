/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.instancemanager.FortSiegeManager;
import l2.rambase.gameserver.instancemanager.FortSiegeManager.SiegeSpawn;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.entity.Fort;

import javolution.util.FastList;


public class ExShowFortressMapInfo extends L2GameServerPacket
{
	private final Fort _fortress;
	
	public ExShowFortressMapInfo(Fort fortress)
	{
		_fortress = fortress;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:7D ExShowFortressMapInfo";
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x7d);
		
		writeD(_fortress.getFortId());
		writeD(_fortress.getSiege().getIsInProgress() ? 1 : 0); // fortress siege status
		writeD(_fortress.getFortSize()); // barracks count
		
		FastList<SiegeSpawn> commanders = FortSiegeManager.getInstance().getCommanderSpawnList(_fortress.getFortId());
		if (commanders != null && commanders.size() != 0 && _fortress.getSiege().getIsInProgress())
		{
			switch (commanders.size())
			{
				case 3:
				{
					for (SiegeSpawn spawn : commanders)
					{
						if (isSpawned(spawn.getNpcId()))
							writeD(0);
						else
							writeD(1);
					}
					break;
				}
				case 4: // TODO: change 4 to 5 once control room supported
				{
					int count = 0;
					for (SiegeSpawn spawn : commanders)
					{
						count++;
						if (count == 4)
							writeD(1); // TODO: control room emulated
						if (isSpawned(spawn.getNpcId()))
							writeD(0);
						else
							writeD(1);
					}
					break;
				}
			}
		}
		else
		{
			for (int i = 0; i <_fortress.getFortSize(); i++)
			{
				writeD(0);
			}
		}
	}
	
	/**
	 * @param npcId
	 * @return
	 */
	private boolean isSpawned(int npcId)
	{
		boolean ret = false;
		for (L2Spawn spawn : _fortress.getSiege().getCommanders())
		{
			if (spawn.getNpcid() == npcId)
				ret = true;
		}
		return ret;
	}
}