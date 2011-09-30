/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class NormalCamera extends L2GameServerPacket
{
    private static final String _S__C8_NORMALCAMERA = "[S] d7 NormalCamera";

    public NormalCamera(){}

    @Override
	public void writeImpl()
    {
        writeC(0xd7);
    }

    @Override
	public String getType()
    {
        return _S__C8_NORMALCAMERA;
    }
}