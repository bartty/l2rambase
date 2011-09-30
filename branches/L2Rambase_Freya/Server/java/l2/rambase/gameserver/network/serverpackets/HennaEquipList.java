/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class HennaEquipList extends L2GameServerPacket
{
	private static final String _S__E2_HennaEquipList = "[S] ee HennaEquipList";

	private L2PcInstance _player;
	private L2HennaInstance[] _hennaEquipList;

	public HennaEquipList(L2PcInstance player,L2HennaInstance[] hennaEquipList)
	{
		_player = player;
		_hennaEquipList = hennaEquipList;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xee);
		writeQ(_player.getAdena()); //activeChar current amount of adena
		writeD(3); //available equip slot
		writeD(_hennaEquipList.length);

		for (L2HennaInstance temp: _hennaEquipList)
		{
			// Player must have at least one dye in inventory
			// to be able to see the henna that can be applied with it.
			if ((_player.getInventory().getItemByItemId(temp.getItemIdDye())) != null)
			{
				writeD(temp.getSymbolId()); //symbolId
				writeD(temp.getItemIdDye()); //itemId of dye
				writeQ(temp.getAmountDyeRequire()); //amount of dye require
				writeQ(temp.getPrice()); //amount of adena required
				writeD(1); //meet the requirement or not
			}
			else
			{
				writeD(0x00);
				writeD(0x00);
				writeQ(0x00);
				writeQ(0x00);
				writeD(0x00);
			}
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__E2_HennaEquipList;
	}
}