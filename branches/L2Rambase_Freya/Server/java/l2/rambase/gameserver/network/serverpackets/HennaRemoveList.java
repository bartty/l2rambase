/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class HennaRemoveList extends L2GameServerPacket
{
	private static final String _S__E2_HennaRemoveList = "[S] ee HennaRemoveList";

	private L2PcInstance _player;

	public HennaRemoveList(L2PcInstance player)
	{
		_player = player;
	}

	private final int getHennaUsedSlots()
	{
		int _slots = 0;
		switch (_player.getHennaEmptySlots())
		{
			case 0:
				_slots = 3;
				break;
			case 1:
				_slots = 2;
				break;
			case 2:
				_slots = 1;
				break;
			case 3:
				_slots = 0;
				break;
		}

		return _slots;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xe6);
		writeQ(_player.getAdena());
		writeD(0x00);
		writeD(getHennaUsedSlots());

		for (int i = 1; i <= 3; i++)
		{
			L2HennaInstance henna = _player.getHenna(i);
			if (henna != null)
			{
				writeD(henna.getSymbolId());
				writeD(henna.getItemIdDye());
				writeD(henna.getAmountDyeRequire() / 2);
				writeD(0x00);
				writeD(henna.getPrice() / 5);
				writeD(0x00);
				writeD(0x01);
			}
		}
	}

	@Override
	public String getType()
	{
		return _S__E2_HennaRemoveList;
	}
}