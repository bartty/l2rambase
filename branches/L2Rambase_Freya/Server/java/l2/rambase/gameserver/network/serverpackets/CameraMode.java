/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class CameraMode extends L2GameServerPacket
{
    private static final String _S__F1_CAMERAMODE = "[S] f7 CameraMode";

    private int _mode;

    /**
     * Forces client camera mode change
     * @param mode
     * 0 - third person cam
     * 1 - first person cam
     */
    public CameraMode(int mode)
    {
        _mode = mode;
    }

    @Override
	public void writeImpl()
    {
        writeC(0xf7);
        writeD(_mode);
    }

    @Override
	public String getType()
    {
        return _S__F1_CAMERAMODE;
    }
}