/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class EndScenePlayer extends L2GameClientPacket
{
	private static final String _C__d05b_EndScenePlayer = "[C] d0:5b EndScenePlayer";
	private static Logger _log = Logger.getLogger(EndScenePlayer.class.getName());
	
	private int _moviveId;
	
	@Override
	protected void readImpl()
	{
		_moviveId = readD();
	}
	
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		if (_moviveId == 0)
			return;
		if (activeChar.getMovieId() != _moviveId)
		{
			_log.warning("Player "+getClient()+" sent EndScenePlayer with wrong movie id: "+_moviveId);
			return;
		}
		activeChar.setMovieId(0);
        activeChar.setIsTeleporting(true, false);
        activeChar.decayMe();
        activeChar.spawnMe(activeChar.getPosition().getX(), activeChar.getPosition().getY(), activeChar.getPosition().getZ());
        activeChar.setIsTeleporting(false, false);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__d05b_EndScenePlayer;
	}
}