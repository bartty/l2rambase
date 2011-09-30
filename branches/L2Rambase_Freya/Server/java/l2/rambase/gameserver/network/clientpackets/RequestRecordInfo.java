/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.Collection;

import l2.rambase.gameserver.TaskPriority;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2.rambase.gameserver.network.serverpackets.SpawnItem;
import l2.rambase.gameserver.network.serverpackets.UserInfo;


public class RequestRecordInfo extends L2GameClientPacket
{
	private static final String _0__CF_REQUEST_RECORD_INFO = "[0] CF RequestRecordInfo";

	/** urgent messages, execute immediatly */
	public TaskPriority getPriority() { return TaskPriority.PR_NORMAL; }

	@Override
	protected void readImpl()
	{
	}

    @Override
	protected void runImpl()
	{
		L2PcInstance _activeChar = getClient().getActiveChar();

		if (_activeChar == null)
			return;

		_activeChar.sendPacket(new UserInfo(_activeChar));
		_activeChar.sendPacket(new ExBrExtraUserInfo(_activeChar));

		Collection<L2Object> objs = _activeChar.getKnownList().getKnownObjects().values();
		for (L2Object object : objs)
		{
			if (object.getPoly().isMorphed()
			        && object.getPoly().getPolyType().equals("item"))
				_activeChar.sendPacket(new SpawnItem(object));
			else
			{
				object.sendInfo(_activeChar);
				
				if (object instanceof L2Character)
				{
					// Update the state of the L2Character object client
					// side by sending Server->Client packet
					// MoveToPawn/CharMoveToLocation and AutoAttackStart to
					// the L2PcInstance
					L2Character obj = (L2Character) object;
					if (obj.getAI() != null)
						obj.getAI().describeStateToPlayer(_activeChar);
				}
			}
		}		
	}

	@Override
	public String getType()
	{
		return _0__CF_REQUEST_RECORD_INFO;
	}
}
