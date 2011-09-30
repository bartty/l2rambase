/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class EnchantResult extends L2GameServerPacket
{
	private static final String _S__87_ENCHANTRESULT = "[S] 87 EnchantResult";

	private int _result;
	private int _crystal;
	private int _count;

	public EnchantResult(int result, int crystal, int count)
	{
		_result = result;
		_crystal = crystal;
		_count = count;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x87);
		writeD(_result);
		writeD(_crystal);
		writeQ(_count);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__87_ENCHANTRESULT;
	}
}