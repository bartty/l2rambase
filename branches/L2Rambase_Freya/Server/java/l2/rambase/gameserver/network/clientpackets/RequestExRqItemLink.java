/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.ExRpItemLink;

public class RequestExRqItemLink extends L2GameClientPacket
{
	private static String name = "[C] DO:1E RequestExRqItemLink";
	private int _objectId;
	private Logger _log = Logger.getLogger(RequestExRqItemLink.class.getName());
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return name;
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
		_objectId = readD();
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2GameClient client = this.getClient();
		if (client != null)
		{
			L2Object object = L2World.getInstance().findObject(_objectId);
			if (object instanceof L2ItemInstance)
			{
				L2ItemInstance item = (L2ItemInstance)object;
				if (item.isPublished())
					client.sendPacket(new ExRpItemLink(item));
				else
					_log.info(getClient()+" requested "+name+" for item which wasnt published! ID:"+_objectId);
			}
		}
	}
	
}
