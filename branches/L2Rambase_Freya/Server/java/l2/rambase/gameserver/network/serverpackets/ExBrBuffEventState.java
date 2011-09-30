/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Eva's Inferno event packet.
 * Format: (ch)dddd // time info params: type (1 - %, 2 - npcId), value (depending on type: for type 1 - % value; for type 2 - 20573-20575), state (0-1), endtime (only when type 2)
 */
public class ExBrBuffEventState extends L2GameServerPacket
{
	private int _type; // 1 - %, 2 - npcId
	private int _value; // depending on type: for type 1 - % value; for type 2 - 20573-20575
	private int _state; // 0-1
	private int _endtime; // only when type 2 as unix time in seconds from 1970
	
	public ExBrBuffEventState(int type, int value, int state, int endtime)
	{
		_type = type;
		_value = value;
		_state = state;
		_endtime = endtime;
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:BF ExBrBuffEventState";
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xbf);
		writeD(_type);
		writeD(_value);
		writeD(_state);
		writeD(_endtime);
	}
	
}
