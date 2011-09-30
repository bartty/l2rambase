/**
 * @author L2Rambase Project
 * 
 */
package l2.rambase.gameserver.event.pvp;

import l2.rambase.Config;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Duel;
import l2.rambase.util.Rnd;


public class PvPEventTeleporter implements Runnable
{
	private L2PcInstance _playerInstance = null;
	private int[] _coordinates = new int[3];
	private boolean _adminRemove = false;

	public PvPEventTeleporter(L2PcInstance playerInstance, int[] coordinates, boolean fastSchedule, boolean adminRemove)
	{
		_playerInstance = playerInstance;
		_coordinates = coordinates;
		_adminRemove = adminRemove;
		long delay = (PvPEvent.isStarted() ? Config.PVP_EVENT_RESPAWN_TELEPORT_DELAY : Config.PVP_EVENT_START_LEAVE_TELEPORT_DELAY) * 1000;
		ThreadPoolManager.getInstance().scheduleGeneral(this, fastSchedule ? 0 : delay);
	}
	
	public void run()
	{
		if (_playerInstance == null)
			return;
		
		L2Summon summon = _playerInstance.getPet();
		if (summon != null)
			summon.unSummon(_playerInstance);
		if (Config.PVP_EVENT_EFFECTS_REMOVAL == 0 || (Config.PVP_EVENT_EFFECTS_REMOVAL == 1 && (_playerInstance.getTeam() == 0 || (_playerInstance.isInDuel() && _playerInstance.getDuelState() != Duel.DUELSTATE_INTERRUPTED))))
			_playerInstance.stopAllEffectsExceptThoseThatLastThroughDeath();
		if (_playerInstance.isInDuel())
			_playerInstance.setDuelState(Duel.DUELSTATE_INTERRUPTED);
		int PvPInstance = PvPEvent.getPvPEventInstance();
		if (PvPInstance != 0)
		{
			if (PvPEvent.isStarted() && !_adminRemove)
			{
				_playerInstance.setInstanceId(PvPInstance);
			}
			else
			{
				_playerInstance.setInstanceId(0);
			}
		}
		else
		{
			_playerInstance.setInstanceId(0);
		}
		_playerInstance.doRevive();
		_playerInstance.teleToLocation( _coordinates[ 0 ] + Rnd.get(101)-50, _coordinates[ 1 ] + Rnd.get(101)-50, _coordinates[ 2 ], false );
		_playerInstance.setCurrentCp(_playerInstance.getMaxCp());
		_playerInstance.setCurrentHp(_playerInstance.getMaxHp());
		_playerInstance.setCurrentMp(_playerInstance.getMaxMp());
		_playerInstance.broadcastStatusUpdate();
		_playerInstance.broadcastUserInfo();
	}
}