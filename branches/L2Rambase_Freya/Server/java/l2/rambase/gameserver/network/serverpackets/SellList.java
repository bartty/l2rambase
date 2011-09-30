/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2MerchantInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import javolution.util.FastList;


public class SellList extends L2GameServerPacket
{
	private static final String _S__10_SELLLIST = "[S] 06 SellList";
	private static Logger _log = Logger.getLogger(SellList.class.getName());
	private final L2PcInstance _activeChar;
	private final L2MerchantInstance _lease;
	private long _money;
	private List<L2ItemInstance> _selllist = new FastList<L2ItemInstance>();

	public SellList(L2PcInstance player)
	{
		_activeChar = player;
		_lease = null;
		_money = _activeChar.getAdena();
		doLease();
	}

	public SellList(L2PcInstance player, L2MerchantInstance lease)
	{
		_activeChar = player;
		_lease = lease;
		_money = _activeChar.getAdena();
		doLease();
	}

	private void doLease()
	{
		if (_lease == null)
		{
			for (L2ItemInstance item : _activeChar.getInventory().getItems())
			{
				if (!item.isEquipped() &&														// Not equipped
                        item.isSellable() &&													// Item is sellable
                        (_activeChar.getPet() == null ||										// Pet not summoned or
                        item.getObjectId() != _activeChar.getPet().getControlItemId()))			// Pet is summoned and not the item that summoned the pet
				{
					_selllist.add(item);
					if (Config.DEBUG)
						_log.fine("item added to selllist: " + item.getItem().getName());
				}
			}
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x06);
		writeQ(_money);
		writeD(_lease == null ? 0x00 : 1000000 + _lease.getTemplate().npcId);
		writeH(_selllist.size());

		for (L2ItemInstance item : _selllist)
		{
			writeH(item.getItem().getType1());
			writeD(item.getObjectId());
			writeD(item.getItemId());
			writeQ(item.getCount());
			writeH(item.getItem().getType2());
			writeH(0x00);
			writeD(item.getItem().getBodyPart());
			writeH(item.getEnchantLevel());
			writeH(0x00);
			writeH(item.getCustomType2());
			writeQ(item.getItem().getReferencePrice()/2);

			// T1
			writeH(item.getAttackElementType());
			writeH(item.getAttackElementPower());
			for (byte i = 0; i < 6; i++)
				writeH(item.getElementDefAttr(i));

			writeH(0x00); // Enchant effect 1
			writeH(0x00); // Enchant effect 2
			writeH(0x00); // Enchant effect 3 
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__10_SELLLIST;
	}
}