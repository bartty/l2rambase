/**
 * @author L2Rambase Project
 */
package handlers.voicedcommandhandlers;

import java.util.StringTokenizer;
import java.util.logging.Level;

import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.skills.AbnormalEffect;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 *
 * @author L2Rambase Project
 *
 */
public class CAPTCHA implements IVoicedCommandHandler
{
	private static final String[] _voicedCommands =
	{
		"CAPTCHA","ECAPTCHA"
	};
	
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target)
	{
		NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage( 0 );
		if (command.startsWith("CAPTCHA"))
		{
			if (command.equalsIgnoreCase("CAPTCHA") && target != null)
			{
				StringTokenizer st = new StringTokenizer(target);
				try
				{
					String pass = null;
					if (st.hasMoreTokens()) pass = st.nextToken();
					
					if (pass != null)
					{
						if (pass.equals(activeChar.getCaptchaName()))
						{
							activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);						
							activeChar.setIsInvul(false);
							activeChar.setIsParalyzed(false);
							activeChar.broadcastUserInfo();
							return false;
						}
						
					}
					if (!pass.equals(activeChar.getCaptchaName()))
					{
						String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You are still in jail.</font></center></body></html>";
						npcHtmlMessage.setHtml(Text);
						activeChar.sendPacket( npcHtmlMessage );
						if (activeChar.isFlyingMounted())
							activeChar.untransform();
						if (!activeChar.isInJail())
						{							
							activeChar.setPunishLevel(L2PcInstance.PunishLevel.JAIL, 15);
						}
						activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
						activeChar.setIsInvul(false);
						activeChar.setIsParalyzed(false);
						activeChar.broadcastUserInfo();
						return false;
					}
					else
					{
						String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You are still in jail.</font></center></body></html>";
						npcHtmlMessage.setHtml(Text);
						activeChar.sendPacket( npcHtmlMessage );
						if (activeChar.isFlyingMounted())
							activeChar.untransform();
						if (!activeChar.isInJail())
						{							
							activeChar.setPunishLevel(L2PcInstance.PunishLevel.JAIL, 15);
						}
						activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
						activeChar.setIsInvul(false);
						activeChar.setIsParalyzed(false);
						activeChar.broadcastUserInfo();
						return false;
					}
				}
				catch (Exception e)
				{
					activeChar.sendMessage("A problem occured while adding captcha!");
					_log.log(Level.WARNING, "", e);
				}
			}
			else
			{			
				String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You Will Be Jailed For 15 Min.</font></center></body></html>";
				npcHtmlMessage.setHtml(Text);			
				activeChar.sendPacket( npcHtmlMessage );
				if (activeChar.isFlyingMounted())
					activeChar.untransform();
				if (!activeChar.isInJail())
				{							
					activeChar.setPunishLevel(L2PcInstance.PunishLevel.JAIL, 15);
				}
				activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
				activeChar.setIsInvul(false);
				activeChar.setIsParalyzed(false);
				activeChar.broadcastUserInfo();
				return false;
			}
		}
		else if (command.startsWith("ECAPTCHA"))
		{
			if (command.equalsIgnoreCase("ECAPTCHA") && target != null)
			{
				StringTokenizer st = new StringTokenizer(target);
				try
				{
					String pass = null;
					if (st.hasMoreTokens()) pass = st.nextToken();
					
					if (pass != null)
					{
						if (pass.equals(activeChar.getCaptchaName()))
						{
							activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);						
							activeChar.setIsInvul(false);
							activeChar.setIsParalyzed(false);
							activeChar.broadcastUserInfo();
							return false;
						}
						
					}
					if (!pass.equals(activeChar.getCaptchaName()))
					{
						String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You are still in jail.</font></center></body></html>";
						npcHtmlMessage.setHtml(Text);
						activeChar.sendPacket( npcHtmlMessage );
						if (activeChar.isFlyingMounted())
							activeChar.untransform();
						activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
						activeChar.setIsInvul(false);
						activeChar.setIsParalyzed(false);
						activeChar.broadcastUserInfo();
						activeChar.logout();
						return false;
					}
					else
					{
						String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You are still in jail.</font></center></body></html>";
						npcHtmlMessage.setHtml(Text);
						activeChar.sendPacket( npcHtmlMessage );
						if (activeChar.isFlyingMounted())
							activeChar.untransform();
						activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
						activeChar.setIsInvul(false);
						activeChar.setIsParalyzed(false);
						activeChar.broadcastUserInfo();
						activeChar.logout();
						return false;
					}
				}
				catch (Exception e)
				{
					activeChar.sendMessage("A problem occured while adding captcha!");
					_log.log(Level.WARNING, "", e);
				}
			}
			else
			{			
				String Text = "<html><title>Antibot System Incorrect</title><body><center><font color=FF0000>Incorrect.<br><br></font><font color=66FF00><center></font><font color=FF0000>You Will Be Jailed For 15 Min.</font></center></body></html>";
				npcHtmlMessage.setHtml(Text);			
				activeChar.sendPacket( npcHtmlMessage );
				if (activeChar.isFlyingMounted())
					activeChar.untransform();
				activeChar.logout();
				activeChar.stopAbnormalEffect(AbnormalEffect.REAL_TARGET);
				activeChar.setIsInvul(false);
				activeChar.setIsParalyzed(false);
				activeChar.broadcastUserInfo();
				return false;
			}
		}
		return true;
	}
	
	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}