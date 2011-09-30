/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.CastleManorManager.SeedProduction;


public final class BuyListSeed extends L2GameServerPacket
{
	private static final String _S__E8_BUYLISTSEED = "[S] e9 BuyListSeed";

	private int _manorId;
	private List<Seed> _list = null;
	private long _money;

	public BuyListSeed(long currentMoney, int castleId, List<SeedProduction> seeds)
	{
		_money  = currentMoney;
		_manorId = castleId;

		if (seeds != null && seeds.size() > 0)
		{
			_list = new FastList<Seed>();
			for (SeedProduction s : seeds)
			{
				if (s.getCanProduce() > 0 && s.getPrice() > 0)
					_list.add(new Seed(s.getId(), s.getCanProduce(), s.getPrice()));
			}
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xe9);

		writeQ(_money);									// current money
		writeD(_manorId);								// manor id

		if (_list != null && _list.size() > 0)
		{
			writeH(_list.size());							// list length
			for (Seed s : _list)
			{
				writeH(0x04);								// item->type1
				writeD(0x00);								// objectId
				writeD(s._itemId);							// item id
				writeQ(s._count);							// item count
				writeH(0x04);								// item->type2
				writeH(0x00);								// unknown :)
				writeQ(s._price);							// price
			}
			_list.clear();
		}
		else
			writeH(0x00);

	}

	@Override
	public String getType()
	{
		return _S__E8_BUYLISTSEED;
	}

	private class Seed
	{
		public final int _itemId;
		public final long _count;
		public final long _price;

		public Seed(int itemId, long count, long price)
		{
			_itemId = itemId;
			_count = count;
			_price = price;
		}
	}
}