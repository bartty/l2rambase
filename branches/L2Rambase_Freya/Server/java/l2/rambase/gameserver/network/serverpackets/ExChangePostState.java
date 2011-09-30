/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExChangePostState extends L2GameServerPacket
{
	private static final String _S__FE_B3_EXCHANGEPOSTSTATE = "[S] B3 ExChangePostState";

	private boolean _receivedBoard;
	private int[] _changedMsgIds;
	private int _changeId;

	public ExChangePostState(boolean receivedBoard, int[] changedMsgIds, int changeId)
	{
		_receivedBoard = receivedBoard;
		_changedMsgIds = changedMsgIds;
		_changeId = changeId;
	}

	public ExChangePostState(boolean receivedBoard, int changedMsgId, int changeId)
	{
		_receivedBoard = receivedBoard;
		_changedMsgIds = new int[]{changedMsgId};
		_changeId = changeId;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xb3);
		writeD(_receivedBoard ? 1 : 0);
		writeD(_changedMsgIds.length);
		for (int postId : _changedMsgIds)
		{
			writeD(postId); // postId
			writeD(_changeId); // state
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_B3_EXCHANGEPOSTSTATE;
	}
}