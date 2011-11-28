package events.SquashEvent;

import l2.Rambase.gameserver.instancemanager.QuestManager;
import l2.Rambase.gameserver.model.L2Object;
import l2.Rambase.gameserver.model.L2Skill;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
import l2.Rambase.gameserver.network.clientpackets.Say2;
import l2.Rambase.gameserver.network.serverpackets.CreatureSay;
import l2.Rambase.util.Rnd;

import events.EventsConfig;

/**
 * @author  L2RamBase Project
 * @recoded for Hi5 - ShadowMan
 */
public class SquashEvent extends Quest
{
        private static final String qn = "SquashEvent";
        private static final int MANAGER = 31860;
        private static final int NECTAR_SKILL = 2005;
        private static final int[] CHRONO_LIST = { 4202 , 5133 , 5817 , 7058 , 8350 };
        
        private static final int[] SQUASH_LIST = { 12774 , 12775 , 12776 , 12777 , 12778 , 12779 , 13016 , 13017 };
        
        private static final String[] _NOCHRONO_TEXT = { "You cannot kill me without Chrono", "Hehe...keep trying...", "Nice try...", "Tired ?", "Go go ! haha..." };
    
        private static final String[] _CHRONO_TEXT = { "Arghh... Chrono weapon...", "My end is coming...", "Please leave me!", "Heeellpppp...", "Somebody help me please..." };
    
        private static final String[] _NECTAR_TEXT = { "Yummie... Nectar...", "Plase give me more...", "Hmmm.. More.. I need more...", "I would like you more, if you give me more...", "Hmmmmmmm...", "My favourite..." };
        
        private static final int Adena = 57;
        private static final int Crystal_A_Grade = 1461;
        private static final int Crystal_S_Grade = 1462;
        private static final int Blessed_SOE = 1538;
        private static final int Quick_Healing_Potion = 1540;
        private static final int Gems_A_Grade = 2133;
        private static final int Gems_S_Grade = 2134;
        private static final int Lunargent = 6029;
        private static final int Hellfire_oil = 6033;
        private static final int Firework = 6406;
        private static final int Large_Firework = 6407;
		private static final int Giant_Codex = 6622;
        private static final int High_Grade_LS76 = 8752;
        private static final int Top_Grade_LS76 = 8762;
        private static final int Energy_Ginseng = 20004;
        private static final int Baguette_Herb_1 = 20272;
        private static final int Baguette_Herb_2 = 20273;
        private static final int Baguette_Herb_3 = 20274;
		private static final int Large_Lucky_cub = 22005;
		private static final int Ancient_Enchant_Weapon_A = 22015;
		private static final int Ancient_Enchant_Armour_A = 22017;
        
