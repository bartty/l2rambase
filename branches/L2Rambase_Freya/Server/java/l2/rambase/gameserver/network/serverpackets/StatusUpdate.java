/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.ArrayList;

import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;


public final class StatusUpdate extends L2GameServerPacket
{
    private static final String _S__1A_STATUSUPDATE = "[S] 18 StatusUpdate";
    private static final int HP_MOD = 10000000;
    
    public static final int LEVEL = 0x01;
    public static final int EXP = 0x02;
    public static final int STR = 0x03;
    public static final int DEX = 0x04;
    public static final int CON = 0x05;
    public static final int INT = 0x06;
    public static final int WIT = 0x07;
    public static final int MEN = 0x08;

    public static final int CUR_HP = 0x09;
    public static final int MAX_HP = 0x0a;
    public static final int CUR_MP = 0x0b;
    public static final int MAX_MP = 0x0c;

    public static final int SP = 0x0d;
    public static final int CUR_LOAD = 0x0e;
    public static final int MAX_LOAD = 0x0f;

    public static final int P_ATK = 0x11;
    public static final int ATK_SPD = 0x12;
    public static final int P_DEF = 0x13;
    public static final int EVASION = 0x14;
    public static final int ACCURACY = 0x15;
    public static final int CRITICAL = 0x16;
    public static final int M_ATK = 0x17;
    public static final int CAST_SPD = 0x18;
    public static final int M_DEF = 0x19;
    public static final int PVP_FLAG = 0x1a;
    public static final int KARMA = 0x1b;

    public static final int CUR_CP = 0x21;
    public static final int MAX_CP = 0x22;

    private int _objectId;
    private int _maxHp = -1;
    private ArrayList<Attribute> _attributes;

    class Attribute
    {
        /** id values
         * 09 - current health
         * 0a - max health
         * 0b - current mana
         * 0c - max mana
         *
         */
        public int id;
        public int value;

        Attribute(int pId, int pValue)
        {
            id = pId;
            value = pValue;
        }
    }

    public StatusUpdate(int objectId)
    {
        _attributes = new ArrayList<Attribute>();
        _objectId = objectId;
        L2Object obj = L2World.getInstance().findObject(objectId);
		if (obj != null && obj instanceof L2Attackable)
		{
			_maxHp = ((L2Character) obj).getMaxVisibleHp();
		}
    }

	public StatusUpdate(L2Object object)
	{
		_attributes = new ArrayList<Attribute>();
		_objectId = object.getObjectId();
		if (object instanceof L2Attackable)
			_maxHp = ((L2Character) object).getMaxVisibleHp();
	}
	
    public void addAttribute(int id, int level)
    {
    	if (_maxHp != -1)
		{
			if (id == MAX_HP)
				level = HP_MOD;
			else if (id == CUR_HP)
			{
				level = (int) ((level / (float)_maxHp) * HP_MOD);
			}
		}
		_attributes.add(new Attribute(id, level));
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0x18);
        writeD(_objectId);
        writeD(_attributes.size());

        for (Attribute temp: _attributes)
        {
            writeD(temp.id);
            writeD(temp.value);
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__1A_STATUSUPDATE;
    }
}
