/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.templates.skills.L2EffectType;

public class RequestDispel extends L2GameClientPacket
{
	private int _skillId;
	private int _skillLevel;

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
		_skillId = readD();
		_skillLevel = readD();
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		L2Skill skill = SkillTable.getInstance().getInfo(_skillId, _skillLevel);
		if (skill != null && (!skill.isDance() || Config.DANCE_CANCEL_BUFF) && !skill.isDebuff() && skill.canBeDispeled())
		{
			for (L2Effect e : activeChar.getAllEffects())
			{
				if (e != null && e.getSkill() == skill && e.getEffectType() != L2EffectType.TRANSFORMATION)
					e.exit();
			}
		}
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:4E RequestDispel";
	}
}
