/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

import javolution.util.FastList;


public class ExOlympiadSpelledInfo extends L2GameServerPacket
{
	private static final String _S__FE_2A_OLYMPIADSPELLEDINFO = "[S] FE:7b ExOlympiadSpelledInfo";
	private int _playerID;
	private List<Effect> _effects;

	private class Effect
	{
		protected int _skillId;
		protected int _level;
		protected int _duration;

		public Effect(int pSkillId, int pLevel, int pDuration)
		{
			_skillId = pSkillId;
			_level = pLevel;
			_duration = pDuration;
		}
	}

	public ExOlympiadSpelledInfo(L2PcInstance player)
	{
		_effects = new FastList<Effect>();
		_playerID = player.getObjectId();
	}

	public void addEffect(int skillId, int level, int duration)
	{
		_effects.add(new Effect(skillId, level, duration));
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x7b);
		writeD(_playerID);
		writeD(_effects.size());
		for (Effect temp : _effects)
		{
			writeD(temp._skillId);
			writeH(temp._level);
			writeD(temp._duration/1000);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_2A_OLYMPIADSPELLEDINFO;
	}
}