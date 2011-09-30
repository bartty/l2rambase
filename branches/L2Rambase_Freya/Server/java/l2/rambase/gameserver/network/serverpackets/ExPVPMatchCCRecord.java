/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Sent at the end of Crateis Cube OR when you click the "Match results" icon during
 * the match.
 */
public class ExPVPMatchCCRecord extends L2GameServerPacket
{
	private static final String _S__FE_89_EXPVPMATCHCCRECORD = "[S] FE:89 ExPVPMatchCCRecord";
	public static final CCPlayer[] EMPTY_ARRAY = new CCPlayer[0];

	private final int _state;
	private final CCPlayer[] _players;

	public ExPVPMatchCCRecord(int state, CCPlayer[] players)
	{
		_state = state;
		_players = players;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x89);

		writeD(_state); // 0x01 - in progress, 0x02 - finished
		writeD(_players.length);
		for (CCPlayer ccp : _players)
		{
			writeS(ccp.getName());
			writeD(ccp.getPoints());
		}
	}

	@Override
	public String getType()
	{
		return _S__FE_89_EXPVPMATCHCCRECORD;
	}

	/** Example of usage */
	public class CCPlayer {
		private final String _name;
		private int _points;

		public CCPlayer(String name) {
			_name = name;
			_points = 0;
		}

		public final String getName() {
			return _name;
		}

		public final int getPoints() {
			return _points;
		}

		public final void setPoints(int points) {
			_points = points;
		}

		public final void addPoints(int points) {
			_points += points;
		}
	}
}