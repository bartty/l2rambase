/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.datatables.EnchantGroupsTable;
import l2.rambase.gameserver.model.L2EnchantSkillLearn;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExEnchantSkillInfoDetail;


/**
 * Format (ch) ddd c: (id) 0xD0 h: (subid) 0x31 d: type d: skill id d: skill lvl
 * 
 */
public final class RequestExEnchantSkillInfoDetail extends L2GameClientPacket
{
	protected static final Logger _log = Logger.getLogger(RequestExEnchantSkillInfoDetail.class.getName());

	private int _type;
	private int _skillId;
	private int _skillLvl;

	@Override
	protected void readImpl()
	{
		_type = readD();
		_skillId = readD();
		_skillLvl = readD();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
			return;

		int reqSkillLvl = -2;

		if (_type == 0 || _type == 1)
			reqSkillLvl = _skillLvl - 1; // enchant
		else if (_type == 2)
			reqSkillLvl = _skillLvl + 1; // untrain
		else if (_type == 3)
			reqSkillLvl = _skillLvl; // change route

		int playerSkillLvl = activeChar.getSkillLevel(_skillId);

		// dont have such skill
		if (playerSkillLvl == -1)
			return;

		// if reqlvl is 100,200,.. check base skill lvl enchant
		if ((reqSkillLvl % 100) == 0)
		{
			L2EnchantSkillLearn esl = EnchantGroupsTable.getInstance().getSkillEnchantmentBySkillId(_skillId);
			if (esl != null)
			{
				// if player dont have min level to enchant
				if (playerSkillLvl != esl.getBaseLevel())
					return;
			}
			// enchant data dont exist?
			else
				return;
		}
		else if (playerSkillLvl != reqSkillLvl)
		{
			// change route is different skill lvl but same enchant
			if (_type == 3 && ((playerSkillLvl % 100) != (_skillLvl % 100)))
				return;
		}

		// send skill enchantment detail
		ExEnchantSkillInfoDetail esd = new ExEnchantSkillInfoDetail(_type, _skillId, _skillLvl, activeChar);
		activeChar.sendPacket(esd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:46 RequestExEnchantSkillInfo";
	}

}
