/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * format: dc
 * 
 */
public class ExBrPremiumState extends L2GameServerPacket
{
	private static final String _S__FE_BC_EXBRPREMIUMSTATE = "[S] FE:BC ExBrPremiumState";
	private L2PcInstance _activeChar;
	private int _state;
	
	public ExBrPremiumState(L2PcInstance activeChar, int state)
	{
		_activeChar = activeChar;
		_state = state;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xbc);
		writeD(_activeChar.getObjectId());
		writeC(_state);
	}
	
	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_BC_EXBRPREMIUMSTATE;
	}
}