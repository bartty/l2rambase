package lineage2.gameserver.network.serverpackets;

public class ExShowLines extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xA6);
		// TODO hdcc cx[ddd]
	}
}