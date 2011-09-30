/**
 * @author L2Rambase Project
 */
package handlers.itemhandlers;

import java.util.Collection;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SummonItemsData;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.handler.IItemHandler;
import l2.rambase.gameserver.handler.ISkillHandler.PetSummonFinalizer;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.L2SummonItem;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2XmassTreeInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.MagicSkillUse;
import l2.rambase.gameserver.network.serverpackets.SetupGauge;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.gameserver.util.Broadcast;

public class SummonItems implements IItemHandler
{
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IItemHandler#useItem(l2.rambase.gameserver.model.actor.L2Playable, l2.rambase.gameserver.model.L2ItemInstance)
	 */
	public void useItem(L2Playable playable, L2ItemInstance item)
	{
		if (!(playable instanceof L2PcInstance))
			return;

		if (!TvTEvent.onItemSummon(playable.getObjectId()))
			return;
		if (!PvPEvent.onItemSummon(playable.getObjectId()))
			return;

		final L2PcInstance activeChar = (L2PcInstance) playable;

		if (!activeChar.getFloodProtectors().getItemPetSummon().tryPerformAction("summon items"))
			return;

		if (activeChar.isSitting())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_MOVE_SITTING));
			return;
		}

		if (activeChar.inObserverMode())
			return;

		if (activeChar.isInOlympiadMode())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT));
			return;
		}
		if (activeChar.isAllSkillsDisabled() || activeChar.isCastingNow())
			return;

		final L2SummonItem sitem = SummonItemsData.getInstance().getSummonItem(item.getItemId());

		if ((activeChar.getPet() != null || activeChar.isMounted()) && sitem.isPetSummon())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_ALREADY_HAVE_A_PET));
			return;
		}

		if (activeChar.isAttackingNow())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.YOU_CANNOT_SUMMON_IN_COMBAT));
			return;
		}

		if (activeChar.isCursedWeaponEquipped() && sitem.isPetSummon())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.STRIDER_CANT_BE_RIDDEN_WHILE_IN_BATTLE));
			return;
		}

		final int npcId = sitem.getNpcId();
		if (npcId == 0)
			return;

		final L2NpcTemplate npcTemplate = NpcTable.getInstance().getTemplate(npcId);
		if (npcTemplate == null)
			return;

		activeChar.stopMove(null, false);

		switch (sitem.getType())
		{
			case 0: // static summons (like Christmas tree)
				try
				{
					Collection<L2Character> characters = activeChar.getKnownList().getKnownCharactersInRadius(1200);
					for (L2Character ch : characters)
					{
						if (ch instanceof L2XmassTreeInstance && npcTemplate.isSpecialTree())
						{
							SystemMessage sm = new SystemMessage(SystemMessageId.CANNOT_SUMMON_S1_AGAIN);
							sm.addCharName(ch);
							activeChar.sendPacket(sm);
							return;
						}
					}

					if (activeChar.destroyItem("Summon", item.getObjectId(), 1, null, false))
					{
						final L2Spawn spawn = new L2Spawn(npcTemplate);
						spawn.setLocx(activeChar.getX());
						spawn.setLocy(activeChar.getY());
						spawn.setLocz(activeChar.getZ());
						spawn.setInstanceId(activeChar.getInstanceId());
						spawn.stopRespawn();
						final L2Npc npc = spawn.spawnOne(true);
						npc.setTitle(activeChar.getName());
						npc.setIsRunning(false); // broadcast info
						if (sitem.getDespawnDelay() > 0)
							npc.scheduleDespawn(sitem.getDespawnDelay() * 1000L);
					}
				}
				catch (Exception e)
				{
					activeChar.sendPacket(new SystemMessage(SystemMessageId.TARGET_CANT_FOUND));
				}
				break;
			case 1: // pet summons
				final L2Object oldTarget = activeChar.getTarget();
				activeChar.setTarget(activeChar);
				Broadcast.toSelfAndKnownPlayers(activeChar, new MagicSkillUse(activeChar, 2046, 1, 5000, 0));
				activeChar.setTarget(oldTarget);
				activeChar.sendPacket(new SetupGauge(0, 5000));
				activeChar.sendPacket(new SystemMessage(SystemMessageId.SUMMON_A_PET));
				activeChar.setIsCastingNow(true);

				ThreadPoolManager.getInstance().scheduleGeneral(new PetSummonFinalizer(activeChar, npcTemplate, item), 5000);
				break;
			case 2: // wyvern
				activeChar.mount(sitem.getNpcId(), item.getObjectId(), true);
				break;
			case 3: // Great Wolf
				activeChar.mount(sitem.getNpcId(), item.getObjectId(), false);
				break;
		}
	}	
}
