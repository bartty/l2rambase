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
package handlers.actionhandlers;

import l2.rambase.gameserver.handler.IActionHandler;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.network.serverpackets.StaticObject;
import l2.rambase.util.StringUtil;

public class L2DoorInstanceActionShift implements IActionHandler
{
	public boolean action(L2PcInstance activeChar, L2Character target, boolean interact)
	{
		if (activeChar.getAccessLevel().isGm())
		{
			activeChar.setTarget(target);
			activeChar.sendPacket(new MyTargetSelected(target.getObjectId(), activeChar.getLevel()));
			
			StaticObject su;
			// send HP amount if doors are inside castle/fortress zone
			if ((((L2DoorInstance)target).getCastle() != null
					&& ((L2DoorInstance)target).getCastle().getCastleId() > 0)
					|| (((L2DoorInstance)target).getFort() != null
							&& ((L2DoorInstance)target).getFort().getFortId() > 0
							&& !((L2DoorInstance)target).getIsCommanderDoor()))
			{
				su = new StaticObject((L2DoorInstance)target, true);
			}
			else if (((L2DoorInstance)target).getClanHall() != null &&
					((L2DoorInstance)target).getClanHall().isResidence())
			{
				su = new StaticObject((L2DoorInstance)target, true);
			}
			else
				su  = new StaticObject((L2DoorInstance)target, false);
			
			activeChar.sendPacket(su);
			
			NpcHtmlMessage html = new NpcHtmlMessage(target.getObjectId());
                        final String html1 = StringUtil.concat(
                                "<html><body><center><font color=\"LEVEL\">Door Info</font></center><br><table border=0><tr><td>HP: </td><td>",
                                String.valueOf(target.getCurrentHp()),
                                " / ",
                                String.valueOf(target.getMaxHp()),
                                "</td></tr><tr><td>Max X,Y,Z: </td><td>",
                                String.valueOf(((L2DoorInstance)target).getXMax()),
                                ", ",
                                String.valueOf(((L2DoorInstance)target).getYMax()),
                                ", ",
                                String.valueOf(((L2DoorInstance)target).getZMax()),
                                "</td></tr><tr><td>Min X,Y,Z: </td><td>",
                                String.valueOf(((L2DoorInstance)target).getXMin()),
                                ", ",
                                String.valueOf(((L2DoorInstance)target).getYMin()),
                                ", ",
                                String.valueOf(((L2DoorInstance)target).getZMin()),
                                "</td></tr><tr><td>Object ID: </td><td>",
                                String.valueOf(target.getObjectId()),
                                "</td></tr><tr><td>Door ID: </td><td>",
                                String.valueOf(((L2DoorInstance)target).getDoorId()),
                                "</td></tr><tr><td><br></td></tr><tr><td>Class: </td><td>",
                                target.getClass().getSimpleName(),
                                "</td></tr></table><br><table><tr><td><button value=\"Open\" action=\"bypass -h admin_open ",
                                String.valueOf(((L2DoorInstance)target).getDoorId()),
                                "\" width=40 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td><td><button value=\"Close\" action=\"bypass -h admin_close ",
                                String.valueOf(((L2DoorInstance)target).getDoorId()),
                                "\" width=40 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td><td><button value=\"Kill\" action=\"bypass -h admin_kill\" width=40 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td><td><button value=\"Delete\" action=\"bypass -h admin_delete\" width=40 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr></table></body></html>"
                                );
			html.setHtml(html1);
			activeChar.sendPacket(html);
		}
		return true;
	}

	public InstanceType getInstanceType()
	{
		return InstanceType.L2DoorInstance;
	}
}