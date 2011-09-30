package donate;

import l2.rambase.gameserver.model.quest.Quest;

public class DonateConfig extends Quest
{
	private static final String qn = "DonateConfig";
	
	public static final int SPECIAL_MEMBER_ITEM = 53;
	/**
	 * Configuration
	 * @param boolean
	 */
	public static final boolean AIOBUFFER_STARTED = false;
	public static final boolean GLOBALGK_STARTED = true;
	public static final boolean DONATE_NPC_STARTED = true;
	public static final boolean DONATE_ITEMS = true;
	public static final boolean MAMMON_NPC_STARTED = true;
	/**
	 * Configuration for abonament options
	 */
	public static final boolean ABONAMENT_OPTIONS = true;
	/**
	 * Donate Items Configuration
	 */
	public static final int AIO_MARK  = 13063;
	public static final int GK_MARK  = 5249;
	public static final int BUFF_MARK2  = 13070;
	public static final int MERCH_MARK = 13127;
	public static final int DONATOR_COIN = 13693;
	/**
	 * Items Configuration
	 */
	public static final int ANCIENT_ADENA = 5575;
	public static final int RED_SEAL_STONE = 6360;
	public static final int GREEN_SEAL_STONE = 6361;
	public static final int BLUE_SEAL_STONE = 6362;
	/**
	 * Cost Configuration
	 */
	public static final int AIO_PAY = 250;
	public static final int GK_PAY = 100;
	public static final int MERCHANT_PAY = 50;
	/**
	 * Time Configuration
	 */
	public static final boolean AIO_DOUBLE_TIME = false;
	
	public DonateConfig(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}
	
	public static void main(String[] args)
	{
		new DonateConfig(-1,qn,"donate");
		_log.warning("Donate System: Configuration for donate");
	}
}