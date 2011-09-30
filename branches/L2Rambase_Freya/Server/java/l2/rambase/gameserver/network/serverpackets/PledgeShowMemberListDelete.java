/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class PledgeShowMemberListDelete extends L2GameServerPacket
{
	private static final String _S__6B_PLEDGESHOWMEMBERLISTDELETE = "[S] 5d PledgeShowMemberListDelete";
	private String _player;

	public PledgeShowMemberListDelete(String playerName)
	{
		_player = playerName;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x5d);
		writeS(_player);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__6B_PLEDGESHOWMEMBERLISTDELETE;
	}
}