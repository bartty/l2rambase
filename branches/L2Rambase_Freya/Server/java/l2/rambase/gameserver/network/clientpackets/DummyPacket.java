/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;


public final class DummyPacket extends L2GameClientPacket
{
	//private static Logger _log = Logger.getLogger(DummyPacket.class.getName());

	@Override
	protected void readImpl()
	{

	}

	@Override
	public void runImpl()
	{

	}

	@Override
	public String getType()
	{
		return "DummyPacket";
	}
}
