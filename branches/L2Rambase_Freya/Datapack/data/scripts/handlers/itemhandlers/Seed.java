/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.itemhandlers;

import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.handler.IItemHandler;
import l2.rambase.gameserver.instancemanager.CastleManorManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Rambaseanor;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2ChestInstance;
import l2.rambase.gameserver.model.actor.instance.L2RambaseonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.SkillHolder;

/**
 * @author  l3x
 */
public class Seed implements IItemHandler
{	
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IItemHandler#useItem(l2.rambase.gameserver.model.actor.L2Playable, l2.rambase.gameserver.model.L2ItemInstance)
	 */
	public void useItem(L2Playable playable, L2ItemInstance item)
	{
		if (!(playable instanceof L2PcInstance))
			return;

		if (CastleManorManager.getInstance().isDisabled())
			return;

		final L2Object tgt = playable.getTarget();
		if (!(tgt instanceof L2Npc))
		{
			playable.sendPacket(new SystemMessage(SystemMessageId.INCORRECT_TARGET));
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if (!(tgt instanceof L2RambaseonsterInstance) || tgt instanceof L2ChestInstance || ((L2Character)tgt).isRaid())
		{
			playable.sendPacket(new SystemMessage(SystemMessageId.THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING));
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final L2RambaseonsterInstance target = (L2RambaseonsterInstance)tgt;		
		if (target.isDead())
		{
			playable.sendPacket(new SystemMessage(SystemMessageId.INCORRECT_TARGET));
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (target.isSeeded())
		{
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final int seedId = item.getItemId();
		if (areaValid(seedId, MapRegionTable.getInstance().getAreaCastle(playable)))
		{
			target.setSeeded(seedId, (L2PcInstance)playable);
			final SkillHolder[] skills = item.getEtcItem().getSkills();
			if (skills != null)
			{
				if(skills[0] == null)
					return;
				
				L2Skill itemskill = skills[0].getSkill();
				((L2PcInstance)playable).useMagic(itemskill, false, false);
			}
			
		}
		else
			playable.sendPacket(new SystemMessage(SystemMessageId.THIS_SEED_MAY_NOT_BE_SOWN_HERE));
	}
	
	/**
	 * 
	 * @param seedId
	 * @param castleId
	 * @return
	 */
	private boolean areaValid(int seedId, int castleId)
	{
		return (L2Rambaseanor.getInstance().getCastleIdForSeed(seedId) == castleId);
	}
}