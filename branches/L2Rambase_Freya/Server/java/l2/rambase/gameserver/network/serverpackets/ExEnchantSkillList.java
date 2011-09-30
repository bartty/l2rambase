/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import javolution.util.FastList;

public class ExEnchantSkillList extends L2GameServerPacket
{
    public enum EnchantSkillType
    {
        NORMAL,
        SAFE,
        UNTRAIN,
        CHANGE_ROUTE,
    }
    
    private static final String _S__FE_17_EXENCHANTSKILLLIST = "[S] FE:29 ExEnchantSkillList";
    private final EnchantSkillType _type;
    private final List<Skill> _skills;

    class Skill
    {
        public int id;
        public int nextLevel;

        Skill(int pId, int pNextLevel)
        {
            id = pId;
            nextLevel = pNextLevel;
        }
    }

    public void addSkill(int id, int level)
    {
        _skills.add(new Skill(id, level));
    }

    public ExEnchantSkillList(EnchantSkillType type)
    {
        _type = type;
        _skills = new FastList<Skill>();
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
     */
    @Override
	protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x29);
        
        writeD(_type.ordinal());
        writeD(_skills.size());
        for (Skill sk : _skills)
        {
            writeD(sk.id);
            writeD(sk.nextLevel);
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _S__FE_17_EXENCHANTSKILLLIST;
    }
}