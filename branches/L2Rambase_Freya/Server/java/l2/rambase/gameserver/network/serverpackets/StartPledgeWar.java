/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class StartPledgeWar extends L2GameServerPacket
{
    private static final String _S__65_STARTPLEDGEWAR = "[S] 63 StartPledgeWar";
    private String _pledgeName;
    private String _playerName;

    public StartPledgeWar(String pledge, String charName)
    {
        _pledgeName = pledge;
        _playerName = charName;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0x63);
        writeS(_playerName);
        writeS(_pledgeName);
    }

    @Override
	public String getType()
    {
        return _S__65_STARTPLEDGEWAR;
    }
}