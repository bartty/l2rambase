/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import javolution.util.FastList;

/**
 * sample
 *
 * a3
 * 05000000
 * 03000000 03000000 06000000 3c000000 00000000 	power strike
 * 10000000 02000000 06000000 3c000000 00000000 	mortal blow
 * 38000000 04000000 06000000 36010000 00000000 	power shot
 * 4d000000 01000000 01000000 98030000 01000000 	ATTACK aura  920sp
 * 8e000000 03000000 03000000 cc010000 00000000     Armor Mastery
 *
 * format   d (ddddd)
 * skillid, level, maxlevel?,
 *
 * C4 format changes:
 * 0000: [8a] [00 00 00 00] [35 00 00 00] 92 00 00 00 01 00 00    .....5..........
 *            ^^^^^^^^^^^^^
 * 0010: 00 2d 00 00 00 04 01 00 00 00 00 00 00 a4 00 00    .-..............
 * 0020: 00 01 00 00 00 03 00 00 00 e4 0c 00 00 00 00 00    ................
 * 0030: 00 d4 00 00 00 01 00 00 00 06 00 00 00 08 52 00    ..............R.
 */
public final class AcquireSkillList extends L2GameServerPacket
{	
    public enum SkillType
    {
    	Usual, // 0
    	Fishing, // 1
    	Clan, // 2
    	unk3,
    	unk4,
    	unk5,
    	Special, // 6
    	unk7,
    	unk8,
    	Pomander
    }

	private static final String _S__A3_AQUIRESKILLLIST = "[S] 90 AquireSkillList";

	private List<Skill> _skills;
	private SkillType _fishingSkills;

	private class Skill
	{
		public int id;
		public int nextLevel;
		public int maxLevel;
		public int spCost;
		public int requirements;

		public Skill(int pId, int pNextLevel, int pMaxLevel, int pSpCost, int pRequirements)
		{
			id = pId;
			nextLevel = pNextLevel;
			maxLevel = pMaxLevel;
			spCost = pSpCost;
			requirements = pRequirements;
		}
	}

	public AcquireSkillList(SkillType type)
	{
		_skills = new FastList<Skill>();
		_fishingSkills = type;
	}

	public void addSkill(int id, int nextLevel, int maxLevel, int spCost, int requirements)
	{
		_skills.add(new Skill(id, nextLevel, maxLevel, spCost, requirements));
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x90);
        writeD(_fishingSkills.ordinal());   //c4 : C5 : 0: usuall  1: fishing 2: clans
		writeD(_skills.size());

		for (Skill temp : _skills)
		{
			writeD(temp.id);
			writeD(temp.nextLevel);
			writeD(temp.maxLevel);
			writeD(temp.spCost);
			writeD(temp.requirements);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A3_AQUIRESKILLLIST;
	}
}