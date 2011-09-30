/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class VehicleStarted extends L2GameServerPacket
{
	private int _objectId;
	private int _state;
		
	public VehicleStarted(L2Character boat, int state)
	{
		_objectId = boat.getObjectId();;
		_state = state;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xC0);
		writeD(_objectId);
		writeD(_state);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] C0 VehicleStarted";
	}	
}