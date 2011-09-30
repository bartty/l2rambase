/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.templates.skills.L2SkillType;


public final class RequestMagicSkillUse extends L2GameClientPacket
{
	private static final String _C__2F_REQUESTMAGICSKILLUSE = "[C] 2F RequestMagicSkillUse";
	private static Logger _log = Logger.getLogger(RequestMagicSkillUse.class.getName());

	private int _magicId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	protected void readImpl()
	{
		_magicId      = readD();              // Identifier of the used skill
		_ctrlPressed  = readD() != 0;         // True if it's a ForceAttack : Ctrl pressed
		_shiftPressed = readC() != 0;         // True if Shift pressed
	}

	@Override
	protected void runImpl()
	{
		// Get the current L2PcInstance of the player
		L2PcInstance activeChar = getClient().getActiveChar();

		if (activeChar == null)
			return;

		// Get the level of the used skill
		int level = activeChar.getSkillLevel(_magicId);
		if (level <= 0)
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Get the L2Skill template corresponding to the skillID received from the client
		L2Skill skill = SkillTable.getInstance().getInfo(_magicId, level);

		// Check the validity of the skill
		if (skill != null)
		{
			if ((activeChar.isTransformed() || activeChar.isInStance())
					&& !activeChar.containsAllowedTransformSkill(skill.getId()))
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			// _log.fine("	skill:"+skill.getName() + " level:"+skill.getLevel() + " passive:"+skill.isPassive());
			// _log.fine("	range:"+skill.getCastRange()+" targettype:"+skill.getTargetType()+" optype:"+skill.getOperateType()+" power:"+skill.getPower());
			// _log.fine("	reusedelay:"+skill.getReuseDelay()+" hittime:"+skill.getHitTime());
			// _log.fine("	currentState:"+activeChar.getCurrentState());	//for debug

			// If Alternate rule Karma punishment is set to true, forbid skill Return to player with Karma
			if (skill.getSkillType() == L2SkillType.RECALL && !Config.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && activeChar.getKarma() > 0)
				return;

			// players mounted on pets cannot use any toggle skills
			if (skill.isToggle() && activeChar.isMounted())
					return;
			// activeChar.stopMove();
			activeChar.useMagic(skill, _ctrlPressed, _shiftPressed);
		}
		else
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			_log.warning("No skill found with id " + _magicId + " and level " + level + " !!");
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__2F_REQUESTMAGICSKILLUSE;
	}
	
	@Override
	protected boolean triggersOnActionRequest()
	{
		return true;
	}
}
