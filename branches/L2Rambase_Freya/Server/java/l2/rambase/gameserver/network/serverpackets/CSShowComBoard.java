/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class CSShowComBoard extends L2GameServerPacket
{
	private static final String _S__6E_SHOWBOARD = "[S] 7b ShowBoard";
	
	private final byte[] _html;
	
	public CSShowComBoard(final byte[] html)
	{
		_html = html;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0x7b);
		writeC(0x01); //c4 1 to show community 00 to hide
		writeB(_html);
	}
	
	@Override
	public String getType()
	{
		return _S__6E_SHOWBOARD;
	}
}