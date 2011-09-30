package events.SquashEvent;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2RambaseonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.util.Rnd;

import events.EventsConfig;

/**
 * @author L2Rambase Project
 */
public class SquashEvent extends Quest
{
	private static final int MANAGER = 31860;
	
	private static final int NECTAR_SKILL = 2005;
		
	private static final int[] CHRONO_LIST =
	{
		4202,5133,5817,7058,8350
	};
	
	private static final int[] SQUASH_LIST =
	{
		12774,12775,12776,
		12777,12778,12779,
		13016,13017
	};
	
	private static final int[] EventMonsters = 
	{ 
		7000,7001,7002,7003,7004,7005,7006,7007,7008,7009,
		7010,7011,7012,7013,7014,7015,7016,7017,7018,7019,
		7020,7021,7022,7023
	};
	
	private static final String[] _NOCHRONO_TEXT =
	{
		"You cannot kill me without Chrono",
		"Hehe...keep trying...",
		"Nice try...",
		"Tired ?",
		"Go go ! haha..."
	};
	
	private static final String[] _CHRONO_TEXT =
	{
		"Arghh... Chrono weapon...",
		"My end is coming...",
		"Please leave me !",
		"Heeellpppp...",
		"Somebody help me please..."
	};
	private static final String[] _NECTAR_TEXT =
	{
		"Yummy... Nectar...",
		"Plase give me more...",
		"Hmmm.. More.. I need more...",
		"I will like you more if you give me more...",
		"Hmmmmmmm...",
		"My favourite..."
	};
	
	private static final int Adena = 57;
	private static final int Lesser_Healing_Potion = 1060;
	private static final int Healing_Potion = 1061;
	private static final int Haste_Potion = 1062;
	private static final int Greater_Haste_Potion = 1374;
	private static final int Greater_Swift_Attack_Potion = 1375;
	private static final int Crystal_D_Grade = 1458;
	private static final int Crystal_C_Grade = 1459;
	private static final int Crystal_B_Grade = 1460;
	private static final int Crystal_A_Grade = 1460;
	private static final int Greater_Healing_Potion = 1539;
	private static final int Quick_Healing_Potion = 1540;
	private static final int Mystery_Potion = 5234;
	private static final int Magic_Haste_Potion = 6035;
	private static final int Greather_Magic_Haste_Potion = 6036;
	private static final int Energy_Ginseng = 13028;
	private static final int Baguette_Herb_1 = 20272;
	private static final int Baguette_Herb_2 = 20273;
	private static final int Baguette_Herb_3 = 20274;	
	
	private static final int[][] DROPLIST =
	{
		{ 12774, Baguette_Herb_1, 80 },
		{ 12774, Lesser_Healing_Potion, 100 },
		{ 12774, Haste_Potion, 50 },
		
		{ 12775, Baguette_Herb_2, 80 },
		{ 12775, Greater_Healing_Potion, 100 },
		{ 12775, Greater_Swift_Attack_Potion, 70 },
		{ 12775, Crystal_C_Grade, 50 },
		
		{ 12776, Baguette_Herb_3, 80 },
		{ 12776, Healing_Potion, 100 },
		{ 12776, Haste_Potion, 70 },
		{ 12776, Crystal_D_Grade, 50 },
		
		{ 12777, Baguette_Herb_1, 80 },
		{ 12777, Healing_Potion, 100 },
		{ 12777, Greater_Haste_Potion, 50 },
		
		{ 12778, Baguette_Herb_2, 80 },
		{ 12778, Greater_Healing_Potion, 100 },
		{ 12778, Greather_Magic_Haste_Potion, 70 },
		{ 12778, Crystal_C_Grade, 40 },
		
		{ 12779, Baguette_Herb_3, 80 },
		{ 12779, Magic_Haste_Potion, 70 },
		{ 12779, Crystal_D_Grade, 50 },
		
		{ 13016, Adena, 100 },
		{ 13016, Quick_Healing_Potion, 100 },
		{ 13016, Crystal_B_Grade, 40 },
		{ 13016, Mystery_Potion, 20 },
		
		{ 13017, Adena, 100 },
		{ 13017, Quick_Healing_Potion, 100 },
		{ 13017, Energy_Ginseng, 40 },
		{ 13017, Crystal_A_Grade, 20 },
		{ 13017, Mystery_Potion, 10 }
	};
	
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
	{
		if (contains(SQUASH_LIST,npc.getNpcId()))
		{
			if(isPet)
			{
				noChronoText(npc);
				npc.setIsInvul(true);
				return null;
			}
			if(attacker.getActiveWeaponItem() != null && contains(CHRONO_LIST,attacker.getActiveWeaponItem().getItemId()))
			{
				ChronoText(npc);
				npc.setIsInvul(false);
				npc.getStatus().reduceHp(1250, attacker);
				return null;
			}
			else
			{
				noChronoText(npc);
				npc.setIsInvul(true);
				return null;
			}
		}
		return super.onAttack(npc, attacker, damage, isPet);
	}
	
