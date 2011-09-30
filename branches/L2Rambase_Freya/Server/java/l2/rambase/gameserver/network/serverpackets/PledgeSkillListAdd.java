/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class PledgeSkillListAdd extends L2GameServerPacket
{
	private static final String _S__FE_3A_PLEDGESKILLLISTADD = "[S] FE:3b PledgeSkillListAdd";
	private int _id;
	private int _lvl;

	public PledgeSkillListAdd(int id, int lvl)
	{
		_id = id;
		_lvl = lvl;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x3b);

		writeD(_id);
		writeD(_lvl);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_3A_PLEDGESKILLLISTADD;
	}
}