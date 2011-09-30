package events;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.model.quest.Quest;

public class EventsConfig extends Quest
{
	private static final String qn = "EventsConfig";
	
	/**
	 * Master of Enchanting date
	 * @author Deyoun
	 * @category Retail
	 * 
	 * Event polega na enchantowaniu specjalnej laski za kupione spcjalne enchanty i wymienieniu jej na nagrody.
	 * 
	 * Styczeñ 1-31
	 **/
	public static boolean MY_STARTED = getEvent("MasterOfEnchanting");
	 /** 
	 * The Valentine Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Event polega na zbieraniu kawa³ków serca i z³o¿eniu ich - po oddaniu ca³ego serca dostaje siê nagrodê.
	 * 
	 * Luty 2-28
	 **/
	public static boolean TVE_STARTED = getEvent("TheValentineEvent");	
	public static boolean TVE_ACTIVE_DROP = getEvent("TheValentineEvent");
	/**
	 * Holly Cow Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Event polegaj¹cy na przywo³aniu za kupione scrolle krowy i karmienie jej lub dojenie. 
	 * Z dojenia otrzymujemy mleko daj¹ce buff, je¿eli dojenie siê nie uda poka¿e siê byk.
	 * Byki atakuj¹ oraz dropi¹ dodatkowe rzeczy
	 * 
	 * Marzec 1-31
	 * */
	public static boolean HC_STARTED = getEvent("HollyCow");
	/**
	 * April Fool's Day date
	 * @author Deyoun
	 * @category Custom
	 * Event polega na zbieraniu paczek. Po zebraniu odpowiedniej iloœci wymieniamy je na dobr¹ lub z³¹ nagrodê. 
	 * 
	 * Kwiecieñ 1-6
	 **/
	public static boolean AP_STARTED = getEvent("AprilFools");
	/**
	 * Ninja Adventures Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Polega na przy³¹czeniu siê do losowej wioski i walki dla niej.
	 * Po przy³¹czeniu siê do wybranej wioski nale¿y zabijaæ wybrane potwory aby pokaza³ siê kot ninja, który jest celem.
	 * Za zabicie kotów ninja s¹ punkty dla wojowników Ninja oraz dla wiosek. Za zsumowane odpowiednio punkty mo¿na wybraæ nagrody z listy.
	 * 
	 * 
	 * Kwiecieñ 7-30
	 **/
	public static boolean NA_STARTED = getEvent("NinjaAdventures");	
	/**
	 * Super Star Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Polega na zbieraniu gwiazd z instancji oraz wymianie gwiazd na nagrody u specjalnego npc.
	 * 
	 * Maj 1-31
	 */
	public static boolean SS_STARTED = getEvent("SuperStar");
	/** 
	 * Squish and Squash Event date
	 * @author Deyoun
	 * @category Retail
	 * 
	 * Event polega na podlewaniu oraz zabijaniu specjaln¹ broni¹ du¿ych owoców.
	 * Po zabiciu tych owoców otrzymujemy specjalne nagrody.
	 * 
	 * Czerwiec 1-30
	 **/
	public static boolean SQUASH_STARTED = getEvent("SquashEvent");
	public static boolean SQUASH_DROP_ACTIVE = getEvent("SquashEvent");
	 /** 
	 * L2Day Event date
	 * @author Deyoun
	 * @category Retail
	 * 
	 * Event polega na zbieraniu liter i uk³adaniu z nich s³ów pokazanych przez NPC.
	 * Za u³o¿one s³owo dostajemy nagrody wypisane u NPC.
	 * 
	 * Lipiec 1-31
	 **/
	public static boolean L2DAY_STARTED = getEvent("L2Day");
	public static boolean L2DAY_ACTIVE_DROP = getEvent("L2Day");
	/**	
	 * Heavy Medal Event
	 * @author Deyoun
	 * @category Retail
	 * 
	 * Event polega na zbieraniu dwóch typów medali. 
	 * Jedne z tych medali s¹ u¿ywane w grze która pozwala na wybranie lepszych nagród.
	 * Nagrody mo¿na otrzymaæ za medale.
	 *  
	 * Sierpieñ 1-31
	 **/
	public static boolean HM_STARTED = getEvent("HeavyMedal");
	public static boolean HM_ACTIVE_DROP = getEvent("HeavyMedal");
	 /** 
	 * SchoolDays date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Event polega na uratowaniu wiedzy œwiata L2. 
	 * Nale¿y zbieraæ ksi¹¿ki do nauki skilli swojej klasy.
	 * Za zebrane ksi¹¿ki mo¿emy u NPC otrzymaæ nagrody.
	 * 
	 * Wrzesieñ 1-30 
	 **/
	public static boolean SD_STARTED = getEvent("SchoolDays");
	public static boolean SD_ACTIVE_DROP = getEvent("SchoolDays");
	/**
	 * Trick or Transmutation Event date
	 * @author Deyoun
	 * @category Retail
	 * 
	 * Event polega na wydropieniu kluczy, za pomoc¹ których mo¿na otworzyæ skrzyniê.
	 * Ze skrzyni otrzymujemy materia³y oraz recepty potrzebne nam do z³o¿enia kamienia filozofów.
	 * Gdy otworzymy z³o¿ony kamieñ otrzymamy losow¹ nagrodê wypisan¹ u npc.
	 * 
	 * PaŸdziernik 1-31
	 **/
	public static boolean TOT_STARTED = getEvent("TrickorTransmutation");	
	public static boolean TOT_ACTIVE_DROP = getEvent("TrickorTransmutation");
	/** 
	 * Hallowed You Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Event polega na zabijaniu duchów które wychodz¹ z cia³ potworów ¿yj¹cych na cmentarzach.
	 * Za zabicie ducha otrzymujemy nagrodê w postaci, lizaka.
	 * Lizaki mo¿na u¿yæ, w celu regeneracji Vitality lub wymieniæ na specjalne nagrody.
	 * 
	 * Listopad 1-30
	 **/
	public static boolean HY_STARTED = getEvent("HallowedYou");
	public static boolean HY_ACTIVE_DROP = getEvent("HallowedYou");
	/**
	 * Christmas Is Here Event date
	 * @author Deyoun
	 * @category Custom
	 * 
	 * Event polega na zbieraniu zagubionych skarpet.
	 * Skarpety mo¿na wymieniæ na nagrody œwi¹teczne u miko³aja.
	 * 
	 * Grudzieñ 1-31
	 **/
	public static boolean CH_STARTED = getEvent("ChristmasIsHere");
	public static boolean CH_ACTIVE_DROP = getEvent("ChristmasIsHere");
	/**
	 * Get Event data from sql
	 */
	private static boolean getEvent(String eventName) 
	{
		boolean state = false;
		int eventMonth = 0;
		int eventStartDay = 0;
		int eventEndDay = 0;
		
		Connection con = null;
		try
		{			
	        con = L2DatabaseFactory.getInstance().getConnection();
	        PreparedStatement statement = con.prepareStatement("SELECT * FROM events WHERE name=?");
	        statement.setString(1, eventName);
	        ResultSet result = statement.executeQuery();
	        if (result.next())
	        {
	        	eventMonth = result.getInt("month");
	        	eventStartDay = result.getInt("start_day");
	        	eventEndDay = result.getInt("end_day");
	        }
			statement.close();
		}
		catch (Exception e)
		{
			_log.warning("could not read friend data:" + e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		int Month = Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH)) + 1;
		int Day = Integer.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		try
		{
			if(eventMonth == Month && Day >= eventStartDay && Day <= eventEndDay)
			{
				return true;
			}
		}
		catch(Exception e)
		{			
		}
		return state;
	}
	
