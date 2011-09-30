/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExAskJoinPartyRoom extends L2GameServerPacket
{
	private static final String _S__FE_34_EXASKJOINPARTYROOM = "[S] FE:35 ExAskJoinPartyRoom";
	private String _charName;


	public ExAskJoinPartyRoom(String charName)
	{
		_charName = charName;
	}


	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x35);
		writeS(_charName);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_34_EXASKJOINPARTYROOM;
	}
}