/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class ShortBuffStatusUpdate extends L2GameServerPacket
{
	private static final String _S__F4_SHORTBUFFSTATUSUPDATE = "[S] fa ShortBuffStatusUpdate";
	private int _skillId;
	private int _skillLvl;
	private int _duration;

	public ShortBuffStatusUpdate(int skillId, int skillLvl, int duration)
	{
		_skillId = skillId;
		_skillLvl = skillLvl;
		_duration = duration;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfa);
		writeD(_skillId);
		writeD(_skillLvl);
		writeD(_duration);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__F4_SHORTBUFFSTATUSUPDATE;
	}
}