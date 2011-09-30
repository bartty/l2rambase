/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.util.Util;


public class OnEventTrigger extends L2GameServerPacket
{
	private final int _emitterId;
	private final boolean _opened;
	
	private static final int[] _reverse_doors = { 16200023, 16200024, 16200025 };
	
	public OnEventTrigger(L2DoorInstance door, boolean opened)
	{
		_emitterId = door.getEmitter();
		
		if(Util.contains(_reverse_doors, door.getDoorId()))
			_opened = !opened;
		else
			_opened = opened;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xCF);
		writeD(_emitterId);
		writeD(_opened ? 0 : 1);
	}
	
	@Override
	public String getType()
	{
		return "[S] CF OnEventTrigger".intern();
	}
}