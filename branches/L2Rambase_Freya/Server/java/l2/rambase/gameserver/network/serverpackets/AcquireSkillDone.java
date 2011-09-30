/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class AcquireSkillDone extends L2GameServerPacket
{
	public AcquireSkillDone()
	{
		
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x94);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] 94 AcquireSkillDone";
	}
}