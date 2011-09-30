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
package handlers.skillhandlers;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.ExtractableSkillsData;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.handler.ISkillHandler;
import l2.rambase.gameserver.model.L2ExtractableProductItem;
import l2.rambase.gameserver.model.L2ExtractableSkill;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.skills.L2SkillType;
import l2.rambase.util.Rnd;

public class Extractable implements ISkillHandler
{
	private static final L2SkillType[] SKILL_IDS =
	{
		L2SkillType.EXTRACTABLE,
		L2SkillType.EXTRACTABLE_FISH
	};
	
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#useSkill(l2.rambase.gameserver.model.actor.L2Character, l2.rambase.gameserver.model.L2Skill, l2.rambase.gameserver.model.L2Object[])
	 */
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
	{
		if (!(activeChar instanceof L2PcInstance))
			return;
		
		L2PcInstance player = (L2PcInstance)activeChar;
		L2ExtractableSkill exitem = ExtractableSkillsData.getInstance().getExtractableItem(skill);
		
		if (exitem == null)
			return;
		
		int rndNum = Rnd.get(100), chanceFrom = 0;
		int[] createItemID = new int[20];
		int[] createAmount = new int[20];
		
		
		// calculate extraction
		for (L2ExtractableProductItem expi : exitem.getProductItemsArray())
		{
			int chance = expi.getChance();
			
			if (rndNum >= chanceFrom && rndNum <= chance + chanceFrom)
			{
				for (int i = 0; i < expi.getId().length; i++)
				{
					createItemID[i] = expi.getId()[i];

					if (skill.getSkillType() == L2SkillType.EXTRACTABLE_FISH)
						createAmount[i] = (int)(expi.getAmmount()[i]* Config.RATE_EXTR_FISH);
					else
						createAmount[i] = expi.getAmmount()[i];
				}
				break;
			}
			
			chanceFrom += chance;
		}
		if (player.isSubClassActive() && skill.getReuseDelay() > 0)
		{
			player.sendPacket(new SystemMessage(SystemMessageId.MAIN_CLASS_SKILL_ONLY));
			player.sendPacket(new SystemMessage(SystemMessageId.S1_CANNOT_BE_USED).addSkillName(skill));
			return;
		}
		if (createItemID[0] <= 0 || createItemID.length == 0 )
		{
			player.sendPacket(new SystemMessage(SystemMessageId.NOTHING_INSIDE_THAT));
			return;
		}
		else
		{
			for (int i = 0; i < createItemID.length; i++)
			{
				if (createItemID[i] <= 0)
					return;
						
				if (ItemTable.getInstance().createDummyItem(createItemID[i]) == null)
				{
					_log.warning("createItemID " + createItemID[i] + " doesn't have template!");
					player.sendPacket(new SystemMessage(SystemMessageId.NOTHING_INSIDE_THAT));
					return;
				}

				if (ItemTable.getInstance().createDummyItem(createItemID[i]).isStackable())
					player.addItem("Extract", createItemID[i], createAmount[i], targets[0], false);
				else
				{
					for (int j = 0; j < createAmount[i]; j++)
						player.addItem("Extract", createItemID[i], 1, targets[0], false);
				}
				SystemMessage sm = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);;
				SystemMessage sm2 = new SystemMessage(SystemMessageId.EARNED_ADENA);;
				if (createItemID[i] == 57)
				{
					sm2.addNumber(createAmount[i]);
					player.sendPacket(sm2);
				}
				else
				{
					sm.addItemName(createItemID[i]);
					if (createAmount[i] > 1)
						sm.addNumber(createAmount[i]);
					player.sendPacket(sm);
				}
			}
		}
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.ISkillHandler#getSkillIds()
	 */
	public L2SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}