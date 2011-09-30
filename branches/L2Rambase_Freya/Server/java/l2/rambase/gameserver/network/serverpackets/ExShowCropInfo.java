/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.CastleManorManager.CropProcure;
import l2.rambase.gameserver.model.L2Manor;


/**
 * Format: ch cddd[ddddcdcdcd]
 * c - id (0xFE)
 * h - sub id (0x1D)
 *
 * c
 * d - manor id
 * d
 * d - size
 * [
 * d - crop id
 * d - residual buy
 * d - start buy
 * d - buy price
 * c - reward type
 * d - seed level
 * c - reward 1 items
 * d - reward 1 item id
 * c - reward 2 items
 * d - reward 2 item id
 * ]
 */

public class ExShowCropInfo extends L2GameServerPacket
{
	private static final String _S__FE_1C_EXSHOWSEEDINFO = "[S] FE:24 ExShowCropInfo";
	private List<CropProcure> _crops;
	private int _manorId;

	public ExShowCropInfo(int manorId, List<CropProcure> crops)
	{
		_manorId = manorId;
		_crops = crops;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xFE);     // Id
		writeH(0x24);     // SubId
		writeC(0);
		writeD(_manorId); // Manor ID
		writeD(0);
		if (_crops == null)
		{
			writeD(0);
			return;
		}
		writeD(_crops.size());
		for (CropProcure crop : _crops)
		{
			writeD(crop.getId());          // Crop id
			writeQ(crop.getAmount());      // Buy residual
			writeQ(crop.getStartAmount()); // Buy
			writeQ(crop.getPrice());       // Buy price
			writeC(crop.getReward());      // Reward
			writeD(L2Manor.getInstance().getSeedLevelByCrop(crop.getId())); // Seed Level
			writeC(1); // rewrad 1 Type
			writeD(L2Manor.getInstance().getRewardItem(crop.getId(),1));    // Rewrad 1 Type Item Id
			writeC(1); // rewrad 2 Type
			writeD(L2Manor.getInstance().getRewardItem(crop.getId(),2));    // Rewrad 2 Type Item Id
		}
	}

	@Override
	public String getType()
	{
		return _S__FE_1C_EXSHOWSEEDINFO;
	}
}