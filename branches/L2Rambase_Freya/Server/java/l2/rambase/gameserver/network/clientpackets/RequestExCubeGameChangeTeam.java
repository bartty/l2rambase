/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: chdd
 * d: Arena
 * d: Team
 * 
 */
public final class RequestExCubeGameChangeTeam extends L2GameClientPacket
{
	private static final String _C__D0_5A_REQUESTEXCUBEGAMECHANGETEAM = "[C] D0:5A RequestExCubeGameChangeTeam";
	private static Logger _log = Logger.getLogger(RequestExCubeGameChangeTeam.class.getName());
	
	int _arena;
	int _team;
	
	@Override
	protected void readImpl()
	{
		_arena = readD() + 1;
		_team = readD();
	}

	@Override
	public void runImpl()
	{
		if(HandysBlockCheckerManager.getInstance().arenaIsBeingUsed(_arena))
            return;
        L2PcInstance player = getClient().getActiveChar();
		switch (_team)
		{
			case 0:
			case 1:
				HandysBlockCheckerManager.getInstance().changePlayerToTeam(player, _arena, _team);
				break;
			case -1:
				int team = HandysBlockCheckerManager.getInstance().getHolder(_arena).getPlayerTeam(player);
	            if(team > -1)
	                HandysBlockCheckerManager.getInstance().removePlayer(player, _arena, team);
				break;
			default:
				_log.warning("Wrong Cube Game Team ID: "+_team);
				break;
		}
	}

	@Override
	public String getType()
	{
		return _C__D0_5A_REQUESTEXCUBEGAMECHANGETEAM;
	}
}