	// Events Config is created by L2Rambase Project
	// How to change a event
	// 1. Change Boolean value of event
	// 2. Use command //quest_reload EventsConfig	
	// 3. Use command //quest_reload EventName
	// 4. Now you can use event.
	
	/**
	 * Character Birthday
	 */
	public static final int ADVENTURER_HAT = 10250;
	public static final int BIRTHDAY_CAKE_SKILL = 5950;
	/**
	 * Dimensional Merchant
	 */
	public static final boolean DIM_MERCHANT_STARTED = true;
	public static final int DIM_VITAMIN_LEVEL = 77;
	public static final int DIM_COIN  = 13419;
	/**
	 * Heavy Medal
	 */	
	public static final int HM_MEDAL  = 6392;
	public static final int HM_GLITTERING_MEDAL  = 6393;
	/**
	 * Holly Cow 
	 */	
	public static final int HC_ADENA = 57;
	public static final int HC_MILK = 14739;
	public static final int HC_MILK_COW_SCROLL = 14724;
	public static final int HC_HEAD_MILK_COW_SCROLL = 14725;
	public static final int HC_GLOOM_MILK_COW_SCROLL = 14726;
	public static final int HC_GLOOM_HEAD_MILK_COW_SCROLL = 14727;
	/**
	 * L2 Day
	 */
	public static final int L2DAY_A  = 3875;
	public static final int L2DAY_C  = 3876;
	public static final int L2DAY_E  = 3877;
	public static final int L2DAY_F  = 3878;
	public static final int L2DAY_G  = 3879;
	public static final int L2DAY_H  = 3880;
	public static final int L2DAY_I  = 3881;
	public static final int L2DAY_L  = 3882;
	public static final int L2DAY_N  = 3883;
	public static final int L2DAY_O  = 3884;
	public static final int L2DAY_R  = 3885;
	public static final int L2DAY_S  = 3886;
	public static final int L2DAY_T  = 3887;
	public static final int L2DAY_II  = 3888;
	public static final int L2DAY_Y  = 13417;
	public static final int L2DAY_5  = 13418;
	public static final int L2DAY_GUIDANCE = 3926;
	public static final int L2DAY_DEATH_WHISPER = 3927;
	public static final int L2DAY_FOCUS = 3928; 
	public static final int L2DAY_GREATER_ACUMEN = 3929;
	public static final int L2DAY_HASTE = 3930;
	public static final int L2DAY_AGILITY = 3931;
	public static final int L2DAY_MYSTIC_EMPOWER = 3932;
	public static final int L2DAY_MIGHT = 3933;
	public static final int L2DAY_WINDWALK = 3934;
	public static final int L2DAY_SHIELD = 3935;
	public static final int L2DAY_BSOE = 1538;
	public static final int L2DAY_BSOR = 3936;
	public static final int L2DAY_MANA_REGENERATION = 4218;
	public static final int L2DAY_ADENA = 57;
	public static final int L2DAY_ANCIENT_ADENA = 5575;
	public static final int L2DAY_MAGE_COCKTAIL = 20394;
	public static final int L2DAY_FIGHTER_COCKTAIL = 20393;
	/**
	 * Master Of Enchanting
	 */	
	public static final int MY_STAFF = 13539;
	public static final int MY_SCROLL = 13540;
	public static final int MY_ADENA = 57;
	public static final int MY_STAFF_PRICE	= 1000;
	public static final int MY_SCROLL_24_PRICE	= 6000;
	public static final int MY_SCROLL_24_TIME = 6;
	public static final int MY_SCROLL_1_PRICE = 77777;
	public static final int MY_SCROLL_10_PRICE	= 777770;
	public static final int MY_HAND_SLOT = 16;
	public static final int[] MY_HAT_SHADOW_REWARD	= { 13074, 13075, 13076 };
	public static final int[] MY_HAT_EVENT_REWARD = { 13518, 13519, 13522 };
	public static final int[] MY_CRYSTAL_REWARD = { 9570, 9571, 9572 };
	/**
	 * Ninja Adventures
	 */	
	public static final int NA_HAIRBAND = 7060;
	public static final int NA_ADENA = 57;
	public static final int NA_ANCIENT_ADENA = 5575;
	/**
	 * The Valentine Event
	 */
	public static final int TVE_RECIPE = 20191;
	public static final int TVE_REAGENT1 = 20194;
	public static final int TVE_REAGENT2 = 20192;
	public static final int TVE_REAGENT3 = 20193;
	public static final int TVE_CAKE1 = 20195;
	public static final int TVE_CAKE2 = 20196;
	public static final int TVE_CAKE3 = 20197;
	public static final int TVE_CAKE4 = 20198;
	/**
	 * Trick or Transmutation
	 */
	public static final int TOT_KEY  = 9205;
	public static final int TOT_RED_STONE = 9162;
	public static final int TOT_BLUE_STONE = 9163;
	public static final int TOT_ORANGE_STONE = 9164;
	public static final int TOT_BLACK_STONE = 9165;
	public static final int TOT_WHITE_STONE = 9166;
	public static final int TOT_GREEN_STONE = 9167;
	public static final int TOT_STONE_ORE = 9168;
	public static final int TOT_STONE_FORMULA = 9169;	
	public static final int TOT_MAGIC_REAGENTS = 9170;
	/**
	 * HallowedYou
	 */
	public static final int HALLOWEEN_CANDY = 15430;
	/**
	 * ChristmasIsHere
	 */
	public static final int CH_CHRISTMAS_SOCK = 14612;
	/**
	 * SchoolDays
	 */
	
