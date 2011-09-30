/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: ch Sd
 */
public class ExDuelAskStart extends L2GameServerPacket
{
	private static final String _S__FE_4B_EXDUELASKSTART = "[S] FE:4c ExDuelAskStart";

	private String _requestorName;
	private int _partyDuel;

	public ExDuelAskStart(String requestor, int partyDuel)
	{
		_requestorName = requestor;
		_partyDuel = partyDuel;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x4c);

		writeS(_requestorName);
		writeD(_partyDuel);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_4B_EXDUELASKSTART;
	}
}