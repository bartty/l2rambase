package vehicles.AirShipGludioGracia;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.instancemanager.AirShipManager;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.Location;
import l2.rambase.gameserver.model.VehiclePathPoint;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.NpcSay;

/**
 * 
 * @author L2Rambase Project
 *
 */
public class AirShipGludioGracia extends Quest implements Runnable
{
	private static final int[] CONTROLLERS = {32607, 32609};

	private static final int GLUDIO_DOCK_ID = 10;
	private static final int GRACIA_DOCK_ID = 11;

	private static final Location OUST_GLUDIO = new Location(-149379, 255246, -80);
	private static final Location OUST_GRACIA = new Location(-186563, 243590, 2608);

	private static final VehiclePathPoint[] GLUDIO_TO_WARPGATE =
	{
		new VehiclePathPoint(-151202, 252556, 231),
		new VehiclePathPoint(-160403, 256144, 222),
		new VehiclePathPoint(-167874, 256731, -509, 0, 41035) // teleport: x,y,z,speed=0,heading
	};

	private static final VehiclePathPoint[] WARPGATE_TO_GRACIA =
	{
		new VehiclePathPoint(-169763, 254815, 282),
		new VehiclePathPoint(-171822, 250061, 425),
		new VehiclePathPoint(-172595, 247737, 398),
		new VehiclePathPoint(-174538, 246185, 39),
		new VehiclePathPoint(-179440, 243651, 1337),
		new VehiclePathPoint(-182601, 243957, 2739),
		new VehiclePathPoint(-184952, 245122, 2694),
		new VehiclePathPoint(-186936, 244563, 2617)
	};

	private static final VehiclePathPoint[] GRACIA_TO_WARPGATE =
	{
		new VehiclePathPoint(-187801, 244997, 2672),
		new VehiclePathPoint(-188520, 245932, 2465),
		new VehiclePathPoint(-189932, 245243, 1682),
		new VehiclePathPoint(-191192, 242969, 1523),
		new VehiclePathPoint(-190408, 239088, 1706),
		new VehiclePathPoint(-187475, 237113, 2768),
		new VehiclePathPoint(-184673, 238433, 2802),
		new VehiclePathPoint(-184524, 241119, 2816),
		new VehiclePathPoint(-182129, 243385, 2733),
		new VehiclePathPoint(-179440, 243651, 1337),
		new VehiclePathPoint(-174538, 246185, 39),
		new VehiclePathPoint(-172595, 247737, 398),
		new VehiclePathPoint(-171822, 250061, 425),
		new VehiclePathPoint(-169763, 254815, 282),
		new VehiclePathPoint(-168067, 256626, 343),
		new VehiclePathPoint(-157261, 255664, 221, 0, 64781) // teleport: x,y,z,speed=0,heading
	};

	private static final VehiclePathPoint[] WARPGATE_TO_GLUDIO =
	{
		new VehiclePathPoint(-153414, 255385, 221),
		new VehiclePathPoint(-149548, 258172, 221),
		new VehiclePathPoint(-146884, 257097, 221),
		new VehiclePathPoint(-146672, 254239, 221),
		new VehiclePathPoint(-147855, 252712, 206),
		new VehiclePathPoint(-149378, 252552, 198)
	};

	private final L2AirShipInstance _ship;
	private int _cycle = 0;

