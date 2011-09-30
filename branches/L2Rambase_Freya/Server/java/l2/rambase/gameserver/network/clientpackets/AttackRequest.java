/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;

/**
 * This class ...
 *
 * @version $Revision: 1.7.2.1.2.2 $ $Date: 2005/03/27 15:29:30 $
 */
public final class AttackRequest extends L2GameClientPacket
{
	// cddddc
	private int _objectId;
	@SuppressWarnings("unused")
    private int _originX;
	@SuppressWarnings("unused")
    private int _originY;
	@SuppressWarnings("unused")
    private int _originZ;
	@SuppressWarnings("unused")
    private int _attackId;

	private static final String _C__0A_ATTACKREQUEST = "[C] 0A AttackRequest";

	@Override
	protected void readImpl()
	{
		_objectId  = readD();
		_originX  = readD();
		_originY  = readD();
		_originZ  = readD();
		_attackId  = readC(); 	 // 0 for simple click   1 for shift-click
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null) return;
		// avoid using expensive operations if not needed
		final L2Object target;
		if (activeChar.getTargetId() == _objectId)
			target = activeChar.getTarget();
		else
			target = L2World.getInstance().findObject(_objectId);
		if (target == null)
			return;

		// Players can't attack objects in the other instances
		// except from multiverse
		if (target.getInstanceId() != activeChar.getInstanceId()
				&& activeChar.getInstanceId() != -1)
			return;

		// Only GMs can directly attack invisible characters
		if (target instanceof L2PcInstance
				&& ((L2PcInstance)target).getAppearance().getInvisible()
				&& !activeChar.isGM())
			return;

		if (activeChar.getTarget() != target)
		{
			target.onAction(activeChar);
		}
		else
		{
			if ((target.getObjectId() != activeChar.getObjectId())
					&& activeChar.getPrivateStoreType() ==0
					&& activeChar.getActiveRequester() ==null)
			{
				//_log.debug("Starting ForcedAttack");
				target.onForcedAttack(activeChar);
				//_log.debug("Ending ForcedAttack");
			}
			else
			{
				sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__0A_ATTACKREQUEST;
	}
	
	@Override
	protected boolean triggersOnActionRequest()
	{
		return true;
	}
}
