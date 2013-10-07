package lineage2.gameserver.network.serverpackets;

import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.pledge.Clan;
import lineage2.gameserver.model.pledge.SubUnit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Reworked: VISTALL
 */
public class PledgeSkillList extends L2GameServerPacket
{
	private List<SkillInfo> _allSkills = Collections.emptyList();
	private List<UnitSkillInfo> _unitSkills = new ArrayList<UnitSkillInfo>();

	public PledgeSkillList(Clan clan)
	{
		Collection<Skill> skills = clan.getSkills();
		_allSkills = new ArrayList<SkillInfo>(skills.size());

		for (Skill sk : skills)
			_allSkills.add(new SkillInfo(sk.getId(), sk.getLevel()));

		for (SubUnit subUnit : clan.getAllSubUnits())
			for (Skill sk : subUnit.getSkills())
				_unitSkills.add(new UnitSkillInfo(subUnit.getType(), sk.getId(), sk.getLevel()));
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0x3a);
		writeD(_allSkills.size());
		writeD(_unitSkills.size());

		for (SkillInfo info : _allSkills)
		{
			writeD(info._id);
			writeD(info._level);
		}

		for (UnitSkillInfo info : _unitSkills)
		{
			writeD(info._type);
			writeD(info._id);
			writeD(info._level);
		}
	}

	static class SkillInfo
	{
		public int _id, _level;

		public SkillInfo(int id, int level)
		{
			_id = id;
			_level = level;
		}
	}

	static class UnitSkillInfo extends SkillInfo
	{
		private int _type;

		public UnitSkillInfo(int type, int id, int level)
		{
			super(id, level);
			_type = type;
		}
	}
}