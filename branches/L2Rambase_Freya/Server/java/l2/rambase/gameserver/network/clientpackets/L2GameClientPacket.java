/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.nio.BufferUnderflowException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.mmocore.network.ReceivablePacket;

import l2.rambase.Config;
import l2.rambase.gameserver.GameTimeController;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.L2GameServerPacket;

/**
 * Packets received by the game server from clients
 *
 */
public abstract class L2GameClientPacket extends ReceivablePacket<L2GameClient>
{
	private static final Logger _log = Logger.getLogger(L2GameClientPacket.class.getName());

	@Override
	protected boolean read()
	{
		//_log.info(this.getType());
		try
		{
			readImpl();
			return true;
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Client: " + getClient().toString() + " - Failed reading: " + getType() + " - L2J Server Version: " + Config.SERVER_VERSION + " - DP Revision: " + Config.DATAPACK_VERSION + " ; " + e.getMessage(), e);
			
			if (e instanceof BufferUnderflowException) // only one allowed per client per minute
			{
				if (GameTimeController.getGameTicks() - getClient().underflowReadStartTick > 600)
				{
					getClient().underflowReadStartTick = GameTimeController.getGameTicks();
					getClient().underflowReadsInMin = 1;
				}
				else if (++getClient().underflowReadsInMin > 1)
				{
					getClient().closeNow();
					_log.severe("Client " + getClient().toString() + " - Disconnected: Too many buffer underflow exceptions");
				}
			}
		}
		return false;
	}

	protected abstract void readImpl();

	@Override
	public void run()
	{
		try
		{
            // flood protection
			if (GameTimeController.getGameTicks() - getClient().packetsSentStartTick > 10)
			{
				getClient().packetsSentStartTick = GameTimeController.getGameTicks();
				getClient().packetsSentInSec = 0;
			}
			else
			{
				getClient().packetsSentInSec++;
				if (getClient().packetsSentInSec > 12) 
				{
					if (getClient().packetsSentInSec < 100)
						sendPacket(ActionFailed.STATIC_PACKET); 
					return;
				}
			}
			
			runImpl();
			
			/* Removes onspawn protection - player has faster computer than average
			 * 
			 * True for these packets:
			 * AttackRequest
			 * MoveBackwardToLocation
			 * RequestActionUse
			 * RequestMagicSkillUse
			 * 
			 * it could include pickup and talk too, but less is better
			 */
			if (triggersOnActionRequest() && getClient().getActiveChar() != null) 
				getClient().getActiveChar().onActionRequest(); 
		 	 		                         
			cleanUp(); 
            
		}
		catch (Throwable t)
		{
			_log.log(Level.SEVERE, "Client: " + getClient().toString() + " - Failed running: " + getType() + " - L2J Server Version: " + Config.SERVER_VERSION + " - DP Revision: " + Config.DATAPACK_VERSION + " ; " + t.getMessage(), t);			
			// in case of EnterWorld error kick player from game 
			if (this instanceof EnterWorld)  
				getClient().closeNow();
		}
	}
	
	protected abstract void runImpl();
	
	protected final void sendPacket(L2GameServerPacket gsp)
	{
		getClient().sendPacket(gsp);
	}
	
	/**
	 * @return A String with this packet name for debuging purposes
	 */
	public abstract String getType();
	
	/**
	 * Overriden with true value on some packets that should disable spawn protection
	 */
	protected boolean triggersOnActionRequest()
	{
		return false;
	}
 		         
	protected void cleanUp() 
	{} 
}
