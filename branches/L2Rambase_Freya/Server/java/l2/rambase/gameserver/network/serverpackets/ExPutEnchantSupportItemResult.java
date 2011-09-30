/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPutEnchantSupportItemResult extends L2GameServerPacket
{
	private static final String _S__82_EXPUTENCHANTSUPPORTITEMRESULT = "[S] 82 ExPutEnchantSupportItemResult";
	
	private int _result;

	public ExPutEnchantSupportItemResult(int result)
	{
		_result = result;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__82_EXPUTENCHANTSUPPORTITEMRESULT;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x82);
		writeD(_result);
	}
}