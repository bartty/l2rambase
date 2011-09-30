/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.network.serverpackets.AbstractNpcInfo;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class L2BlockInstance extends L2Npc
{
	private boolean _isRed;
	
	/**
	 * @param objectId
	 * @param template
	 */
	public L2BlockInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}
	
	/**
	 * Will change the color of the block and update
	 * the appearance in the known players clients
	 */
	public void changeColor(L2PcInstance attacker)
	{
		// Do not update color while sending old info
		synchronized(this)
		{
			if(_isRed)
			{
				// Change color
				_isRed = false;
				// BroadCast to all known players
				this.broadcastPacket(new AbstractNpcInfo.NpcInfo(this, attacker));
			}
			else
			{
				// Change color
				_isRed = true;
				// BroadCast to all known players
				this.broadcastPacket(new AbstractNpcInfo.NpcInfo(this, attacker));
			}
		}
	}
	
	/**
	 * Sets if the block is red or blue. Mainly used in
	 * block spawn
	 * @param isRed
	 */
	public void setRed(boolean isRed)
	{
		_isRed = isRed;
	}
	
	/**
	 * Return if the block is red at this momment
	 * @return
	 */
	public boolean isRed()
	{
		return _isRed;
	}
}