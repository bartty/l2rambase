/*
 * L2Rambase Project
 */

package custom.AwlUnderFoot;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.util.Rnd;


public class AwlUnderFoot extends Quest
{
	private static final String qn = "AwlUnderFoot";
	/*
	 * List of Npc and Mobs
	 */
	/*
	 * List of Monsters 60-65 
	 * HAGER_THE_OUTLAW - lvl 61 
	 * ALL_SEEING_RANGO - lvl 62
	 * JAKARD - lvl 63 
	 * GILLIEN- lvl 65
	 */
	private static final int HAGER_THE_OUTLAW = 25572;
	private static final int ALL_SEEING_RANGO = 25575;
	private static final int JAKARD = 25578;
	private static final int HELSING = 25579;
	/*
	 * List of Monsters 65-70 
	 * GILLIEN - lvl 65 
	 * MEDICI - lvl 66 
	 * IMMORTAL_MUUS - lvl 67 
	 * BRAND_THE_EXILE - lvl 68
	 * COMANDER_KOENIG - lvl 69
	 */
	private static final int GILLIEN = 25582;
	private static final int MEDICI = 25585;
	private static final int IMMORTAL_MUUS = 25588;
	private static final int BRAND_THE_EXILE = 25589;
	private static final int COMANDER_KOENIG = 25592;
	/*
	 * List of Monsters 70-75 
	 * GERG - lvl 70
	 * RHIANNA_THE_TRAITOR - lvl 71 
	 * TESLA_THE_DECEIVER - lvl 72 
	 * SOUL_HUNTER_CHAKUNDEL - lvl 73 
	 * DURANGO_THE_CRUSHER - lvl 74
	 */
	private static final int GERG = 25593;
	private static final int RHIANNA_THE_TRAITOR = 25546;
	private static final int TESLA_THE_DECEIVER = 25549;
	private static final int SOUL_HUNTER_CHAKUNDEL = 25552;
	private static final int DURANGO_THE_CRUSHER = 25553;
	/*
	 * List of Monsters 75-80 
	 * BRUTUS_THE_OBSTINATE - lvl 75 
	 * RANGER_KARANKAWA - lvl 76 
	 * SARGON_THE_MAD - lvl 77 
	 * BEATIFUL_ATRIELLE - lvl 78
	 * NAGEN_THE_TOMBOY - lvl 79 
	 * JAX_THE_DESTROYER - lvl 80
	 */
	private static final int BRUTUS_THE_OBSTINATE = 25554;
	private static final int RANGER_KARANKAWA = 25557;
	private static final int SARGON_THE_MAD = 25560;
	private static final int BEATIFUL_ATRIELLE = 25563;
	private static final int NAGEN_THE_TOMBOY = 25566;
	private static final int JAX_THE_DESTROYER = 25569;
	/*
	 * List of all bosses
	 */
	private static final int[] Bosses = { HAGER_THE_OUTLAW, ALL_SEEING_RANGO, HELSING, GILLIEN,
			MEDICI, BRAND_THE_EXILE, RHIANNA_THE_TRAITOR,
			TESLA_THE_DECEIVER, BRUTUS_THE_OBSTINATE, RANGER_KARANKAWA,
			SARGON_THE_MAD, BEATIFUL_ATRIELLE, NAGEN_THE_TOMBOY,
			JAX_THE_DESTROYER, JAKARD, IMMORTAL_MUUS,
			SOUL_HUNTER_CHAKUNDEL, DURANGO_THE_CRUSHER, COMANDER_KOENIG, GERG };
	private static final int[] Minions = { 
		25547,25548,25550,25551,25555,25556,25558,25559,25561,25562,
		25564,25565,25567,25568,25570,25571,25573,25574,25574,25576,
		25577,25580,25581,25583,25584,25586,25587,25590,25591,25594,
		25595 };
	/*
	 * Items
	 */
	private static final int Dungeon_Leader_Mark = 9797;
	private static final int Dungeon_Leader_Mark_Part = 9798;
	
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		if (player.getParty() != null)
		{
			for (L2PcInstance partyMember : player.getParty().getPartyMembers())
			{
				QuestState pst = partyMember.getQuestState(getName());
				if (pst == null)
				{
					pst = newQuestState(partyMember);
				}
				if (pst.getState() == State.STARTED)
				{
					for (int npcId : Bosses)
					{
						if (npc.getNpcId() == npcId)
						{
							long countMark;
							if (npc.getLevel() < 70)
							{
								countMark = 1;
							}
							else
							{
								countMark = Rnd.get(1,2);
							}					
							pst.giveItems(Dungeon_Leader_Mark, countMark);
						}
					}
					for (int npcId : Minions)
					{
						if (npc.getNpcId() == npcId)
						{
							long countMark;
							if (npc.getLevel() < 70)
							{
								countMark = Rnd.get(15,25);
							}
							else
							{
								countMark = Rnd.get(25,50);
							}
							pst.giveItems(Dungeon_Leader_Mark_Part, countMark);
						}
					}
				}
			}
		}
		return super.onKill(npc, player, isPet);
	}
	
	public AwlUnderFoot(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		for (int npcId : Bosses)
			addKillId(npcId);
		for (int npcId : Minions)
			addKillId(npcId);
		
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new AwlUnderFoot(-1,qn,"Awl Under Foot");
		_log.warning("Knight Epaulettes: System for Dungeon Installed ...");
	}
}