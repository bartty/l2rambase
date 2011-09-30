/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.instancemanager.TownManager;
import l2.rambase.gameserver.model.PartyMatchRoom;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExPartyRoomMember extends L2GameServerPacket
{
	private final PartyMatchRoom _room;
	private final int _mode;
	
	public ExPartyRoomMember(L2PcInstance player, PartyMatchRoom room, int mode)
    {
        _room = room;
        _mode = mode;
    }

    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x08);
        writeD(_mode);
        writeD(_room.getMembers());
        for(L2PcInstance _member : _room.getPartyMembers())
        {
        	writeD(_member.getObjectId());
        	writeS(_member.getName());
        	writeD(_member.getActiveClass());
        	writeD(_member.getLevel());
        	writeD(TownManager.getClosestLocation(_member));
        	if (_room.getOwner().equals(_member))
        		writeD(1);
        	else
        	{
        		if((_room.getOwner().isInParty() && _member.isInParty()) && (_room.getOwner().getParty().getPartyLeaderOID() == _member.getParty().getPartyLeaderOID()))
        			writeD(2);
        		else
        			writeD(0);
        	}
        }
    }

    @Override
    public String getType()
    {
        return "[S] FE:08 ExPartyRoomMember";
    }
}