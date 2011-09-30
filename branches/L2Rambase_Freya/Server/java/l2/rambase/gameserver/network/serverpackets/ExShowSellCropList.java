/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.CastleManorManager.CropProcure;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Manor;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import javolution.util.FastList;
import javolution.util.FastMap;

/**
 * format(packet 0xFE) ch dd [ddddcdcdddc] c - id h - sub id
 * 
 * d - manor id d - size
 *  [ d - Object id d - crop id d - seed level c d - reward 1 id c d - reward 2
 * id d - manor d - buy residual d - buy price d - reward ]
 */
public class ExShowSellCropList extends L2GameServerPacket
{
	private static final String _S__FE_21_EXSHOWSELLCROPLIST = "[S] FE:2c ExShowSellCropList";

	private int _manorId = 1;
	private final FastMap<Integer, L2ItemInstance> _cropsItems;
	private final FastMap<Integer, CropProcure> _castleCrops;

	public ExShowSellCropList(L2PcInstance player, int manorId, List<CropProcure> crops)
	{
		_manorId = manorId;
		_castleCrops = new FastMap<Integer, CropProcure>();
		_cropsItems = new FastMap<Integer, L2ItemInstance>();

		FastList<Integer> allCrops = L2Manor.getInstance().getAllCrops();
		for (int cropId : allCrops)
		{
			L2ItemInstance item = player.getInventory().getItemByItemId(cropId);
			if (item != null)
			{
				_cropsItems.put(cropId, item);
			}
		}

		for (CropProcure crop : crops)
		{
			if (_cropsItems.containsKey(crop.getId()) && crop.getAmount() > 0)
			{
				_castleCrops.put(crop.getId(), crop);
			}
		}
	}

	@Override
	public void runImpl()
	{
	}

	@Override
	public void writeImpl()
	{
		writeC(0xFE);
		writeH(0x2c);

		writeD(_manorId); // manor id
		writeD(_cropsItems.size()); // size

		for (L2ItemInstance item : _cropsItems.values())
		{
			writeD(item.getObjectId()); // Object id
			writeD(item.getItemId()); // crop id
			writeD(L2Manor.getInstance().getSeedLevelByCrop(item.getItemId())); // seed level
			writeC(1);
			writeD(L2Manor.getInstance().getRewardItem(item.getItemId(), 1)); // reward 1 id
			writeC(1);
			writeD(L2Manor.getInstance().getRewardItem(item.getItemId(), 2)); // reward 2 id

			if (_castleCrops.containsKey(item.getItemId()))
			{
				CropProcure crop = _castleCrops.get(item.getItemId());
				writeD(_manorId); // manor
				writeQ(crop.getAmount()); // buy residual
				writeQ(crop.getPrice()); // buy price
				writeC(crop.getReward()); // reward
			}
			else
			{
				writeD(0xFFFFFFFF); // manor
				writeQ(0); // buy residual
				writeQ(0); // buy price
				writeC(0); // reward
			}
			writeQ(item.getCount()); // my crops
		}
	}

	@Override
	public String getType()
	{
		return _S__FE_21_EXSHOWSELLCROPLIST;
	}
}