        private static final int[][] DROPLIST =
        {
                // High Quality Young Squash
                { 12775, Baguette_Herb_2, 100 },
                { 12775, Energy_Ginseng, 100 },
                { 12775, Gems_S_Grade, 80 },
                { 12775, Crystal_S_Grade, 80 },
                { 12775, Quick_Healing_Potion, 75 },
				{ 12775, Large_Lucky_cub, 50 },
                { 12775, Adena, 100 },
                
                // Low Quality Young Squash
                { 12776, Baguette_Herb_3, 100 },
                { 12776, Quick_Healing_Potion, 70 },
                { 12776, Gems_A_Grade, 80 },
                { 12776, Crystal_A_Grade, 100 },
                { 12776, High_Grade_LS76, 10 },
                { 12776, Adena, 100 },
                
                // High Quality Large Squash
                { 12778, Baguette_Herb_2, 100 },
                { 12778, Adena, 100 },
                { 12778, Top_Grade_LS76, 35 },
                { 12778, Giant_Codex, 10 },
                { 12778, Quick_Healing_Potion, 60 },
                { 12778, Gems_S_Grade, 80 },
                { 12778, Crystal_S_Grade, 100 },
				{ 12778, Energy_Ginseng, 100 },
				{ 12778, Large_Lucky_cub, 60 },
                
                // Low Quality Large Squash
                { 12779, Baguette_Herb_3, 100 },
                { 12779, Adena, 100 },
                { 12779, High_Grade_LS76, 10 },
                { 12779, Quick_Healing_Potion, 60 },
                { 12779, Energy_Ginseng, 70 },
                { 12779, Gems_A_Grade, 100 },
                
                // King Squash
                { 13016, Adena, 100 },
                { 13016, Quick_Healing_Potion, 100 },
                { 13016, Crystal_S_Grade, 10 },
                { 13016, Giant_Codex, 10 },
                { 13016, Lunargent, 15 },
                { 13016, High_Grade_LS76, 50 },
                { 13016, Firework, 100 },
                { 13016, Baguette_Herb_1, 100 },
                { 13016, Baguette_Herb_2, 80 },
				{ 13016, Blessed_SOE, 80 },
				{ 13016, Energy_Ginseng, 100 },
				{ 13016, Ancient_Enchant_Weapon_A, 5 },
				
                
                // Emperor Squash
                { 13017, Adena, 100 },
                { 13017, Top_Grade_LS76, 10 },
                { 13017, Gems_S_Grade, 100 },
                { 13017, Crystal_S_Grade, 100 },
                { 13017, Hellfire_oil, 5 },
                { 13017, Energy_Ginseng, 100 },
                { 13017, Blessed_SOE, 70 },
                { 13017, Giant_Codex, 30 },
                { 13017, Large_Firework, 100 },
				{ 13017, Ancient_Enchant_Armour_A, 10 },
                { 13017, Baguette_Herb_2, 100 },
                { 13017, Baguette_Herb_3, 100 }
        };
        
        @Override
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
                        if(attacker.getActiveWeaponItem() != null && contains(CHRONO_LIST, attacker.getActiveWeaponItem().getItemId()))
                        {
                                ChronoText(npc);
                                npc.setIsInvul(false);
                                npc.getStatus().reduceHp(10, attacker);
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
                if (contains(targets, npc) && contains(SQUASH_LIST, npc.getNpcId()) && (skill.getId() == NECTAR_SKILL))
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
                return super.onSkillSee(npc, caster, skill, targets, isPet);
        }
        
        @Override
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
                                        if(drop[1] > 6000)
                                        {
                                                int adenaCount = Rnd.get(50000,150000);
                                                if (drop[1] == 57)
                                                {
                                                        ((L2MonsterInstance)mob).dropItem(player, drop[1], adenaCount);
                                                }
                                                else
                                                {
                                                        ((L2MonsterInstance)mob).dropItem(player, drop[1], 1);
                                                }
                                        }
                                        else
                                        {
                                                int adenaCount = Rnd.get(50000,200000);
                                                if (drop[1] == 57)
                                                {
                                                        ((L2MonsterInstance)mob).dropItem(player, drop[1], adenaCount);
                                                }
                                                else
                                                {
                                                        ((L2MonsterInstance)mob).dropItem(player, drop[1], Rnd.get(2, 6));
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
                final int _random = Rnd.get(100);
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
                addSpawn(npcId, npc.getX(), npc.getY(), npc.getZ(), npc.getHeading(), false, 60000);
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
                
                for (final int mob : SQUASH_LIST)
                {
                        addAttackId(mob);
                        addKillId(mob);
                        addSpawnId(mob);
                        addSkillSeeId(mob);
                }

                addStartNpc(MANAGER);
                addFirstTalkId(MANAGER);
                addTalkId(MANAGER);
        }
        
        @Override
        public String onFirstTalk(L2Npc npc, L2PcInstance player)
        {
            String htmltext = "";
            QuestState st = player.getQuestState(getName());
            if (st == null)
            {
                final Quest q = QuestManager.getInstance().getQuest(getName());
                st = q.newQuestState(player);
            }
            htmltext = npc.getNpcId() + ".htm";
            return htmltext;
        }
        
        public static void main(String[] args)
        {
            new SquashEvent(-1,qn,"events");
            if (EventsConfig.SQUASH_STARTED)
                _log.info("Event System: Squash Event loaded.");
        }
}