/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.datatables.EnchantGroupsTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExEnchantSkillInfo;

/**
 * Format (ch) dd
 * c: (id) 0xD0
 * h: (subid) 0x06
 * d: skill id
 * d: skill lvl
 *
 */
public final class RequestExEnchantSkillInfo extends L2GameClientPacket
{
	private static final String _C__D0_06_REQUESTEXENCHANTSKILLINFO = "[C] D0:06 RequestExEnchantSkillInfo";

    private int _skillId;
	private int _skillLvl;

	@Override
	protected void readImpl()
	{
		_skillId = readD();
		_skillLvl = readD();
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

        if (activeChar == null)
            return;

        if (activeChar.getLevel() < 76)
            return;

        L2Skill skill = SkillTable.getInstance().getInfo(_skillId, _skillLvl);
        
        if (skill == null || skill.getId() != _skillId)
        {
            return;
        }

        if ( EnchantGroupsTable.getInstance().getSkillEnchantmentBySkillId(_skillId) == null)
        	return;
        
        int playerSkillLvl = activeChar.getSkillLevel(_skillId);
        if (playerSkillLvl == -1 || playerSkillLvl != _skillLvl)
        	return;
        
        activeChar.sendPacket(new ExEnchantSkillInfo(_skillId, _skillLvl));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_06_REQUESTEXENCHANTSKILLINFO;
	}

}