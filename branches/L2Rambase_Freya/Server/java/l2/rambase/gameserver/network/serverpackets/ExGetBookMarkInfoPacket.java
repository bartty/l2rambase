/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance.TeleportBookmark;


/**
 * @Structure d dd (ddddSdS)
 */
public class ExGetBookMarkInfoPacket extends L2GameServerPacket
{
	private static final String _S__FE_84_EXGETBOOKMARKINFOPACKET = "[S] FE:84 ExGetBookMarkInfoPacket";
	
	private L2PcInstance player;
	public ExGetBookMarkInfoPacket(L2PcInstance cha)
	{
		player = cha;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xFE);
		writeH(0x84);
		writeD(0x00); // Dummy
		writeD(player._bookmarkslot);
		writeD(player.tpbookmark.size());
      

		for (TeleportBookmark tpbm : player.tpbookmark) 
		{
			writeD(tpbm._id);
			writeD(tpbm._x);
			writeD(tpbm._y);
			writeD(tpbm._z);
			writeS(tpbm._name);
			writeD(tpbm._icon);
			writeS(tpbm._tag);
		}
	}

	@Override
	public String getType()
	{
		return _S__FE_84_EXGETBOOKMARKINFOPACKET;
	}
}