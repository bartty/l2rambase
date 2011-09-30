/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public final class Ride extends L2GameServerPacket
{
	private static final String _S__8c_Ride = "[S] 8c Ride";
	public static final int ACTION_MOUNT = 1;
	public static final int ACTION_DISMOUNT = 0;
	private final int _id;
	private final int _bRide;
	private final int _rideType;
	private final int _rideClassID;
	private final int _x, _y, _z;
	
	public Ride(L2PcInstance cha, boolean mount, int rideClassId)
	{
		_id = cha.getObjectId();
		_bRide = mount ? 1 : 0;
		_rideClassID = rideClassId + 1000000; // npcID
		
		_x = cha.getX();
		_y = cha.getY();
		_z = cha.getZ();
		
		switch (rideClassId)
		{
			case 0: // dismount
				_rideType = 0;
				break;
			case 12526: // Wind
			case 12527: // Star
			case 12528: // Twilight
			case 16038: // red strider of wind
			case 16039: // red strider of star
			case 16040: // red strider of dusk
			case 16068: // Guardian Strider
				_rideType = 1;
				break;
			case 12621: // Wyvern
				_rideType = 2;
				break;
			case 16037: // Great Snow Wolf
			case 16041: // Fenrir Wolf
			case 16042: // White Fenrir Wolf
				_rideType = 3;
				break;
			case 32:    //Jet Bike 
			case 13130: // Light Purple Maned Horse
			case 13146: // Tawny-Maned Lion
			case 13147: // Steam Sledge
				_rideType = 4;
				break;
			default:
				throw new IllegalArgumentException("Unsupported mount NpcId: " + rideClassId);
		}
	}
	
	@Override
	public void runImpl()
	{
		
	}
	
	public int getMountType()
	{
		return _rideType;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x8c);
		writeD(_id);
		writeD(_bRide);
		writeD(_rideType);
		writeD(_rideClassID);
		writeD(_x);
		writeD(_y);
		writeD(_z);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__8c_Ride;
	}
}