/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.knownlist;

import java.util.logging.Logger;

import l2.rambase.gameserver.MonsterRace;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2RaceManagerInstance;
import l2.rambase.gameserver.network.serverpackets.DeleteObject;


public class RaceManagerKnownList extends NpcKnownList
{
    protected static final Logger _log = Logger.getLogger(RaceManagerKnownList.class.getName());
    // =========================================================
    // Data Field

    // =========================================================
    // Constructor
    public RaceManagerKnownList(L2RaceManagerInstance activeChar)
    {
        super(activeChar);
    }

    // =========================================================
    // Method - Public
    @Override
	public boolean addKnownObject(L2Object object)
    {
        if (!super.addKnownObject(object)) return false;
        return true;
    }

    @Override
	protected boolean removeKnownObject(L2Object object, boolean forget)
    {
        if (!super.removeKnownObject(object, forget)) return false;

        if (object instanceof L2PcInstance)
        {
            //_log.info("Sending delete monsrac info.");
            DeleteObject obj = null;
            for (int i=0; i<8; i++)
            {
                obj = new DeleteObject(MonsterRace.getInstance().getMonsters()[i]);
                ((L2PcInstance)object).sendPacket(obj);
            }
        }

        return true;
    }

    // =========================================================
    // Method - Private

    // =========================================================
    // Property - Public
    @Override
	public L2RaceManagerInstance getActiveChar() { return (L2RaceManagerInstance)super.getActiveChar(); }
}