	public String onSkillSee(L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
	{
		if (contains(targets,npc) && contains(SQUASH_LIST,npc.getNpcId()) && (skill.getId() == NECTAR_SKILL))
		{
			switch(npc.getNpcId())
			{
				case 12774:
					randomSpawn(12775, 12776, npc, true);
					break;
				case 12777:
					randomSpawn(12778, 12779, npc, true);
					break;
				case 12775:
	  				randomSpawn(13016, npc, true);
					break;
				case 12778:
	  				randomSpawn(13017, npc, true);
					break;
			}
		}
		return super.onSkillSee(npc,caster,skill,targets,isPet);
	}
	
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		if (EventsConfig.SQUASH_DROP_ACTIVE)
		{
			for (int ID: SQUASH_LIST)
			{
				if (npc.getNpcId() == ID)
				{
					dropItem(npc, killer);
				}
			}
		}
		QuestState st = killer.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(killer);	
		}
		if (EventsConfig.SQUASH_DROP_ACTIVE)
		{
			for(int ID : EventMonsters)
			{ 
				if (npc.getNpcId() == ID)
				{
					st.giveItems(6391,1);
				}
			}
		}
		return super.onKill(npc, killer, isPet);
	}
	
	public String onSpawn(L2Npc npc) 
	{
		npc.setIsImmobilized(true);
		npc.disableCoreAI(true);
		return null;
	}
	
	private static final void dropItem(L2Npc mob, L2PcInstance player)
	{
		final int npcId = mob.getNpcId();
		final int chance = Rnd.get(100);
		for (int i = 0; i < DROPLIST.length; i++)
		{
			int[] drop = DROPLIST[i];
			if (npcId == drop[0])
			{
				if (chance < drop[2])
				{
					if(drop[1] > 20000)
					{
						int adenaCount = Rnd.get(1000,5000);
						if (drop[1] == 57)
						{
							((L2RambaseonsterInstance)mob).dropItem(player, drop[1], adenaCount);
						}
						else
						{
							((L2RambaseonsterInstance)mob).dropItem(player, drop[1], 2);
						}
					}
					else
					{
						int adenaCount = Rnd.get(5000,15000);
						if (drop[1] == 57)
						{
							((L2RambaseonsterInstance)mob).dropItem(player, drop[1], adenaCount);
						}
						else
						{
							((L2RambaseonsterInstance)mob).dropItem(player, drop[1], Rnd.get(2, 6));
						}
					}
					continue;
				}
			}
			if (npcId < drop[0])
				return;
		}
	}
	
	private void randomSpawn(int lower, int higher, L2Npc npc, boolean delete)
	{
		int _random = Rnd.get(100);
		if (_random < 10)
			spawnNext(lower, npc);
		else if(_random < 30)
			spawnNext(higher, npc);
		else
			nectarText(npc);
	}
	
	private void randomSpawn(int npcId, L2Npc npc, boolean delete)
	{
		if(Rnd.get(100) < 10)
			spawnNext(npcId, npc);
		else
			nectarText(npc);
	}
	
	private void ChronoText(L2Npc npc)
	{
		if(Rnd.get(100) < 20)
			npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), _CHRONO_TEXT[Rnd.get(_CHRONO_TEXT.length)]));		
	}
	private void noChronoText(L2Npc npc)
	{
		if(Rnd.get(100) < 20)
			npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), _NOCHRONO_TEXT[Rnd.get(_NOCHRONO_TEXT.length)]));		
	}
	private void nectarText(L2Npc npc)
	{
		if(Rnd.get(100) < 30)
			npc.broadcastPacket(new CreatureSay(npc.getObjectId(), Say2.ALL, npc.getName(), _NECTAR_TEXT[Rnd.get(_NECTAR_TEXT.length)]));
	}
	
	private void spawnNext(int npcId, L2Npc npc)
	{
		addSpawn(npcId, npc.getX(), npc.getY(), npc.getZ(), npc.getHeading(), false, 300000);		
		npc.deleteMe();
	}

	public static <T> boolean contains(T[] array, T obj)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == obj)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(int[] array, int obj)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == obj)
			{
				return true;
			}
		}
		return false;
	}
	
	public SquashEvent(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		for (int mob : SQUASH_LIST)
		{
			addAttackId(mob);
			addKillId(mob);
			addSpawnId(mob);
			addSkillSeeId(mob);
		}

		addStartNpc(MANAGER);
		addFirstTalkId(MANAGER);
		addTalkId(MANAGER);
		
		for (int MONSTER: EventMonsters)
		{
			addKillId(MONSTER);
		}
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{			
			st = newQuestState(player);
		}
		if (EventsConfig.SQUASH_STARTED)
		{
			htmltext = npc.getNpcId() + ".htm";
		}
		else
		{
			htmltext = EventsConfig.EVENT_DISABLED;
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new SquashEvent(-1,"SquashEvent","events");
	}
}