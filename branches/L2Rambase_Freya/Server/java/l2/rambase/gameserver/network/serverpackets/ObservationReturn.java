/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ObservationReturn extends L2GameServerPacket
{
	private static final String _S__E0_OBSERVRETURN = "[S] ec ObservationReturn";
	private L2PcInstance _activeChar;

	/**
	 * @param _characters
	 */
	public ObservationReturn(L2PcInstance observer)
	{
		_activeChar = observer;
	}

	@Override
	protected final void writeImpl()
	{
		writeC( 0xec );
		writeD( _activeChar.getObsX() );
		writeD( _activeChar.getObsY() );
		writeD( _activeChar.getObsZ() );
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__E0_OBSERVRETURN;
	}
}