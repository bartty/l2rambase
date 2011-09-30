/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import javolution.util.FastList;

/**
 * Format : (h) d [dS]
 * h  sub id
 *
 * d: number of manors
 * [
 * d: id
 * S: manor name
 * ]
 */
public class ExSendManorList extends L2GameServerPacket
{
	private static final String _S__FE_1B_EXSENDMANORLIST = "[S] FE:22 ExSendManorList";

	private List<String> _manors;

	public ExSendManorList(FastList<String> manors)
	{
		_manors = manors;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x22);
		writeD(_manors.size());
        int i = 1;
        for (String manor : _manors)
        {
            writeD(i++);
            writeS(manor);
        }
	}

	@Override
	public String getType()
    {
		return _S__FE_1B_EXSENDMANORLIST;
	}
}