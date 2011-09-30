/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;


public class MagicSkillLaunched extends L2GameServerPacket
{
	private static final String _S__8E_MAGICSKILLLAUNCHED = "[S] 54 MagicSkillLaunched";
	private int _charObjId;
	private int _skillId;
	private int _skillLevel;
	private int _numberOfTargets;
	private L2Object[] _targets;
	private int _singleTargetId;


	public MagicSkillLaunched(L2Character cha, int skillId, int skillLevel, L2Object[] targets)
	{
		_charObjId = cha.getObjectId();
		_skillId = skillId;
		_skillLevel = skillLevel;
		
		if (targets != null)
		{
			_numberOfTargets = targets.length;
			_targets = targets;
		}
		else
		{
			_numberOfTargets = 1;
			L2Object[] objs = {cha};
			_targets = objs;
		}
		_singleTargetId = 0;
	}

	public MagicSkillLaunched(L2Character cha, int skillId, int skillLevel)
	{
		_charObjId = cha.getObjectId();
		_skillId = skillId;
		_skillLevel = skillLevel;
		_numberOfTargets = 1;
		_singleTargetId = cha.getTargetId();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x54);
		writeD(_charObjId);
		writeD(_skillId);
		writeD(_skillLevel);
		writeD(_numberOfTargets); // also failed or not?
		if (_singleTargetId != 0 || _numberOfTargets == 0)
        {
			writeD(_singleTargetId);
        }
		else for(L2Object target : _targets)
		{
            writeD(target.getObjectId());
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__8E_MAGICSKILLLAUNCHED;
	}
}