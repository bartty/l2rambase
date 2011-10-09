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
package l2.Rambase.gameserver.model.actor.instance;

import javolution.util.FastList;
import javolution.util.FastMap;

import l2.Rambase.Config;
import l2.Rambase.gameserver.datatables.SkillTable;
import l2.Rambase.gameserver.datatables.SkillTreesData;
import l2.Rambase.gameserver.model.L2Effect;
import l2.Rambase.gameserver.model.L2Skill;
import l2.Rambase.gameserver.model.L2SkillLearn;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.status.FolkStatus;
import l2.Rambase.gameserver.model.base.ClassId;
import l2.Rambase.gameserver.network.SystemMessageId;
import l2.Rambase.gameserver.network.serverpackets.AcquireSkillList;
import l2.Rambase.gameserver.network.serverpackets.AcquireSkillList.SkillType;
import l2.Rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.Rambase.gameserver.network.serverpackets.SystemMessage;
import l2.Rambase.gameserver.skills.effects.EffectBuff;
import l2.Rambase.gameserver.skills.effects.EffectDebuff;
import l2.Rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.Rambase.util.StringUtil;

public class L2NpcInstance extends L2Npc
{
	private final ClassId[] _classesToTeach;
	
	public L2NpcInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2NpcInstance);
		setIsInvul(false);
		_classesToTeach = template.getTeachInfo();
	}
	
	@Override
	public FolkStatus getStatus()
	{
		return (FolkStatus)super.getStatus();
	}
	
	@Override
	public void initCharStatus()
	{
		setStatus(new FolkStatus(this));
	}
	
	@Override
	public void addEffect(L2Effect newEffect)
	{
		if (newEffect instanceof EffectDebuff || newEffect instanceof EffectBuff)
			super.addEffect(newEffect);
		else if (newEffect != null)
			newEffect.stopEffectTask();
	}
	
	public ClassId[] getClassesToTeach()
	{
		return _classesToTeach;
	}
	
	/**
	 * Displays Skill Tree for a given player, npc and class Id.
	 * @param player the active character.
	 * @param npc the last folk.
	 * @param classId player's active class id.
	 */
	public static void showSkillList(L2PcInstance player, L2Npc npc, ClassId classId)
	{
		if (Config.DEBUG)
		{
			_log.fine("SkillList activated on: "+npc.getObjectId());
		}
		
		final int npcId = npc.getTemplate().npcId;
		
		if (npcId == 32611) //Tolonis (Officer)
		{
			final FastList<L2SkillLearn> skills = SkillTreesData.getInstance().getAvailableCollectSkills(player);
			final AcquireSkillList asl = new AcquireSkillList(SkillType.Collect);
			
			int counts = 0;
			
			for (L2SkillLearn s : skills)
			{
				final L2Skill sk = SkillTable.getInstance().getInfo(s.getSkillId(), s.getSkillLevel());
				
				if (sk != null)
				{
					counts++;
					asl.addSkill(s.getSkillId(), s.getSkillLevel(), s.getSkillLevel(), 0, 1);
				}
			}
			
			if (counts == 0) // No more skills to learn, come back when you level.
			{
				final int minLevel = SkillTreesData.getInstance().getMinLevelForNewSkill(player, SkillTreesData.getInstance().getCollectSkillTree());
				if (minLevel > 0)
				{
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1);
					sm.addNumber(minLevel);
					player.sendPacket(sm);
				}
				else
				{
					player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.NO_MORE_SKILLS_TO_LEARN));
				}
			}
			else
			{
				player.sendPacket(asl);
			}
			return;
		}
		
		if (!npc.getTemplate().canTeach(classId))
		{
			npc.showNoTeachHtml(player);
			return;
		}
		
		if (((L2NpcInstance)npc).getClassesToTeach() == null)
		{
			NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
			final String sb = StringUtil.concat(
					"<html><body>" +
					"I cannot teach you. My class list is empty.<br> Ask admin to fix it. Need add my npcid and classes to skill_learn.sql.<br>NpcId:",
					String.valueOf(npcId),
					", Your classId:",
					String.valueOf(player.getClassId().getId()),
					"<br>" +
					"</body></html>"
			);
			html.setHtml(sb);
			player.sendPacket(html);
			return;
		}
		
		//Normal skills, No LearnedByFS, no AutoGet skills.
		final FastList<L2SkillLearn> skills = SkillTreesData.getInstance().getAvailableSkills(player, classId, false, false);
		final AcquireSkillList asl = new AcquireSkillList(AcquireSkillList.SkillType.ClassTransform);
		int count = 0;
		
		for (L2SkillLearn s: skills)
		{
			final L2Skill sk = SkillTable.getInstance().getInfo(s.getSkillId(), s.getSkillLevel());
			if (sk != null)
			{
				asl.addSkill(s.getSkillId(), s.getSkillLevel(), s.getSkillLevel(), s.getLevelUpSp(), 0);
				count++;
			}
		}
		
		if (count == 0)
		{
			final FastMap<Integer, L2SkillLearn> skillTree = SkillTreesData.getInstance().getCompleteClassSkillTree(classId);
			
			final int minLevel = SkillTreesData.getInstance().getMinLevelForNewSkill(player, skillTree);
			if (minLevel > 0)
			{
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1);
				sm.addNumber(minLevel);
				player.sendPacket(sm);
			}
			else
			{
				//TODO: Is this SysMsg really used here?
				if (player.getClassId().level() == 1)
				{
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.NO_SKILLS_TO_LEARN_RETURN_AFTER_S1_CLASS_CHANGE);
					sm.addNumber(2);
					player.sendPacket(sm);
				}
				else
				{
					player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.NO_MORE_SKILLS_TO_LEARN));
				}
			}
		}
		else
		{
			player.sendPacket(asl);
		}
	}
}
