/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.UserInfo;

public final class RequestEvaluate extends L2GameClientPacket
{
	private static final String _C__B9_REQUESTEVALUATE = "[C] B9 RequestEvaluate";

	@SuppressWarnings("unused")
    private int _targetId;

	@Override
	protected void readImpl()
	{
		_targetId = readD();
	}

	@Override
	protected void runImpl()
	{
		SystemMessage sm;
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;


        if (!(activeChar.getTarget() instanceof L2PcInstance))
        {
            sm = new SystemMessage(SystemMessageId.TARGET_IS_INCORRECT);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        if (activeChar.getLevel() < 10)
        {
            sm = new SystemMessage(SystemMessageId.ONLY_LEVEL_SUP_10_CAN_RECOMMEND);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        if (activeChar.getTarget() == activeChar)
        {
            sm = new SystemMessage(SystemMessageId.YOU_CANNOT_RECOMMEND_YOURSELF);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        if (activeChar.getRecomLeft() <= 0)
        {
            sm = new SystemMessage(SystemMessageId.NO_MORE_RECOMMENDATIONS_TO_HAVE);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        L2PcInstance target = (L2PcInstance)activeChar.getTarget();

        if (target.getRecomHave() >= 255)
        {
            sm = new SystemMessage(SystemMessageId.YOUR_TARGET_NO_LONGER_RECEIVE_A_RECOMMENDATION);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        if (!activeChar.canRecom(target))
        {
            sm = new SystemMessage(SystemMessageId.THAT_CHARACTER_IS_RECOMMENDED);
            activeChar.sendPacket(sm);
            sm =null;
            return;
        }

        activeChar.giveRecom(target);

		sm = new SystemMessage(SystemMessageId.YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT);
		sm.addPcName(target);
        sm.addNumber(activeChar.getRecomLeft());
		activeChar.sendPacket(sm);

		sm = new SystemMessage(SystemMessageId.YOU_HAVE_BEEN_RECOMMENDED_BY_C1);
		sm.addPcName(activeChar);
		target.sendPacket(sm);
		sm =null;

        activeChar.sendPacket(new UserInfo(activeChar));
        sendPacket(new ExBrExtraUserInfo(activeChar));
		target.broadcastUserInfo();
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType() {
		return _C__B9_REQUESTEVALUATE;
	}
}
