/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExBrExtraUserInfo extends L2GameServerPacket
{
	private int _charObjId;
	private int _val;

	public ExBrExtraUserInfo(L2PcInstance player)
	{
		_charObjId = player.getObjectId();
		_val = player.getEventEffectId();
		_invisible = player.getAppearance().getInvisible();
	}

	@Override
	/**
	 * This packet should belong to Quest windows, not UserInfo in T3.
	 */
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0xbe);
		writeD(_charObjId);  //object ID of Player
		writeD(_val);  //	event effect id
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:BE ExBrExtraUSerInfo";
	}
}
