/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.L2Npc;


public class MonRaceInfo extends L2GameServerPacket
{
    protected static final Logger _log = Logger.getLogger(MonRaceInfo.class.getName());
    private static final String _S__DD_MonRaceInfo = "[S] e3 MonRaceInfo";
    private int _unknown1;
    private int _unknown2;
    private L2Npc[] _monsters;
    private int[][] _speeds;

    public MonRaceInfo(int unknown1 , int unknown2, L2Npc[] monsters, int[][] speeds)
    {
        /*
         * -1 0 to initial the race
         * 0 15322 to start race
         * 13765 -1 in middle of race
         * -1 0 to end the race
         */
        _unknown1   = unknown1;
        _unknown2   = unknown2;
        _monsters   = monsters;
        _speeds     = speeds;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0xe3);

        writeD(_unknown1);
        writeD(_unknown2);
        writeD(8);

        for (int i=0; i<8; i++)
        {
             writeD(_monsters[i].getObjectId());                         //npcObjectID
            writeD(_monsters[i].getTemplate().npcId+1000000);   //npcID
            writeD(14107);                                              //origin X
            writeD(181875 + (58 * (7-i)));                                  //origin Y
            writeD(-3566);                                              //origin Z
            writeD(12080);                                              //end X
            writeD(181875 + (58 * (7-i)));                                  //end Y
            writeD(-3566);                                              //end Z
            writeF(_monsters[i].getTemplate().fCollisionHeight);                  //coll. height
            writeF(_monsters[i].getTemplate().fCollisionRadius);                  //coll. radius
            writeD(120);            // ?? unknown
            for (int j=0; j<20; j++)
            {
                if  (_unknown1 == 0 )
                {
                    writeC(_speeds[i][j]);
                }
                else
                    writeC(0);
            }
            writeD(0);
            writeD(0); // CT2.3 special effect
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__DD_MonRaceInfo;
    }
}