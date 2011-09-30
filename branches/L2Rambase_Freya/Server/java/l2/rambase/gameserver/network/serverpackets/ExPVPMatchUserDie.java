/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Should be sent just after ExPVPMatchRecord.<BR>
 * This packet specifies the main score shown in the dialog
 * opened by the mentioned packet, where _t1 is in Blue color
 * and _t2 is in orange.<BR>
 * What is important, it also newly decides the WIN/LOSE status,
 * so with a specifically crafted ExPVPMatchRecord you can have
 * such a dialog:<BR>
 * WIN 13:10 LWIN (LOSE is below WIN)
 */
public class ExPVPMatchUserDie extends L2GameServerPacket
{
	private static final String _S__FE_7F_EXPVPMATCHUSERDIE = "[S] FE:7F ExPVPMatchUserDie";

	private final int _t1;
	private final int _t2;

	public ExPVPMatchUserDie(int t1Score, int t2Score)
	{
		_t1 = t1Score;
		_t2 = t2Score;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x7e);

		writeD(_t1);
		writeD(_t2);
	}

	@Override
	public String getType()
	{
		return _S__FE_7F_EXPVPMATCHUSERDIE;
	}
}