	private boolean _foundAtcGludio = false;
	private L2Npc _atcGludio = null;
	private boolean _foundAtcGracia = false;
	private L2Npc _atcGracia = null;

	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (player.getQuestState("AirShipGludioGracia") == null)
		{
			newQuestState(player);
		}
		if (_ship.isInDock() && _ship.isInsideRadius(player, 600, true, false))
		{
			_ship.addPassenger(player);
		}
		return "";
	}

	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (player.getQuestState("AirShipGludioGracia") == null)
		{
			newQuestState(player);
		}
		String htmltext = "";
		if (npc.getNpcId() == 32607)
		{
			htmltext = "<html><body>Airship Controller:<br>";
			htmltext += "You see a floating piece of stone filled with a mysterious power. The green light is flashing, so there doesn't appear to be anything wrong with its function.<br>";
			htmltext += "Using this should allow you to board the Airship headed for the continent of Gracia.<br>";
			htmltext += "<a action=\"bypass -h Quest AirShipGludioGracia\">Board the Airship.</a><br>";
			htmltext += "<a action=\"bypass -h Quest\">Quest</a></body></html>";
		}
		else if (npc.getNpcId() == 32609)
		{
			htmltext = "<html><head><body>Airship Controller:<br>";
			htmltext += "You see a floating piece of stone filled with a mysterious power. The green light is flashing, so there doesn't appear to be anything wrong with its function.<br>";
			htmltext += "Using this should allow you to board the Airship headed for the continent of Gracia.<br>";
			htmltext += "<a action=\"bypass -h Quest AirShipGludioGracia\">Board the Airship.</a><br>";
			htmltext += "<a action=\"bypass -h Quest\">Quest</a></body></html>";
		}
		return htmltext;
	}

	public AirShipGludioGracia(int questId, String name, String descr)
	{
		super(questId, name, descr);
		for (int id : CONTROLLERS)
		{
			addStartNpc(id);
			addFirstTalkId(id);
			addTalkId(id);
		}
		_ship = AirShipManager.getInstance().getNewAirShip(-149378, 252552, 198, 33837);
		_ship.setOustLoc(OUST_GLUDIO);
		_ship.registerEngine(this);
		_ship.runEngine(60000);
	}

	public void run()
	{
		try
		{
			switch (_cycle)
			{
				case 0:
					broadcastInGludio("The regularly scheduled airship that flies to the Gracia continent has departed.");
					_ship.setInDock(0);
					_ship.executePath(GLUDIO_TO_WARPGATE);
					break;
				case 1:
//					_ship.teleToLocation(-167874, 256731, -509, 41035, false);
					_ship.setOustLoc(OUST_GRACIA);
					ThreadPoolManager.getInstance().scheduleGeneral(this, 5000);
					break;
				case 2:
					_ship.executePath(WARPGATE_TO_GRACIA);
					break;
				case 3:
					broadcastInGracia("The regurarly scheduled airship has arrived. It will depart for the Aden continent in 1 minute.");
					_ship.setInDock(GRACIA_DOCK_ID);
					_ship.oustPlayers();
					ThreadPoolManager.getInstance().scheduleGeneral(this, 60000);
					break;
				case 4:
					broadcastInGracia("The regurarly scheduled airship that flies to the Aden continent has departed.");
					_ship.setInDock(0);
					_ship.executePath(GRACIA_TO_WARPGATE);
					break;
				case 5:
//					_ship.teleToLocation(-157261, 255664, 221, 64781, false);
					_ship.setOustLoc(OUST_GLUDIO);
					ThreadPoolManager.getInstance().scheduleGeneral(this, 5000);
					break;
				case 6:
					_ship.executePath(WARPGATE_TO_GLUDIO);
					break;
				case 7:
					broadcastInGludio("The regularly scheduled airship has arrived. It will depart for the Gracia continent in 1 minute.");
					_ship.setInDock(GLUDIO_DOCK_ID);
					_ship.oustPlayers();
					ThreadPoolManager.getInstance().scheduleGeneral(this, 60000);
					break;
			}
			_cycle++;
			if (_cycle > 7)
				_cycle = 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private final void broadcastInGludio(String msg)
	{
		if (!_foundAtcGludio)
		{
			_foundAtcGludio = true;
			_atcGludio = findController();
		}
		if (_atcGludio != null)
			_atcGludio.broadcastPacket(new NpcSay(_atcGludio.getObjectId(), Say2.SHOUT, _atcGludio.getNpcId(), msg));
	}

	private final void broadcastInGracia(String msg)
	{
		if (!_foundAtcGracia)
		{
			_foundAtcGracia = true;
			_atcGracia = findController();
		}
		if (_atcGracia != null)
			_atcGracia.broadcastPacket(new NpcSay(_atcGracia.getObjectId(), Say2.SHOUT, _atcGracia.getNpcId(), msg));
	}

	private final L2Npc findController()
	{
		// check objects around the ship
		for (L2Object obj : L2World.getInstance().getVisibleObjects(_ship, 600))
		{
			if (obj instanceof L2Npc)
			{
				for (int id : CONTROLLERS)
				{
					if (((L2Npc)obj).getNpcId() == id)
						return (L2Npc)obj;
				}
			}
		}
		return null;
	}

	public static void main(String[] args)
	{
		new AirShipGludioGracia(-1, "AirShipGludioGracia", "vehicles");
	}
}
