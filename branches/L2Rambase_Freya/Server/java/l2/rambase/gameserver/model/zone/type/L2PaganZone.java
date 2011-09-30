/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class L2PaganZone extends L2ZoneType
{
	public L2PaganZone(int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			L2PcInstance player = (L2PcInstance) character;
			L2ItemInstance item = player.getInventory().getItemByItemId(8064);
			if (item != null)
			{
				player.destroyItemByItemId("Mark", 8064, 1, player, true);
				L2ItemInstance fadedMark = player.getInventory().addItem("Faded Mark", 8065, 1, player, player);

				SystemMessage ms = new SystemMessage(SystemMessageId.EARNED_ITEM);
				ms.addItemName(fadedMark);
				player.sendPacket(ms);
			}
		}
	}

	@Override
	protected void onExit(L2Character character)
	{
	}

	@Override
	public void onDieInside(L2Character character)
	{
	}

	@Override
	public void onReviveInside(L2Character character)
	{
	}
}