/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Sent whenever you forfeit Crateis Cube registration.
 */
public class ExPVPMatchCCRetire extends L2GameServerPacket
{
	private static final String _S__EXPVPMATCHCCRETIRE = "[S] FE:8B ExPVPMatchCCRetire ch";
	public static final ExPVPMatchCCRetire PACKET = new ExPVPMatchCCRetire();

	private ExPVPMatchCCRetire()
	{
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x8b);
	}

	@Override
	public String getType()
	{
		return _S__EXPVPMATCHCCRETIRE;
	}
}