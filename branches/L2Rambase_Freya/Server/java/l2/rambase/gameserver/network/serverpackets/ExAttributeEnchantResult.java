/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class ExAttributeEnchantResult extends L2GameServerPacket
{
	private static final String _S__FE_61_EXATTRIBUTEENCHANTRESULT = "[S] FE:61 ExAttributeEnchantResult [d]";
	
	private int _result;
	
	public ExAttributeEnchantResult (int result)
	{
		_result = result;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x61);
		
		writeD(_result);
	}
	
	@Override
	public String getType()
	{
		return _S__FE_61_EXATTRIBUTEENCHANTRESULT;
	}
}