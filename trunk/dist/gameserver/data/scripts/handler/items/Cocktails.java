/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handler.items;

import lineage2.gameserver.Config;
import lineage2.gameserver.model.Effect;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.items.ItemInstance;
import lineage2.gameserver.network.serverpackets.MagicSkillUse;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.skills.effects.EffectTemplate;
import lineage2.gameserver.stats.Env;
import lineage2.gameserver.tables.SkillTable;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class Cocktails extends SimpleItemHandler
{
	/**
	 * Field ITEM_IDS.
	 */
	private static final int[] ITEM_IDS = new int[]
	{
		10178,
		15356,
		20393,
		10179,
		15357,
		20394,
		14739,
		32316,
		33862,
		33766,
		30274,
		34082
	};
	
	/**
	 * Method getItemIds.
	 * @return int[] * @see lineage2.gameserver.handler.items.IItemHandler#getItemIds()
	 */
	@Override
	public int[] getItemIds()
	{
		return ITEM_IDS;
	}
	
	/**
	 * Field luxury_fighter_list.
	 */
	private static final int[] luxury_fighter_list =
	{
		364,264,529,265,
		349,305,304,267,
		268,530,274,275,
		277,310,271,1499,
		1519,1502,1503,1504,
		1501,1397,1243,1240,
		1043,1044,834,1444,
		1363,4699,4703,825,
		828,1352,1353,1354,
		1392,1393,1032,1035,
		1182,1191,1033,1189,
		1548,1259,
	};
	
	/**
	 * Field luxury_mage_list.
	 */
	private static final int[] luxury_mage_list =
	{
		264,529,270,265,363,
		349,305,308,304,267,
		268,530,276,272,365,
		273,1503,1504,1501,1500,
		1085,1062,1303,1078,1397,
		1044,834,1444,1443,1413,
		4700,4703,830,1352,1353,
		1354,1392,1393,1032,1035,
		1182,1191,1033,1189,1548,1259,
	};
	
	/**
	 * Field bless_list.
	 */
	private static final int[] bless_list =
	{
		11517,
		11520,
		11519,
		11522,
		11521,
	};
	/**
	 * Field sweet_list.
	 */
	private static final int[] sweet_list =
	{
		2404,
		2405,
		2406,
		2407,
		2408,
		2409,
		2410,
		2411,
		2412,
		2413,
	};
	/**
	 * Field fresh_list.
	 */
	private static final int[] fresh_list =
	{
		2414,
		2411,
		2415,
		2405,
		2406,
		2416,
		2417,
		2418,
		2419,
	};
	/**
	 * Field milk_list.
	 */
	private static final int[] milk_list =
	{
		2873,
		2874,
		2875,
		2876,
		2877,
		2878,
		2879,
		2885,
		2886,
		2887,
		2888,
		2889,
		2890,
	};
	
	/**
	 * Method useItemImpl.
	 * @param player Player
	 * @param item ItemInstance
	 * @param ctrl boolean
	 * @return boolean
	 */
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		final int itemId = item.getItemId();
		if (player.isInOlympiadMode())
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(itemId));
			return false;
		}
		if (!useItem(player, item, 1))
		{
			return false;
		}
		switch (itemId)
		{
			case 10178:
			case 15356:
			case 20393:
				for (int skill : sweet_list)
				{
					player.broadcastPacket(new MagicSkillUse(player, player, skill, 1, 0, 0));
					player.altOnMagicUseTimer(player, SkillTable.getInstance().getInfo(skill, 1));
				}
				break;
				
			case 30274:
				for (int tmp_skill : luxury_fighter_list)
				{
					Skill skill = SkillTable.getInstance().getInfo(tmp_skill, SkillTable.getInstance().getBaseLevel(tmp_skill));
					for (EffectTemplate et : skill.getEffectTemplates())
					{
						Env env = new Env(player, player, skill);
						Effect effect = et.getEffect(env);
						effect.setPeriod((60*60)*1000);
						player.getEffectList().addEffect(effect);
						player.updateEffectIconsImpl();
					}
				}
				break;
				
			case 34082:
				for (int tmp_skill : luxury_mage_list)
				{
					Skill skill = SkillTable.getInstance().getInfo(tmp_skill, SkillTable.getInstance().getBaseLevel(tmp_skill));
					for (EffectTemplate et : skill.getEffectTemplates())
					{
						Env env = new Env(player, player, skill);
						Effect effect = et.getEffect(env);
						effect.setPeriod((60*60)*1000);
						player.getEffectList().addEffect(effect);
						player.updateEffectIconsImpl();
					}
				}
				break;
				
			case 10179:
			case 15357:
			case 20394:
				for (int skill : fresh_list)
				{
					player.broadcastPacket(new MagicSkillUse(player, player, skill, 1, 0, 0));
					player.altOnMagicUseTimer(player, SkillTable.getInstance().getInfo(skill, 1));
				}
				break;
				
			case 32316:
			case 33862:
			case 33766:
				for (int skill : bless_list)
				{
					player.broadcastPacket(new MagicSkillUse(player, player, skill, 1, 0, 0));
					player.altOnMagicUseTimer(player, SkillTable.getInstance().getInfo(skill, 1));
				}
				break;
				
			case 14739:
				player.broadcastPacket(new MagicSkillUse(player, player, 2873, 1, 0, 0));
				player.altOnMagicUseTimer(player, SkillTable.getInstance().getInfo(2891, 6));
				for (int skill : milk_list)
				{
					player.broadcastPacket(new MagicSkillUse(player, player, skill, 1, 0, 0));
					player.altOnMagicUseTimer(player, SkillTable.getInstance().getInfo(skill, 1));
				}
				break;
			default:
				return false;
		}
		return true;
	}
}
