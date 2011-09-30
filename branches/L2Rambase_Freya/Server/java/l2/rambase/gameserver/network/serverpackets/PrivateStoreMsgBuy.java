/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class PrivateStoreMsgBuy extends L2GameServerPacket
{
	private static final String _S__D2_PRIVATESTOREMSGBUY = "[S] bf PrivateStoreMsgBuy";
	private int _objId;
	private String _storeMsg;
	
	public PrivateStoreMsgBuy(L2PcInstance player)
	{
		_objId = player.getObjectId();
		if (player.getBuyList() != null)
			_storeMsg = player.getBuyList().getTitle();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xbf);
		writeD(_objId);
		writeS(_storeMsg);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D2_PRIVATESTOREMSGBUY;
	}	
}