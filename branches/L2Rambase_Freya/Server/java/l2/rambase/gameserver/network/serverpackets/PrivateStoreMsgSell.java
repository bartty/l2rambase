/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PrivateStoreMsgSell extends L2GameServerPacket
{
	private static final String _S__B5_PRIVATESTOREMSGSELL = "[S] a2 PrivateStoreMsgSell";
	private int _objId;
	private String _storeMsg;
	
	public PrivateStoreMsgSell(L2PcInstance player)
	{
		_objId = player.getObjectId();
		if (player.getSellList() != null)
			_storeMsg = player.getSellList().getTitle();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xa2);
		writeD(_objId);
		writeS(_storeMsg);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__B5_PRIVATESTOREMSGSELL;
	}
}