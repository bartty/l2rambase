/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: chddd
 * 
 * d: Arena
 * d: Answer
 */
public final class RequestExCubeGameReadyAnswer extends L2GameClientPacket
{
	private static final String _C__D0_5C_REQUESTEXCUBEGAMEREADYANSWER = "[C] D0:5C RequestExCubeGameReadyAnswer";
	private static Logger _log = Logger.getLogger(RequestExCubeGameReadyAnswer.class.getName());
	
	int _arena;
	int _answer;
	
	@Override
	protected void readImpl()
	{
		_arena = readD() + 1;
		_answer = readD();
	}

	@Override
	public void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
        if(player == null)
            return;
		switch (_answer)
		{
			case 0:
				// Cancel
				break;
			case 1:
				HandysBlockCheckerManager.getInstance().increaseArenaVotes(_arena);
				break;
			default:
				_log.warning("Unknown Cube Game Answer ID: "+_answer);
				break;
		}
	}

	@Override
	public String getType()
	{
		return _C__D0_5C_REQUESTEXCUBEGAMEREADYANSWER;
	}
}
