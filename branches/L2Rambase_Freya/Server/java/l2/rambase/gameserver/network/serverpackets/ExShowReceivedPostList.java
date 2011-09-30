/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import javolution.util.FastList;

import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.model.entity.Message;


public class ExShowReceivedPostList extends L2GameServerPacket
{
	private static final String _S__FE_AA_EXSHOWRECEIVEDPOSTLIST = "[S] FE:AA ExShowReceivedPostList";

	private List<Message> _inbox;

	public ExShowReceivedPostList(int objectId)
	{
		_inbox = MailManager.getInstance().getInbox(objectId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xaa);
		writeD((int)(System.currentTimeMillis() / 1000));
		if (_inbox != null && _inbox.size() > 0)
		{
			writeD(_inbox.size());
			for (Message msg : _inbox)
			{
				writeD(msg.getId());
				writeS(msg.getSubject());
				writeS(msg.getSenderName());
				writeD(msg.isLocked() ? 0x01 : 0x00);
				writeD(msg.getExpirationSeconds());
				writeD(msg.isUnread() ? 0x01 : 0x00);
				writeD(0x01);
				writeD(msg.hasAttachments() ? 0x01 : 0x00);
				writeD(msg.isFourStars() ? 0x01 : 0x00);
				writeD(msg.isNews() ? 0x01 : 0x00);
			}
		}
		else
		{
			writeD(0x00);
		}
		FastList.recycle((FastList<Message>) _inbox);
		_inbox = null;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_AA_EXSHOWRECEIVEDPOSTLIST;
	}
}