	/**
	 * Super Star
	 */
	public static final int SS_BACKUP_STONE_WEP_D = 12362;
	public static final int SS_BACKUP_STONE_WEP_C = 12363;
	public static final int SS_BACKUP_STONE_WEP_B = 12364;
	public static final int SS_BACKUP_STONE_WEP_A = 12365;
	public static final int SS_BACKUP_STONE_WEP_S = 12366;
	public static final int SS_BACKUP_STONE_ARM_D = 12367;
	public static final int SS_BACKUP_STONE_ARM_C = 12368;
	public static final int SS_BACKUP_STONE_ARM_B = 12369;
	public static final int SS_BACKUP_STONE_ARM_A = 12370;
	public static final int SS_BACKUP_STONE_ARM_S = 12371;
	
	/**
	 * Text - this event is disabled
	 */
	public static final String EVENT_DISABLED = "<html><title>Event Message</title><body><center><img src=\"L2UI_CH3.onscrmsg_pattern01_1\" width=300 height=32 align=left><br>" +
	"<br><br><br><br><br><br><b><font color=LEVEL>THIS EVENT<br>IS<br>NOW<br>DISABLED</font></b><br><br><br><br><br><br><br><br><br><br><br><br>" +
	"<br><img src=\"L2UI_CH3.onscrmsg_pattern01_2\" width=300 height=32 align=left></center></body></html>";
	
	
	public static void setValue(boolean value, boolean status)
	{
		value = status;		
	}
	
