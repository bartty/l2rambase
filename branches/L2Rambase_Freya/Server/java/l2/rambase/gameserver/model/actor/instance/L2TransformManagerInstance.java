/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SkillTreeTable;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2TransformSkillLearn;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.AcquireSkillList;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2TransformManagerInstance extends L2MerchantInstance
{
    /**
	 * @param objectId
	 * @param template
	 */
	public L2TransformManagerInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2TransformManagerInstance);
	}

	@Override
	public String getHtmlPath(int npcId, int val)
	{
		String pom = "";

		if (val == 0)
			pom = "" + npcId;
		else
			pom = npcId + "-" + val;

		return "data/html/default/" + pom + ".htm";
	}

	/**
	 * this displays TransformationSkillList to the player.
	 * @param player
	 */
	public static void showTransformSkillList(L2PcInstance player)
	{        
		L2TransformSkillLearn[] skills = SkillTreeTable.getInstance().getAvailableTransformSkills(player);
		AcquireSkillList asl = new AcquireSkillList(AcquireSkillList.SkillType.Usual);
		int counts = 0;

		for (L2TransformSkillLearn s: skills)
		{
			L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());
			if (sk == null)
				continue;

			counts++;

			asl.addSkill(s.getId(), s.getLevel(), s.getLevel(), s.getSpCost(), 0);
		}

		if (counts == 0)
		{
			int minlevel = SkillTreeTable.getInstance().getMinLevelForNewTransformSkill(player);
			if (minlevel > 0)
			{
				// No more skills to learn, come back when you level.
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