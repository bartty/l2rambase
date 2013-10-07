package lineage2.gameserver.network.serverpackets;

import lineage2.gameserver.skills.TimeStamp;

public class ExUseSharedGroupItem extends L2GameServerPacket
{
	private int _itemId, _grpId, _remainedTime, _totalTime;

	public ExUseSharedGroupItem(int grpId, TimeStamp timeStamp)
	{
		_grpId = grpId;
		_itemId = timeStamp.getId();
		_remainedTime = (int) (timeStamp.getReuseCurrent() / 1000);
		_totalTime = (int) (timeStamp.getReuseBasic() / 1000);
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0x4B);

		writeD(_itemId);
		writeD(_grpId);
		writeD(_remainedTime);
		writeD(_totalTime);
	}
}