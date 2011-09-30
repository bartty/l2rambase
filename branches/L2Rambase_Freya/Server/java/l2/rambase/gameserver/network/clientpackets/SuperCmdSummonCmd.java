/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

/**
 * Format chS
 * c: (id) 0x39
 * h: (subid) 0x01
 * S: the summon name (or maybe cmd string ?)
 */
public class SuperCmdSummonCmd extends L2GameClientPacket
{
	private static final String _C__39_01_SUPERCMDSUMMONCMD = "[C] 39:01 SuperCmdSummonCmd";
	@SuppressWarnings("unused")
	private String _summonName;

	@Override
	protected void readImpl()
	{
		_summonName = readS();
	}

	@Override
	protected
	void runImpl()
	{
	}

	@Override
	public String getType()
	{
		return _C__39_01_SUPERCMDSUMMONCMD;
	}
}