/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;


public class PetStatusUpdate extends L2GameServerPacket
{
	private static final String _S__CE_PETSTATUSSHOW = "[S] b6 PetStatusUpdate";

	private L2Summon _summon;
	private int _maxHp, _maxMp;
	private int _maxFed, _curFed;

	public PetStatusUpdate(L2Summon summon)
	{
        _summon = summon;
        _maxHp = _summon.getMaxVisibleHp();
		_maxMp = _summon.getMaxMp();
		if (_summon instanceof L2PetInstance)
		{
			L2PetInstance pet = (L2PetInstance)_summon;
			_curFed = pet.getCurrentFed(); // how fed it is
			_maxFed = pet.getMaxFed(); //max fed it can be
		}
		else if (_summon instanceof L2SummonInstance)
		{
			L2SummonInstance sum = (L2SummonInstance)_summon;
			_curFed = sum.getTimeRemaining();
			_maxFed = sum.getTotalLifeTime();
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xb6);
		writeD(_summon.getSummonType());
		writeD(_summon.getObjectId());
		writeD(_summon.getX());
		writeD(_summon.getY());
		writeD(_summon.getZ());
		writeS("");
		writeD(_curFed);
		writeD(_maxFed);
		writeD((int)_summon.getCurrentHp());
		writeD(_maxHp);
		writeD((int)_summon.getCurrentMp());
		writeD(_maxMp);
		writeD(_summon.getLevel());
		writeQ(_summon.getStat().getExp());
		writeQ(_summon.getExpForThisLevel());// 0% absolute value
		writeQ(_summon.getExpForNextLevel());// 100% absolute value
	}

	@Override
	public String getType()
	{
		return _S__CE_PETSTATUSSHOW;
	}
}