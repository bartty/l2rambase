/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class PledgeShowMemberListUpdate extends L2GameServerPacket
{
	private static final String _S__54_PLEDGESHOWMEMBERLISTUPDATE = "[S] 5b PledgeShowMemberListUpdate";
	private int _pledgeType;
	private int _hasSponsor;
	private String _name;
	private int _level;
	private int _classId;
	private int _objectId;
	private boolean _isOnline;
    private int _race;
    private int _sex;

	public PledgeShowMemberListUpdate(L2PcInstance player)
	{
		_pledgeType = player.getPledgeType();
		if (_pledgeType == L2Clan.SUBUNIT_ACADEMY)
        {
			_hasSponsor = player.getSponsor() != 0 ? 1 : 0;
        }
		else
        {
			_hasSponsor = 0;
        }
		_name = player.getName();
		_level = player.getLevel();
		_classId = player.getClassId().getId();
        _race = player.getRace().ordinal();
        _sex = player.getAppearance().getSex() ? 1 : 0;
		_objectId = player.getObjectId();
		_isOnline = player.isOnline() == 1;
	}

	public PledgeShowMemberListUpdate(L2ClanMember member)
	{
	    _name = member.getName();
	    _level = member.getLevel();
	    _classId = member.getClassId();
	    _objectId = member.getObjectId();
	    _isOnline = member.isOnline();
	    _pledgeType = member.getPledgeType();
	    _race = member.getRaceOrdinal();
	    _sex = member.getSex() ? 1 : 0;
	    if (_pledgeType == L2Clan.SUBUNIT_ACADEMY)
	    {
	        _hasSponsor = member.getSponsor() != 0 ? 1 : 0;
	    }
	    else
	    {
	        _hasSponsor = 0;
	    }
	}


	@Override
	protected final void writeImpl()
	{
		writeC(0x5b);
		writeS(_name);
		writeD(_level);
		writeD(_classId);
		writeD(_sex);
        writeD(_race);
        if (_isOnline)
        {
            writeD(_objectId);
            writeD(_pledgeType);
        }
        else
        {
            // when going offline send as 0
            writeD(0);
            writeD(0);
        }
		writeD(_hasSponsor);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__54_PLEDGESHOWMEMBERLISTUPDATE;
	}
}