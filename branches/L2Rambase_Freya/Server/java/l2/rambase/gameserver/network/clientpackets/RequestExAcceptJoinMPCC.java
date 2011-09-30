/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2CommandChannel;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 * format: (ch) d
 * 
 */
public final class RequestExAcceptJoinMPCC extends L2GameClientPacket
{
	private static final String _C__D0_0E_REQUESTEXASKJOINMPCC = "[C] D0:0E RequestExAcceptJoinMPCC";
	private int _response;

	/**
	 * @param buf
	 * @param client
	 */
	@Override
	protected void readImpl()
	{
		_response = readD();
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
        if(player != null)
        {
	    	L2PcInstance requestor = player.getActiveRequester();
	    	SystemMessage sm;
			if (requestor == null)
			    return;

			if (_response == 1)
	        {
				boolean newCc = false;
				if(!requestor.getParty().isInCommandChannel())
				{
					new L2CommandChannel(requestor); // Create new CC
					sm = new SystemMessage(SystemMessageId.COMMAND_CHANNEL_FORMED);
					requestor.sendPacket(sm);
					newCc = true;
				}
				requestor.getParty().getCommandChannel().addParty(player.getParty());
				if (!newCc)
				{
					sm = new SystemMessage(SystemMessageId.JOINED_COMMAND_CHANNEL);
					player.sendPacket(sm);
				}
			}
			else
	        {
				requestor.sendMessage("The player declined to join your Command Channel.");
			}

			player.setActiveRequester(null);
			requestor.onTransactionResponse();
        }

	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_0E_REQUESTEXASKJOINMPCC;
	}

}
