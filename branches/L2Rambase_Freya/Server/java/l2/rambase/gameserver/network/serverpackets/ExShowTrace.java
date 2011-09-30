/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.model.L2Object;

import javolution.util.FastList;


public final class ExShowTrace extends L2GameServerPacket
{
    private final List<Trace> _traces = new FastList<Trace>();
    
    public void addTrace(int x, int y, int z, int time)
    {
        _traces.add(new Trace(x, y, z, time));
    }
    
    public void addTrace(L2Object obj, int time)
    {
        this.addTrace(obj.getX(), obj.getY(), obj.getZ(), time);
    }
    
    static final class Trace
    {
        public final int _x;
        public final int _y;
        public final int _z;
        public final int _time;
        
        public Trace(int x, int y, int z, int time)
        {
            _x = x;
            _y = y;
            _z = z;
            _time = time;
        }
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] FE:67 ExShowTrace";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0x67);
        
        writeH(_traces.size());
        for (Trace t : _traces)
        {
            writeD(t._x);
            writeD(t._y);
            writeD(t._z);
            writeH(t._time);
        }
    }    
}