/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPutEnchantTargetItemResult extends L2GameServerPacket
{
	private static final String _S__81_EXPUTENCHANTTARGETITEMRESULT = "[S] 81 ExPutEnchantTargetItemResult";
	
	private int _result;
	
	public ExPutEnchantTargetItemResult(int result)
	{
		_result = result;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__81_EXPUTENCHANTTARGETITEMRESULT;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x81);
		writeD(_result);
	}
}