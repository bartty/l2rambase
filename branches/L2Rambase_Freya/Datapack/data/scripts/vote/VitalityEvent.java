/**
 * @author L2Rambase Project
 */
package vote;

import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;
import l2.rambase.gameserver.model.actor.stat.PcStat;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class VitalityEvent extends Quest
{
	// Reuse between buffs
	private static final int _hours = 4;	
	private static final int _jack = 4306;	
	public boolean GOF_STARTED = false;	
	private static final int TIME_OF_EVENT = 5;
	
	public static final String qn = "VitalityEvent";
	public long _lastTimeOfStart = 0;
	// Time in hours
	private static final int TIME_FOR_NEXT = 24;
			
	public VitalityEvent(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(_jack);
		addFirstTalkId(_jack);
		addTalkId(_jack);		
	}
	
	private class StopEvent implements Runnable 
	{
		public void run() 
		{
			if (GOF_STARTED)
			{
				GOF_STARTED = false;
				Announcements.getInstance().announceToAll("=======================",1);
				Announcements.getInstance().announceToAll("Vitality Event Stoped:",1);
				Announcements.getInstance().announceToAll("Vote for our server",1);
				Announcements.getInstance().announceToAll("=======================",1);
			}
		}
	}
	
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		htmltext = event;

		if (event.equalsIgnoreCase("vitality"))
		{
			long _reuse = 0;
			String _streuse = st.get("reuse");
			if(_streuse != null)
				_reuse = Long.parseLong(_streuse);
			if(_reuse > System.currentTimeMillis())
			{
				long remainingTime = (_reuse - System.currentTimeMillis()) / 1000;
				int hours = (int) (remainingTime / 3600);
				int minutes = (int) ((remainingTime%3600) / 60);
				SystemMessage sm = new SystemMessage(SystemMessageId.AVAILABLE_AFTER_S1_S2_HOURS_S3_MINUTES);
				sm.addSkillName(23179);
				sm.addNumber(hours);
				sm.addNumber(minutes);
				player.sendPacket(sm);
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "Look me straight in the eyes and answer me. Haven't you already received the Gift of Vitality? If you lie, I will break your neck.<br>";
				htmltext += "(The Gift of Vitality buff will disappear when switching sub-classes.)<br><br>";
				htmltext += "</body></html>";

			}
			else
			{
				npc.setTarget(player);
				npc.doCast(SkillTable.getInstance().getInfo(23179,1));	// Gift of Vitality
				player.setVitalityPoints(PcStat.MAX_VITALITY_POINTS, false);
				st.setState(State.STARTED);
				st.set("reuse", String.valueOf(System.currentTimeMillis() + _hours*60*60*1000));
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "Your Vitality has been replenished to level 4 and will be maintained in that state for 2 hours! Go outside, defeat monsters, and return as an adventurer capable of protecting the world!<br>";
				htmltext += "(The Gift of Vitality buff will disappear when switching sub-classes.)<br><br>";
				htmltext += "</body></html>";
			}
		}
		else if (event.equalsIgnoreCase("memories_player"))
		{
			if (player.getLevel() < 76)
			{
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "You are not qualified to receive the Memories of a Newbie. Can you see a newbie guide or adventurer guide next to me? Please receive the Newbie Support buffs from them. The Memories of a Newbie has the same effect as the Newbie Support buffs which you're receiving.<br>";
				htmltext += "(Only characters level 76 or more can receive the Memories of a Newbie.)<br><br>";
				htmltext += "</body></html>";
			}
			else
			{				
				if (player.isMageClass())
				{
					npc.setTarget(player);
					npc.doCast(SkillTable.getInstance().getInfo(5627,1));	// Wind Walk
					npc.doCast(SkillTable.getInstance().getInfo(5628,1));	// Shield
					npc.doCast(SkillTable.getInstance().getInfo(5637,1));	// Magic Barrier
					npc.doCast(SkillTable.getInstance().getInfo(5633,1));	// Bless the Soul
					npc.doCast(SkillTable.getInstance().getInfo(5634,1));	// Acumen
					npc.doCast(SkillTable.getInstance().getInfo(5635,1));	// Concentration
					npc.doCast(SkillTable.getInstance().getInfo(5636,1));	// Empower
				}
				else
				{
					npc.setTarget(player);
					npc.doCast(SkillTable.getInstance().getInfo(5627,1));	// Wind Walk
					npc.doCast(SkillTable.getInstance().getInfo(5628,1));	// Shield
					npc.doCast(SkillTable.getInstance().getInfo(5637,1));	// Magic Barrier
					npc.doCast(SkillTable.getInstance().getInfo(5629,1));	// Bless the Body
					npc.doCast(SkillTable.getInstance().getInfo(5630,1));	// Vampiric Rage
					npc.doCast(SkillTable.getInstance().getInfo(5631,1));	// Regeneration
					npc.doCast(SkillTable.getInstance().getInfo(5632,1));	// Haste
				}
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "May Eva's blessing accompany you on your journey.<br>";
				htmltext += "Good luck!<br><br>";
				htmltext += "</body></html>";				
			}
		}
		else if (event.equalsIgnoreCase("memories_summon"))
		{
			if (player.getLevel() < 76)
			{
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "You are not qualified to receive the Memories of a Newbie. Can you see a newbie guide or adventurer guide next to me? Please receive the Newbie Support buffs from them. The Memories of a Newbie has the same effect as the Newbie Support buffs which you're receiving.<br>";
				htmltext += "(Only characters level 76 or more can receive the Memories of a Newbie.)<br><br>";
				htmltext += "</body></html>";
			}
			else if (player.getPet() == null || !(player.getPet() instanceof L2SummonInstance))
			{
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "Hum. You don't have any summon, do you? Why do you covet a gift that is not intended for you? You can receive the Memories of a Newbie for summons only if you bring a summon.<br><br>";
				htmltext += "</body></html>";
			}
			else
			{
				npc.setTarget(player.getPet());
				npc.doCast(SkillTable.getInstance().getInfo(5627,1));	// Wind Walk
				npc.doCast(SkillTable.getInstance().getInfo(5628,1));	// Shield
				npc.doCast(SkillTable.getInstance().getInfo(5637,1));	// Magic Barrier
				npc.doCast(SkillTable.getInstance().getInfo(5629,1));	// Bless the Body
				npc.doCast(SkillTable.getInstance().getInfo(5633,1));	// Bless the Soul
				npc.doCast(SkillTable.getInstance().getInfo(5630,1));	// Vampiric Rage
				npc.doCast(SkillTable.getInstance().getInfo(5634,1));	// Acumen
				npc.doCast(SkillTable.getInstance().getInfo(5631,1));	// Regeneration
				npc.doCast(SkillTable.getInstance().getInfo(5635,1));	// Concentration
				npc.doCast(SkillTable.getInstance().getInfo(5632,1));	// Haste
				npc.doCast(SkillTable.getInstance().getInfo(5636,1));	// Empower
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "May Eva's blessing accompany you on your journey.<br>";
				htmltext += "Good luck!<br><br>";
				htmltext += "</body></html>";
			}
		}
		else if (event.equalsIgnoreCase("4306-02"))
		{
			htmltext = "<html><body>Jack Sage:<br><br>";
			htmltext += "The Memories of a Newbie provides the &quot;Newbie Support buffs&quot;, which can be received up to level 75, to veteran adventurers of level 76 or more.<br>";
			htmltext += "This is an additional gift given by the Temple, so that all of you can focus on training. This is a small reward to all the great adventurers who fought on the front lines when our world was in danger. Please note that it will only be available <font color=LEVEL>during the time I'm dispatched</font>.<br><br>";
			htmltext += "You will receive the following buffs:<br><br>";
			htmltext += "Warrior: Wind Walk, Shield, Magic Barrier, Bless the Body, Vampiric Rage, Regeneration, Haste.<br>";
			htmltext += "Mage: Wind Walk, Shield, Magic Barrier, Bless the Soul, Acumen, Concentration, Empower.<br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" memories_player\">Receive the Memories of a Newbie.</a><br><br>";
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("4306-03"))
		{
			htmltext = "<html><body>Jack Sage:<br><br>";
			htmltext += "The Memories of a Newbie for summons provides the &quot;Newbie Support buffs&quot;, which can be received up to level 75, to the veteran adventurers' summons of level 76 or more.<br>";
			htmltext += "This is an additional gift given by the Temple, so that all of you can focus on training. This is a small reward to all the great adventurers who fought on the front lines when our world was in danger. Please note that it will only be available <font color=LEVEL>during the time I'm dispatched</font>.<br><br>";
			htmltext += "Your summon will receive the following buffs (these buffs do not apply to pets):<br><br>";
			htmltext += "Wind Walk, Shield, Magic Barrier, Bless the Body, Bless the Soul, Vampiric Rage, Acumen, Regeneration, Concentration, Haste, and Empower.<br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" memories_summon\">Receive the Memories of a Newbie for summons.</a><br><br>";
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("4306-04"))
		{
			htmltext = "<html><body>Jack Sage:<br><br>";
			htmltext += "The Oracle said that the great danger coming from the Frozen Labyrinth in the Schuttgart territory will affect the whole world. Since the Archbishop has received this revelation from Eva, his nights have been sleepless. He finally went to the Magic Research Institute and came to an important decision: the Gift of Vitality must be unleashed. This shall help Aden's adventurers to reveal their maximum potential.<br>";
			htmltext += "The Gift was sealed centuries ago and a huge amount of money and effort from the highest ranked wizards was required for this decision to become real. The Magic Research Institute made it happen.<br>";
			htmltext += "Now everyone must work together to face the approaching evil.<br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" 4306-05\">What should I do?</a><br><br>";
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("4306-05"))
		{
			htmltext = "<html><body>Jack Sage:<br><br>";
			htmltext += "All you need to do is work harder on the things that you have done up to now. <font color=LEVEL>Train more, become stronger</font>, and stretch your limits! Soon a great danger will threaten the world. We are in dire need of talented adventurers who are prepared to fight! I recommend you take the Gift of Vitality and go hunting!<br><br>";
			htmltext += "</body></html>";
		}
		else if (event.equalsIgnoreCase("start"))
		{
			if (!GOF_STARTED)
			{
				if (checkCanStart(System.currentTimeMillis(),_lastTimeOfStart))
				{
					if (player != null)
					{
						GOF_STARTED = true;
						player.sendMessage("Event status is now changed");
						Announcements.getInstance().announceToAll("=======================",1);
						Announcements.getInstance().announceToAll("Vitality Event Started:",1);					
						Announcements.getInstance().announceToAll("Jack Sage give your price for voting.",1);
						Announcements.getInstance().announceToAll("This event is limited to "+String.valueOf(TIME_OF_EVENT) + " hours.",1);
						Announcements.getInstance().announceToAll("=======================",1);					
					}
					htmltext = "";
					ThreadPoolManager.getInstance().scheduleGeneral(new StopEvent(), (TIME_OF_EVENT*3600)*1000);
				}
			}
		}
		return htmltext;
	}
	
	private boolean checkCanStart(long currentTimeMillis, long lastTimeOfStart) 
	{
		if (lastTimeOfStart == 0)
		{
			_lastTimeOfStart = System.currentTimeMillis();
			return true;
		}
		else if (currentTimeMillis - lastTimeOfStart > (TIME_FOR_NEXT*3600*1000))
		{
			_lastTimeOfStart = System.currentTimeMillis();
			return true;
		}
		else
		{			
			return false;
		}
	}

	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			Quest q = QuestManager.getInstance().getQuest(getName());
			st = q.newQuestState(player);
		}
		String htmltext = "";
		if (GOF_STARTED)
		{			
			htmltext = "<html><body>Jack Sage:<br><br>";
			htmltext += "Greetings, brave adventurer of Aden!<br>";
			htmltext += "My name is Jack Sage from Eva's Temple. According to Eva's Oracle, <font color=LEVEL>the world is now facing a great danger</font>. So great, in fact, that in our current state we cannot cope with it. As a consequence, Eva's Temple and the Magic Research Institute have decided to provide an ancient magic, which had been sealed long ago, to every adventurer who wishes to improve their abilities. It is called the <font color=LEVEL>Gift of Vitality</font>. The Gift of Vitality <font color=LEVEL>fills the vitality up to level 4 and maintains that state for 2 hours!</font> We shall do our best to overcome the imminent danger.<br>";
			htmltext += "May Eva's blessing accompany you on your journey.<br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" vitality\">Receive the Gift of Vitality.</a><br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" 4306-02\">Ask about the Memories of a Newbie.</a><br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" 4306-03\">Ask about the Memories of a Newbie for summons.</a><br><br>";
			htmltext += "<a action=\"bypass -h Quest "+qn+" 4306-04\">What is the great danger you're talking about?</a><br><br>";
			htmltext += "</body></html>";
		}
		else
		{			
			if (player.isGM())
			{
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "Greetings, brave adventurer of Aden!<br>";
				htmltext += "My name is Jack Sage from Eva's Temple. According to Eva's Oracle, <font color=LEVEL>the world is now facing a great danger</font>. So great, in fact, that in our current state we cannot cope with it. As a consequence, Eva's Temple and the Magic Research Institute have decided to provide an ancient magic, which had been sealed long ago, to every adventurer who wishes to improve their abilities. It is called the <font color=LEVEL>Gift of Vitality</font>. The Gift of Vitality <font color=LEVEL>fills the vitality up to level 4 and maintains that state for 2 hours!</font> We shall do our best to overcome the imminent danger.<br>";
				htmltext += "May Eva's blessing cannot accompany you on your journey.<br><br>";
				htmltext += "<a action=\"bypass -h Quest "+qn+" start\">(GM Option) Start Event</a><br><br>";
				htmltext += "</body></html>";
			}
			else
			{
				htmltext = "<html><body>Jack Sage:<br><br>";
				htmltext += "Who are you?<br1>";
				htmltext += "My name is Jack Sage from Eva's Temple.";
				htmltext += "I don't know you so i cannot give you anything. <br><br></body></html>";
			}
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new VitalityEvent(-1, qn, "events");
	}
}