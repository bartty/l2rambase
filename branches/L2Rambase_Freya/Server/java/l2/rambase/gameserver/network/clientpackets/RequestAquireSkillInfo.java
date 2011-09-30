/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.SkillSpellbookTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SkillTreeTable;
import l2.rambase.gameserver.model.L2PledgeSkillLearn;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2SkillLearn;
import l2.rambase.gameserver.model.L2TransformSkillLearn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2NpcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2TransformManagerInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.network.serverpackets.AcquireSkillInfo;


/**
 * This class ...
 *
 * @version $Revision: 1.5.2.1.2.5 $ $Date: 2005/04/06 16:13:48 $
 */
public class RequestAquireSkillInfo extends L2GameClientPacket
{
	private static final String _C__6B_REQUESTAQUIRESKILLINFO = "[C] 6B RequestAquireSkillInfo";
	private static Logger _log = Logger.getLogger(RequestAquireSkillInfo.class.getName());

	private int _id;
	private int _level;
	private int _skillType;

	@Override
	protected void readImpl()
	{
		_id = readD();
		_level = readD();
		_skillType = readD();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
            return;

		final L2Npc trainer = activeChar.getLastFolkNPC();
		if (!(trainer instanceof L2NpcInstance))
			return;

        if (!trainer.canInteract(activeChar) && !activeChar.isGM())
            return;

		final L2Skill skill = SkillTable.getInstance().getInfo(_id, _level);

		boolean canteach = false;

		if (skill == null)
		{
			if (Config.DEBUG)
				_log.warning("skill id " + _id + " level " + _level
					+ " is undefined. aquireSkillInfo failed.");
			return;
		}

		if (_skillType == 0)
		{
			if (trainer instanceof L2TransformManagerInstance)
			{
				int itemId = 0;
				L2TransformSkillLearn[] skillst = SkillTreeTable.getInstance().getAvailableTransformSkills(activeChar);

				for (L2TransformSkillLearn s : skillst)
				{
					if (s.getId() == _id && s.getLevel() == _level)
					{
						canteach = true;
						itemId = s.getItemId();
						break;
					}
				}

				if (!canteach)
					return; // cheater

				int requiredSp = 0;
				AcquireSkillInfo asi = new AcquireSkillInfo(skill.getId(), skill.getLevel(), requiredSp,0);

				// all transformations require scrolls
				asi.addRequirement(99, itemId, 1, 50);
				sendPacket(asi);
				return;
		    }

			if (!trainer.getTemplate().canTeach(activeChar.getSkillLearningClassId()))
                return; // cheater

			L2SkillLearn[] skills = SkillTreeTable.getInstance().getAvailableSkills(activeChar, activeChar.getSkillLearningClassId());

			for (L2SkillLearn s : skills)
			{
				if (s.getId() == _id && s.getLevel() == _level)
				{
					canteach = true;
					break;
				}
			}

			if (!canteach)
				return; // cheater

			int requiredSp = SkillTreeTable.getInstance().getSkillCost(activeChar, skill);
			AcquireSkillInfo asi = new AcquireSkillInfo(skill.getId(), skill.getLevel(), requiredSp,0);

        	int spbId = -1;
			if (Config.DIVINE_SP_BOOK_NEEDED && skill.getId() == L2Skill.SKILL_DIVINE_INSPIRATION)
        		spbId = SkillSpellbookTable.getInstance().getBookForSkill(skill, _level);
			else if (Config.SP_BOOK_NEEDED && skill.getLevel() == 1)
        		spbId = SkillSpellbookTable.getInstance().getBookForSkill(skill);

			if (spbId > -1)
				asi.addRequirement(99, spbId, 1, 50);

			sendPacket(asi);
		}
		else if (_skillType == 2)
        {
            int requiredRep = 0;
            int itemId = 0;
            int itemCount = 0;
            L2PledgeSkillLearn[] skills = SkillTreeTable.getInstance().getAvailablePledgeSkills(activeChar);

            for (L2PledgeSkillLearn s : skills)
            {
                if (s.getId() == _id && s.getLevel() == _level)
                {
                    canteach = true;
                    requiredRep = s.getRepCost();
                    itemId = s.getItemId();
                    itemCount = s.getItemCount();
                    break;
                }
            }

            if (!canteach)
                return; // cheater

            AcquireSkillInfo asi = new AcquireSkillInfo(skill.getId(), skill.getLevel(), requiredRep, 2);

            if (Config.LIFE_CRYSTAL_NEEDED)
                asi.addRequirement(1, itemId, itemCount, 0);

            sendPacket(asi);
        }
		else if (_skillType == 4)
		{
			Quest[] qlst = trainer.getTemplate().getEventQuests(Quest.QuestEventType.ON_SKILL_LEARN);
			if ((qlst != null) && qlst.length == 1)
			{
				if (!qlst[0].notifyAcquireSkillInfo(trainer, activeChar, skill))
				{
					qlst[0].notifyAcquireSkillList(trainer, activeChar);
					return;
				}
			}
			else
			{
				return;
			}
		}
		else if (_skillType == 6)
        {
			int costid = 0;
			int costcount = 0;
			L2SkillLearn[] skillsc = SkillTreeTable.getInstance().getAvailableSpecialSkills(activeChar);
			for (L2SkillLearn s : skillsc)
			{
				L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());

				if (sk == null || sk != skill)
                    continue;

				canteach = true;
				costid = s.getIdCost();
				costcount = s.getCostCount();
			}

			AcquireSkillInfo asi = new AcquireSkillInfo(skill.getId(), skill.getLevel(), 0, 6);
			asi.addRequirement(5, costid, costcount, 0);
			sendPacket(asi);
        }
		else // Common Skills
		{
			int costid = 0;
			int costcount = 0;
			int spcost = 0;

			L2SkillLearn[] skillsc = SkillTreeTable.getInstance().getAvailableSkills(activeChar);

			for (L2SkillLearn s : skillsc)
			{
				L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());

				if (sk == null || sk != skill)
                    continue;

				canteach = true;
				costid = s.getIdCost();
				costcount = s.getCostCount();
				spcost = s.getSpCost();
			}

			AcquireSkillInfo asi = new AcquireSkillInfo(skill.getId(), skill.getLevel(), spcost, 1);
			asi.addRequirement(4, costid, costcount, 0);
			sendPacket(asi);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__6B_REQUESTAQUIRESKILLINFO;
	}
}
