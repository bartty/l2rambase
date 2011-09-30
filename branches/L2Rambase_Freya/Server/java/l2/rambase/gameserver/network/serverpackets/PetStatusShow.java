/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Summon;


public class PetStatusShow extends L2GameServerPacket
{
	private static final String _S__C9_PETSTATUSSHOW = "[S] b1 PetStatusShow";
	private int _summonType;

	public PetStatusShow(L2Summon summon)
	{
		_summonType = summon.getSummonType();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xb1);
		writeD(_summonType);
	}

	@Override
	public String getType()
	{
		return _S__C9_PETSTATUSSHOW;
	}
}