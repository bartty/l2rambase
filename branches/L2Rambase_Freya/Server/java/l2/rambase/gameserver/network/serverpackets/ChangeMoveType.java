/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class ChangeMoveType extends L2GameServerPacket
{
	private static final String _S__3E_CHANGEMOVETYPE = "[S] 28 ChangeMoveType";
	public static final int WALK = 0;
	public static final int RUN = 1;

	private int _charObjId;
	private boolean _running;

	public ChangeMoveType(L2Character character)
	{
		_charObjId = character.getObjectId();
		_running = character.isRunning();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x28);
		writeD(_charObjId);
		writeD(_running ? RUN : WALK);
		writeD(0); //c2
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__3E_CHANGEMOVETYPE;
	}
}
