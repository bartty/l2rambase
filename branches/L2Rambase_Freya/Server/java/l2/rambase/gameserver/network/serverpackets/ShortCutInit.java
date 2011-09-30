/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ShortCut;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class ShortCutInit extends L2GameServerPacket
{
	private static final String _S__57_SHORTCUTINIT = "[S] 45 ShortCutInit";

	private L2ShortCut[] _shortCuts;
    private L2PcInstance _activeChar;

	public ShortCutInit(L2PcInstance activeChar)
	{
        _activeChar = activeChar;

        if (_activeChar == null)
			return;

		_shortCuts = _activeChar.getAllShortCuts();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x45);
		writeD(_shortCuts.length);

		for (L2ShortCut sc: _shortCuts)
		{
		    writeD(sc.getType());
            writeD(sc.getSlot() + sc.getPage() * 12);

            switch(sc.getType())
            {
            case L2ShortCut.TYPE_ITEM: //1
            	writeD(sc.getId());
            	writeD(0x01);
            	writeD(sc.getSharedReuseGroup());
            	writeD(0x00);
            	writeD(0x00);
            	writeH(0x00);
            	writeH(0x00);
            	break;
            case L2ShortCut.TYPE_SKILL: //2
            	writeD(sc.getId());
            	writeD(sc.getLevel());
            	writeC(0x00); // C5
            	writeD(0x01); // C6
            	break;
            case L2ShortCut.TYPE_ACTION: //3
            	writeD(sc.getId());
            	writeD(0x01); // C6
            	break;
            case L2ShortCut.TYPE_MACRO: //4
            	writeD(sc.getId());
            	writeD(0x01); // C6
            	break;
            case L2ShortCut.TYPE_RECIPE: //5
            	writeD(sc.getId());
            	writeD(0x01); // C6
            	break;
            default:
            	writeD(sc.getId());
        		writeD(0x01); // C6
            }
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__57_SHORTCUTINIT;
	}
}