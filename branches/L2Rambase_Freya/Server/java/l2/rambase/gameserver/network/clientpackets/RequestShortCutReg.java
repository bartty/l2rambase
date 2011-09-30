/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2ShortCut;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ShortCutRegister;


public final class RequestShortCutReg extends L2GameClientPacket
{
	private static final String _C__33_REQUESTSHORTCUTREG = "[C] 33 RequestShortCutReg";
	
	private int _type;
	private int _id;
	private int _slot;
	private int _page;
	private int _lvl;
	private int _characterType; // 1 - player, 2 - pet
	
	@Override
	protected void readImpl()
	{
		_type = readD();
		int slot = readD();
		_id = readD();
		_lvl = readD();
		_characterType = readD();
		
		_slot = slot % 12;
		_page = slot / 12;
	}
	
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		if (_page > 10 || _page < 0)
			return;
		
		switch (_type)
		{
			case 0x01: // item
			case 0x02: // skill
			{
				L2ShortCut sc = new L2ShortCut(_slot, _page, _type, _id, _lvl, _characterType);
				activeChar.registerShortCut(sc);
				sendPacket(new ShortCutRegister(sc));
				break;
			}
			case 0x03: // action
			case 0x04: // macro
			case 0x05: // recipe
			{
				L2ShortCut sc = new L2ShortCut(_slot, _page, _type, _id, _lvl, _characterType);
				activeChar.registerShortCut(sc);
				sendPacket(new ShortCutRegister(sc));
				break;
			}
			case 0x06: // Teleport Bookmark
			{
				L2ShortCut sc = new L2ShortCut(_slot, _page, _type, _id, _lvl, _characterType);
				activeChar.registerShortCut(sc);
				sendPacket(new ShortCutRegister(sc));
				break;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__33_REQUESTSHORTCUTREG;
	}
}
