/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.ArrayList;

import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.gameserver.templates.item.L2Item;

public final class SystemMessage extends L2GameServerPacket
{
	private static final int TYPE_ZONE_NAME = 7;
	private static final int TYPE_ITEM_NUMBER = 6;
	private static final int TYPE_FORTRESS = 5; // maybe not only for fortress, rename if needed
	private static final int TYPE_SKILL_NAME = 4;
	private static final int TYPE_ITEM_NAME = 3;
	private static final int TYPE_NPC_NAME = 2;
	private static final int TYPE_NUMBER = 1;
	private static final int TYPE_TEXT = 0;
	private static final String _S__7A_SYSTEMMESSAGE = "[S] 62 SystemMessage";
	private int _messageId;
	
	private final ArrayList<SysMsgData> _info = new ArrayList<SysMsgData>();
	private int _skillLvL = 1;

	protected class SysMsgData
	{
		protected final int type;
		protected final Object value;
		
		protected SysMsgData(int t, Object val)
		{
			type = t;
			value = val;
		}
	}
	
	public SystemMessage(SystemMessageId messageId)
	{
		_messageId = messageId.getId();
	}

	/**
	 * Use SystemMessage(SystemMessageId messageId) where possible instead
	 */
	public SystemMessage(int messageId)
	{
		_messageId = messageId;
	}

 	public static SystemMessage sendString(String msg)
	{
 		SystemMessage sm = new SystemMessage(SystemMessageId.S1);
 		sm.addString(msg);

 		return sm;
	}

	public SystemMessage addString(String text)
	{
		_info.add(new SysMsgData(TYPE_TEXT, text));
		return this;
	}

	public SystemMessage addFortId(int number)
	{
		_info.add(new SysMsgData(TYPE_FORTRESS, number));
		return this;
	}

	public SystemMessage addNumber(int number)
	{
		_info.add(new SysMsgData(TYPE_NUMBER, number));
		return this;
	}

	public SystemMessage addItemNumber(long number)
	{
		_info.add(new SysMsgData(TYPE_ITEM_NUMBER, number));
		return this;
	}
	
	public SystemMessage addCharName(L2Character cha)
	{
		if (cha instanceof L2Npc)
		{
			if (((L2Npc)cha).getTemplate().serverSideName)
				return addString(((L2Npc)cha).getTemplate().name);
			else			
				return addNpcName((L2Npc)cha);
		}
		if (cha instanceof L2PcInstance)
			return addPcName((L2PcInstance)cha);
		if (cha instanceof L2Summon)
		{
			if (((L2Summon)cha).getTemplate().serverSideName)
				return addString(((L2Summon)cha).getTemplate().name);
			else			
				return addNpcName((L2Summon)cha);
		}
		return addString(cha.getName());
	}

	public SystemMessage addPcName(L2PcInstance pc)
	{
		return addString(pc.getAppearance().getVisibleName());
	}

	public SystemMessage addNpcName(L2Npc npc)
	{
		return addNpcName(npc.getTemplate());
	}

	public SystemMessage addNpcName(L2Summon npc)
	{
		return addNpcName(npc.getNpcId());
	}

	public SystemMessage addNpcName(L2NpcTemplate tpl)
	{
		if (tpl.isCustom())
			return addString(tpl.name);
		return addNpcName(tpl.npcId);
	}

	public SystemMessage addNpcName(int id)
	{
		_info.add(new SysMsgData(TYPE_NPC_NAME, 1000000+id));
		return this;
	}

	public SystemMessage addItemName(L2ItemInstance item)
	{
		return addItemName(item.getItem().getItemId());
	}

	public SystemMessage addItemName(L2Item item)
	{
		return addItemName(item.getItemId());
	}

	public SystemMessage addItemName(int id)
	{
		_info.add(new SysMsgData(TYPE_ITEM_NAME, id));
		return this;
	}

	public SystemMessage addZoneName(int x, int y, int z)
	{
		int[] coord = {x, y, z};
		_info.add(new SysMsgData(TYPE_ZONE_NAME, coord));
		return this;
	}

	public SystemMessage addSkillName(L2Effect effect)
	{
		return addSkillName(effect.getSkill());
	}

	public SystemMessage addSkillName(L2Skill skill)
	{
		if (skill.getId() != skill.getDisplayId()) //custom skill -  need nameId or smth like this.
			return addString(skill.getName());
		return addSkillName(skill.getId(), skill.getLevel());
	}

	public SystemMessage addSkillName(int id)
	{
		return addSkillName(id, 1);
	}

	public SystemMessage addSkillName(int id, int lvl)
	{
		_info.add(new SysMsgData(TYPE_SKILL_NAME, id));
		_skillLvL = lvl;

		return this;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x62);

		writeD(_messageId);
		writeD(_info.size());

		for (SysMsgData data : _info)
		{
			int t = data.type;

			writeD(t);

			switch (t)
			{
				case TYPE_TEXT:
					writeS((String)data.value);
					break;
				case TYPE_ITEM_NUMBER:
					writeQ((Long)data.value);
					break;
				case TYPE_ITEM_NAME:
				case TYPE_FORTRESS:
				case TYPE_NUMBER:
				case TYPE_NPC_NAME:
					writeD((Integer)data.value);
					break;
				case TYPE_SKILL_NAME:
					writeD((Integer)data.value); // Skill Id
					writeD(_skillLvL); // Skill lvl
					break;
				case TYPE_ZONE_NAME:
					int[] coords = (int[])data.value;
					writeD(coords[0]);
					writeD(coords[1]);
					writeD(coords[2]);
					break;
			}
		}
	}

	@Override
	public String getType()
	{
		return _S__7A_SYSTEMMESSAGE;
	}

	public int getMessageID()
	{
		return _messageId;
	}
}