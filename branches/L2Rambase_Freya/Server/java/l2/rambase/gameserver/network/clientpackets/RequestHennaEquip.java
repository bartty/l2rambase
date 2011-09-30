/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.HennaTable;
import l2.rambase.gameserver.datatables.HennaTreeTable;
import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.InventoryUpdate;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.item.L2Henna;
import l2.rambase.gameserver.util.Util;

public final class RequestHennaEquip extends L2GameClientPacket
{
	private static final String _C__BC_RequestHennaEquip = "[C] bc RequestHennaEquip";
	private int _symbolId;
	// format  cd

	/**
	 * packet type id 0xbb
	 * format:		cd
	 * @param decrypt
	 */
	@Override
	protected void readImpl()
	{
		_symbolId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
		    return;

		L2Henna template = HennaTable.getInstance().getTemplate(_symbolId);

		if (template == null)
			return;

		L2HennaInstance henna = new L2HennaInstance(template);
		long _count = 0;

		/**
		 *  Prevents henna drawing exploit: 
		 * 1) talk to L2SymbolMakerInstance 
		 * 2) RequestHennaList
		 * 3) Don't close the window and go to a GrandMaster and change your subclass
		 * 4) Get SymbolMaker range again and press draw
		 * You could draw any kind of henna just having the required subclass...
		 */
		boolean cheater = true;
		for (L2HennaInstance h : HennaTreeTable.getInstance().getAvailableHenna(activeChar.getClassId()))
		{
			if (h.getSymbolId() == henna.getSymbolId()) 
			{
				cheater = false;
				break;
			}
		}
		try
		{
			_count = activeChar.getInventory().getItemByItemId(henna.getItemIdDye()).getCount();
		}
		catch(Exception e){}

		if (activeChar.getHennaEmptySlots() == 0)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.SYMBOLS_FULL));
			return;
		}

		if (!cheater && (_count >= henna.getAmountDyeRequire()) && (activeChar.getAdena() >= henna.getPrice()) && activeChar.addHenna(henna))
		{
			activeChar.destroyItemByItemId("Henna", henna.getItemIdDye(), henna.getAmountDyeRequire(), activeChar, true);

			activeChar.getInventory().reduceAdena("Henna", henna.getPrice(), activeChar, activeChar.getLastFolkNPC());

			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(activeChar.getInventory().getAdenaInstance());
			activeChar.sendPacket(iu);

			activeChar.sendPacket(new SystemMessage(SystemMessageId.SYMBOL_ADDED));
		}
		else
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_DRAW_SYMBOL));
			if ((!activeChar.isGM()) && (cheater))
				Util.handleIllegalPlayerAction(activeChar,"Exploit attempt: Character "+activeChar.getName()+" of account "+activeChar.getAccountName()+" tryed to add a forbidden henna.",Config.DEFAULT_PUNISH);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__BC_RequestHennaEquip;
	}
}
