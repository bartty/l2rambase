/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

/**
 * Format: (chd) dddS
 * d: Always -1
 * d: Player Team
 * d: Player Object ID
 * S: Player Name
 */
public class ExCubeGameAddPlayer extends L2GameServerPacket
{
	private static final String _S__FE_97_01_EXCUBEGAMEADDPLAYER = "[S] FE:97:01 ExCubeGameAddPlayer";
	L2PcInstance _player;
	boolean _isRedTeam;
	
	/**
	 * Add Player To Minigame Waiting List
	 * 
	 * @param player Player Instance
	 * @param isRedTeam Is Player from Red Team?
	 */
	public ExCubeGameAddPlayer(L2PcInstance player, boolean isRedTeam)
	{
		_player = player;
		_isRedTeam = isRedTeam;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x97);
		writeD(0x01);
		
		writeD(0xffffffff);
		
		writeD(_isRedTeam ? 0x01 : 0x00);
		writeD(_player.getObjectId());
		writeS(_player.getName());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_97_01_EXCUBEGAMEADDPLAYER;
	}
}