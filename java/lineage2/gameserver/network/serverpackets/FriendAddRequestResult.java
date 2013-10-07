package lineage2.gameserver.network.serverpackets;

public class FriendAddRequestResult extends L2GameServerPacket
{
	public FriendAddRequestResult()
	{
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x55);
		// TODO: when implementing, consult an up-to-date
		// packets_game_server.xml and/or savormix
		writeD(0); // Accepted
		writeD(0); // Character ID
		writeS(""); // Name
		writeD(0); // Online
		writeD(0); // Friend OID
		writeD(0); // Level
		writeD(0); // Class
		writeH(0); // ??? 0
	}
}
