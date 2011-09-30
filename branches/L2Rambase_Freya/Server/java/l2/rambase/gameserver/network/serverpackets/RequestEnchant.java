/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class RequestEnchant extends L2GameServerPacket
{
	private int _unk;
	private static final String _S__FE_81_REQUESTENCHANT = "[S] FE:81 RequestEnchant";
	
	public RequestEnchant(int value)
	{
		_unk = value;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_81_REQUESTENCHANT;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x81);
		writeD(_unk);
	}
}