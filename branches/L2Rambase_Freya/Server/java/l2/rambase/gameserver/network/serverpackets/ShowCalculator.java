/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ShowCalculator extends L2GameServerPacket
{
	private static final String _S__DC_SHOWCALCULATOR = "[S] e2 ShowCalculator";
	private int _calculatorId;

	/**
	 */
	public ShowCalculator(int calculatorId)
	{
		_calculatorId = calculatorId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xe2);
		writeD(_calculatorId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__DC_SHOWCALCULATOR;
	}
}