/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2ShortCut;


public final class ShortCutRegister extends L2GameServerPacket
{
	private static final String _S__56_SHORTCUTREGISTER = "[S] 44 ShortCutRegister";

    private L2ShortCut _shortcut;

	/**
	 * Register new skill shortcut
	 * @param slot
	 * @param type
	 * @param typeId
	 * @param level
	 * @param dat2
	 */
	public ShortCutRegister(L2ShortCut shortcut)
	{
		_shortcut = shortcut;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x44);

		writeD(_shortcut.getType());
		writeD(_shortcut.getSlot() + _shortcut.getPage() * 12); // C4 Client
		switch(_shortcut.getType())
        {
        case L2ShortCut.TYPE_ITEM: //1
        	writeD(_shortcut.getId());
        	writeD(_shortcut.getCharacterType());
        	writeD(_shortcut.getSharedReuseGroup());
        	writeD(0x00);  // unknown
        	writeD(0x00);  // unknown
        	writeD(0x00);  // item augment id
        	break;
        case L2ShortCut.TYPE_SKILL: //2
        	writeD(_shortcut.getId());
        	writeD(_shortcut.getLevel());
        	writeC(0x00); // C5
        	writeD(_shortcut.getCharacterType());
        	break;
       /** these are same as default case, no need to duplicate, enable if packet get changed
        */ 
       /*	case L2ShortCut.TYPE_ACTION: //3
        *		writeD(_shortcut.getId());
        *		writeD(_shortcut.getUserCommand());
        *		break;
        *	case L2ShortCut.TYPE_MACRO: //4
        *		writeD(_shortcut.getId());
        *		writeD(_shortcut.getUserCommand());
        *		break;
        *	case L2ShortCut.TYPE_RECIPE: //5
        *		writeD(_shortcut.getId());
        *		writeD(_shortcut.getUserCommand());
        *		break;
        */
        default:
        {
        	writeD(_shortcut.getId());
        	writeD(_shortcut.getCharacterType());
        }
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__56_SHORTCUTREGISTER;
	}
}