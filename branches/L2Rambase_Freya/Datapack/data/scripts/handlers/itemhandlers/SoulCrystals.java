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

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.handler.IItemHandler;
import l2.rambase.gameserver.handler.ISkillHandler.CrystalFinalizer;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2RambaseonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


/**
 * This class ...
 *
 * @version $Revision: 1.2.4 $ $Date: 2005/08/14 21:31:07 $
 */

public class SoulCrystals implements IItemHandler
{
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IItemHandler#useItem(l2.rambase.gameserver.model.actor.L2Playable, l2.rambase.gameserver.model.L2ItemInstance)
	 */
	public void useItem(L2Playable playable, L2ItemInstance item)
	{
		if (!(playable instanceof L2PcInstance))
			return;
		
		L2PcInstance activeChar = (L2PcInstance) playable;
		L2Object target = activeChar.getTarget();
		if (!(target instanceof L2RambaseonsterInstance))
		{
			// Send a System Message to the caster
			SystemMessage sm = new SystemMessage(SystemMessageId.INCORRECT_TARGET);
			activeChar.sendPacket(sm);
			
			// Send a Server->Client packet ActionFailed to the L2PcInstance
			ActionFailed af = ActionFailed.STATIC_PACKET;
			activeChar.sendPacket(af);
			
			return;
		}
		
		// u can use soul crystal only when target hp goes below 50%
		if (((L2RambaseonsterInstance) target).getCurrentHp() > ((L2RambaseonsterInstance) target).getMaxHp() / 2.0)
		{
			ActionFailed af = ActionFailed.STATIC_PACKET;
			activeChar.sendPacket(af);
			return;
		}
		
		int crystalId = item.getItemId();
		
		// Soul Crystal Casting section
		L2Skill skill = SkillTable.getInstance().getInfo(2096, 1);
		activeChar.useMagic(skill, false, true);
		// End Soul Crystal Casting section
		
		// Continue execution later
		CrystalFinalizer cf = new CrystalFinalizer(activeChar, target, crystalId);
		ThreadPoolManager.getInstance().scheduleEffect(cf, skill.getHitTime());
		
	}	
}