	public EventsConfig(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}

	public static void main(String[] args)
	{
		new EventsConfig(-1,qn,"events");		
		_log.warning("===============================");
		_log.warning("Events System: Configuration for events:");
		_log.warning("HeavyMedal value " + String.valueOf(HM_STARTED));
		_log.warning("HollyCow value " + String.valueOf(HC_STARTED));
		_log.warning("L2Day value " + String.valueOf(L2DAY_STARTED));
		_log.warning("MasterOfEnchanting value " + String.valueOf(MY_STARTED));
		_log.warning("NinjaAdventures value " + String.valueOf(NA_STARTED));
		_log.warning("SuperStar value " + String.valueOf(SS_STARTED));
		_log.warning("TheValentineEvent value " + String.valueOf(TVE_STARTED));
		_log.warning("TrickorTransmutation value " + String.valueOf(TOT_STARTED));
		_log.warning("HallowedYou value " + String.valueOf(HY_STARTED));
		_log.warning("ChristmasIsHere value " + String.valueOf(CH_STARTED));
		_log.warning("SquashEvent value " + String.valueOf(SQUASH_STARTED));
		_log.warning("AprillFools value " + String.valueOf(AP_STARTED));
		_log.warning("SchoolDays value " + String.valueOf(SD_STARTED));
		_log.warning("===============================");		
	}
}