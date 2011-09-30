/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SkillTreeTable;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2SkillLearn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.status.FolkStatus;
import l2.rambase.gameserver.model.base.ClassId;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.AcquireSkillList;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.effects.EffectBuff;
import l2.rambase.gameserver.skills.effects.EffectDebuff;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.StringUtil;

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
	 * this displays SkillList to the player.
	 * @param player
	 */
	public static void showSkillList(L2PcInstance player, L2Npc npc, ClassId classId)
	{
		if (Config.DEBUG)
			_log.fine("SkillList activated on: "+npc.getObjectId());

		int npcId = npc.getTemplate().npcId;

		if (npcId == 32611)
		{
			L2SkillLearn[] skills = SkillTreeTable.getInstance().getAvailableSpecialSkills(player);
			AcquireSkillList asl = new AcquireSkillList(AcquireSkillList.SkillType.Special);

			int counts = 0;

			for (L2SkillLearn s : skills)
			{
				L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());

				if (sk == null)
					continue;

				counts++;
				asl.addSkill(s.getId(), s.getLevel(), s.getLevel(), 0, 1);
			}

			if (counts == 0) // No more skills to learn, come back when you level.
				player.sendPacket(new SystemMessage(SystemMessageId.NO_MORE_SKILLS_TO_LEARN));
			else
				player.sendPacket(asl);

			player.sendPacket(ActionFailed.STATIC_PACKET);
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

		L2SkillLearn[] skills = SkillTreeTable.getInstance().getAvailableSkills(player, classId);
		AcquireSkillList asl = new AcquireSkillList(AcquireSkillList.SkillType.Usual);
		int counts = 0;

		for (L2SkillLearn s: skills)
		{
			L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());
			if (sk == null)
				continue;

			int cost = SkillTreeTable.getInstance().getSkillCost(player, sk);
			counts++;

			asl.addSkill(s.getId(), s.getLevel(), s.getLevel(), cost, 0);
		}

		if (counts == 0)
		{
			int minlevel = SkillTreeTable.getInstance().getMinLevelForNewSkill(player, classId);
			if (minlevel > 0)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN);
				sm.addNumber(minlevel);
				player.sendPacket(sm);
			}
			else
				player.sendPacket(new SystemMessage(SystemMessageId.NO_MORE_SKILLS_TO_LEARN));
		}
		else
			player.sendPacket(asl);

		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}